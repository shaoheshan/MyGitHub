package com.frank.utils.test;

import com.frank.utils.string.EmptyUtils;
import com.google.common.cache.*;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:heshanshao@ebnew.com">Administrator</a>
 * @version V1.0
 * @date 2016/3/24
 */
public class GuavaCache {
    private  static Boolean status=false;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //����ӿ�������LoadingCache��LoadingCache�ڻ��������ʱ�����Զ����ػ���
        LoadingCache<Integer,Student> studentCache
                //CacheBuilder�Ĺ��캯����˽�еģ�ֻ��ͨ���侲̬����newBuilder()�����CacheBuilder��ʵ��
                = CacheBuilder.newBuilder()
                //���ò�������Ϊ8������������ָ����ͬʱд������߳���
                .concurrencyLevel(8)
                        //����д�����8���ӹ���
                .expireAfterWrite(8, TimeUnit.SECONDS)
                        //���û��������ĳ�ʼ����Ϊ10
                .initialCapacity(10)
                        //���û����������Ϊ100������100֮��ͻᰴ��LRU�������ʹ���㷨���Ƴ�������
                .maximumSize(100)
                        //����Ҫͳ�ƻ����������
                .recordStats()
                        //���û�����Ƴ�֪ͨ
                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                        //build�����п���ָ��CacheLoader���ڻ��治����ʱͨ��CacheLoader��ʵ���Զ����ػ���
                .build(
                        new CacheLoader<Integer, Student>() {
                            @Override
                            public Student load(Integer key) throws Exception {
                                System.out.println("load student " + key);
                                Student student = new Student();
                                student.setId(key);
                                student.setName("name " + key);
                                return student;
                            }
                        }
                );
        Cache<String,String> cache=CacheBuilder.newBuilder().expireAfterWrite(8, TimeUnit.SECONDS).recordStats().build();
        cache.put("test1", "test1");
        long start=System.currentTimeMillis();
        System.out.println(cache.getIfPresent("test1"));
        for (int i=0;i<5;i++) {

             Thread t=new Thread(new Runnable() {
                 @Override
                 public void run() {
                     while (!status) {
                         try {
                             System.out.println("current thread " + Thread.currentThread().getId()+":"+System.currentTimeMillis());
                             Thread.sleep(1000);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                 }
             });
             t.setDaemon(true);
             t.setName("name"+i);
             t.start();

         }
        long end=0L;

        while(!status){
           if(EmptyUtils.isEmpty(cache.getIfPresent("test1"))) {
               end=System.currentTimeMillis();
               status = true;
           }
        }
       /* for (int i=0;i<20;i++) {
            //�ӻ����еõ����ݣ���������û�����ù����棬������Ҫͨ��CacheLoader���ػ�������
            Student student = studentCache.get(1);
            System.out.println(student);
            //����1��
            TimeUnit.SECONDS.sleep(1);
        }*/

        System.out.println("cache stats:");
        //����ӡ����������ʵ� ���
        System.out.println(cache.stats().toString());
        System.out.println(end-start+":"+(end-start)/1000);
        Map<String,String> maps= Maps.newHashMap();
        maps.put("1","11");
        Set<Map.Entry<String,String>> str=maps.entrySet();
        for(Map.Entry<String,String> entry:str){
            System.out.println(entry.getKey());
        }
        Iterator<Map.Entry<String,String>> it=maps.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> entry=it.next();
        }
        TimeUnit.SECONDS.sleep(1);
        TimeUnit.MILLISECONDS.sleep(1);
    }
}
