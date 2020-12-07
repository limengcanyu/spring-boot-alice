package com.spring.boot.webflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.Exceptions;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://projectreactor.io/docs/core/release/reference/index.html#getting
 * <p>
 * https://www.infoq.com/articles/reactor-by-example/
 */
//@SpringBootTest
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

    CompletableFuture<String> ifhName(String name) {
        return CompletableFuture.completedFuture("");
    }

    CompletableFuture<Integer> ifhStat(String stat) {
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

    Flux<String> ifhrIds() {
        return Flux.fromArray(new String[]{"1", "2"});
    }

    Mono<String> ifhrName(String id) {
        return Mono.just("1");
    }

    Mono<Integer> ifhrStat(String id) {
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

    @Test
    void sampleSubscriber() {
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();

        Flux<Integer> ints = Flux.range(1, 4);

        ints.subscribe(
                System.out::println, // the consumer to invoke on each value 每个值都调用的消费者
                error -> System.err.println("Error " + error), // the consumer to invoke on error signal 错误时调用的消费者
                () -> System.out.println("Done"), // the consumer to invoke on complete signal 完成时调用的消费者
                s -> s.request(10) // the consumer to invoke on subscribe signal, to be used 订阅时调用的消费者
        );

        System.out.println("subscribe=================");
        ints.subscribe(ss);
    }

    @Test
    void generate() {
        System.out.println("1=========================================================================================");

        Flux<String> flux = Flux.generate(
                () -> 0, // We supply the initial state value of 0.
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state); // We use the state to choose what to emit (a row in the multiplication table of 3).
                    if (state == 10) sink.complete(); // We also use it to choose when to stop.
                    return state + 1; // We return a new state that we use in the next invocation (unless the sequence terminated in this one).

                });
        flux.subscribe(System.out::println);

        System.out.println("2=========================================================================================");

        flux = Flux.generate(
                AtomicLong::new, // This time, we generate a mutable object as the state
                (state, sink) -> {
                    long i = state.getAndIncrement(); // We mutate the state here
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state; // We return the same instance as the new state
                });
        flux.subscribe(System.out::println);

        System.out.println("3=========================================================================================");

        flux = Flux.generate(
                AtomicLong::new, // Again, we generate a mutable object as the state
                (state, sink) -> {
                    long i = state.getAndIncrement(); // We mutate the state here
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state; // We return the same instance as the new state
                },
                (state) -> System.out.println("state: " + state) // We see the last state value (11) as the output of this Consumer lamb
        );
//        flux.subscribe(); // 触发订阅
        flux.subscribe(System.out::println);

    }

    public String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }

    @Test
    void handle() {
        Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
                .handle((i, sink) -> {
                    String letter = alphabet(i);
                    if (letter != null)
                        sink.next(letter);
                });

        alphabet.subscribe(System.out::println);
    }

    @Test
    void threadingAndSchedulers() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        final Mono<String> mono = Mono.just("hello "); // The Mono<String> is assembled in thread main

        Thread t = new Thread(
                () -> mono.map(msg -> msg + "thread ")
                        .subscribe(v -> // However, it is subscribed to in thread Thread-0
                                System.out.println(v + Thread.currentThread().getName()) // As a consequence, both the map and the onNext callback actually run in Thread-0
                        )
        );

        t.start();
        t.join();
    }

    /**
     * 在 Spring Reactor 项目中，有两个出镜较少的方法：publishOn 和 subscribeOn。这两个方法的作用是指定执行 Reactive Streaming 的 Scheduler（可理解为线程池）。
     * <p>
     * 为何需要指定执行 Scheduler 呢？一个显而易见的原因是：组成一个反应式流的代码有快有慢，例如 NIO、BIO。如果将这些功能都放在一个线程里执行，快的就会被慢的影响，
     * 所以需要相互隔离。这是这两个方法应用的最典型的场景。
     */
    @Test
    void publishOn() {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4); // Creates a new Scheduler backed by four Thread instances.

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i) // The first map runs on the anonymous thread in <5>.
                .publishOn(s) // The publishOn switches the whole sequence on a Thread picked from <1>.
                .map(i -> "value " + i); // The second map runs on the Thread from <1>.

        new Thread(() -> flux.subscribe(System.out::println)); // This anonymous Thread is the one where the subscription happens. The print happens on the latest execution context, which is the one from publishOn.
    }

    @Test
    void subscribeOn() {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4); // Creates a new Scheduler backed by four Thread.

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i) // The first map runs on one of these four threads…
                .subscribeOn(s) // …because subscribeOn switches the whole sequence right from subscription time (<5>).
                .map(i -> "value " + i); // The second map also runs on same thread.

        new Thread(() -> flux.subscribe(System.out::println)); // This anonymous Thread is the one where the subscription initially happens, but subscribeOn immediately shifts it to one of the four scheduler threads.
    }

    String doSomethingDangerous(Integer v) {
        return "";
    }

    String doSecondTransform(String v) {
        return "";
    }

    /**
     * similar to the following try-catch block:
     * <p>
     * try {
     * for (int i = 1; i < 11; i++) {
     * String v1 = doSomethingDangerous(i);
     * String v2 = doSecondTransform(v1);
     * System.out.println("RECEIVED " + v2);
     * }
     * } catch (Throwable t) {
     * System.err.println("CAUGHT " + t);
     * }
     */
    @Test
    void onError() throws InterruptedException {
//        Flux<String> s = Flux.range(1, 10)
//                .map(v -> doSomethingDangerous(v)) // A transformation that can throw an exception is performed.
//                .map(v -> doSecondTransform(v)); // If everything went well, a second transformation is performed.
//
//        s.subscribe(value -> System.out.println("RECEIVED " + value), // Each successfully transformed value is printed out.
//                error -> System.err.println("CAUGHT " + error) // In case of an error, the sequence terminates and an error message is displayed.
//        );
//
//        Flux.just(10)
//                .map(this::doSomethingDangerous)
//                .onErrorReturn("RECOVERED");
//
//        Flux.just(10)
//                .map(this::doSomethingDangerous)
//                .onErrorReturn(e -> e.getMessage().equals("boom10"), "recovered10");

        Flux<String> flux =
                Flux.interval(Duration.ofMillis(250))
                        .map(input -> {
                            if (input < 3) return "tick " + input;
                            throw new RuntimeException("boom");
                        })
                        .onErrorReturn("Uh oh");

        flux.subscribe(System.out::println);
        Thread.sleep(2100);
    }

    @Test
    void retry() throws InterruptedException {
        Flux.interval(Duration.ofMillis(250))
                .map(input -> {
                    if (input < 3) return "tick " + input;
                    throw new RuntimeException("boom");
                })
                .retry(1)
                .elapsed()
                .subscribe(System.out::println, System.err::println);

        Thread.sleep(2100);
    }

    @Test
    void exception() {
//        Flux.just("foo")
//                .map(s -> { throw new IllegalArgumentException(s); })
//                .subscribe(v -> System.out.println("GOT VALUE"),
//                        e -> System.out.println("ERROR: " + e));

        List<String> stringList = new ArrayList<>();
        Flux.just("foo")
                .subscribe(stringList::add,
                        e -> System.out.println("ERROR: " + e));
        System.out.println("stringList: " + stringList);
    }

    @Test
    void log() {
        Flux<Integer> flux = Flux.range(1, 10)
                .log()
                .take(3); // 取前3个值
        flux.subscribe();
    }

    @Test
    void metric() {
        Flux<Integer> flux = Flux.range(1, 10)
                .name("events")
                .tag("source", "kafka")
                .metrics()
                .retry(1)
                .take(3);
        flux.subscribe(integer -> log());
        System.out.println("======================================");
        flux.subscribe(integer -> log());
    }

    @Test
    void transform() {
        // 定义函数
//        Function<Flux<String>, Flux<String>> filterAndMap =
//                f -> f.filter(color -> !color.equals("orange"))
//                        .map(String::toUpperCase);

        Function<Flux<String>, Flux<String>> filterAndMap =
                f -> {
                    Flux<String> nonOrangeFlux = f.filter(color -> !color.equals("orange"))
                            .map(String::toUpperCase);
                    Flux<String> orangeFlux = f.filter(color -> color.equals("orange"))
                            .map(String::toUpperCase);
                    return nonOrangeFlux.concatWith(orangeFlux); // 将orangeFlux合并到nonOrangeFlux
                };

        Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
//                .doOnNext(System.out::println)
                .transform(filterAndMap) // 使用函数转换为一个新的Flux
                .subscribe(d -> System.out.println("Subscriber to Transformed MapAndFilter: " + d));
    }

    @Test
    void transformDeferred() {
        AtomicInteger ai = new AtomicInteger();

        Function<Flux<String>, Flux<String>> filterAndMap = f -> {
            if (ai.incrementAndGet() == 1) {
                return f.filter(color -> !color.equals("orange"))
                        .map(String::toUpperCase);
            }
            return f.filter(color -> !color.equals("purple"))
                    .map(String::toUpperCase);
        };

        Flux<String> composedFlux =
                Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                        .doOnNext(System.out::println)
                        .transformDeferred(filterAndMap);

        composedFlux.subscribe(d -> System.out.println("Subscriber 1 to Composed MapAndFilter :" + d));
        composedFlux.subscribe(d -> System.out.println("Subscriber 2 to Composed MapAndFilter: " + d));
    }

    @Test
    void ConnectableFlux() throws InterruptedException {
//        Flux<Integer> source = Flux.range(1, 3)
//                .doOnSubscribe(s -> System.out.println("subscribed to source"));
//
//        ConnectableFlux<Integer> co = source.publish();
//
//        co.subscribe(System.out::println, e -> {}, () -> {});
//        co.subscribe(System.out::println, e -> {}, () -> {});
//
//        System.out.println("done subscribing");
//        Thread.sleep(500);
//        System.out.println("will now connect");
//
//        co.connect();


        // autoConnect
//        System.out.println("1 currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
        Flux<Integer> source = Flux.range(1, 3)
                .doOnSubscribe(s -> {
//                    System.out.println("2 currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
                    System.out.println("subscribed to source: " + s);
                });

        source.subscribe(integer -> {
//            System.out.println("3 currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
            System.out.println("1 integer: " + integer);
        }, e -> {
        }, () -> {
        });

        System.out.println("============================================================");
        // 指定数量的subscribers订阅时，将ConnectableFlux连接到上游source
        // 如果subscribers数量不够，则都不会订阅；如果超过，则前几个subscribers会成功订阅
        Flux<Integer> autoCo = source.publish().autoConnect(2);

        autoCo.subscribe(integer -> {
//            System.out.println("4 currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
            System.out.println("2 integer: " + integer);
        }, e -> {
        }, () -> {
        });

        System.out.println("subscribed first");
        Thread.sleep(500);
        System.out.println("subscribing second");

        autoCo.subscribe(integer -> {
//            System.out.println("5 currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
            System.out.println("3 integer: " + integer);
        }, e -> {
        }, () -> {
        });

//        autoCo.subscribe(integer -> {
////            System.out.println("6 currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
//            System.out.println("4 integer: " + integer);
//        }, e -> {
//        }, () -> {
//        });
    }

    @Test
    void grouping() {
        StepVerifier.create(
                Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                        .groupBy(i -> i % 2 == 0 ? "even" : "odd")
                        .concatMap(g -> g.defaultIfEmpty(-1) // if empty groups, show them
                                .map(String::valueOf) // map to string
                                .startWith(g.key())) // start with the group's key
        )
                .expectNext("odd", "1", "3", "5", "11", "13")
                .expectNext("even", "2", "4", "6", "12")
                .verifyComplete();
    }

    @Test
    void windowing() {
        StepVerifier.create(
                Flux.range(1, 10)
                        .window(5, 3) // overlapping windows
                        .concatMap(g -> g.defaultIfEmpty(-1)) // show empty windows as -1
        )
                .expectNext(1, 2, 3, 4, 5)
                .expectNext(4, 5, 6, 7, 8)
                .expectNext(7, 8, 9, 10)
                .expectNext(10)
                .verifyComplete();

        System.out.println("=================================================");

        StepVerifier.create(
                Flux.range(1, 10)
                        .window(5, 2) // overlapping windows
                        .concatMap(g -> g.defaultIfEmpty(-1)) // show empty windows as -1
        )
                .expectNext(1, 2, 3, 4, 5)
                .expectNext(3, 4, 5, 6, 7)
                .expectNext(5, 6, 7, 8, 9)
                .expectNext(7, 8, 9, 10)
                .expectNext(9, 10)
                .verifyComplete();

        System.out.println("=================================================");

        StepVerifier.create(
                Flux.range(1, 10)
                        .window(5, 6) // overlapping windows
                        .concatMap(g -> g.defaultIfEmpty(-1)) // show empty windows as -1
        )
                .expectNext(1, 2, 3, 4, 5)
                .expectNext(7, 8, 9, 10)
                .verifyComplete();

        System.out.println("=================================================");

        StepVerifier.create(
                Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                        .windowWhile(i -> i % 2 == 0)
                        .concatMap(g -> g.defaultIfEmpty(-1))
        )
                // 1 3 5都不满足windowWhile，因此都丢弃
                .expectNext(-1, -1, -1) //respectively triggered by odd 1 3 5
                // 2 4 6满足2, 4, 6因此形成一个窗window，因为11不满足，截至到11
                .expectNext(2, 4, 6) // triggered by 11
                // 12 又满足条件
                .expectNext(12) // triggered by 13
                // however, no empty completion window is emitted (would contain extra matching elements)
                .verifyComplete();
    }

    @Test
    void buffering() {
        // Buffering can also lead to dropping source elements or having overlapping buffers
        StepVerifier.create(
                Flux.range(1, 10)
                        .buffer(5, 3) // overlapping buffers
        )
                .expectNext(Arrays.asList(1, 2, 3, 4, 5))
                .expectNext(Arrays.asList(4, 5, 6, 7, 8))
                .expectNext(Arrays.asList(7, 8, 9, 10))
                .expectNext(Collections.singletonList(10))
                .verifyComplete();

        // bufferUntil and bufferWhile do not emit an empty buffer
        StepVerifier.create(
                Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                        .bufferWhile(i -> i % 2 == 0)
        )
                // 满足条件开始，满足条件截至，形成一个buffer
                .expectNext(Arrays.asList(2, 4, 6)) // triggered by 11
                .expectNext(Collections.singletonList(12)) // triggered by 13
                .verifyComplete();
    }

    @Test
    void ParallelFlux() {
        // 没有并行执行
        Flux.range(1, 10)
                .parallel(2) // We force a number of rails instead of relying on the number of CPU cores
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));

        System.out.println("===============================================");

        // 并行执行
        Flux.range(1, 10)
                .parallel(5)
                .runOn(Schedulers.parallel())
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
    }

    @Test
    void Context() {
//        String key = "message";
//        Mono<String> r = Mono.just("Hello")
//                .flatMap(s -> Mono.deferContextual(ctx ->
//                        Mono.just(s + " " + ctx.get(key))))
//                .contextWrite(ctx -> ctx.put(key, "World"));
//
//        StepVerifier.create(r)
//                .expectNext("Hello World")
//                .verifyComplete();
    }

    @Test
    void wrapSynchronousBlockingCall() {
        Mono<String> blockingWrapper = Mono.fromCallable(() -> { // Create a new Mono by using fromCallable.
            return "111"; /* make a remote synchronous call */ // Return the asynchronous, blocking resource.
        });

        System.out.println("1 currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());

        blockingWrapper = blockingWrapper.subscribeOn(Schedulers.boundedElastic()); // Ensure each subscription happens on a dedicated single-threaded worker from Schedulers.boundedElastic().
        blockingWrapper.subscribe(string -> {
            System.out.println("2 currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
            System.out.println(string);
        });
    }

    /**
     * Use retryWhen to Emulate retry(3)
     */
    @Test
    void retryWhen() {
        AtomicInteger errorCount = new AtomicInteger();
        Flux<String> flux =
                Flux.<String>error(new IllegalArgumentException())
                        .doOnError(e -> errorCount.incrementAndGet())
                        .retryWhen(Retry.from(companion -> // We customize Retry by adapting from a Function lambda rather than providing a concrete class
                                companion.map(rs -> { // The companion emits RetrySignal objects, which bear number of retries so far and last failure
                                    if (rs.totalRetries() < 3) return rs.totalRetries(); // To allow for three retries, we consider indexes < 3 and return a value to emit (here we simply return the index).
                                    else throw Exceptions.propagate(rs.failure()); // In order to terminate the sequence in error, we throw the original exception after these three retries.
                                })
                        ));
        flux.subscribe(System.out::println);
    }
}
