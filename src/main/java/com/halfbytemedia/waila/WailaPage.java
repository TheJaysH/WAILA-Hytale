package com.halfbytemedia.waila;

import javax.annotation.Nonnull;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.CameraManager;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.entity.entities.player.pages.BasicCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;

public class WailaPage extends CustomUIHud {

    private PlayerRef playerRef;

    public WailaPage(PlayerRef playerRef) {
        super(playerRef);
        this.playerRef = playerRef;
    }

    @Override
    protected void build(@Nonnull UICommandBuilder builder) {
       builder.append("Pages/WailaPage.ui");
    }

    @Override
    public void update(boolean clear, @Nonnull UICommandBuilder commandBuilder){
        
        var ref = playerRef.getReference();
        
        var store = ref.getStore();
        var player = store.getComponent(ref, Player.getComponentType());
        var world = player.getWorld();
        var camera = store.getComponent(ref, CameraManager.getComponentType());
        var lastBlockPos = camera.getLastTargetBlock();
        var block = world.getBlockType(lastBlockPos);
        var blockId = block.getId();
        var item = block.getItem();

        commandBuilder.set("#Title", Message.raw(blockId));
        
        this.update(clear, commandBuilder);
    }

    
}
