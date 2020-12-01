package com.sickcat.androidadvanced.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/11/25 6:44 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

@Module
@InstallIn(ActivityComponent::class)
abstract class EngineModule {

    @BindGasEngine
    @Binds
    abstract fun bindGasEngine(gasEngine: GasEngine): Engine

    @BindElectricEngine
    @Binds
    abstract fun bindElectricEngine(gasEngine: ElectricEngine): Engine
}