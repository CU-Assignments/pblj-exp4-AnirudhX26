import java.util.*;

class Card {
    private String symbol;
    private int number;

    public Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }
}

public class CollectAndGroupCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<Card>> cardMap = new TreeMap<>(); // TreeMap for sorted keys

        System.out.print("Enter Number of Cards: ");
        int n = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println("Enter card " + i + ": ");
            String symbol = scanner.next();
            int number = scanner.nextInt();

            cardMap.putIfAbsent(symbol, new ArrayList<>());
            cardMap.get(symbol).add(new Card(symbol, number));
        }

        System.out.print("\nDistinct Symbols are: ");
        for (String symbol : cardMap.keySet()) {
            System.out.print(symbol + " ");
        }
        System.out.println();

        for (String symbol : cardMap.keySet()) {
            List<Card> cards = cardMap.get(symbol);
            System.out.println("\nCards in " + symbol + " Symbol");

            int sum = 0;
            for (Card card : cards) {
                System.out.println(card.getSymbol() + " " + card.getNumber());
                sum += card.getNumber();
            }

            System.out.println("Number of cards: " + cards.size());
            System.out.println("Sum of Numbers: " + sum);
        }

        scanner.close();
    }
}
