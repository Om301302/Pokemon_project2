import java.awt.Point;
import java.io.*;
import java.util.Scanner;

/**
* This class represents a map object.
* @author Phillip Huynh (old)
* @author Gabriel Villanueva
*/
public class Map {
  
  //arrays to represent the read map and the displayed map, and the instance of the Singleton.
  private char [][] map;
  private boolean [][] revealed;
  private static Map instance = null;

  /**
  * This constructs a map object. Map sizes are intialized sive 5.
  */
  private Map() {
    map = new char [5][5];
    revealed = new boolean [5][5];
  }

  public static Map getInstance(){
    if (instance == null) {
      instance = new Map();
    }
    return instance;
  }
  
  /**
  * Method that loads the map depending on the number that is passed through.
  * @param mapNum holds for the map number to load.
  */
  public void loadMap(int mapNum) {
    try{

    if(mapNum == 1){
      /*Read the map and txt, take out whitespace, iterate through characters, insert them
      to each index of the map.*/
      Scanner read = new Scanner(new File("Area1.txt"));
      for(int row = 0; row < map.length; row++){

        String strRow = read.nextLine();
        String strRowUpdate = strRow.replaceAll(" ", "");

        for(int col = 0; col < map.length; col++){
          map[row][col] = strRowUpdate.charAt(col);
          //initialize revealed to false.
          revealed[row][col] = false;

          }
        } 
      read.close();

      } else if(mapNum == 2){
          /*Read the map and txt, take out whitespace, iterate through characters, insert them
          to each index of the map.*/
          Scanner read = new Scanner(new File("Area2.txt"));
          for(int row = 0; row < map.length; row++){

            String strRow = read.nextLine();
            String strRowUpdate = strRow.replaceAll(" ", "");

            for(int col = 0; col < map.length; col++){
              map[row][col] = strRowUpdate.charAt(col);
              //initialize revealed to false.
              revealed[row][col] = false;

            }
          }
          read.close();

      } else if (mapNum == 3){
          /*Read the map and txt, take out whitespace, iterate through characters, insert them
          to each index of the map.*/
          Scanner read = new Scanner(new File("Area3.txt"));
          for(int row = 0; row < map.length; row++){

            String strRow = read.nextLine();
            String strRowUpdate = strRow.replaceAll(" ", "");

            for(int col = 0; col < map.length; col++){
              map[row][col] = strRowUpdate.charAt(col);
              //initialize revealed to false.
              revealed[row][col] = false;
              
            }
          } 
          read.close();
      }

    } catch(FileNotFoundException fnf){
        System.out.println("File was not found.");
      }

  }

  /**
  * Method that gest the character at the current location.
  * @param p holds for the current point
  * @return the character at the point.
  */
  public char getCharAtLoc(Point p){
    int x = (int) p.getX();
    int y = (int) p.getY();
    //if point is out of bounds return x.
    if(x < 0 || x > 4 || y < 0 || y > 4) {
      return 'X';
    }
    return map[x][y];

  }
  /**
  * Method that returns the revealed map.
  * @param p holds for the current point.
  * @return the string representation of the map.
  */
  public String mapToString(Point p){
    String s = "";
    //get x and y coordinates
    int x = (int) p.getX();
    int y = (int) p.getY();

        //iterate to add to the string of map.
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map.length; col++) {
                //if it is current location, output *.
                if(row == x && col == y){
                    s += '*' + " ";
                    
                }
                //if location is revelaed, output the character.
                else if(revealed[row][col] == true) {
                    s += map[row][col] + " ";  
                }
                //if location is not revealed, output an 'X'.
                else if (revealed[row][col] == false) {
                    s += 'X' + " ";
                }
            }
            s += "\n";
        }
        return s;

  }

  /**
  * Method that creates a starting point for the trainer and map at the
  * position where 's' is in.
  * @return the location of the starting point.
  */
  public Point findStart(){
    Point loc = new Point();
        for (int row = 0; row < map.length; row++) {
            for(int col = 0; col < map.length; col++) {
                if(map[row][col] == 's') {
                    loc.setLocation(row, col);
                }
            }
        }
        return loc;

  }
  
  /**
  * Method that reveals the location on map while playing the game.
  * @param p holds for the current point. 
  */ 
  public void reveal(Point p){
    int x = (int) p.getX();
    int y = (int) p.getY();
    revealed[x][y] = true;

  }

  /**
  * Method that removes a character from the map and replaces it with 'n'.
  * @param p holds for the current location of the character to replace.
  */
  public void removeCharAtLoc(Point p){
    int x = (int) p.getX();
    int y = (int) p.getY();
    map[x][y] = 'n';
  }
  
}