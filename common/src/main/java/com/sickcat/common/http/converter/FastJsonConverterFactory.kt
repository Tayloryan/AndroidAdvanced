package com.sickcat.common.http.converter

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.parser.Feature
import com.alibaba.fastjson.parser.ParserConfig
import com.alibaba.fastjson.serializer.SerializeConfig
import com.alibaba.fastjson.serializer.SerializerFeature
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import com.sickcat.common.http.converter.FastJsonResponseBodyConverter as FastJsonResponseBodyConverter

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/30 2:18 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class FastJsonConverterFactory private constructor() : Converter.Factory() {

    private var mParserConfig: ParserConfig = ParserConfig.getGlobalInstance()
    private var featureValues: Int = JSON.DEFAULT_PARSER_FEATURE
    private var features: Array<Feature> = arrayOf<Feature>(Feature.OrderedField)

    private var serializeConfig: SerializeConfig? = null
    private lateinit var serializerFeatures: Array<SerializerFeature>

    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation>,
        retrofit: Retrofit?
    ): Converter<ResponseBody, *>? {
        return FastJsonResponseBodyConverter<Any?>(type, mParserConfig, featureValues, *features)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit?
    ): Converter<*, RequestBody> {
        return FastJsonRequestBodyConverter<Any?>(serializeConfig, *serializerFeatures)
    }

    fun getParserConfig(): ParserConfig? {
        return mParserConfig
    }

    fun setParserConfig(config: ParserConfig): FastJsonConverterFactory? {
        mParserConfig = config
        return this
    }

    fun getParserFeatureValues(): Int {
        return featureValues
    }

    fun setParserFeatureValues(featureValues: Int): FastJsonConverterFactory? {
        this.featureValues = featureValues
        return this
    }

    fun getParserFeatures(): Array<Feature>? {
        return features
    }

    fun setParserFeatures(features: Array<Feature>): FastJsonConverterFactory? {
        this.features = features
        return this
    }

    fun getSerializeConfig(): SerializeConfig? {
        return serializeConfig
    }

    fun setSerializeConfig(serializeConfig: SerializeConfig?): FastJsonConverterFactory? {
        this.serializeConfig = serializeConfig
        return this
    }

    fun getSerializerFeatures(): Array<SerializerFeature>? {
        return serializerFeatures
    }

    fun setSerializerFeatures(features: Array<SerializerFeature>): FastJsonConverterFactory? {
        serializerFeatures = features
        return this
    }

    companion object {
        /**
         * Create an default instance for conversion. Encoding to JSON and
         * decoding from JSON (when no charset is specified by a header) will use UTF-8.
         * @return The instance of FastJsonConverterFactory
         */
        fun create(): FastJsonConverterFactory {
            return FastJsonConverterFactory()
        }
    }

}