package kevin.miningmod.block.entity;

import kevin.miningmod.MiningMod;
import kevin.miningmod.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static final BlockEntityType<MinerRobotEntity> MINER_ROBOT_ENTITY_TYPE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, new Identifier(MiningMod.MOD_ID, "miner_robot_entity"),
            FabricBlockEntityTypeBuilder.create(MinerRobotEntity::new, ModBlocks.MINER_ROBOT).build());

    public static void registerModBlockEntities(){
        MiningMod.LOGGER.info("Registering block entities");
    }

}