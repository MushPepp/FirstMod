package net.mushpepp.tutorialmod.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JumpyBlock extends Block {
    public JumpyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player,
                                 InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(level.isClientSide) {
            if(interactionHand == InteractionHand.MAIN_HAND) {
                //player.sendSystemMessage(Component.literal("Right clicked this!"));
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 5));
            }
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        if(entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, 200,5));
        }
        super.stepOn(level, blockPos, blockState, entity);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter blockGetter, List<Component> components, TooltipFlag tooltipFlag) {
        if(Screen.hasAltDown()) {
            components.add(Component.literal("Step on this block to get high").withStyle(ChatFormatting.DARK_GREEN));
        } else {
            components.add(Component.literal("Press ALT for more info").withStyle(ChatFormatting.BLUE));
        }
        super.appendHoverText(itemStack, blockGetter, components, tooltipFlag);
    }
}
