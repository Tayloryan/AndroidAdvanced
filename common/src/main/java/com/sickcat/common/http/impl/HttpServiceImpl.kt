package com.sickcat.common.http.impl

import android.annotation.SuppressLint
import com.sickcat.common.http.HttpConfig
import com.sickcat.common.http.HttpListener
import com.sickcat.common.http.HttpRequest
import com.sickcat.common.http.HttpService
import com.sickcat.common.http.interceptor.HttpInterceptor
import com.sickcat.common.http.security.SecurityConvertFactory
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.net.Proxy
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/30 5:35 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class HttpServiceImpl constructor(private val config: HttpConfig) : HttpService {

    private var mapping: ConcurrentHashMap<Any, CompositeDisposable> = ConcurrentHashMap()
    private var pool: ConnectionPool = ConnectionPool()

    override fun addInterceptor(interceptor: HttpInterceptor) {

    }

    @SuppressLint("CheckResult")
    override fun <T> sendRequest(request: HttpRequest, listener: HttpListener<T>) {
        val retrofit = getRetrofit(request)
        request.getObservable<T>(retrofit)
            .retryWhen(
                RetryHandler(
                    request.getRetryCount(),
                    request.getRetryDelay(),
                    request.getRetryIncreaseDelay()
                )
            ).subscribeOn(Schedulers.io())
            .doOnSubscribe {
                listener.onRequestStart()
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T> {
                lateinit var disposable: Disposable
                override fun onComplete() {
                    releaseRequest(request, disposable)
                }

                override fun onSubscribe(d: Disposable) {
                    var tag: Any = request.getTag()

                    var compositeDisposable = mapping[tag]
                    if (compositeDisposable == null) {
                        compositeDisposable = CompositeDisposable()
                        mapping[tag] = compositeDisposable
                    }

                    disposable = d
                    compositeDisposable.add(d)

                }

                override fun onNext(t: T) {
                    listener.onRequestResult(t)
                }

                override fun onError(e: Throwable) {
                    listener.onRequestError(e)
                    releaseRequest(request, disposable)
                }

            })
    }

    private fun releaseRequest(request: HttpRequest, disposable: Disposable) {
        val tag = request.getTag()
        val compositeDisposable = mapping[tag]
        if (compositeDisposable != null) {
            compositeDisposable.remove(disposable)
            if (compositeDisposable.size() == 0) {
                compositeDisposable.clear()
                mapping.remove(tag)
            }
        }
    }

    override fun cancelRequest(tag: Any) {
        var compositeDisposable = mapping.remove(tag)
        if (compositeDisposable != null) {
            compositeDisposable.dispose()
            compositeDisposable.clear()
        }
    }

    override fun cancelAll() {
        mapping.keys().asSequence().forEach { cancelRequest(it) }
    }

    override fun <T> getApiObservable(orgRequest: HttpRequest): Observable<T> {
        val retrofit = getRetrofit(orgRequest)
        val observable = orgRequest.getObservable<T>(retrofit)
        return observable.retryWhen(
            RetryHandler(
                orgRequest.getRetryCount(), orgRequest.getRetryDelay(),
                orgRequest.getRetryIncreaseDelay()
            )
        ).doOnSubscribe {
            val tag: Any? = orgRequest.getTag()
            if (tag != null) {
                var compositeDisposable = CompositeDisposable();
                var value = mapping.putIfAbsent(tag, compositeDisposable)
                if (value != null) {
                    compositeDisposable = value
                }
                compositeDisposable.add(it)
            }
        }
    }

    private fun getRetrofit(request: HttpRequest): Retrofit {
        val baseURL = request.getBaseURL()
        return Retrofit.Builder()
            .client(okHttpConfig(baseURL).build())
            .addConverterFactory(SecurityConvertFactory.create(request.responseDecryptHandler()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun okHttpConfig(baseURL: String): OkHttpClient.Builder {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(config.getReadTimeout().toLong(), TimeUnit.SECONDS)
            .connectionPool(this.pool)
        if (config.isDebug()) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(loggingInterceptor)
        }

        if (!config.isSupportProxy()) {
            builder.proxy(Proxy.NO_PROXY)
        }

        builder.readTimeout(config.getReadTimeout().toLong(), TimeUnit.SECONDS)
        builder.writeTimeout(config.getWriteTimeout().toLong(), TimeUnit.SECONDS)

        return builder
    }
}