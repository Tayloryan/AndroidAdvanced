package com.sickcat.biz.http

import com.sickcat.common.http.core.CommonHttpResponse
import java.io.Serializable

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/07 11:17 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
open class MapiHttpResponse<Content : Serializable> : CommonHttpResponse() {

    private var code = 0
    private var busiCode: String? = null
    private var msg: String? = null
    private var uid: String? = null
    private var version: String? = null
    private var status: String? = null
    private var handler: String? = null
    private var content: Content? = null

    fun getCode(): Int {
        return code
    }

    fun setCode(code: Int) {
        this.code = code
    }

    fun getBusiCode(): String? {
        return busiCode
    }

    fun setBusiCode(busiCode: String?) {
        this.busiCode = busiCode
    }

    fun getMsg(): String? {
        return msg
    }

    fun setMsg(msg: String?) {
        this.msg = msg
    }

    fun getUid(): String? {
        return uid
    }

    fun setUid(uid: String?) {
        this.uid = uid
    }

    fun getVersion(): String? {
        return version
    }

    fun setVersion(version: String?) {
        this.version = version
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getHandler(): String? {
        return handler
    }

    fun setHandler(handler: String?) {
        this.handler = handler
    }

    fun getContent(): Content? {
        return content
    }

    fun setContent(content: Content) {
        this.content = content
    }
}

