import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
// Потік покупця
public class BuyShareThread implements Runnable{
    private Purchaser purchaser; // Покупець
    private ArrayList<Share> shares; // Список акцій на біржі
    public BuyShareThread(Purchaser purchaser, ArrayList<Share> shares) { // Конструктор
        this.purchaser = purchaser;
        this.shares = shares;
    }

    @Override
    public void run() {
        while(true) {
            for (Share requiredShare : purchaser.getRequiredShares()) { // Проходимось по всіх акціях, які хоче купити покупець
                for (Share share : shares) { // Проходимось по всіх акціях
                    if (share.getName().equals(requiredShare.getName())) { // Якщо в нас ім'я акції, яку хоче купити покупець і акцією співпало
                        // Формуємо реальну час та дату в потрібному форматі
                        LocalDateTime dateTime = LocalDateTime.now();
                        String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        // Якщо ціна акції така, за якою покупець хоче придбати акцію І в нас доступна така кількість акцій
                        if (requiredShare.getPrice() == share.getPrice() && share.getAmount() > requiredShare.getAmount()) {
                            // Купуємо акцію
                            share.minusAmount(requiredShare.getAmount()); // Віднімаємо n акцій в загальній кількості акцій
                            purchaser.addBoughtShare(requiredShare); // Добавляємо n акцій в список куплених акцій покупця
                            System.out.printf("\n%s Спроба купівлі акції %s для %s успішна. Куплено %d акцій", formattedDate, share.getName(), purchaser.getName(), requiredShare.getAmount());
                        } else {
                            // Якщо не вдалось купити акцію, виводимо про не успішність
                            System.out.printf("\n%s Спроба купівлі акції %s для %s не успішна", formattedDate, share.getName(), purchaser.getName());
                        }
                    }
                }
            }
            try {
                // Засинаємо на 5 секунд
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
