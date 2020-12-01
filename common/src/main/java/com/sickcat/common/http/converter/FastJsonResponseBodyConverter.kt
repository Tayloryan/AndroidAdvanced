package com.sickcat.common.http.converter

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.parser.Feature
import com.alibaba.fastjson.parser.ParserConfig
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/30 2:42 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class FastJsonResponseBodyConverter<T> constructor(
    type: Type?,
    private var config: ParserConfig?,
    private var featureValues: Int,
    vararg features: Feature?
) : Converter<ResponseBody, T>{

    private val EMPTY_SERIALIZER_FEATURES = arrayOfNulls<Feature>(0)

    private var mType: Type? = type

    private var mFeatures: Array<out Feature?> = features

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val text = value.string()
        value.close()
        return JSON.parseObject(
            text, mType, config, featureValues, *mFeatures
        )
    }
}