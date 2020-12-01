package com.sickcat.common.http.security

import com.sickcat.common.http.ResponseDecryptHandler
import com.sickcat.common.http.exception.InvalidContentException
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/03 5:41 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class SecurityResponseBodyConverter<T>(
    private val decryptHandler: ResponseDecryptHandler?,
    private val converter: Converter<ResponseBody, T>?
) : Converter<ResponseBody, T> {
    private val MEDIA_TYPE =
        MediaType.parse("application/json; charset=UTF-8")


    override fun convert(value: ResponseBody): T? {
        try {
            if (decryptHandler == null) {
                return converter?.convert(value)
            }
            val rawString = value.string()
            value.close()
            val decryptStr  = decryptHandler.decrypt(rawString)
            val decryptBody =
                ResponseBody.create(MEDIA_TYPE, decryptStr)
            return converter?.convert(decryptBody)
        } catch (e: Exception) {
            throw InvalidContentException("could not parse content", e)
        }
    }
}