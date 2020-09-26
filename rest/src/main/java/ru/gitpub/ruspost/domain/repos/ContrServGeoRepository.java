package ru.gitpub.ruspost.domain.repos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gitpub.ruspost.domain.entities.ContrServGeo;

@Repository
public interface ContrServGeoRepository extends JpaRepository<ContrServGeo, UUID> {

    @Query(value = "select cg.* from CONTRACTOR c,\n" +
                   "     CONTR_SERV_GEO cg,\n" +
                   "     CONTR_SERV_SUPPL csup,\n" +
                   "     CONTR_SERV cserv\n" +
                   "where 1 = 1\n" +
                   "  AND c.ID = cg.CONTRSERVID\n" +
                   "  AND cserv.CONTRACTORID = c.ID\n" +
                   "  AND csup.CONTRSERVID = cserv.ID\n" +
                   "  AND cserv.SERVICETYPEID = '9c43245d79894e05bdb1cac99bc44155'\n" +
                   "  AND cg.GEOZONEID = :fromZoneId\n" +
                   "  AND csup.SUPPLEMENTARYID in (:suppls)\n" +
                   "  ORDER BY cg.PRICE asc\n" +
                   "  LIMIT 1", nativeQuery = true)
    Optional<ContrServGeo> getFirstContractor(@Param("fromZoneId") UUID fromGeozone, @Param("suppls") List<UUID> suppls);

    @Query(value = "select cg.*\n" +
                   "from CONTRACTOR c,\n" +
                   "     CONTR_SERV_GEO cg,\n" +
                   "     CONTR_SERV_SUPPL csup,\n" +
                   "     CONTR_SERV cserv\n" +
                   "where 1 = 1\n" +
                   "  AND c.ID = cg.CONTRSERVID\n" +
                   "  AND cserv.CONTRACTORID = c.ID\n" +
                   "  AND csup.CONTRSERVID = cserv.ID\n" +
                   "  AND cserv.SERVICETYPEID = 'd4ca479dec3d4d159fda4a826cb2ef57'\n" +
                   "  AND cg.GEOZONEID = :toZoneId\n" +
                   "  AND csup.SUPPLEMENTARYID in (:suppls)\n" +
                   "  order by cg.PRICE asc limit 1", nativeQuery = true)
    Optional<ContrServGeo> getLastContractor(@Param("toZoneId") UUID toGeozone, @Param("suppls") List<UUID> suppl);

    @Query(value = "select cg.*\n" +
                   "from CONTRACTOR c,\n" +
                   "     CONTR_SERV_GEO cg,\n" +
                   "     CONTR_SERV_SUPPL csup,\n" +
                   "     CONTR_SERV cserv\n" +
                   "where 1 = 1\n" +
                   "  AND c.ID = cg.CONTRSERVID\n" +
                   "  AND cserv.CONTRACTORID = c.ID\n" +
                   "  AND csup.CONTRSERVID = cserv.ID\n" +
                   "  AND cserv.SERVICETYPEID = 'a4379f8cca3b402db192d78fac8f815f'\n" +
                   "  AND cg.GEOZONEID in(:zones)\n" +
                   "  order by cg.PRICE asc limit 1;", nativeQuery = true)
    Optional<ContrServGeo> getFfCenter(@Param("zones") List<UUID> geozones);
}
