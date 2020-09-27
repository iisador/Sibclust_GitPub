package ru.gitpub.ruspost.services;

import java.nio.ByteBuffer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import ru.gitpub.ruspost.domain.entities.Order;
import ru.gitpub.ruspost.domain.repos.ContServRepository;
import ru.gitpub.ruspost.domain.repos.ContrServGeoRepository;
import ru.gitpub.ruspost.domain.repos.OrderRepository;
import ru.gitpub.ruspost.rest.InputRequest;
import ru.gitpub.ruspost.rest.resources.ChainResource;
import ru.gitpub.ruspost.rest.resources.OrderResource;

import static java.util.stream.Collectors.toList;

@Service
public class ChainService {

    private static final List<double[]> rates = Arrays.asList(
            new double[]{0.5, 1.0, 4.0},
            new double[]{0.5, 1.0, 1.0},
            new double[]{0.5, 4.0, 1.0});

    private static final UUID FIRST_MILE = UUID.fromString("9c43245d-7989-4e05-bdb1-cac99bc44155");

    private static final UUID FF = UUID.fromString("a4379f8c-ca3b-402d-b192-d78fac8f815f");

    private static final UUID LAST_MILE = UUID.fromString("d4ca479d-ec3d-4d15-9fda-4a826cb2ef57");

    private static final RowMapper<Pair> PAIR_ROW_MAPPER = new RowMapper<Pair>() {
        public UUID getGuidFromByteArray(byte[] bytes) {
            ByteBuffer bb = ByteBuffer.wrap(bytes);
            long high = bb.getLong();
            long low = bb.getLong();
            return new UUID(high, low);
        }

        @Override
        public Pair mapRow(ResultSet resultSet, int i) throws SQLException {
            UUID service = getGuidFromByteArray((byte[]) resultSet.getObject("service"));
            UUID geo = getGuidFromByteArray((byte[]) resultSet.getObject("geozone"));
            Double rate = resultSet.getDouble("rating");
            Long time = resultSet.getLong("time");
            return new Pair(geo, service, rate, time);
        }
    };

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    private final ContrServGeoRepository contrServGeoRepository;

    private final ContServRepository contServRepository;

    private final OrderRepository orderRepository;

