/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.tobiasmjc.dbcadditions.data.forms.display;

import JinRyuu.JRMCore.JRMCoreH;
import com.tobiasmjc.dbcadditions.entities.AuraEntity;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class CustomAura {
    private String sound = "jinryuudragonbc:DBC.aura";
    private int spd = 40;
    private float alp = 0.3f;
    private String texL1;
    private String texL2;
    private String texL3;
    private int colL1 = -1;
    private int colL2 = -1;
    private int colL3 = -1;
    private int legCol = -1;
    private int divineCol = -1;
    private boolean hasLightning;
    private boolean bol4;
    private boolean bol4a;
    private float lightningR = -1.0f;
    private float lightningG = -1.0f;
    private float lightningB = -1.0f;
    private float lightningA = -1.0f;
    private int bol6 = -1;
    private float st;

    protected AuraEntity createAura(EntityPlayer p) {
        String eff = JRMCoreH.StusEfctsClient(p);
        boolean w = JRMCoreH.StusEfcts(7, eff) || JRMCoreH.StusEfcts(9, eff) && JRMCoreH.data(p.getDisplayName(), 3, "0").contains("1") && !JRMCoreH.StusEfctsMe(4);
        AuraEntity aura2 = new AuraEntity(p.field_70170_p, p.getDisplayName(), 0, 0.0f, 0.0f, 0, w);
        aura2.setBol(JRMCoreH.StusEfcts(1, eff));
        aura2.setBol2(JRMCoreH.StusEfcts(4, eff));
        aura2.setBol3(JRMCoreH.StusEfcts(3, eff));
        return aura2;
    }

    protected AuraEntity createAura(EntityPlayer p, float state) {
        String eff = JRMCoreH.StusEfctsClient(p);
        boolean w = JRMCoreH.StusEfcts(7, eff) || JRMCoreH.StusEfcts(9, eff) && JRMCoreH.data(p.getDisplayName(), 3, "0").contains("1") && !JRMCoreH.StusEfctsMe(4);
        AuraEntity aura2 = new AuraEntity(p.field_70170_p, p.getDisplayName(), 0, state, 0.0f, 0, w);
        aura2.setBol(JRMCoreH.StusEfcts(1, eff));
        aura2.setBol2(JRMCoreH.StusEfcts(4, eff));
        aura2.setBol3(JRMCoreH.StusEfcts(3, eff));
        if (this.bol6 != -1) {
            aura2.setBol6(this.bol6);
        }
        return aura2;
    }

    public void spawnKaioken(EntityPlayer p, float state2) {
        String eff = JRMCoreH.StusEfctsClient(p);
        boolean w = JRMCoreH.StusEfcts(7, eff) || JRMCoreH.StusEfcts(9, eff) && JRMCoreH.data(p.getDisplayName(), 3, "0").contains("1") && !JRMCoreH.StusEfctsMe(4);
        AuraEntity entityAura22 = new AuraEntity(p.field_70170_p, p.func_70005_c_(), 0xFE0000, 2.0f + this.st, state2 * 1.5f, 0, w);
        entityAura22.setSpd(40);
        entityAura22.setAlp(0.3f);
        entityAura22.setTex("aurak");
        entityAura22.setInner(false);
        entityAura22.setRendPass(0);
        p.field_70170_p.func_72838_d((Entity)entityAura22);
    }

    public void spawn(EntityPlayer p) {
        AuraEntity aura = this.createAura(p, this.st);
        if (this.hasLightning) {
            aura.hasLightning = true;
            if (this.lightningR != -1.0f) {
                aura.lightningColorR = this.lightningR;
            }
            if (this.lightningG != -1.0f) {
                aura.lightningColorG = this.lightningG;
            }
            if (this.lightningB != -1.0f) {
                aura.lightningColorB = this.lightningB;
            }
            if (this.lightningA != -1.0f) {
                aura.lightningColorA = this.lightningA;
            }
        }
        aura.setSpd(this.spd);
        aura.setAlp(this.alp);
        if (this.texL1 != null && !this.texL1.isEmpty()) {
            aura.setTex(this.texL1);
        }
        if (this.texL2 != null && !this.texL2.isEmpty()) {
            aura.setTexL2(this.texL2);
        }
        if (this.texL3 != null && !this.texL3.isEmpty()) {
            aura.setTexL3(this.texL3);
        }
        int c1 = this.colL1;
        int c2 = this.colL2;
        int c3 = this.colL3;
        if (this.legCol != -1 && DataUtils.legendary(p)) {
            c1 = DataUtils.mergeColors(c1, this.legCol, 0.5f);
            c2 = DataUtils.mergeColors(c1, this.legCol, 0.5f);
            c3 = DataUtils.mergeColors(c1, this.legCol, 0.5f);
        } else if (this.divineCol != -1 && DataUtils.divine(p)) {
            c1 = DataUtils.mergeColors(c1, this.divineCol, 0.3f);
            c2 = DataUtils.mergeColors(c1, this.divineCol, 0.3f);
            c3 = DataUtils.mergeColors(c1, this.divineCol, 0.3f);
        }
        if (c1 != -1) {
            aura.setCol(c1);
        }
        if (c2 != -1) {
            aura.setColL2(c2);
        }
        if (c3 != -1) {
            aura.setColL3(c3);
        }
        aura.setBol4(this.bol4);
        aura.setBol4a(this.bol4a);
        p.field_70170_p.func_72838_d((Entity)aura);
    }

    public CustomAura setSound(String sound) {
        this.sound = sound;
        return this;
    }

    public CustomAura setBol6(int bol6) {
        this.bol6 = bol6;
        return this;
    }

    public CustomAura setColL1(int col) {
        this.colL1 = col;
        return this;
    }

    public CustomAura setColL2(int col) {
        this.colL2 = col;
        return this;
    }

    public CustomAura setColL3(int col) {
        this.colL3 = col;
        return this;
    }

    public CustomAura setLegendaryCol(int col) {
        this.legCol = col;
        return this;
    }

    public CustomAura setDivineCol(int col) {
        this.divineCol = col;
        return this;
    }

    public CustomAura setTexL1(String tex) {
        this.texL1 = tex;
        return this;
    }

    public CustomAura setTexL2(String tex) {
        this.texL2 = tex;
        return this;
    }

    public CustomAura setTexL3(String tex) {
        this.texL3 = tex;
        return this;
    }

    public CustomAura setSpd(int spd) {
        this.spd = spd;
        return this;
    }

    public CustomAura setState(float st) {
        this.st = st;
        return this;
    }

    public CustomAura setAlp(float alp) {
        this.alp = alp;
        return this;
    }

    public CustomAura setBol4(boolean b, boolean b2) {
        this.bol4 = b;
        this.bol4a = b2;
        return this;
    }

    public CustomAura setLightning(boolean b) {
        this.hasLightning = b;
        return this;
    }

    public CustomAura setLightning(float r, float g, float b, float a) {
        this.hasLightning = true;
        this.lightningR = r;
        this.lightningG = g;
        this.lightningB = b;
        this.lightningA = a;
        return this;
    }

    public String getSound() {
        return this.sound;
    }

    public NBTTagCompound write() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("Sound", this.sound);
        if (this.texL1 != null && !this.texL1.isEmpty()) {
            compound.func_74778_a("TexL1", this.texL1);
        }
        if (this.texL2 != null && !this.texL2.isEmpty()) {
            compound.func_74778_a("TexL2", this.texL2);
        }
        if (this.texL3 != null && !this.texL3.isEmpty()) {
            compound.func_74778_a("TexL3", this.texL3);
        }
        compound.func_74768_a("ColL1", this.colL1);
        compound.func_74768_a("ColL2", this.colL2);
        compound.func_74768_a("ColL3", this.colL3);
        compound.func_74768_a("Speed", this.spd);
        compound.func_74768_a("Bol6", this.bol6);
        compound.func_74776_a("St", this.st);
        compound.func_74780_a("Alp", (double)this.alp);
        compound.func_74757_a("HasLightning", this.hasLightning);
        compound.func_74757_a("Bol4", this.bol4);
        compound.func_74757_a("Bol4A", this.bol4a);
        compound.func_74776_a("LightningR", this.lightningR);
        compound.func_74776_a("LightningG", this.lightningG);
        compound.func_74776_a("LightningB", this.lightningB);
        compound.func_74776_a("LightningA", this.lightningA);
        compound.func_74768_a("LegendaryCol", this.legCol);
        compound.func_74768_a("DivineCol", this.divineCol);
        return compound;
    }

    public static CustomAura read(NBTTagCompound compound) {
        CustomAura aura = new CustomAura();
        String texL1 = compound.func_74779_i("TexL1");
        String texL2 = compound.func_74779_i("TexL2");
        String texL3 = compound.func_74779_i("TexL3");
        aura.setSound(compound.func_74779_i("Sound"));
        if (texL1 != null) {
            aura.setTexL1(texL1);
        }
        if (texL2 != null) {
            aura.setTexL2(texL2);
        }
        if (texL3 != null) {
            aura.setTexL3(texL3);
        }
        aura.setColL1(compound.func_74762_e("ColL1"));
        aura.setColL2(compound.func_74762_e("ColL2"));
        aura.setColL3(compound.func_74762_e("ColL3"));
        aura.setLegendaryCol(compound.func_74762_e("LegendaryCol"));
        aura.setDivineCol(compound.func_74762_e("DivineCol"));
        aura.setSpd(compound.func_74762_e("Speed"));
        aura.setBol6(compound.func_74762_e("Bol6"));
        aura.setState(compound.func_74760_g("St"));
        aura.setAlp(compound.func_74760_g("Alp"));
        aura.setBol4(compound.func_74767_n("Bol4"), compound.func_74767_n("Bol4A"));
        if (compound.func_74767_n("HasLightning")) {
            aura.setLightning(compound.func_74760_g("LightningR"), compound.func_74760_g("LightningG"), compound.func_74760_g("LightningB"), compound.func_74760_g("LightningA"));
        }
        return aura;
    }
}

