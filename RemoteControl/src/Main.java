/**
 * Created by ashishw on 19/4/16.
 */
public class Main {
    public static void main(String[] args) {

        for (int i = 1; i < 20; i++) {
            benchedPermuatation(i);
            benchedForkedPermuatation(i);
        }
    }

    private static int benchedPermuatation(int level) {
        long startTime = System.currentTimeMillis();
        int value = CandidateCode.combinationCounts(level);
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
        return value;
    }

    private static int benchedForkedPermuatation(int level) {
        long startTime = System.currentTimeMillis();
        int value = CandidateCode.combinationCounts(level);
        long endTime = System.currentTimeMillis();
        System.out.println("Forked Total execution time: " + (endTime - startTime) + "ms");
        return value;
    }
}
