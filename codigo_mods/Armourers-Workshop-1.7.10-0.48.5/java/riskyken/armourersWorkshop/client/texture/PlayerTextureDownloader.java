/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.IImageBuffer
 *  net.minecraft.client.renderer.ImageBufferDownload
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.SkinManager$SkinAvailableCallback
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 */
package riskyken.armourersWorkshop.client.texture;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.texture.ModThreadDownloadImageData;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.GameProfileCache;
import riskyken.armourersWorkshop.common.data.TextureType;
import riskyken.armourersWorkshop.proxies.CommonProxy;
import riskyken.armourersWorkshop.utils.ModLogger;

@SideOnly(value=Side.CLIENT)
public class PlayerTextureDownloader
implements GameProfileCache.IGameProfileCallback {
    private final HashMap<String, PlayerTexture> playerTextureMap = new HashMap();
    private static final PlayerTexture NO_TEXTURE = new PlayerTexture("", TextureType.USER);
    private static long lastSkinDownload = 0L;

    public PlayerTexture getPlayerTexture(PlayerTexture playerTexture) {
        return this.getPlayerTexture(playerTexture.getTextureString(), playerTexture.getTextureType());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public PlayerTexture getPlayerTexture(String textureString, TextureType textureType) {
        PlayerTexture playerTexture = NO_TEXTURE;
        HashMap<String, PlayerTexture> hashMap = this.playerTextureMap;
        synchronized (hashMap) {
            if (this.playerTextureMap.containsKey(textureString)) {
                playerTexture = this.playerTextureMap.get(textureString);
            } else if (lastSkinDownload + 250L < System.currentTimeMillis()) {
                if (textureType == TextureType.URL) {
                    lastSkinDownload = System.currentTimeMillis();
                    playerTexture = new PlayerTexture(textureString, textureType);
                    this.playerTextureMap.put(textureString, playerTexture);
                    this.downloadTexture(textureString, playerTexture, textureType);
                } else {
                    this.getPlayerTextureFromName(textureString);
                }
            }
        }
        return playerTexture;
    }

    private void downloadTexture(String textureString, PlayerTexture playerTexture, TextureType textureType) {
        ResourceLocation resourceLocation = null;
        if (StringUtils.func_151246_b((String)textureString)) {
            return;
        }
        if (textureType == TextureType.URL) {
            resourceLocation = new ResourceLocation("skins/" + StringUtils.func_76338_a((String)textureString));
            ThreadDownloadImageData threadDownloadImageData = this.getDownloadImageSkin(resourceLocation, textureString, playerTexture, textureType);
        } else {
            ModLogger.log("Setting wrong " + textureString);
        }
    }

    private PlayerTexture getPlayerTextureFromName(String username) {
        GameProfile gameProfile = this.getGameProfile(username);
        if (gameProfile != null && !this.playerTextureMap.containsKey(gameProfile.getName())) {
            Minecraft minecraft = Minecraft.func_71410_x();
            PlayerTexture playerTexture = new PlayerTexture(gameProfile.getName(), TextureType.USER);
            playerTexture.setModelTypeFromProfile(gameProfile);
            minecraft.func_152342_ad().func_152790_a(gameProfile, (SkinManager.SkinAvailableCallback)new DownloadWrapper(playerTexture), false);
            this.playerTextureMap.put(gameProfile.getName(), playerTexture);
        }
        return NO_TEXTURE;
    }

    private GameProfile getGameProfile(String username) {
        if (!StringUtils.func_151246_b((String)username)) {
            CommonProxy proxy = ArmourersWorkshop.getProxy();
            if (proxy.isLocalPlayer(username)) {
                if (proxy.haveFullLocalProfile()) {
                    return proxy.getLocalGameProfile();
                }
                return null;
            }
            GameProfile filledProfile = GameProfileCache.getGameProfileClient(username, this);
            if (filledProfile != null) {
                return filledProfile;
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void profileDownloaded(GameProfile gameProfile) {
        Minecraft minecraft = Minecraft.func_71410_x();
        PlayerTexture playerTexture = new PlayerTexture(gameProfile.getName(), TextureType.USER);
        playerTexture.setModelTypeFromProfile(gameProfile);
        minecraft.func_152342_ad().func_152790_a(gameProfile, (SkinManager.SkinAvailableCallback)new DownloadWrapper(playerTexture), false);
        HashMap<String, PlayerTexture> hashMap = this.playerTextureMap;
        synchronized (hashMap) {
            this.playerTextureMap.put(gameProfile.getName(), playerTexture);
        }
    }

    private ThreadDownloadImageData getDownloadImageSkin(ResourceLocation resourceLocation, String textureString, PlayerTexture playerTexture, TextureType textureType) {
        TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
        Object object = texturemanager.func_110581_b(resourceLocation);
        if (object == null) {
            object = new ModThreadDownloadImageData(null, textureString, AbstractClientPlayer.field_110314_b, (IImageBuffer)new ImageBufferDownload(), playerTexture);
            texturemanager.func_110579_a(resourceLocation, object);
        }
        return (ThreadDownloadImageData)object;
    }

    private static class DownloadWrapper
    implements SkinManager.SkinAvailableCallback {
        private final PlayerTexture playerTexture;

        public DownloadWrapper(PlayerTexture playerTexture) {
            this.playerTexture = playerTexture;
        }

        public void func_152121_a(MinecraftProfileTexture.Type type, ResourceLocation resourceLocation) {
            if (type == MinecraftProfileTexture.Type.SKIN) {
                this.playerTexture.setResourceLocation(resourceLocation);
            }
        }
    }
}

