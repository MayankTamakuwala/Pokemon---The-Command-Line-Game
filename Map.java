import java.util.Scanner;
import java.io.*;
import java.awt.Point;

public class Map {

  private char[][] map = new char[5][5];
  private boolean[][] revealed = new boolean[5][5];
  private static Map instance = null;


  /**
   * Constuctor of the Map Class
   */
  private Map() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        revealed[i][j] = false;
      }
    }
  }

  /**
   * Generates the instance of the Map if its null
   * @return The instance of the Map
   */
  public static Map getInstance(){
    if( instance == null){
      instance = new Map();
    }
    return instance;
  }

  /**
   * Loads maps represented by a number
   *
   * @param mapNum represents map number
   */
  public void loadMap(int mapNum) {
    try { 
      Scanner read = null;
      if (mapNum == 1) {
        read = new Scanner(new File("Area1.txt")); //scans area1
        for (int i = 0; i < 5; i++) {
          for (int j = 0; j < 5; j++) {
            revealed[i][j] = false;
          }
        }
      } else if (mapNum == 2) {
        read = new Scanner(new File("Area2.txt")); //scans area2
         for (int i = 0; i < 5; i++) {
          for (int j = 0; j < 5; j++) {
            revealed[i][j] = false;
          }
        }
      } else {
        read = new Scanner(new File("Area3.txt")); //scans area3
         for (int i = 0; i < 5; i++) {
          for (int j = 0; j < 5; j++) {
            revealed[i][j] = false;
          }
        }
      }
      //loads area to map array
      for (int i = 0; i < map.length; i++) {
        String charValues = read.nextLine();
        for (int j = 0; j < map[i].length; j++) {
          map[i][j] = charValues.charAt(2 * j);
        }
      }
      read.close(); //closes area file
    } catch (FileNotFoundException fnf) {
      System.out.println("File not Found");
    }
  }

  /**
   * Makes sure that the location is in valid location
   *
   * @param p point on the map
   *
   * @return the char on the map at the inputted Point
   */
  public char getCharAtLoc(Point p) {

    //finds char in location of map
    char loc = map[(int) p.getX()][(int) p.getY()];

    return loc;
  }

  /**
   * Prints the map to show display revealed map and trainer
   *
   * @param p point on the map
   *
   * @return returns string of revealed, unreavealed map and trainer as well.
   */
  public String mapToString(Point p) {
    char[][] revealedMap = new char[5][5];
    String mapString = "";
    //loops reveled map to find true and false for map to turn into string
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        int currentX = (int) p.getX();
        int currentY = (int) p.getY();
        if (revealed[i][j] == true) {
          revealedMap[i][j] = map[i][j];
        } else {
          revealedMap[i][j] = 'x';
        }
        if (currentX == i && currentY == j) {
          revealedMap[i][j] = '*';
        }
      }
    }
    //map is turned into string
    for (int i = 0; i < revealedMap.length; i++) {
      for (int j = 0; j < revealedMap.length; j++) {
        mapString = mapString + revealedMap[i][j] + " ";
      }
      mapString += "\n";
    }
    return mapString;
  }

  /**
   * Finds start point of the map
   *
   * @return returns the point of the start in the area
   */
  public Point findStart() {
    //intitializes varibles
    char chS = 's';
    int pointX = 0;
    int pointY = 0;

    //scans map to find the start if an area
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (map[i][j] == chS) {
          pointY = j;
          pointX = i;
        }
      }
    }
    //turns the coordinates into a new point to return
    Point p = new Point(pointX, pointY);
    return p;
  }

  /**
   * Reveals whats inside of the map by returning booloean
   *
   * @param p Object of Point class on which we want to turn revealed as "true".
   */
  public void reveal(Point p) {
    //turns point trainer has been in into true
    int i = (int) p.getX();
    int j = (int) p.getY();

    revealed[i][j] = true;

  }

  /**
   * Description: removes characters from map to not repeat and encounter
   *
   * @param p : point on the map
   */
  public void removeCharAtLoc(Point p) {
    //turns point into varibles
    int i = (int) p.getX();
    int j = (int) p.getY();
    map[i][j] = 'n';
  }

}