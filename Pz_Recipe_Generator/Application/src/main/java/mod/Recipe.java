package mod;

public class Recipe{
    String txnType;
    String dispName;
    String money;
    String itemName;
    String time;


    public Recipe(String txnType, String dispName, String itemName, String money, String time) {
        this.txnType = txnType;
        this.dispName = dispName;
        this.money = money;
        this.itemName = itemName;
        this.time = time;
    }
}
