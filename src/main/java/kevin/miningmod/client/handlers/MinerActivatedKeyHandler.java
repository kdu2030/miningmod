package kevin.miningmod.client.handlers;

import kevin.miningmod.client.ModNetworkingConstants;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;

public class MinerActivatedKeyHandler {
    private boolean isClientMinerActivated;

    public MinerActivatedKeyHandler(){
        isClientMinerActivated = false;
    }

    public void handleMinerActivateKeyPress(MinecraftClient client){
        if(client.player == null){
            return;
        }

        PacketByteBuf packetPayload = PacketByteBufs.create();
        packetPayload.writeUuid(client.player.getUuid());
        ClientPlayNetworking.send(ModNetworkingConstants.ACTIVATE_MINER_ID, packetPayload);
        isClientMinerActivated = true;
    }

    public void handleMinerDeactivateKeyRelease(MinecraftClient client){
        if(client.player == null){
            return;
        }

        PacketByteBuf packetPayload = PacketByteBufs.create();
        packetPayload.writeUuid(client.player.getUuid());
        ClientPlayNetworking.send(ModNetworkingConstants.DEACTIVATE_MINER_ID, packetPayload);
        isClientMinerActivated = false;
    }

    public boolean isClientMinerActivated() {
        return isClientMinerActivated;
    }
}
