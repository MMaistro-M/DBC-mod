/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.ability.type.AbilityDefend;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import noppes.npcs.api.ability.type.IAbilityGuard;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityGuard
extends AbilityDefend
implements IAbilityGuard {
    private float damageReduction = 0.5f;

    public AbilityGuard() {
        this.typeId = "ability.cnpc.guard";
        this.name = "Guard";
        this.cooldownTicks = 0;
        this.allowedBy = UserType.BOTH;
        this.activeAnimationName = "Ability_Guard_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/guard.png")};
    }

    @Override
    protected boolean isValidDamageSource(DamageSource source) {
        return true;
    }

    @Override
    protected float performDefend(EntityLivingBase attacker, float amount) {
        return Math.max(0.0f, amount * (1.0f - this.damageReduction));
    }

    @Override
    protected void writeSubTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("damageReduction", this.damageReduction);
    }

    @Override
    protected void readSubTypeNBT(NBTTagCompound nbt) {
        this.damageReduction = nbt.func_74764_b("damageReduction") ? nbt.func_74760_g("damageReduction") : 0.5f;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void getTypeDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.floatField("ability.damageReduction", this::getDamageReduction, this::setDamageReduction));
        FieldDef.modifyVisibility(defs, "ability.windUpAnimation", () -> false);
        FieldDef.modifyVisibility(defs, "ability.windUpSound", () -> false);
    }

    @Override
    public float getDamageReduction() {
        return this.damageReduction;
    }

    @Override
    public void setDamageReduction(float damageReduction) {
        this.damageReduction = damageReduction;
    }
}

