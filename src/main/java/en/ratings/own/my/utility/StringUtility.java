package en.ratings.own.my.utility;

import java.util.ArrayList;

public class StringUtility {
    private static final String SPACE = " ";

    public static String makeText(ArrayList<String> sentences) {
        StringBuilder text = new StringBuilder();
        int listSize = sentences.size();
        for (int index = 0; index < listSize; index++) {
            text.append(sentences.get(index));
            if (!(isLastIndex(index, listSize))) {
                text.append(SPACE);
            }
        }
        return text.toString();
    }

    private static boolean isLastIndex(int index, int size) {
        return index == (size - 1);
    }
}
