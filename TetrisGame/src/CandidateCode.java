import java.io.*;
import java.util.BitSet;
import java.util.Stack;

/**
 * Created by ashishw on 24/4/16.
 */
public class CandidateCode {
    private static Stack<TertisMove> result = new Stack<TertisMove>();

    private static boolean[][] addTetris = {
            {true, true, true, false, false, false, false, false, false}, //1
            {false, false, true, true, true, true, false, false, false}, //2
            {true, true, true, false, false, true, false, false, false}, //3
            {false, true, false, true, true, true, false, false, false}, //4
            {true, true, false, false, true, true, false, false, false}, //5
            {true, true, false, true, true, false, false, false, false}, //6
            {false, true, true, true, true, false, false, false, false}, //7
            {false, true, false, false, true, false, false, true, false}, //8
            {true, true, false, false, true, false, false, true, false}, //9
            {true, true, true, true, false, false, false, false, false}, //10
            {true, false, false, true, false, false, true, true, true}, //11
            {true, true, false, true, false, false, true, false, false}, //12
            {true, false, false, true, true, true, false, false, false}, //13
            {false, true, false, false, true, false, true, true, false}, //14
            {false, true, false, true, true, false, false, true, false}, //15
            {true, true, true, false, true, false, false, false, false}, //16
            {true, false, false, true, true, false, true, false, false}, //17
            {false, true, false, true, true, false, true, false, false}, //18
            {true, false, false, true, true, false, false, true, false}, //19
    };

    private static BitSet ground;

    private static class TertisMove {
        int tetrisNo;
        int indexAdded;

        TertisMove(int index, int tetrisNo) {
            this.indexAdded = index;
            this.tetrisNo = tetrisNo;
        }
    }


    public static int requiredTetriminos(int length, int breadth, int[] field_details) {

        int filledCells = 0;
        int maxCells = length * breadth;
        ground = new BitSet(field_details.length);
        for (int i = 0; i < field_details.length; i++) {
            if (field_details[i] == 1) {
                ground.set(i, true);
                filledCells++;
            } else {
                ground.set(i, false);
            }
        }

        int offset = 0;

        while (filledCells < maxCells) {
            if (ground.get(offset) == true) {
                offset++;
            } else {
                int pos = 0;
                int tetrisNo = 0;

                for (; tetrisNo < addTetris.length; tetrisNo++) {
                    boolean[] tetris = addTetris[tetrisNo];

                    while (pos < 9) {
                        int fieldPos = offset + pos + (pos % 3 * breadth);
                        boolean flagSet = ground.get(fieldPos);
                        if (tetris[pos]) {
                            if (!flagSet) {
                                ground.set(fieldPos, true);
                                filledCells++;
                                if (flagSet) {
                                    while (pos > 0) {
                                        if (tetris[pos] == true) {
                                            int resetFieldPos = offset + pos + (pos % 3 * breadth);
                                            ground.set(resetFieldPos, false);
                                            filledCells--;
                                        }
                                        pos--;
                                    }
                                    break;
                                }
                            }
                            pos++;
                        }
                    }
                    if (pos == 9) {
                        result.push(new TertisMove(offset, tetrisNo));
                    }
                }
            }
            if (filledCells == maxCells) {
                return result.size();
            }
            if (filledCells < maxCells && result.size() > 0) {
                TertisMove move = result.pop();
                offset = move.indexAdded;
            }
        }
        return 0;
    }
}
