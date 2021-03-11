package logic;

import model.Constants;
import model.Ship;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class BattleshipLogic {

    //ship variables
    private ArrayList<Ship> ships = new ArrayList<Ship>();

    //stats variables
    private int sunkenShips = 0;
    private int shipCount = 0;

    //TODO: Generate Ships
    /*Create Ships*/
    public void generateShips(){
        for (int i = 0; i< Constants.NUMBEROFSHIPS; i++){
            createShip();
            shipCount++;
        }
    }

    //TODO: Create Logic to Randomize Battleships
    /*
    * Creates one ship and tries to position ship in grid
    * */
    private void createShip(){
        Random randomShip = new Random();
        int fromX,fromY, toX, toY;
        boolean hor;
        int maxShipLength = 3;
        //randomizes size of ship
        int shipLength = randomShip.nextInt(maxShipLength - 2 + 1)+2;

        //Checks if the ship has been created
        boolean created = false;

        //Randomize position based on boardsize
        //Randomizes if horizontal or vertical
        //try to position ship on board
        do{
         fromX = randomShip.nextInt(Constants.BOARDSIZE-1);
         fromY = randomShip.nextInt(Constants.BOARDSIZE-1);
         hor = randomShip.nextBoolean();

         created = positionShip(fromX, fromY, hor, shipLength);
            System.out.println("Ship was created? " + created + ". Initial position x & y " + fromX + " , " + fromY);
        }while (!created);
    }

    private boolean positionShip(int x, int y, boolean horizontal, int length){
        //try to position checking if the position has been taken

        ArrayList<ArrayList<Integer>> positions = new ArrayList<>();
        ArrayList<Integer> initialPosition = new ArrayList<>();
        initialPosition.add(x);
        initialPosition.add(y);
        positions.add(initialPosition);

        if (!shipCanBeCreated(horizontal,x,y,length)){
            return false;
        }else {
            if (horizontal){
                for(int i = 0; i<length-1; i++){
                    ArrayList<Integer> xy = new ArrayList<>();
                    xy.add(x);

                    y++;
                    xy.add(y);
                    positions.add(xy);
                }
            }
            else {
                for(int i = 0; i<length-1; i++){
                    ArrayList<Integer> xy = new ArrayList<>();
                    x++;
                    xy.add(x);
                    xy.add(y);
                    positions.add(xy);

                }
            }
        }

        System.out.println(positions);
        Ship ship = new Ship(horizontal,length, positions);
        ships.add(ship);

        return true;
    }

    //TODO: Create Logic to check for collisions in the board

    /*Check if square is occupied*/
    private boolean isOccupied(int x, int y){

        for(Ship ship : ships) {
            for(int i = 0; i<ship.getSize(); i++){
                ArrayList<Integer> shipxy =ship.getOneLocation(i);
                    if(shipxy.get(0) == x && shipxy.get(1)==y){return true;}
            }
        }

        return false;
    }

    /*
    * Check if ship length is possible based on BoardSize
    *
    * Check if start coordinates are within range
    * then checks if the ship size will fit in that position
    * */
    public boolean checkBoardSize(int direction, int shipSize){
        if((direction+(shipSize) > Constants.BOARDSIZE)){
            return false;
            }
        return true;
    }

    /*Check if ship can be created*/
    public boolean shipCanBeCreated(boolean horizontal, int x, int y, int length){
        //Check if ship will fit in board
        if(horizontal){
            if(checkBoardSize(x, length)){
                //If a ship has been created then check for collision
                if(shipCount!=0){
                    //check collision
                    if (!isOccupied(x,y)){return true;}
                }else {return true;}
            }
        }
        else {
            if(checkBoardSize(y, length)){
                if(shipCount !=0){
                    //check collision
                    if (!isOccupied(x,y)){ return true;}
                }else {return true; }
            }
        }
        return false;
    }

    //TODO: Create Logic to check for hit/miss

    public boolean hit(int x, int y){

        for(Ship ship : ships){
            for(int i = 0; i<ship.getSize(); i++){
                ArrayList<Integer> shipxy = ship.getOneLocation(i);
                if(shipxy.get(0) == x && shipxy.get(1)==y){
                    int count = ship.isHit(x,y);
                    sunkenShips += count;
                    return true;}
            }
        }
        return false;
    }

    //TODO: Create Logic to Track Win

    public boolean checkforWin(){

        if (sunkenShips == Constants.NUMBEROFSHIPS){
            System.out.println("You have win" );
            JOptionPane.showMessageDialog(null,"YOU WON!!!!!! \nPLEASE REFRESH TO PLAY AGAIN");
            return true;
        }
        return false;
    }
}
