package recipe.generator.pz;

public class Recipe{
    String txnType;
    String identifier;
    String dispName;
    String money;
    String itemName;
    String time;


    public Recipe(String txnType,String identifier, String dispName, String itemName, String money, String time) {
        this.txnType = txnType;
        this.identifier = identifier;
        this.dispName = dispName;
        this.money = money;
        this.itemName = itemName;
        this.time = time;
    }
}
