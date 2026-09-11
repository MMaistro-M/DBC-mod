/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kamkeel.npcs.addon.GeckoAddon
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.goodbird.npcgecko.mixin.impl;

import com.goodbird.npcgecko.entity.EntityCustomModel;
import com.goodbird.npcgecko.mixin.IDataDisplay;
import com.goodbird.npcgecko.utils.NpcTextureUtils;
import kamkeel.npcs.addon.GeckoAddon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.entity.EntityNPCInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={GeckoAddon.class})
public class MixinGeckoAddon {
    @Shadow(remap=false)
    public boolean supportEnabled;

    @Overwrite(remap=false)
    public void geckoCopyData(EntityLivingBase copied, EntityLivingBase entity) {
        if (!this.supportEnabled) {
            return;
        }
        if (entity instanceof EntityCustomModel) {
            EntityCustomModel modelEntity = (EntityCustomModel)entity;
            if (copied instanceof EntityNPCInterface) {
                EntityNPCInterface npc = (EntityNPCInterface)copied;
                IDataDisplay display = (IDataDisplay)((Object)npc.display);
                modelEntity.textureResLoc = NpcTextureUtils.getNpcTexture((EntityNPCInterface)copied);
                modelEntity.modelResLoc = new ResourceLocation(display.getCustomModelData().getModel());
                modelEntity.animResLoc = new ResourceLocation(display.getCustomModelData().getAnimFile());
                modelEntity.idleAnimName = display.getCustomModelData().getIdleAnim();
                modelEntity.walkAnimName = display.getCustomModelData().getWalkAnim();
                modelEntity.meleeAttackAnimName = display.getCustomModelData().getMeleeAttackAnim();
                modelEntity.rangedAttackAnimName = display.getCustomModelData().getRangedAttackAnim();
                modelEntity.hurtAnimName = display.getCustomModelData().getHurtAnim();
                modelEntity.leftHeldItem = npc.inventory.getOffHand();
                modelEntity.field_70737_aN = npc.field_70737_aN;
                modelEntity.field_70725_aQ = npc.field_70725_aQ;
                modelEntity.tintData = npc.display.tintData;
            }
        }
    }
}
