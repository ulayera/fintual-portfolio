package cl.ulayera.fintual.portfolio.service;

import cl.ulayera.fintual.portfolio.model.Portfolio;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReturnService {

    BigDecimal absolute(Portfolio portfolio, LocalDate start, LocalDate end);

    BigDecimal annualizedReturn(Portfolio portfolio, LocalDate start, LocalDate end);
}
