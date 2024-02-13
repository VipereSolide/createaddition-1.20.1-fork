package com.rose.gods_retribution.content.block.waste_processor;

import com.rose.gods_retribution.content.AllBlocks;
import com.rose.gods_retribution.content.AllMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;

public class WasteProcessorMenu extends AbstractContainerMenu
{
	public final WasteProcessorBlockEntity blockEntity;

	private final Level level;
	private final ContainerData data;

	public WasteProcessorMenu(MenuType<?> type, int containerId, Inventory inventory, FriendlyByteBuf extraData)
	{
		this(type,
			 containerId,
			 inventory,
			 inventory.player.level().getBlockEntity(extraData.readBlockPos()),
			 new SimpleContainerData(2));
	}

	public WasteProcessorMenu(MenuType<?> type, int containerId, Inventory inventory, BlockEntity entity, ContainerData containerData)
	{
		// inventory is the player's inventory
		super(AllMenuTypes.WASTE_PROCESSOR.get(), containerId);
		checkContainerSize(inventory, 2);
		blockEntity = (WasteProcessorBlockEntity) entity;
		level = inventory.player.level();
		data = containerData;

		addPlayerInventory(inventory);
		addPlayerHotbar(inventory);

		blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(itemHandler -> {
			addSlot(new SlotItemHandler(itemHandler, 0, 80, 11));
			addSlot(new SlotItemHandler(itemHandler, 1, 80, 59));
		});

		addDataSlots(data);
	}

	// Credits for this method goes to diesieben07 | https://www.github.com/diesieben07/SevenCommons
	private static final int HOTBAR_SLOT_COUNT = 9;
	private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
	private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
	private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
	private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
	private static final int VANILLA_FIRST_SLOT_INDEX = 0;
	private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
	private static final int TE_INVENTORY_SLOT_COUNT = 2; // number of slots in the waste processor

	@Override
	public ItemStack quickMoveStack(Player player, int index)
	{
		Slot sourceSlot = slots.get(index);
		if (sourceSlot == null || !sourceSlot.hasItem())
			return ItemStack.EMPTY;
		ItemStack sourceStack = sourceSlot.getItem();
		ItemStack copyOfSourceStack = sourceStack.copy();

		// check if the clicked slot is one of the vanilla container slots
		if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT)
		{
			// This is a vanilla container slot so merge the stack into the tile inventory
			if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false))
				return ItemStack.EMPTY;
		}
		else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT)
		{
			// this is a TE slot so merge the stack into the player's inventory
			if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false))
				return ItemStack.EMPTY;
		}
		else
		{
			System.out.println("Invalid slotIndex: " + index);
			return ItemStack.EMPTY;
		}

		// if stackSize == 0 (the entire stack was moved), set slot contents to null
		if (sourceStack.getCount() == 0)
			sourceSlot.set(ItemStack.EMPTY);
		else
			sourceSlot.setChanged();
		sourceSlot.onTake(player, sourceStack);
		return copyOfSourceStack;
	}

	@Override
	public boolean stillValid(Player player)
	{
		return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, AllBlocks.WASTE_PROCESSOR.get());
	}

	private void addPlayerInventory(Inventory playerInventory)
	{
		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 9; ++j)
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
	}

	private void addPlayerHotbar(Inventory playerInventory)
	{
		for (int i = 0; i < 9; ++i)
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
	}

	/**
	 * Is the waste processor currently crafting something ?
	 */
	public boolean isCrafting()
	{
		return data.get(0) > 0;
	}

	public int getScaledProgress()
	{
		int progress = data.get(0);
		int maxProgress = data.get(1);
		int progressArrowSize = 26; // The height in pixels of the progress arrow

		return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
	}
}
