package id.iglo.common.base

open class AppResponse<T> {
    companion object {
        fun <T> success(value: T): AppResponse<T> = AppResponseSuccess(value)

        fun <T> failure(e: Throwable?): AppResponse<T> = AppResponseFailure(e)

        fun <T> loading() = AppResponseLoading<T>()

    }

    class AppResponseSuccess<T>(val data: T) : AppResponse<T>() {}

    class AppResponseFailure<T>(val e: Throwable?) : AppResponse<T>() {}

    class AppResponseLoading<T>() : AppResponse<T>()
}