/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package kamkeel.npcs.controllers.data.ability.conditions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.advanced.SubGuiCustomEffectSelect;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.data.CustomEffect;

public class ConditionHasEffect
extends AbilityCondition {
    private int effectId = -1;
    private int effectIndex = 0;

    public ConditionHasEffect() {
        this.typeId = "condition.cnpc.has_effect";
        this.name = "condition.has_effect";
        this.userType = UserType.PLAYER_ONLY;
    }

    @Override
    protected boolean checkEntity(EntityLivingBase entity) {
        if (!(entity instanceof EntityPlayer)) {
            return false;
        }
        if (this.effectId < 0) {
            return false;
        }
        EntityPlayer player = (EntityPlayer)entity;
        return CustomEffectController.getInstance().hasEffect(player, this.effectId, this.effectIndex);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getConditionDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.subGuiField("condition.select_effect", () -> new SubGuiCustomEffectSelect(this.effectId, this.effectIndex), gui -> {
            SubGuiCustomEffectSelect sel = gui;
            if (sel.getSelectedEffectId() >= 0) {
                this.effectId = sel.getSelectedEffectId();
                this.effectIndex = sel.getSelectedIndex();
            }
        }).buttonLabel(() -> {
            if (this.effectId < 0) {
                return "None";
            }
            CustomEffect effect = this.getEffect();
            return effect != null ? effect.getName() : "ID:" + this.effectId;
        }).clearable(() -> {
            this.effectId = -1;
            this.effectIndex = 0;
        }));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getConditionSummary() {
        String filterLabel = StatCollector.func_74838_a((String)this.getFilter().toString());
        String effectName = "None";
        if (this.effectId >= 0) {
            CustomEffect effect = this.getEffect();
            effectName = effect != null ? effect.getName() : "ID:" + this.effectId;
        }
        return "[" + filterLabel + "] Effect: " + effectName;
    }

    @Override
    public boolean isConfigured() {
        return this.effectId >= 0;
    }

    private CustomEffect getEffect() {
        HashMap<Integer, CustomEffect> map = CustomEffectController.getInstance().getEffectMap(this.effectIndex);
        return map != null ? map.get(this.effectId) : null;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("effectId", this.effectId);
        nbt.func_74768_a("effectIndex", this.effectIndex);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.effectId = nbt.func_74762_e("effectId");
        this.effectIndex = nbt.func_74762_e("effectIndex");
    }

    public int getEffectId() {
        return this.effectId;
    }

    public void setEffectId(int effectId) {
        this.effectId = effectId;
    }

    public int getEffectIndex() {
        return this.effectIndex;
    }

    public void setEffectIndex(int effectIndex) {
        this.effectIndex = effectIndex;
    }
}

