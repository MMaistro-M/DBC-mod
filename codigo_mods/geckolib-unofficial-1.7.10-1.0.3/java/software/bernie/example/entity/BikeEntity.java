/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIControlledByPlayer
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package software.bernie.example.entity;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BikeEntity
extends EntityAnimal
implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    private final EntityAIControlledByPlayer aiControlledByPlayer;

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bike.idle", true));
        return PlayState.CONTINUE;
    }

    public BikeEntity(World worldIn) {
        super(worldIn);
        this.field_70158_ak = true;
        this.aiControlledByPlayer = new EntityAIControlledByPlayer((EntityLiving)this, 0.3f);
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.aiControlledByPlayer);
        this.func_70105_a(0.5f, 0.6f);
    }

    public boolean func_70085_c(EntityPlayer player) {
        if (this.field_70153_n == null) {
            player.func_70078_a((Entity)this);
            return super.func_70085_c(player);
        }
        return super.func_70085_c(player);
    }

    @Nullable
    public Entity getControllingPassenger() {
        return this.field_70154_o;
    }

    public boolean func_82171_bF() {
        return true;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<BikeEntity>(this, "controller", 0.0f, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Nullable
    public EntityAgeable func_90011_a(EntityAgeable ageable) {
        return null;
    }
}

