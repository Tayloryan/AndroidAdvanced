package com.sickcat.common.http

import java.io.IOException

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/07/30 11:33 AM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

interface ResponseDecryptHandler {
    /**
     * 解密
     * @param rawString 加密的数据
     * @return  解密后的数据
     * @throws IOException
     */
    @Throws(IOException::class)
    fun decrypt(rawString: String): String
}