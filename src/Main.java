import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.*;

public class Main {

    public static HashMap<Long,Long> genFriendNumbsToMax(long max){
    HashMap<Long, Long> out = new HashMap<>();
    long dividerSum;
    for(long i=2;i<max;i++){
        dividerSum= sumDivider(i);
        if(i!=dividerSum&&i<dividerSum&&i==sumDivider(dividerSum)){
            out.put(i,dividerSum);
        }
    }
    return out;
    }

    public static long sumDivider(long n) {

        HashSet<Long> lowerSet= LongStream.rangeClosed(2,(long)Math.sqrt(n)).filter((i)->n%i==0).boxed().collect(Collectors.toCollection(HashSet::new));  //find all divider of n below sqrt(n) and convert to a HashSet
        long totalSum=lowerSet.stream().reduce(0L,Long::sum)+1; // sum them up
        for (long l:lowerSet) { //calculate and sum up the dividers  of n greater than sqrt(n)
            if(l*l!=n) {
                totalSum += n / l;
            }
        }
        return totalSum;
    }

    public static void main(String[] args){
            boolean error;
            long max;
            Scanner input = new Scanner(System.in);
            do {
                System.out.print("Enter maximum value: ");
                error=false;
                max=input.nextLong();
                if(max<=1){
                    System.out.println("Maximum value must be greater than One!");
                    error=true;
                }
            }while(error);
            genFriendNumbsToMax(max).forEach((k,v)-> System.out.println(k+", "+ v));
    }
}
