package com.sickcat.androidadvanced.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sickcat.androidadvanced.R
import com.sickcat.androidadvanced.hilt.MyViewModel
import com.sickcat.androidadvanced.hilt.Truck
import com.sickcat.common.activity.BaseContext
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity(), BaseContext {

    @Inject
    lateinit var truck: Truck

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var retrofit: Retrofit

//    @Inject
//    lateinit var viewModel: MyViewModel

    val viewModel : MyViewModel by lazy { ViewModelProvider(this).get(MyViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        truck.deliver()
        //classloader 测试

//        ApiHelper.send(LoginHttpRequest(this), object: MapiHttpCallback<MapiHttpResponse<LoginHttpResponse>>(){
//            override fun success(response: MapiHttpResponse<LoginHttpResponse>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun failure(isThrowable: Boolean, result: Any?) {
//                TODO("Not yet implemented")
//            }
//
//        })
    }

    override fun showLoading(show: Boolean) {
        TODO("Not yet implemented")
    }

    override fun toast(text: String?, vararg isShowImage: Boolean) {
        TODO("Not yet implemented")
    }

    override fun toast(resId: Int, vararg isShowImage: Boolean) {
        TODO("Not yet implemented")
    }
}