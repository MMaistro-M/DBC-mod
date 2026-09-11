/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.goodbird.npcgecko.mixin.impl;

import com.goodbird.npcgecko.data.CustomModelData;
import com.goodbird.npcgecko.entity.EntityCustomModel;
import com.goodbird.npcgecko.mixin.IDataDisplay;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.DataDisplay;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={DataDisplay.class})
public class MixinDataDisplay
implements IDataDisplay {
    @Shadow(remap=false)
    public EntityNPCInterface npc;
    @Unique
    private final CustomModelData customNPC_Gecko_Addon$customModelData = new CustomModelData();

    @Inject(method={"writeToNBT"}, at={@At(value="HEAD")}, remap=false)
    public void writeToNBT(NBTTagCompound nbttagcompound, CallbackInfoReturnable<NBTTagCompound> cir) {
        if (this.hasCustomModel()) {
            this.customNPC_Gecko_Addon$customModelData.writeToNBT(nbttagcompound);
        }
    }

    @Inject(method={"readToNBT"}, at={@At(value="HEAD")}, remap=false)
    public void readFromNBT(NBTTagCompound nbttagcompound, CallbackInfo ci) {
        this.customNPC_Gecko_Addon$customModelData.readFromNBT(nbttagcompound);
    }

    @Override
    @Unique
    public CustomModelData getCustomModelData() {
        return this.customNPC_Gecko_Addon$customModelData;
    }

    @Override
    @Unique
    public boolean hasCustomModel() {
        return this.npc instanceof EntityCustomNpc && ((EntityCustomNpc)this.npc).modelData.getEntity(this.npc) != null && ((EntityCustomNpc)this.npc).modelData.getEntity(this.npc) instanceof EntityCustomModel;
    }
}

