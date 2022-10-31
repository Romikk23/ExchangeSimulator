// Клас акції
public class Share {
    private String Name; // Ім'я
    private int amount; // Кількість
    private int price; // Ціна

    public Share(String name, int amount, int price) { // Конструктор
        Name = name;
        this.amount = amount;
        this.price = price;
    }

    // Геттери
    public String getName() {
        return Name;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public void changePrice() { // Змінюємо ціну на 3%
        if((int) ( Math.random() * 2 ) == 1){ // Отримуємо 1 або 0, якщо 1 - зменшуємо на 3%
            price -= (price*3/100);
        } else { // Якщо 0 - збільшуємо на 3%
            price += (price*3/100);
        }
    }

    public void minusAmount(int n){ // Зменшуємо кількість акцій на стільки, скільки їх купив покупець
        amount -= n;
    }
}
