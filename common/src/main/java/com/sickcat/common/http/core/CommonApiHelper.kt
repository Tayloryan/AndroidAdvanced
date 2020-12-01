package com.sickcat.common.http.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.sickcat.common.http.HttpService
import com.sickcat.common.http.HttpServiceProvider
import com.sickcat.common.http.utils.HttpLogUtil
import java.net.ConnectException

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/05 11:07 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

object CommonApiHelper {

    /**
     * 发送请求
     *
     * @param request  请求model, 需要继承CommonHttpRequest
     * @param callback 请求回调
     */
    fun sendRequest(request: CommonHttpRequest, callback: CommonHttpCallback<*>) {
        if (!isNetworkAvailable(getHttpConfig()!!.context)) {
            HttpLogUtil.e("sendRequest：network not available")
            callback.invokeFailure(ConnectException("sendRequest：network not available"))
            return
        }
        if (sHttpService == null) {
            HttpLogUtil.e("http service is null")
            return
        }
        HttpLogUtil.i("sendRequest:" + request.getBaseURL() + request.getUrlAction())
        callback.invokeStart()
        if (request.getIsMockTest()) {
            sHttpService!!.sendRequest(request, callback)
        }
    }

    /**
     * 发起请求, 不校验动态密钥
     *
     * @param request  请求model, 需要继承CommonHttpRequest
     * @param callback 请求回调
     */
    fun sendRequestSkipCheckKeyValid(request: CommonHttpRequest, callback: CommonHttpCallback<*>) {
        sendRequestSkipCheckKeyValid(request, callback, true)
    }

    /**
     * 发起请求, 不校验动态密钥
     *
     * @param request       请求model, 需要继承CommonHttpRequest
     * @param callback      请求回调
     * @param invokeOnStart 是否触发请求的onStart回调
     */
    private fun sendRequestSkipCheckKeyValid(request: CommonHttpRequest, callback: CommonHttpCallback<*>, invokeOnStart: Boolean) {
        if (!isNetworkAvailable(getHttpConfig()!!.context)) {
            HttpLogUtil.e("sendRequestSkipCheckKeyValid：network not available")
            callback.invokeFailure(ConnectException("sendRequestSkipCheckKeyValid：network not available"))
            return
        }
        if (sHttpService != null) {
            HttpLogUtil.i("sendRequest skip check key:" + request.getBaseURL() + request.getUrlAction())
            if (invokeOnStart) {
                callback.invokeStart()
            }
            sHttpService!!.sendRequest(request, callback)
        } else {
            HttpLogUtil.e("http service is null")
        }
    }

    /**
     * 取消当前tag对应的所有请求
     *
     * @param tag 请求关联的tag
     */
    fun cancelRequest(tag: Any) {
        if (sHttpService != null) {
            sHttpService!!.cancelRequest(tag)
        }
    }


    /**
     * 网络库Client
     */
    private var sHttpService: HttpService? = null

    /**
     * 网络配置
     */
    private var sHttpConfig: CommonHttpConfig? = null

    /**
     * 获取网络库实例
     *
     * @return HttpService
     */
    fun getHttpService(): HttpService? {
        if (sHttpService == null) {
            throw NullPointerException("网络框架未初始化，请先在application#onCreate中调用CommonApiHelper#init方法")
        }
        return sHttpService
    }


    /**
     * 初始化网络库
     *
     * @param config 配置
     * @return client
     */
    fun init(config: CommonHttpConfig): HttpService? {
        sHttpConfig = config
        if (sHttpService == null) {
            sHttpService = HttpServiceProvider.createHttpService(config)
        }
        return sHttpService
    }

    /**
     * 获取网络配置
     *
     * @return CommonHttpConfig
     */
    fun getHttpConfig(): CommonHttpConfig? {
        if (sHttpConfig == null) {
            throw NullPointerException("网络框架未初始化，请先在application#onCreate中调用CommonApiHelper#init方法")
        }
        return sHttpConfig
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService("connectivity") as ConnectivityManager

        if (cm == null) {
            return false
        } else {
            val infos = cm.allNetworkInfo
            val var4 = infos.size
            for (var5 in 0 until var4) {
                val info = infos[var5]
                val state = info.state
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    return true
                }
            }
            return false
        }

    }
}