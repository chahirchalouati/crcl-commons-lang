package org.crcl;

import java.util.Arrays;
import java.util.function.Predicate;

public class PredicateUtils {

    public static <T> Predicate<T> allMatch(Predicate<T>... predicates) {
        return value -> Arrays.stream(predicates)
                .reduce(Predicate::and)
                .map(predicate -> predicate.test(value))
                .orElse(false);
    }

    public static <T> Predicate<T> anyMatch(Predicate<T>... predicates) {
        return value -> Arrays.stream(predicates)
                .reduce(Predicate::or)
                .map(predicate -> predicate.test(value))
                .orElse(false);
    }

    public static <T> Predicate<T> anyNonMatch(Predicate<T>... predicates) {
        return value -> Arrays.stream(predicates)
                .reduce(Predicate::or)
                .map(predicate -> !predicate.test(value))
                .orElse(false);
    }

    public static <T> Predicate<T> allNonMatch(Predicate<T>... predicates) {
        return value -> Arrays.stream(predicates)
                .reduce(Predicate::and)
                .map(predicate -> !predicate.test(value))
                .orElse(false);
    }
}
