package com.sickcat.biz.http

import android.content.Context
import android.widget.Toast
import com.sickcat.common.activity.BaseContext
import com.sickcat.common.http.core.CommonHttpCallback

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/07 10:33 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
abstract class MapiHttpCallback<T : MapiHttpResponse<*>> : CommonHttpCallback<T>() {

    /**
     * 请求对象
     */
    private lateinit var mRequest: MapiHttpRequest

    /**
     * 请求成功回调
     *
     * @param response 响应数据
     */
    abstract fun success(response: T)

    /**
     * 请求失败回调
     *
     * @param isThrowable 是否是异常对象；如果否，则是响应数据Model
     * @param result      响应model or 异常throwable
     */
    abstract fun failure(isThrowable: Boolean, result: Any?)
    // ------------------------通用业务逻辑------------------------------------
    /**
     * 设置当前回调对应的请求对象
     * @param mRequest 请求
     */
    fun setRequest(mRequest: MapiHttpRequest) {
        this.mRequest = mRequest
    }

    override fun onStart() {
        if (needShowLoadingDialog()) {
            showLoading(mRequest.getContext(), true)
        }
    }

    override fun onSuccess(response: T) {
        handleSuccess(mRequest, response)
    }

    override fun onFailure(throwable: Throwable) {
        handleFailure(mRequest, throwable)
    }

    /**
     * 处理请求成功通用逻辑
     *
     * @param request  MapiHttpRequest
     * @param response 响应
     */
    protected fun handleSuccess(request: MapiHttpRequest, response: T) {
        if (needShowLoadingDialog()) {
            showLoading(request.getContext(), false)
        }

        // 请求成功
        if (response.getCode() === request.getSuccessCode()) {
            success(response)
        } else {
            // 统计
//            MonitorUtil.eventFailure(request, response)
//
//            // 未登录
//            if (response.getCode() === MapiResultCode.NOT_LOGIN.value()) {
//
//            } else if (response.getCode() === MapiResultCode.SECRETKEY_EXPIRED.value()) {
//
//                ApiHelper.send(request, this)
//            } else {
//                if (needShowToast()) {
//                    val msg: String = response.getMsg()
//                    if (!TextUtils.isEmpty(msg)) {
//                        ToastUtil.showShort(msg)
//                    }
//                }
//            }

            // 通知业务方
            failure(false, response)
        }
    }

    /**
     * 处理请求失败通用逻辑
     *
     * @param request   MapiHttpRequest
     * @param throwable Throwable
     */
    private fun handleFailure(request: MapiHttpRequest, throwable: Throwable) {
        if (needShowLoadingDialog()) {
            showLoading(request.getContext(), false)
        }
        if (needShowToast()) {
            Toast.makeText(request.getContext() as Context, "您的网络状况不好，请稍后再试", Toast.LENGTH_SHORT).show()
        }
        failure(true, throwable)
    }

    /**
     * 当前请求是否需要显示加载中对话框
     *
     * @return b
     */
    fun needShowLoadingDialog(): Boolean {
        return true
    }

    /**
     * 当前请求是否需要显示Toast提示
     *
     * @return b
     */
    fun needShowToast(): Boolean {
        return true
    }

    /**
     * 显示加载中对话框
     *
     * @param context 上下文
     * @param show    显示 or 隐藏
     */
    protected fun showLoading(context: BaseContext?, show: Boolean) {
        if (context != null) {
            context.showLoading(show)
        }
    }
}
