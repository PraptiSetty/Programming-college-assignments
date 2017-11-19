import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;

public class InitialSieve {
	public static void main(String[] args) 
	{
		System.out.print("Enter number > 2: ");
		Scanner inputScanner = new Scanner(System.in);
		if(inputScanner.hasNext("1")||inputScanner.hasNext("0") )
		{
			System.out.println("Invlid Input. Ensure you enter an integer "
					+ "greater than 2.");
		}
		else if(inputScanner.hasNextInt())
		{
			int finalNumber= inputScanner.nextInt();
			int[] sequenceArrayInt = sequence(finalNumber);
			//System.out.println("Sequence : " + Arrays.toString(sequenceArrayInt));
			boolean[]seqArray = createSequence(finalNumber);
			//System.out.println("Sequence : " + Arrays.toString(seqArray));
			int[] sieveArray = null;
			displayNumbers2ToN(finalNumber);
			for(int start = 2; start < Math.sqrt(seqArray.length) ;start++)
			{
				boolean [] crossedArray = crossOutHigherMultiples (finalNumber, seqArray, start);
			
				//System.out.println("crossed array : " + Arrays.toString(crossedArray));
				sieveArray = sieve(finalNumber, crossedArray, sequenceArrayInt);
				displayComposite(crossedArray, sieveArray,/*primeCount,*/ finalNumber);
				//System.out.println("sieve array : " + Arrays.toString(sieveArray));
				//String[] sequenceToStringArray = (sequenceToString(finalNumber,sieveArray));
				//System.out.println("sieve array " +start+" : " + Arrays.toString(sequenceToString(finalNumber,sieveArray)));
			}
			String[] nonCrossedOutSubseqToStringArray = (nonCrossedOutSubseqToString(finalNumber, sieveArray));
			//System.out.println(Arrays.toString(nonCrossedOutSubseqToString(finalNumber, sieveArray)));
			

			displayPrime(nonCrossedOutSubseqToStringArray, finalNumber);
		}
		else
		{
			System.out.println("Invlid Input. Ensure you enter an integer "
					+ "greater than 2.");
		}
		inputScanner.close();
	}
	//create integer sequence Array of all numbers from 2 to N 
	//NB! - N = number the user sets as an upper limit for primes 
	public static int[] sequence(int finalNumber)
	{
		int[] sequenceArrayInt= new int[finalNumber+1];
		int beginning=0;
		for(int count=0; count < finalNumber +1 ; count++)
		{
			sequenceArrayInt[count]= beginning;
			beginning++;
		}
		return sequenceArrayInt;
	}
	//createSequence which takes a positive integer N (whose value is at least 2)
	//and which returns an array initialised to represent the sequence of numbers
	//from 2 through to N.
	
