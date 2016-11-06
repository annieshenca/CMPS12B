//FileTokens.java

import java.io.*;
import java.util.Scanner;

class FileTokens{
    public static void main(String[] args) throws IOException{
        int lineNumber = 0; //initialize line number

        if(args.length <2){
            System.out.println("Usage: FileCopy <input file> <output file>");
            System.exit(1);
        }

        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));

        while(in.hasNextLine()){
            lineNumber++;

            String line = in.nextLine().trim() + " ";

            String[] token = line.split("\\s+");

            int n = token.length;
            out.println("Line " + lineNumber + " contains " + n + " tokens:");
            for(int i=0; i<n; i++){
                out.println(" " + token[i]);
            }
        }

        in.close();
        out.close();
    }
}
