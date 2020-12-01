package com.sickcat.common.http.core

import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.TypeReference
import com.sickcat.common.http.HttpListener
import com.sickcat.common.http.utils.HttpLogUtil
import com.sickcat.common.http.utils.UIHelper

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/05 3:32 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
abstract class CommonHttpCallback<T : CommonHttpResponse> :
    TypeReference<T>(), HttpListener<String> {
    /**
     * 请求开始
     */
    abstract fun onStart()

    /**
     * 请求成功回调
     *
     * @param response 响应数据
     */
    abstract fun onSuccess(response: T)

    /**
     * 请求失败回调
     *
     * @param throwable 错误信息
     */
    abstract fun onFailure(throwable: Throwable)

    // ------------------------------------------------------------------------------
    override fun onRequestStart() {
        // 在CommonApiHelper#sendRequest的时候直接触发onStart。这边不处理。
    }

    override fun onRequestResult(result: String) {
        if (result == null) {
            HttpLogUtil.e("请求失败: result = null , 响应数据为空")
            onFailure(Exception("响应数据为空"))
        } else {
//            val response: T?
//            response = try {
//                result.toJavaObject(getType())
//            } catch (e: Exception) {
//                HttpLogUtil.e(e.toString())
//                null
//            }
//            if (response != null) {
//                onSuccess(response)
//            } else {
//                onFailure(Exception("响应数据解析失败"))
//            }
        }
    }

    override fun onRequestError(e: Throwable) {
        HttpLogUtil.e("请求失败:" + (e?.toString() ?: ""))
        onFailure(e)
    }

    /**
     * 内部方法 触发onStart回调
     */
    fun invokeStart() {
        UIHelper.runOnUiThread(Runnable { onStart() })
    }

    /**
     * 内部方法 触发onFailure回调
     *
     * @param throwable throwable
     */
    fun invokeFailure(throwable: Throwable) {
        UIHelper.runOnUiThread(Runnable { onFailure(throwable) })
    }
}

