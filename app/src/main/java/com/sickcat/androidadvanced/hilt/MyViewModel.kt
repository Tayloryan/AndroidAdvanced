package com.sickcat.androidadvanced.hilt

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/11/26 4:48 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */
//@ActivityRetainedScoped
class MyViewModel @ViewModelInject constructor(val repository: Repository): ViewModel() {

}