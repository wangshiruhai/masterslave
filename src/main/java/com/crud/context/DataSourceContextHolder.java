package com.crud.context;

/**
 * 利用ThreadLocal封装的保存数据源上线的上下文context
 * @author tommy
 * @date 11/6/21
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    /**
     * 赋值
     *
     * @param datasourceType
     */
    public static void set(String datasourceType) {
        context.set(datasourceType);
    }

    /**
     * 获取值
     * @return
     */
    public static String get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
