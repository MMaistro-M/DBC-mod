/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  buildcraft.api.robots.IRobotOverlayItem
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.common.items;

import buildcraft.api.robots.IRobotOverlayItem;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.handler.EquipmentRenderHandler;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

@Optional.Interface(iface="buildcraft.api.robots.IRobotOverlayItem", modid="BuildCraft|Core")
public class ItemSkinRobotOverlay
extends ItemSkin
implements IRobotOverlayItem {
    @Optional.Method(modid="BuildCraft|Core")
    public boolean isValidRobotOverlay(ItemStack stack) {
        if (!SkinNBTHelper.stackHasSkinData(stack)) {
            return false;
        }
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        return skinPointer.getIdentifier().getSkinType() == SkinTypeRegistry.skinHead;
    }

    @Optional.Method(modid="BuildCraft|Core")
    @SideOnly(value=Side.CLIENT)
    public void renderRobotOverlay(ItemStack stack, TextureManager textureManager) {
        GL11.glPushMatrix();
        GL11.glScalef((float)1.0125f, (float)1.0125f, (float)1.0125f);
        GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        EquipmentRenderHandler.INSTANCE.renderSkinWithHelper(stack);
        GL11.glPopMatrix();
    }
}

