package com.sickcat.common.http

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/30 11:48 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

open class HttpConfig {
    /**
     * 是否debug模式
     * 如果为true，则会打印http请求和响应的数据
     */
    private var debug = false

    /**
     * 读取数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后接收网络请求数据的超时时间设定
     */
    private var readTimeout = 0

    /**
     * 发送数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后发送网络请求数据的超时时间设定
     */
    private var writeTimeout = 0

    /**
     * 链接超时时间，单位为秒
     */
    private var timeout = 25

    private var supportProxy = true

    /**
     * 查询是否debug模式
     * 如果为true，则会打印http请求和响应的数据
     *
     * @return 是否为debug模式
     */
    fun isDebug(): Boolean {
        return debug
    }

    /**
     * 设置是否为debug模式
     * 如果为true，则会打印http请求和响应的数据
     *
     * @param debug 是否为debug模式
     */
    fun setDebug(debug: Boolean) {
        this.debug = debug
    }

    /**
     * 获得读取数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后接收网络请求数据的超时时间设定
     *
     * @return 读取数据超时时间
     */
    fun getReadTimeout(): Int {
        return readTimeout
    }

    /**
     * 设置读取数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后接收网络请求数据的超时时间设定
     *
     * @param readTimeout 数据读取超时时间
     */
    fun setReadTimeout(readTimeout: Int) {
        this.readTimeout = readTimeout
    }

    /**
     * 获得发送数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后发送网络请求数据的超时时间设定
     *
     * @return 数据发送超时时间
     */
    fun getWriteTimeout(): Int {
        return writeTimeout
    }

    /**
     * 设置发送数据超时时间
     * 单位为秒，默认为0，为不超时；连接成功后发送网络请求数据的超时时间设定
     *
     * @param writeTimeout 发送数据超时时间
     */
    fun setWriteTimeout(writeTimeout: Int) {
        this.writeTimeout = writeTimeout
    }

    /**
     * 获取链接超时时间，单位为秒
     *
     * @return 链接超时时间
     */
    fun getTimeout(): Int {
        return timeout
    }

    /**
     * 设置链接超时时间，单位为秒
     *
     * @param timeout 链接超时时间
     */
    fun setTimeout(timeout: Int) {
        this.timeout = timeout
    }

    /**
     * 查询是否支持http代理
     * @return
     */
    fun isSupportProxy(): Boolean {
        return supportProxy
    }

    /**
     * 设置是否支持http代理
     * @param supportProxy true为支持，false为不支持，该字段默认为true
     */
    fun setSupportProxy(supportProxy: Boolean) {
        this.supportProxy = supportProxy
    }
}