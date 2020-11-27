package com.spring.boot.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
    void simpleCreation() {
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
    void findingMissingLetter() {
        Flux<String> manyLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        manyLetters.subscribe(System.out::println);
    }


    CompletableFuture<List<String>> ifhIds() {
        return CompletableFuture.completedFuture(new ArrayList<>());
    }

    CompletableFuture<String> ifhName(String name){
        return CompletableFuture.completedFuture("");
    }

    CompletableFuture<Integer> ifhStat(String stat){
        return CompletableFuture.completedFuture(1);
    }

    /**
     * Example of CompletableFuture combination
     */
    @Test
    void futureCombination() {
        CompletableFuture<List<String>> ids = ifhIds();

        CompletableFuture<List<String>> result = ids.thenComposeAsync(l -> {
            Stream<CompletableFuture<String>> zip = l.stream().map(i -> {
                        CompletableFuture<String> nameTask = ifhName(i); // 根据id获取名称
                        CompletableFuture<Integer> statTask = ifhStat(i); // 根据id获取stat

                        // 将名称与stat任务的结果合在一起操作后返回
                        return nameTask.thenCombineAsync(statTask, (name, stat) -> "Name " + name + " has stats " + stat);
                    });

            List<CompletableFuture<String>> combinationList = zip.collect(Collectors.toList());

            CompletableFuture<String>[] combinationArray = combinationList.toArray(new CompletableFuture[combinationList.size()]);

            CompletableFuture<Void> allDone = CompletableFuture.allOf(combinationArray);

            return allDone.thenApply(v -> combinationList.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList()));
        });

        List<String> results = result.join();
        assertThat(results).contains(
                "Name NameJoe has stats 103",
                "Name NameBart has stats 104",
                "Name NameHenry has stats 105",
                "Name NameNicole has stats 106",
                "Name NameABSLAJNFOAJNFOANFANSF has stats 121");

    }

    Flux<String> ifhrIds(){
        return Flux.fromArray(new String[]{"1", "2"});
    }

    Mono<String> ifhrName(String id){
        return Mono.just("1");
    }

    Mono<Integer> ifhrStat(String id){
        return Mono.just(1);
    }

    /**
     * Example of Reactor code equivalent to future code
     */
    @Test
    void reactorCombination() {
        Flux<String> ids = ifhrIds();

        Flux<String> combinations =
                ids.flatMap(id -> {
                    Mono<String> nameTask = ifhrName(id);
                    Mono<Integer> statTask = ifhrStat(id);

                    return nameTask.zipWith(statTask,
                            (name, stat) -> "Name " + name + " has stats " + stat);
                });

        Mono<List<String>> result = combinations.collectList();

        List<String> results = result.block();
        assertThat(results).containsExactly(
                "Name NameJoe has stats 103",
                "Name NameBart has stats 104",
                "Name NameHenry has stats 105",
                "Name NameNicole has stats 106",
                "Name NameABSLAJNFOAJNFOANFANSF has stats 121"
        );
    }
}
