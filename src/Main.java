import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Створюємо список акцій та покупців
        ArrayList<Share> shares = new ArrayList<>();
        ArrayList<Purchaser> purchasers = new ArrayList<>();
        // Заповнюємо список акцій
        shares.add(new Share("APPL", 100, 141));
        shares.add(new Share("COKE", 1000, 387));
        shares.add(new Share("IBM", 200, 137));
        // Заповнюємо список покупців
        ArrayList<Share> reqSharesAlice = new ArrayList<>();
        reqSharesAlice.add(new Share("APPL", 10, 137));
        reqSharesAlice.add(new Share("COKE", 20, 364));
        purchasers.add(new Purchaser("Alice", reqSharesAlice));

        ArrayList<Share> reqSharesBob = new ArrayList<>();
        reqSharesBob.add(new Share("APPL", 10, 145));
        reqSharesBob.add(new Share("IBM", 20, 137));
        purchasers.add(new Purchaser("Bob", reqSharesBob));

        ArrayList<Share> reqSharesCharlie = new ArrayList<>();
        reqSharesCharlie.add(new Share("COKE", 30, 398));
        purchasers.add(new Purchaser("Charlie", reqSharesCharlie));

        // Створюємо потік біржі і запускаємо його
        Thread threadShare = new Thread(new ExchangeThread(shares, purchasers));
        System.out.println("\n\tБіржа працює");
        threadShare.start();
        try {
            Thread.sleep(1000*60*10); // Час скільки буде працювати біржа
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Вивід фінальної статистики
        System.out.println("\n\n\n\tБіржа не працює");
        System.out.println("\n\tЦіна акцій:\n");
        for(Share share : shares){
            System.out.printf("\tКомпанія %s \tЦіна: %d \tКількість: %d\n", share.getName(), share.getPrice(), share.getAmount());
        }
        System.out.println("\n\tРезультат купівлі");
        for(Purchaser purchaser : purchasers){
            System.out.printf("\nПокупець %s:", purchaser.getName());
            for(Share share : purchaser.getBoughtShares()){
                System.out.printf("\n Спроба купівлі акції %s для %s успішна. Куплено %d акцій", share.getName(), purchaser.getName(), share.getAmount());
            }
        }

        System.exit(0);

    }
}