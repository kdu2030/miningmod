package kevin.miningmod;

import kevin.miningmod.client.ModClientEvents;
import net.fabricmc.api.ClientModInitializer;

public class MiningModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModClientEvents.registerModClientEvents();
    }
}
