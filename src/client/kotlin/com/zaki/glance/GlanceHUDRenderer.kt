package com.zaki.glance

import com.zaki.glance.config.GlanceHudConfig
import com.zaki.glance.utils.TimeUtils
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.text.Text
import kotlin.jvm.optionals.getOrNull

class GlanceHudRenderer(
    private val config: GlanceHudConfig
) {
    fun render(drawContext: DrawContext) {
        val client = MinecraftClient.getInstance()
        val player = client.player ?: return
        val world = client.world ?: return
        val textRenderer = client.textRenderer

        val x = config.positionX
        var y = config.positionY
        val lineHeight = 10  // Vertical space between lines

        if (config.showTime) {
            val time = world.timeOfDay
            val days = TimeUtils.getCurrentDay()
            val timeText = Text.literal("Day: $days | Time: ${TimeUtils.getFormattedTime(time)}")
            drawContext.drawText(textRenderer, timeText, x, y, 0xFFFFFF, true)
            y += lineHeight
        }

        if (config.showCoordinates) {
            val coordsText = Text.literal("XYZ: ${player.blockX}, ${player.blockY}, ${player.blockZ}")
            drawContext.drawText(textRenderer, coordsText, x, y, 0xFFFFFF, true)
            y += lineHeight
        }

        if (config.showBiome) {
            val biomeEntry = world.getBiome(player.blockPos)
            val biomeKey = biomeEntry.key.getOrNull()
            val biomeName = biomeKey?.value ?: "Unknown"
            val biomeText = Text.literal("Biome: $biomeName")
            drawContext.drawText(textRenderer, biomeText, x, y, 0xFFFFFF, true)
            y += lineHeight
        }

        if (config.showFps) {
            val fps = client.currentFps
            val fpsText = Text.literal("FPS: $fps")
            drawContext.drawText(textRenderer, fpsText, x, y, 0xFFFFFF, true)
        }
    }
}