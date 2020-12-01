package com.sickcat.androidadvanced.hilt

import android.app.Application
import com.sickcat.androidadvanced.application.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/11/26 4:35 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun provideMyApplication(application: Application): MyApplication{
        return application as MyApplication
    }
}