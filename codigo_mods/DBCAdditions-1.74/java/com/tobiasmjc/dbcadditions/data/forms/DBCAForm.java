/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package com.tobiasmjc.dbcadditions.data.forms;

import com.tobiasmjc.dbcadditions.data.FormMasteryData;
import com.tobiasmjc.dbcadditions.data.forms.DBCAFormMastery;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.FormColors;
import com.tobiasmjc.dbcadditions.data.forms.display.CustomAura;
import com.tobiasmjc.dbcadditions.data.forms.display.HairType;
import com.tobiasmjc.dbcadditions.data.forms.display.Tattoo;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class DBCAForm {
    public static int MIN_ID = 20;
    public String DisplayName;
    private String name;
    private String nextForm = "";
    private float multiplier;
    private FormColors defaultColors = new FormColors();
    private FormColors legendaryColors = new FormColors();
    private FormColors divineColors = new FormColors();
    private int bodyType = -1;
    private float kiDrain = -1.0f;
    private float healthDrain = 0.0f;
    private float healthDrainKaio = 5.0f;
    private boolean eyebrow = true;
    private boolean berserk = false;
    private char colorCode;
    private boolean kaiokenStackable = true;
    private float sizeMultiplier = 1.0f;
    private float bulkMultiplier = 1.0f;
    private int arcosianSt;
    private boolean enabled = true;
    private HairType hairType;
    private List<Tattoo> tattoos = new ArrayList<Tattoo>();
    private String customHair = "";
    private int id;
    private List<CustomAura> auras = new ArrayList<CustomAura>();
    public FormMasteryData MasteryData;

    private DBCAForm(int id, String name, float multiplier) {
        this.name = name;
        this.multiplier = multiplier;
        this.colorCode = '\u0000';
        this.id = id;
        this.MasteryData = new FormMasteryData();
    }

    public DBCAForm(int id, String name, float multiplier, int hairColor, int eyeColor, int barColor) {
        this.name = name;
        this.multiplier = multiplier;
        this.defaultColors.setHairColor(hairColor);
        this.defaultColors.setEyeColor(eyeColor);
        this.defaultColors.setBarColor(barColor);
        this.defaultColors.setKiColor(barColor);
        this.defaultColors.setTailColor(hairColor);
        this.colorCode = '\u0000';
        this.id = id;
        this.MasteryData = new FormMasteryData();
    }

    public DBCAForm(String name, float multiplier, int hairColor, int eyeColor, int barColor) {
        this.name = name;
        this.multiplier = multiplier;
        this.defaultColors.setHairColor(hairColor);
        this.defaultColors.setEyeColor(eyeColor);
        this.defaultColors.setBarColor(barColor);
        this.defaultColors.setKiColor(barColor);
        this.defaultColors.setTailColor(hairColor);
        this.colorCode = '\u0000';
        this.id = ++MIN_ID;
        this.MasteryData = new FormMasteryData();
    }

    public DBCAFormMastery getMastery(EntityPlayer p) {
        String str = DataUtils.getDBCAFormMasteries(p);
        DBCAFormMastery mastery = new DBCAFormMastery(str, this.id);
        mastery.player = p;
        return mastery;
    }

    public DBCAForm setLegendaryColors(int hair, int eye, int ki, int bar, int fur, int tail, int tattoo, int[] body) {
        this.legendaryColors.setColors(hair, eye, ki, bar, fur, tail, tattoo, body);
        return this;
    }

    public DBCAForm setDivineColors(int hair, int eye, int ki, int bar, int fur, int tail, int tattoo, int[] body) {
        this.divineColors.setColors(hair, eye, ki, bar, fur, tail, tattoo, body);
        return this;
    }

    public DBCAForm setMasteryData(FormMasteryData data) {
        this.MasteryData = data;
        return this;
    }

    public DBCAForm setNextForm(String form) {
        this.nextForm = form;
        return this;
    }

    public DBCAForm setBodyType(int t) {
        this.bodyType = t;
        return this;
    }

    public DBCAForm setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public DBCAForm getNextForm() {
        return DBCAForms.getForm(this.nextForm);
    }

    public String getName() {
        return this.name;
    }

    public float getMultiplier() {
        return this.multiplier;
    }

    public int getStateRender() {
        return this.arcosianSt;
    }

    public int getBodyType() {
        return this.bodyType;
    }

    public boolean IsBerserk() {
        return this.berserk;
    }

    public FormColors getColors() {
        return this.defaultColors;
    }

    public DBCAForm setStateRender(int st) {
        this.arcosianSt = st;
        return this;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = (float)multiplier;
    }

    public DBCAForm setBulkMultiplier(float f) {
        this.bulkMultiplier = f;
        return this;
    }

    public float getBulkMultiplier() {
        return this.bulkMultiplier;
    }

    public DBCAForm setSizeMultiplier(float sizeMultiplier) {
        this.sizeMultiplier = sizeMultiplier;
        return this;
    }

    public DBCAForm addTattoo(Tattoo t) {
        this.tattoos.add(t);
        return this;
    }

    public float getSizeMultiplier() {
        return this.sizeMultiplier;
    }

    public int getHairColor(boolean legendary, boolean divine) {
        int legendaryHairColor = this.legendaryColors.getHairColor();
        int divineHairColor = this.divineColors.getHairColor();
        if (legendary && legendaryHairColor != -1) {
            return legendaryHairColor;
        }
        if (divine && divineHairColor != -1) {
            return divineHairColor;
        }
        return this.defaultColors.getHairColor();
    }

    public int[] getBodyColors(boolean legendary, boolean divine) {
        int[] legendaryBodyColors = this.legendaryColors.getBodyColors();
        int[] divineBodyColors = this.divineColors.getBodyColors();
        if (legendary && legendaryBodyColors != null) {
            return legendaryBodyColors;
        }
        if (divine && divineBodyColors != null) {
            return divineBodyColors;
        }
        return this.defaultColors.getBodyColors();
    }

    public int getEyeColor(boolean legendary, boolean divine) {
        int legendaryEyeColor = this.legendaryColors.getEyeColor();
        int divineEyeColor = this.divineColors.getEyeColor();
        if (legendary && legendaryEyeColor != -1) {
            return legendaryEyeColor;
        }
        if (divine && divineEyeColor != -1) {
            return divineEyeColor;
        }
        return this.defaultColors.getEyeColor();
    }

    public int getTattooColor(boolean legendary, boolean divine) {
        int legendaryTattooColor = this.legendaryColors.getTattooColor();
        int divineTattooColor = this.divineColors.getTattooColor();
        if (legendary && legendaryTattooColor != -1) {
            return legendaryTattooColor;
        }
        if (divine && divineTattooColor != -1) {
            return divineTattooColor;
        }
        return this.defaultColors.getTattooColor();
    }

    public int getTailColor(boolean legendary, boolean divine) {
        int legendaryTailColor = this.legendaryColors.getTailColor();
        int divineTailColor = this.divineColors.getTailColor();
        if (legendary && legendaryTailColor != -1) {
            return legendaryTailColor;
        }
        if (divine && divineTailColor != -1) {
            return divineTailColor;
        }
        return this.defaultColors.getTailColor();
    }

    public int getBarColor(boolean legendary, boolean divine) {
        int legendaryBarColor = this.legendaryColors.getBarColor();
        int divineBarColor = this.divineColors.getBarColor();
        if (legendary && legendaryBarColor != -1) {
            return legendaryBarColor;
        }
        if (divine && divineBarColor != -1) {
            return divineBarColor;
        }
        return this.defaultColors.getBarColor();
    }

    public int getID() {
        return this.id;
    }

    public int getKiColor(boolean legendary, boolean divine) {
        int legendaryKiColor = this.legendaryColors.getKiColor();
        int divineKiColor = this.divineColors.getKiColor();
        if (legendary && legendaryKiColor != -1) {
            return legendaryKiColor;
        }
        if (divine && divineKiColor != -1) {
            return divineKiColor;
        }
        return this.defaultColors.getKiColor();
    }

    public boolean hasEyeBrow() {
        return this.eyebrow;
    }

    public String getCustomHair() {
        return this.customHair;
    }

    public DBCAForm setTattoos(List<Tattoo> tattoos) {
        this.tattoos = tattoos;
        return this;
    }

    public DBCAForm setHairType(HairType type) {
        this.hairType = type;
        return this;
    }

    public float getKiDrain() {
        return this.kiDrain;
    }

    public float getHealthDrain() {
        return this.healthDrain;
    }

    public float getHealthDrainKaio() {
        return this.healthDrainKaio;
    }

    public DBCAForm setCustomHair(String hair) {
        this.customHair = hair;
        if (!hair.isEmpty()) {
            this.hairType = HairType.CUSTOM;
        }
        return this;
    }

    public DBCAForm setHealthDrain(double healthDrain) {
        this.healthDrain = (float)healthDrain;
        return this;
    }

    public DBCAForm setHealthDrainKaio(double healthDrain) {
        this.healthDrainKaio = (float)healthDrain;
        return this;
    }

    public DBCAForm setKiDrainPercentage(double kiDrain) {
        this.kiDrain = (float)kiDrain;
        return this;
    }

    public DBCAForm setEyeBrow(boolean b) {
        this.eyebrow = b;
        return this;
    }

    public DBCAForm addAura(CustomAura aura) {
        this.auras.add(aura);
        return this;
    }

    public DBCAForm setKaiokenStackable(boolean kaio) {
        this.kaiokenStackable = kaio;
        return this;
    }

    public DBCAForm setColorCode(char c) {
        this.colorCode = c;
        return this;
    }

    public DBCAForm setBerserk(boolean b) {
        this.berserk = b;
        return this;
    }

    public int getFurColor(boolean legendary, boolean divine) {
        int legendaryFurColor = this.legendaryColors.getFurColor();
        int divineFurColor = this.divineColors.getFurColor();
        if (legendary && legendaryFurColor != -1) {
            return legendaryFurColor;
        }
        if (divine && divineFurColor != -1) {
            return divineFurColor;
        }
        return this.defaultColors.getFurColor();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean canStackWithKaioken() {
        return this.kaiokenStackable;
    }

    public List<CustomAura> getAuras() {
        return this.auras;
    }

    public String getColorCode() {
        return "\u00a7" + this.colorCode;
    }

    public DBCAForm setLegendaryColors(FormColors colors) {
        this.legendaryColors = colors;
        return this;
    }

    public DBCAForm setDivineColors(FormColors colors) {
        this.divineColors = colors;
        return this;
    }

    public DBCAForm setColors(FormColors colors) {
        this.defaultColors = colors;
        return this;
    }

    public List<Tattoo> getTattoos() {
        return this.tattoos;
    }

    public HairType getHairType() {
        return this.hairType;
    }

    public NBTTagCompound write() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("FormID", this.id);
        compound.func_74778_a("Name", this.name);
        compound.func_74757_a("Enabled", this.enabled);
        compound.func_74776_a("Multiplier", this.multiplier);
        if (!this.nextForm.isEmpty()) {
            compound.func_74778_a("NextForm", this.nextForm);
        }
        compound.func_74776_a("KiDrain", this.kiDrain);
        compound.func_74776_a("HealthDrainKaio", this.healthDrainKaio);
        compound.func_74776_a("HealthDrain", this.healthDrain);
        compound.func_74757_a("Berserk", this.berserk);
        compound.func_74757_a("EyeBrow", this.eyebrow);
        compound.func_74776_a("SizeMultiplier", this.sizeMultiplier);
        compound.func_74776_a("BulkMultiplier", this.bulkMultiplier);
        compound.func_74778_a("ColorCode", this.colorCode + "");
        compound.func_74768_a("ArcosianState", this.arcosianSt);
        compound.func_74768_a("BodyType", this.bodyType);
        if (this.DisplayName != null) {
            compound.func_74778_a("DisplayName", this.DisplayName);
        }
        if (this.hairType != null) {
            compound.func_74768_a("HairType", this.hairType.ordinal());
        } else {
            compound.func_74768_a("HairType", -1);
        }
        if (this.tattoos != null && this.tattoos.size() > 0) {
            int[] t = new int[this.tattoos.size()];
            for (int i = 0; i < t.length; ++i) {
                t[i] = this.tattoos.get(i).ordinal();
            }
            compound.func_74783_a("Tattoos", t);
        }
        compound.func_74778_a("CustomHair", this.customHair);
        if (this.MasteryData != null) {
            compound.func_74782_a("MasteryData", (NBTBase)this.MasteryData.write());
        }
        compound.func_74782_a("DefaultColors", (NBTBase)this.defaultColors.write());
        compound.func_74782_a("LegendaryColors", (NBTBase)this.legendaryColors.write());
        compound.func_74782_a("DivineColors", (NBTBase)this.divineColors.write());
        NBTTagList list = new NBTTagList();
        for (CustomAura customAura : this.auras) {
            list.func_74742_a((NBTBase)customAura.write());
        }
        compound.func_74782_a("Auras", (NBTBase)list);
        return compound;
    }

    public static DBCAForm read(NBTTagCompound compound) {
        String nextFormID;
        int id = compound.func_74762_e("FormID");
        String name = compound.func_74779_i("Name");
        boolean enabled = compound.func_74767_n("Enabled");
        float multiplier = compound.func_74760_g("Multiplier");
        int hairType = compound.func_74762_e("HairType");
        int[] tattoos = new int[]{};
        if (compound.func_74764_b("Tattoos")) {
            tattoos = compound.func_74759_k("Tattoos");
        }
        DBCAForm form = new DBCAForm(id, name, multiplier).setEnabled(enabled);
        if (compound.func_74764_b("NextForm") && !(nextFormID = compound.func_74779_i("NextForm")).isEmpty()) {
            form.setNextForm(nextFormID);
        }
        if (compound.func_74764_b("DisplayName")) {
            form.DisplayName = compound.func_74779_i("DisplayName");
        }
        form.setBodyType(compound.func_74762_e("BodyType"));
        form.setKiDrainPercentage(compound.func_74760_g("KiDrain")).setHealthDrainKaio(compound.func_74760_g("HealthDrainKaio")).setHealthDrain(compound.func_74760_g("HealthDrain"));
        form.setBerserk(compound.func_74767_n("Berserk")).setEyeBrow(compound.func_74767_n("EyeBrow"));
        form.setColorCode(compound.func_74779_i("ColorCode").charAt(0));
        form.setSizeMultiplier(compound.func_74760_g("SizeMultiplier")).setBulkMultiplier(compound.func_74760_g("BulkMultiplier")).setStateRender(compound.func_74762_e("ArcosianState"));
        form.setColorCode(compound.func_74779_i("ColorCode").charAt(0));
        if (hairType != -1) {
            form.setHairType(HairType.values()[hairType]);
        }
        if (tattoos.length > 0) {
            ArrayList<Tattoo> tattos2 = new ArrayList<Tattoo>();
            for (int i : tattoos) {
                tattos2.add(Tattoo.values()[i]);
            }
            form.setTattoos(tattos2);
        }
        form.setCustomHair(compound.func_74779_i("CustomHair"));
        form.setMasteryData(FormMasteryData.read(compound.func_74775_l("MasteryData")));
        form.setColors(FormColors.read(compound.func_74775_l("DefaultColors")));
        form.setLegendaryColors(FormColors.read(compound.func_74775_l("LegendaryColors")));
        form.setDivineColors(FormColors.read(compound.func_74775_l("DivineColors")));
        NBTTagList auras = compound.func_150295_c("Auras", 10);
        for (int i = 0; i < auras.func_74745_c(); ++i) {
            NBTTagCompound formData = auras.func_150305_b(i);
            CustomAura aura = CustomAura.read(formData);
            form.addAura(aura);
        }
        return form;
    }

    public DBCAForm setKiColor(int i) {
        this.defaultColors.setKiColor(i);
        return this;
    }

    public DBCAForm setBodyColors(int i, int j, int k, int l) {
        this.defaultColors.setBodyColors(i, j, k, l);
        return this;
    }
}

