package com.arohau.e;

import static java.util.Objects.isNull;

public class Main {

    private static final String aNull = null;
    private static String npeMessage = "null string";;

    public static void main(String[] args) {
        String toSplit = "firebase:12345";
        String by = ":";

        validate1(toSplit);
        validate2(toSplit);

        opt1(toSplit, by);
        opt2(toSplit, by);
        opt3(toSplit, by);
    }

    // custom validation
    // +++ never changes
    // --- need tests
    private static void validate1(String toSplit) {
        if (toSplit == aNull) {
            throw new NullPointerException(npeMessage);
        }
    }

    // original java approach
    private static void validate2(String toSplit) {
        if (isNull(toSplit)) {
            throw new NullPointerException("null string");
        }
    }

    // our custom solution
    // +++ control -> understand how it works
    // +++ control -> this code never changes

    // --- impl takes time
    // --- need tests, and check exceptional cases
    private static String opt1(String toSplit, String by) {
        char[] charArray = toSplit.toCharArray();
        int indexDelim = 0;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (by.equals(c)) {
                indexDelim = i;
                break;
            }
        }
        String substring = toSplit.substring(indexDelim);
        return substring;
    }

    // original solution
    private static String opt2(String toSplit, String by) {
        String[] split = toSplit.split(by);
        return split[1];
    }

    // google or apache or else lib to
    // com.google:Utils:1.2.5
    // com.google:Validate:1.3.4
    private static String opt3(String toSplit, String by) {
        String res = "";
        // todo
//         String[] split = com.google.utils.Strings.split(toSplit, by);
        // res = split[1];
        return "";
    }
}
