package cl.ulayera.fintual.portfolio.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {

    private Map<Stock, BigDecimal> stocksAndWeights = new HashMap<>();

    public Map<Stock, BigDecimal> getStocksAndWeights() {
        return stocksAndWeights;
    }

    public void loadStock(Stock stock, BigDecimal weight) {
        stocksAndWeights.put(stock, weight);
    }

    public boolean isWeightValid() {
        return stocksAndWeights.values().stream()
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO).compareTo(BigDecimal.ONE) == 0;
    }
}
