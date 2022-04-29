package me.catxyz.multiply.utils;

import java.text.NumberFormat;

public class Format {

    public static String formatItemNamespace(String[] args) {
        StringBuilder builder = new StringBuilder();
        String arg = "";
        for (int i = 1; i < args.length; i++) {
            builder.append(arg);
            arg = "_";
            builder.append(args[i]);
        }
        return builder.toString().toUpperCase();
    }

    public static String formatNumber(long number) {
        return NumberFormat.getInstance().format(number);
    }
}
