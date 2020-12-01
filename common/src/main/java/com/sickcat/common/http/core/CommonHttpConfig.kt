package com.sickcat.common.http.core

import android.content.Context
import com.sickcat.common.http.HttpConfig

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/05 10:21 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class CommonHttpConfig constructor(val context: Context) : HttpConfig() {
    private lateinit var baseURL: String
    private lateinit var mockURL: String

    fun getBaseURL(): String {
        return baseURL
    }

    /**
     * 设置项目的base url
     *
     * @param baseURL base url
     */
    fun setBaseURL(baseURL: String) {
        this.baseURL = baseURL
    }

    fun getMockURL(): String {
        return mockURL
    }

    /**
     * 设置mock base url
     *
     * @param mockURL mock base url
     */
    fun setMockURL(mockURL: String) {
        this.mockURL = mockURL
    }
}

