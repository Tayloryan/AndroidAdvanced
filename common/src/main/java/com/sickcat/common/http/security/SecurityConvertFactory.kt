package com.sickcat.common.http.security;

import com.sickcat.common.http.ResponseDecryptHandler
import com.sickcat.common.http.converter.FastJsonConverterFactory
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/03 5:31 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel 7778
 * @desc
 */

class SecurityConvertFactory constructor(private var decryptHandler: ResponseDecryptHandler) :
    Converter.Factory() {

    private val gsonFactory = FastJsonConverterFactory.create()


    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return SecurityResponseBodyConverter(
            decryptHandler,
            gsonFactory.responseBodyConverter(type, annotations, retrofit)
        )
    }


    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return gsonFactory.requestBodyConverter(
            type,
            parameterAnnotations,
            methodAnnotations,
            retrofit
        )
    }

    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? {
        return gsonFactory.stringConverter(type, annotations, retrofit)
    }

    companion object {
        fun create(decryptHandler: ResponseDecryptHandler): SecurityConvertFactory {
            return SecurityConvertFactory(decryptHandler)
        }
    }


}
