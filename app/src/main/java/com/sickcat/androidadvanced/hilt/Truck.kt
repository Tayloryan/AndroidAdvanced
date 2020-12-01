package com.sickcat.androidadvanced.hilt

import javax.inject.Inject

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/11/25 6:27 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class Truck @Inject constructor(val driver: Driver) {

    @BindGasEngine
    @Inject
    lateinit var gasEngine: Engine

    @BindElectricEngine
    @Inject
    lateinit var electricEngine: Engine

    fun deliver() {
        gasEngine.start()
        electricEngine.start()
        println("Truck is delivering cargo. Driven by $driver")
        gasEngine.shutdown()
        electricEngine.shutdown()
    }
}