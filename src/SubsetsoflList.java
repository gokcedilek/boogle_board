import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        char[][] grid = {{'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}};
        System.out.println(canFindWord(grid, "abz")); // false
        System.out.println(canFindWord(grid, "o")); // true
        System.out.println(canFindWord(grid, "z")); // false
        System.out.println(canFindWord(grid, "efg")); // true
        System.out.println(canFindWord(grid, "klhg")); // true
        System.out.println(canFindWord(grid, "klhgk")); // false - can only pass through position in grid once!
        System.out.println(canFindWord(grid, "abcd")); //true
        System.out.println(canFindWord(grid, "fjn")); //true
        System.out.println(canFindWord(grid, "cgh")); //true
        System.out.println(canFindWord(grid, "aej")); //false
        System.out.println(canFindWord(grid,"hk")); //false
        System.out.println(canFindWord(grid, "mijfbc")); //true
        System.out.println(canFindWord(grid,"jkj")); //false
    }

    // canFindWord returns true if the given word can be found by starting at any character in the grid
    // and moving one square up, down, right or left (not diagonal) to find the next character until the
    // whole word is found. A word cannot be found by visiting the same square in the grid more than once!
    //
    // if the word can't be found, canFindWord must return false
    public static boolean canFindWord(char[][] grid, String wordToFind) {
        //first of all, we need a new visited array starting from every path; meaning that every new (startRow, startCol) on the board
        List<Pair> visited= new ArrayList<>();
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(visitPaths(i,j,grid,wordToFind,visited)){
                    return true; //if any (startRow, startCol) on the board leads to completing the word return true
                }
                //when switching to a new (startRow, startCol) on the board, we have to reset our visited because this will be whole new path on the board
                visited=new ArrayList<>();
            }
        }
        return false; //if none of the (startRow, startCol)s on the board lead to completing the word return false
    }

    private static boolean visitPaths(int startRow, int startCol, char[][] grid, String wordToFind, List<Pair> visited){
        //evaluates the paths starting from a given start row and start column
        //does this evaluation recursively because checks if we have managed to reduce word to size 0 by finding all letters in it in each recursive call
        if(wordToFind.length()==0){
            return true;
        }
        if(startRow>0){
            //moving one step up
            if(grid[startRow-1][startCol]==wordToFind.charAt(0)){
                Pair<Integer,Integer> visit= new Pair<>(startRow-1,startCol);
                if(!visited.contains(visit)){
                    visited.add(visit);
                    return visitPaths(startRow-1,startCol,grid,wordToFind.substring(1),visited);
                }
            }
        }
        if(startRow<grid.length-1){
            //moving one step down
            if(grid[startRow+1][startCol]==wordToFind.charAt(0)){
                Pair<Integer,Integer> visit= new Pair<>(startRow+1,startCol);
                if(!visited.contains(visit)){
                    visited.add(visit);
                    return visitPaths(startRow+1,startCol,grid,wordToFind.substring(1),visited);
                }
            }
        }
        if(startCol>0){
            //moving one step left
            if(grid[startRow][startCol-1]==wordToFind.charAt(0)){
                Pair<Integer,Integer> visit= new Pair<>(startRow,startCol-1);
                if(!visited.contains(visit)){
                    visited.add(visit);
                    return visitPaths(startRow,startCol-1,grid,wordToFind.substring(1),visited);
                }
            }
        }
        if(startCol<grid[0].length-1){
            //moving one step right
            if(grid[startRow][startCol+1]==wordToFind.charAt(0)){
                Pair<Integer,Integer> visit= new Pair<>(startRow,startCol+1);
                if(!visited.contains(visit)){
                    visited.add(visit);
                    return visitPaths(startRow,startCol+1,grid,wordToFind.substring(1),visited);
                }
            }
        }
        //if none of the paths work out for this given location on the board
        //return to the main method and move onto the next location
        return false;
    }
}
