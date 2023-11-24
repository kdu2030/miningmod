package kevin.miningmod.block;

import kevin.miningmod.MiningMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static Block registerBlock(String blockId, Block block){
        registerBlockItem(blockId, block);
        return Registry.register(Registries.BLOCK, new Identifier(MiningMod.MOD_ID, blockId), block);
    }
    private static Item registerBlockItem(String blockId, Block block){
        return Registry.register(Registries.ITEM, new Identifier(MiningMod.MOD_ID, blockId), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        MiningMod.LOGGER.info(String.format("Registering mod blocks for %s", MiningMod.MOD_ID));
    }
}