    public ChainService(NamedParameterJdbcOperations namedParameterJdbcOperations, ContrServGeoRepository contrServGeoRepository, ContServRepository contServRepository, OrderRepository orderRepository) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.contrServGeoRepository = contrServGeoRepository;
        this.contServRepository = contServRepository;
        this.orderRepository = orderRepository;
    }

    public ChainResource calcChain(InputRequest inputRequest) {
        UUID fromGeozone = UUID.fromString(inputRequest.getFromGeozone());
        UUID toGeozone = UUID.fromString(inputRequest.getToGeozone());
        List<UUID> suppls = inputRequest.getSupplementaries().stream()
                .map(UUID::fromString)
                .collect(toList());
        Order first = getOrder(Collections.singletonList(fromGeozone), FIRST_MILE, rates.get(inputRequest.getSpeed()), inputRequest.getWeight(), suppls);
        Order ff = getOrder(Arrays.asList(fromGeozone, toGeozone), FF, rates.get(inputRequest.getSpeed()), inputRequest.getWeight(), Collections.emptyList());
        Order last = getOrder(Collections.singletonList(toGeozone), LAST_MILE, rates.get(inputRequest.getSpeed()), inputRequest.getWeight(), Collections.emptyList());
        first.setChild(ff);
        ff.setParent(first);

        ff.setChild(last);
        last.setParent(ff);

        first = orderRepository.save(first);

        return new ChainResource(Arrays.asList(toResource(first),
                toResource(ff),
                toResource(last)));
    }

    private Order getOrder(List<UUID> geozones, UUID type, double[] rates, Long weight, List<UUID> suppls) {
        Pair pair;

        if (suppls.isEmpty()) {
            SqlParameterSource params = new MapSqlParameterSource("type", type)
                    .addValue("geozones", geozones)
                    .addValue("mRate", rates[0])
                    .addValue("mPrice", rates[1])
                    .addValue("mTime", rates[2])
                    .addValue("weight", weight);
            String query = "select cg.id as geozone, cserv.id as service, cg.AVGEXECTIME as time, ((c.RATING * :mRate) + (c.INDEXPRICE * :mPrice) + (c.INDEXTIME * :mTime)) as rating from CONTRACTOR c,\n" +
                           "                 CONTR_SERV_GEO cg,\n" +
                           "                 CONTR_SERV_SUPPL csup,\n" +
                           "                 CONTR_SERV cserv\n" +
                           "where 1 = 1\n" +
                           "  AND c.ID = cg.CONTRAGENTID\n" +
                           "  AND cserv.CONTRACTORID = c.ID\n" +
                           "  AND csup.CONTRSERVID = cserv.ID\n" +
                           "  AND cserv.SERVICETYPEID = :type\n" +
                           "  AND :weight between cserv.WEIGHTMIN AND cserv.WEIGHTMAX\n" +
                           "  AND cg.GEOZONEID in(:geozones)\n" +
                           "  ORDER BY rating desc\n" +
                           "  LIMIT 1";
            pair = namedParameterJdbcOperations.queryForObject(query, params, PAIR_ROW_MAPPER);
        } else {
            SqlParameterSource params = new MapSqlParameterSource("type", type)
                    .addValue("geozones", geozones)
                    .addValue("suppls", suppls)
                    .addValue("mRate", rates[0])
                    .addValue("mPrice", rates[1])
                    .addValue("mTime", rates[2])
                    .addValue("weight", weight);
            String query = "select cg.id as geozone, cserv.id as service, cg.AVGEXECTIME as time, ((c.RATING * :mRate) + (c.INDEXPRICE * :mPrice) + (c.INDEXTIME * :mTime)) as rating from CONTRACTOR c,\n" +
                           "                 CONTR_SERV_GEO cg,\n" +
                           "                 CONTR_SERV_SUPPL csup,\n" +
                           "                 CONTR_SERV cserv\n" +
                           "where 1 = 1\n" +
                           "  AND c.ID = cg.CONTRAGENTID\n" +
                           "  AND cserv.CONTRACTORID = c.ID\n" +
                           "  AND csup.CONTRSERVID = cserv.ID\n" +
                           "  AND cserv.SERVICETYPEID = :type\n" +
                           "  AND :weight between cserv.WEIGHTMIN AND cserv.WEIGHTMAX\n" +
                           "  AND cg.GEOZONEID in(:geozones)\n" +
                           "  AND csup.SUPPLEMENTARYID in(:suppls)\n" +
                           "  ORDER BY rating desc\n" +
                           "  LIMIT 1";
            pair = namedParameterJdbcOperations.queryForObject(query, params, PAIR_ROW_MAPPER);
        }

        return new Order(contrServGeoRepository.findById(pair.geo).orElseThrow(RuntimeException::new),
                contServRepository.findById(pair.service).orElseThrow(RuntimeException::new), pair.rate, pair.time, weight);
    }

    private OrderResource toResource(Order o) {
        OrderResource r = new OrderResource();
        r.setId(o.getId());
        r.setGeozoneId(o.getContrServGeo().getGeozone().getName());
        r.setName(o.getContrServGeo().getContractor().getName());
        r.setServiceType(o.getService().getServiceType().getName());
        r.setSum(o.getContrServGeo().getPrice());
        r.setRating(o.getRating());
        r.setTime(o.getTime());
        r.setWeight(o.getWeight());
        return r;
    }

    @AllArgsConstructor
    private static class Pair {
        UUID geo;

        UUID service;

        Double rate;

        Long time;
    }
}
