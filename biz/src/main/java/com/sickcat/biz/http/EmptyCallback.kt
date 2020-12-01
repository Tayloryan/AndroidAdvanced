package com.sickcat.biz.http

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/07 10:32 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
class EmptyCallback : MapiHttpCallback<MapiHttpResponse<*>>() {

    override fun failure(isThrowable: Boolean, result: Any?) {
        TODO("Not yet implemented")
    }

    override fun success(response: MapiHttpResponse<*>) {
        TODO("Not yet implemented")
    }

}
