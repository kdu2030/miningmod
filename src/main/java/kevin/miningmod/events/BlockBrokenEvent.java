package kevin.miningmod.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.joml.Vector3i;

import java.util.ArrayList;
import java.util.List;

public class BlockBrokenEvent {
    public static final int MAX_DISTANCE = 10;
//    private static float calculateDistance(BlockPos blockA, BlockPos blockB){
//        float radicand = MathHelper.square(blockB.getX() - blockA.getX()) + MathHelper.square(blockB.getY() - blockA.getY()) + MathHelper.square(blockB.getZ() - blockA.getZ());
//        return MathHelper.sqrt(radicand);
//    }
//
//    public static List<BlockEntity> findBlocksOfTheSameType(World world, BlockPos startingBlock, BlockEntityType<?> startingBlockType, BlockEntity currentBlock, List<BlockEntity> blocksFound){
//        if(calculateDistance(startingBlock, currentBlock.getPos()) > MAX_DISTANCE){
//            return blocksFound;
//        }
//        Vec3i[] blockPosMods = {new Vec3i(1, 0, 0), new Vec3i(-1, 0, 0), new Vec3i(0, 1, 0),
//                                        new Vec3i(0, -1, 0), new Vec3i(0, 0, 1), new Vec3i(0, 0, -1)};
//
//        for(Vec3i blockPosMod: blockPosMods){
//            BlockPos newBlockPos = currentBlock.getPos().add(blockPosMod);
//            BlockEntity newBlockEntity = world.getBlockEntity(newBlockPos);
//            if(newBlockEntity != null && newBlockEntity.getType().equals(startingBlockType)){
//             blocksFound.add(newBlockEntity);
//             blocksFound = findBlocksOfTheSameType(world, startingBlock, startingBlockType, newBlockEntity, blocksFound);
//            }
//        }
//
//        return blocksFound;
//    }

    // TODO Remove player entity - For debugging purposes only
//    public static List<BlockEntity> findBlocksOfTheSameType(World world, BlockPos startingBlockPos, BlockEntityType<?> startingBlockType, PlayerEntity playerEntity){
//        List<BlockEntity> blocksFound = new ArrayList<BlockEntity>();
//        blocksFound = findBlocksOfTheSameType(world, startingBlockPos, startingBlockType);
//        for(BlockEntity blockFound: blocksFound){
//            BlockPos blockFoundPos = blockFound.getPos();
//            String message = String.format("Block found at (%d, %d, %d)", blockFoundPos.getX(), blockFoundPos.getY(), blockFoundPos.getZ());
//            playerEntity.sendMessage(Text.literal(message));
//        }
//        return blocksFound;
//    }

    public static boolean onBlockBroken(World world, PlayerEntity player, BlockPos blockPos, BlockState blockState, BlockEntity block){
//        findBlocksOfTheSameType(world, block, player);
        String message = String.format("Block type: %s", blockState.getBlock().getName());
        player.sendMessage(Text.literal(message));
        return true;
    }
}
