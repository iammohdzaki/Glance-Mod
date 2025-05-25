package com.zaki.glance.config

data class GlanceHudConfig(
    var showTime: Boolean = true,
    var showCoordinates: Boolean = true,
    var showBiome: Boolean = true,
    var showFps: Boolean = true,
    var positionX: Int = 10,
    var positionY: Int = 10
)
