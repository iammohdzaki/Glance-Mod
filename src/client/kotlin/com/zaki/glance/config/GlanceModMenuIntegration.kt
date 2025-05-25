package com.zaki.glance.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import me.shedaniel.clothconfig2.api.ConfigBuilder
import me.shedaniel.clothconfig2.api.ConfigCategory
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

class GlanceModMenuIntegration : ModMenuApi {

    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { parent: Screen? ->
            val config = GlanceConfigManager.config
            val builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("Glance Mod Settings"))

            val general: ConfigCategory = builder.getOrCreateCategory(Text.literal("HUD Toggles"))

            general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Text.literal("Show Time"), config.showTime)
                .setDefaultValue(true)
                .setSaveConsumer { config.showTime = it }
                .build())

            general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Text.literal("Show Coordinates"), config.showCoordinates)
                .setDefaultValue(true)
                .setSaveConsumer { config.showCoordinates = it }
                .build())

            general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Text.literal("Show Biome"), config.showBiome)
                .setDefaultValue(false)
                .setSaveConsumer { config.showBiome = it }
                .build())

            general.addEntry(builder.entryBuilder()
                .startBooleanToggle(Text.literal("Show FPS"), config.showFps)
                .setDefaultValue(false)
                .setSaveConsumer { config.showFps = it }
                .build())

            general.addEntry(builder.entryBuilder()
                .startIntField(Text.literal("HUD X Position"), config.positionX)
                .setDefaultValue(10)
                .setMin(0)
                .setSaveConsumer { config.positionX = it }
                .build())

            general.addEntry(builder.entryBuilder()
                .startIntField(Text.literal("HUD Y Position"), config.positionY)
                .setDefaultValue(10)
                .setMin(0)
                .setSaveConsumer { config.positionY = it }
                .build())

            builder.setSavingRunnable {
                GlanceConfigManager.save()
            }

            builder.build()
        }
    }
}