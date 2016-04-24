import java.util.Arrays;

/**
 * Created by ashishw on 19/4/16.
 */
public class Main {

    public static void main(String[] args) {
        int[] sample = {15, 3, 15, 3};
        System.out.println(CandidateCode.getPoints(sample, 4));
        int [] sample2 = {6,3,5,17,19,15,13,15,6,3,5,18,19};
        System.out.println(CandidateCode.getPoints(sample2,sample2.length));
        int [] sample3 = {6,3,5,17,19,15,13,15,6,3,5,18,19,6,3,5};
        System.out.println(CandidateCode.getPoints(sample3,sample3.length));

    }
}
