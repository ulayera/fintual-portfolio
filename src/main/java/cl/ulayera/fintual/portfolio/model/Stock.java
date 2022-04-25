package cl.ulayera.fintual.portfolio.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Stock {

    private Map<LocalDate, BigDecimal> values = new HashMap<>();

    public Map<LocalDate, BigDecimal> getValues() {
        return values;
    }

    public BigDecimal value(LocalDate date) {
        return values.get(date);
    }

    public void loadValue(LocalDate date, BigDecimal value) {
        values.put(date, value);
    }
}
