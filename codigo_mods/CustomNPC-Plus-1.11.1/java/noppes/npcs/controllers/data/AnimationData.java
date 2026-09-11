/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package noppes.npcs.controllers.data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.UpdateAnimationsPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import noppes.npcs.CustomNpcs;
import noppes.npcs.DataDisplay;
import noppes.npcs.EventHooks;
import noppes.npcs.api.entity.IAnimatable;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.handler.data.IAnimation;
import noppes.npcs.api.handler.data.IAnimationData;
import noppes.npcs.constants.EnumAnimationPart;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.Frame;
import noppes.npcs.controllers.data.FramePart;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.NpcAPI;

public class AnimationData
implements IAnimationData {
    public Object parent;
    public Animation animation;
    public boolean allowAnimation = false;
    public long animatingTime = 0L;
    public Animation currentClientAnimation;
    private boolean isClientAnimating;
    public int finishedTime = -1;
    public int finishedFrame = -1;

    public AnimationData(Object parent) {
        this.parent = parent;
    }

    public static AnimationData getData(Entity entity) {
        if (entity == null || entity.field_70170_p == null) {
            return null;
        }
        if (entity.field_70170_p.field_72995_K) {
            return CustomNpcs.proxy.getClientAnimationData(entity);
        }
        if (entity instanceof EntityPlayerMP) {
            return PlayerData.get((EntityPlayer)((EntityPlayer)entity)).animationData;
        }
        if (entity instanceof EntityNPCInterface) {
            return ((EntityNPCInterface)entity).display.animationData;
        }
        return null;
    }

    @Override
    public IAnimatable getEntity() {
        IEntity<?> entity = NpcAPI.Instance().getIEntity((Entity)this.getMCEntity());
        if (entity instanceof IAnimatable) {
            return (IAnimatable)((Object)entity);
        }
        return null;
    }

    public EntityLivingBase getMCEntity() {
        if (this.parent instanceof DataDisplay) {
            return ((DataDisplay)this.parent).npc;
        }
        if (this.parent instanceof PlayerData) {
            return ((PlayerData)this.parent).player;
        }
        return (EntityPlayer)this.parent;
    }

    @Override
    public void updateClient() {
        this.updateClient(new EntityPlayer[0]);
    }

    public void updateClient(EntityPlayer ... excludedPlayers) {
        float range;
        EntityPlayer sendingEntity = this.parent instanceof PlayerData ? ((PlayerData)this.parent).player : (this.parent instanceof DataDisplay ? ((DataDisplay)this.parent).npc : null);
        float f = range = this.parent instanceof PlayerData ? 160.0f : 60.0f;
        if (sendingEntity != null && sendingEntity.field_70170_p != null) {
            if (sendingEntity.field_71093_bK != sendingEntity.field_70170_p.field_73011_w.field_76574_g) {
                sendingEntity.field_71093_bK = sendingEntity.field_70170_p.field_73011_w.field_76574_g;
            }
            this.animatingTime = 0L;
            boolean prevIsClientAnimating = this.isClientAnimating && this.currentClientAnimation.currentFrame() != null;
            boolean bl = this.isClientAnimating = this.allowAnimation && this.animation != null;
            if (prevIsClientAnimating && (!this.isClientAnimating || this.animation != this.currentClientAnimation)) {
                EventHooks.onAnimationEnded(this.currentClientAnimation);
                this.currentClientAnimation.fireEndTask();
            }
            if (this.isClientAnimating) {
                this.currentClientAnimation = this.animation;
            }
            if (this.animation != null && this.allowAnimation) {
                this.animation.fireStartTask();
                if (EventHooks.onAnimationStarted(this.animation)) {
                    return;
                }
                EventHooks.onAnimationFrameEntered(this.animation, this.animation.currentFrame());
                this.animation.fireFrameTask(this.animation.currentFrame);
            }
            List entities = sendingEntity.field_70170_p.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(sendingEntity.field_70165_t - (double)range), (double)(sendingEntity.field_70163_u - (double)range), (double)(sendingEntity.field_70161_v - (double)range), (double)(sendingEntity.field_70165_t + (double)range), (double)(sendingEntity.field_70163_u + (double)range), (double)(sendingEntity.field_70161_v + (double)range)));
            entities.removeIf(player -> Arrays.stream(excludedPlayers).anyMatch(exp -> player == exp));
            for (EntityPlayer player2 : entities) {
                AnimationData animationData = PlayerData.get((EntityPlayer)player2).animationData;
                NBTTagCompound animationNBT = this.animation != null ? this.animation.writeToNBT() : null;
                animationData.viewAnimation(this.animation, this, animationNBT);
            }
        }
    }

    @Override
    public boolean isClientAnimating() {
        return this.isClientAnimating;
    }

    @Override
    public boolean isActive() {
        return this.isActive(this.animation);
    }

    public boolean isActive(Animation animation) {
        if (!this.allowAnimation || animation == null || animation.currentFrame == animation.frames.size() || animation.currentFrame() == null) {
            return false;
        }
        if (this.parent instanceof DataDisplay) {
            EntityNPCInterface npc = ((DataDisplay)this.parent).npc;
            if (!npc.func_70089_S()) {
                return false;
            }
            return animation.whileAttacking && npc.isAttacking() || animation.whileMoving && npc.isWalking() || animation.whileStanding && !npc.isWalking();
        }
        EntityPlayer player = this.parent instanceof PlayerData ? ((PlayerData)this.parent).player : (EntityPlayer)this.parent;
        if (!player.func_70089_S()) {
            return false;
        }
        boolean moving = Math.sqrt(player.field_70159_w * player.field_70159_w + player.field_70181_x * player.field_70181_x + player.field_70179_y * player.field_70179_y) != 0.0;
        return animation.whileAttacking && player.func_142013_aG() - player.field_70173_aa < 20 || animation.whileMoving && moving || animation.whileStanding && !moving;
    }

    public void increaseTime() {
        Frame frame;
        Animation updateAnimation = null;
        if (this.animation != null && this.isActive(this.animation.parent.currentClientAnimation)) {
            updateAnimation = this.currentClientAnimation;
        } else {
            this.isClientAnimating = false;
            if (this.isActive()) {
                updateAnimation = this.animation;
            }
        }
        if (updateAnimation != null && updateAnimation.increaseTime() && (frame = (Frame)updateAnimation.currentFrame()) != null) {
            ++this.animatingTime;
        }
    }

    public void viewAnimation(Animation animation, AnimationData animationData, NBTTagCompound animationNBT) {
        this.viewAnimation(animation, animationData, animationNBT, animationData.allowAnimation, -1, -1);
    }

    public boolean viewAnimation(Animation animation, AnimationData animationData, NBTTagCompound animationNBT, boolean enabled, int currentFrame, int time) {
        if (animation != null && (currentFrame >= animation.frames.size() || currentFrame >= 0 && animation.frames.get(currentFrame).getDuration() < time)) {
            return false;
        }
        boolean prevEnabled = animationData.allowAnimation;
        animationData.allowAnimation = enabled;
        NBTTagCompound data = animationData.viewWriteNBT(new NBTTagCompound());
        animationData.allowAnimation = prevEnabled;
        if (animation != null && currentFrame >= 0 && currentFrame < animation.frames.size()) {
            data.func_74768_a("Frame", currentFrame);
            data.func_74768_a("Time", time);
        }
        if (animationNBT != null) {
            data.func_74782_a("Animation", (NBTBase)animationNBT);
        } else if (animation != null) {
            data.func_74782_a("Animation", (NBTBase)animation.writeToNBT());
        }
        IAnimatable animatable = animationData.getEntity();
        Object entity = ((IEntity)((Object)animatable)).getMCEntity();
        if (!(entity instanceof EntityPlayer)) {
            data.func_74768_a("EntityId", entity.func_145782_y());
        }
        PacketHandler.Instance.sendToPlayer(new UpdateAnimationsPacket(data, entity.func_70005_c_()), (EntityPlayerMP)((PlayerData)this.parent).player);
        return true;
    }

    public NBTTagCompound viewWriteNBT(NBTTagCompound compound) {
        compound.func_74757_a("AllowAnimation", this.allowAnimation);
        return compound;
    }

    public void viewReadFromNBT(NBTTagCompound compound) {
        this.setEnabled(compound.func_74767_n("AllowAnimation"));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        if (this.currentClientAnimation != null) {
            compound.func_74757_a("IsClientAnimating", this.isClientAnimating);
            if (this.isClientAnimating) {
                compound.func_74782_a("CurrentAnimation", (NBTBase)this.currentClientAnimation.writeToNBT());
            }
        }
        compound.func_74757_a("AllowAnimation", this.allowAnimation);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        boolean isServer;
        EntityLivingBase entity = this.getMCEntity();
        boolean bl = isServer = entity != null && entity.field_70170_p != null && !entity.field_70170_p.field_72995_K;
        if (compound.func_74764_b("IsClientAnimating") && isServer) {
            this.isClientAnimating = compound.func_74767_n("IsClientAnimating");
            if (this.isClientAnimating) {
                this.currentClientAnimation = new Animation();
                this.currentClientAnimation.parent = this;
                this.currentClientAnimation.readFromNBT(compound.func_74775_l("CurrentAnimation"));
                this.animation = this.currentClientAnimation;
            }
        }
        this.setEnabled(compound.func_74767_n("AllowAnimation"));
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (this.allowAnimation != enabled) {
            this.allowAnimation = enabled;
            if (this.parent instanceof EntityPlayer) {
                this.finishedTime = enabled ? -1 : ((EntityPlayer)this.parent).func_70654_ax();
            }
        }
    }

    @Override
    public boolean enabled() {
        return this.allowAnimation;
    }

    @Override
    public void setAnimation(IAnimation animation) {
        Frame frame;
        Animation newAnim = null;
        if (animation != null) {
            newAnim = new Animation();
            newAnim.readFromNBT(((Animation)animation).writeToNBT());
            newAnim.currentFrame = 0;
            newAnim.currentFrameTime = 0;
            newAnim.parent = this;
            newAnim.moveFromGlobalToLocal((Animation)animation);
        }
        if (this.getMCEntity() != null && this.getMCEntity().field_70170_p != null && this.getMCEntity().field_70170_p.field_72995_K && newAnim != null) {
            this.animatingTime = 0L;
        }
        Animation prevAnim = this.animation;
        this.animation = newAnim;
        if (this.getMCEntity() != null && this.getMCEntity().field_70170_p != null && this.getMCEntity().field_70170_p.field_72995_K && this.isActive() && prevAnim != null && newAnim != null && !newAnim.frames.isEmpty() && (frame = (Frame)prevAnim.currentFrame()) != null) {
            Frame firstFrame = newAnim.frames.get(0);
            for (Map.Entry<EnumAnimationPart, FramePart> entry : frame.frameParts.entrySet()) {
                if (!firstFrame.frameParts.containsKey((Object)entry.getKey())) continue;
                FramePart prevFramePart = entry.getValue();
                FramePart newFramePart = firstFrame.frameParts.get((Object)entry.getKey());
                for (int i = 0; i < 3; ++i) {
                    newFramePart.prevPivots[i] = prevFramePart.prevPivots[i];
                    newFramePart.prevRotations[i] = prevFramePart.prevRotations[i];
                }
            }
        }
    }

    @Override
    public IAnimation getAnimation() {
        return this.animation;
    }

    @Override
    public long getAnimatingTime() {
        return this.animatingTime;
    }
}

