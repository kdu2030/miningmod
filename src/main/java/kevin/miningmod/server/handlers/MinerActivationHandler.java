package kevin.miningmod.server.handlers;

import kevin.miningmod.events.ModEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class MinerActivationHandler {

    public static void activateMiner(ServerPlayerEntity player){
        ModEvents.BLOCK_BROKEN_HANDLER.activatePlayerMiner(player);
    }

    public static void deactivateMiner(ServerPlayerEntity player){
        ModEvents.BLOCK_BROKEN_HANDLER.deactivatePlayerMiner(player);
    }

}
