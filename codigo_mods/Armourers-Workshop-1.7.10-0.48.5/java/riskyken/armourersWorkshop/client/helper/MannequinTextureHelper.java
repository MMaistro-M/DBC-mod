/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.util.StringUtils
 */
package riskyken.armourersWorkshop.client.helper;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.StringUtils;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.data.TextureType;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;
import riskyken.armourersWorkshop.proxies.ClientProxy;

@SideOnly(value=Side.CLIENT)
public final class MannequinTextureHelper {
    private static final String TAG_OWNER = "owner";
    private static final String TAG_IMAGE_URL = "imageUrl";
    private static final PlayerTexture NO_TEXTURE = new PlayerTexture("", TextureType.USER);

    private MannequinTextureHelper() {
    }

    public static PlayerTexture getMannequinTexture(ItemStack itemStack) {
        PlayerTexture playerTexture = NO_TEXTURE;
        GameProfile gameProfile = null;
        String imageUrl = null;
        if (itemStack.func_77942_o()) {
            NBTTagCompound compound = itemStack.func_77978_p();
            if (compound.func_150297_b(TAG_OWNER, 10)) {
                gameProfile = NBTUtil.func_152459_a((NBTTagCompound)compound.func_74775_l(TAG_OWNER));
            }
            if (compound.func_150297_b(TAG_IMAGE_URL, 8)) {
                imageUrl = compound.func_74779_i(TAG_IMAGE_URL);
            }
        }
        if (gameProfile != null) {
            playerTexture = MannequinTextureHelper.getMannequinTexture(gameProfile.getName(), TextureType.USER);
        }
        if (!StringUtils.func_151246_b(imageUrl)) {
            playerTexture = MannequinTextureHelper.getMannequinTexture(imageUrl, TextureType.URL);
        }
        return playerTexture;
    }

    public static PlayerTexture getMannequinTexture(TileEntityMannequin tileEntity) {
        PlayerTexture playerTexture = NO_TEXTURE;
        if (tileEntity.getGameProfile() != null && tileEntity.getTextureType() == TextureType.USER) {
            String name = tileEntity.getGameProfile().getName();
            playerTexture = MannequinTextureHelper.getMannequinTexture(name, TextureType.USER);
        }
        if (!StringUtils.func_151246_b((String)tileEntity.getImageUrl()) && tileEntity.getTextureType() == TextureType.URL) {
            playerTexture = MannequinTextureHelper.getMannequinTexture(tileEntity.getImageUrl(), TextureType.URL);
        }
        return playerTexture;
    }

    private static PlayerTexture getMannequinTexture(String textureString, TextureType textureType) {
        return ClientProxy.playerTextureDownloader.getPlayerTexture(textureString, textureType);
    }
}

