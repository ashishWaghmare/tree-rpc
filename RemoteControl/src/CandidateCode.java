import java.io.*;
/**
 * Created by ashishw on 19/4/16.
 */
public class CandidateCode {

   // private static int processors = Runtime.getRuntime().availableProcessors();
    //          1
    //Moves 4 - 0 - 2
    //          3
    private static Integer[][] keyboard = {
            {0, null, 1, 3, null},
            {1, null, 2, 4, 0},
            {2, null, null, 5, 1},
            {3, 0, 4, 6, null},
            {4, 1, 5, 7, 3},
            {5, 2, null, 8, 4},
            {6, 3, 7, null, null},
            {7, 4, 8, 9, 6},
            {8, 5, null, null, 7},
            {9, 7, null, null, null},
    };

    private static int feasible(int[] moveVector) {
        int feasible = 0;
        for (int sp = 0; sp < keyboard.length; sp++) {
            Integer[] currentKey = keyboard[sp];
            int i = 0;
            for (; i < moveVector.length; i++) {
                int move = moveVector[i];
                Integer nextKey = currentKey[move];
                if (nextKey == null) {
                    break;
                } else {
                    currentKey = keyboard[nextKey];
                }
            }
            if (i == moveVector.length) {
                feasible++;
            }
        }
        return feasible;
    }

    private static int[] base5(Integer permutationNumber, int[] moveMatrix) throws ArrayIndexOutOfBoundsException {
        if (moveMatrix.length != 0 && permutationNumber == 0) {
            return moveMatrix;
        }
        int num = permutationNumber;
        int i = 0;
        while (num != 0) {
            moveMatrix[i] = num % 5;
            num = num / 5;
            i++;
        }
        return moveMatrix;
    }

    public static int combinationCounts(int level) {
        int jumps = level - 1;
        int[] moveVector = new int[jumps];
        int finalValue = 0;
        int iteration = 0;
        try {
            while (iteration >= 0) {
                moveVector = base5(iteration++, moveVector);
                if (moveVector.length == jumps) {
                    finalValue += feasible(moveVector);
                } else {
                    return finalValue;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {

        }
        return finalValue;
    }
}



//    private static class CountForkJoin extends RecursiveTask<Integer> {
//        private int startNumber = 0;
//        private int level = 0;
//        private boolean finished = false;
//        private int finalValue = 0;
//
//        CountForkJoin(int startNumber, int level) {
//            this.startNumber = startNumber;
//            this.level = level;
//        }
//
//        @Override
//        protected Integer compute() {
//            int jumps = level - 1;
//            int[] moveVector = new int[jumps];
//
//            int iteration = startNumber;
//            try {
//                while (iteration >= 0) {
//                    moveVector = base5(iteration, moveVector);
//                    iteration += processors;
//                    if (moveVector.length == jumps) {
//                        finalValue += feasible(moveVector);
//                    }
//                }
//            } catch (ArrayIndexOutOfBoundsException ex) {
//                finished = true;
//            }
//            return finalValue;
//        }
//    }
//
//    public static int combinationConcurrentCounts(int level) {
//        List<CountForkJoin> taskPerProcessor = new ArrayList<CountForkJoin>(processors);
//        Integer finalResult = 0;
//        for (int i = 0; i < processors; i++) {
//            taskPerProcessor.add(new CountForkJoin(i, level));
//        }
//        for (CountForkJoin task : taskPerProcessor) {
//            task.fork();
//        }
//        for (CountForkJoin task : taskPerProcessor) {
//            finalResult += task.join();
//        }
//        return finalResult;
//    }
