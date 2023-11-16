package kevin.miningmod.events;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.*;

public class BlockBrokenEvent {
    public static final int MAX_DISTANCE = 10;
    public static final Vec3i[] BLOCK_POS_MODS = {new Vec3i(1, 0, 0), new Vec3i(-1, 0, 0), new Vec3i(0, 1, 0),
            new Vec3i(0, -1, 0), new Vec3i(0, 0, 1), new Vec3i(0, 0, -1)};

    private static float calculateDistance(BlockPos blockA, BlockPos blockB) {
        float radicand = MathHelper.square(blockB.getX() - blockA.getX()) + MathHelper.square(blockB.getY() - blockA.getY()) + MathHelper.square(blockB.getZ() - blockA.getZ());
        return MathHelper.sqrt(radicand);
    }

    private static boolean areComplementaryDirections(int directionAIndex, int directionBIndex){
        return BLOCK_POS_MODS[directionAIndex].multiply(-1).compareTo(BLOCK_POS_MODS[directionBIndex]) == 0;
    }

    public static Set<BlockData> findBlocksOfTheSameType(World world, BlockData startingBlock, BlockData currentBlock, int directionFromPreviousBlock, Set<BlockData> blocksFound) {
        BlockPos startingBlockPos = startingBlock.getBlockPos();
        BlockPos currentBlockPos = currentBlock.getBlockPos();
        if (calculateDistance(startingBlockPos, currentBlockPos) > MAX_DISTANCE) {
            return blocksFound;
        }

        for (int i = 0; i < BLOCK_POS_MODS.length; i++) {
            if (directionFromPreviousBlock >= 0 &&  areComplementaryDirections(directionFromPreviousBlock, i)) {
                continue;
            }
            BlockPos potentialBlockPos = currentBlockPos.add(BLOCK_POS_MODS[i]);
            BlockState potentialBlockState = world.getBlockState(potentialBlockPos);
            BlockData newBlockFound = new BlockData(potentialBlockPos, potentialBlockState);
            if (!blocksFound.contains(newBlockFound) && potentialBlockState.getBlock().equals(startingBlock.getBlockState().getBlock())) {
                blocksFound.add(newBlockFound);
                blocksFound = findBlocksOfTheSameType(world, startingBlock, newBlockFound, i, blocksFound);
            }
        }

        return blocksFound;
    }

    public static Set<BlockData> findBlocksOfTheSameType(World world, BlockData startingBlock, PlayerEntity player) {
        Set<BlockData> blocksFound = new HashSet<>();
        blocksFound = findBlocksOfTheSameType(world, startingBlock, startingBlock, -1, blocksFound);
        for (BlockData blockFound : blocksFound) {
            BlockPos blockFoundPos = blockFound.getBlockPos();
            String message = String.format("Block found at (%d, %d, %d)", blockFoundPos.getX(), blockFoundPos.getY(), blockFoundPos.getZ());
            player.sendMessage(Text.literal(message));
        }
        return blocksFound;
    }

    public static boolean onBlockBroken(World world, PlayerEntity player, BlockPos blockPos, BlockState blockState, BlockEntity block) {
        BlockData startingBlock = new BlockData(blockPos, blockState);
        findBlocksOfTheSameType(world, startingBlock, player);
        return true;
    }

    public static void registerBlockBrokenEventHandlers(){
        PlayerBlockBreakEvents.BEFORE.register(BlockBrokenEvent::onBlockBroken);
    }

}

