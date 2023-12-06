package com.jyf.plugin.util;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

public class HttpUtil {

    private HttpUtil(){}

    /**
     * Http连接超时时间
     */
    private static final int MIN_CONNECT_TIMEOUT = 3000;
    /**
     * Http 写入超时时间
     */
    private static final int MIN_WRITE_TIMEOUT = 3000;
    /**
     * Http Read超时时间
     */
    private static final int MIN_READ_TIMEOUT = 3000;
    /**
     * Http Async Call Timeout
     */
    private static final int MIN_CALL_TIMEOUT = 3000;
    /**
     * Http连接池
     */
    private static final int CONNECTION_POOL_SIZE = 1000;
    /**
     * 静态连接池对象
     */
    private static final ConnectionPool mConnectionPool = new ConnectionPool(CONNECTION_POOL_SIZE, 30, TimeUnit.MINUTES);
    //executor = new ThreadPoolExecutor(0, 2147483647, ...这个是静态的，21亿连接数量
//    private static RealConnectionPool mConnectionPool=new RealConnectionPool(connectionPoolSize, 30, TimeUnit.MINUTES);
//    /**
//     * ContentType
//     */
//    private static final String CONTENT_TYPE = "application/json;charset=utf-8";
//    /**
//     * AcceptType
//     */
//    private static final String ACCEPT_TYPE = "application/json;charset=utf-8";
//    /**
//     * Content-Type
//     */
//    private static final MediaType MEDIA_TYPE_CONTENT_TYPE = MediaType.parse(CONTENT_TYPE);

    /**
     * 获取Http Client对象
     * 降低连接时间
     *
     * @return
     */
    public static OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
//                .addInterceptor(new RetryIntercepter(3))//重试3次
//                .addInterceptor(new GzipRequestInterceptor())//gzip压缩
                .connectTimeout(MIN_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS) //连接超时
                .readTimeout(MIN_READ_TIMEOUT, TimeUnit.MILLISECONDS) //读取超时
                .writeTimeout(MIN_WRITE_TIMEOUT, TimeUnit.MILLISECONDS) //写超时
                .callTimeout(MIN_CALL_TIMEOUT, TimeUnit.MILLISECONDS)
                // okhttp默认使用的RealConnectionPool初始化线程数==2147483647，在服务端会导致大量线程TIMED_WAITING
                //ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
                .connectionPool(mConnectionPool)
                .build();
    }
}