package model;

import javax.swing.*;
import java.util.ArrayList;

public class Ship{

   private boolean sunken;
   private ArrayList<ArrayList<Integer>> location = new ArrayList<>();
   private ArrayList<ArrayList<Integer>> hasBeenHit = new ArrayList<>();
   private int size;
   private boolean hor;

   public Ship(boolean hor, int size, ArrayList<ArrayList<Integer>> location ) {
      this.hor = hor;
      this.size = size;
      this.sunken = false;
      this.location = location;
   }

   public ArrayList<ArrayList<Integer>> getLocation() {
      return location;
   }

   public ArrayList<Integer> getOneLocation(int i) {
      return location.get(i);
   }

   public boolean isHor() {
      return hor;
   }

   public int getSize(){
      return size;
   }

   public boolean isSunken() {
      if(hasBeenHit.size() == size) {
         System.out.println("Ship has been sunken ");
         JOptionPane.showMessageDialog(null, "Ship was sunken!)");
         return true;
      }
      System.out.println("hasBeen hit: " + hasBeenHit.size());
      System.out.println("Ship Size " + size);
      return false;
   }

   public int isHit(int x, int y){
      if (!isSunken()){
         ArrayList<Integer> xy = new ArrayList<>();
         xy.add(x);
         xy.add(y);
         hasBeenHit.add(xy);
         System.out.println(hasBeenHit);

         if(!isSunken()){return 0;}
      }
      return 1;
   }
}
