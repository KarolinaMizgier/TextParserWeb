package com.karolinamizgier.textparserweb.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {
    public static String readText(String path){
        List<String> list = read(path);
        StringBuilder builder = new StringBuilder();
        list.forEach(builder::append);
        return builder.toString();
    }
    public static List<String> read(String path) {

        List<String> textList = new ArrayList<String>();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                textList.add(scanner.nextLine());
            }
            scanner.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return textList;
    }
}
