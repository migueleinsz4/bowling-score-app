package com.jobsity.challenge.bowling.util;

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
}
