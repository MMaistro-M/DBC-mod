/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ResourceLocation
 */
package com.eliotlash.mclib.utils.resources;

import com.eliotlash.mclib.utils.resources.IWritableLocation;
import com.eliotlash.mclib.utils.resources.RLUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Objects;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;

public class FilteredResourceLocation
implements IWritableLocation<FilteredResourceLocation> {
    public static final int DEFAULT_COLOR = -1;
    public ResourceLocation path;
    public boolean autoSize = true;
    public int sizeW;
    public int sizeH;
    public int color = -1;
    public float scale = 1.0f;
    public boolean scaleToLargest;
    public int shiftX;
    public int shiftY;
    public int pixelate = 1;
    public boolean erase;

    public static FilteredResourceLocation from(NBTBase base) {
        try {
            FilteredResourceLocation location = new FilteredResourceLocation();
            location.fromNbt(base);
            return location;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static FilteredResourceLocation from(JsonElement element) {
        try {
            FilteredResourceLocation location = new FilteredResourceLocation();
            location.fromJson(element);
            return location;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public FilteredResourceLocation() {
    }

    public FilteredResourceLocation(ResourceLocation path) {
        this.path = path;
    }

    public int getWidth(int width) {
        if (!this.autoSize && this.sizeW > 0) {
            return this.sizeW;
        }
        return width;
    }

    public int getHeight(int height) {
        if (!this.autoSize && this.sizeH > 0) {
            return this.sizeH;
        }
        return height;
    }

    public String toString() {
        return this.path == null ? "" : this.path.toString();
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        if (obj instanceof FilteredResourceLocation) {
            FilteredResourceLocation frl = (FilteredResourceLocation)obj;
            return Objects.equals(this.path, frl.path) && this.autoSize == frl.autoSize && this.sizeW == frl.sizeW && this.sizeH == frl.sizeH && this.scaleToLargest == frl.scaleToLargest && this.color == frl.color && this.scale == frl.scale && this.shiftX == frl.shiftX && this.shiftY == frl.shiftY && this.pixelate == frl.pixelate && this.erase == frl.erase;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.path.hashCode();
        hashCode = 31 * hashCode + (this.autoSize ? 1 : 0);
        hashCode = 31 * hashCode + this.sizeW;
        hashCode = 31 * hashCode + this.sizeH;
        hashCode = 31 * hashCode + (this.scaleToLargest ? 1 : 0);
        hashCode = 31 * hashCode + this.color;
        hashCode = 31 * hashCode + (int)(this.scale * 1000.0f);
        hashCode = 31 * hashCode + this.shiftX;
        hashCode = 31 * hashCode + this.shiftY;
        hashCode = 31 * hashCode + this.pixelate;
        hashCode = 31 * hashCode + (this.erase ? 1 : 0);
        return hashCode;
    }

    public boolean isDefault() {
        return (this.autoSize || this.sizeW == 0 && this.sizeH == 0) && this.color == -1 && !this.scaleToLargest && this.scale == 1.0f && this.shiftX == 0 && this.shiftY == 0 && this.pixelate <= 1 && !this.erase;
    }

    @Override
    public void fromNbt(NBTBase nbt) throws Exception {
        if (nbt instanceof NBTTagString) {
            this.path = RLUtils.create(nbt);
            return;
        }
        NBTTagCompound tag = (NBTTagCompound)nbt;
        this.path = RLUtils.create(tag.func_74779_i("Path"));
        if (tag.func_74764_b("Color")) {
            this.color = tag.func_74762_e("Color");
        }
        if (tag.func_74764_b("Scale")) {
            this.scale = tag.func_74760_g("Scale");
        }
        if (tag.func_74764_b("ScaleToLargest")) {
            this.scaleToLargest = tag.func_74767_n("ScaleToLargest");
        }
        if (tag.func_74764_b("ShiftX")) {
            this.shiftX = tag.func_74762_e("ShiftX");
        }
        if (tag.func_74764_b("ShiftY")) {
            this.shiftY = tag.func_74762_e("ShiftY");
        }
        if (tag.func_74764_b("Pixelate")) {
            this.pixelate = tag.func_74762_e("Pixelate");
        }
        if (tag.func_74764_b("Erase")) {
            this.erase = tag.func_74767_n("Erase");
        }
        if (tag.func_74764_b("AutoSize")) {
            this.autoSize = tag.func_74767_n("AutoSize");
        }
        if (tag.func_74764_b("SizeW")) {
            this.sizeW = tag.func_74762_e("SizeW");
        }
        if (tag.func_74764_b("SizeH")) {
            this.sizeH = tag.func_74762_e("SizeH");
        }
    }

    @Override
    public void fromJson(JsonElement element) throws Exception {
        if (element.isJsonPrimitive()) {
            this.path = RLUtils.create(element);
            return;
        }
        JsonObject object = element.getAsJsonObject();
        this.path = RLUtils.create(object.get("path").getAsString());
        if (object.has("color")) {
            this.color = object.get("color").getAsInt();
        }
        if (object.has("scale")) {
            this.scale = object.get("scale").getAsFloat();
        }
        if (object.has("scaleToLargest")) {
            this.scaleToLargest = object.get("scaleToLargest").getAsBoolean();
        }
        if (object.has("shiftX")) {
            this.shiftX = object.get("shiftX").getAsInt();
        }
        if (object.has("shiftY")) {
            this.shiftY = object.get("shiftY").getAsInt();
        }
        if (object.has("pixelate")) {
            this.pixelate = object.get("pixelate").getAsInt();
        }
        if (object.has("erase")) {
            this.erase = object.get("erase").getAsBoolean();
        }
        if (object.has("autoSize")) {
            this.autoSize = object.get("autoSize").getAsBoolean();
        }
        if (object.has("sizeW")) {
            this.sizeW = object.get("sizeW").getAsInt();
        }
        if (object.has("sizeH")) {
            this.sizeH = object.get("sizeH").getAsInt();
        }
    }

    @Override
    public NBTBase writeNbt() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74778_a("Path", this.toString());
        if (this.color != -1) {
            tag.func_74768_a("Color", this.color);
        }
        if (this.scale != 1.0f) {
            tag.func_74776_a("Scale", this.scale);
        }
        if (this.scaleToLargest) {
            tag.func_74757_a("ScaleToLargest", this.scaleToLargest);
        }
        if (this.shiftX != 0) {
            tag.func_74768_a("ShiftX", this.shiftX);
        }
        if (this.shiftY != 0) {
            tag.func_74768_a("ShiftY", this.shiftY);
        }
        if (this.pixelate > 1) {
            tag.func_74768_a("Pixelate", this.pixelate);
        }
        if (this.erase) {
            tag.func_74757_a("Erase", this.erase);
        }
        if (!this.autoSize) {
            tag.func_74757_a("AutoSize", this.autoSize);
        }
        if (this.sizeW > 0) {
            tag.func_74768_a("SizeW", this.sizeW);
        }
        if (this.sizeH > 0) {
            tag.func_74768_a("SizeH", this.sizeH);
        }
        return tag;
    }

    @Override
    public JsonElement writeJson() {
        JsonObject object = new JsonObject();
        object.addProperty("path", this.toString());
        if (this.color != -1) {
            object.addProperty("color", (Number)this.color);
        }
        if (this.scale != 1.0f) {
            object.addProperty("scale", (Number)Float.valueOf(this.scale));
        }
        if (this.scaleToLargest) {
            object.addProperty("scaleToLargest", Boolean.valueOf(this.scaleToLargest));
        }
        if (this.shiftX != 0) {
            object.addProperty("shiftX", (Number)this.shiftX);
        }
        if (this.shiftY != 0) {
            object.addProperty("shiftY", (Number)this.shiftY);
        }
        if (this.pixelate > 1) {
            object.addProperty("pixelate", (Number)this.pixelate);
        }
        if (this.erase) {
            object.addProperty("erase", Boolean.valueOf(this.erase));
        }
        if (!this.autoSize) {
            object.addProperty("autoSize", Boolean.valueOf(this.autoSize));
        }
        if (this.sizeW > 0) {
            object.addProperty("sizeW", (Number)this.sizeW);
        }
        if (this.sizeH > 0) {
            object.addProperty("sizeH", (Number)this.sizeH);
        }
        return object;
    }

    public ResourceLocation clone() {
        return RLUtils.clone(this.path);
    }

    @Override
    public FilteredResourceLocation copy() {
        return FilteredResourceLocation.from(this.writeNbt());
    }
}

