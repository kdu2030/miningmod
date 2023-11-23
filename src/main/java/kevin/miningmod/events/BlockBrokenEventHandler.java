package kevin.miningmod.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.*;

public class BlockBrokenEventHandler {
    private final Set<PlayerEntity> playersWithActivatedMiners;
    public static final int MAX_DISTANCE = 3;
    public static final Vec3i[] BLOCK_POS_MODS = {new Vec3i(1, 0, 0), new Vec3i(-1, 0, 0), new Vec3i(0, 1, 0),
            new Vec3i(0, -1, 0), new Vec3i(0, 0, 1), new Vec3i(0, 0, -1)};

    public BlockBrokenEventHandler(){
        playersWithActivatedMiners = new HashSet<>();
    }

    private static float calculateDistance(BlockPos blockA, BlockPos blockB) {
        float radicand = MathHelper.square(blockB.getX() - blockA.getX()) + MathHelper.square(blockB.getY() - blockA.getY()) + MathHelper.square(blockB.getZ() - blockA.getZ());
        return MathHelper.sqrt(radicand);
    }

    private static boolean areComplementaryDirections(int directionAIndex, int directionBIndex){
        return BLOCK_POS_MODS[directionAIndex].multiply(-1).compareTo(BLOCK_POS_MODS[directionBIndex]) == 0;
    }

    public Set<BlockData> findBlocksOfTheSameType(World world, BlockData startingBlock, BlockData currentBlock, int directionFromPreviousBlock, Set<BlockData> blocksFound) {
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

    public void decreaseDurability(PlayerEntity player, Block blockBroken, int numBlocksBroken){
        Hand hand = player.getActiveHand();
        ItemStack activeItemStack = player.getStackInHand(hand);
        int damageAmount = (int) (blockBroken.getHardness() * numBlocksBroken);
        int originalDurability  = activeItemStack.getDamage();
        if(activeItemStack.isDamageable()){
            if(originalDurability - damageAmount <= 0){
                player.handleStatus(hand == Hand.MAIN_HAND ? EntityStatuses.BREAK_MAINHAND : EntityStatuses.BREAK_OFFHAND);
            }
            activeItemStack.damage(damageAmount, player, (p) -> {});
        }
    }

    public void findBlocksOfTheSameType(World world, BlockData startingBlock, PlayerEntity player) {
        Set<BlockData> blocksFound = new HashSet<>();
        blocksFound = findBlocksOfTheSameType(world, startingBlock, startingBlock, -1, blocksFound);

        if(blocksFound.size() == 0){
            return;
        }

        BlockData[] blocksFoundArr = new BlockData[blocksFound.size()];
        blocksFoundArr = blocksFound.toArray(blocksFoundArr);
        Block blockType = blocksFoundArr[0].getBlockState().getBlock();

        for (BlockData blockFound : blocksFoundArr) {
            world.breakBlock(blockFound.getBlockPos(), !player.isCreative());
        }

        if(!player.isCreative()){
            decreaseDurability(player, blockType, blocksFoundArr.length);
        }
    }

    public boolean onBlockBroken(World world, PlayerEntity player, BlockPos blockPos, BlockState blockState, BlockEntity block) {
        // This runs on the server
        if (playersWithActivatedMiners.contains(player)) {
            world.playSound(player, blockPos, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS);
            BlockData startingBlock = new BlockData(blockPos, blockState);
            findBlocksOfTheSameType(world, startingBlock, player);
        }
        return true;
    }

    public void activatePlayerMiner(PlayerEntity player){
        playersWithActivatedMiners.add(player);
    }

    public void deactivatePlayerMiner(PlayerEntity player){
        playersWithActivatedMiners.remove(player);
    }

}

