package Application;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RecipeGenerator {

    //TODO: Replace with dynamic path
    //INSTRUCTIONS: replace these with the path to your project location and whatever you want to be required for trade
    String applicationPath = "D:\\Projects\\SShopRecipeGenerator";
    //WalkieTalkies
//    String keep = "WalkieTalkie1/WalkieTalkie2/WalkieTalkie3/WalkieTalkie4/WalkieTalkie5/HamRadio1/HamRadio2/RadioMakeShift/HamRadioMakeShift/WalkieTalkieMakeShift";
    //For the new trader item
    String keep = "Trader";


    public RecipeGenerator() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Constructed generator");
        try {
            this.getFilesInInputFolder().forEach(file -> {
                this.saveFormattedFile(this.generateRecipe(file), file);
            });
            System.out.println("Recipe succesfully generated. Press enter to close this window");
        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private List<String> getFilesInInputFolder() {
        return Arrays.asList(new File(applicationPath + "\\input").list());
    };

    private String generateRecipe(String file) {
        return this.getFormattedString(this.getFileAsList(file), file);
    }

    private List<Recipe> getFileAsList(String file) {
        List<Recipe> non_formatted_recipe_list = new ArrayList<Recipe>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(applicationPath + "\\input\\" + file));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                String[] lineSplit = line.split(",", 6);
                System.out.println(lineSplit);
                non_formatted_recipe_list.add(new Recipe(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3], lineSplit[4], lineSplit[5]));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return non_formatted_recipe_list;
    }

    private String getFormattedString(List<Recipe> non_formatted_recipe_list, String file) {
        StringBuilder sb = new StringBuilder();
        sb.append("module Base {");
        non_formatted_recipe_list.forEach(recipe -> {
            switch (recipe.txnType) {
                case "Buy":
                    sb.append("\n\trecipe " + recipe.txnType + " " + recipe.identifier + " | " + recipe.dispName + ": $" + recipe.money + " {");
                    sb.append(this.getMoneyDenominations(recipe.money));
                    sb.append("\n\t\tResult:" + recipe.itemName + ",");
                    sb.append("\n\t\tTime:" + recipe.time + ",");
                    sb.append("\n\t\tkeep " + this.keep + ",");
                    sb.append("\n\t\tCanBeDoneFromFloor:true,");
                    sb.append("\n\t\tCategory: SShop " + file.substring(0, file.length() - 4) + ",");
                    sb.append("\n\t}");
                    break;
                case "Sell":
                    sb.append("\n\trecipe " + recipe.txnType + " " + recipe.identifier + " | " + recipe.dispName + ": $" + recipe.money + " {");
                    sb.append("\n\t\t" + recipe.itemName + ",");
                    sb.append("\n\t\tResult: Money=" + recipe.money + ",");
                    sb.append("\n\t\tTime:" + recipe.time + ",");
                    sb.append("\n\t\tkeep " + this.keep + ",");
                    sb.append("\n\t\tCanBeDoneFromFloor:true,");
                    sb.append("\n\t\tCategory: SShop " + file.substring(0, file.length() - 4) + ",");
                    sb.append("\n\t}");
                    break;
                default:
                    break;
            }

        });
        sb.append("\n}");

        return sb.toString();
    }

    private String getMoneyDenominations(String amount_string) {
        int amount = Integer.valueOf(amount_string);
        StringBuilder sb = new StringBuilder();


        if(amount / 100000 >= 1){
            int hundred_thousands = amount / 100000;
            amount -= hundred_thousands * 100000;
            sb.append("\n\t\tAmericanExpress=" + hundred_thousands + ",");
        }
        if(amount / 10000 >= 1) {
            int ten_thousands = amount / 10000;
            amount -= ten_thousands * 10000;
            sb.append("\n\t\t10000TradeVoucher=" + ten_thousands + ",");
        }
        if(amount / 5000 >= 1) {
            int five_thousands = amount / 5000;
            amount -= five_thousands * 5000;
            sb.append("\n\t\t5000TradeVoucher=" + five_thousands + ",");
        }
        if(amount / 1000 >= 1) {
            int thousands = amount / 1000;
            amount -= thousands * 1000;
            sb.append("\n\t\t1000TradeVoucher=" + thousands + ",");
        }
        if(amount / 100 >= 1) {
            int hundreds = amount / 100;
            amount -= hundreds * 100;
            sb.append("\n\t\t100Money=" + hundreds + ",");
        }
        if(amount / 20 >= 1) {
            int twenties = amount / 20;
            amount -= twenties * 20;
            sb.append("\n\t\t20Money=" + twenties + ",");
        }
        if(amount / 10 >= 1) {
            int tens = amount / 10;
            amount -= tens * 10;
            sb.append("\n\t\t10Money=" + tens + ",");
        }
        if(amount / 5 >= 1) {
            int fives = amount / 5;
            amount -= fives * 5;
            sb.append("\n\t\t5Money=" + fives + ",");
        }
        if(amount >= 1)sb.append("\n\t\tMoney=" + amount + ",");
        return sb.toString();

    }

    private void saveFormattedFile(String formattedRecipeList, String fileName) {
        String outputFileName = fileName.substring(0 ,fileName.length() - 4);
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(applicationPath + "\\output" + "\\SShop_" + outputFileName + ".txt"));
            writer.write(formattedRecipeList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

