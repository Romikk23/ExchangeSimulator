import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// Потік акції, для оновлення ціни
public class ChangePriceThread implements Runnable{
    private Share share; // Акція

    public ChangePriceThread(Share share) { // Конструктор
        this.share = share;
    }

    @Override
    public void run() {
        while(true) {
            try {
                // Засинаємо на 30 секунд
                Thread.sleep(1000*30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Формуємо реальну час та дату в потрібному форматі
            LocalDateTime dateTime = LocalDateTime.now();
            String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // Змінюємо ціну акції
            share.changePrice();
            System.out.printf("\n%s Ціна акцій компанії %s змінилась. Поточна вартість: %d", formattedDate, share.getName(), share.getPrice());
        }
    }
}
