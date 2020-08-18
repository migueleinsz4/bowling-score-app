package com.jobsity.challenge.bowling.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utils {
    public static <T> Iterator<T> circularIterator(List<T> list) {
        int size = list.size();
        return new Iterator<T>() {

            int i = 0;

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return list.get(i++ % size);
            }
        };
    }

    public static List<String> getFileFromResources(String fileName) throws IOException {
        ClassLoader classLoader = Utils.class.getClassLoader();
        List<String> content = new ArrayList<>();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            File file = new File(resource.getFile());
            try (FileReader reader = new FileReader(file);
                 BufferedReader br = new BufferedReader(reader)) {

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    content.add(line);
                }
            }
        }
        return content;
    }
}
