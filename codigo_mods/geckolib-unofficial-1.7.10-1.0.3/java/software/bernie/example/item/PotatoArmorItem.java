/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 */
package software.bernie.example.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class PotatoArmorItem
extends GeoArmorItem
implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public PotatoArmorItem(ItemArmor.ArmorMaterial materialIn, int renderIndexIn, int slot) {
        super(materialIn, renderIndexIn, slot);
        this.func_77637_a(GeckoLibMod.getGeckolibItemGroup());
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        EntityLivingBase livingEntity = event.getExtraDataOfType(EntityLivingBase.class).get(0);
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.potato_armor.new", true));
        return this.isFullSetWorn(livingEntity) ? PlayState.CONTINUE : PlayState.STOP;
    }

    public boolean isFullSetWorn(EntityLivingBase entity) {
        for (int i = 1; i < 5; ++i) {
            if (entity.func_71124_b(i) != null && entity.func_71124_b(i).func_77973_b() instanceof PotatoArmorItem) continue;
            return false;
        }
        return true;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<PotatoArmorItem>(this, "controller", 20.0f, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}

