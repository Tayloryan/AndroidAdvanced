package com.sickcat.common.http.core

import android.text.TextUtils
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONException
import com.alibaba.fastjson.JSONObject
import com.sickcat.common.http.ResponseDecryptHandler
import com.sickcat.common.http.utils.HttpLogUtil
import io.reactivex.Observable
import retrofit2.Retrofit
import java.util.*

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/05 11:21 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
abstract class CommonHttpRequest(tag: Any) : BaseApiHttpRequest(tag) {


    /**
     * 请求方式，默认Post。transient防止被Gson序列化作为请求参数。
     */
    @Transient
    protected var mRequestType = RequestType.POST

    override fun <T> getObservable(retrofit: Retrofit): Observable<T> {
        val api = retrofit?.create(CommonApiService::class.java)
        return if (mRequestType === RequestType.GET) api.getResponse(
            getUrlAction(),
            getUrlParam()
        ) else api.postResponse(getUrlAction(), getUrlParam())
    }

    override fun responseDecryptHandler(): ResponseDecryptHandler {
        return if (isMockTest) {
            object : ResponseDecryptHandler {
                override fun decrypt(rawString: String): String {
                    printLog(rawString)
                    return rawString
                }
            }
        } else {
            object : ResponseDecryptHandler {
                override fun decrypt(rawString: String): String {
                    return decryptResponse(rawString)
                }
            }
        }
    }

    override fun getEncodeParam(str: String?): String? {
        return str
    }

    /**
     * 获取请求成功对应的code。默认是1。
     *
     * @return code
     */
    fun getSuccessCode(): Int {
        return MapiResultCode.SUCCESS.value
    }

    /**
     * 设置请求方式，get or post，默认是使用post方式
     *
     * @param type 请求类型
     */
    fun setRequestType(type: RequestType) {
        mRequestType = type
    }

    /**
     * 解码响应数据
     *
     * @param response 原始数据
     * @return 解码后数据
     */
    private fun decryptResponse(response: String): String {
        printLog(response)
        return response
    }

    /**
     * 输出请求日志
     *
     * @param response 解密后的响应数据
     */
    protected fun printLog(response: String) {
        if (CommonApiHelper.getHttpConfig()!!.isDebug()) {
            val url = "[" + mRequestType + "] " + getBaseURL() + getUrlAction()
            HttpLogUtil.log(url, getParamString(), getUrlParam(), response)
        }
    }
}