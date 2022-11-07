//Level 3 chalange

import static java.util.Arrays.copyOf;

class Solution {
    public static int solution(int start, int len) {
        //xor(n,n+len) we change to xor(1,n-1)^xor(1,n+len)

        int xor=0;
        int x = start;
        for(int i = len; i >= 0; i--){

            xor = xor ^ computeXOR(x - 1) ^ computeXOR(x+i-1) ;

            x = x + len;
        }

        return xor;

    }



    // Method to calculate xor for numbers from 1 to n
    static int computeXOR(int n)
    {
        // If n is a multiple of 4
        if (n % 4 == 0)
            return n;

        // If n%4 gives remainder 1
        if (n % 4 == 1)
            return 1;

        // If n%4 gives remainder 2
        if (n % 4 == 2)
            return n + 1;

        // If n%4 gives remainder 3
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution(17,4));
        System.out.println(solution(0,3));

    }
}