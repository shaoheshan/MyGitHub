package com.frank.utils.redis;

import redis.clients.jedis.SortingParams;

import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * Redis Shard �ӿ�
 * @author <a href="mailto:heshanshao@ebnew.com">Administrator</a>
 * @version V1.0
 * @date 2016/3/11
 *//**
 * @author <a href="mailto:heshanshao@ebnew.com">Administrator</a>
 * @version V1.0
 * @date 2016/3/11
 */
/**
 * .
 *
 * @version Ver 1.0
 * @Date  ����10:22:26
 */
public interface BidRedis {

    /**
     * ȷ��key�Ƿ����.
     * <p>
     * key����,����true;����,����false.
     *
     * @author Frank
     * @param key
     * @return
     */
    Boolean exists(String key);

    /**
     * �����������ڵ�key,ʹ���ܼ���ʹ��.
     * <p>
     * key���Լ���ʹ��,����1L;����,����0L.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Long persist(String key);

    /**
     * ����ֵ������.
     * <p>
     * Redis�е���������,��:String,Set��.
     *
     * @author Frank 
     * @param key
     * @return
     */
    String type(String key);

    /**
     * ����key����Чʱ��.
     * <p>
     * ���óɹ�,����1L;����ʧ��,����0L.
     *
     * @author Frank 
     * @param key
     * @param seconds
     *            ����ʱ��,��λΪ��
     * @return
     */
    Long expire(String key, int seconds);

    /**
     * ����һ��key����Чʱ��.
     * <p>
     * ���óɹ�,����>=1L;����ʧ��,����0L.
     *
     * @param map
     *            key=>key, value=>����ʱ��
     * @return
     */
    Long expire(Map<String, Integer> map);

    /**
     * ����һ��key�ĵ���ʱ��.
     * <p>
     * ���óɹ�,����1L;����ʧ��,����0L.
     *
     * @author Frank
     * @param key
     * @param unixTime
     *            ���1970�󾭹�������
     * @return
     */
    Long expireAt(String key, Long unixTime);

    /**
     * ����һ��key�ĵ���ʱ��.
     * <p>
     * ���óɹ�,����>=1L;����ʧ��,����0L.
     *
     * @param map
     *            key=>key, value=>����ʱ��,���1970�󾭹�������
     * @return
     */
    Long expireAt(Map<String, Long> map);

    /**
     * ���һ��key����Чʱ��.
     * <P>
     * ����Key����Чʱ��,��λΪ��.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * ɾ��ָ����key.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Long del(String key);

    /**
     * ɾ��ָ����һ��key.
     *
     * @author Frank 
     * @param keys
     * @return
     */
    Long del(String... keys);

    /**
     * ����һ���ַ���.
     * <p>
     * ֻ����getString(key)���ʹ��.<br>
     * ����ɹ����޷�ͨ��getObject(key)�ӿ�ȡ����ȷ����.<br>
     * ��������Ҫ�Դ�String����incr/decr/incrBy/decrBy����,������setObject�滻.
     *
     * @see #getString(String)
     * @see #setString(String, int, String)
     * @author Frank 
     * @param key
     * @param value
     * @return "OK" or "failed"
     */
    String setString(String key, String value);

    /**
     * ����һ���ַ���.
     * <p>
     * ֻ����getString(key)���ʹ��.<br>
     * ����ɹ����޷�ͨ��getObject(key)�ӿ�ȡ����ȷ����.<br>
     * ��������Ҫ�Դ�String����incr/decr/incrBy/decrBy����,������setObject�滻.
     *
     * @see #getString(String)
     * @see #setString(String, String)
     * @author Frank 
     * @param key
     * @param seconds
     * @param value
     * @return "OK" or "failed"
     */
    String setString(String key, int seconds, String value);

    /**
     * ����һ���ַ���.
     * <p>
     * ֻ����setString()���ʹ��.<br>
     * ͨ��setObject(key,value)֮��Ľӿڻ���Ķ���,�޷�ͨ���˽ӿ�ȡ����ȷ����.
     *
     * @see #setString(String, String)
     * @see #setString(String, int, String)
     * @author Frank 
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * �����µ��ַ���,�����ؾɵ��ַ���.
     * <p>
     * �ַ������ܴ���1GB.
     *
     * @author Frank 
     * @param key
     * @param value
     * @return
     */
    String getSetString(String key, String value);

