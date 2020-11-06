package Streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainDriver {
    public static void main(String[] args) {
        Stream<String> fruitStream = Stream.of("apple", "banana", "pear", "kiwi", "orange");
        fruitStream.filter(s -> s.contains("a"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        List<Integer> integerList = Arrays.asList(0, 1, 2, 3, 42,41,47);
        // sequential
        long howManyOddNumbers = integerList.stream()
                .filter(e -> (e % 2) == 1)
                .count();
        System.out.println("\ncount:  "+howManyOddNumbers); // Output: 4 as 1 ,3,41,47 are valid as per constraint


        //consuming streams
        IntStream.range(1, 10).filter(a -> a % 2 == 0).forEach(System.out::println);

        // Generate infinite stream - 1, 2, 3, 4, 5, 6, 7, ...
        IntStream naturalNumbers = IntStream.iterate(1, x -> x + 1);
        // but setting a limit
        naturalNumbers.limit(100).forEach(System.out::println);

        //flatmap, flat more than one stream
        //A Stream of items that are in turn streamable can be flattened into a single continuous Stream :
        //Array of List of Items can be converted into a single List.
        List<String> list1 = Arrays.asList("one", "two");
        List<String> list2 = Arrays.asList("three","four","five");
        List<String> list3 = Arrays.asList("six");
        Stream<String> finalList = Stream.of(list1, list2,
        list3).flatMap(Collection::stream);
        System.out.println(finalList.collect(Collectors.toList()));

        //parallel stream
        List<String> data = Arrays.asList("Saurabh", "Sahil", "Sanjay", "Sourav", "Sam");
        Stream<String> aParallelStream = data.stream().parallel();
        aParallelStream.forEach(System.out::println);
    }
}
