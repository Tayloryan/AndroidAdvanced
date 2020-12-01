package com.sickcat.common.http

import com.alibaba.fastjson.JSONObject
import retrofit2.Retrofit
import io.reactivex.Observable as Observable

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/30 11:25 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

interface HttpRequest {

    /**
     * 获取Retrofit的Observable<br></br>
     * App需要返回一个RxJava的Observable对象，该对象对应一个具体的http请求，包括url，http 方法以及请求参数
     * 一般情况下，Observable对象由retrofit框架生成
     * @param retrofit retrofit实例
     * @return RxJava的Observable对象
     */
    fun <T> getObservable(retrofit: Retrofit): Observable<T>

    /**
     * 获取url前缀
     * @return url前缀
     */
    fun getBaseURL(): String

    /**
     * 返回失败重试次数
     * @return 失败重试次数
     */
    fun getRetryCount(): Int

    /**
     * 重试延迟时间 单位为毫秒
     * @return 重试延迟时间
     */
    fun getRetryDelay(): Long

    /**
     * 重试延迟递增时间 单位为毫秒<br></br>
     * n为 0到RetryCount返回网络失败后发起重试的时间延长时间<br></br>
     * 这里，假设retry delay = D， retry increase delay = ID<br></br>
     * 假设网络上次请求失败的时间为T，那么第n次重试的时间为<br></br>
     * T + D + (n-1)*ID
     * @return 重试延迟递增时间
     */
    fun getRetryIncreaseDelay(): Long

    /**
     * 获取请求标签
     * 返回该请求的tag，该tag可以用来取消请求。多个请求可以使用同一个tag
     * @return 请求的标签
     */
    fun getTag(): Any

    /**
     * 返回结果解密处理器
     * 返回解密处理器。 如果接口返回的数据是加密的，那么业务层需要定义解密的方法。Framework会在接受到响应的时候，对body里面的数据进行解密处理。
     * 如果返回为空，则不对请求结果进行解密处理
     * @return 结果解密处理器
     */
    fun responseDecryptHandler(): ResponseDecryptHandler
}