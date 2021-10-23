/**
 * Student Name: Chenxu Wang
 * ID: 10457625
 * Course Name: CS 570
 * Homework Assignment 4 
 * */
package Maze;

public class PairInt 
{
		/**Data Fields which represent coordinates including x and y.*/
		private int x;
		private int y;
		
		/**Constructor of class PairInt */
		public PairInt(int x, int y)
		{
			this.x=x;
			this.y=y;
		}
		
		/**Return Coordinate x*/
		public int getX()
		{  		
			return this.x;
		}
		/**Return Coordiniate y*/
		public int getY()
		{
			return this.y;
		}
		
		/**Set the value of Coordinate x
		 @param x The value that will be set*/
		public void setX(int x)
		{
			this.x=x;
		}
		/**Set the value of coordinate y
		 @param y The value that will be set*/
		public void setY(int y)
		{
			this.y=y;
		}
		
		/**Compare the object with Object p 
		 @param Object p 
		 @reuturn if p equals to the object, 
		 it will return true or return false  */
		 @Override
		public boolean equals(Object p)
		{
			//if p is not the tpye of PairInt or p is null, then return false.
			if(p==null || !(p instanceof PairInt))
				return false;
			
			PairInt pp = (PairInt)p;
			return this.x == pp.x && this.y == pp.y;
		}
		
		/**Return a string of the coordinates like [x,y]*/
		@Override
		public String toString()
		{
			return "("+this.x+ "," +this.y+")";
		}
		
		/**Return a new copy of a PairInt */
		public PairInt copy()
		{
			return new PairInt(this.x, this.y);
		}
		
		
}
