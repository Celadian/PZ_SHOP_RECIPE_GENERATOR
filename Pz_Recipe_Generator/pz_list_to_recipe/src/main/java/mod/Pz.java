package mod;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pz {

    String urlInput = "D:\\Projects\\Pz_Recipe_Generator\\input\\recipes.txt";
    String urlOutput = "D:\\Projects\\Pz_Recipe_Generator\\output\\recipes_out.txt";
    List<Recipe> recipes = new ArrayList<Recipe>();


    public Pz(){
        this.getInputList();
        this.saveFormattedFile();
    }


    private void getInputList() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(urlInput));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                String[] lineSplit = line.split(",", 5);
                System.out.println(lineSplit);
                this.recipes.add(new Recipe(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3], lineSplit[4]));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFormattedFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(urlOutput));
            writer.write(this.getFormattedString());
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private String getFormattedString(){
        StringBuilder sb = new StringBuilder();
        sb.append("module Base {");
        this.recipes.forEach(recipe -> {
            sb.append("\n\trecipe " + recipe.txnType + "" + recipe.dispName + " {");
            sb.append("\n\t\tMoney=" + recipe.money + ",");
            sb.append("\n\t\tResult:" + recipe.resultName + ",");
            sb.append("\n\t\tTime:" + recipe.time + ",");
            sb.append("\n\t\tkeep WalkieTalkie1/WalkieTalkie2/WalkieTalkie3/WalkieTalkie4/WalkieTalkie5/HamRadio1/HamRadio2/RadioMakeShift/HamRadioMakeShift/WalkieTalkieMakeShift,");
            sb.append("\n\t\tCategory: SShop " + recipe.txnType + ",");
            sb.append("\n\t}");
        });
        sb.append("\n}");

        return sb.toString();
    }

    private class Recipe{
        String txnType;
        String dispName;
        String money;
        String resultName;
        String time;


        public Recipe(String txnType, String dispName, String resultName, String money, String time) {
            this.txnType = txnType;
            this.dispName = dispName;
            this.money = money;
            this.resultName = resultName;
            this.time = time;
        }
    }

}
