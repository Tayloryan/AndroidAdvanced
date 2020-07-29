package com.sickcat.androidadvanced.application

import android.app.Application
import android.content.Context
import com.sickcat.common.hotfix.HotFix
import java.io.File

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/29 4:46 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class MyApplication : Application(){

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        HotFix.installPatch(this, File("/sdcard/patch.dex"))
    }
}