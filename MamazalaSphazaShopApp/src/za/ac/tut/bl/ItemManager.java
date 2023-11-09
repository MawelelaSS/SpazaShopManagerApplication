/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.bl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.tut.entity.Item;
import za.ac.tut.entity.ItemInterface;

/**
 *
 * @author Student
 */
public class ItemManager implements ItemInterface<Item>{
     private Connection connection;

    public ItemManager(String url,String user,String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
    }
     
     
     
    @Override
    public boolean add(Item t) {
       boolean isAdded = false;
       
       String sql = "INSERT INTO items VALUES(?,?,?,?)";
         try {
             PreparedStatement ps = connection.prepareStatement(sql);
             ps.setInt(1, t.getId());
             ps.setString(2, t.getDescription());
             ps.setInt(3, t.getQuantity());
             ps.setDouble(4, t.getUnitPrice());
             if(ps.executeUpdate() > 0){
                 isAdded = true;
             } 
             
         } catch (SQLException ex) {
             System.err.println(ex.getMessage());
         }
         return isAdded;
       
    }

    @Override
    public boolean update(int id, double newPrice) {
        boolean isUpdated = false;
       
       String sql = "UPDATE items SET price = ? WHERE id = ?";
         try {
             PreparedStatement ps = connection.prepareStatement(sql);
             ps.setDouble(1, newPrice);
             ps.setInt(2, id);
             if(ps.executeUpdate() > 0){
             isUpdated = true;
         }
             ps.close();
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
         }
       
         return isUpdated;
       
    }

    @Override
    public Item get(int id) {
        Item item = null;
      String sql = "SELECT id, description, quantity, price "
              + "FROM items "
              + "WHERE id = ?";  
         try {
             PreparedStatement ps = connection.prepareStatement(sql);
             ps.setInt(1, id);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                 int theId = rs.getInt("id");
                 String description = rs.getString("description");
                 int quantity = rs.getInt("quantity");
                 double price = rs.getDouble("price");
                 
                 item = new Item(theId, description, quantity, price);
             }
         } catch (SQLException ex) {
             System.err.println(ex.getMessage());
         }
      
     return item;
    }

    @Override
    public boolean delete(int id) {
      String sql = "DELETE FROM items WHERE id = ?";
         try {
             PreparedStatement ps = connection.prepareStatement(sql);
             ps.setInt(1, id);
             ps.executeUpdate();
             ps.close();
             return true;
         } catch (SQLException ex) {
           System.err.println(ex.getMessage());
             
         }
         return false;
         
    }

    @Override
    public List<Item> getAll() {
     List<Item> items = new ArrayList<>();
     Item item = null;
      String sql = "SELECT * "
                + "FROM items ";  
         try {
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 int theId = rs.getInt("id");
                 String description = rs.getString("description");
                 int quantity = rs.getInt("quantity");
                 double price = rs.getDouble("price");
                 
                 item = new Item(theId, description, quantity, price);
                 items.add(item);
             }
         } catch (SQLException ex) {
             System.err.println(ex.getMessage());
         }
     
     
     
         return items;
     
     
    }

    @Override
    public int totalOfAll() {
        int count = 0;
      String sql = "SELECT COUNT(*) FROM items";  
         try {
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                count = rs.getInt(1);
             }
         } catch (SQLException ex) {
             System.err.println(ex.getMessage());
         }
         return count;
    }
    
}
