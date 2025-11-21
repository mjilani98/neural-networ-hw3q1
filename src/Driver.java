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
		
		//normalizing training file
		normalizeTraining(inputTraining);
		

	}

	//method normalizes the training file
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
        
       
        
        /*************************************************************************/
        
        //creating an inputs array
        
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
        
        /*************************************************************************/
        
        //normalized inputs array
        double[][] normalizedInputs = new double[numberRecords][numberInputs]; 
        
        //normalizing inputs
       for(int x =0 ; x < numberInputs; x++)
       {   
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

       /*************************************************************************/
       
       //normalizing outputs
       
       //normalized outputs array
       double[][] normalizedOutputs = new double[numberRecords][numberOutputs];
       
       for(int x=0; x <numberOutputs ; x++)
       {
    	   //an output column
    	   double[] col = new double[numberRecords];
    	   
    	   //filling the column
    	   for(int y = 0  ; y < numberRecords ; y++)
    	   {
    		   col[y] = outputs[y][x];
    	   }
    	   
    	   //normalize the column
    	   double[] normalizedColumn = normalizeColumn(col);
    	   
    	   //putting the column into the normalized outputs array
    	   for(int y =0 ; y < numberRecords ; y++)
    	   {
    		   normalizedOutputs[y][x] = normalizedColumn[y];
    	   }
    	   
    	   
       }
        
       
       /*************************************************************************/
       
       //writing the normalized data to normalized file
       
	     for(int x = 0 ; x < numberRecords ; x++)
	     {
	   
	    	 for(int y=0; y<numberInputs; y++)
	    	 {
	    		 outFile.print(normalizedInputs[x][y]+"  ");
	    	 }
	     
	    	 for(int y = 0 ; y < numberOutputs ; y++)
	    	 {
	    		 outFile.print(normalizedOutputs[x][y]+"  ");
	    	 }
	    	 
	    	 outFile.println();
	     
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
	
	
	
	
	
	
	
	
	

}
