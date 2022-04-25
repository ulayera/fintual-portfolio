package cl.ulayera.fintual.portfolio.util;

import cl.ulayera.fintual.portfolio.model.Stock;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;

public class PopulateStockUtil {

    public static final double STOCK_INITIAL_VALUE = 1000.00d;

    public static void populate(LocalDate start, LocalDate end, Stock a, Boolean good) {
        BigDecimal bigDecimal = BigDecimal.valueOf(STOCK_INITIAL_VALUE);
        for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
            a.loadValue(date, bigDecimal.setScale(2, RoundingMode.HALF_UP));
            double random = new Random().nextDouble();
            if (good) {
                random = 1 + (random / 500);
            } else {
                random = 1 - (random / 1000);
            }
            bigDecimal = bigDecimal.multiply(BigDecimal.valueOf(random));
        }
    }
}
