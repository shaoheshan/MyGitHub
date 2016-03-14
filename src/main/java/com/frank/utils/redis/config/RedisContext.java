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
     * @return
     */
    public static String getTenant() {
        return EmptyUtils.isEmpty(TENANT_LOCAL.get()) ? "" : TENANT_LOCAL.get().trim() + "_";
    }

    /**
     * �����⻧��ʶ.
     *
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
     */
    public static void remove() {
        TENANT_LOCAL.remove();
    }
}
