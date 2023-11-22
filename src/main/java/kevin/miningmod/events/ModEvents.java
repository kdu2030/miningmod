package kevin.miningmod.events;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class ModEvents {
    public static final BlockBrokenEventHandler BLOCK_BROKEN_HANDLER = new BlockBrokenEventHandler();

//    public static final KeyBinding MINER_ACTIVATED_KEY = KeyBindingHelper.registerKeyBinding(
//            new KeyBinding("key.miningmod.miner_activated",
//                    InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT,
//                    "category.mining_mod.block_broken"));

    public static void registerEventHandlers(){
        PlayerBlockBreakEvents.BEFORE.register((BLOCK_BROKEN_HANDLER::onBlockBroken));
//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            if(MINER_ACTIVATED_KEY.isPressed() && client.player != null){
//               BLOCK_BROKEN_HANDLER.setMinerActivated(true);
//            }
//            else if(BLOCK_BROKEN_HANDLER.isMinerActivated()){
//                BLOCK_BROKEN_HANDLER.setMinerActivated(false);
//            }
//        });
    }

}
