package kevin.miningmod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class MinerRobotEntity extends BlockEntity {
    public static final String IS_MINING_KEY = "isMining";
    public static final String PLAYER_ID_KEY = "playerId";
    public static final String MINING_USAGES_KEY = "miningUsages";

    private boolean isMining;
    private UUID playerId;
    private int miningUsages = 0;

    public MinerRobotEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MINER_ROBOT_ENTITY_TYPE, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        nbt.getUuid(PLAYER_ID_KEY);
        nbt.getBoolean(IS_MINING_KEY);
        nbt.getInt(MINING_USAGES_KEY);
        super.readNbt(nbt);
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putUuid(PLAYER_ID_KEY, playerId);
        nbt.putBoolean(IS_MINING_KEY, isMining);
        nbt.putInt(MINING_USAGES_KEY, miningUsages);
        super.writeNbt(nbt);
    }

    public boolean isMining() {
        return isMining;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public int getMiningUsages(){
        return miningUsages;
    }

    public void setMining(boolean mining) {
        isMining = mining;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public void setMiningUsages(int miningUsages) {
        this.miningUsages = miningUsages;
    }
}
