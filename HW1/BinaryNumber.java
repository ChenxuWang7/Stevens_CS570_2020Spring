//Student Name:Chenxu Wang
//CWID: 10457625
//CS 570: Homework Assignment 1
package cxPackage;

import java.util.Arrays;

public class BinaryNumber {
	
	// two intance fields
	private int[] data;
	private boolean overflow;
	
	
	// two constructors 
	public BinaryNumber(int length)
	{
		//To check whether it is a legal length.
		if(length<=0)
		{
			throw new IllegalArgumentException("This length is inaccessible.");
		}
		else
		{
			// establish array data
			data=new int[length];

			//initiate the data to make it consisting only zeros
			for(int i:data)
				data[i]=0;
		}
			
	}
	public  BinaryNumber(String str)
	{
		// establish array data[]
		data=new int[str.length()];
		
		
		for(int n=0;n<str.length();n++)
		{
			//Check whether the parameter is a legal binary number.
			if(str.charAt(n)!='0'&&str.charAt(n)!='1')
			{
				throw new IllegalArgumentException("It is not a legal binary number.");
			}
		
			else
			{
				//initiate the data
				//we use little endian format to store binary numbers
				for(int i=0;i<data.length;i++)
				{
					//To return the char value of every element at String str
					char temp1;
					temp1=str.charAt(i);
					
					//To return the int value of temp1
					int temp2;
					temp2=Character.getNumericValue(temp1);

					//put the numbers into data[]
					data[i]=temp2;
				}
			}
				
		}		
	}
	//To improve the length of data.
	private void reallocate(int[] d, int sum_move)
	{
		data=Arrays.copyOf(data, sum_move);//To make rooms for new digits
	}
	
	//To determinie the length of a binary number.
	public int getLength()
	{
		//Check the overflow if it is true. If yes, print "overflow".
		return data.length;

	}
	
	//To obtain a digit of a binary number given an index.
	public int getDigit(int index)
	{
		if(index<0||index>=data.length)// if the index is larger than data.length
		{
			System.out.println("The index is out of bounds.");
			return -1;
		}
		else
		{
			return data[index];
		}
		
	}
	
	//To shift all digits in a binary number any number of places to the right, as indicated by a parameter amount.
	public void shiftR(int amount) 
	{
			//To check the amount if it is vaild.
			if(amount<0)
			{
				throw new IllegalArgumentException("This shiftR amount is inaccessible!");
			}
			
			else
			{
				int ori=data.length;//To record the original data.length
				this.reallocate(data,amount+ori);
				for(int i=ori-1;i>=0;i--)//To shift all digits and let the origial places become zero.
				{
					data[i+amount]=data[i];
					data[i]=0;
				}		
				return;
			}
	}
	
	
	public void add(BinaryNumber aBinaryNumber)
	{
		//if(!overflow&&!aBinaryNumber.overflow)
		{
			int ori=data.length;
			//To check that the lengths of two binary numbers are equal.
			if(ori!=aBinaryNumber.getLength())
			{
				System.out.println("The lengths of the binary numbers do not coincide.");
				//To get the maximum of two lengths, and motify the shorter one.
				//int maxLength=Math.max(ori, aBinaryNumber.getLength());
				//this.reallocate(data, maxLength);
				//ori=data.length;
				//aBinaryNumber.reallocate(aBinaryNumber.data, maxLength);
			}
			else
			{
				int cnum=0;//It is the carried number
				//boolean addBit=false;
				
				for(int i=0;i<ori;i++)
				{

					switch (data[i]+aBinaryNumber.getDigit(i)+cnum)
					{
					case 0 :{data[i]=0;cnum=0;}break;
					case 1 :{data[i]=1;cnum=0;}break;
					case 2 :{	
								data[i]=0;
								cnum=1;
								if(i==ori-1)
								{
									this.reallocate(data, 1+ori);
									data[i+1]=1;
									overflow=true;
								}
							}break;
					case 3 :{
								data[i]=1;
								cnum=1;
								if(i==ori-1)
								{
									this.reallocate(data, 1+ori);
									data[i+1]=1;
									overflow=true;
								}
							}break;
					}

				}			
				return;
			}
			
		}
		//System.out.println("Overflow!");
		return;
	}
	
	//To transform a binary number to a String
	public String toString()
	{
		if(!overflow)
		{
			//creat a new String for data reversed
			String ret="";
			for(int i=0;i<data.length;i++)
			{
				ret+=data[i];
			}
			return ret;	
		}
		return "Overflow!";
	}
	//To print a binary number
	public String toPrint()
	{
			//creat a new String for data reversed
			String ret="";
			for(int i=0;i<data.length;i++)
			{
				ret+=data[i];
			}
			return ret;	
	}
	
	//To transform a binary number to its decimal notation.
	public int toDecimal()
	{
		int temp=0;
		for(int i=0;i<data.length;i++)
		{
			temp+=data[i]*Math.pow(2, i);
		}
		return temp;
	}
	
	
	//To clear the overflow flag
	public void clearOverflow()
	{
		overflow=false;
		System.out.println("The overflow flag has been cleared.");
	}	
}
