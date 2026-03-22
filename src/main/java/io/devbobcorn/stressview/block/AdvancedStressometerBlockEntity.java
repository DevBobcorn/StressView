package io.devbobcorn.stressview.block;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AdvancedStressometerBlockEntity extends KineticBlockEntity {

    public float dialTarget;
    public float dialState;
    public float prevDialState;
    public int color;

    public AdvancedStressometerBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public void write(CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket) {
        compound.putFloat("Value", dialTarget);
        compound.putInt("Color", color);
        super.write(compound, registries, clientPacket);
    }

    @Override
    protected void read(CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket) {
        dialTarget = compound.getFloat("Value");
        color = compound.getInt("Color");
        super.read(compound, registries, clientPacket);
    }

    @Override
    public void tick() {
        super.tick();
        prevDialState = dialState;
        dialState += (dialTarget - dialState) * .125f;
        if (dialState > 1 && level.random.nextFloat() < 1 / 2f)
            dialState -= (dialState - 1) * level.random.nextFloat();
    }
}
