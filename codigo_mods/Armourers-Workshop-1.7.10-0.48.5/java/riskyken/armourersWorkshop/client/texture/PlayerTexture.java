/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  org.apache.commons.codec.Charsets
 *  org.apache.commons.codec.binary.Base64
 */
package riskyken.armourersWorkshop.client.texture;

import com.google.common.collect.Iterables;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import riskyken.armourersWorkshop.common.data.TextureType;

public class PlayerTexture {
    private static final String TAG_TEXTURE_STRING = "string";
    private static final String TAG_TEXTURE_TYPE = "type";
    private ResourceLocation resourceLocation;
    private boolean slimModel;
    private boolean downloaded;
    private TextureType textureType;
    private String textureString;
    private long downloadTime;

    public PlayerTexture(String textureString, TextureType textureType) {
        this.textureString = textureString;
        this.textureType = textureType;
        this.resourceLocation = this.getSteveResourceLocation();
    }

    private ResourceLocation getSteveResourceLocation() {
        return new ResourceLocation("textures/entity/steve.png");
    }

    public void textureDownloaded(boolean slimModel) {
        this.slimModel = slimModel;
        if (!StringUtils.func_151246_b((String)this.textureString)) {
            this.resourceLocation = this.textureType == TextureType.URL ? new ResourceLocation("skins/" + StringUtils.func_76338_a((String)this.textureString)) : new ResourceLocation("armourersWorkshop".toLowerCase(), StringUtils.func_76338_a((String)this.textureString));
        }
        this.downloadTime = System.currentTimeMillis();
        this.downloaded = true;
    }

    public void setModelTypeFromProfile(GameProfile gameProfile) {
        if (gameProfile != null && gameProfile.getProperties().containsKey((Object)"textures")) {
            Property property = (Property)Iterables.getFirst((Iterable)gameProfile.getProperties().get((Object)"textures"), null);
            try {
                String json = new String(Base64.decodeBase64((String)property.getValue()), Charsets.UTF_8);
                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse(json);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonObject jsonTextures = jsonObject.getAsJsonObject("textures");
                JsonObject jsonSkin = jsonTextures.getAsJsonObject("SKIN");
                if (jsonSkin != null && jsonSkin.has("metadata")) {
                    this.slimModel = true;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public String getTextureString() {
        return this.textureString;
    }

    public TextureType getTextureType() {
        return this.textureType;
    }

    public boolean isDownloaded() {
        return this.downloaded;
    }

    public long getDownloadTime() {
        return this.downloadTime;
    }

    public boolean isSlimModel() {
        return this.slimModel;
    }

    public ResourceLocation getResourceLocation() {
        return this.resourceLocation;
    }

    public void writeToNBT(NBTTagCompound compound) {
        compound.func_74778_a(TAG_TEXTURE_STRING, this.textureString);
        compound.func_74774_a(TAG_TEXTURE_TYPE, (byte)this.textureType.ordinal());
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.textureString = compound.func_74779_i(TAG_TEXTURE_STRING);
        this.textureType = TextureType.values()[compound.func_74771_c(TAG_TEXTURE_TYPE)];
    }

    public static PlayerTexture fromNBT(NBTTagCompound compound) {
        PlayerTexture playerTexture = new PlayerTexture("", TextureType.USER);
        playerTexture.readFromNBT(compound);
        return playerTexture;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.textureString == null ? 0 : this.textureString.hashCode());
        result = 31 * result + (this.textureType == null ? 0 : this.textureType.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        PlayerTexture other = (PlayerTexture)obj;
        if (this.textureString == null ? other.textureString != null : !this.textureString.equals(other.textureString)) {
            return false;
        }
        return this.textureType == other.textureType;
    }

    public void setResourceLocation(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
        this.downloadTime = System.currentTimeMillis();
        this.downloaded = true;
    }
}

