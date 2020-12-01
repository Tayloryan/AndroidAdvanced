package com.sickcat.biz.http

import com.sickcat.common.activity.BaseContext
import com.sickcat.common.http.core.CommonHttpRequest

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/07 10:35 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
abstract class MapiHttpRequest(context: BaseContext) : CommonHttpRequest(context) {
    /**
     * 获取该请求对应的BaseContext
     *
     * @return BaseContext
     */
    fun getContext(): BaseContext {
        return mTag as BaseContext
    }

    /**
     * 设置该请求对应的BaseContext
     *
     * @param context 如果为null，在请求过程中将不会显示loading。
     */
    fun setContext(context: BaseContext) {
        mTag = context
    }
}
