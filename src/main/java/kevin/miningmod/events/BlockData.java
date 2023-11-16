package kevin.miningmod.events;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class BlockData {
    private BlockPos blockPos;
    private BlockState blockState;

    public BlockData(BlockPos blockPos, BlockState blockState){
        this.blockPos = blockPos;
        this.blockState = blockState;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(BlockState blockState) {
        this.blockState = blockState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockData blockData = (BlockData) o;
        return blockPos.equals(blockData.blockPos) && blockState.equals(blockData.blockState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockPos, blockState);
    }
}
