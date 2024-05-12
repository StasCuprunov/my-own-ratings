package en.ratings.own.my.utility;

import java.util.ArrayList;

import static en.ratings.own.my.utility.Utility.isLastIndex;

public class StringUtility {
    private static final String SPACE = " ";
    private static final String COMMA_SEPARATION = ", ";

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

    public static ArrayList<String> addExistentStringToArrayList(ArrayList<String> list, String string) {
        if (string != null) {
            list.add(string);
        }
        return list;
    }

    public static String enumerateStrings(ArrayList<String> arrayList) {
        int size = arrayList.size();
        StringBuilder enumeration = new StringBuilder();
        for (int index = 0; index < size; index++) {
            enumeration.append(arrayList.get(index));
            if (!isLastIndex(index, size)) {
                enumeration.append(COMMA_SEPARATION);
            }
        }
        return enumeration.toString();
    }
}
