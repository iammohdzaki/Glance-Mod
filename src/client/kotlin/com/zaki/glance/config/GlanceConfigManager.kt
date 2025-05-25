package com.zaki.glance.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File

object GlanceConfigManager {
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    private val configFile = File("config/glance_mod.json")

    var config: GlanceHudConfig = GlanceHudConfig()

    fun load() {
        if (configFile.exists()) {
            config = gson.fromJson(configFile.readText(), GlanceHudConfig::class.java)
        } else {
            save() // Save default
        }
    }

    fun save() {
        configFile.parentFile.mkdirs()
        configFile.writeText(gson.toJson(config))
    }
}