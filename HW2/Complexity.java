//Student Name:Chenxu Wang
//CWID: 10457625
//CS 570: Homework Assignment 2

public class Complexity 
{

	//a method that has time complexity O(n*n)
	public static void method1(int n)
	{
		//To keep a legal input.
		if(n<=0)
		{
			throw new IllegalArgumentException("It has an illegal argument in the method1.");
		}
		int counter=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
			{
				System.out.println("Operation"+counter);
				counter++;
			}	
	}
	
	//a method that has time complexity O(n*n*n).
	public static void method2(int n)
	{
		//To keep a legal input.
		if(n<=0)
		{
			throw new IllegalArgumentException("It has an illegal argument in the method2.");
		}
		int counter=0;
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				for(int k=0;k<n;k++)
				{
					System.out.println("Operation"+counter);
					counter++;
				}
		
	}
	
	
	//a method that has time complexity O(log(n)).
	public static void method3(int n)
	{
		//To keep a legal input.
		if(n<=0)
		{
			throw new IllegalArgumentException("It has an illegal argument in the method3.");
		}
		int counter=0;
		for(int i=1;i<n;i*=2)
		{
			System.out.println("Operation"+counter);
			counter++;
		}	
	}
	
	
	//a method that has time complexity O(n*log n).
	public static void method4(int n) 
	{
		//To keep a legal input.
		if(n<=0)
		{
			throw new IllegalArgumentException("It has an illegal argument in the method4.");
		}
		int counter=0;
		for(int i=1;i<n;i++)
			for(int j=1;j<n;j*=2)
			{
				System.out.println("Operation"+counter);
				counter++;
			}
	}
	
	
	//a method that has time complexity O(loglog n).
	public static void method5(int n)
	{
		//To keep a legal input.
		if(n<=0)
		{
			throw new IllegalArgumentException("It has an illegal argument in the method5.");
		}
		int counter=0;
		for(double i=2.0;i<=n;i=Math.pow(i, 2))
		{
			System.out.println("Operation"+counter);
			counter++;
		}
	}
	
	//a method that has time complexity O(2^n).
	static private int counter_6 = 0;
	public static int method6(int n)
	{
		//To keep a legal input.
		if(n<=0)
		{
			throw new IllegalArgumentException("It has an illegal argument in the method6.");
		}
		
		if (n == 1) 
		{
			counter_6++;
            System.out.println("Operation6 " + counter_6);
            
            return n;
        }
		else
		{
			
			System.out.println("Operation6 " + counter_6);
			counter_6++;
			return method6(n -1 ) + method6(n-1);
		}
	}
	
	
}
