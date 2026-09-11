/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.common.registry.GameRegistry$UniqueIdentifier
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.handler.data.IMagic;
import noppes.npcs.constants.EnumTextureType;
import noppes.npcs.controllers.MagicController;

public class Magic
implements IMagic {
    public String name = "";
    public String displayName = "";
    public int color = Integer.parseInt("FF00", 16);
    public int id = -1;
    public ItemStack item = null;
    public EnumTextureType type = EnumTextureType.BASE;
    public String iconTexture = "";
    public Map<Integer, Float> interactions = new HashMap<Integer, Float>();

    public Magic() {
    }

    public Magic(int id, String name, int color) {
        this.name = name;
        this.displayName = name;
        this.color = color;
        this.id = id;
    }

    public static String formatName(String name) {
        name = name.toLowerCase().trim();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public void readNBT(NBTTagCompound compound) {
        String[] parts;
        this.name = compound.func_74779_i("Name");
        this.displayName = compound.func_74779_i("DisplayName");
        this.color = compound.func_74762_e("Color");
        this.id = compound.func_74762_e("Slot");
        this.type = EnumTextureType.values()[compound.func_74762_e("Type")];
        this.iconTexture = compound.func_74779_i("IconTexture");
        if (this.type == EnumTextureType.ITEM && !this.iconTexture.isEmpty() && (parts = this.iconTexture.split(":")).length == 2) {
            String modID = parts[0];
            String itemName = parts[1];
            Item item = GameRegistry.findItem((String)modID, (String)itemName);
            this.item = item != null ? new ItemStack(item) : null;
        }
        this.interactions.clear();
        if (compound.func_74764_b("Interactions")) {
            NBTTagList interactionsList = compound.func_150295_c("Interactions", 10);
            for (int i = 0; i < interactionsList.func_74745_c(); ++i) {
                NBTTagCompound interactionTag = interactionsList.func_150305_b(i);
                int magicId = interactionTag.func_74762_e("MagicID");
                float percentage = interactionTag.func_74760_g("Percentage");
                this.interactions.put(magicId, Float.valueOf(percentage));
            }
        }
    }

    public void writeNBT(NBTTagCompound compound) {
        String[] parts;
        compound.func_74768_a("Slot", this.id);
        compound.func_74778_a("Name", this.name);
        compound.func_74778_a("DisplayName", this.displayName);
        compound.func_74768_a("Color", this.color);
        compound.func_74778_a("IconTexture", this.iconTexture);
        compound.func_74768_a("Type", this.type.ordinal());
        NBTTagList interactionsList = new NBTTagList();
        for (Map.Entry<Integer, Float> entry : this.interactions.entrySet()) {
            NBTTagCompound interactionTag = new NBTTagCompound();
            interactionTag.func_74768_a("MagicID", entry.getKey().intValue());
            interactionTag.func_74776_a("Percentage", entry.getValue().floatValue());
            interactionsList.func_74742_a((NBTBase)interactionTag);
        }
        compound.func_74782_a("Interactions", (NBTBase)interactionsList);
        if (this.type == EnumTextureType.ITEM && !this.iconTexture.isEmpty() && (parts = this.iconTexture.split(":")).length == 2) {
            String modID = parts[0];
            String itemName = parts[1];
            Item item = GameRegistry.findItem((String)modID, (String)itemName);
            this.item = item != null ? new ItemStack(item) : null;
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setColor(int c) {
        this.color = c;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public void save() {
        MagicController.getInstance().saveMagic(this);
    }

    public void setItem(ItemStack item) {
        GameRegistry.UniqueIdentifier identifier = GameRegistry.findUniqueIdentifierFor((Item)item.func_77973_b());
        if (identifier != null) {
            this.item = item;
            this.type = EnumTextureType.ITEM;
            this.iconTexture = identifier.modId + ":" + identifier.name;
        }
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setIconTexture(String texture) {
        this.iconTexture = texture;
    }

    public String getIconTexture() {
        return this.iconTexture;
    }

    public void setInteractions(Map<Integer, Float> interactions) {
        this.interactions = interactions;
    }

    public Map<Integer, Float> getInteractions() {
        return this.interactions;
    }

    @Override
    public boolean hasInteraction(int magicID) {
        return this.interactions.containsKey(magicID);
    }

    @Override
    public void setInteraction(int magicID, float value) {
        this.interactions.put(magicID, Float.valueOf(value));
    }

    @Override
    public float getInteraction(int magicID, float value) {
        if (this.hasInteraction(magicID)) {
            return this.interactions.get(magicID).floatValue();
        }
        return 0.0f;
    }
}

