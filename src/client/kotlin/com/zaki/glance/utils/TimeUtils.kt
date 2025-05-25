package com.zaki.glance.utils

import net.minecraft.client.MinecraftClient

object TimeUtils {
    fun getCurrentTimeTicks(): Long {
        val world = MinecraftClient.getInstance().world ?: return 0
        return world.timeOfDay % 24000
    }

    fun getCurrentDay(): Long {
        val world = MinecraftClient.getInstance().world ?: return 0
        return world.timeOfDay / 24000 + 1
    }

    fun getFormattedTime(ticks: Long): String {
        val hours24 = (ticks / 1000 + 6) % 24
        val minutes = (ticks % 1000) * 60 / 1000

        val amPm = if (hours24 < 12) "AM" else "PM"
        val hours12 = when {
            hours24.toInt() == 0 -> 12
            hours24 > 12 -> hours24 - 12
            else -> hours24
        }

        return String.format("%02d:%02d %s", hours12, minutes, amPm)
    }

    fun isDay(ticks: Long): Boolean = ticks in 0..12000
}