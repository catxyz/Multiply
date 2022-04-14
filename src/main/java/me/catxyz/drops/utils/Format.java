package me.catxyz.drops.utils;

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
}
