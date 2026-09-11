/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.wardrobe.tab;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.awt.image.BufferedImage;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiIconButton;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.gui.wardrobe.GuiWardrobe;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.common.SkinHelper;
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientSkinWardrobeUpdate;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;
import riskyken.armourersWorkshop.common.wardrobe.ExtraColours;
import riskyken.armourersWorkshop.proxies.ClientProxy;

@SideOnly(value=Side.CLIENT)
public class GuiTabWardrobeColourSettings
extends GuiTabPanel {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LibGuiResources.WARDROBE_2);
    EntityPlayer entityPlayer;
    ExPropsPlayerSkinData propsPlayerSkinData;
    EquipmentWardrobeData equipmentWardrobeData;
    private ExtraColours.ExtraColourType selectingColourType = null;
    private Color selectingColour = null;
    private Color colourSkin;
    private Color colourHair;
    private GuiButtonExt selectSkinButton;
    private GuiButtonExt autoSkinButton;
    private GuiButtonExt selectHairButton;
    private GuiButtonExt autoHairButton;
    private GuiIconButton buttonSkinSelect;
    private GuiIconButton buttonSkinAuto;
    private GuiIconButton buttonHairSelect;
    private GuiIconButton buttonHairAuto;
    String guiName = "equipmentWardrobe";

    public GuiTabWardrobeColourSettings(int tabId, GuiScreen parent, EntityPlayer entityPlayer, ExPropsPlayerSkinData propsPlayerSkinData, EquipmentWardrobeData equipmentWardrobeData) {
        super(tabId, parent, false);
        this.entityPlayer = entityPlayer;
        this.propsPlayerSkinData = propsPlayerSkinData;
        this.equipmentWardrobeData = equipmentWardrobeData;
        this.getColours();
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        this.buttonSkinSelect = new GuiIconButton(this.parent, 0, 101, 36, 18, 18, GuiHelper.getLocalizedControlName(this.guiName, "selectSkin"), TEXTURE).setIconLocation(238, 0, 18, 18).setHorizontal(false);
        this.buttonSkinAuto = new GuiIconButton(this.parent, 0, 123, 36, 18, 18, GuiHelper.getLocalizedControlName(this.guiName, "autoSkin"), TEXTURE).setIconLocation(238, 76, 18, 18).setHorizontal(false);
        this.buttonHairSelect = new GuiIconButton(this.parent, 0, 177, 36, 18, 18, GuiHelper.getLocalizedControlName(this.guiName, "selectHair"), TEXTURE).setIconLocation(238, 0, 18, 18).setHorizontal(false);
        this.buttonHairAuto = new GuiIconButton(this.parent, 0, 199, 36, 18, 18, GuiHelper.getLocalizedControlName(this.guiName, "autoHair"), TEXTURE).setIconLocation(238, 76, 18, 18).setHorizontal(false);
        this.buttonList.add(this.buttonSkinSelect);
        this.buttonList.add(this.buttonSkinAuto);
        this.buttonList.add(this.buttonHairSelect);
        this.buttonList.add(this.buttonHairAuto);
    }

    private void getColours() {
        ExtraColours extraColours = this.propsPlayerSkinData.getEquipmentWardrobeData().getExtraColours();
        this.colourSkin = new Color(extraColours.getColour(ExtraColours.ExtraColourType.SKIN));
        this.colourHair = new Color(extraColours.getColour(ExtraColours.ExtraColourType.HAIR));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (button == 0 & this.selectingColourType != null) {
            EquipmentWardrobeData ewd = new EquipmentWardrobeData(this.equipmentWardrobeData);
            byte[] newColour = PaintingHelper.intToBytes(this.selectingColour.getRGB());
            newColour[3] = (byte)PaintType.NORMAL.getKey();
            ewd.getExtraColours().setColourBytes(this.selectingColourType, newColour);
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientSkinWardrobeUpdate(ewd));
            this.selectingColourType = null;
            this.buttonSkinSelect.setPressed(false);
            this.buttonHairSelect.setPressed(false);
        }
        super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        EquipmentWardrobeData ewd;
        if (button == this.buttonSkinSelect) {
            this.selectingColourType = ExtraColours.ExtraColourType.SKIN;
            this.buttonSkinSelect.setPressed(true);
        }
        if (button == this.buttonHairSelect) {
            this.selectingColourType = ExtraColours.ExtraColourType.HAIR;
            this.buttonHairSelect.setPressed(true);
        }
        this.equipmentWardrobeData = ClientProxy.equipmentWardrobeHandler.getEquipmentWardrobeData(new PlayerPointer(this.entityPlayer));
        if (button == this.buttonSkinAuto) {
            ewd = new EquipmentWardrobeData(this.equipmentWardrobeData);
            int newSkinColour = this.autoColour((AbstractClientPlayer)this.entityPlayer, ExtraColours.ExtraColourType.SKIN);
            ewd.getExtraColours().setColour(ExtraColours.ExtraColourType.SKIN, newSkinColour);
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientSkinWardrobeUpdate(ewd));
        }
        if (button == this.buttonHairAuto) {
            ewd = new EquipmentWardrobeData(this.equipmentWardrobeData);
            int newHairColour = this.autoColour((AbstractClientPlayer)this.entityPlayer, ExtraColours.ExtraColourType.HAIR);
            ewd.getExtraColours().setColour(ExtraColours.ExtraColourType.HAIR, newHairColour);
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientSkinWardrobeUpdate(ewd));
        }
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.drawColourDisplay(83, 38, this.colourSkin);
        this.drawColourDisplay(159, 38, this.colourHair);
        this.mc.field_71446_o.func_110577_a(TEXTURE);
        this.func_73729_b(this.x + 83, this.y + 88, 22, 0, 128, 56);
    }

    private void drawColourDisplay(int x, int y, Color colour) {
        float r = (float)colour.getRed() / 255.0f;
        float g = (float)colour.getGreen() / 255.0f;
        float b = (float)colour.getBlue() / 255.0f;
        this.drawColourDisplay(x, y, r, g, b);
    }

    private void drawColourDisplay(int x, int y, float r, float g, float b) {
        this.func_73729_b(this.x + x, this.y + y, 242, 180, 14, 14);
        GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
        this.func_73729_b(this.x + x + 1, this.y + y + 1, 243, 181, 12, 12);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    public void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "label.skinColour") + ":", 83, 26, 0x404040);
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "label.hairColour") + ":", 159, 26, 0x404040);
        this.getColours();
        if (this.selectingColourType == ExtraColours.ExtraColourType.SKIN & this.selectingColour != null) {
            this.colourSkin = this.selectingColour;
        }
        if (this.selectingColourType == ExtraColours.ExtraColourType.HAIR & this.selectingColour != null) {
            this.colourHair = this.selectingColour;
        }
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-this.x), (double)(-this.y), (double)0.0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ModRenderHelper.enableAlphaBlend();
        if (this.selectingColourType != null) {
            this.selectingColour = ((GuiWardrobe)this.parent).drawPlayerPreview(this.x, this.y, mouseX, mouseY, true);
        } else {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            ModRenderHelper.enableAlphaBlend();
            ((GuiWardrobe)this.parent).drawPlayerPreview(this.x, this.y, mouseX, mouseY, false);
        }
        GL11.glPopMatrix();
        for (int i = 0; i < this.buttonList.size(); ++i) {
            GuiButton button = (GuiButton)this.buttonList.get(i);
            if (!(button instanceof GuiIconButton)) continue;
            ((GuiIconButton)button).drawRollover(this.mc, mouseX - this.x, mouseY - this.y);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public int autoColour(AbstractClientPlayer player, ExtraColours.ExtraColourType type) {
        Color c;
        int iy;
        int ix;
        BufferedImage playerTexture = SkinHelper.getBufferedImageSkin(player);
        if (playerTexture == null) {
            return ExtraColours.COLOUR_HAIR_DEFAULT.getRGB();
        }
        int r = 0;
        int g = 0;
        int b = 0;
        if (type == ExtraColours.ExtraColourType.SKIN) {
            for (ix = 0; ix < 2; ++ix) {
                for (iy = 0; iy < 1; ++iy) {
                    c = new Color(playerTexture.getRGB(ix + 11, iy + 13));
                    r += c.getRed();
                    g += c.getGreen();
                    b += c.getBlue();
                }
            }
            r /= 2;
            g /= 2;
            b /= 2;
        }
        if (type == ExtraColours.ExtraColourType.HAIR) {
            for (ix = 0; ix < 2; ++ix) {
                for (iy = 0; iy < 1; ++iy) {
                    c = new Color(playerTexture.getRGB(ix + 11, iy + 3));
                    r += c.getRed();
                    g += c.getGreen();
                    b += c.getBlue();
                }
            }
            r /= 2;
            g /= 2;
            b /= 2;
        }
        if (type == ExtraColours.ExtraColourType.EYE) {
            Color c1 = new Color(playerTexture.getRGB(10, 13));
            Color c2 = new Color(playerTexture.getRGB(13, 13));
            r += c1.getRed();
            g += c1.getGreen();
            b += c1.getBlue();
            r += c2.getRed();
            g += c2.getGreen();
            b += c2.getBlue();
            r /= 2;
            g /= 2;
            b /= 2;
        }
        return new Color(r, g, b).getRGB();
    }
}

