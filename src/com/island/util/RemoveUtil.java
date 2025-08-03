package com.island.util;

import com.island.model.GameObject;

import java.util.List;
import java.util.ListIterator;

public class RemoveUtil {
    // Safely delete from List
    public static <T> boolean removeGameObjectFromList(List<T> list, T object) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(object)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
