package com.rose.gods_retribution.content.block.waste_processor;

import com.rose.gods_retribution.content.AllItems;
import com.rose.gods_retribution.content.AllTags;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WasteProcessorBlockEntity extends KineticBlockEntity implements MenuProvider
{
	private final ItemStackHandler itemHandler = new ItemStackHandler(2); // the inventory
	private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;

	/**
	 * Synchronize the client side and server side for the progress bar.
	 */
	protected final ContainerData data;
	private int progress = 0;
	private int maxProgress = 60;

	public WasteProcessorBlockEntity(BlockEntityType<WasteProcessorBlockEntity> blockEntityType, BlockPos pPos, BlockState pBlockState)
	{
		super(blockEntityType, pPos, pBlockState);

		this.data = new ContainerData()
		{
			@Override
			public int get(int index)
			{
				return switch (index)
				{
					case 0 -> WasteProcessorBlockEntity.this.progress;
					case 1 -> WasteProcessorBlockEntity.this.maxProgress;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value)
			{
				switch (index)
				{
					case 0 -> WasteProcessorBlockEntity.this.progress = value;
					case 1 -> WasteProcessorBlockEntity.this.maxProgress = value;
				}
			}

			@Override
			public int getCount()
			{
				return 2;
			}
		};
	}

	@Override
	public Component getDisplayName()
	{
		return Component.translatable("block.gods_retribution.waste_processor");
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int containedId, Inventory inventory, Player player)
	{
		return new WasteProcessorMenu(null, containedId, inventory, this, this.data);
	}

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
	{
		if (cap == ForgeCapabilities.ITEM_HANDLER)
			return lazyItemHandler.cast();
		return super.getCapability(cap, side);
	}

	@Override
	public void onLoad()
	{
		super.onLoad();
		lazyItemHandler = LazyOptional.of(() -> itemHandler);
	}

	@Override
	public void invalidateCaps()
	{
		super.invalidateCaps();
		lazyItemHandler.invalidate();
	}

	/**
	 * Helper method for that when the block is broken, it drops its inventory.
	 */
	public void drops()
	{
		SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
		for (int i = 0; i < itemHandler.getSlots(); i++)
			inventory.setItem(i, itemHandler.getStackInSlot(i));
		Containers.dropContents(level, worldPosition, inventory);
	}

	/**
	 * Used to save the data when the game is saved.
	 */
	@Override
	protected void write(CompoundTag pTag, boolean clientPacket)
	{
		pTag.put("inventory", itemHandler.serializeNBT());
		pTag.putInt("waste_processor.progress", progress);
		super.write(pTag, clientPacket);
	}

	/**
	 * Used to load the data when the game is loaded.
	 */
	@Override
	public void read(CompoundTag pTag, boolean clientPacket)
	{
		super.read(pTag, clientPacket);
		itemHandler.deserializeNBT(pTag.getCompound("inventory"));
		progress = pTag.getInt("waste_processor.progress");
	}

	@Override
	public void tick()
	{
		super.tick();
		if (hasValidInput())
		{
			progress += getProcessingSpeed();

			if (progress >= maxProgress)
			{
				craftOutput();
				progress = 0;
			}
		}
		else
			progress = 0;
	}

	@Override
	public @NotNull CompoundTag getUpdateTag()
	{
		var nbt = super.getUpdateTag();
		saveAdditional(nbt);
		return nbt;
	}

	private void craftOutput()
	{
		ItemStack result = new ItemStack(AllItems.WASTE.get(), 1);
		itemHandler.extractItem(INPUT_SLOT, 1, false);
		itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(), itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
		playSound();
	}

	private boolean hasValidInput()
	{
		boolean isInputProcessable = (!this.itemHandler.getStackInSlot(INPUT_SLOT).is(AllTags.Items.WASTE) && !itemHandler.getStackInSlot(INPUT_SLOT).is(ItemStack.EMPTY.getItem()));
		ItemStack result = new ItemStack(AllItems.WASTE.get());
		boolean isPowered = getSpeed() != 0;

		return isPowered && isInputProcessable && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
	}

	private boolean canInsertItemIntoOutputSlot(Item item)
	{
		return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()
				|| itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
	}

	private boolean canInsertAmountIntoOutputSlot(int count)
	{
		return itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
	}

	public void playSound()
	{
		AllSoundEvents.MECHANICAL_PRESS_ACTIVATION.playAt(getLevel(), worldPosition, 3, 1, true);
	}

	public int getProcessingSpeed()
	{
		return Mth.clamp((int) Math.abs(getSpeed() / 16f), 1, 512);
	}
}
