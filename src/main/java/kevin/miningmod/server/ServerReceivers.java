package kevin.miningmod.server;

import kevin.miningmod.client.ModNetworkingConstants;
import kevin.miningmod.server.handlers.MinerActivationHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ServerReceivers {

    public static void registerServerReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.ACTIVATE_MINER_ID, (server, player, handler, buf, responseSender) -> {
            if(player != null){
                MinerActivationHandler.activateMiner(player);
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.DEACTIVATE_MINER_ID, (server, player, handler, buf, responseSender) -> {
            if (player != null) {
                MinerActivationHandler.deactivateMiner(player);
            }
        });
    }
}
