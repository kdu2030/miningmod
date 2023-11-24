package kevin.miningmod.client.handlers;

import kevin.miningmod.client.ModNetworkingConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MinerActivatedKeyHandler {
    private boolean isClientMinerActivated;

    public MinerActivatedKeyHandler() {
        isClientMinerActivated = false;
    }

    private void sendPacketToUpdateMiner(MinecraftClient client, Identifier serverEndpointId) {
        if (client.player == null) {
            return;
        }
        PacketByteBuf packetPayload = PacketByteBufs.empty();
        ClientPlayNetworking.send(serverEndpointId, packetPayload);
    }

    public void handleMinerActivateKeyPress(MinecraftClient client) {
        sendPacketToUpdateMiner(client, ModNetworkingConstants.ACTIVATE_MINER_ID);
        isClientMinerActivated = true;
    }

    public void handleMinerDeactivateKeyRelease(MinecraftClient client) {
        sendPacketToUpdateMiner(client, ModNetworkingConstants.DEACTIVATE_MINER_ID);
        isClientMinerActivated = false;
    }

    public boolean isClientMinerActivated() {
        return isClientMinerActivated;
    }
}
