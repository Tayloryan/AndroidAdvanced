package com.sickcat.common.http.core

import android.text.TextUtils
import com.google.gson.Gson
import com.sickcat.common.http.HttpRequest
import java.util.*

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/05 10:47 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

abstract class BaseApiHttpRequest constructor(@Transient var mTag: Any) : HttpRequest {
    /**
     * 是否是mock测试。transient防止被Gson序列化作为请求参数。
     */
    @Transient
    protected var isMockTest = false


    override fun getTag(): Any {
        return mTag
    }

    override fun getRetryCount(): Int {
        return RETRY_COUNT
    }

    override fun getRetryDelay(): Long {
        return RETRY_DELAY.toLong()
    }

    override fun getRetryIncreaseDelay(): Long {
        return RETRY_INCREASE_DELAY.toLong()
    }

    override fun getBaseURL(): String {
        val httpConfig = CommonApiHelper.getHttpConfig()
        return if (isMockTest) httpConfig!!.getMockURL() else httpConfig!!.getBaseURL()
    }

    /**
     * 获取请求的URL（除去BaseURL的后半段）
     *
     * @return url action
     */
    abstract fun getUrlAction(): String


    /**
     * 获取编码or加密后的请求参数
     *
     * @param str 原数据
     * @return str 编码后的数据
     */
    protected abstract fun getEncodeParam(str: String?): String?


    /**
     * 获取请求参数的有序集合
     *
     * @return 参数集合
     */
    fun getUrlParam(): SortedMap<String, String> {
        var params: SortedMap<String, String> =
            TreeMap()
        params["q"] = getEncodeParam(getParamString())
        return params
    }

    /**
     * 获取请求参数json
     *
     * @return json
     */
    fun getParamString(): String {
        var result = Gson().toJson(this)
        val empty = "null"
        if (TextUtils.isEmpty(result) || empty.equals(result, ignoreCase = true)) {
            result = "{}"
        }
        return result
    }

    /**
     * 参数转url string
     *
     * @param signMap 参数集合
     * @return url string
     */
    private fun paramMapToStr(signMap: SortedMap<String, String>): String {
        val sb = StringBuilder()
        val iterator: Iterator<Map.Entry<String, String>> =
            signMap.entries.iterator()
        var item: Map.Entry<String, String>
        var isFirst = true
        while (iterator.hasNext()) {
            item = iterator.next()
            if (!TextUtils.isEmpty(item.value) && "sign" != item.key) {
                if (isFirst) {
                    isFirst = false
                } else {
                    sb.append(";")
                }
                sb.append(item.key).append("=").append(item.value)
            }
        }
        return sb.toString()
    }

    /**
     * 判断是否是json数据格式
     *
     * @param json json
     * @return b
     */
    protected fun isJson(json: String): Boolean {
        if (TextUtils.isEmpty(json)) {
            return false
        }
        return json.startsWith("{") || json.startsWith("[")
    }

    companion object {
        /**
         * 请求失败重试次数
         */
        private const val RETRY_COUNT = 3

        /**
         * 请求失败下次重试的延迟时间
         */
        private const val RETRY_DELAY = 0

        /**
         * 请求失败重试延迟递增时间
         */
        private const val RETRY_INCREASE_DELAY = 0
    }

    fun getIsMockTest(): Boolean {
        return isMockTest
    }

    fun setIsMockTest(isMockTest: Boolean) {
        this.isMockTest = isMockTest
    }
}