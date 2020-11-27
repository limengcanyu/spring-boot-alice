package com.spring.boot.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * https://projectreactor.io/docs/core/release/reference/index.html#getting
 *
 * https://www.infoq.com/articles/reactor-by-example/
 */
@SpringBootTest
class SpringBootWebfluxApplicationTests {
    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    /**
     * Very similarly to how you would create your first Observable in RxJava,
     * you can create a Flux using the just(T…) and fromIterable(Iterable<T>) Reactor factory methods.
     * Remember that given a List, just would just emit the list as one whole, single emission,
     * while fromIterable will emit each element from the iterable list:
     *
     */
    @Test
    public void simpleCreation() {
        Flux<String> fewWords = Flux.just("Hello", "World");
        Flux<String> manyWords = Flux.fromIterable(words);

        fewWords.subscribe(System.out::println);
        System.out.println();
        manyWords.subscribe(System.out::println);
    }

    /**
     * In order to output the individual letters in the fox sentence we'll also need flatMap
     * (as we did in RxJava by Example), but in Reactor we use fromArray instead of from.
     * We then want to filter out duplicate letters and sort them using distinct and sort.
     * Finally, we want to output an index for each distinct letter, which can be done using zipWith and range:
     */
    @Test
    public void findingMissingLetter() {
        Flux<String> manyLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        manyLetters.subscribe(System.out::println);
    }

}
