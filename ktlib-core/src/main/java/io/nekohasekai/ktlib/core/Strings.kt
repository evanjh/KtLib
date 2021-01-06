package io.nekohasekai.ktlib.core

const val RADIO_ENABLE = "●"
const val RADIO_DISABLE = "○"
const val SQUARE_ENABLE ="■"
const val SQUARE_DISABLE = "□"

fun Boolean.toStatusString(radio: Boolean = false): String {

    return if (radio) {

        if (this) RADIO_ENABLE else RADIO_DISABLE

    } else {

        if (this) SQUARE_ENABLE else SQUARE_DISABLE

    }

}