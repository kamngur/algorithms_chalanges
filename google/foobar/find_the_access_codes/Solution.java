class Solution {

    public static int solution(int[] l) {
        int[] count = new int[l.length];
        int triplets = 0;

        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < i; j++) {
                if (l[i] % l[j] == 0) {
                    count[i]++;
                    triplets = triplets + count[j];
                }
            }
        }
        return triplets;
    }


    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,1,1}));
        System.out.println(solution(new int[]{1,2,3}));
        System.out.println(solution(new int[]{1,2,4,8}));

    }
}