package chap01

import java.util.logging.Logger

public inline fun <R: Any> R.logger(): Lazy<Logger> {
    return lazy { Logger.getLogger(this.javaClass.name) }
}