	//create boolean sequence Array of all numbers from 2 to N 
	//NB! - N = number the user sets as an upper limit for primes
	public static boolean[] createSequence(int finalNumber)
	{
		boolean[] seqArray = new boolean[finalNumber + 1];
	    for (int count = 0; count < seqArray.length ; count++) 
	    {
	        seqArray[count] = true;
	    }
	    seqArray[0] = seqArray[1] = false;
		return seqArray;
	}
	//crossOutHigherMultiples which takes a sequence of numbers σ (which may contain
	//both crossed out and non-crossed out numbers) and a number n, and which crosses
	//out all higher multiples of the given number n from the given sequence of 
	//numbers σ.
	public static boolean[] crossOutHigherMultiples (int finalNumber,  boolean[] seqArray, int start)
	{
	        if(seqArray[start]) 
	        {
	            for (int i=2 ; start*i<seqArray.length; i++) 
	            {
	                seqArray[start*i]=false;
	            }
	        }
		return seqArray;
	}
	//sieve which takes a positive integer N (whose value is at least 2) and which returns
	//a sequence of numbers σ (represented as array) from 2 through to N where all non-prime
	//numbers have been crossed out from the sequence σ using the Sieve of Eratosthenes technique.
	public static int[] sieve (int finalNumber, boolean[] crossedArray, int[] sequenceArrayInt)
	{
		for(int count=0; count <= finalNumber ; count++)
		{
				if(crossedArray[count]==false)
				{
					sequenceArrayInt[count]=0;
				}
				else
				{
					sequenceArrayInt[count]=count;
				}
		}
		return sequenceArrayInt;
	}
	//sequenceToString which takes a sequence of numbers σ and which returns a String representation
	//of the given sequence of numbers σ (where crossed out numbers are denoted by encasement 
	//in square bracket characters) separated by commas characters, 
	public static String[] sequenceToString(int finalNumber, int[] sieveArray)
	{
		String[] primeArray = new String[finalNumber + 1] ;
		//array = ArrayUtils.removeElement(array, element)
		for(int count=0; count <= finalNumber ; count++)
		{
			if(sieveArray[count]!=0)
			{
				primeArray[count] = ""+count+"";
			}
			else
			{
				primeArray[count] = "["+count+"]";
			}
		}	
		return primeArray;
	}
	//nonCrossedOutSubseqToString which takes a sequence of numbers σ and which returns a String 
	//representation of the sub-sequence of only non-crossed out numbers separated by comma 
	//characters
	public static String[] nonCrossedOutSubseqToString(int finalNumber, int[] sieveArray)
	{
		String[] primeArray = new String [finalNumber/2] ;
		int counter=0;
		for(int count=0; count <= finalNumber ; count++)
		{
			if(sieveArray[count]!=0)
			{
				primeArray[counter]= ""+count;
				counter++;
			}
		}
		return primeArray;	
	}
	//displayNumber which takes a number n (the natural number which is to be displayed in a square),
	//a colour c (the colour of the square which will contain the number n) and the number N (which 
	//was enter by the user as the largest number in the sieve)
	//repeatedly invoking this method, where the value of n runs from 2 thought to N, should draw the
	//sequence of numbers 2 through to N within squares of equal size, arranged (approximately) as a 
	//matrix squares within the window.	
	public static void displayNumber(int numInSquare, double x, double y, int finalNumber)
	{
		double matrix = (Math.sqrt(finalNumber)*0.06);
		double boxSize = (matrix/Math.sqrt(finalNumber))/4;
		Font arial_italic = new Font("Arial", Font.ITALIC, 12);
		StdDraw.setFont(arial_italic);
		if (finalNumber <= 272)
		{
			StdDraw.filledSquare(x, y, 0.028);
		}
		else
		{
			StdDraw.filledSquare(x, y, boxSize);
		}
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(x, y-0.004, Integer.toString(numInSquare));
	}
	//displayNumbers2ToN which takes the number N (which was enter by the user as the largest number
	//in the sieve) and which displays the natural numbers from 2 through to N, within squares of equal
	//size and colour, arranged (approximately) as a matrix of squares within the window
	public static void displayNumbers2ToN(int finalNumber)
	{
		double matrix = (Math.sqrt(finalNumber)*0.06);
		double dx = matrix/Math.sqrt(finalNumber);
		double dy = dx;
		double x = (matrix/Math.sqrt(finalNumber))*2;
		double y = 1-0.06;
		StdDraw.setCanvasSize(900,900);
		int numInSquare = 2;
		for(int i=2 ; i < finalNumber+1 ; i++)
		{
		if(x > (matrix))
		{
			x=(matrix/Math.sqrt(finalNumber));
			y-=dy;
		}
		
			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			displayNumber(numInSquare, x, y, finalNumber);
		x += dx;
		numInSquare++;
		}
	}
	// function that generates a random color required to color in non-primes (multiples of number) 
	public static Color randomColorGenerator()
	{
		int redElem = (int)(Math.random()*255);
		int greenElem = (int)(Math.random()*255);
		int blueElem = (int)(Math.random()*255);
		Color randomColor = new Color(redElem, greenElem, blueElem);
		return randomColor;
	}
	//displayComposite which takes a number m (a composite number to be displayed) and a number prime_count
	//(counting the number of prime numbers which have been found so far by the sieve algorithm during execution) and 
	//additionally the number N (which was enter by the user as the largest number in the sieve); the method should 
	//change the colour of the square containing the composite number m; the colour which square changes to is related 
	//to (indexed by) the number prime_count (i.e., is based on the mostly recently found prime number during the 
	//execution of the sieve); following colour change of the square containing the composite number the method should 
	//pause the graphical simulation's execution for 50 milliseconds.
	public static void displayComposite(boolean[]crossedArray, int[]sieveArray, int finalNumber)
	{
		Color c = randomColorGenerator();
		int notAPrimeNo = 0;
		double matrix = (Math.sqrt(finalNumber)*0.06);
		double x = (matrix/Math.sqrt(finalNumber))*2;
		double y = 1-0.06;
		double dx = matrix/Math.sqrt(finalNumber);
		double dy = dx;
		for(int m=2/*composite number to be displayed,*/; m <= finalNumber ;m++)
		{
			if(x > (matrix))
			{
				x=(matrix/Math.sqrt(finalNumber));
				y-=dy;
			}
			if(sieveArray[m] == 0)
			{
				notAPrimeNo = m;
				StdDraw.setPenColor(c);
				displayNumber(notAPrimeNo, x, y, finalNumber);
			}
			x += dx;
		}
	}
	//displayPrime which takes a number(prime number to be displayed) and a number prime_count 
	//(counting the number of prime numbers which have been found so far by the sieve algorithm during execution);
	//the method should display the prime number p in the window in a position related to (indexed by) the number 
	//prime_count (so that as prime numbers are found by the sieve algorithm they are display as an (approximate) 
	//matrix of prime numbers adjacent to the position of the matrix of colour squares containing the numbers 2 
	//through to N); following the display of the prime number the method should pause the graphical simulation's 
	//execution for 50 milliseconds.
	public static void displayPrime(String[] nonCrossedOutSubseqToStringArray, int finalNumber)
	{
		double matrix = (Math.sqrt(finalNumber)*0.06);
		double x = 0.9;
		double y = 1-0.06;

		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.text(0.9, 0.94, "Prime Numbers");
		for(int count=0; count < nonCrossedOutSubseqToStringArray.length ;count++)
		{ 
			if(x > (matrix))
			{
				x=0.9;
				y-=0.02;
			}
			String number = nonCrossedOutSubseqToStringArray[count];
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(x , y , number );
			x += 0.1;
		}
	}
}