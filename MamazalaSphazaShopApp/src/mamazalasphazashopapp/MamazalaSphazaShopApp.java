/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mamazalasphazashopapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import za.ac.tut.bl.ItemManager;
import za.ac.tut.entity.Item;

/**
 *
 * @author Student
 */
public class MamazalaSphazaShopApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here
        Item item = null;
        int id,totNumberOfItems;
        List<Item> items = new ArrayList<>();
        ItemManager manager = new ItemManager("jdbc:mysql://localhost:3306/itemdb", "root", "root");
        int choice = userUnput();
        while (choice != 7) { 
            switch (choice) {
                case 1:
                   item = getItem(); 
                    
                    boolean isAdded = manager.add(item);
                    if (isAdded) {
                         System.out.println("New Item Added...");   
                        }else{
                        System.err.println("Error adding Item..");
                    }
                    break;
                case 2:
                    id = getId();
                    double unitPrice = getNewPrice();
                   boolean isUpdated = manager.update(id, unitPrice);
                    if (isUpdated) {
                        System.out.println("Unit price Updated..");
                    } else {
                        System.err.println("Error Updating unit Price");
                    }
                    break;
                case 3:
                    id = getId();
                    item = manager.get(id);
                    display(item);
                    break;
                case 4:
                    id = getId();
                    boolean isDeleted = manager.delete(id);
                    if (isDeleted) {
                        System.out.println("Item deleted");
                    } else {
                        System.err.println("Item do not exist");
                    }
                     break;
                case 5:
                    items = manager.getAll();
                    displayAll(items);
                    break;
                case 6:
                    totNumberOfItems = manager.totalOfAll();
                    displayTotal(totNumberOfItems);
                    break;
                default:
                    throw new AssertionError();
            }
           choice = userUnput(); 
        }
        if(choice == 7){
            System.out.println("GoodBye User ");
        }
    }

    private static int userUnput() {
    Scanner sc = new Scanner(System.in);
    
        System.out.print("\tSELECT ONE OF THE FOLLOWING OPTIONS\n"
                + "--------------------------------------------------------\n"
                + "Enter 1 – for adding/persisting item information into the database.\n"
                + "Enter 2 – for changing the unit price of an item\n"
                + "Enter 3 – for getting the details of a specific item.\n"
                + "Enter 4 – for removing a specific item from the database\n"
                + "Enter 5 – for displaying the details of all the items\n"
                + "Enter 6 – for displaying the number of items stored in the database\n"
                + "Enter 7 – for exiting the application\n\n"
                + "Your Choice : ");
        int choice = sc.nextInt();
        return choice;
    }

    private static Item getItem() {
       Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter Item Information\n"
                + "============================================\n");
        int id = getId();
        
        System.out.println("Enter item description");
        String description = sc.next();
        
        System.out.println("Enter Item's quantity");
        int quantity = sc.nextInt();
        
        System.out.println("Enter Item Unit Price");
        double unitPrice = sc.nextDouble();
        
        Item item = new Item(id, description, quantity, unitPrice);
        return item;
    }

    private static int getId() {
     Scanner sc = new Scanner(System.in);   
        System.out.println("Enter Item id");
        int id = sc.nextInt();
        return id;
    }

    private static double getNewPrice() {
       Scanner sc = new Scanner(System.in);   
        System.out.println("Enter new Item Price");
        double price = sc.nextDouble();
        return price;
    }

    private static void display(Item item) {
        System.out.println(item + "\n");  
        
    }

    private static void displayAll(List<Item> items) {
        for (Item item : items) {
            System.out.println(item.toString());
           
        }
        System.out.println("\n");
    }

    private static void displayTotal(int totNumberOfItems) {
        System.out.println("Total Number of item in Database are(is) " + totNumberOfItems +" \n");  
    }
    
}
