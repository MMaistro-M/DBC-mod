/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package com.goodbird.npcgecko.entity;

import com.goodbird.npcgecko.constants.EnumSyncAutoAnim;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import noppes.npcs.controllers.data.TintData;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityCustomModel
extends EntityCreature
implements IAnimatable,
IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public ResourceLocation modelResLoc = new ResourceLocation("geckolib3", "geo/npc.geo.json");
    public ResourceLocation animResLoc = new ResourceLocation("custom", "geo_npc.animation.json");
    public ResourceLocation textureResLoc = new ResourceLocation("geckolib3", "textures/model/entity/geo_npc.png");
    public String idleAnimName = "";
    public String walkAnimName = "";
    public String hurtAnimName = "";
    public String meleeAttackAnimName = "";
    public String rangedAttackAnimName = "";
    public AnimationBuilder dialogAnim = null;
    public AnimationBuilder manualAnim = null;
    public AnimationBuilder attackAnim = null;
    public AnimationBuilder hurtAnim = null;
    public ItemStack leftHeldItem;
    public boolean isSemiVisible = false;
    public TintData tintData;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private <E extends IAnimatable> PlayState predicateMovement(AnimationEvent<E> event) {
        if (this.manualAnim != null) {
            if (event.getController().getAnimationState() == AnimationState.Stopped) {
                this.manualAnim = null;
            } else {
                if (event.getController().currentAnimationBuilder != this.manualAnim) {
                    event.getController().markNeedsReload();
                }
                event.getController().setAnimation(this.manualAnim);
                return PlayState.CONTINUE;
            }
        }
        if (this.dialogAnim != null) {
            if (event.getController().getAnimationState() == AnimationState.Stopped) {
                this.dialogAnim = null;
            } else {
                if (event.getController().currentAnimationBuilder != this.dialogAnim) {
                    event.getController().markNeedsReload();
                }
                event.getController().setAnimation(this.dialogAnim);
                return PlayState.CONTINUE;
            }
        }
        if (this.attackAnim != null) {
            if (event.getController().getAnimationState() == AnimationState.Stopped) {
                this.attackAnim = null;
            } else {
                if (event.getController().currentAnimationBuilder != this.attackAnim) {
                    event.getController().markNeedsReload();
                }
                event.getController().setAnimation(this.attackAnim);
                return PlayState.CONTINUE;
            }
        }
        if (this.hurtAnim != null) {
            if (event.getController().getAnimationState() == AnimationState.Stopped) {
                this.hurtAnim = null;
            } else {
                if (event.getController().currentAnimationBuilder != this.hurtAnim) {
                    event.getController().markNeedsReload();
                }
                event.getController().setAnimation(this.hurtAnim);
                return PlayState.CONTINUE;
            }
        }
        if (!event.isMoving() || this.walkAnimName.isEmpty()) {
            if (this.idleAnimName.isEmpty()) return PlayState.STOP;
            event.getController().setAnimation(new AnimationBuilder().loop(this.idleAnimName));
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().loop(this.walkAnimName));
        }
        return PlayState.CONTINUE;
    }

    public void setDialogAnim(String name) {
        this.dialogAnim = new AnimationBuilder().playOnce(name);
    }

    public void activateReceivedAnim(EnumSyncAutoAnim type) {
        switch (type) {
            case HURT: {
                if (this.hurtAnimName.isEmpty()) break;
                this.hurtAnim = new AnimationBuilder().playOnce(this.hurtAnimName);
                break;
            }
            case MELEE_ATTACK: {
                if (this.meleeAttackAnimName.isEmpty()) break;
                this.attackAnim = new AnimationBuilder().playOnce(this.meleeAttackAnimName);
                break;
            }
            case RANGED_ATTACK: {
                if (this.rangedAttackAnimName.isEmpty()) break;
                this.attackAnim = new AnimationBuilder().playOnce(this.rangedAttackAnimName);
            }
        }
    }

    public EntityCustomModel(World worldIn) {
        super(worldIn);
        this.field_70158_ak = true;
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.func_70105_a(0.7f, 2.0f);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<EntityCustomModel>(this, "movement", 10.0f, this::predicateMovement));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return this.field_70173_aa;
    }
}

