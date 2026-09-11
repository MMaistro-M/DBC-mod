/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.controllers;

import java.util.HashMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;

public class Preset {
    public ModelData data = new ModelData();
    public String name;

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("PresetName", this.name);
        compound.func_74782_a("PresetData", (NBTBase)this.data.writeToNBT());
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.name = compound.func_74779_i("PresetName");
        this.data.readFromNBT(compound.func_74775_l("PresetData"));
    }

    public static void FillDefault(HashMap<String, Preset> presets) {
        ModelData data = new ModelData();
        Preset preset = new Preset();
        preset.name = "Elf Male";
        preset.data = data;
        data.modelScale.legs.setScale(0.85f, 1.15f);
        data.modelScale.arms.setScale(0.85f, 1.15f);
        data.modelScale.body.setScale(0.85f, 1.15f);
        data.modelScale.head.setScale(0.85f, 0.95f);
        presets.put("elf male", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Elf Female";
        preset.data = data;
        data.breasts = (byte)2;
        data.modelScale.legs.setScale(0.8f, 1.05f);
        data.modelScale.arms.setScale(0.8f, 1.05f);
        data.modelScale.body.setScale(0.8f, 1.05f);
        data.modelScale.head.setScale(0.8f, 0.85f);
        presets.put("elf female", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Dwarf Male";
        preset.data = data;
        data.modelScale.legs.setScale(1.1f, 0.7f, 0.9f);
        data.modelScale.arms.setScale(0.9f, 0.7f);
        data.modelScale.body.setScale(1.2f, 0.7f, 1.5f);
        data.modelScale.head.setScale(0.85f, 0.85f);
        presets.put("dwarf male", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Dwarf Female";
        preset.data = data;
        data.breasts = (byte)2;
        data.modelScale.legs.setScale(0.9f, 0.65f);
        data.modelScale.arms.setScale(0.9f, 0.65f);
        data.modelScale.body.setScale(1.0f, 0.65f, 1.1f);
        data.modelScale.head.setScale(0.85f, 0.85f);
        presets.put("dwarf female", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Orc Male";
        preset.data = data;
        data.modelScale.legs.setScale(1.2f, 1.05f);
        data.modelScale.arms.setScale(1.2f, 1.05f);
        data.modelScale.body.setScale(1.4f, 1.1f, 1.5f);
        data.modelScale.head.setScale(1.2f, 1.1f);
        presets.put("orc male", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Orc Female";
        preset.data = data;
        data.breasts = (byte)2;
        data.modelScale.legs.setScale(1.1f, 1.0f);
        data.modelScale.arms.setScale(1.1f, 1.0f);
        data.modelScale.body.setScale(1.1f, 1.0f, 1.25f);
        presets.put("orc female", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Human Male";
        preset.data = data;
        presets.put("human male", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Human Female";
        preset.data = data;
        data.breasts = (byte)2;
        data.modelScale.head.setScale(0.95f, 0.95f);
        data.modelScale.legs.setScale(0.92f, 0.92f);
        data.modelScale.arms.setScale(0.8f, 0.92f);
        data.modelScale.body.setScale(0.92f, 0.92f);
        presets.put("human female", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Cat Male";
        preset.data = data;
        ModelPartData ears = data.getOrCreatePart("ears");
        ears.setTexture("ears/type1", 0);
        ears.color = 14263886;
        ModelPartData snout = data.getOrCreatePart("snout");
        snout.setTexture("snout/small1", 0);
        snout.color = 14263886;
        ModelPartData tail = data.getOrCreatePart("tail");
        tail.setTexture("tail/tail1", 0);
        tail.color = 14263886;
        presets.put("cat male", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Cat Female";
        preset.data = data;
        ears = data.getOrCreatePart("ears");
        ears.setTexture("ears/type1", 0);
        ears.color = 14263886;
        snout = data.getOrCreatePart("snout");
        snout.setTexture("snout/small1", 0);
        snout.color = 14263886;
        tail = data.getOrCreatePart("tail");
        tail.setTexture("tail/tail1", 0);
        tail.color = 14263886;
        data.breasts = (byte)2;
        data.modelScale.head.setScale(0.95f, 0.95f);
        data.modelScale.legs.setScale(0.92f, 0.92f);
        data.modelScale.arms.setScale(0.8f, 0.92f);
        data.modelScale.body.setScale(0.92f, 0.92f);
        presets.put("cat female", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Wolf Male";
        preset.data = data;
        ears = data.getOrCreatePart("ears");
        ears.setTexture("ears/type1", 0);
        ears.color = 0x5E5855;
        snout = data.getOrCreatePart("snout");
        snout.setTexture("snout/large1", 2);
        snout.color = 0x5E5855;
        tail = data.getOrCreatePart("tail");
        tail.setTexture("tail/tail2", 0);
        tail.color = 0x5E5855;
        presets.put("wolf male", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Wolf Female";
        preset.data = data;
        ears = data.getOrCreatePart("ears");
        ears.setTexture("ears/type1", 0);
        ears.color = 0x5E5855;
        snout = data.getOrCreatePart("snout");
        snout.setTexture("snout/large1", 2);
        snout.color = 0x5E5855;
        tail = data.getOrCreatePart("tail");
        tail.setTexture("tail/tail2", 0);
        tail.color = 0x5E5855;
        data.breasts = (byte)2;
        data.modelScale.head.setScale(0.95f, 0.95f);
        data.modelScale.legs.setScale(0.92f, 0.92f);
        data.modelScale.arms.setScale(0.8f, 0.92f);
        data.modelScale.body.setScale(0.92f, 0.92f);
        presets.put("wolf female", preset);
        data = new ModelData();
        preset = new Preset();
        preset.name = "Enderchibi";
        preset.data = data;
        data.modelScale.legs.setScale(0.65f, 0.75f);
        data.modelScale.arms.setScale(0.5f, 1.45f);
        ModelPartData part = data.getOrCreatePart("particles");
        part.setTexture("particle/type1", 1);
        part.color = 0xFF0000;
        presets.put("enderchibi", preset);
    }
}

