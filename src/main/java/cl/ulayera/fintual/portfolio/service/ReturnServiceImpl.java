package cl.ulayera.fintual.portfolio.service;

import static java.time.temporal.ChronoUnit.DAYS;

import cl.ulayera.fintual.portfolio.model.Portfolio;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class ReturnServiceImpl implements ReturnService {

    public static final BigDecimal BIG_DECIMAL_HUNDRED = BigDecimal.valueOf(100L);

    @Override
    public BigDecimal absolute(Portfolio portfolio, LocalDate start, LocalDate end) {
        return portfolio.getStocksAndWeights().entrySet().stream()
            .map(entry -> {
                BigDecimal weight = entry.getValue();
                BigDecimal startingPrice = entry.getKey().value(start);
                BigDecimal endingPrice = entry.getKey().value(end);
                return endingPrice.subtract(startingPrice).divide(startingPrice, 3, RoundingMode.HALF_UP).multiply(weight);
            })
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO).stripTrailingZeros().multiply(BigDecimal.valueOf(100L));
    }

    @Override
    public BigDecimal annualizedReturn(Portfolio portfolio, LocalDate start, LocalDate end) {
        double absolute = absolute(portfolio, start, end).doubleValue();
        long totalDays = DAYS.between(start, end);
        return BigDecimal.valueOf(Math.pow(100 + absolute, 365d / (double) totalDays)).subtract(BigDecimal.ONE).setScale(3, RoundingMode.HALF_UP).multiply(BIG_DECIMAL_HUNDRED).stripTrailingZeros();
    }
}
