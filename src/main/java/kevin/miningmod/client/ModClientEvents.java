package kevin.miningmod.client;

import kevin.miningmod.client.handlers.MinerActivatedKeyHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ModClientEvents {
    public static final KeyBinding MINER_ACTIVATED_KEY = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("key.miningmod.miner_activated",
                    InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT,
                    "category.mining_mod.block_broken"));

    public static final MinerActivatedKeyHandler minerActivatedHandler = new MinerActivatedKeyHandler();

    public static void registerModClientEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(MINER_ACTIVATED_KEY.isPressed() && !minerActivatedHandler.isClientMinerActivated()){
                minerActivatedHandler.handleMinerActivateKeyPress(client);
            }
            else if(!MINER_ACTIVATED_KEY.isPressed() && minerActivatedHandler.isClientMinerActivated()) {
                minerActivatedHandler.handleMinerDeactivateKeyRelease(client);
            }
        });
    }
}
