package common;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionUtils {

    private CollectionUtils() {
    }

    public static boolean isEmpty(Collection<?> col) {
        return col == null || col.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (list == null || predicate == null) {
            return Collections.emptyList();
        }
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T> List<T> distinctByKey(List<T> list, Function<T, Object> keyExtractor) {
        if (list == null || keyExtractor == null) {
            return Collections.emptyList();
        }
        Set<Object> seen = new HashSet<>();
        return list.stream().filter(element -> seen.add(keyExtractor.apply(element))).collect(Collectors.toList());
    }

    public static <T> List<List<T>> partition(List<T> list, int batchSize) {
        if (list == null || list.isEmpty()) {
            return List.of();
        }
        if (batchSize <= 0) {
            throw new IllegalArgumentException("batchSize must be greater than 0");
        }

        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i += batchSize) {
            result.add(list.subList(
                    i,
                    Math.min(i + batchSize, list.size())
            ));
        }
        return result;
    }
}
