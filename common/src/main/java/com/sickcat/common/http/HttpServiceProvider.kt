package com.sickcat.common.http

import com.sickcat.common.http.impl.HttpServiceImpl

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/30 11:51 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

object HttpServiceProvider {

    /**
     * 创建HttpService
     * @param config http请求的配置信息
     * @return HttpService实例
     */
    fun createHttpService(config: HttpConfig): HttpService {
        return HttpServiceImpl(config)
    }
}