    /**
     * ����һ���ַ���.
     * <p>
     * ֻ����getObject()���ʹ��.<br>
     * ����ɹ����޷�ͨ��getString(key)�ӿ�ȡ����ȷ����.<br>
     * ��������Ҫ�Դ�String����incr/decr/incrBy/decrBy����,�����ô˷���.
     *
     * @see #getObject(String)
     * @see #setObject(String, int, Object)
     * @author Frank 
     * @param key
     * @param value
     * @return "OK" or "failed"
     */
    String setObject(String key, Object value);

    /**
     * ����һ���ַ���.
     * <p>
     * ֻ����getObject()���ʹ��.<br>
     * ����ɹ����޷�ͨ��getString(key)�ӿ�ȡ����ȷ����.<br>
     * ��������Ҫ�Դ�String����incr/decr/incrBy/decrBy����,�����ô˷���.
     *
     * @see #getObject(String)
     * @see #setObject(String, Object)
     * @author Frank 
     * @param key
     * @param seconds
     * @param value
     * @return "OK" or "failed"
     */
    String setObject(String key, int seconds, Object value);

    /**
     * ����һ���ַ���.
     * <p>
     * ֻ����setObject()���ʹ��.<br>
     * ͨ��setString(key,value)֮��Ľӿڻ���Ķ���,�޷�ͨ���˽ӿ�ȡ����ȷ����.
     *
     * @see #setObject(String, Object)
     * @see #setObject(String, int, Object)
     * @author Frank 
     * @param key
     * @return
     */
    Object getObject(String key);

    /**
     * �����µĶ���,�����ؾɵĶ���.
     * <p>
     * �����ܴ���1GB.
     *
     * @author Frank 
     * @param key
     * @param value
     * @return
     */
    Object getSetObject(String key, Object value);

    /**
     * ��key������1.
     * <p>
     * ���key������,����ִ�д˲���ǰ����Ĭ��ֵΪ0.<br>
     * ���key����,��key��ʮ������ֵ���ַ�����ʾ(e.g: "-123L"),���ڴ����ݻ���������(e.g:return -122L).<br>
     * ���key����,��key����ʮ������ֵ���ַ�����ʾ,�򷵻�null.
     *
     * @see #decr(String)
     * @see #incrBy(String, long)
     * @see #decrBy(String, long)
     * @author Frank 
     * @param key
     * @return
     */
    Long incr(String key);

    /**
     * ��key������N.
     *
     * @see #decrBy(String, long)
     * @see #decr(String)
     * @see #incr(String)
     * @author Frank 
     * @param key
     * @param integer
     * @return
     */
    Long incrBy(String key, long integer);

    /**
     * ��key���Լ�1.
     * <p>
     * ���key������,����ִ�д˲���ǰ����Ĭ��ֵΪ0.<br>
     * ���key����,��key��ʮ������ֵ���ַ�����ʾ(e.g: "-123L"),���ڴ����ݻ���������(e.g:return -124L).<br>
     * ���key����,��key����ʮ������ֵ���ַ�����ʾ,�򷵻�null.
     *
     * @see #incr(String)
     * @see #incrBy(String, long)
     * @see #decrBy(String, long)
     * @author Frank 
     * @param key
     * @return
     */
    Long decr(String key);

    /**
     * ��key���Լ�N.
     *
     * @see #incrBy(String, long)
     * @see #decr(String)
     * @see #incr(String)
     * @author Frank 
     * @param key
     * @param integer
     * @return
     */
    Long decrBy(String key, long integer);

    /**
     * ������Ϊkey��hash�����Ԫ��field<��>value.
     * <p>
     * ����ֶ��Ѿ�����,�����fieldֵ,����0L;<br>
     * ���򴴽�һ�µ�field,����1L.
     *
     * @author Frank 
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hset(String key, String field, Object value);

    /**
     * ������Ϊkey��hash�����Ԫ��field i<��>value i.
     * <p>
     * �����ɹ�,����true;<br>
     * ����,����false.
     *
     * @author Frank 
     * @param key
     * @param hash
     * @return
     */
    Boolean hmset(String key, Map<String, Object> hash);

    /**
     * ��������Ϊkey��hash��field��Ӧ��value.
     *
     * @author Frank 
     * @param key
     * @param field
     * @return
     */
    Object hget(String key, String field);

