# Store-Application JAVA Graphical User Interface
This is a small project in COMP1406-W20

Please do not copy this code as I do not permit it :)
These codes are for viewed only

Please read the Set Up JavaFX Instructions before running the codes.

I will assume you have your IntelliJ IDEA set up and ready to run the codes.

In case you unsuccessfully set up and run the codes, here is what you can expect to see what the codes can do:

When you compile the codes, the Windows will pop up

![image](https://user-images.githubusercontent.com/62405278/109598332-14cdd580-7ae7-11eb-8e67-333190cf1cad.png)

- You can select any items inside the "Store Stock" box, items you selected will be printed to the "Current Cart" box.
- Each items in "Store Stock" have the quantity of 10, once the quantity reaches 0, the items will disappear off the "Store Stock" box.
- If "Current Cart" is not empty, you can click the "Complete Sale" button to complete sale. 
- The price of sold items will be updated in the "Revenue" box, and they will be accumulated for each time "Complete Sale" button is pressed (given the Current Cart is not empty)
- The "# Sales" box indicates the number of times you complete a sale.
- The "$/Sale" is the average Revenue made over total amount of Sales.
- The "Most Popular Items" box indicates the top 3 most sold items in Store Stock. By default, all items in Store Stock have sold amount of 0, the info will be update after first sale is made.
- The "Remove from Cart" button will remove selected items from Current Cart back to Store Stock.
- The "Reset Store" button does exactly what it is named for: reset everything to the default state.
- Few things to keep in mind: each of the buttons is enabled/disabled depending on the state of the store, eg:
  + If Current Cart is empty, the "Remove From Cart" and "Complete Sale" buttons are disabled.
  + By default, all buttons are disabled, only "Reset" button is enabled.
  + After "Complete Sale" button is pressed, all buttons are back to their default state.
  + The "Remove" button is disabled until an item is selected from Current Cart

Here is a video of me trying the program, for your better understanding: 

https://youtu.be/PFTyaiEfy5M

#Thanks for reading!








