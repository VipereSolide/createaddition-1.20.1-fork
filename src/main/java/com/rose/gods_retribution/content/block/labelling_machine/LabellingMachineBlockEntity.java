package com.rose.gods_retribution.content.block.labelling_machine;

import com.rose.gods_retribution.fundation.CompoundTagHelper;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.behaviour.DirectBeltInputBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.VecHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// https://www.youtube.com/watch?v=LipuPScG2-s for inventory block entities tutorial.
public class LabellingMachineBlockEntity extends KineticBlockEntity implements MenuProvider
{
    public static final int INPUT_SLOT_ID = 0;
    public static final int PAPER_SLOT_ID = 1;
    public static final int LABELLING_TAG_SLOT_ID = 2;
    public static final int OUTPUT_SLOT_ID = 3;

    /**
     * The number of slots the block entity contains.
     */
    public static final int SLOT_COUNT = 4;

    /**
     * The name of the inventory NBT tag inside the labelling machine block entity.
     */
    public static final String INVENTORY_NBT_TAG = "inventory";

    public LabellingMachineInventory inventory;
    public LazyOptional<ItemStackHandler> optional = LazyOptional.of(() -> this.inventory);

    public int timer;

    public class LabellingMachineInventory extends ItemStackHandler
    {
        public LabellingMachineInventory()
        {
            super(SLOT_COUNT);
        }

        @Override
        protected void onContentsChanged(int slot)
        {
            super.onContentsChanged(slot);
            setChanged();
        }
    }

    public LabellingMachineBlockEntity(BlockEntityType<LabellingMachineBlockEntity> type, BlockPos pos, BlockState state)
    {
        super(type, pos, state);
        inventory = new LabellingMachineInventory();
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours)
    {
        super.addBehaviours(behaviours);
    }

    @Override
    public void tick()
    {
        super.tick();

        if (!manageCurrentCycle())
            return;

        // The following logic is only executed when the current cycle is finished (= the timer is lower than 0).
        manageProcessingCycle();
    }

    /**
     * Executes logic to tick the timer and know when a cycle is finished.
     *
     * @return Whether the cycle is finished or not.
     */
    protected boolean manageCurrentCycle()
    {
        // We don't want to execute any logic of the labelling machine if it's not powered.
        if (getSpeed() == 0)
            return false;

        // If the output slot is full we don't want to process another item. Only one item can be processed at a time.
        if (!inventory.getStackInSlot(OUTPUT_SLOT_ID).isEmpty())
            return false;

        // Ticking the timer until it reaches 0.
        if (timer > 0)
        {
            timer -= getProcessingSpeed();

            // If the latest decrement made the timer reach 0 we execute the process method.
            if (timer <= 0)
                process();

            return false;
        }

        return true;
    }

    protected void manageProcessingCycle()
    {
        // Checking if there's any new item to process. If there's none, then we don't do anything anymore.
        if (inventory.getStackInSlot(INPUT_SLOT_ID).isEmpty())
            return;

        // We don't want to start another processing cycle if the paper slot is empty.
        if (inventory.getStackInSlot(PAPER_SLOT_ID).isEmpty())
            return;

        // If there's a new item to process, we reset the timer for another cycle.
        timer = getProcessingDuration();
    }

    public void process()
    {
        if (getLevel() == null)
            return;

        var processed = inventory.getStackInSlot(INPUT_SLOT_ID);
        var labellingTag = inventory.getStackInSlot(LABELLING_TAG_SLOT_ID);
        String currentLabellingTagContent = labellingTag.getOrCreateTag().getString("label");

        var outputStack = processed.copy();
        var paper = inventory.getStackInSlot(PAPER_SLOT_ID);
        // This means that if you have more items than paper, it will only process the same amount of item as of paper.
        outputStack.setCount(Math.min(processed.getCount(), paper.getCount()));
        // Setting the new name based on the label.
        MutableComponent currentLabellingTag = Component.literal(currentLabellingTagContent);
        currentLabellingTag.setStyle(Style.EMPTY.withItalic(false));
        outputStack.setHoverName(currentLabellingTag);
        inventory.setStackInSlot(OUTPUT_SLOT_ID, outputStack);

        var paperRemainder = paper.copy();
        paperRemainder.shrink(outputStack.getCount());
        inventory.setStackInSlot(PAPER_SLOT_ID, paperRemainder);

        var itemRemainder = processed.copy();
        itemRemainder.shrink(outputStack.getCount());
        inventory.setStackInSlot(INPUT_SLOT_ID, itemRemainder);

        playSound();
    }

    public void playSound()
    {
        AllSoundEvents.MECHANICAL_PRESS_ACTIVATION.playAt(getLevel(), worldPosition, 3, 1, true);
    }

    public int getProcessingDuration()
    {
        // 3 seconds.
        return 60;
    }

    public int getProcessingSpeed()
    {
        return Mth.clamp((int) Math.abs(getSpeed() / 16f), 1, 512);
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket)
    {
        super.read(tag, clientPacket);

        CompoundTag namespace = CompoundTagHelper.getMainNamespace(tag);
        // Loads the inventory item handler from the saved nbt data.
        this.inventory.deserializeNBT(namespace.getCompound(INVENTORY_NBT_TAG));
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket)
    {
        super.write(tag, clientPacket);

        CompoundTag namespace = new CompoundTag();
        // Saves the inventory item handler as a NBT tag inside the namespace compound. Check CompoundTagHelper to know
        // what are namespace compounds.
        namespace.put(INVENTORY_NBT_TAG, this.inventory.serializeNBT());

        CompoundTagHelper.createMainNamespace(tag, namespace);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap)
    {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return this.optional.cast();

        return super.getCapability(cap);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        this.optional.invalidate();
    }

    // SYNCING

    @Override
    public CompoundTag getUpdateTag()
    {
        var nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket()
    {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    // END SYNCING

    /**
     * Gets the content of a given slot.
     *
     * @param slot The slot id you wish to query the content of.
     * @return An ItemStack which is the content of the given slot id. If the given slot id is greater than the amount of
     * slots in this inventory, an empty itemstack will be returned instead.
     */
    public ItemStack getStackInSlot(int slot)
    {
        if (slot > SLOT_COUNT - 1)
            return ItemStack.EMPTY;

        return inventory.getStackInSlot(slot);
    }

    /**
     * Sets the content of a given slot.
     *
     * @param slot  The slot id you wish to set the content of.
     * @param stack What stack you want to set the slot content of.
     */
    public void setStackInSlot(int slot, ItemStack stack)
    {
        if (slot > SLOT_COUNT - 1)
            return;

        inventory.setStackInSlot(slot, stack);
    }

    /**
     * Queries a slot in the inventory of this machine that contain the given Item type.
     *
     * @param item The item you wish to find in the inventory.
     * @return An Int representing the id of the slot that would contain this item, or -1 if no such slot was found.
     */
    public int findSlotWithItem(Item item)
    {
        for (int slot = 0; slot < SLOT_COUNT; slot++)
            if (getStackInSlot(slot).getItem() == item)
                return slot;

        return -1;
    }

    public LazyOptional<ItemStackHandler> getOptional()
    {
        return this.optional;
    }

    @Override
    public Component getDisplayName()
    {
        return LabellingMachineScreen.title;
    }

    public void sendToMenu(FriendlyByteBuf buffer)
    {
        buffer.writeBlockPos(getBlockPos());
        buffer.writeNbt(getUpdateTag());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer)
    {
        return LabellingMachineMenu.create(pContainerId, pPlayerInventory, this);
    }
}