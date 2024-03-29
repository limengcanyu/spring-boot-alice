package com.spring.boot.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 *
 * @author rock.jxf
 * @date 2020/11/24 14:25
 */
@Slf4j
@Service
public class TestService {

    /**
     * @param name
     * @return
     * @SentinelResource 注解
     * <p>
     * 注意：注解方式埋点不支持 private 方法。
     * @SentinelResource 用于定义资源，并提供可选的异常处理和 fallback 配置项。 @SentinelResource 注解包含以下属性：
     * <p>
     * value：资源名称，必需项（不能为空）
     * <p>
     * entryType：entry 类型，可选项（默认为 EntryType.OUT）
     * <p>
     * blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项。blockHandler 函数访问范围需要是 public，
     * 返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。blockHandler 函数默认需要和原方法在同一个类中。
     * 若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     * <p>
     * fallback：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore
     * 里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：
     * 1. 返回值类型必须与原函数返回值类型一致；
     * 2. 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
     * 3. fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     * <p>
     * defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。
     * 默认 fallback 函数可以针对所以类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，
     * 则只有 fallback 会生效。defaultFallback 函数签名要求：
     * 1. 返回值类型必须与原函数返回值类型一致；
     * 2. 方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
     * 3. defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     * <p>
     * exceptionsToIgnore（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。
     * 注：1.6.0 之前的版本 fallback 函数只针对降级异常（DegradeException）进行处理，不能针对业务异常进行处理。
     * <p>
     * 特别地，若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。
     * 若未配置 blockHandler、fallback 和 defaultFallback，则被限流降级时会将 BlockException 直接抛出。
     * <p>
     * 示例：
     * <p>
     * public class TestService {
     * <p>
     * // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 static 函数.
     * @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
     * public void test() {
     * System.out.println("Test");
     * }
     * <p>
     * // 原函数
     * @SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
     * public String hello(long s) {
     * return String.format("Hello at %d", s);
     * }
     * <p>
     * // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
     * public String helloFallback(long s) {
     * return String.format("Halooooo %d", s);
     * }
     * <p>
     * // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
     * public String exceptionHandler(long s, BlockException ex) {
     * // Do some log here.
     * ex.printStackTrace();
     * return "Oops, error occurred at " + s;
     * }
     * }
     * 从 1.4.0 版本开始，注解方式定义资源支持自动统计业务异常，无需手动调用 Tracer.trace(ex) 来记录业务异常。Sentinel 1.4.0 以前的版本需要自行调用
     * Tracer.trace(ex) 来记录业务异常。
     */
    @SentinelResource(value = "sayHello",
            blockHandler = "sayHelloBlockHandler"
//            fallback = "sayHelloFallback"
    )
    public String sayHello(String name) {
//        log.debug("currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            log.debug("Interrupted!", e);
            Thread.currentThread().interrupt();
        }

        return "Hello, " + name + " currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName();
    }

    /**
     * blockHandler 函数会在原方法被限流/降级/系统保护的时候调用，而 fallback 函数会针对所有类型的异常。
     *
     * @param name sayHello(String name) 方法的参数
     * @param ex
     * @return
     */
    public String sayHelloBlockHandler(String name, BlockException ex) {
//        log.debug("sayHelloBlockHandler 资源：{} 调用频繁，异常：", name, ex);
//        log.debug("currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName()
//                + " 资源：{} 调用频繁，异常：", name, ex);
        return "系统繁忙，请稍后再试！" + " currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName();
    }

    public String sayHelloFallback(String name, Throwable ex) {
//        log.debug("sayHelloFallback 资源：{} 调用频繁，异常：", name, ex);
//        log.debug("currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName()
//                + " 资源：{} 调用频繁，异常：", name, ex);
        return "系统繁忙，请稍后再试！" + " currentThread id: " + Thread.currentThread().getId() + " name: " + Thread.currentThread().getName();
    }
}
