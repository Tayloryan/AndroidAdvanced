package com.sickcat.common.http

import com.sickcat.common.http.interceptor.HttpInterceptor
import io.reactivex.Observable

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/30 11:38 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

interface HttpService {
    /**
     * 添加请求的拦截器
     * @param interceptor 拦截器
     */
    fun addInterceptor(interceptor: HttpInterceptor)

    /**
     * 发送网络请求
     * @param request 待发送的HttpRequest
     * @param listener HttpRequest事件的监听器
     * @param <T> 服务器在http body里面返回的数据类型
    </T> */
    fun <T> sendRequest(request: HttpRequest, listener: HttpListener<T>)

    /**
     * 取消特定的HTTP请求
     * @param tag HttpRequest的tag
     */
    fun cancelRequest(tag: Any)

    /**
     * 取消全部请求
     */
    fun cancelAll()

    /**
     * 获取网络请求 observable
     * 扩展操作符的使用
     * @param orgRequest 待发送的HttpRequest
     * @return
     */
    fun <T> getApiObservable(orgRequest: HttpRequest): Observable<T>
}