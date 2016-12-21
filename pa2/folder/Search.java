//Sort the word array using Merge Sort, then search it using
//Binary Search, which you'll recall is more efficient than a
//linear search.

import java.io.*;
import java.util.Scanner;


public class Search{
    
    public static void main(String[] args) throws IOException{
        int a=0;
        String line;
        
        if(args.length < 2){
            System.err.println("Usage: Search file target1 [target2 ...]");
            System.exit(1);
        }
        
        Scanner in1 = new Scanner(new File("file"));
        while(in1.hasNextLine()){
            line = in1.nextLine();
            a++;
        }
        
        Scanner in2 = new Scanner(new File("file"));
        String[] A = new String[a];
        int[] lineNum = new int[a];
        
        
    }
    
    static void mergeSort(String[] word, int[] lineNumber, int p, int r){
        int q;
        if(p < r) {
        q = (p+r)/2;
        
        mergeSort(word, lineNumber, p, q);
        mergeSort(word,lineNumber, q+1, r);
        merge(word, lineNumber, p, q, r);
        }
    }
    
    static void merge(String[] word, int[] lineNumber, int p, int q, int r){
        int n1 = q-p+1;
        int n2 = r-q;
        String[] L = new String[n1];
        String[] R = new String[n2];
        
        int i, j, k;

        for(i=0; i<n1; i++){
            
            L[i] = word[p+i];
        }
        for(j=0; j<n2; j++){ 
            
            R[j] = word[q+j+1];
        }

        i = 0; j = 0;
        
        
        for(k=p; k<=r; k++){
            if( i<n1 && j<n2 ){
                if( L[i].compareTo(R[j]) >0){
                    word[k] = L[i];
                    i++;
                }else{
                    word[k] = R[j];
                    j++;
                }
            }else if( i<n1 ){
                word[k] = L[i];
                i++;
            }else{ // j<n2
                word[k] = R[j];
                j++;
            }
        }
    }
    
    static int binarySearch(String[] word, int p, int r,  String target){
        int q;
        if(p > r) {
            return -1;
        }else{
            q = (p+r)/2;
            if(target.compareTo(word[q]) == 0){
                return q;
            }else if(target.compareTo(word[q]) > 0){
                return binarySearch(word, q+1, r, target);
            }else{ //A[q] > target
                return binarySearch(word, p, q-1, target);
            }
        }
    }
}
//Search.java
//Annie Shen (ashen7@ucsc.edu)
//CMPS 12B/M PA2
//Due Oct 14, 2016

import java.io.*;
import java.util.Scanner;


public class Search{
    
    public static void main(String[] args) throws IOException{
        int a=0;
        String line;
        
        if(args.length < 2){
            System.err.println("Usage: Search file target1 [target2 ...]");
            System.exit(1);
        }
        
        Scanner in1 = new Scanner(new File("file"));
        while(in1.hasNextLine()){
            line = in1.nextLine();
            a++;
        }
        
        Scanner in2 = new Scanner(new File("file"));
        String[] A = new String[a];
        int[] lineNum = new int[a];
        
    }
    
    public static void mergeSort(String[] word, int[] lineNumber, int p, int r){
        int q;
        if(p < r) {
        q = (p+r)/2;
        
        mergeSort(word, lineNumber, p, q);
        mergeSort(word,lineNumber, q+1, r);
        merge(word, lineNumber, p, q, r);
        }
    }
    
    public static void merge(String[] word, int[] lineNumber, int p, int q, int r){
        int n1 = q-p+1;
        int n2 = r-q;
        String[] L = new String[n1];
        String[] R = new String[n2];
        
        int i, j, k;

        for(i=0; i<n1; i++){
            
            L[i] = word[p+i];
        }
        for(j=0; j<n2; j++){ 
            
            R[j] = word[q+j+1];
        }

        i = 0; j = 0;
        
        
        for(k=p; k<=r; k++){
            if( i<n1 && j<n2 ){
                if( L[i].compareTo(R[j]) >0){
                    word[k] = L[i];
                    i++;
                }else{
                    word[k] = R[j];
                    j++;
                }
            }else if( i<n1 ){
                word[k] = L[i];
                i++;
            }else{ // j<n2
                word[k] = R[j];
                j++;
            }
        }
    }
    
    static int binarySearch(String[] word, int p, int r,  String target){
        int q;
        if(p > r) {
            return -1;
        }else{
            q = (p+r)/2;
            if(target.compareTo(word[q]) == 0){
                return q;
            }else if(target.compareTo(word[q]) > 0){
                return binarySearch(word, q+1, r, target);
            }else{ //A[q] > target
                return binarySearch(word, p, q-1, target);
            }
        }
    }
}
