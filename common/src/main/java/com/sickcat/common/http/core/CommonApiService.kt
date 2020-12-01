package com.sickcat.common.http.core

import io.reactivex.Observable
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.*

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/05 10:18 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

open interface CommonApiService<T> {
    /**
     * get请求
     *
     * @param url    url
     * @param params 参数
     * @return Observable
     */
    @GET("{url}")
    fun <T> getResponse(
        @Path(value = "url", encoded = true) url: String,
        @QueryMap params: Map<String, String>?
    ): Observable<T>

    /**
     * post请求
     *
     * @param url    url
     * @param params 参数
     * @return Observable
     */
    @POST("{url}")
    @FormUrlEncoded
    fun <T> postResponse(
        @Path(value = "url", encoded = true) url: String,
        @FieldMap params: Map<String, String>
    ): Observable<T>

//    /**
//     * post请求
//     *
//     * @param url         url
//     * @param requestBody 参数
//     * @return Observable
//     */
//    @POST("{url}")
//    @Headers("Content-Type: application/json;charset=UTF-8")
//    fun postJSONBody(
//        @Path(value = "url", encoded = true) url: String,
//        @Body requestBody: RequestBody
//    ): Observable<String>
}
