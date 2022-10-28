
import java.math.BigInteger;
import java.util.Arrays;



class PowerHungry {

    public static String solution(int[] xs){
        int positives = 0;
        int negatives = 0;
        int biggestNegative = Integer.MIN_VALUE;
        BigInteger power = null;

        if(xs.length==1)
            return String.valueOf(xs[0]);

        for (int i = 0; i< xs.length; i++){
            if(xs[i] == 0)
                continue;

            if(xs[i] < 0){
                negatives++;
                biggestNegative = Math.max(biggestNegative, xs[i]);
            }else{
                positives ++;

            }

            if(power == null)
                power = BigInteger.valueOf(xs[i]);
            else
                power = power.multiply(BigInteger.valueOf(xs[i]));
        }
        if(power == null) // array with 0's
            return "0";


        if(power.compareTo(BigInteger.ZERO) < 0){
            if(positives==0)  //array without positives
                return "0";
            else if(positives > 0)
                power= power.divide(BigInteger.valueOf(biggestNegative));

        }


        return String.valueOf(power);
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 0, 2, 2, 0}));
        System.out.println(solution(new int[]{-2,-3, 4, -5}));
        System.out.println(solution(new int[]{0,0}));
        System.out.println(solution(new int[]{0}));
        System.out.println(solution(new int[]{0,-1}));
        System.out.println(solution(new int[]{0,-1,1}));

        System.out.println(solution(new int[]{-2,-3,0}));
        System.out.println(solution(new int[]{-2,-3,-4}));

    }
}

