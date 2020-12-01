package com.sickcat.androidadvanced.hilt

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/11/26 4:56 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class MyContentProvider : ContentProvider() {

    @EntryPoint
    @InstallIn(ApplicationComponent::class)
    interface MyEntryPoint{
        fun getRetrofit(): Retrofit
    }

    override fun onCreate(): Boolean {
        TODO("Not yet implemented")
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {

//       context?.let {
//           val appContext = it.applicationContext
//           val entryPoint = EntryPointAccessors.fromApplication(appContext, MyEntryPoint::class.java)
//           val retrofit = entryPoint.getRetrofit()
//       }
        TODO("Not yet implemented")
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
}