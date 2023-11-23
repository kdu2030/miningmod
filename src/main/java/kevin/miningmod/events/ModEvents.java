package kevin.miningmod.events;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class ModEvents {
    public static final BlockBrokenEventHandler BLOCK_BROKEN_HANDLER = new BlockBrokenEventHandler();

    public static void registerEventHandlers(){
        PlayerBlockBreakEvents.BEFORE.register((BLOCK_BROKEN_HANDLER::onBlockBroken));
    }

}
