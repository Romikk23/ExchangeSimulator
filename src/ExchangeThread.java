import java.util.ArrayList;

// Потік біржі
public class ExchangeThread implements Runnable{
    private ArrayList<Share> shares; // Список акцій на біржі
    private ArrayList<Purchaser> purchasers; // Список покупців

    public ExchangeThread(ArrayList<Share> shares, ArrayList<Purchaser> purchasers) { // Конструктор
        this.shares = shares;
        this.purchasers = purchasers;
    }

    @Override
    public void run() {
        for(Share share : shares){ // Проходимось по всіх акціях та запускаємо для кожної свій потік
            Thread threadShare = new Thread(new ChangePriceThread(share));
            threadShare.start();
        }
        for(Purchaser purchaser : purchasers){ // Проходимо по всіх покупцях та запускаємо для кожного свій потік
            Thread threadShare = new Thread(new BuyShareThread(purchaser, shares));
            threadShare.start();
        }
    }
}
