/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package software.bernie.example.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.SoundKeyframeEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class JackInTheBoxItem
extends Item
implements IAnimatable {
    public AnimationFactory factory = new AnimationFactory(this);
    private String controllerName = "popupController";

    private <P extends Item> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }

    public JackInTheBoxItem() {
        this.func_77637_a(GeckoLibMod.getGeckolibItemGroup());
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController<JackInTheBoxItem> controller = new AnimationController<JackInTheBoxItem>(this, this.controllerName, 20.0f, this::predicate);
        controller.registerSoundListener(this::soundListener);
        data.addAnimationController(controller);
    }

    private <ENTITY extends IAnimatable> void soundListener(SoundKeyframeEvent<ENTITY> event) {
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public ItemStack func_77659_a(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.field_72995_K) {
            return super.func_77659_a(stack, worldIn, player);
        }
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, stack, this.controllerName);
        if (controller.getAnimationState() == AnimationState.Stopped) {
            controller.markNeedsReload();
            controller.setAnimation(new AnimationBuilder().addAnimation("firework", false));
        }
        return super.func_77659_a(stack, worldIn, player);
    }
}

