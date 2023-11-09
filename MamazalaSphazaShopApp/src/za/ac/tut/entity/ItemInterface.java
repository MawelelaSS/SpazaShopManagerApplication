/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package za.ac.tut.entity;

import java.util.List;

/**
 *
 * @author Student
 */
public interface ItemInterface<T> {
   public boolean add(T t);
   public boolean update(int id, double newPrice);
   public T get(int id);
   public boolean delete(int id);
   public List<T> getAll();
   public int totalOfAll();
   
}
