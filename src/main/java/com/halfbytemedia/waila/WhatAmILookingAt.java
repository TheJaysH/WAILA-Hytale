package com.halfbytemedia.waila;

import java.util.concurrent.CompletableFuture;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.PlayerRef;

public class WhatAmILookingAt extends JavaPlugin {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public WhatAmILookingAt(JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Hello from %s version %s", this.getName(), this.getManifest().getVersion().toString());
    }

    @Override
    protected void setup() {

        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, event -> {

            var player = event.getPlayer();            
            var world = player.getWorld();
            var ref = event.getPlayerRef();
            var store = player.getReference().getStore();
            var playerRef = store.getComponent(player.getReference(), PlayerRef.getComponentType());
            var page = new WailaPage(playerRef);

            player.getHudManager().setCustomHud(playerRef, page);

            // CompletableFuture.runAsync(() -> {
            //     if (player.getHudManager().getCustomHud() == null){
                    
            //     }
            //     else{
            //         player.getHudManager().resetHud(playerRef);                    
            //     }
            // }, world);

            
        });

        LOGGER.atInfo().log("WAILA plugin setup complete - commands registered (including debug)");
    }
}
