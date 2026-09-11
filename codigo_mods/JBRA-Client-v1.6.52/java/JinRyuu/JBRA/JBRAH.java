/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  JinRyuu.JHDSkins.e.JHDSkinsExtender
 *  JinRyuu.JHDSkins.e.SkinData
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 */
package JinRyuu.JBRA;

import JinRyuu.JHDSkins.e.JHDSkinsExtender;
import JinRyuu.JHDSkins.e.SkinData;
import cpw.mods.fml.common.Loader;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class JBRAH {
    public static ModelRenderer H;
    public static ModelRenderer RA;
    public static ModelRenderer LA;
    public static ModelRenderer RL;
    public static ModelRenderer LL;
    public static ModelRenderer B;
    public static ModelRenderer B1;
    public static ModelRenderer B2;
    public static ModelRenderer B3;
    public static ModelRenderer B4;
    public static ModelRenderer B5;
    public static ModelRenderer B7;
    public static ModelRenderer B9;

    public static Object skinData(EntityPlayer acp) {
        if (acp == null) {
            return null;
        }
        Object[] o = (Object[])JHDSkinsExtender.SList.get(acp.func_70005_c_());
        if (o == null) {
            return null;
        }
        return (SkinData)o[1];
    }

    public static boolean JHDS() {
        return Loader.isModLoaded((String)"jinryuuhdskinsextended");
    }

    public static ResourceLocation getSkinLoc(Object data) {
        return ((SkinData)data).getCapeLocation();
    }

    public static boolean getSkinHas(Object data) {
        return data != null && ((SkinData)data).image != null;
    }
}

