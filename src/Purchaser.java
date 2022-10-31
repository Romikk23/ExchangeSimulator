import java.util.ArrayList;
// Клас покупця
public class Purchaser {
    private String name; // Ім'я
    private ArrayList<Share> requiredShares; // Список потрібних акцій для купівлі

    private ArrayList<Share> boughtShares = new ArrayList<>(); // Список куплених акцій

    public Purchaser(String name, ArrayList<Share> requiredShares) { // Конструктор
        this.name = name;
        this.requiredShares = requiredShares;
    }

    // Геттери
    public String getName() {
        return name;
    }

    public ArrayList<Share> getRequiredShares() {
        return requiredShares;
    }

    public ArrayList<Share> getBoughtShares() {
        return boughtShares;
    }

    public void addBoughtShare(Share share) { // Якщо купили акцію, добавити в список куплених акцій
        boughtShares.add(share);
    }

}
