package com.sickcat.androidadvanced.application

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.StrictMode
import com.sickcat.androidadvanced.BuildConfig
import com.sickcat.common.hotfix.HotFix
import dagger.hilt.android.HiltAndroidApp
import java.io.File

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/29 4:46 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
@HiltAndroidApp
class MyApplication : Application(){

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
//        HotFix.installPatch(this, File("/sdcard/patch.dex"))
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyDeath()
                .penaltyLog()
                .build())

            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectLeakedClosableObjects()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build())
        }

    }
}