    /**
     * ��������Ϊkey��hash�����еļ�(field)�����Ӧ��value.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Map<String, Object> hgetAll(String key);

    /**
     * ��������Ϊkey��hash��field i��Ӧ��value.
     *
     * @author Frank 
     * @param key
     * @param fields
     * @return
     */
    List<Object> hmget(String key, String... fields);

    /**
     * ����Ϊkey��hash���Ƿ���ڼ�Ϊfield����.
     * <p>
     * ����,����true;<br>
     * ����,����false.
     *
     * @author Frank 
     * @param key
     * @param field
     * @return
     */
    Boolean hexists(String key, String field);

    /**
     * ɾ������Ϊkey��hash�м�Ϊfield i����.
     * <p>
     * �������,ɾ��,����1L;<br>
     * ����,����0L,��ִ�в���.
     *
     * @author Frank 
     * @param key
     * @param fields
     * @return
     */
    Long hdel(final String key, final String... fields);

    /**
     * ��������Ϊkey��hash��Ԫ�ظ���.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Long hlen(String key);

    /**
     * ��������Ϊkey��hash�����м�.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Set<String> hkeys(String key);

    /**
     * ��������Ϊkey��hash�����м���Ӧ��value.
     *
     * @author Frank 
     * @param key
     * @return
     */
    List<Object> hvals(String key);

    /**
     * ������Ϊkey��listβ���һ��ֵΪvalue��Ԫ��.
     * <p>
     * ������ɺ��list����.
     *
     * @author Frank 
     * @param key
     * @param values
     * @return
     */
    Long rpush(String key, Object... values);

    /**
     * ������Ϊkey��listͷ���һ��ֵΪvalue�� Ԫ��.
     *
     * @author Frank 
     * @param key
     * @param values
     * @return
     */
    Long lpush(String key, Object... values);

    /**
     * ��������Ϊkey��list�ĳ���.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Long llen(String key);

    /**
     * ��������Ϊkey��list��start��end֮���Ԫ��(�±��0��ʼ).
     *
     * @author Frank 
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<Object> lrange(String key, long start, long end);

    /**
     * ��ȡ����Ϊkey��list,����start��end֮���Ԫ��.
     *
     * @author Frank 
     * @param key
     * @param start
     * @param end
     * @return
     */
    String ltrim(String key, long start, long end);

    /**
     * ��������Ϊkey��list��indexλ�õ�Ԫ��.
     *
     * @author Frank 
     * @param key
     * @param index
     * @return
     */
    Object lindex(String key, long index);

    /**
     * ������Ϊkey��list��indexλ�õ�Ԫ�ظ�ֵΪvalue.
     *
     * @author Frank 
     * @param key
     * @param index
     * @param value
     * @return
     */
    String lset(String key, long index, Object value);

    /**
     * ɾ��count������Ϊkey��list��ֵΪvalue��Ԫ��.
     * <p>
     * count=0,ɾ������ֵΪvalue��Ԫ��;<br>
     * count>0,��ͷ��βɾ��count��ֵΪvalue��Ԫ��;<br>
     * count<0,��β��ͷɾ��count��ֵΪvalue��Ԫ��.
     *
     * @author Frank 
     * @param key
     * @param count
     * @param value
     * @return
     */
    Long lrem(String key, long count, Object value);

    /**
     * ���ز�ɾ������Ϊkey��list�е���Ԫ��.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Object lpop(String key);

    /**
     * ���ز�ɾ������Ϊkey��list�е�βԪ��.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Object rpop(String key);

    /**
     * ������Ϊkey��set�����Ԫ��member i.
     *
     * @author Frank 
     * @param key
     * @param members
     * @return
     */
    Long sadd(String key, Object... members);

    /**
     * ��������Ϊkey��set������Ԫ��.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Set<Object> smembers(String key);

    /**
     * ɾ������Ϊkey��set�е�Ԫ��member i.
     *
     * @author Frank 
     * @param key
     * @param members
     * @return
     */
    Long srem(String key, Object... members);

    /**
     * ������ز�ɾ������Ϊkey��set��һ��Ԫ��.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Object spop(String key);

    /**
     * ��������Ϊkey��set�Ļ���.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Long scard(String key);

    /**
     * ����member�Ƿ�������Ϊkey��set��Ԫ��.
     *
     * @author Frank 
     * @param key
     * @param member
     * @return
     */
    Boolean sismember(String key, String member);

