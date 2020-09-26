package ru.gitpub.ruspost.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;
import ru.gitpub.ruspost.domain.entities.ContrServGeo;
import ru.gitpub.ruspost.domain.entities.Contractor;
import ru.gitpub.ruspost.domain.repos.ContractorRepository;

@Service
@Lazy(false)
public class IndexService {

    private ContractorRepository contractorRepository;

    private JdbcOperations jdbcOperations;

    public IndexService(ContractorRepository contractorRepository, JdbcOperations jdbcOperations) {
        this.contractorRepository = contractorRepository;
        this.jdbcOperations = jdbcOperations;
    }

    @PostConstruct
    void init() {
        updateIndexes();
    }
    public void updateIndexes() {
        Long avgPrice = getAvgPrice();
        Long avgTime = getAvgTime();

        List<Contractor> updated = contractorRepository.findAll().stream()
                .peek(c -> {
                    Long contractorPriceSum = c.getGeos().stream()
                            .mapToLong(ContrServGeo::getPrice)
                            .sum();
                    if (contractorPriceSum > 0) {
                        if((contractorPriceSum / avgPrice) > 0) {
                            c.setIndexPrice(1.0 / (contractorPriceSum / avgPrice));
                        } else {
                            c.setIndexPrice(0.0);
                        }
                    } else {
                        c.setIndexPrice(0.0);
                    }

                    Long contractorTime = c.getGeos().stream()
                            .mapToLong(ContrServGeo::getAvgExecTime)
                            .sum();
                    if (contractorTime > 0) {
                        if((contractorTime / avgTime) > 0) {
                            c.setIndexTime(1.0 / (contractorTime / avgTime));
                        } else {
                            c.setIndexTime(0.0);
                        }
                    } else {
                        c.setIndexTime(0.0);
                    }

                    Double successProbability = c.getStatistic().getCompleted() * 1.0 / c.getStatistic().getAssignedOverall();
                    Double cancelProbability = c.getStatistic().getCancelled() * 1.0 / c.getStatistic().getAssignedOverall();
                    Double lostProbability = c.getStatistic().getFailed() * 1.0 / c.getStatistic().getAssignedOverall();
                    c.setRating((4.0 * successProbability) - (2.0 * lostProbability) - cancelProbability);
                })
                .collect(Collectors.toList());
        contractorRepository.saveAll(updated);
    }

    private Long getAvgTime() {
        return jdbcOperations.queryForObject("select avg(AVGEXECTIME) from CONTR_SERV_GEO", Long.class);
    }

    private Long getAvgPrice() {
        return jdbcOperations.queryForObject("select avg(PRICE) from CONTR_SERV_GEO", Long.class);
    }
}
