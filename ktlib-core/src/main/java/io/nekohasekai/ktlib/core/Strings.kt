package io.nekohasekai.ktlib.core

fun Boolean.toStatusString(radio: Boolean = false) : String {

    return if (radio) {

        if (this) "●" else "○"

    } else {

        if (this) "■" else "□"

    }

}