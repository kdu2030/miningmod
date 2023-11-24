package kevin.miningmod.block;

import kevin.miningmod.MiningMod;
import kevin.miningmod.block.custom.MinerRobot;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static Block MINER_ROBOT = registerBlock("miner_robot", new MinerRobot(FabricBlockSettings.copyOf(Blocks.DISPENSER)));

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
