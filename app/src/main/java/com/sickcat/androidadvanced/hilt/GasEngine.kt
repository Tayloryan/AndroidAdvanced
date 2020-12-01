package com.sickcat.androidadvanced.hilt

import javax.inject.Inject

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/11/25 6:39 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class GasEngine @Inject constructor(): Engine {
    override fun start() {
        println("Gas engine start.")
    }

    override fun shutdown() {
        println("Gas engine shutdown.")
    }
}