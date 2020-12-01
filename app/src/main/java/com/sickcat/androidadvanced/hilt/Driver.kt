package com.sickcat.androidadvanced.hilt

import android.app.Application
import android.content.Context
import com.sickcat.androidadvanced.application.MyApplication
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/11/25 6:33 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
@ActivityScoped
class Driver @Inject constructor(val application: MyApplication){

}