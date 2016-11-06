//this program is to reverse a given string from file in and remove whitespaces, and output a file out.

import java.io.*;
import java.util.Scanner;

class FileReverse{
    public static void main(String[] args) throws IOException{
        int lineNumber = 0;

        if(args.length <2){
            System.out.println("Usage: FileCopy <input file> <output file>");
            System.exit(1);
        }
        
        //scan input file
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));

        //while the input file has next line
        while(in.hasNextLine()){
            lineNumber++;

            String line = in.nextLine().trim() + " "; //take away the whitespaces

            String[] token = line.split("\\s+");

            int n = token.length;

            for(int i=0; i<n; i++){
                out.println(stringReverse(token[i], token[i].length()));
            }
        }

        in.close();
        out.close();
    }

    //Return reversed string recursivly until there's no more lines 
    public static String stringReverse(String s, int n){
        if(s.length() > 0){
            return stringReverse(s.substring(1), n-1) + s.charAt(0);
        } else {
            return " ";

        }
        
    }
}
