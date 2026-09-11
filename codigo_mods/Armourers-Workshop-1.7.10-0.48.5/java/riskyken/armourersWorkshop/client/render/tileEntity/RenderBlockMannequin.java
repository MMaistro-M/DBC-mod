/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.tileEntity;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.nio.FloatBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequin;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequinTabSkinHair;
import riskyken.armourersWorkshop.client.helper.MannequinTextureHelper;
import riskyken.armourersWorkshop.client.model.ModelHelper;
import riskyken.armourersWorkshop.client.model.ModelMannequin;
import riskyken.armourersWorkshop.client.render.EntityTextureInfo;
import riskyken.armourersWorkshop.client.render.IRenderBuffer;
import riskyken.armourersWorkshop.client.render.MannequinFakePlayer;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.RenderBridge;
import riskyken.armourersWorkshop.client.render.tileEntity.RenderBlockMannequinItems;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.ApiRegistrar;
import riskyken.armourersWorkshop.common.Contributors;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.data.BipedRotations;
import riskyken.armourersWorkshop.common.inventory.MannequinSlotType;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;
import riskyken.armourersWorkshop.utils.HolidayHelper;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

@SideOnly(value=Side.CLIENT)
public class RenderBlockMannequin
extends TileEntitySpecialRenderer {
    private static final ResourceLocation circle = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/other/nanohaCircle.png");
    private static RenderBlockMannequinItems renderItems = new RenderBlockMannequinItems();
    private static boolean isHalloweenSeason;
    private static boolean isHalloween;
    private static final float SCALE = 0.0625f;
    private static long lastTextureBuild;
    private final ModelMannequin modelSteve;
    private final ModelMannequin modelAlex;
    private MannequinFakePlayer mannequinFakePlayer;
    private final RenderPlayer renderPlayer;
    private final Minecraft mc;

    public RenderBlockMannequin() {
        this.renderPlayer = (RenderPlayer)RenderManager.field_78727_a.field_78729_o.get(EntityPlayer.class);
        this.mc = Minecraft.func_71410_x();
        this.modelSteve = new ModelMannequin(false);
        this.modelAlex = new ModelMannequin(true);
    }

    public void renderTileEntityAt(TileEntityMannequin te, double x, double y, double z, float partialTickTime) {
        Contributors.Contributor contributor;
        this.mc.field_71424_I.func_76320_a("armourersMannequin");
        this.mc.field_71424_I.func_76320_a("holidayCheck");
        isHalloweenSeason = HolidayHelper.halloween_season.isHolidayActive();
        isHalloween = HolidayHelper.halloween.isHolidayActive();
        MannequinFakePlayer fakePlayer = te.getFakePlayer();
        this.mc.field_71424_I.func_76318_c("move");
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)8192);
        GL11.glPushAttrib((int)64);
        GL11.glEnable((int)32826);
        ModRenderHelper.disableAlphaBlend();
        int rotaion = te.getRotation();
        GL11.glTranslated((double)(x + 0.5 + (double)te.getOffsetX()), (double)(y + 1.0 + (double)te.getOffsetY()), (double)(z + 0.5 + (double)te.getOffsetZ()));
        BipedRotations rots = te.getBipedRotations();
        GL11.glRotated((double)Math.toDegrees(rots.chest.rotationX), (double)1.0, (double)0.0, (double)0.0);
        GL11.glRotated((double)Math.toDegrees(rots.chest.rotationY), (double)0.0, (double)1.0, (double)0.0);
        GL11.glRotated((double)Math.toDegrees(rots.chest.rotationZ), (double)0.0, (double)0.0, (double)1.0);
        GL11.glTranslated((double)0.0, (double)0.5, (double)0.0);
        GL11.glScalef((float)0.9375f, (float)0.9375f, (float)0.9375f);
        GL11.glTranslated((double)0.0, (double)-0.1f, (double)0.0);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glRotatef((float)((float)rotaion * 22.5f), (float)0.0f, (float)1.0f, (float)0.0f);
        if (te.getIsDoll()) {
            float dollScale = 0.5f;
            GL11.glScalef((float)dollScale, (float)dollScale, (float)dollScale);
            GL11.glTranslatef((float)0.0f, (float)1.5f, (float)0.0f);
        }
        this.mc.field_71424_I.func_76318_c("getTexture");
        boolean slimModel = false;
        PlayerTexture playerTexture = MannequinTextureHelper.getMannequinTexture(te);
        ResourceLocation rl = playerTexture.getResourceLocation();
        slimModel = playerTexture.isSlimModel();
        boolean download = playerTexture.isDownloaded();
        ModelMannequin model = this.modelSteve;
        if (slimModel) {
            model = this.modelAlex;
        }
        this.mc.field_71424_I.func_76318_c("fakePlayer");
        if (this.mannequinFakePlayer == null) {
            this.mannequinFakePlayer = new MannequinFakePlayer(te.func_145831_w(), new GameProfile(null, "[Mannequin]"));
            this.mannequinFakePlayer.field_70165_t = x;
            this.mannequinFakePlayer.field_70163_u = y;
            this.mannequinFakePlayer.field_70161_v = z;
            this.mannequinFakePlayer.field_70169_q = x;
            this.mannequinFakePlayer.field_70167_r = y;
            this.mannequinFakePlayer.field_70166_s = z;
        }
        if (te.getGameProfile() != null) {
            if (te.func_145831_w() != null & fakePlayer != null) {
                fakePlayer.func_145769_d(te.field_145851_c * 31 * -te.field_145849_e);
                fakePlayer.field_70160_al = te.isFlying();
                fakePlayer.field_71075_bZ.field_75100_b = te.isFlying();
            }
        } else {
            this.mannequinFakePlayer.func_145769_d(te.field_145851_c * 31 * -te.field_145849_e);
            this.mannequinFakePlayer.field_70160_al = te.isFlying();
            this.mannequinFakePlayer.field_71075_bZ.field_75100_b = te.isFlying();
        }
        if (fakePlayer != null) {
            fakePlayer.func_145769_d(te.field_145851_c * 31 * -te.field_145849_e);
            fakePlayer.field_70160_al = te.isFlying();
            fakePlayer.field_71075_bZ.field_75100_b = te.isFlying();
        }
        if (te.getBipedRotations() != null) {
            te.getBipedRotations().applyRotationsToBiped(model);
            te.getBipedRotations().applyRotationsToBiped(this.renderPlayer.field_77111_i);
            te.getBipedRotations().applyRotationsToBiped(this.renderPlayer.field_77108_b);
        }
        ApiRegistrar.INSTANCE.onRenderMannequin(te, te.getGameProfile());
        model.field_78112_f.func_78793_a(-5.0f, 2.0f, 0.0f);
        model.field_78113_g.func_78793_a(5.0f, 2.0f, 0.0f);
        model.field_78116_c.func_78793_a(0.0f, 0.0f, 0.0f);
        model.field_78114_d.func_78793_a(0.0f, 0.0f, 0.0f);
        model.field_78123_h.func_78793_a(-1.9f, 12.0f, 0.0f);
        model.field_78124_i.func_78793_a(1.9f, 12.0f, 0.0f);
        rots.applyRotationsToBiped(model);
        model.field_78115_e.field_78795_f = 0.0f;
        model.field_78115_e.field_78796_g = 0.0f;
        model.field_78115_e.field_78808_h = 0.0f;
        if (isHalloween) {
            double dX = -x - 0.5;
            double dY = -y - (double)1.72f;
            double dZ = -z - 0.5;
            double yaw = Math.atan2(dZ, dX);
            double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
            model.field_78116_c.field_78795_f = (float)(pitch += 1.5707963267948966);
            model.field_78116_c.field_78796_g = (float)(yaw -= Math.toRadians((float)rotaion * 22.5f - 90.0f));
            model.field_78114_d.field_78795_f = model.field_78116_c.field_78795_f;
            model.field_78114_d.field_78796_g = model.field_78116_c.field_78796_g;
        }
        this.mc.field_71424_I.func_76318_c("textureBuild");
        if (te.haveSkinsUpdated()) {
            te.sp = this.getSkinPointers(te);
        }
        if (te.sp != null) {
            ISkinPointer[] sp = te.sp;
            Skin[] skins = new Skin[sp.length];
            ISkinDye[] dyes = new ISkinDye[sp.length];
            boolean hasPaintedSkin = false;
            for (int i = 0; i < sp.length; ++i) {
                if (sp[i] == null) continue;
                skins[i] = ClientSkinCache.INSTANCE.getSkin(sp[i]);
                dyes[i] = sp[i].getSkinDye();
                if (skins[i] == null) continue;
                Skin skin = skins[i];
                if (skin.hasPaintData()) {
                    hasPaintedSkin = true;
                    break;
                }
                if (SkinProperties.PROP_MODEL_OVERRIDE_HEAD.getValue(skin.getProperties()).booleanValue()) {
                    hasPaintedSkin = true;
                    break;
                }
                if (SkinProperties.PROP_MODEL_OVERRIDE_CHEST.getValue(skin.getProperties()).booleanValue()) {
                    hasPaintedSkin = true;
                    break;
                }
                if (SkinProperties.PROP_MODEL_OVERRIDE_ARM_LEFT.getValue(skin.getProperties()).booleanValue()) {
                    hasPaintedSkin = true;
                    break;
                }
                if (SkinProperties.PROP_MODEL_OVERRIDE_ARM_RIGHT.getValue(skin.getProperties()).booleanValue()) {
                    hasPaintedSkin = true;
                    break;
                }
                if (SkinProperties.PROP_MODEL_OVERRIDE_LEG_LEFT.getValue(skin.getProperties()).booleanValue()) {
                    hasPaintedSkin = true;
                    break;
                }
                if (!SkinProperties.PROP_MODEL_OVERRIDE_ARM_RIGHT.getValue(skin.getProperties()).booleanValue()) continue;
                hasPaintedSkin = true;
                break;
            }
            if (hasPaintedSkin) {
                if (te.skinTexture == null) {
                    te.skinTexture = new EntityTextureInfo();
                }
                te.skinTexture.updateTexture(rl);
                te.skinTexture.updateSkinColour(te.getSkinColour());
                te.skinTexture.updateHairColour(te.getHairColour());
                te.skinTexture.updateSkins(skins);
                te.skinTexture.updateDyes(dyes);
                if (te.skinTexture.getNeedsUpdate()) {
                    if (lastTextureBuild + 200L < System.currentTimeMillis()) {
                        lastTextureBuild = System.currentTimeMillis();
                        rl = te.skinTexture.preRender();
                    }
                } else {
                    rl = te.skinTexture.preRender();
                }
            }
        }
        this.mc.field_71424_I.func_76318_c("textureBind");
        this.func_147499_a(rl);
        this.mc.field_71424_I.func_76318_c("selectModelRender");
        te.getBipedRotations().hasCustomHead = this.hasCustomHead(te);
        boolean selectingColour = false;
        GuiMannequinTabSkinHair tabSkinHair = null;
        if (this.mc.field_71462_r instanceof GuiMannequin) {
            GuiMannequin screen = (GuiMannequin)this.mc.field_71462_r;
            if (screen.tileEntity == te) {
                tabSkinHair = screen.tabSkinAndHair;
                if (tabSkinHair.selectingSkinColour | tabSkinHair.selectingHairColour) {
                    selectingColour = true;
                }
            }
        }
        if (selectingColour) {
            GL11.glDisable((int)2896);
            if (te.isVisible() & (te.getGameProfile() == null || !te.getGameProfile().getName().equalsIgnoreCase("null"))) {
                this.renderModel(te, model, fakePlayer);
            }
            tabSkinHair.hoverColour = this.getColourAtPos(Mouse.getX(), Mouse.getY());
            GL11.glEnable((int)2896);
        }
        this.mc.field_71424_I.func_76318_c("modelRender");
        if (te.isVisible() & (te.getGameProfile() == null || !te.getGameProfile().getName().equalsIgnoreCase("null"))) {
            long time = System.currentTimeMillis();
            int fadeTime = 1000;
            int fade = (int)(time - playerTexture.getDownloadTime());
            if (playerTexture.isDownloaded() & fade < fadeTime) {
                this.func_147499_a(AbstractClientPlayer.field_110314_b);
                this.renderModel(te, model, fakePlayer);
                this.func_147499_a(rl);
                ModRenderHelper.enableAlphaBlend();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((float)fade / 1000.0f));
                GL11.glEnable((int)32823);
                GL11.glPolygonOffset((float)-3.0f, (float)-3.0f);
                this.renderModel(te, model, fakePlayer);
                GL11.glPolygonOffset((float)0.0f, (float)0.0f);
                GL11.glDisable((int)32823);
                ModRenderHelper.disableAlphaBlend();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            } else {
                this.renderModel(te, model, fakePlayer);
            }
        }
        this.mc.field_71424_I.func_76318_c("earRender");
        if (te.getGameProfile() != null && te.getGameProfile().getName().equals("deadmau5")) {
            GL11.glPushMatrix();
            GL11.glRotated((double)Math.toDegrees(model.field_78116_c.field_78808_h), (double)0.0, (double)0.0, (double)1.0);
            GL11.glRotated((double)Math.toDegrees(model.field_78116_c.field_78796_g), (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotated((double)Math.toDegrees(model.field_78116_c.field_78795_f), (double)1.0, (double)0.0, (double)0.0);
            GL11.glTranslated((double)-0.34375, (double)0.0, (double)0.0);
            GL11.glTranslated((double)0.0, (double)-0.40625, (double)0.0);
            model.field_78121_j.func_78785_a(0.0625f);
            GL11.glTranslated((double)0.6875, (double)0.0, (double)0.0);
            model.field_78121_j.func_78785_a(0.0625f);
            GL11.glPopMatrix();
        }
        this.mc.field_71424_I.func_76318_c("magicCircle");
        if (te.isRenderExtras() & te.isVisible() && (contributor = Contributors.INSTANCE.getContributor(te.getGameProfile())) != null) {
            int offset = te.field_145851_c * te.field_145848_d * te.field_145849_e;
            this.renderMagicCircle(contributor.r, contributor.g, contributor.b, partialTickTime, offset, te.getBipedRotations().isChild);
        }
        this.mc.field_71424_I.func_76318_c("equippedItems");
        double distance = Minecraft.func_71410_x().field_71439_g.func_70011_f((double)((float)te.field_145851_c + 0.5f), (double)((float)te.field_145848_d + 0.5f), (double)((float)te.field_145849_e + 0.5f));
        if (distance <= (double)ConfigHandlerClient.mannequinMaxEquipmentRenderDistance) {
            this.renderEquippedItems(te, fakePlayer, model, distance);
        }
        this.mc.field_71424_I.func_76318_c("reset");
        model.field_78124_i.field_78808_h = 0.0f;
        model.field_78123_h.field_78808_h = 0.0f;
        model.field_78116_c.field_78808_h = 0.0f;
        model.field_78114_d.field_78808_h = 0.0f;
        this.renderPlayer.field_77111_i.field_78124_i.field_78808_h = 0.0f;
        this.renderPlayer.field_77111_i.field_78123_h.field_78808_h = 0.0f;
        this.renderPlayer.field_77111_i.field_78116_c.field_78808_h = 0.0f;
        this.renderPlayer.field_77111_i.field_78114_d.field_78808_h = 0.0f;
        this.renderPlayer.field_77108_b.field_78124_i.field_78808_h = 0.0f;
        this.renderPlayer.field_77108_b.field_78123_h.field_78808_h = 0.0f;
        this.renderPlayer.field_77108_b.field_78116_c.field_78808_h = 0.0f;
        this.renderPlayer.field_77108_b.field_78114_d.field_78808_h = 0.0f;
        this.mc.field_71424_I.func_76318_c("pop");
        GL11.glPopAttrib();
        GL11.glPopAttrib();
        GL11.glPopMatrix();
        this.mc.field_71424_I.func_76319_b();
        this.mc.field_71424_I.func_76319_b();
    }

    private void renderMagicCircle(byte r, byte g, byte b, float partialTickTime, int offset, boolean isChild) {
        GL11.glPushMatrix();
        if (isChild) {
            ModelHelper.enableChildModelScale(false, 0.0625f);
        }
        GL11.glColor4ub((byte)r, (byte)g, (byte)b, (byte)-1);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)0.0f, (float)1.48f, (float)0.0f);
        float circleScale = 2.0f;
        GL11.glScalef((float)circleScale, (float)circleScale, (float)circleScale);
        float rotation = (float)((double)(this.mc.field_71441_e.func_82737_E() + (long)offset) / (double)0.8f % 360.0) + partialTickTime;
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        ModRenderHelper.disableLighting();
        ModRenderHelper.enableAlphaBlend();
        this.func_147499_a(circle);
        IRenderBuffer renderBuffer = RenderBridge.INSTANCE;
        renderBuffer.startDrawingQuads();
        renderBuffer.addVertexWithUV(-1.0, 0.0, -1.0, 1.0, 0.0);
        renderBuffer.addVertexWithUV(1.0, 0.0, -1.0, 0.0, 0.0);
        renderBuffer.addVertexWithUV(1.0, 0.0, 1.0, 0.0, 1.0);
        renderBuffer.addVertexWithUV(-1.0, 0.0, 1.0, 1.0, 1.0);
        renderBuffer.draw();
        ModRenderHelper.disableAlphaBlend();
        ModRenderHelper.enableLighting();
        GL11.glEnable((int)2884);
        GL11.glEnable((int)2896);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (isChild) {
            ModelHelper.disableChildModelScale();
        }
        GL11.glPopMatrix();
    }

    private void renderModel(TileEntityMannequin te, ModelBiped targetBiped, MannequinFakePlayer fakePlayer) {
        if (!this.hasCustomHead(te)) {
            if (te.getBipedRotations().isChild) {
                ModelHelper.enableChildModelScale(true, 0.0625f);
            }
            targetBiped.field_78116_c.func_78785_a(0.0625f);
            GL11.glDisable((int)2884);
            targetBiped.field_78114_d.func_78785_a(0.0625f);
            GL11.glEnable((int)2884);
            if (te.getBipedRotations().isChild) {
                ModelHelper.disableChildModelScale();
            }
        }
        if (te.getBipedRotations().isChild) {
            ModelHelper.enableChildModelScale(false, 0.0625f);
        }
        targetBiped.field_78115_e.func_78785_a(0.0625f);
        targetBiped.field_78112_f.func_78785_a(0.0625f);
        targetBiped.field_78113_g.func_78785_a(0.0625f);
        targetBiped.field_78123_h.func_78785_a(0.0625f);
        targetBiped.field_78124_i.func_78785_a(0.0625f);
        if (te.getBipedRotations().isChild) {
            ModelHelper.disableChildModelScale();
        }
    }

    private Color getColourAtPos(int x, int y) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer((int)3);
        GL11.glReadPixels((int)x, (int)y, (int)1, (int)1, (int)6407, (int)5126, (FloatBuffer)buffer);
        int r = Math.round(buffer.get() * 255.0f);
        int g = Math.round(buffer.get() * 255.0f);
        int b = Math.round(buffer.get() * 255.0f);
        return new Color(r, g, b);
    }

    private void renderEquippedItems(TileEntityMannequin te, MannequinFakePlayer fakePlayer, ModelMannequin targetBiped, double distance) {
        RenderItem ri = (RenderItem)RenderManager.field_78727_a.field_78729_o.get(EntityItem.class);
        MannequinFakePlayer renderEntity = fakePlayer;
        if (renderEntity == null) {
            renderEntity = this.mannequinFakePlayer;
        }
        Color skinColour = new Color(te.getSkinColour());
        Color hairColour = new Color(te.getHairColour());
        byte[] extraColours = new byte[]{(byte)skinColour.getRed(), (byte)skinColour.getGreen(), (byte)skinColour.getBlue(), (byte)hairColour.getRed(), (byte)hairColour.getGreen(), (byte)hairColour.getBlue()};
        for (int i = 0; i < te.func_70302_i_(); ++i) {
            ItemStack stack = te.func_70301_a(i);
            if (renderEntity == null) continue;
            if (i == 0 & isHalloweenSeason) {
                this.renderEquippedItem(renderEntity, new ItemStack(Blocks.field_150428_aP), targetBiped, i, extraColours, distance, te.getBipedRotations());
                continue;
            }
            if (stack == null) continue;
            this.renderEquippedItem(renderEntity, stack, targetBiped, i, extraColours, distance, te.getBipedRotations());
        }
    }

    public ItemStack getStackInMannequinSlot(IInventory inventory, MannequinSlotType slot) {
        return inventory.func_70301_a(slot.ordinal());
    }

    private boolean hasCustomHead(IInventory inventory) {
        Skin skin;
        ItemStack stack = this.getStackInMannequinSlot(inventory, MannequinSlotType.HEAD);
        if (stack != null && stack.func_77973_b() instanceof ItemBlock) {
            return true;
        }
        if (isHalloweenSeason) {
            return true;
        }
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        if (skinPointer != null && (skin = ClientSkinCache.INSTANCE.getSkin(skinPointer, false)) != null) {
            if (SkinProperties.PROP_MODEL_OVERRIDE_HEAD.getValue(skin.getProperties()).booleanValue()) {
                return true;
            }
            if (SkinProperties.PROP_MODEL_HIDE_OVERLAY_HEAD.getValue(skin.getProperties()).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    private void renderEquippedItem(MannequinFakePlayer fakePlayer, ItemStack stack, ModelMannequin targetBiped, int slot, byte[] extraColours, double distance, BipedRotations rots) {
        Item targetItem = stack.func_77973_b();
        RenderManager rm = RenderManager.field_78727_a;
        String[] slotName = new String[]{"head", "chest", "legs", "unused", "feet", "rightArm", "leftArm"};
        this.mc.field_71424_I.func_76320_a(slotName[slot %= 7]);
        GL11.glPushMatrix();
        boolean isChild = targetBiped.field_78091_s;
        if (isChild) {
            ModelHelper.enableChildModelScale(slot == 0, 0.0625f);
        }
        targetBiped.field_78091_s = false;
        switch (slot) {
            case 0: {
                renderItems.renderHeadStack(fakePlayer, stack, targetBiped, rm, extraColours, distance);
                if (rots == null) break;
                rots.applyRotationsToBiped(targetBiped);
                break;
            }
            case 1: {
                renderItems.renderChestStack(fakePlayer, stack, targetBiped, rm, extraColours, distance);
                if (rots == null) break;
                rots.applyRotationsToBiped(targetBiped);
                break;
            }
            case 2: {
                renderItems.renderLegsStack(fakePlayer, stack, targetBiped, rm, extraColours, distance);
                if (rots == null) break;
                rots.applyRotationsToBiped(targetBiped);
                break;
            }
            case 3: {
                renderItems.renderFeetStack(fakePlayer, stack, targetBiped, rm, extraColours, distance);
                if (rots == null) break;
                rots.applyRotationsToBiped(targetBiped);
                break;
            }
            case 4: {
                renderItems.renderRightArmStack(fakePlayer, stack, targetBiped, rm, extraColours, distance);
                if (rots == null) break;
                rots.applyRotationsToBiped(targetBiped);
                break;
            }
            case 5: {
                renderItems.renderLeftArmStack(fakePlayer, stack, targetBiped, rm, extraColours, distance);
                if (rots == null) break;
                rots.applyRotationsToBiped(targetBiped);
                break;
            }
            case 6: {
                renderItems.renderWingsStack(fakePlayer, stack, targetBiped, rm, extraColours, distance);
            }
        }
        targetBiped.field_78091_s = isChild;
        if (isChild) {
            ModelHelper.disableChildModelScale();
        }
        GL11.glPopMatrix();
        this.mc.field_71424_I.func_76319_b();
    }

    private ISkinPointer[] getSkinPointers(TileEntityMannequin te) {
        ISkinPointer[] skinPointers = new ISkinPointer[20];
        for (int i = 0; i < 5; ++i) {
            skinPointers[0 + i * 4] = this.getSkinPointerForSlot(te, 0 + i * 7);
            skinPointers[1 + i * 4] = this.getSkinPointerForSlot(te, 1 + i * 7);
            skinPointers[2 + i * 4] = this.getSkinPointerForSlot(te, 2 + i * 7);
            skinPointers[3 + i * 4] = this.getSkinPointerForSlot(te, 3 + i * 7);
        }
        return skinPointers;
    }

    private ISkinPointer getSkinPointerForSlot(TileEntityMannequin te, MannequinSlotType slotType) {
        return SkinNBTHelper.getSkinPointerFromStack(this.getStackInMannequinSlot(te, slotType));
    }

    private ISkinPointer getSkinPointerForSlot(TileEntityMannequin te, int slotIndex) {
        return SkinNBTHelper.getSkinPointerFromStack(te.func_70301_a(slotIndex));
    }

    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float partialTickTime) {
        this.renderTileEntityAt((TileEntityMannequin)tileEntity, x, y, z, partialTickTime);
    }

    static {
        lastTextureBuild = 0L;
    }
}

