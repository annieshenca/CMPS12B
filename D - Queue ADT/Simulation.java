//-----------------------------------------------------------------------------
// Annie Shen (ashen7@ucsc.edu)
// CMPS 12B/M pa4
// Simulation.java
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation{

	public static Job getJob(Scanner in) {
		String[] s = in.nextLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int b = Integer.parseInt(s[1]);
		return new Job(a, b);
	}
	
	public static void main(String[] args) throws IOException{
		Scanner in = null;
		int m = 0; //Number of jobs
		PrintWriter report = new PrintWriter(new FileWriter(args[0]+".rpt"));
		PrintWriter trace = new PrintWriter(new FileWriter(args[0]+".trc"));
		Job job = null;
		Queue storage = new Queue();
		int processors = 0;
		int time, totalWait, maxWait = 0;
		double avgWait = 0;
		
		//Check command line arguments is at least 1
		if(args.length < 1) {
			System.out.println("Usage: Simulation <input file>");
			System.exit(1);
		}
		
		//Open files for reading and writing
		in = new Scanner(new File(args[0])); 
		m = Integer.parseInt(in.nextLine()); //Read in m jobs from input file
		
		while(in.hasNextLine()) {
			job = getJob(in);
			storage.enqueue(job); //Adding jobs into storage Queue
		}
		
		//Print in .rpt and .trc files
		report.println("Report file: " + args[0] + ".rpt");
		report.println(m + " Jobs:");
	    report.println(storage.toString()); //Printing out jobs as strings
	    report.println();
	    report.println("***********************************************************");
		
	    trace.println("Trace file: " + args[0] + ".trc");
		trace.println(m + " Jobs:");
		trace.println(storage.toString());
		trace.println();
	    
		//Run simulation with n processors for n = 1 to n = m-1. --> (1, m-1)
		for (processors = 1; processors < m; processors++) { 			
			
			trace.println("*****************************");
			if (processors == 1) { //Single or multiple of processors
				trace.print(processors + " processor:");
			} else { //processors > 1
				trace.print(processors + " processors:");	
			}
			trace.println("*****************************");
			trace.println();
			
		}
		
////    5.      declare and initialize an array of n processor Queues and any 
////            necessary storage Queues
////
////    6.      while unprocessed jobs remain  {
////
////    7.          determine the time of the next arrival or finish event and 
////                update time
////
////    8.          complete all processes finishing now
////
////    9.          if there are any jobs arriving now, assign them to a processor 
////                Queue of minimum length and with lowest index in the queue array.
////
////    10.     } end loop
////
////    11.     compute the total wait, maximum wait, and average wait for 
////            all Jobs, then reset finish times
////
////    12. } end loop
////
////    13. close input and output files

    report.close();
    trace.close();
   } //Exit main

} //Exit class


