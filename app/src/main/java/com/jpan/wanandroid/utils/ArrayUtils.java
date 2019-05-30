package com.jpan.wanandroid.utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {

    /**
     * is List empty
     *
     * @param list list
     * @return is list empty or not
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    /**
     * is ArrayList empty
     *
     * @param list array list
     * @return is array list empty or not
     */
    public static <T> boolean isEmpty(ArrayList<T> list) {
        return list == null || list.size() == 0;
    }
}
