package com.sickcat.common.http.impl

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import java.io.IOException
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/31 9:45 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class RetryHandler constructor(
    private var retryCount: Int,
    private var retryDelay: Long,
    private var increaseDelay: Long
) : Function<Observable<Throwable>, Observable<*>> {


    override fun apply(t: Observable<Throwable>): Observable<*> {
        return t.zipWith(Observable.range(1, retryCount + 1),
            BiFunction<Throwable, Int, Wrapper> { t1:Throwable, t2:Int ->
                Wrapper(t1, t2)
            })
            .flatMap { wrapper ->
                if (canRetry(wrapper)){
                    Observable.timer(
                        retryDelay + (wrapper.index - 1) * increaseDelay,
                        TimeUnit.MILLISECONDS
                    )
                }else{
                    Observable.error(wrapper.throwable)
                }
            }
    }

    private fun canRetry(wrapper: Wrapper): Boolean {
        return (wrapper.throwable is ConnectException
                || wrapper.throwable is SocketTimeoutException
                || wrapper.throwable is TimeoutException
                || wrapper.throwable is SSLHandshakeException
                || wrapper.throwable is ProtocolException
                || wrapper.throwable is IOException)
                && wrapper.index < retryCount + 1
    }

    class Wrapper constructor(var throwable: Throwable,var index: Int) {

    }
}