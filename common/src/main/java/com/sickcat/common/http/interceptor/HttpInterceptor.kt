package com.sickcat.common.http.interceptor

import java.io.IOException

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/30 11:40 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

interface HttpInterceptor {
    /**
     * 拦截器的拦截方法
     * @param chain http调用链
     * @return http response对象
     * @throws IOException
     */
//    @Throws(IOException::class)
//    fun intercept(chain: HttpChain?): HttpInterceptorResponse?
}