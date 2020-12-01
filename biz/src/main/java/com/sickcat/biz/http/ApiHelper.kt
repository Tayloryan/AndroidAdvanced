package com.sickcat.biz.http

import com.sickcat.common.http.core.CommonApiHelper
import java.io.Serializable

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/07 10:31 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
 
object ApiHelper {

    /**
     * 发送请求，不需要回调
     *
     * @param request 请求model，包含请求的参数，需要继承MapiHttpRequest
     */
    fun send(request: MapiHttpRequest) {
        send(request, EmptyCallback())
    }

    /**
     * 发起请求, 不校验动态密钥，不需要回调
     *
     * @param request 请求model，包含请求的参数，需要继承MapiHttpRequest
     */
    fun sendSkipCheckKeyValid(request: MapiHttpRequest) {
        sendSkipCheckKeyValid(request, EmptyCallback())
    }

    /**
     * 发送请求
     *
     * @param request  请求model，包含请求的参数，需要继承MapiHttpRequest
     * @param callback 请求回调，需要继承MapiHttpCallback
     * @param <T>      响应数据model，需要继承MapiHttpResponse
    </T> */
    fun <T : MapiHttpResponse<out Serializable>> send(request: MapiHttpRequest, callback: MapiHttpCallback<T>) {
        callback.setRequest(request)
        CommonApiHelper.sendRequest(request, callback)
    }

    /**
     * 发起请求, 不校验动态密钥
     *
     * @param request  请求model，包含请求的参数，需要继承MapiHttpRequest
     * @param callback 请求回调，需要继承MapiHttpCallback
     * @param <T>      响应数据model，需要继承MapiHttpResponse
    </T> */
    fun <T : MapiHttpResponse<out Serializable>> sendSkipCheckKeyValid(request: MapiHttpRequest, callback: MapiHttpCallback<T>) {
        callback.setRequest(request)
        CommonApiHelper.sendRequestSkipCheckKeyValid(request, callback)
    }
}