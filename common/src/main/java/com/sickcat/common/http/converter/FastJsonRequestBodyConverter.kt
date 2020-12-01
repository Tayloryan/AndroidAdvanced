package com.sickcat.common.http.converter

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializeConfig
import com.alibaba.fastjson.serializer.SerializerFeature
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Converter
import java.io.IOException

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/30 2:31 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class FastJsonRequestBodyConverter<T>(
    config: SerializeConfig?,
    vararg features: SerializerFeature
) : Converter<T, RequestBody> {
    private val MEDIA_TYPE =
        MediaType.parse("application/json; charset=UTF-8")
    private var serializeConfig: SerializeConfig? = config
    private var serializerFeatures: Array<out SerializerFeature>? = features

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody? {
        val content: ByteArray = if (serializeConfig != null) {
            if (serializerFeatures != null) {
                JSON.toJSONBytes(value, serializeConfig, *serializerFeatures!!)
            } else {
                JSON.toJSONBytes(value, serializeConfig)
            }
        } else {
            if (serializerFeatures != null) {
                JSON.toJSONBytes(value, *serializerFeatures!!)
            } else {
                JSON.toJSONBytes(value)
            }
        }
        return RequestBody.create(MEDIA_TYPE, content)
    }
}