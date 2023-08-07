package id.nonime.app.utils

object Helpers {
    fun onError(message: String = "Data Processing Error", t: Throwable? = null): String {
        t?.printStackTrace()
        val msg =
            if (message.isNullOrBlank() or message.isNullOrEmpty()) "Unknown error" else message
        return StringBuilder("Error: ").append("$msg some data may not displayed properly")
            .toString()
    }
}

class MyOnClickListener<T>(val clickListener: (data: T) -> Unit) {
    fun onClick(data: T) = clickListener(data)
}