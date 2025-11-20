import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException
	{
		//Scanner object 
		Scanner input = new Scanner(System.in);
		
		//get files
		System.out.println("Enter the name of the training file : ");
		String inputTraining = input.nextLine();
		
		normalizeTraining(inputTraining);
		

	}

	public static void normalizeTraining(String inputTraining) throws IOException 
	{
		//input and output files
		Scanner inFile = new Scanner(new File(inputTraining));
        PrintWriter outFile = new PrintWriter(new FileWriter("normalizedTraining.txt"));
        
        //get numbers : records, inputs, outputs
        int numberRecords = inFile.nextInt();	//number of records
        int numberInputs = inFile.nextInt();	//number of input
        int numberOutputs = inFile.nextInt();	//number of outputs
        
        //writing numbers : records, inputs, outputs to normalized file 
        outFile.println(numberRecords+" "+numberInputs+" "+numberOutputs);
        
        //creating an inputs array
        
        //columns = numberInputs;
        //rows = numberRecords;
        double[][] inputs = new double[numberRecords][numberInputs]; //inputs array
        
        double[][] outputs = new double[numberRecords][numberOutputs]; //outputs array
        
        //reading records , going through records line by line 
        for(int x = 0 ; x < numberRecords ; x++)
        {
        	//reading inputs 
        	 for(int y=0; y<numberInputs; y++)
        	 {
        		 inputs[x][y] = inFile.nextDouble();
        	 }
        	 
        	 //reading outputs
        	 for(int y=0; y <numberOutputs ; y++)
        	 {
        		 outputs[x][y] = inFile.nextDouble();
        	 }
        	 
        }
        
        for(int x = 0 ; x < numberRecords ; x++)
        {
        	for(int y=0; y <numberOutputs ; y++)
        	{
        		System.out.println(outputs[x][y]);
        	}
        }
        
        
        //normalized inputs array
        double[][] normalizedInputs = new double[numberRecords][numberInputs]; 
        
        //normalizing inputs
       for(int x =0 ; x < numberInputs; x++)
       {
    	   System.out.println("column "+(x+1));
    	   
    	   //an input column
    	   double[] col = new double[numberRecords];
    	   
    	   //filling the column 
    	   for(int y = 0  ; y < numberRecords ; y++)
    	   {
    		   col[y] = inputs[y][x];
    	   }
    	   
    	   //normalize the column
    	   double[] normalizedColumn = normalizeColumn(col);
    	   
    	   //putting the column into the normalized inputs array
    	   for(int y =0 ; y < numberRecords ; y++)
    	   {
    		   normalizedInputs[y][x] = normalizedColumn[y];
    	   } 	   
       }
        
       
       
       
		//closing scanner and printWriter
        inFile.close();
        outFile.close();
	}
	

	//method normalizes column based on maximum and minimum values within the column
	public static double[] normalizeColumn(double[] column)
	{
		double max = Double.NEGATIVE_INFINITY;
		double min = Double.POSITIVE_INFINITY;

		
		//finding the minimum and max value in the column
		for(int x = 0 ; x < column.length ; x++)
		{
			if(column[x] < min)
				min = column[x];
			
			if(column[x]>max)
				max = column[x];
		}
		
		//creating normalized column
		double[] normalizedColumn = new double[column.length];
		
		//normalizing values
		for(int x = 0 ; x < column.length ; x++)
		{
			normalizedColumn[x] = (column[x] - min) / (max - min);
		}
		
		return normalizedColumn;
	}
	
	
	
	
	
	
	public static void printColumn(double[] col)
	{
		for(int x = 0 ; x < col.length ; x++)
		{
			System.out.println(col[x]);
		}
	}
	
	

}
