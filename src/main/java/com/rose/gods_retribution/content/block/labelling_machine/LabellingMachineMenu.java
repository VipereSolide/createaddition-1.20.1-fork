package com.rose.gods_retribution.content.block.labelling_machine;

import com.rose.gods_retribution.content.AllItems;
import com.rose.gods_retribution.content.AllMenuTypes;
import com.rose.gods_retribution.fundation.Vec2i;
import com.simibubi.create.foundation.gui.menu.MenuBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import static com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlockEntity.INPUT_SLOT_ID;
import static com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlockEntity.PAPER_SLOT_ID;
import static com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlockEntity.LABELLING_TAG_SLOT_ID;
import static com.rose.gods_retribution.content.block.labelling_machine.LabellingMachineBlockEntity.OUTPUT_SLOT_ID;

public class LabellingMachineMenu extends MenuBase<LabellingMachineBlockEntity>
{
    public static final Vec2i INPUT_SLOT_POSITION = new Vec2i(18, 37);
    public static final Vec2i PAPER_SLOT_POSITION = new Vec2i(50, 37);
    public static final Vec2i LABELLING_TAG_SLOT_POSITION = new Vec2i(82, 37);
    public static final Vec2i OUTPUT_SLOT_POSITION = new Vec2i(141, 37);

    private Slot inputSlot;
    private Slot paperSlot;
    private Slot labellingTagSlot;
    private Slot outputSlot;

    public LabellingMachineMenu(MenuType<?> type, int id, Inventory inv, FriendlyByteBuf extraData)
    {
        super(type, id, inv, extraData);
    }

    public LabellingMachineMenu(MenuType<?> type, int id, Inventory inv, LabellingMachineBlockEntity be)
    {
        super(type, id, inv, be);
    }

    public static LabellingMachineMenu create(int id,
                                              Inventory playerInventory,
                                              LabellingMachineBlockEntity labellingMachine)
    {
        return new LabellingMachineMenu(AllMenuTypes.LABELLING_MACHINE.get(), id, playerInventory, labellingMachine);
    }

    /**
     * Very important. Without it, slot based menus won't work.
     */
    @Override
    protected LabellingMachineBlockEntity createOnClient(FriendlyByteBuf extraData)
    {
        ClientLevel world = Minecraft.getInstance().level;
        if (world != null)
        {
            BlockEntity blockEntity = world.getBlockEntity(extraData.readBlockPos());
            if (blockEntity instanceof LabellingMachineBlockEntity labellingMachine)
            {
                var nbt = extraData.readNbt();

                if (nbt != null)
                    labellingMachine.readClient(nbt);

                return labellingMachine;
            }
        }

        return null;
    }

    @Override
    protected void initAndReadInventory(LabellingMachineBlockEntity contentHolder)
    {
    }

    @Override
    protected void addSlots()
    {
        inputSlot = new SlotItemHandler(contentHolder.inventory,
                INPUT_SLOT_ID,
                INPUT_SLOT_POSITION.x,
                INPUT_SLOT_POSITION.y);

        paperSlot = new SlotItemHandler(contentHolder.inventory,
                PAPER_SLOT_ID,
                PAPER_SLOT_POSITION.x,
                PAPER_SLOT_POSITION.y)
        {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack)
            {
                return stack.is(Items.PAPER);
            }
        };

        labellingTagSlot = new SlotItemHandler(contentHolder.inventory,
                LABELLING_TAG_SLOT_ID,
                LABELLING_TAG_SLOT_POSITION.x,
                LABELLING_TAG_SLOT_POSITION.y)
        {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack)
            {
                return stack.is(AllItems.LABELLING_TAG.get());
            }
        };

        outputSlot = new SlotItemHandler(contentHolder.inventory,
                OUTPUT_SLOT_ID,
                OUTPUT_SLOT_POSITION.x,
                OUTPUT_SLOT_POSITION.y)
        {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack)
            {
                return false;
            }
        };

        addSlot(inputSlot);
        addSlot(paperSlot);
        addSlot(labellingTagSlot);
        addSlot(outputSlot);

        addPlayerSlots(8, 84);
    }

    @Override
    protected void saveData(LabellingMachineBlockEntity contentHolder)
    {

    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index)
    {
        Slot clickedSlot = getSlot(index);
        if (!clickedSlot.hasItem())
            return ItemStack.EMPTY;

        ItemStack stack = clickedSlot.getItem();
        if (index < 2)
            moveItemStackTo(stack, 2, slots.size(), false);
        else
            moveItemStackTo(stack, 0, 1, false);

        return ItemStack.EMPTY;
    }
}