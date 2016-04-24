/**
 * Created by ashishw on 19/4/16.
 */
public class CandidateCode {

    public static String getPoints(int[] a, int length) {
        StringBuffer buff = new StringBuffer();
        int compareRight = 1;
        int compareLeft = 0;
        while (compareRight < length) {
            buff.append(compareLeft).append(",");
            int currVal=a[compareRight];
            if(currVal < 0 || currVal >20 ){
                return "invalid";
            }
            if (a[compareLeft] == currVal) {
                compareLeft++;
            } else {
                compareLeft = 0;
            }
            compareRight++;
        }
        buff.append(compareLeft).append(",");
        return buff.substring(0,buff.length()-1);
    }
}
