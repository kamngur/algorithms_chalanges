import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.copyOf;

class Solution {
    public static int solution(int[] l) {
        int[] arr = copyOf(l, l.length);
        Arrays.sort(arr);

        List<Integer> data = Arrays.stream(arr).boxed().collect(Collectors.toList());

        int sum = sumList(data);

        int state = sum % 3;

        while (state > 0 && !data.isEmpty()) {
            Integer remove;

            if (state == 1) {
                remove = data.stream().filter(v -> v.intValue() % 3 == 1).findFirst().orElse(null);
                if (remove == null)
                    remove = data.stream().filter(v -> v.intValue() % 3 == 2).findFirst().orElse(null);
            } else { // state == 2
                remove = data.stream().filter(v -> v.intValue() % 3 == 2).findFirst().orElse(null);
                if (remove == null)
                    remove = data.stream().filter(v -> v.intValue() % 3 == 1).findFirst().orElse(null);
            }
            data.remove(remove); // state whene remove is null is impossible due to while loop conditions
            state = sumList(data) % 3;
        }
        if ( data.isEmpty())
            return 0;
        else{
            StringBuilder sb = new StringBuilder();
            Collections.reverse(data);
            data.forEach(number -> sb.append(number.intValue()));
            int number =  Integer.valueOf(sb.toString());
            return number;
        }

    }



    public static int sumList(List<Integer> list) {
        return list.stream().collect(Collectors.summingInt(Integer::intValue));
    }


    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 1, 4, 1, 5, 9}));
        System.out.println(solution(new int[]{2, 0, 2, 2, 0}));
        System.out.println(solution(new int[]{1,0}));
        System.out.println(solution(new int[]{1,2,0}));
    }
}