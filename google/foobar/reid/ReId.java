import java.util.Arrays;



public class ReId {

    public static String solution(int i) {

        final int maxPrime = 206411; // 100006'th prime number
        final String primes = sieveOfEratosthenes(maxPrime + 1);
        return primes.substring(i, i + 5);

    }

    public static String sieveOfEratosthenes(int n) {
        boolean prime[] = new boolean[n];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; i <= Math.sqrt(n); i++)
        {
            for (int t = i + i; t < n; t = t + i) {
                prime[t] = false;
            }
        }
        final StringBuilder primes = new StringBuilder();
        for (int r = 2; r < n; r++) {
            if (prime[r])
                primes.append(r);
        }

        return primes.toString();
    }



    public static void main(String[] args) {

        System.out.println(ReId.solution(0));
        System.out.println(ReId.solution(3));
        System.out.println(ReId.solution(10000));
    }
}