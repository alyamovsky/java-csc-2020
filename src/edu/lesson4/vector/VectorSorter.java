package edu.lesson4.vector;

import java.util.*;

public class VectorSorter {
    public static List<ImmutableVector> sortBy(Collection<ImmutableVector> src, SortCriterion criterion) {
        List<ImmutableVector> list = new ArrayList<>(src);

        Comparator<ImmutableVector> cmp = switch (criterion) {
            case SortCriterion.LENGTH -> Comparator.comparingDouble(a -> a.toString().length());
            case SortCriterion.X -> Comparator.comparingDouble(ImmutableVector::getX);
            case SortCriterion.Y -> Comparator.comparingDouble(ImmutableVector::getY);
            case SortCriterion.Z -> Comparator.comparingDouble(ImmutableVector::getZ);
        };

        list.sort(cmp);
        return list;
    }

    public enum SortCriterion {
        LENGTH,
        X,
        Y,
        Z,
    }
}
