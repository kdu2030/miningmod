package kevin.miningmod.block.custom;
import kevin.miningmod.block.entity.MinerRobotEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MinerRobot extends DispenserBlock {
    private int ticksBnMine;

    public MinerRobot(int ticksBnMine, Settings settings) {
        super(settings);
        this.ticksBnMine = ticksBnMine;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MinerRobotEntity(pos, state);
    }

    public void updateRobotEntity(MinerRobotEntity robotEntity, boolean isMining, PlayerEntity player){
        robotEntity.setMining(isMining);
        robotEntity.setPlayerId(player != null ? player.getUuid() : null);
        robotEntity.markDirty();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient()){
            return ActionResult.SUCCESS;
        }

        BlockEntity entity = world.getBlockEntity(pos);
        if(!(entity instanceof MinerRobotEntity robotEntity)){
            return ActionResult.FAIL;
        }

        if(!robotEntity.isMining()){
            world.scheduleBlockTick(pos, this, ticksBnMine);
            updateRobotEntity(robotEntity, true, player);
        }
        else {
            updateRobotEntity(robotEntity, false, player);
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockEntity entity = world.getBlockEntity(pos);
        if(!(entity instanceof MinerRobotEntity minerRobotEntity)){
            return;
        }
        PlayerEntity player = world.getPlayerByUuid(minerRobotEntity.getPlayerId());
        minerRobotEntity.setMiningUsages(minerRobotEntity.getMiningUsages() + 1);

        if(player != null){
            player.sendMessage(Text.literal(String.format("Block mine %d scheduled!", minerRobotEntity.getMiningUsages())));
        }

        if(minerRobotEntity.isMining()){
            world.scheduleBlockTick(pos, this, ticksBnMine);
        }
        minerRobotEntity.markDirty();
    }
}
