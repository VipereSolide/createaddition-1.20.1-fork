package com.rose.gods_retribution.content.block.engraved_blocks;

import com.rose.gods_retribution.content.AllBlockEntityTypes;
import com.rose.gods_retribution.content.AllFonts;
import com.rose.gods_retribution.content.AllItems;
import com.rose.gods_retribution.content.block.keyholes.KeyholeBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SignApplicator;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;

public class EngravedLimestoneBlock extends DirectionalBlock implements IBE<EngravedLimestoneBlockEntity>
{
    // What font will the block be using? Player can cycle through fonts by shift right-clicking the block using a chisel.
    public static IntegerProperty FONT_TYPE = IntegerProperty.create(
            "font_type",
            0,
            AllFonts.values().length - 1);

    public EngravedLimestoneBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, FONT_TYPE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState()
                .setValue(FACING, context.getClickedFace())
                .setValue(FONT_TYPE, 0);
    }

    @Override
    public Class<EngravedLimestoneBlockEntity> getBlockEntityClass()
    {
        return EngravedLimestoneBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends EngravedLimestoneBlockEntity> getBlockEntityType()
    {
        return AllBlockEntityTypes.ENGRAVED_LIMESTONE.get();
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState)
    {
        return AllBlockEntityTypes.ENGRAVED_LIMESTONE.create(pPos, pState);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        Item item = itemInHand.getItem();

        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof EngravedLimestoneBlockEntity engravedLimestone)
        {
            if (pLevel.isClientSide())
                return InteractionResult.PASS;

            // Only players using chisels can modify the block.
            if (item != AllItems.ENGRAVING_CHISEL.get())
                return InteractionResult.PASS;

            boolean isFacingPlayer = engravedLimestone.isFacingFrontText(pPlayer);
            boolean isFree = !this.otherPlayerIsEditingSign(pPlayer, engravedLimestone);
            boolean hasText = this.hasEditableText(pPlayer, engravedLimestone, isFacingPlayer);
            if (hasText && isFree && pPlayer.mayBuild())
            {
                this.openTextEdit(pPlayer, engravedLimestone, isFacingPlayer);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    private boolean hasEditableText(Player pPlayer, SignBlockEntity pSignEntity, boolean pIsFrontText)
    {
        SignText signtext = pSignEntity.getText(pIsFrontText);
        return Arrays
                .stream(signtext.getMessages(pPlayer.isTextFilteringEnabled()))
                .allMatch((component) -> component.equals(CommonComponents.EMPTY) ||
                        component.getContents() instanceof LiteralContents
                );
    }

    private boolean otherPlayerIsEditingSign(Player pPlayer, SignBlockEntity pSignEntity)
    {
        UUID uuid = pSignEntity.getPlayerWhoMayEdit();
        return uuid != null && !uuid.equals(pPlayer.getUUID());
    }

    public void openTextEdit(Player pPlayer, SignBlockEntity pSignEntity, boolean pIsFrontText)
    {
        pSignEntity.setAllowedPlayerEditor(pPlayer.getUUID());
        pPlayer.openTextEdit(pSignEntity, pIsFrontText);
    }
}
