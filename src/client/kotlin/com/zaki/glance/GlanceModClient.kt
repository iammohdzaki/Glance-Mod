package com.zaki.glance

import com.zaki.glance.config.GlanceConfigManager
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback

object GlanceModClient : ClientModInitializer {
    override fun onInitializeClient() {
        GlanceConfigManager.load()
        val hudRenderer = GlanceHudRenderer(GlanceConfigManager.config)

        HudRenderCallback.EVENT.register(HudRenderCallback { drawContext, _ ->
            hudRenderer.render(drawContext)
        })
    }
}