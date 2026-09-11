/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.render.MannequinFakePlayer;
import riskyken.armourersWorkshop.client.render.SkinModelRenderer;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.client.render.SkinPartRenderer;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.wings.SkinWings;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExtraColours;
import riskyken.armourersWorkshop.proxies.ClientProxy;
import riskyken.armourersWorkshop.utils.SkinUtils;

@SideOnly(value=Side.CLIENT)
public class ModelRendererAttachment
extends ModelRenderer {
    private final ISkinType skinType;
    private final ISkinPartType skinPart;
    private final Minecraft mc;
    private ModelBiped baseModel;

    public ModelRendererAttachment(ModelBiped modelBase, ISkinType skinType, ISkinPartType skinPart) {
        super((ModelBase)modelBase);
        this.baseModel = modelBase;
        this.mc = Minecraft.func_71410_x();
        this.skinType = skinType;
        this.skinPart = skinPart;
        this.func_78789_a(0.0f, 0.0f, 0.0f, 0, 0, 0);
    }

    public void func_78785_a(float scale) {
        if (ClientProxy.getSkinRenderType() != ClientProxy.SkinRenderType.MODEL_ATTACHMENT) {
            return;
        }
        this.mc.field_71424_I.func_76320_a("armourers player render");
        SkinModelRenderer modelRenderer = SkinModelRenderer.INSTANCE;
        EntityPlayer player = modelRenderer.targetPlayer;
        if (player == null) {
            this.mc.field_71424_I.func_76319_b();
            return;
        }
        if (player instanceof MannequinFakePlayer) {
            this.mc.field_71424_I.func_76319_b();
            return;
        }
        double distance = Minecraft.func_71410_x().field_71439_g.func_70011_f(player.field_70165_t, player.field_70163_u, player.field_70161_v);
        if (distance > (double)ConfigHandlerClient.maxSkinRenderDistance) {
            return;
        }
        EquipmentWardrobeData ewd = ClientProxy.equipmentWardrobeHandler.getEquipmentWardrobeData(new PlayerPointer(player));
        byte[] extraColours = null;
        if (ewd != null) {
            Color skinColour = new Color(ewd.getExtraColours().getColour(ExtraColours.ExtraColourType.SKIN));
            Color hairColour = new Color(ewd.getExtraColours().getColour(ExtraColours.ExtraColourType.HAIR));
            extraColours = new byte[]{(byte)skinColour.getRed(), (byte)skinColour.getGreen(), (byte)skinColour.getBlue(), (byte)hairColour.getRed(), (byte)hairColour.getGreen(), (byte)hairColour.getBlue()};
        }
        for (int skinIndex = 0; skinIndex < 10; ++skinIndex) {
            Skin data = modelRenderer.getPlayerCustomArmour((Entity)player, this.skinType, skinIndex);
            if (data == null) continue;
            ISkinDye skinDye = modelRenderer.getPlayerDyeData((Entity)player, this.skinType, skinIndex);
            SkinWings.MovementType movmentType = SkinWings.MovementType.valueOf(SkinProperties.PROP_WINGS_MOVMENT_TYPE.getValue(data.getProperties()));
            int size = data.getParts().size();
            for (int i = 0; i < size; ++i) {
                SkinPart partData = data.getParts().get(i);
                if (partData.getPartType() != this.skinPart) continue;
                GL11.glPushMatrix();
                if (this.skinPart.getRegistryName().equals("armourers:legs.skirt")) {
                    GL11.glTranslatef((float)0.0f, (float)(12.0f * scale), (float)0.0f);
                    if (player.func_70093_af()) {
                        GL11.glRotatef((float)-30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                        GL11.glTranslatef((float)0.0f, (float)(-1.25f * scale), (float)(-2.0f * scale));
                    }
                    if (player.func_70115_ae()) {
                        GL11.glRotated((double)-70.0, (double)1.0, (double)0.0, (double)0.0);
                    }
                }
                if (this.skinPart.getRegistryName().equals("armourers:wings.rightWing") | this.skinPart.getRegistryName().equals("armourers:wings.leftWing")) {
                    GL11.glTranslated((double)0.0, (double)0.0, (double)(scale * 2.0f));
                    double angle = SkinUtils.getFlapAngleForWings((Entity)player, data, i);
                    Point3D point = new Point3D(0, 0, 0);
                    ForgeDirection axis = ForgeDirection.DOWN;
                    if (partData.getMarkerCount() > 0) {
                        point = partData.getMarker(0);
                        axis = partData.getMarkerSide(0);
                    }
                    GL11.glTranslated((double)(scale * 0.5f), (double)(scale * 0.5f), (double)(scale * 0.5f));
                    GL11.glTranslated((double)(scale * (float)point.getX()), (double)(scale * (float)point.getY()), (double)(scale * (float)point.getZ()));
                    if (this.skinPart.getRegistryName().equals("armourers:wings.rightWing")) {
                        angle = -angle;
                    }
                    switch (axis) {
                        case UP: {
                            GL11.glRotated((double)angle, (double)0.0, (double)1.0, (double)0.0);
                            break;
                        }
                        case DOWN: {
                            GL11.glRotated((double)angle, (double)0.0, (double)-1.0, (double)0.0);
                            break;
                        }
                        case SOUTH: {
                            GL11.glRotated((double)angle, (double)0.0, (double)0.0, (double)-1.0);
                            break;
                        }
                        case NORTH: {
                            GL11.glRotated((double)angle, (double)0.0, (double)0.0, (double)1.0);
                            break;
                        }
                        case EAST: {
                            GL11.glRotated((double)angle, (double)1.0, (double)0.0, (double)0.0);
                            break;
                        }
                        case WEST: {
                            GL11.glRotated((double)angle, (double)-1.0, (double)0.0, (double)0.0);
                            break;
                        }
                    }
                    GL11.glTranslated((double)(scale * (float)(-point.getX())), (double)(scale * (float)(-point.getY())), (double)(scale * (float)(-point.getZ())));
                    GL11.glTranslated((double)(scale * -0.5f), (double)(scale * -0.5f), (double)(scale * -0.5f));
                }
                GL11.glEnable((int)2884);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glEnable((int)3042);
                SkinPartRenderer.INSTANCE.renderPart(new SkinPartRenderData(partData, scale, skinDye, extraColours, distance, true, false, false, null));
                GL11.glDisable((int)2884);
                GL11.glPopMatrix();
            }
        }
        if (ClientProxy.useSafeTextureRender() && player instanceof AbstractClientPlayer) {
            AbstractClientPlayer clientPlayer = (AbstractClientPlayer)player;
            Minecraft.func_71410_x().field_71446_o.func_110577_a(clientPlayer.func_110306_p());
        }
        this.mc.field_71424_I.func_76319_b();
    }
}

