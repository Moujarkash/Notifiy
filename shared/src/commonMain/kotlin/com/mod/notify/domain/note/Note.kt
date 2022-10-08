package com.mod.notify.domain.note

import com.mod.notify.presentation.*
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val createdAt: LocalDateTime
) {
    companion object {
        private val colors = listOf(RedOrangeHex, RedPinkHex, BabyBlueHex, LightGreenHex, VioletHex)

        fun generateRandomColor() = colors.random()
    }
}
