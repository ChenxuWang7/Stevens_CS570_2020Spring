/**
 * Student Name: Chenxu Wang
 * ID: 10457625
 * Course Name: CS 570
 * Homework Assignment 4 
 * */

package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in NON_BACKGROUND color;
     *      barrier cells are in BACKGROUND color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    // PROBLEM 1
    public boolean findMazePath(int x, int y) {

    	//When the cells are out of bound, the method returns false.
    	if(x<0||y<0||x>=maze.getNCols()||y>=maze.getNRows())
    	return false;
    	//When cells are dead end or on barrier, the method returns false.
    	else if(!maze.getColor(x,y).equals(NON_BACKGROUND))
    		return false;
    	//When the cell is the exit of maze, the method recolor the cell and return true.
    	else if(x==maze.getNCols()-1 && y==maze.getNRows()-1)
    	{
    		maze.recolor(x,y,PATH);
    		return true;
    	}
    	// Recursive case
    	else
    	{
    		//check the four neighbors of the cell and find a path
    		maze.recolor(x,y,PATH);
    		if(findMazePath(x-1,y)||findMazePath(x+1,y)||findMazePath(x,y-1)||findMazePath(x,y+1))
    			return true;
    		else// When it is a dead end, recolor it as a Temporary color.
    		{
    			maze.recolor(x,y,TEMPORARY);
    			return false;
    		}
    	}
    		
    }

    // PROBLEM 2
    /** Wrapper Method. */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths()
    {
    	return findAllMazePaths(0,0);
    }
    /**Return a list of all the solutions to the maze.
     * If there is no solution, return an empty list.
     * @param x  The x-coordinate of current point
     * @param y  The y-coordinate of current point
     * */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y)
    {
    	ArrayList <ArrayList<PairInt>> result= new ArrayList <>();
    	Stack <PairInt> trace = new Stack <>();
    	findMazePathStackBased(0 ,0 , result, trace);
    	return result;
    }
    /**Helper Method
     * @param x  The x-coordinate of current point
     * @param y  The y-coordinate of current point
     * @param result The list of successful paths recorded up to now
     * @param trace  The trace of the current path being explored
     **/
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace)
    {
    	//When the point is out of bounds, it will return.
    	if(x<0||y<0||x>=maze.getNCols()||y>=maze.getNRows())
    	{
    		return;
    	}
    	//When the point represents barriers and cannot be part of the path, it will return.
    	else if(!maze.getColor(x, y).equals(NON_BACKGROUND))
    	{
    		return;
    	}
    	//When the point is the exit of maze, it will return;
    	else if(x==maze.getNCols()-1 && y==maze.getNRows()-1)
    	{
    		//Push the last point into stack;
    		trace.push(new PairInt (x,y));
    		//Copy the content of the stack into a list, then the list is added to the result.
    		ArrayList <PairInt> temp=new ArrayList<>();
    		temp.addAll(trace);
    		result.add(temp);
    		//Remove the mark for other path.
    		maze.recolor(x, y, NON_BACKGROUND);
    		//Clear the stack.
    		trace.pop();
    		return;
    	}
    	else
    	{
    		//Identify the point as a part of path and push into the stack.
    		maze.recolor(x,y,PATH);
    		trace.push(new PairInt(x,y));
    		//Find all the way which can reach the exit.
    		findMazePathStackBased(x-1,y ,result,trace);
    		findMazePathStackBased(x+1,y ,result,trace);
    		findMazePathStackBased(x,y-1 ,result,trace);
    		findMazePathStackBased(x,y+1 ,result,trace);
    		
    		//Remove the mark for other path.
    		maze.recolor(x, y, NON_BACKGROUND);
    		//Clear the stack.
    		trace.pop();
    	}
    }
    
    //PROBLEM 3 
    /*Wrapper Method*/
    public ArrayList<PairInt> findMazePathMin()
    {
    	return findMazePathMin(0,0);
    }
    /**Find the first shortest path in the list of paths.
     * @param x  The x-coordinate of current point
     * @param y  The y-coordinate of current point */
    public ArrayList<PairInt> findMazePathMin(int x, int y)
    {
    	ArrayList <ArrayList<PairInt>> allResult= new ArrayList <>();
    	//Use the method 'findAllMazePaths' to get a list of all paths of maze.
    	allResult=findAllMazePaths();
    	ArrayList<PairInt> minResult=new ArrayList<>();
    	
    	//Find the shortest path and copy it into minResult.
    	int minSizePath=Integer.MAX_VALUE;
    	for(int i=0;i<allResult.size();i++)
    	{
    		if(allResult.get(i).size()<minSizePath)
    		{
    			minSizePath=allResult.get(i).size();
    			minResult=allResult.get(i);
    		}	
    	}
    	return minResult;
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
