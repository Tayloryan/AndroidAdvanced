package com.sickcat.common.http.utils

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.sickcat.common.http.core.CommonApiHelper
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/05 2:27 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
object HttpLogUtil {
    private const val TAG = "CommonApiHelper"
    fun d(message: String?) {
        if (!CommonApiHelper.getHttpConfig()!!.isDebug()) {
            return
        }
        Log.d(TAG, message)
    }

    fun i(message: String?) {
        if (!CommonApiHelper.getHttpConfig()!!.isDebug()) {
            return
        }
        Log.i(TAG, message)
    }

    fun e(message: String?) {
        if (!CommonApiHelper.getHttpConfig()!!.isDebug()) {
            return
        }
        Log.e(TAG, message)
    }

    fun log(
        url: String,
        businessParams: String,
        requestParams: Any?,
        response: String
    ) {
        if (!CommonApiHelper.getHttpConfig()!!.isDebug()) {
            return
        }
        val line = "----------------------------"
        val msg = """
            请求日志
            $line
            接口地址：$url
            $line
            业务参数:
            ${getFormatJson(businessParams)}
            $line
            请求参数:
            ${getFormatJson(Gson().toJson(requestParams))}
            $line
            响应数据:
            ${getFormatJson(response)}
            """.trimIndent()
        logMsg(msg)
    }

    fun logJson(msg: String, json: Any?) {
        if (!CommonApiHelper.getHttpConfig()!!.isDebug()) {
            return
        }
        logJson(msg, Gson().toJson(json))
    }

    fun logJson(msg: String, json: String) {
        var msg = msg
        if (!CommonApiHelper.getHttpConfig()!!.isDebug()) {
            return
        }
        msg = """
            $msg
            ${getFormatJson(json)}
            """.trimIndent()
        logMsg(msg)
    }

    private fun logMsg(msg: String) {
        var msg = msg
        val segmentSize = 3 * 1024
        val length = msg.length.toLong()
        // 长度小于等于限制直接打印
        if (length <= segmentSize) {
            Log.d(TAG, msg)
        } else {
            // 循环分段打印日志
            while (msg.length > segmentSize) {
                val logContent = msg.substring(0, segmentSize)
                msg = msg.replace(logContent, "")
                Log.d(TAG, logContent)
            }
            // 打印剩余日志
            Log.d(TAG, msg)
        }
    }

    private fun getFormatJson(json: String): String {
        if (TextUtils.isEmpty(json)) {
            return "Empty/Null json content"
        }
        try {
            val newJson = json.trim()
            if (newJson.startsWith("{")) {
                val jsonObject = JSONObject(newJson)
                return jsonObject.toString(2)
            }
            if (newJson.startsWith("[")) {
                val jsonArray = JSONArray(newJson)
                return jsonArray.toString(2)
            }
            return "Invalid Json"
        } catch (e: JSONException) {
            return "Invalid Json"
        }
    }
}