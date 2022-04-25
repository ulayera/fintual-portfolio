package cl.ulayera.fintual.portfolio;

import static java.time.LocalDate.of;

import cl.ulayera.fintual.portfolio.model.Portfolio;
import cl.ulayera.fintual.portfolio.model.Stock;
import cl.ulayera.fintual.portfolio.service.ReturnService;
import cl.ulayera.fintual.portfolio.service.ReturnServiceImpl;
import cl.ulayera.fintual.portfolio.util.PopulateStockUtil;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FintualPortfolio {

    public static void main(String[] args) {
        // setup
        Stock a = new Stock();
        Stock b = new Stock();

        LocalDate jan1st2021 = of(2021,1,1);
        LocalDate dec31st2025 = of(2025,12,31);

        PopulateStockUtil.populate(jan1st2021, dec31st2025, a, true);
        PopulateStockUtil.populate(jan1st2021, dec31st2025, b, false);

        Portfolio portfolio = new Portfolio();
        portfolio.loadStock(a, BigDecimal.valueOf(0.4d));
        portfolio.loadStock(b, BigDecimal.valueOf(0.6d));

        // validation
        if (!portfolio.isWeightValid()) {
            System.err.println("Weights must sum 1");
            System.exit(1);
        }

        ReturnService returnService = new ReturnServiceImpl();

        // output
        System.out.println("Profit during 2021 is: " + returnService.absolute(portfolio, of(2021,1,1), of(2021,12,31)) + "%");
        System.out.println("Profit during 2022 is: " + returnService.absolute(portfolio, of(2022,1,1), of(2022,12,31)) + "%");
        System.out.println("Profit during 2023 is: " + returnService.absolute(portfolio, of(2023,1,1), of(2023,12,31)) + "%");
        System.out.println("Profit during 2024 is: " + returnService.absolute(portfolio, of(2024,1,1), of(2024,12,31)) + "%");
        System.out.println("Profit during 2025 is: " + returnService.absolute(portfolio, of(2025,1,1), of(2025,12,31)) + "%");
        System.out.println("Profit from Jan 1st, 2021 to Dec 31st, 2025 is: " + returnService.absolute(portfolio, of(2021,1,1), of(2025,12,31)) + "%");
        System.out.println("Annualized return from Jan 1st, 2022 to Dec 31st, 2025 is: " + returnService.annualizedReturn(portfolio, jan1st2021, dec31st2025) + "%");
        System.out.println("Annualized return from Jan 1st, 2022 to Dec 31st, 2021 is: " + returnService.annualizedReturn(portfolio, of(2021,1,1), of(2022,12,31)) + "%");
    }

}
