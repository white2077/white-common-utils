package common;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
}
