package en.ratings.own.my.utility;

import java.util.ArrayList;

import static en.ratings.own.my.utility.math.MathUtility.isLastIndex;

public class StringUtility {

    public static final String SPACE_CHARACTER = " ";
    public static final String EMPTY_STRING = "";
    public static final String COMMA_SEPARATION = ", ";

    public static String makeText(ArrayList<String> sentences) {
        StringBuilder text = new StringBuilder();
        int listSize = sentences.size();
        for (int index = 0; index < listSize; index++) {
            text.append(sentences.get(index));
            if (!(isLastIndex(index, listSize))) {
                text.append(SPACE_CHARACTER);
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

    public static String enumerateStrings(String[] list) {
        int length = list.length;
        StringBuilder enumeration = new StringBuilder();
        for (int index = 0; index < length; index++) {
            enumeration.append(list[index]);
            if (!isLastIndex(index, length)) {
                enumeration.append(COMMA_SEPARATION);
            }
        }
        return enumeration.toString();
    }

    public static String removeSpaceCharacters(String stringWithSpaceCharacters) {
        return stringWithSpaceCharacters.replace(SPACE_CHARACTER, EMPTY_STRING);
    }

    public static void print(ArrayList<String> list) {
        String text = EMPTY_STRING;
        int sizeList = list.size();
        for (int index = 0; index < sizeList; index++) {
            text += list.get(index);
            if (!isLastIndex(index, sizeList)) {
                text += COMMA_SEPARATION;
            }
        }
        System.out.println(text);
    }
}
