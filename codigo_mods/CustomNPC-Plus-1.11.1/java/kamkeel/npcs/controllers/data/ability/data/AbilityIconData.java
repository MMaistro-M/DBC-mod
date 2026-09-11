/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package kamkeel.npcs.controllers.data.ability.data;

import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class AbilityIconData {
    public static final String NBT_KEY = "AbilityIcon";
    public static final int MAX_LAYERS = 3;
    private final NBTTagCompound customData;
    public int width = 32;
    public int height = 32;
    public float scale = 1.0f;
    private boolean enabled = false;
    private int layerCount = 1;
    private Layer[] layers = new Layer[]{new Layer(), new Layer(), new Layer()};
    private int[][] stateIcons = null;
    private boolean animated = false;
    private int frameCount = 1;
    private int frametime = 2;

    private AbilityIconData(NBTTagCompound customData) {
        this.customData = customData;
    }

    public static AbilityIconData fromCustomData(NBTTagCompound customData) {
        AbilityIconData icon = new AbilityIconData(customData);
        if (customData.func_74764_b(NBT_KEY)) {
            icon.readFromNBT(customData.func_74775_l(NBT_KEY));
        }
        return icon;
    }

    public static AbilityIconData fromAbility(Ability ability) {
        return AbilityIconData.fromCustomData(ability.getCustomData());
    }

    public static AbilityIconData fromChainedAbility(ChainedAbility chain) {
        return AbilityIconData.fromCustomData(chain.getCustomData());
    }

    public void save() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        this.customData.func_74782_a(NBT_KEY, (NBTBase)tag);
    }

    public void writeToNBT(NBTTagCompound nbt) {
        nbt.func_74757_a("Enabled", this.enabled);
        nbt.func_74768_a("Width", this.width);
        nbt.func_74768_a("Height", this.height);
        nbt.func_74776_a("Scale", this.scale);
        nbt.func_74768_a("LayerCount", this.layerCount);
        NBTTagList layerList = new NBTTagList();
        for (int i = 0; i < 3; ++i) {
            NBTTagCompound layerNBT = new NBTTagCompound();
            this.layers[i].writeToNBT(layerNBT);
            layerList.func_74742_a((NBTBase)layerNBT);
        }
        nbt.func_74782_a("Layers", (NBTBase)layerList);
        nbt.func_74757_a("Animated", this.animated);
        nbt.func_74768_a("FrameCount", this.frameCount);
        nbt.func_74768_a("FrameTime", this.frametime);
        if (this.stateIcons != null && this.stateIcons.length > 0) {
            NBTTagList list = new NBTTagList();
            for (int[] pair : this.stateIcons) {
                NBTTagCompound comp = new NBTTagCompound();
                comp.func_74768_a("IconX", pair[0]);
                comp.func_74768_a("IconY", pair[1]);
                list.func_74742_a((NBTBase)comp);
            }
            nbt.func_74782_a("StateIcons", (NBTBase)list);
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        int i;
        this.enabled = nbt.func_74767_n("Enabled");
        this.width = nbt.func_74762_e("Width");
        if (this.width <= 0) {
            this.width = 32;
        }
        this.height = nbt.func_74762_e("Height");
        if (this.height <= 0) {
            this.height = 32;
        }
        this.scale = nbt.func_74760_g("Scale");
        if (this.scale <= 0.0f) {
            this.scale = 1.0f;
        }
        this.layerCount = nbt.func_74762_e("LayerCount");
        if (this.layerCount < 1) {
            this.layerCount = 1;
        }
        if (this.layerCount > 3) {
            this.layerCount = 3;
        }
        if (nbt.func_74764_b("Animated")) {
            this.animated = nbt.func_74767_n("Animated");
        }
        if (nbt.func_74764_b("FrameCount")) {
            this.frameCount = Math.max(1, nbt.func_74762_e("FrameCount"));
        }
        if (nbt.func_74764_b("FrameTime")) {
            this.frametime = Math.max(1, nbt.func_74762_e("FrameTime"));
        }
        if (nbt.func_74764_b("Layers")) {
            NBTTagList layerList = nbt.func_150295_c("Layers", 10);
            for (i = 0; i < 3; ++i) {
                this.layers[i] = new Layer();
                if (i >= layerList.func_74745_c()) continue;
                this.layers[i].readFromNBT(layerList.func_150305_b(i));
            }
        }
        if (nbt.func_74764_b("StateIcons")) {
            NBTTagList list = nbt.func_150295_c("StateIcons", 10);
            this.stateIcons = new int[list.func_74745_c()][2];
            for (i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound comp = list.func_150305_b(i);
                this.stateIcons[i][0] = comp.func_74762_e("IconX");
                this.stateIcons[i][1] = comp.func_74762_e("IconY");
            }
        }
    }

    public int getLayerCount() {
        return this.layerCount;
    }

    public void setLayerCount(int count) {
        this.layerCount = Math.max(1, Math.min(3, count));
        this.save();
    }

    public Layer getLayer(int index) {
        return index >= 0 && index < 3 ? this.layers[index] : this.layers[0];
    }

    public void setLayerTexture(int index, String tex) {
        if (index >= 0 && index < 3) {
            this.layers[index].texture = tex != null ? tex : "";
            this.save();
        }
    }

    public void setLayerIconX(int index, int x) {
        if (index >= 0 && index < 3) {
            this.layers[index].iconX = Math.max(0, x);
            this.save();
        }
    }

    public void setLayerIconY(int index, int y) {
        if (index >= 0 && index < 3) {
            this.layers[index].iconY = Math.max(0, y);
            this.save();
        }
    }

    public void setLayerTintColor(int index, int color) {
        if (index >= 0 && index < 3) {
            this.layers[index].tintColor = color & 0xFFFFFF;
            this.save();
        }
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        this.save();
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = Math.max(1, width);
        this.save();
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = Math.max(1, height);
        this.save();
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = Math.max(0.1f, scale);
        this.save();
    }

    public boolean isAnimated() {
        return this.animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
        this.save();
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = Math.max(1, frameCount);
        this.save();
    }

    public int getFrameTime() {
        return this.frametime;
    }

    public void setFrameTime(int frametime) {
        this.frametime = Math.max(1, frametime);
        this.save();
    }

    public void setStateIcons(int[][] stateIcons) {
        this.stateIcons = stateIcons;
        this.save();
    }

    public int getIconXForState(int state) {
        if (state > 0 && this.stateIcons != null && state - 1 < this.stateIcons.length) {
            return this.stateIcons[state - 1][0];
        }
        return this.layers[0].iconX;
    }

    public int getIconYForState(int state) {
        if (state > 0 && this.stateIcons != null && state - 1 < this.stateIcons.length) {
            return this.stateIcons[state - 1][1];
        }
        return this.layers[0].iconY;
    }

    public static class Layer {
        public String texture = "";
        public int iconX = 0;
        public int iconY = 0;
        public int tintColor = 0xFFFFFF;

        public boolean hasTexture() {
            return this.texture != null && !this.texture.isEmpty();
        }

        public void writeToNBT(NBTTagCompound nbt) {
            nbt.func_74778_a("Texture", this.texture);
            nbt.func_74768_a("IconX", this.iconX);
            nbt.func_74768_a("IconY", this.iconY);
            nbt.func_74768_a("TintColor", this.tintColor);
        }

        public void readFromNBT(NBTTagCompound nbt) {
            this.texture = nbt.func_74779_i("Texture");
            this.iconX = nbt.func_74762_e("IconX");
            this.iconY = nbt.func_74762_e("IconY");
            this.tintColor = nbt.func_74764_b("TintColor") ? nbt.func_74762_e("TintColor") : 0xFFFFFF;
        }
    }
}

