package kevin.miningmod.client.handlers;

import kevin.miningmod.client.ModNetworkingConstants;
import kevin.miningmod.client.mining.ClientMinerState;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;

public class MinerActivatedKeyHandler {
    private ClientMinerState clientMinerState;

    public MinerActivatedKeyHandler(){
        clientMinerState = new ClientMinerState();
    }

    public void handleMinerActivateKeyPress(MinecraftClient client){
        if(client.player == null){
            return;
        }

        PacketByteBuf packetPayload = PacketByteBufs.create();
        packetPayload.writeInt(client.player.getId());

        if(clientMinerState.isClientMinerActivated()){
            // Send a packet to the server with the player network ID activate miner ID
            ClientPlayNetworking.send(ModNetworkingConstants.ACTIVATE_MINER_ID, packetPayload);
            clientMinerState.setClientMinerActivated(true);
        } else {
            // Send a packet to deactivate the player's miner
            ClientPlayNetworking.send(ModNetworkingConstants.DEACTIVATE_MINER_ID, packetPayload);
        }
    }


}
