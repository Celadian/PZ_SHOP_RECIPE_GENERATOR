
# PZ_SHOP_RECIPE_GENERATOR
Converts csv files from input into recipe text files in output.
---

### Data Source
 - https://docs.google.com/spreadsheets/d/10prL77W5hVMRwuRhYPEHwfJQ6hDm3HQoUp84thegcZk/edit?usp=sharing
 - You can go to the link here
 - Either copy it to edit it or just use it as is. 
 - Big thank you to the heros who do the wiki. It could use a little work if someone has time. The stuff marked
 - blue on the sheets page is stuff that had the wrong item id name on the wiki. 
---
### Instructions from Sheets to recipes
**In Google sheets(Or excel):**
  - Go to File > Download > Comma Separated Value (.csv) 
---
**In any text editor**
  - Simply make a comma separated list with the data in the correct order
---
**CSV Order**
  - Comma separated values are
  - TransactionType "Buy/Sell", Identifier "clothes", DisplayName "T-Shirt Red", "T_Shirt_Red_KY", Cost, Time(To craft)
---
**Choosing a File Name**
  - The name of the file e.x: (Test.csv), will become the name of the category
  - So the example file above becomes SShop_Test.txt with formatted recipes.
  - You can put as many files as you like in the input folder
  - Running the application again will overrwite the existing files in output or create new ones if none exist. 
---
  **Run the program**
  - Simply run the program and check the output folder to find your recipes. 
---
### *Feel free to modify this application or use it however you want.* 
### *Happy modding.* 

