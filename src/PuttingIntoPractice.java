import java.lang.annotation.Target;
import java.util.*;
import java.util.stream.Collectors;

public class PuttingIntoPractice {
    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(

                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        List<Transaction> getTransactionsFor2011 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println(getTransactionsFor2011);

        Set<String> getUniqueCities = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .collect(Collectors.toSet());

        System.out.println(getUniqueCities);

        List<Trader> getTradersFromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .toList();

        System.out.println(getTradersFromCambridge);

        List<String> getSortedTraders = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(getSortedTraders);

        Boolean doesExistsTraderFromMilan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        System.out.println(doesExistsTraderFromMilan);

        Integer sumOfValuesFromCambridge = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum).orElse(0);

        System.out.println(sumOfValuesFromCambridge);

        Integer maxValues = transactions.stream()
                .map(Transaction::getValue)
                .max(Comparator.naturalOrder())
                .orElse(0);

        System.out.println(maxValues);

        Integer minValue = transactions.stream()
                .map(Transaction::getValue)
                .min(Comparator.naturalOrder())
                .orElse(0);

        System.out.println(minValue);
    }
}
