package com.sickcat.common.http.exception

import java.io.IOException

/**
 * @author 闫晓虎 <a href="xiaohu.yan@ucarinc.com">Email</a>
 * @date 2020/08/03 5:45 PM
 * @email xiaohu.yan@ucarinc.com
 * @tel  7778
 * @desc
 */

class InvalidContentException : IOException {

    constructor(){

    }
    constructor(message: String) : super(message) {
    }

    constructor(cause: Throwable) : super(cause) {
    }

    constructor(message: String, cause: Throwable) : super(message, cause) {

    }
}