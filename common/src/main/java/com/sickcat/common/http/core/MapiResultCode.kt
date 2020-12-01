package com.sickcat.common.http.core

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/05 3:04 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
enum class MapiResultCode constructor(val value: Int) {
    /**
     * 成功
     */
    SUCCESS(1),

    /**
     * API未找到
     */
    API_NOT_FIND(2),

    /**
     * 请求限制错误
     */
    LIMIT_ERROR(3),

    /**
     * 未授权
     */
    NO_AUTH(4),

    /**
     * 未登录
     */
    NOT_LOGIN(5),

    /**
     * MAPI接口错误
     */
    MAPI_ERROR(6),

    /**
     * 基础服务错误
     */
    BASE_ERROR(7),

    /**
     * 密钥错误
     */
    SECURITY_ERROR(8),

    /**
     * 参数错误
     */
    PARAM_ERROR(9),

    /**
     * 调用初始化失败
     */
    INVOKE_INIT_FAIL(10),

    /**
     * 协议错误
     */
    PROTOCOL_ERROR(12),

    /**
     * 密钥过期
     */
    SECRETKEY_EXPIRED(13),

    /**
     * 客户端需要升级版本
     */
    CLIENT_NEED_UPGRADE(16),

    /**
     * 密钥为空
     */
    SECURITY_KEY_IS_NULL(17),

    /**
     * 异步token缺失
     */
    ASYNC_TOKEN_MISSING(18),

    /**
     * 过滤中断
     */
    FILTER_INTERRUPT(19),

    /**
     * 拦截器中断
     */
    INTERCEPTOR_INTERRUPT(20),

    /**
     * API无法使用
     */
    API_UNABLE(21),

    /**
     * 内部服务错误
     */
    INNER_SERVICE_ERROR(22);
}
