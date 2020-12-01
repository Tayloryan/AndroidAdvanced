package com.sickcat.common.activity

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/07 10:35 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
interface BaseContext {
    fun showLoading(show: Boolean)
    fun toast(text: String?, vararg isShowImage: Boolean)
    fun toast(resId: Int, vararg isShowImage: Boolean)
}

