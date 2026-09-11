/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 */
package riskyken.armourersWorkshop.client.model.skin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.common.skin.data.Skin;

@SideOnly(value=Side.CLIENT)
public interface IEquipmentModel {
    public void render(Entity var1, Skin var2, float var3, float var4, float var5, float var6, float var7);

    public void render(Entity var1, ModelBiped var2, Skin var3, boolean var4, ISkinDye var5, byte[] var6, boolean var7, double var8, boolean var10);
}

