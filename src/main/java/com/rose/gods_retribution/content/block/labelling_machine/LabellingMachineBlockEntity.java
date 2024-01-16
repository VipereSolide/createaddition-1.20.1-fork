package com.rose.gods_retribution.content.block.labelling_machine;

import com.rose.gods_retribution.fundation.InventoryHelper;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.content.kinetics.press.PressingBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Predicate;

public class LabellingMachineBlockEntity extends KineticBlockEntity implements PressingBehaviour.PressingBehaviourSpecifics
{
    public boolean canProcess;
    public BaseContainerBlockEntity containerBlockEntity;

    public LabellingMachineBlockEntity(BlockEntityType<? extends LabellingMachineBlockEntity> typeIn, BlockPos pos, BlockState state)
    {
        super(typeIn, pos, state);
    }

    @Override
    public void tick()
    {
        super.tick();

        BlockEntity topContainerBlockEntity = level.getBlockEntity(getBlockPos().above());
        if (topContainerBlockEntity instanceof BaseContainerBlockEntity containerBlockEntity)
        {
            int paperCount = containerBlockEntity.countItem(Items.PAPER);
            int glueCount = containerBlockEntity.countItem(AllItems.SUPER_GLUE.get());
            boolean hasRequiredItems = paperCount > 0 && glueCount > 0;

            canProcess = hasRequiredItems;
            if (canProcess)
            {
                this.containerBlockEntity = containerBlockEntity;
            }
        }
    }

    /**
     * Removes one paper and one durability of the super-glue from the container above this block.
     */
    public void damageRequiredItems()
    {
        // take 1 paper from the container above.
        int paperSlotId = InventoryHelper.getSlotFromItem(containerBlockEntity, Items.PAPER);
        containerBlockEntity.removeItem(paperSlotId, 1);

        // damage the glue item once.
        int glueSlotId = InventoryHelper.getSlotFromItem(containerBlockEntity, AllItems.SUPER_GLUE.get());
        ItemStack glue = containerBlockEntity.getItem(glueSlotId);
        glue.hurtAndBreak(1, null, null);
        containerBlockEntity.setItem(glueSlotId, glue);
    }

    @Override
    public boolean tryProcessInBasin(boolean simulate)
    {
        return false;
    }

    @Override
    public boolean tryProcessOnBelt(TransportedItemStack input, List<ItemStack> outputList, boolean simulate)
    {
        if (simulate)
            return true;

        ItemStack stack = input.stack;
        stack.setHoverName(Component.literal("bite"));
        outputList = List.of(stack);

        return true;
    }

    @Override
    public boolean tryProcessInWorld(ItemEntity itemEntity, boolean simulate)
    {
        return false;
    }

    @Override
    public boolean canProcessInBulk()
    {
        return false;
    }

    @Override
    public void onPressingCompleted()
    {
        damageRequiredItems();
    }

    @Override
    public int getParticleAmount()
    {
        return 15;
    }

    @Override
    public float getKineticSpeed()
    {
        return getSpeed();
    }
}