    /**
     * �����������Ϊkey��set��һ��Ԫ��.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Object srandmember(String key);

    /**
     * ������Ϊkey��zset�����Ԫ��member,score��������.
     * <p>
     * �����Ԫ���Ѿ�����,�����score���¸�Ԫ�ص�˳��.
     *
     * @author Frank 
     * @param key
     * @param score
     * @param member
     * @return
     */
    Long zadd(String key, double score, Object member);

    /**
     * ������Ϊkey��zset�����Ԫ��member i,score i��������.
     * <p>
     * �����Ԫ���Ѿ�����,�����score���¸�Ԫ�ص�˳��.
     *
     * @author Frank 
     * @param key
     * @param scoreMembers
     * @return
     */
    Long zadd(String key, Map<Double, Object> scoreMembers);

    /**
     * ɾ������Ϊkey��zset�е�Ԫ��member i.
     *
     * @author Frank 
     * @param key
     * @param members
     * @return
     */
    Long zrem(String key, Object... members);

    /**
     * ���������Ϊkey��zset���Ѿ�����Ԫ��member,���Ԫ�ص�score����increment;<br>
     * �����򼯺�����Ӹ�Ԫ��,��score��ֵΪincrement.
     *
     * @author Frank 
     * @param key
     * @param score
     * @param member
     * @return
     */
    Double zincrby(String key, double score, Object member);

    /**
     * ��������Ϊkey��zset�Ļ���.
     *
     * @author Frank 
     * @param key
     * @return
     */
    Long zcard(String key);

    /**
     * ��������Ϊkey��zset��Ԫ��element��score.
     *
     * @author Frank 
     * @param key
     * @param member
     * @return
     */
    Double zscore(String key, Object member);

    /**
     * ��������Ϊkey��zset��score >= min��score <= max��Ԫ�ظ���.
     *
     * @author Frank 
     * @param key
     * @param min
     * @param max
     * @return
     */

    Long zcount(String key, double min, double max);

    /**
     * ��������Ϊkey��zset(Ԫ���Ѱ�score��С��������)��memberԪ�ص�rank(��index,��0��ʼ),<br>
     * ��û��memberԪ��,���� "nil".
     *
     * @author Frank 
     * @param key
     * @param member
     * @return
     */
    Long zrank(String key, Object member);

    /**
     * ��������Ϊkey��zset(Ԫ���Ѱ�score�Ӵ�С����)��memberԪ�ص�rank(��index,��0��ʼ),<br>
     * ��û��memberԪ��,���� "nil".
     *
     * @author Frank 
     * @param key
     * @param member
     * @return
     */
    Long zrevrank(String key, Object member);

    /**
     * ��������Ϊkey��zset(Ԫ���Ѱ�score��С��������)�е�index��start��end������Ԫ��.
     *
     * @author Frank 
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<Object> zrange(String key, long start, long end);

    /**
     * ��������Ϊkey��zset(Ԫ���Ѱ�score�Ӵ�С����)�е�index��start��end������Ԫ��.
     *
     * @author Frank 
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<Object> zrevrange(String key, long start, long end);

    /**
     * ��������Ϊkey��zset��score >= min��score <= max������Ԫ��(Ԫ���Ѱ�score��С��������).
     *
     * @author Frank 
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<Object> zrangeByScore(String key, double min, double max);

    /**
     * ��������Ϊkey��zset��score >= min��score <= max������Ԫ��(Ԫ���Ѱ�score�Ӵ�С����).
     *
     * @author Frank 
     * @param key
     * @param max
     * @param min
     * @return
     */
    Set<Object> zrevrangeByScore(String key, double max, double min);

    /**
     * ��������Ϊkey��zset��score >= min��score <= max��count��Ԫ��(Ԫ���Ѱ�score��С��������).
     *
     * @author Frank 
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    Set<Object> zrangeByScore(String key, double min, double max, int offset, int count);

    /**
     * ��������Ϊkey��zset��score >= min��score <= max��count��Ԫ��(Ԫ���Ѱ�score�Ӵ�С����).
     *
     * @author Frank 
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return
     */
    Set<Object> zrevrangeByScore(String key, double max, double min, int offset, int count);

    /**
     * ����.
     *
     * @author Frank 
     * @param key
     * @return
     */
    List<Object> sort(String key);

    /**
     * ����.
     *
     * @author Frank 
     * @param key
     * @param sortingParameters
     * @return
     */
    List<Object> sort(String key, SortingParams sortingParameters);
}

