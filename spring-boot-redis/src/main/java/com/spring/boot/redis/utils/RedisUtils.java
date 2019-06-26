package com.spring.boot.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: Redis Utils</p>
 *
 * @author rock.jiang
 * date 2019/06/20
 */
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOps;

    @Resource(name = "redisTemplate")
    private ListOperations<String, Object> listOps;

    @Resource(name = "redisTemplate")
    private SetOperations<String, Object> setOps;

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, Object> zSetOps;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOps;

    /* **********************************************************************
     * ValueOperations
     ********************************************************************** */

    /**
     * 设置 key 的 value
     *
     * Set {@code value} for {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param value must not be {@literal null}.
     */
    public void valueOpsSet(String key, Object value) {
        valueOps.set(key, value);
    }

    /**
     * 设置 key 的 value 和 有效时间
     *
     * Set the {@code value} and expiration {@code timeout} for {@code key}.
     *
     * @param key     must not be {@literal null}.
     * @param value   must not be {@literal null}.
     * @param timeout the key expiration timeout.
     * @param unit    must not be {@literal null}.
     */
    public void valueOpsSet(String key, Object value, long timeout, TimeUnit unit) {
        valueOps.set(key, value, timeout, unit);
    }

    /**
     * 设置 key 的 value 和 期间
     *
     * Set the {@code value} and expiration {@code timeout} for {@code key}.
     *
     * @param key     must not be {@literal null}.
     * @param value   must not be {@literal null}.
     * @param timeout must not be {@literal null}.
     * @throws IllegalArgumentException if either {@code key}, {@code value} or {@code timeout} is not present.
     */
    public void valueOpsSet(String key, Object value, Duration timeout) {
        if (TimeoutUtils.hasMillis(timeout)) {
            valueOpsSet(key, value, timeout.toMillis(), TimeUnit.MILLISECONDS);
        } else {
            valueOpsSet(key, value, timeout.getSeconds(), TimeUnit.SECONDS);
        }
    }

    /**
     * Set {@code key} to hold the string {@code value} if {@code key} is absent.
     *
     * @param key   must not be {@literal null}.
     * @param value must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public Boolean valueOpsSetIfAbsent(String key, Object value) {
        return valueOps.setIfAbsent(key, value);
    }

    /**
     * Set {@code key} to hold the string {@code value} and expiration {@code timeout} if {@code key} is absent.
     *
     * @param key     must not be {@literal null}.
     * @param value   must not be {@literal null}.
     * @param timeout the key expiration timeout.
     * @param unit    must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public Boolean valueOpsSetIfAbsent(String key, Object value, long timeout, TimeUnit unit) {
        return valueOps.setIfAbsent(key, value, timeout, unit);
    }

    /**
     * Set {@code key} to hold the string {@code value} and expiration {@code timeout} if {@code key} is absent.
     *
     * @param key     must not be {@literal null}.
     * @param value   must not be {@literal null}.
     * @param timeout must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     * @throws IllegalArgumentException if either {@code key}, {@code value} or {@code timeout} is not present.
     */
    public boolean valueOpsSetIfAbsent(String key, Object value, Duration timeout) {
        if (TimeoutUtils.hasMillis(timeout)) {
            return valueOpsSetIfAbsent(key, value, timeout.toMillis(), TimeUnit.MILLISECONDS);
        }

        return valueOpsSetIfAbsent(key, value, timeout.getSeconds(), TimeUnit.SECONDS);
    }

    /**
     * Set {@code key} to hold the string {@code value} if {@code key} is present.
     *
     * @param key   must not be {@literal null}.
     * @param value must not be {@literal null}.
     * @return command result indicating if the key has been set.
     * @throws IllegalArgumentException if either {@code key} or {@code value} is not present.
     */
    public boolean valueOpsSetIfPresent(String key, Object value) {
        return valueOps.setIfPresent(key, value) != null;
    }

    /**
     * Set {@code key} to hold the string {@code value} and expiration {@code timeout} if {@code key} is present.
     *
     * @param key     must not be {@literal null}.
     * @param value   must not be {@literal null}.
     * @param timeout the key expiration timeout.
     * @param unit    must not be {@literal null}.
     * @return command result indicating if the key has been set.
     * @throws IllegalArgumentException if either {@code key}, {@code value} or {@code timeout} is not present.
     */
    public boolean valueOpsSetIfPresent(String key, Object value, long timeout, TimeUnit unit) {
        return valueOps.setIfPresent(key, value, timeout, unit) != null;
    }

    /**
     * Set {@code key} to hold the string {@code value} and expiration {@code timeout} if {@code key} is present.
     *
     * @param key     must not be {@literal null}.
     * @param value   must not be {@literal null}.
     * @param timeout must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     * @throws IllegalArgumentException if either {@code key}, {@code value} or {@code timeout} is not present.
     */
    public boolean valueOpsSetIfPresent(String key, Object value, Duration timeout) {
        if (TimeoutUtils.hasMillis(timeout)) {
            return valueOpsSetIfPresent(key, value, timeout.toMillis(), TimeUnit.MILLISECONDS);
        }

        return valueOpsSetIfPresent(key, value, timeout.getSeconds(), TimeUnit.SECONDS);
    }

    /**
     * Set multiple keys to multiple values using key-value pairs provided in {@code tuple}.
     *
     * @param map must not be {@literal null}.
     */
    public void valueOpsMultiSet(Map<String, Object> map) {
        valueOps.multiSet(map);
    }

    /**
     * Set multiple keys to multiple values using key-value pairs provided in {@code tuple} only if the provided key does
     * not exist.
     *
     * @param map       must not be {@literal null}.
     * @param {@literal null} when used in pipeline / transaction.
     */
    public boolean valueOpsMultiSetIfAbsent(Map<String, Object> map) {
        return valueOps.multiSetIfAbsent(map) != null;
    }

    /**
     * Get the value of {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public Object valueOpsGet(Object key) {
        return valueOps.get(key);
    }

    /**
     * Set {@code value} of {@code key} and return its old value.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public Object valueOpsGetAndSet(String key, Object value) {
        return valueOps.getAndSet(key, value);
    }

    /**
     * Get multiple {@code keys}. Values are returned in the order of the requested keys.
     *
     * @param keys must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public List<Object> valueOpsMultiGet(Collection<String> keys) {
        return valueOps.multiGet(keys);
    }

    /**
     * Increment an integer value stored as string value under {@code key} by one.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public Long valueOpsIncrement(String key) {
//        Long result = valueOps.increment(key);
//        return result == null ? 0 : result;

//        return Optional.ofNullable(valueOps.increment(key)).orElse(0L);

        return valueOps.increment(key);
    }

    /**
     * Increment an integer value stored as string value under {@code key} by {@code delta}.
     *
     * @param key   must not be {@literal null}.
     * @param delta
     * @return {@literal null} when used in pipeline / transaction.
     */
    public Long valueOpsIncrement(String key, long delta) {
//        Long result = valueOps.increment(key, delta);
//        return result == null ? 0 : result;

//        return Optional.ofNullable(valueOps.increment(key, delta)).orElse(0L);

        return valueOps.increment(key, delta);
    }

    /**
     * Increment a floating point number value stored as string value under {@code key} by {@code delta}.
     *
     * @param key   must not be {@literal null}.
     * @param delta
     * @return {@literal null} when used in pipeline / transaction.
     * @see <a href="http://redis.io/commands/incrbyfloat">Redis Documentation: INCRBYFLOAT</a>
     */
    public Double valueOpsIncrement(String key, double delta) {
//        Double result = valueOps.increment(key, delta);
//        return result == null ? 0.0 : result;

//        return Optional.ofNullable(valueOps.increment(key, delta)).orElse(0.0);

        return valueOps.increment(key, delta);
    }

    /**
     * Decrement an integer value stored as string value under {@code key} by one.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public Long valueOpsDecrement(String key) {
//        Long result = valueOps.decrement(key);
//        return result == null ? 0 : result;

//        return Optional.ofNullable(valueOps.decrement(key)).orElse(0L);

        return valueOps.decrement(key);
    }

    /**
     * Decrement an integer value stored as string value under {@code key} by {@code delta}.
     *
     * @param key   must not be {@literal null}.
     * @param delta
     * @return {@literal null} when used in pipeline / transaction.
     */
    public Long valueOpsDecrement(String key, long delta) {
//        Long result = valueOps.decrement(key, delta);
//        return result == null ? 0 : result;

//        return Optional.ofNullable(valueOps.decrement(key, delta)).orElse(0L);

        return valueOps.decrement(key, delta);
    }

    /**
     * Append a {@code value} to {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     */
    public Integer valueOpsAppend(String key, String value) {
//        Integer result = valueOps.append(key, value);
//        return result == null ? 0 : result;

//        return Optional.ofNullable(valueOps.append(key, value)).orElse(0);

        return valueOps.append(key, value);
    }

    /**
     * Get a substring of value of {@code key} between {@code begin} and {@code end}.
     *
     * @param key   must not be {@literal null}.
     * @param start
     * @param end
     * @return {@literal null} when used in pipeline / transaction.
     */
    public String valueOpsGet(String key, long start, long end) {
        return valueOps.get(key, start, end);
    }

    /**
     * Overwrite parts of {@code key} starting at the specified {@code offset} with given {@code value}.
     *
     * @param key    must not be {@literal null}.
     * @param value
     * @param offset
     */
    public void valueOpsSet(String key, Object value, long offset) {
        valueOps.set(key, value, offset);
    }

    /**
     * Get the length of the value stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public long valueOpsSize(String key) {
//        Long result = valueOps.size(key);
//        return result == null ? 0 : result;

        return Optional.ofNullable(valueOps.size(key)).orElse(0L);
    }

    /**
     * Sets the bit at {@code offset} in value stored at {@code key}.
     *
     * @param key    must not be {@literal null}.
     * @param offset
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     */
    public boolean valueOpsSetBit(String key, long offset, boolean value) {
//        Boolean result = valueOps.setBit(key, offset, value);
//        return result == null ? false : result;

        return Optional.ofNullable(valueOps.setBit(key, offset, value)).orElse(false);
    }

    /**
     * Get the bit value at {@code offset} of value at {@code key}.
     *
     * @param key    must not be {@literal null}.
     * @param offset
     * @return {@literal null} when used in pipeline / transaction.
     */
    public boolean valueOpsGetBit(String key, long offset) {
//        Boolean result = valueOps.getBit(key, offset);
//        return result == null ? false : result;

        return Optional.ofNullable(valueOps.getBit(key, offset)).orElse(false);
    }

    /**
     * Get / Manipulate specific integer fields of varying bit widths and arbitrary non (necessary) aligned offset stored
     * at a given {@code key}.
     *
     * @param key         must not be {@literal null}.
     * @param subCommands must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    public List<Long> valueOpsBitField(String key, BitFieldSubCommands subCommands) {
        return valueOps.bitField(key, subCommands);
    }

    /* **********************************************************************
     * ListOperations
     ********************************************************************** */

    /**
     * Get elements between {@code begin} and {@code end} from list at {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param start
     * @param end
     * @return {@literal null} when used in pipeline / transaction.
     */
    List<Object> listOpsListRange(String key, long start, long end) {
        return listOps.range(key, start, end);
    }

    /**
     * Trim list at {@code key} to elements between {@code start} and {@code end}.
     *
     * @param key   must not be {@literal null}.
     * @param start
     * @param end
     */
    void listOpsTrim(String key, long start, long end) {
        listOps.trim(key, start, end);
    }

    /**
     * Get the size of list stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsSize(String key) {
        return listOps.size(key);
    }

    /**
     * Prepend {@code value} to {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsLeftPush(String key, Object value) {
        return listOps.leftPush(key, value);
    }

    /**
     * Prepend {@code values} to {@code key}.
     *
     * @param key    must not be {@literal null}.
     * @param values
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsLeftPushAll(String key, Object... values) {
        return listOps.leftPushAll(key, values);
    }

    /**
     * Prepend {@code values} to {@code key}.
     *
     * @param key    must not be {@literal null}.
     * @param values must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsLeftPushAll(String key, Collection<Object> values) {
        return listOps.leftPushAll(key, values);
    }

    /**
     * Prepend {@code values} to {@code key} only if the list exists.
     *
     * @param key   must not be {@literal null}.
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsLeftPushIfPresent(String key, Object value) {
        return listOps.leftPushIfPresent(key, value);
    }

    /**
     * Prepend {@code values} to {@code key} before {@code value}.
     *
     * @param key   must not be {@literal null}.
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsLeftPush(String key, Object pivot, Object value) {
        return listOps.leftPush(key, pivot, value);
    }

    /**
     * Append {@code value} to {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsRightPush(String key, Object value) {
        return listOps.rightPush(key, value);
    }

    /**
     * Append {@code values} to {@code key}.
     *
     * @param key    must not be {@literal null}.
     * @param values
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsRightPushAll(String key, Object... values) {
        return listOps.rightPushAll(key, values);
    }

    /**
     * Append {@code values} to {@code key}.
     *
     * @param key    must not be {@literal null}.
     * @param values
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsRightPushAll(String key, Collection<Object> values) {
        return listOps.rightPushAll(key, values);
    }

    /**
     * Append {@code values} to {@code key} only if the list exists.
     *
     * @param key   must not be {@literal null}.
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsRightPushIfPresent(String key, Object value) {
        return listOps.rightPushIfPresent(key, value);
    }

    /**
     * Append {@code values} to {@code key} before {@code value}.
     *
     * @param key   must not be {@literal null}.
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsRightPush(String key, Object pivot, Object value) {
        return listOps.rightPush(key, pivot, value);
    }

    /**
     * Set the {@code value} list element at {@code index}.
     *
     * @param key   must not be {@literal null}.
     * @param index
     * @param value
     */
    void listOpsSet(String key, long index, Object value) {
        listOps.set(key, index, value);
    }

    /**
     * Removes the first {@code count} occurrences of {@code value} from the list stored at {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param count
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long listOpsRemove(String key, long count, Object value) {
        return listOps.remove(key, count, value);
    }

    /**
     * Get element at {@code index} form list at {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param index
     * @return {@literal null} when used in pipeline / transaction.
     */
    Object listOpsIndex(String key, long index) {
        return listOps.index(key, index);
    }

    /**
     * Removes and returns first element in list stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return can be {@literal null}.
     */
    Object listOpsLeftPop(String key) {
        return listOps.leftPop(key);
    }

    /**
     * Removes and returns first element from lists stored at {@code key} . <br>
     * <b>Blocks connection</b> until element available or {@code timeout} reached.
     *
     * @param key     must not be {@literal null}.
     * @param timeout
     * @param unit    must not be {@literal null}.
     * @return can be {@literal null}.
     */
    Object listOpsLeftPop(String key, long timeout, TimeUnit unit) {
        return listOps.leftPop(key, timeout, unit);
    }

    /**
     * Removes and returns last element in list stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return can be {@literal null}.
     */
    Object listOpsRightPop(String key) {
        return listOps.rightPop(key);
    }

    /**
     * Removes and returns last element from lists stored at {@code key}. <br>
     * <b>Blocks connection</b> until element available or {@code timeout} reached.
     *
     * @param key     must not be {@literal null}.
     * @param timeout
     * @param unit    must not be {@literal null}.
     * @return can be {@literal null}.
     */
    Object listOpsRightPop(String key, long timeout, TimeUnit unit) {
        return listOps.rightPop(key, timeout, unit);
    }

    /**
     * Remove the last element from list at {@code sourceKey}, append it to {@code destinationKey} and return its value.
     *
     * @param sourceKey      must not be {@literal null}.
     * @param destinationKey must not be {@literal null}.
     * @return can be {@literal null}.
     */
    Object listOpsRightPopAndLeftPush(String sourceKey, String destinationKey) {
        return listOps.rightPopAndLeftPush(sourceKey, destinationKey);
    }

    /**
     * Remove the last element from list at {@code srcKey}, append it to {@code dstKey} and return its value.<br>
     * <b>Blocks connection</b> until element available or {@code timeout} reached.
     *
     * @param sourceKey      must not be {@literal null}.
     * @param destinationKey must not be {@literal null}.
     * @param timeout
     * @param unit           must not be {@literal null}.
     * @return can be {@literal null}.
     */
    Object listOpsRightPopAndLeftPush(String sourceKey, String destinationKey, long timeout, TimeUnit unit) {
        return listOps.rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
    }

    /* **********************************************************************
     * SetOperations
     ********************************************************************** */

    /**
     * Add given {@code values} to set at {@code key}.
     *
     * @param key    must not be {@literal null}.
     * @param values
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long setOpsAdd(String key, Object... values) {
        return setOps.add(key, values);
    }

    /**
     * Remove given {@code values} from set at {@code key} and return the number of removed elements.
     *
     * @param key    must not be {@literal null}.
     * @param values
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long setOpsSetRemove(String key, Object... values) {
        return setOps.remove(key, values);
    }

    /**
     * Remove and return a random member from set at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Object setOpsPop(String key) {
        return setOps.pop(key);
    }

    /**
     * Remove and return {@code count} random members from set at {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param count number of random members to pop from the set.
     * @return {@literal null} when used in pipeline / transaction.
     * @since 2.0
     */
    List<Object> setOpsPop(String key, long count) {
        return setOps.pop(key, count);
    }

    /**
     * Move {@code value} from {@code key} to {@code destKey}
     *
     * @param key     must not be {@literal null}.
     * @param value
     * @param destKey must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Boolean setOpsMove(String key, Object value, String destKey) {
        return setOps.move(key, value, destKey);
    }

    /**
     * Get size of set at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long setOpsSize(String key) {
        return setOps.size(key);
    }

    /**
     * Check if set at {@code key} contains {@code value}.
     *
     * @param key must not be {@literal null}.
     * @param o
     * @return {@literal null} when used in pipeline / transaction.
     */
    Boolean setOpsIsMember(String key, Object o) {
        return setOps.isMember(key, o);
    }

    /**
     * Returns the members intersecting all given sets at {@code key} and {@code otherKey}.
     *
     * @param key      must not be {@literal null}.
     * @param otherKey must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> setOpsIntersect(String key, String otherKey) {
        return setOps.intersect(key, otherKey);
    }

    /**
     * Returns the members intersecting all given sets at {@code key} and {@code otherKeys}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> setOpsIntersect(String key, Collection<String> otherKeys) {
        return setOps.intersect(key, otherKeys);
    }

    /**
     * Intersect all given sets at {@code key} and {@code otherKey} and store result in {@code destKey}.
     *
     * @param key      must not be {@literal null}.
     * @param otherKey must not be {@literal null}.
     * @param destKey  must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long setOpsIntersectAndStore(String key, String otherKey, String destKey) {
        return setOps.intersectAndStore(key, otherKey, destKey);
    }

    /**
     * Intersect all given sets at {@code key} and {@code otherKeys} and store result in {@code destKey}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @param destKey   must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long setOpsIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return setOps.intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * Union all sets at given {@code keys} and {@code otherKey}.
     *
     * @param key      must not be {@literal null}.
     * @param otherKey must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> setOpsUnion(String key, String otherKey) {
        return setOps.union(key, otherKey);
    }

    /**
     * Union all sets at given {@code keys} and {@code otherKeys}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> setOpsUnion(String key, Collection<String> otherKeys) {
        return setOps.union(key, otherKeys);
    }

    /**
     * Union all sets at given {@code key} and {@code otherKey} and store result in {@code destKey}.
     *
     * @param key      must not be {@literal null}.
     * @param otherKey must not be {@literal null}.
     * @param destKey  must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long setOpsUnionAndStore(String key, String otherKey, String destKey) {
        return setOps.unionAndStore(key, otherKey, destKey);
    }

    /**
     * Union all sets at given {@code key} and {@code otherKeys} and store result in {@code destKey}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @param destKey   must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long setOpsUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return setOps.unionAndStore(key, otherKeys, destKey);
    }

    /**
     * Diff all sets for given {@code key} and {@code otherKey}.
     *
     * @param key      must not be {@literal null}.
     * @param otherKey must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> setOpsDifference(String key, String otherKey) {
        return setOps.difference(key, otherKey);
    }

    /**
     * Diff all sets for given {@code key} and {@code otherKeys}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> setOpsDifference(String key, Collection<String> otherKeys) {
        return setOps.difference(key, otherKeys);
    }

    /**
     * Diff all sets for given {@code key} and {@code otherKey} and store result in {@code destKey}.
     *
     * @param key      must not be {@literal null}.
     * @param otherKey must not be {@literal null}.
     * @param destKey  must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long setOpsDifferenceAndStore(String key, String otherKey, String destKey) {
        return setOps.differenceAndStore(key, otherKey, destKey);
    }

    /**
     * Diff all sets for given {@code key} and {@code otherKeys} and store result in {@code destKey}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @param destKey   must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long setOpsDifferenceAndStore(String key, Collection<String> otherKeys, String destKey) {
        return setOps.differenceAndStore(key, otherKeys, destKey);
    }

    /**
     * Get all elements of set at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> setOpsMembers(String key) {
        return setOps.members(key);
    }

    /**
     * Get random element from set at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Object setOpsRandomMember(String key) {
        return setOps.randomMember(key);
    }

    /**
     * Get {@code count} distinct random elements from set at {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param count nr of members to return
     * @return empty {@link Set} if {@code key} does not exist.
     * @throws IllegalArgumentException if count is negative.
     */
    Set<Object> setOpsDistinctRandomMembers(String key, long count) {
        return setOps.distinctRandomMembers(key, count);
    }

    /**
     * Get {@code count} random elements from set at {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param count nr of members to return.
     * @return empty {@link List} if {@code key} does not exist or {@literal null} when used in pipeline / transaction.
     * @throws IllegalArgumentException if count is negative.
     */
    List<Object> setOpsRandomMembers(String key, long count) {
        return setOps.randomMembers(key, count);
    }

    /**
     * Iterate over elements in set at {@code key}. <br />
     * <strong>Important:</strong> Call {@link Cursor#close()} when done to avoid resource leak.
     *
     * @param key
     * @param options
     * @return
     * @since 1.4
     */
    Cursor<Object> setOpsScan(String key, ScanOptions options) {
        return setOps.scan(key, options);
    }

    /* **********************************************************************
     * ZSetOperations
     ********************************************************************** */

    /**
     * Add {@code value} to a sorted set at {@code key}, or update its {@code score} if it already exists.
     *
     * @param key   must not be {@literal null}.
     * @param score the score.
     * @param value the value.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Boolean zSetOpsAdd(String key, Object value, double score) {
        return zSetOps.add(key, value, score);
    }

    /**
     * Add {@code tuples} to a sorted set at {@code key}, or update its {@code score} if it already exists.
     *
     * @param key    must not be {@literal null}.
     * @param tuples must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsAdd(String key, Set<ZSetOperations.TypedTuple<Object>> tuples) {
        return zSetOps.add(key, tuples);
    }

    /**
     * Remove {@code values} from sorted set. Return number of removed elements.
     *
     * @param key    must not be {@literal null}.
     * @param values must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsRemove(String key, Object... values) {
        return zSetOps.remove(key, values);
    }

    /**
     * Increment the score of element with {@code value} in sorted set by {@code increment}.
     *
     * @param key   must not be {@literal null}.
     * @param delta
     * @param value the value.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Double zSetOpsIncrementScore(String key, Object value, double delta) {
        return zSetOps.incrementScore(key, value, delta);
    }

    /**
     * Determine the index of element with {@code value} in a sorted set.
     *
     * @param key must not be {@literal null}.
     * @param o   the value.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsRank(String key, Object o) {
        return zSetOps.rank(key, o);
    }

    /**
     * Determine the index of element with {@code value} in a sorted set when scored high to low.
     *
     * @param key must not be {@literal null}.
     * @param o   the value.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsReverseRank(String key, Object o) {
        return zSetOps.reverseRank(key, o);
    }

    /**
     * Get elements between {@code start} and {@code end} from sorted set.
     *
     * @param key   must not be {@literal null}.
     * @param start
     * @param end
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> zSetOpsRange(String key, long start, long end) {
        return zSetOps.range(key, start, end);
    }

    /**
     * Get set of {@link RedisZSetCommands.Tuple}s between {@code start} and {@code end} from sorted set.
     *
     * @param key   must not be {@literal null}.
     * @param start
     * @param end
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<ZSetOperations.TypedTuple<Object>> zSetOpsRangeWithScores(String key, long start, long end) {
        return zSetOps.rangeWithScores(key, start, end);
    }

    /**
     * Get elements where score is between {@code min} and {@code max} from sorted set.
     *
     * @param key must not be {@literal null}.
     * @param min
     * @param max
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> zSetOpsRangeByScore(String key, double min, double max) {
        return zSetOps.rangeByScore(key, min, max);
    }

    /**
     * Get set of {@link RedisZSetCommands.Tuple}s where score is between {@code min} and {@code max} from sorted set.
     *
     * @param key must not be {@literal null}.
     * @param min
     * @param max
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<ZSetOperations.TypedTuple<Object>> zSetOpsRangeByScoreWithScores(String key, double min, double max) {
        return zSetOps.rangeByScoreWithScores(key, min, max);
    }

    /**
     * Get elements in range from {@code start} to {@code end} where score is between {@code min} and {@code max} from
     * sorted set.
     *
     * @param key    must not be {@literal null}.
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> zSetOpsRangeByScore(String key, double min, double max, long offset, long count) {
        return zSetOps.rangeByScore(key, min, max, offset, count);
    }

    /**
     * Get set of {@link RedisZSetCommands.Tuple}s in range from {@code start} to {@code end} where score is between {@code min} and
     * {@code max} from sorted set.
     *
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<ZSetOperations.TypedTuple<Object>> zSetOpsRangeByScoreWithScores(String key, double min, double max, long offset, long count) {
        return zSetOps.rangeByScoreWithScores(key, min, max, offset, count);
    }

    /**
     * Get elements in range from {@code start} to {@code end} from sorted set ordered from high to low.
     *
     * @param key   must not be {@literal null}.
     * @param start
     * @param end
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> zSetOpsReverseRange(String key, long start, long end) {
        return zSetOps.reverseRange(key, start, end);
    }

    /**
     * Get set of {@link RedisZSetCommands.Tuple}s in range from {@code start} to {@code end} from sorted set ordered from high to low.
     *
     * @param key   must not be {@literal null}.
     * @param start
     * @param end
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<ZSetOperations.TypedTuple<Object>> zSetOpsReverseRangeWithScores(String key, long start, long end) {
        return zSetOps.reverseRangeWithScores(key, start, end);
    }

    /**
     * Get elements where score is between {@code min} and {@code max} from sorted set ordered from high to low.
     *
     * @param key must not be {@literal null}.
     * @param min
     * @param max
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> zSetOpsReverseRangeByScore(String key, double min, double max) {
        return zSetOps.reverseRangeByScore(key, min, max);
    }

    /**
     * Get set of {@link RedisZSetCommands.Tuple} where score is between {@code min} and {@code max} from sorted set ordered from high to
     * low.
     *
     * @param key must not be {@literal null}.
     * @param min
     * @param max
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<ZSetOperations.TypedTuple<Object>> zSetOpsReverseRangeByScoreWithScores(String key, double min, double max) {
        return zSetOps.reverseRangeByScoreWithScores(key, min, max);
    }

    /**
     * Get elements in range from {@code start} to {@code end} where score is between {@code min} and {@code max} from
     * sorted set ordered high -> low.
     *
     * @param key    must not be {@literal null}.
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> zSetOpsReverseRangeByScore(String key, double min, double max, long offset, long count) {
        return zSetOps.reverseRangeByScore(key, min, max, offset, count);
    }

    /**
     * Get set of {@link RedisZSetCommands.Tuple} in range from {@code start} to {@code end} where score is between {@code min} and
     * {@code max} from sorted set ordered high -> low.
     *
     * @param key    must not be {@literal null}.
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<ZSetOperations.TypedTuple<Object>> zSetOpsReverseRangeByScoreWithScores(String key, double min, double max, long offset, long count) {
        return zSetOps.reverseRangeByScoreWithScores(key, min, max, offset, count);
    }

    /**
     * Count number of elements within sorted set with scores between {@code min} and {@code max}.
     *
     * @param key must not be {@literal null}.
     * @param min
     * @param max
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsCount(String key, double min, double max) {
        return zSetOps.count(key, min, max);
    }

    /**
     * Returns the number of elements of the sorted set stored with given {@code key}.
     *
     * @param key
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsSize(String key) {
        return zSetOps.size(key);
    }

    /**
     * Get the size of sorted set with {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsZCard(String key) {
        return zSetOps.zCard(key);
    }

    /**
     * Get the score of element with {@code value} from sorted set with key {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param o   the value.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Double zSetOpsScore(String key, Object o) {
        return zSetOps.score(key, o);
    }

    /**
     * Remove elements in range between {@code start} and {@code end} from sorted set with {@code key}.
     *
     * @param key   must not be {@literal null}.
     * @param start
     * @param end
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsRemoveRange(String key, long start, long end) {
        return zSetOps.removeRange(key, start, end);
    }

    /**
     * Remove elements with scores between {@code min} and {@code max} from sorted set with {@code key}.
     *
     * @param key must not be {@literal null}.
     * @param min
     * @param max
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsRemoveRangeByScore(String key, double min, double max) {
        return zSetOps.removeRangeByScore(key, min, max);
    }

    /**
     * Union sorted sets at {@code key} and {@code otherKeys} and store result in destination {@code destKey}.
     *
     * @param key      must not be {@literal null}.
     * @param otherKey must not be {@literal null}.
     * @param destKey  must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsUnionAndStore(String key, String otherKey, String destKey) {
        return zSetOps.unionAndStore(key, otherKey, destKey);
    }

    /**
     * Union sorted sets at {@code key} and {@code otherKeys} and store result in destination {@code destKey}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @param destKey   must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsUnionAndStore(String key, Collection<String> otherKeys, String destKey) {
        return zSetOps.unionAndStore(key, otherKeys, destKey);
    }

    /**
     * Union sorted sets at {@code key} and {@code otherKeys} and store result in destination {@code destKey}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @param destKey   must not be {@literal null}.
     * @param aggregate must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsUnionAndStore(String key, Collection<String> otherKeys, String destKey, RedisZSetCommands.Aggregate aggregate) {
        return zSetOpsUnionAndStore(key, otherKeys, destKey, aggregate, RedisZSetCommands.Weights.fromSetCount(1 + otherKeys.size()));
    }

    /**
     * Union sorted sets at {@code key} and {@code otherKeys} and store result in destination {@code destKey}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @param destKey   must not be {@literal null}.
     * @param aggregate must not be {@literal null}.
     * @param weights   must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsUnionAndStore(String key, Collection<String> otherKeys, String destKey, RedisZSetCommands.Aggregate aggregate, RedisZSetCommands.Weights weights) {
        return zSetOps.unionAndStore(key, otherKeys, destKey, aggregate, weights);
    }

    /**
     * Intersect sorted sets at {@code key} and {@code otherKey} and store result in destination {@code destKey}.
     *
     * @param key      must not be {@literal null}.
     * @param otherKey must not be {@literal null}.
     * @param destKey  must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsIntersectAndStore(String key, String otherKey, String destKey) {
        return zSetOps.intersectAndStore(key, otherKey, destKey);
    }

    /**
     * Intersect sorted sets at {@code key} and {@code otherKeys} and store result in destination {@code destKey}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @param destKey   must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsIntersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        return zSetOps.intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * Intersect sorted sets at {@code key} and {@code otherKeys} and store result in destination {@code destKey}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @param destKey   must not be {@literal null}.
     * @param aggregate must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsIntersectAndStore(String key, Collection<String> otherKeys, String destKey, RedisZSetCommands.Aggregate aggregate) {
        return zSetOpsIntersectAndStore(key, otherKeys, destKey, aggregate, RedisZSetCommands.Weights.fromSetCount(1 + otherKeys.size()));
    }

    /**
     * Intersect sorted sets at {@code key} and {@code otherKeys} and store result in destination {@code destKey}.
     *
     * @param key       must not be {@literal null}.
     * @param otherKeys must not be {@literal null}.
     * @param destKey   must not be {@literal null}.
     * @param aggregate must not be {@literal null}.
     * @param weights   must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long zSetOpsIntersectAndStore(String key, Collection<String> otherKeys, String destKey, RedisZSetCommands.Aggregate aggregate, RedisZSetCommands.Weights weights) {
        return zSetOps.intersectAndStore(key, otherKeys, destKey, aggregate, weights);
    }

    /**
     * Iterate over elements in zset at {@code key}. <br />
     * <strong>Important:</strong> Call {@link Cursor#close()} when done to avoid resource leak.
     *
     * @param key
     * @param options
     * @return {@literal null} when used in pipeline / transaction.
     */
    Cursor<ZSetOperations.TypedTuple<Object>> zSetOpsScan(String key, ScanOptions options) {
        return zSetOps.scan(key, options);
    }

    /**
     * Get all elements with lexicographical ordering from {@literal ZSET} at {@code key} with a value between
     * {@link RedisZSetCommands.Range#getMin()} and {@link RedisZSetCommands.Range#getMax()}.
     *
     * @param key   must not be {@literal null}.
     * @param range must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> zSetOpsRangeByLex(String key, RedisZSetCommands.Range range) {
        return zSetOps.rangeByLex(key, range);
    }

    /**
     * Get all elements {@literal n} elements, where {@literal n = } {@link RedisZSetCommands.Limit#getCount()}, starting at
     * {@link RedisZSetCommands.Limit#getOffset()} with lexicographical ordering from {@literal ZSET} at {@code key} with a value between
     * {@link RedisZSetCommands.Range#getMin()} and {@link RedisZSetCommands.Range#getMax()}.
     *
     * @param key   must not be {@literal null}
     * @param range must not be {@literal null}.
     * @param limit can be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<Object> zSetOpsRangeByLex(String key, RedisZSetCommands.Range range, RedisZSetCommands.Limit limit) {
        return zSetOps.rangeByLex(key, range, limit);
    }

    /* **********************************************************************
     * HashOperations
     ********************************************************************** */

    /**
     * Delete given hash {@code hashKeys}.
     *
     * @param key      must not be {@literal null}.
     * @param hashKeys must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long hashOpsDelete(String key, Object... hashKeys) {
        return hashOps.delete(key, hashKeys);
    }

    /**
     * Determine if given hash {@code hashKey} exists.
     *
     * @param key     must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Boolean hashOpsHasKey(String key, Object hashKey) {
        return hashOps.hasKey(key, hashKey);
    }

    /**
     * Get value for given {@code hashKey} from hash at {@code key}.
     *
     * @param key     must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @return {@literal null} when key or hashKey does not exist or used in pipeline / transaction.
     */
    Object hashOpsGet(String key, Object hashKey) {
        return hashOps.get(key, hashKey);
    }

    /**
     * Get values for given {@code hashKeys} from hash at {@code key}.
     *
     * @param key      must not be {@literal null}.
     * @param hashKeys must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    List<?> hashOpsMultiGet(String key, Collection<String> hashKeys) {
        return hashOps.multiGet(key, hashKeys);
    }

    /**
     * Increment {@code value} of a hash {@code hashKey} by the given {@code delta}.
     *
     * @param key     must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @param delta
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long hashOpsIncrement(String key, String hashKey, long delta) {
        return hashOps.increment(key, hashKey, delta);
    }

    /**
     * Increment {@code value} of a hash {@code hashKey} by the given {@code delta}.
     *
     * @param key     must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @param delta
     * @return {@literal null} when used in pipeline / transaction.
     */
    Double hashOpsIncrement(String key, String hashKey, double delta) {
        return hashOps.increment(key, hashKey, delta);
    }

    /**
     * Get key set (fields) of hash at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Set<String> hashOpsKeys(String key) {
        return hashOps.keys(key);
    }

    /**
     * Returns the length of the value associated with {@code hashKey}. If either the {@code key} or the {@code hashKey}
     * do not exist, {@code 0} is returned.
     *
     * @param key     must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     * @since 2.1
     */
    Long hashOpsLengthOfValue(String key, String hashKey) {
        return hashOps.lengthOfValue(key, hashKey);
    }

    /**
     * Get size of hash at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Long hashOpsSize(String key) {
        return hashOps.size(key);
    }

    /**
     * Set multiple hash fields to multiple values using data provided in {@code m}.
     *
     * @param key must not be {@literal null}.
     * @param m   must not be {@literal null}.
     */
    void hashOpsPutAll(String key, Map<String, ?> m) {
        hashOps.putAll(key, m);
    }

    /**
     * Set the {@code value} of a hash {@code hashKey}.
     *
     * @param key     must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @param value
     */
    void hashOpsPut(String key, String hashKey, Object value) {
        hashOps.put(key, hashKey, value);
        ;
    }

    /**
     * Set the {@code value} of a hash {@code hashKey} only if {@code hashKey} does not exist.
     *
     * @param key     must not be {@literal null}.
     * @param hashKey must not be {@literal null}.
     * @param value
     * @return {@literal null} when used in pipeline / transaction.
     */
    Boolean hashOpsPutIfAbsent(String key, String hashKey, Object value) {
        return hashOps.putIfAbsent(key, hashKey, value);
    }

    /**
     * Get entry set (values) of hash at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    List<?> hashOpsValues(String key) {
        return hashOps.values(key);
    }

    /**
     * Get entire hash stored at {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return {@literal null} when used in pipeline / transaction.
     */
    Map<String, ?> hashOpsEntries(String key) {
        return hashOps.entries(key);
    }

    /**
     * Use a {@link Cursor} to iterate over entries in hash at {@code key}. <br />
     * <strong>Important:</strong> Call {@link Cursor#close()} when done to avoid resource leak.
     *
     * @param key     must not be {@literal null}.
     * @param options
     * @return {@literal null} when used in pipeline / transaction.
     * @since 1.4
     */
    Cursor<Map.Entry<String, Object>> hashOpsScan(String key, ScanOptions options) {
        return hashOps.scan(key, options);
    }

}
