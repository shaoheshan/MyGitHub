package com.frank.utils.redis.config;

import com.frank.utils.string.EmptyUtils;

/**
 * @author <a href="mailto:heshanshao@ebnew.com">Administrator</a>
 * @version V1.0
 * @date 2016/3/11
 */
public class RedisContext {

    /**
     * ��¼�⻧��ʶ
     */
    private final static InheritableThreadLocal<String> TENANT_LOCAL = new InheritableThreadLocal<String>();

    /**
     * ��ȡ�⻧��ʶ.
     *
     * @author 	: <a href="mailto:wangtao@ebnew.com">wangtao</a>  2015-7-29 ����4:10:33
     * @return
     */
    public static String getTenant() {
        return EmptyUtils.isEmpty(TENANT_LOCAL.get()) ? "" : TENANT_LOCAL.get().trim() + "_";
    }

    /**
     * �����⻧��ʶ.
     *
     * @author 	: <a href="mailto:wangtao@ebnew.com">wangtao</a>  2015-7-29 ����4:11:01
     * @param tenant
     */
    public static void setTenant(String tenant) {
        if (!EmptyUtils.isEmpty(tenant)) {
            TENANT_LOCAL.set(tenant);
        }
    }

    /**
     * ɾ��.
     *
     * @author 	: <a href="mailto:wangtao@ebnew.com">wangtao</a>  2015-7-29 ����4:13:09
     */
    public static void remove() {
        TENANT_LOCAL.remove();
    }
}
