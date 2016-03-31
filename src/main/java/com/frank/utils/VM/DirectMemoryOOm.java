package com.frank.utils.VM;

import sun.misc.Unsafe;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;

/**
 * @author <a href="mailto:heshanshao@ebnew.com">Administrator</a>
 * @version V1.0
 * 手动申请内存
 * @date 2016/3/30
 */
public class DirectMemoryOOm {
    private  static  final  int _1mb=1024*1024;

    public static void main(String[] args) throws Exception{

        Field unsafeField= Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe=(Unsafe)unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1mb);
        }

    }
}
