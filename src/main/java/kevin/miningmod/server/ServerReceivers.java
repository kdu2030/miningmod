package kevin.miningmod.server;

import kevin.miningmod.client.ModNetworkingConstants;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.text.Text;

public class ServerReceivers {

    public static void registerServerReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.ACTIVATE_MINER_ID, (server, player, handler, buf, responseSender) -> {
            if (player != null) {
                player.sendMessage(Text.literal("Miner activated"));
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.DEACTIVATE_MINER_ID, (server, player, handler, buf, responseSender) -> {
            if (player != null) {
                player.sendMessage(Text.literal("Miner deactivated"));
            }
        });
    }
}
