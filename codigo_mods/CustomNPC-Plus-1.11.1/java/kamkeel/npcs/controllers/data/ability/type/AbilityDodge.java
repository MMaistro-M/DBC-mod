/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.ability.type.AbilityDefend;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.type.IAbilityDodge;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.data.Animation;

public class AbilityDodge
extends AbilityDefend
implements IAbilityDodge {
    private int dodgeAnimation1Id = -1;
    private String dodgeAnimation1Name = "";
    private int dodgeAnimation2Id = -1;
    private String dodgeAnimation2Name = "";
    private int dodgeAnimation3Id = -1;
    private String dodgeAnimation3Name = "";

    public AbilityDodge() {
        this.typeId = "ability.cnpc.dodge";
        this.name = "Dodge";
        this.cooldownTicks = 0;
        this.windUpTicks = 10;
        this.allowedBy = UserType.BOTH;
        this.activeAnimationName = "";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/guard.png")};
    }

    @Override
    protected float performDefend(EntityLivingBase attacker, float amount) {
        return 0.0f;
    }

    @Override
    protected Animation getDefendAnimation() {
        return this.getRandomDodgeAnimation();
    }

    public Animation getRandomDodgeAnimation() {
        if (AnimationController.Instance == null) {
            return null;
        }
        ArrayList<Animation> available = new ArrayList<Animation>();
        Animation a1 = this.getDodgeAnimation(this.dodgeAnimation1Id, this.dodgeAnimation1Name);
        Animation a2 = this.getDodgeAnimation(this.dodgeAnimation2Id, this.dodgeAnimation2Name);
        Animation a3 = this.getDodgeAnimation(this.dodgeAnimation3Id, this.dodgeAnimation3Name);
        if (a1 != null) {
            available.add(a1);
        }
        if (a2 != null) {
            available.add(a2);
        }
        if (a3 != null) {
            available.add(a3);
        }
        if (available.isEmpty()) {
            return null;
        }
        return (Animation)available.get((int)(Math.random() * (double)available.size()));
    }

    private Animation getDodgeAnimation(int id, String name) {
        if (id >= 0) {
            return (Animation)AnimationController.Instance.get(id);
        }
        if (name != null && !name.isEmpty()) {
            return (Animation)AnimationController.Instance.get(name, true);
        }
        return null;
    }

    @Override
    protected void writeSubTypeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("dodgeAnimation1Id", this.dodgeAnimation1Id);
        nbt.func_74778_a("dodgeAnimation1Name", this.resolveAnimationName(this.dodgeAnimation1Id, this.dodgeAnimation1Name));
        nbt.func_74768_a("dodgeAnimation2Id", this.dodgeAnimation2Id);
        nbt.func_74778_a("dodgeAnimation2Name", this.resolveAnimationName(this.dodgeAnimation2Id, this.dodgeAnimation2Name));
        nbt.func_74768_a("dodgeAnimation3Id", this.dodgeAnimation3Id);
        nbt.func_74778_a("dodgeAnimation3Name", this.resolveAnimationName(this.dodgeAnimation3Id, this.dodgeAnimation3Name));
    }

    @Override
    protected void readSubTypeNBT(NBTTagCompound nbt) {
        this.dodgeAnimation1Id = nbt.func_74762_e("dodgeAnimation1Id");
        this.dodgeAnimation1Name = nbt.func_74779_i("dodgeAnimation1Name");
        this.dodgeAnimation2Id = nbt.func_74762_e("dodgeAnimation2Id");
        this.dodgeAnimation2Name = nbt.func_74779_i("dodgeAnimation2Name");
        this.dodgeAnimation3Id = nbt.func_74762_e("dodgeAnimation3Id");
        this.dodgeAnimation3Name = nbt.func_74779_i("dodgeAnimation3Name");
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void getTypeDefinitions(List<FieldDef> defs) {
        FieldDef.insertAfter(defs, "ability.dazedAnimation", FieldDef.section("ability.section.dodgeAnimations").tab("Effects"));
        FieldDef.insertAfter(defs, "ability.section.dodgeAnimations", FieldDef.animSubGui("ability.dodgeAnimation1", this::getDodgeAnimation1Id, this::setDodgeAnimation1Id, this::getDodgeAnimation1Name, this::setDodgeAnimation1Name).tab("Effects"));
        FieldDef.insertAfter(defs, "ability.dodgeAnimation1", FieldDef.animSubGui("ability.dodgeAnimation2", this::getDodgeAnimation2Id, this::setDodgeAnimation2Id, this::getDodgeAnimation2Name, this::setDodgeAnimation2Name).tab("Effects"));
        FieldDef.insertAfter(defs, "ability.dodgeAnimation2", FieldDef.animSubGui("ability.dodgeAnimation3", this::getDodgeAnimation3Id, this::setDodgeAnimation3Id, this::getDodgeAnimation3Name, this::setDodgeAnimation3Name).tab("Effects"));
        FieldDef.modifyVisibility(defs, "ability.windUpAnimation", () -> false);
        FieldDef.modifyVisibility(defs, "ability.windUpSound", () -> false);
    }

    @Override
    public int getDodgeAnimation1Id() {
        return this.dodgeAnimation1Id;
    }

    @Override
    public void setDodgeAnimation1Id(int animationId) {
        this.dodgeAnimation1Id = animationId;
    }

    public String getDodgeAnimation1Name() {
        return this.dodgeAnimation1Name;
    }

    public void setDodgeAnimation1Name(String name) {
        this.dodgeAnimation1Name = name != null ? name : "";
    }

    @Override
    public int getDodgeAnimation2Id() {
        return this.dodgeAnimation2Id;
    }

    @Override
    public void setDodgeAnimation2Id(int animationId) {
        this.dodgeAnimation2Id = animationId;
    }

    public String getDodgeAnimation2Name() {
        return this.dodgeAnimation2Name;
    }

    public void setDodgeAnimation2Name(String name) {
        this.dodgeAnimation2Name = name != null ? name : "";
    }

    @Override
    public int getDodgeAnimation3Id() {
        return this.dodgeAnimation3Id;
    }

    @Override
    public void setDodgeAnimation3Id(int animationId) {
        this.dodgeAnimation3Id = animationId;
    }

    public String getDodgeAnimation3Name() {
        return this.dodgeAnimation3Name;
    }

    public void setDodgeAnimation3Name(String name) {
        this.dodgeAnimation3Name = name != null ? name : "";
    }
}

