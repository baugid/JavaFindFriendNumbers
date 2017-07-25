import java.util.*;
import java.util.stream.*;

public class Main {

    private static class Pair {
        public long number1;
        public long number2;

        public Pair(long number1, long number2) {
            this.number1 = number1;
            this.number2 = number2;
        }
    }


    public static ArrayList<Pair> genFriendNumbsToMax(long max) {
        ArrayList<Pair> out = new ArrayList<>();
        LongStream.range(2, max).parallel().forEach((i) -> {
            long dividerSum = sumDivider(i);
            if (i != dividerSum && i < dividerSum && i == sumDivider(dividerSum)) {
                synchronized (out) {
                    out.add(new Pair(i, dividerSum));
                }
            }
        });
        return out;
    }

    public static long sumDivider(long n) {
        HashSet<Long> lowerSet = LongStream.rangeClosed(2, (long) Math.sqrt(n)).filter((i) -> n % i == 0).boxed().collect(Collectors.toCollection(HashSet::new));  //find all divider of n below sqrt(n) and convert to a HashSet
        long totalSum = lowerSet.stream().reduce(0L, Long::sum) + 1; // sum them up
        for (long l : lowerSet) { //calculate and sum up the dividers  of n greater than sqrt(n)
            if (l * l != n) {
                totalSum += n / l;
            }
        }
        return totalSum;
    }

    public static void main(String[] args) {
        boolean error;
        long max;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("Enter maximum value: ");
            error = false;
            max = input.nextLong();
            if (max <= 1) {
                System.out.println("Maximum value must be greater than one!");
                error = true;
            }
        } while (error);
        ArrayList<Pair> result = genFriendNumbsToMax(max);
        System.out.println("Done!\nSorting...");
        result.stream()
                .sorted(Comparator.comparingLong(o -> o.number1)) //compares the numbers
                .forEach((element) -> System.out.println(element.number1 + ", " + element.number2));
    }
}
