package com.rose.gods_retribution.content.block.waste_processor;

import com.rose.gods_retribution.content.AllItems;
import com.rose.gods_retribution.content.AllTags;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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

public class WasteProcessorBlockEntity extends BlockEntity implements MenuProvider
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
	private int maxProgress = 78;

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
	 * @param pTag
	 */
	@Override
	protected void saveAdditional(CompoundTag pTag)
	{
		pTag.put("inventory", itemHandler.serializeNBT());
		pTag.putInt("waste_processor.progress", progress);
		super.saveAdditional(pTag);
	}

	/**
	 * Used to load the data when the game is loaded.
	 * @param pTag
	 */
	@Override
	public void load(CompoundTag pTag)
	{
		super.load(pTag);
		itemHandler.deserializeNBT(pTag.getCompound("inventory"));
		progress = pTag.getInt("waste_processor.progress");
	}

	public void tick(Level lvl, BlockPos pos, BlockState blockstate)
	{
		if (hasValidInput())
		{
			progress++;
			setChanged(lvl, pos, blockstate);

			if (progress >= maxProgress)
			{
				craftOutput();
				progress = 0;
			}
		}
		else
			progress = 0;
	}

	private void craftOutput()
	{
		ItemStack result = new ItemStack(AllItems.WASTE.get(), 1);
		itemHandler.extractItem(INPUT_SLOT, 1, false);
		itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(), itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
	}

	private boolean hasValidInput()
	{
		boolean isInputProcessable = (!this.itemHandler.getStackInSlot(INPUT_SLOT).is(AllTags.Items.WASTE) && !itemHandler.getStackInSlot(INPUT_SLOT).is(ItemStack.EMPTY.getItem()));
		ItemStack result = new ItemStack(AllItems.WASTE.get());

		return isInputProcessable && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
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
}
