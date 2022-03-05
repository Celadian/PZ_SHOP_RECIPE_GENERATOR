package mod;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RecipeGenerator {

    String urlInput = "D:\\Projects\\Pz_Recipe_Generator\\input\\";
    String urlOutput = "D:\\Projects\\Pz_Recipe_Generator\\output\\";


    public RecipeGenerator() {
        this.getFilesInInputFolder().forEach(file -> {
            this.saveFormattedFile(this.generateRecipe(file), file);
        });
    }

    private List<String> getFilesInInputFolder() {
        return Arrays.asList(new File("D:\\Projects\\Pz_Recipe_Generator\\input").list());
    }

    ;

    private String generateRecipe(String file) {
        List<Recipe> non_formatted_recipe_list = this.getFileAsList(file);
        return this.getFormattedString(non_formatted_recipe_list);
    }

    private List<Recipe> getFileAsList(String file) {
        List<Recipe> non_formatted_recipe_list = new ArrayList<Recipe>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(urlInput + "fileName"));
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line);
                String[] lineSplit = line.split(",", 5);
                System.out.println(lineSplit);
                non_formatted_recipe_list.add(new Recipe(lineSplit[0], lineSplit[1], lineSplit[2], lineSplit[3], lineSplit[4]));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return non_formatted_recipe_list;
    }

    private String getFormattedString(List<Recipe> non_formatted_recipe_list) {
        StringBuilder sb = new StringBuilder();
        sb.append("module Base {");
        non_formatted_recipe_list.forEach(recipe -> {
            switch (recipe.txnType) {
                case "Buy":
                    sb.append("\n\trecipe " + recipe.txnType + " " + recipe.dispName + " {");
                    sb.append("\n\t\tMoney=" + recipe.money + ",");
                    sb.append("\n\t\tResult:" + recipe.itemName + ",");
                    sb.append("\n\t\tTime:" + recipe.time + ",");
                    sb.append("\n\t\tkeep WalkieTalkie1/WalkieTalkie2/WalkieTalkie3/WalkieTalkie4/WalkieTalkie5/HamRadio1/HamRadio2/RadioMakeShift/HamRadioMakeShift/WalkieTalkieMakeShift,");
                    sb.append("\n\t\tCategory: SShop " + recipe.txnType + ",");
                    sb.append("\n\t}");
                    break;
                case "Sell":
                    sb.append("\n\trecipe " + recipe.txnType + "" + recipe.dispName + " {");
                    sb.append("\n\t\t" + recipe.itemName + ",");
                    sb.append("\n\t\tResult: Money=" + recipe.money + ",");
                    sb.append("\n\t\tTime:" + recipe.time + ",");
                    sb.append("\n\t\tkeep WalkieTalkie1/WalkieTalkie2/WalkieTalkie3/WalkieTalkie4/WalkieTalkie5/HamRadio1/HamRadio2/RadioMakeShift/HamRadioMakeShift/WalkieTalkieMakeShift,");
                    sb.append("\n\t\tCategory: SShop " + recipe.txnType + ",");
                    sb.append("\n\t}");
                    break;
                default:
                    break;
            }

        });
        sb.append("\n}");

        return sb.toString();
    }

    private void saveFormattedFile(String formattedRecipeList, String outputFileName) {
        Scanner scanner = new Scanner(System.in);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(urlOutput + outputFileName));
            writer.write(formattedRecipeList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Recipe succesfully generated. Press enter to close this window");
        scanner.nextLine();
    }
}

