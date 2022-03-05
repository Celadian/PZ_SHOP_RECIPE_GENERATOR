package mod;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RecipeGenerator {

    //TODO: Replace with dynamic path
    //INSTRUCTIONS: replace these with the path to your project location
    String urlInput = "D:\\Projects\\PZ_SHOP_RECIPE_GENERATOR\\Pz_Recipe_Generator\\input";
    String urlOutput = "D:\\Projects\\PZ_SHOP_RECIPE_GENERATOR\\Pz_Recipe_Generator\\output";


    public RecipeGenerator() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Constructed generator");
        try {
            this.getFilesInInputFolder().forEach(file -> {
                this.saveFormattedFile(this.generateRecipe(file), file);
            });
            System.out.println("Recipe succesfully generated. Press enter to close this window");
            scanner.nextLine();
        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private List<String> getFilesInInputFolder() {
        return Arrays.asList(new File(urlInput).list());
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
            bufferedReader = new BufferedReader(new FileReader(urlInput + "\\" + file));
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

    private String getFormattedString(List<Recipe> non_formatted_recipe_list) {
        StringBuilder sb = new StringBuilder();
        sb.append("module Base {");
        non_formatted_recipe_list.forEach(recipe -> {
            switch (recipe.txnType) {
                case "Buy":
                    sb.append("\n\trecipe " + recipe.txnType + " " + recipe.identifier + " " + recipe.dispName + " {");
                    sb.append("\n\t\tMoney=" + recipe.money + ",");
                    sb.append("\n\t\tResult:" + recipe.itemName + ",");
                    sb.append("\n\t\tTime:" + recipe.time + ",");
                    sb.append("\n\t\tkeep WalkieTalkie1/WalkieTalkie2/WalkieTalkie3/WalkieTalkie4/WalkieTalkie5/HamRadio1/HamRadio2/RadioMakeShift/HamRadioMakeShift/WalkieTalkieMakeShift,");
                    sb.append("\n\t\tCategory: SShop " + recipe.txnType + ",");
                    sb.append("\n\t}");
                    break;
                case "Sell":
                    sb.append("\n\trecipe " + recipe.txnType + " " + recipe.identifier + " " + recipe.dispName + " {");
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

    private void saveFormattedFile(String formattedRecipeList, String fileName) {
        String outputFileName = fileName.substring(0 ,fileName.length() - 4);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(urlOutput + "\\" + outputFileName + ".txt"));
            writer.write(formattedRecipeList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

