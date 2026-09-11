/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  javax.annotation.Nullable
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.data.IMetadataSection
 *  net.minecraft.client.resources.data.IMetadataSerializer
 *  net.minecraft.util.ResourceLocation
 */
package net.geckominecraft.client.resources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

@SideOnly(value=Side.CLIENT)
public class LegacyV2Adapter
implements IResourcePack {
    private final IResourcePack pack;

    public LegacyV2Adapter(IResourcePack packIn) {
        this.pack = packIn;
    }

    public InputStream func_110590_a(ResourceLocation location) throws IOException {
        return this.pack.func_110590_a(this.fudgePath(location));
    }

    private ResourceLocation fudgePath(ResourceLocation p_191382_1_) {
        int i;
        String s = p_191382_1_.func_110623_a();
        if (!"lang/swg_de.lang".equals(s) && s.startsWith("lang/") && s.endsWith(".lang") && (i = s.indexOf(95)) != -1) {
            final String s1 = s.substring(0, i + 1) + s.substring(i + 1, s.indexOf(46, i)).toUpperCase() + ".lang";
            return new ResourceLocation(p_191382_1_.func_110624_b(), ""){

                public String func_110623_a() {
                    return s1;
                }
            };
        }
        return p_191382_1_;
    }

    public boolean func_110589_b(ResourceLocation location) {
        return this.pack.func_110589_b(this.fudgePath(location));
    }

    public Set<String> func_110587_b() {
        return this.pack.func_110587_b();
    }

    @Nullable
    public IMetadataSection func_135058_a(IMetadataSerializer metadataSerializer, String metadataSectionName) throws IOException {
        return this.pack.func_135058_a(metadataSerializer, metadataSectionName);
    }

    public BufferedImage func_110586_a() throws IOException {
        return this.pack.func_110586_a();
    }

    public String func_130077_b() {
        return this.pack.func_130077_b();
    }
}

