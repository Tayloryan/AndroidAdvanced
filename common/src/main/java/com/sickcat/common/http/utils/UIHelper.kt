package com.sickcat.common.http.utils

import android.os.Handler
import android.os.Looper

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/05 3:33 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
object UIHelper {
    /**
     * 主线程Handler
     */
    @Volatile
    private var sHandler: Handler? = null

    /**
     * 在主线程中执行
     *
     * @param runnable runnable
     */
    fun runOnUiThread(runnable: Runnable) {
        if (runningMainThread()) {
            runnable.run()
        } else {
            getInstance()!!.post(runnable)
        }
    }

    /**
     * 在主线程中延时执行
     *
     * @param runnable    runnable
     * @param delayMillis 时间
     */
    fun runOnUiThreadDelay(runnable: Runnable?, delayMillis: Long) {
        getInstance()!!.postDelayed(runnable, delayMillis)
    }

    /**
     * 移除Runnable
     *
     * @param runnable runnable
     */
    fun removeCallbacks(runnable: Runnable?) {
        getInstance()!!.removeCallbacks(runnable)
    }

    /**
     * 判断当前线程是否主线程
     *
     * @return b
     */
    private fun runningMainThread(): Boolean {
        return Looper.getMainLooper() == Looper.myLooper()
    }

    /**
     * 获取主线程Handler实例
     *
     * @return 主线程Handler
     */
    private fun getInstance(): Handler? {
        if (sHandler == null) {
            synchronized(UIHelper::class.java) {
                if (sHandler == null) {
                    sHandler = Handler(Looper.getMainLooper())
                }
            }
        }
        return sHandler
    }
}

