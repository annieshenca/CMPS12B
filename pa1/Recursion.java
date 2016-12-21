//Annie Shen(ashen7@ucsc.edu)
//CMPS 12B/M
//PA1: Recursion.java

class Recursion{
    
    //reverseArray1
    //leftmost n of X[] into rightmost n in Y[] in reverse order
    static void reverseArray1(int[] X, int n, int[] Y){
        if(n > 0){
            Y[n - 1] = X[X.length - n];
            reverseArray1(X, n - 1, Y);
        }//do nothing when n==0
    }
    
    //reverseArray2
    //rightmost n of X[] into leftmost n in Y[] in reverse order
    static void reverseArray2(int[] X, int n, int[] Y){
        if(n > 0){
            Y[X.length - n] = X[n - 1];
            reverseArray2(X, n - 1, Y);
        }//do nothing when n==0
    }
    
    //reverseArray3
    //reverse the subarray X[i...j].
    static void reverseArray3(int[] X, int i, int j){
        if(i < j){
            int m;
            m = X[i];
            X[i] = X[j];
            X[j] = m;
            reverseArray3(X, i+1, j-1);
        } //stop when i>=j. No need to continue the switching
    }

    //maxArrayIndex
    //returns index of largest value in int array X
    static int maxArrayIndex(int[] X, int p, int r){
        if(p < r){
            int q = (p+r)/2;//middle index
            int L = maxArrayIndex(X, p, q);//L as left side of sort
            int R = maxArrayIndex(X, q+1, r);//R as right side

            if(X[L] > X[R]){
                return L;//if left index is bigger than right, return left
            }
            else if(X[R] >X[L]){
            return R;//other way around
            }
            else
            {
            return q;
            }
        }
        return 0;//return here
    }

    //minArrayIndex
    //returns index of smallest value in int array X
    static int minArrayIndex(int[] X, int p, int r){
        if(p < r){
            int q = (p+r)/2;//middle index
            int L = minArrayIndex(X, p, q);//left
            int R = minArrayIndex(X, q+1, r);//right

            if(X[L] < X[R]){
                return L;
            } 
            else if(X[L] > X[R])
            {
                return R;
            }
            else
            {
                return q;
            }
        }
        return 0;
    }

    public static void main(String[] args){
        int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
        int[] B = new int[A.length];
        int[] C = new int[A.length];
        int minIndex = minArrayIndex(A, 0, A.length-1);
        int maxIndex = maxArrayIndex(A, 0, A.length-1);

        for(int x: A) System.out.print(x + " ");
            System.out.println();

        System.out.println("minIndex = " + minIndex);
        System.out.println("maxIndex = " + maxIndex);

        reverseArray1(A, A.length, B);
        for(int x: B) System.out.print(x + " ");
            System.out.println();

        reverseArray2(A, A.length, C);
        for(int x: C) System.out.print(x + " ");
            System.out.println();

        reverseArray3(A, 0, A.length-1);
        for(int x: A) System.out.print(x + " ");
            System.out.println();
    }
}

/* output:
 -1 2 6 12 9 2 -5 -2 8 5 7
 minIndex = 6
 maxIndex = 3
 7 5 8 -2 -5 2 9 12 6 2 -1
 7 5 8 -2 -5 2 9 12 6 2 -1
 7 5 8 -2 -5 2 9 12 6 2 -1
*/
