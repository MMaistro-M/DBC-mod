/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.mannequin;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.awt.image.BufferedImage;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequin;
import riskyken.armourersWorkshop.client.helper.MannequinTextureHelper;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.SkinHelper;
import riskyken.armourersWorkshop.common.data.Rectangle_I_2D;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;

@SideOnly(value=Side.CLIENT)
public class GuiMannequinTabSkinHair
extends GuiTabPanel {
    private static final ResourceLocation texture = new ResourceLocation(LibGuiResources.MANNEQUIN);
    private static final int TAB_WIDTH = 200;
    private static final int TAB_HEIGHT = 62;
    private GuiButtonExt selectSkinButton;
    private GuiButtonExt selectHairButton;
    private GuiButtonExt autoSkinButton;
    private GuiButtonExt autoHairButton;
    private final TileEntityMannequin tileEntity;
    public int skinColour;
    public int hairColour;
    public boolean selectingSkinColour = false;
    public boolean selectingHairColour = false;
    public Color hoverColour = null;

    public GuiMannequinTabSkinHair(int tabId, GuiScreen parent, TileEntityMannequin tileEntity) {
        super(tabId, parent, true);
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        this.skinColour = this.tileEntity.getSkinColour();
        this.hairColour = this.tileEntity.getHairColour();
        this.selectSkinButton = new GuiButtonExt(0, width / 2 - 90, 25, 80, 14, GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "selectSkin"));
        this.selectHairButton = new GuiButtonExt(0, width / 2 - 90, 40, 80, 14, GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "selectHair"));
        this.autoSkinButton = new GuiButtonExt(0, width / 2 + 10, 25, 80, 14, GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "autoSkin"));
        this.autoHairButton = new GuiButtonExt(0, width / 2 + 10, 40, 80, 14, GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "autoHair"));
        this.buttonList.add(this.selectSkinButton);
        this.buttonList.add(this.selectHairButton);
        this.buttonList.add(this.autoSkinButton);
        this.buttonList.add(this.autoHairButton);
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        Rectangle_I_2D rec = new Rectangle_I_2D(0, 0, 200, 62);
        rec.x = this.width / 2 - rec.width / 2;
        GuiUtils.drawContinuousTexturedBox((int)rec.x, (int)rec.y, (int)0, (int)200, (int)rec.width, (int)rec.height, (int)38, (int)38, (int)4, (float)this.field_73735_i);
        if (this.selectingSkinColour) {
            if (this.hoverColour != null) {
                this.drawColourBox(this.width / 2 - 7, 25, this.hoverColour.getRGB());
            }
        } else {
            this.drawColourBox(this.width / 2 - 7, 25, this.skinColour);
        }
        if (this.selectingHairColour) {
            if (this.hoverColour != null) {
                this.drawColourBox(this.width / 2 - 7, 40, this.hoverColour.getRGB());
            }
        } else {
            this.drawColourBox(this.width / 2 - 7, 40, this.hairColour);
        }
    }

    private void drawColourBox(int x, int y, int colour) {
        Color c = new Color(colour);
        this.func_73729_b(x, y, 38, 200, 14, 14);
        GL11.glColor3f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f));
        this.func_73729_b(x + 1, y + 1, 39, 201, 12, 12);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (this.selectingSkinColour) {
            this.selectingSkinColour = false;
            if (this.hoverColour != null) {
                this.skinColour = this.hoverColour.getRGB();
            }
            ((GuiMannequin)this.parent).tabOffset.sendData();
        }
        if (this.selectingHairColour) {
            this.selectingHairColour = false;
            if (this.hoverColour != null) {
                this.hairColour = this.hoverColour.getRGB();
            }
            ((GuiMannequin)this.parent).tabOffset.sendData();
        }
        super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        PlayerTexture playerTexture;
        if (button == this.selectSkinButton) {
            this.selectingSkinColour = true;
        }
        if (button == this.autoSkinButton) {
            playerTexture = MannequinTextureHelper.getMannequinTexture(this.tileEntity);
            this.skinColour = this.autoColourSkin(playerTexture.getResourceLocation());
            ((GuiMannequin)this.parent).tabOffset.sendData();
        }
        if (button == this.selectHairButton) {
            this.selectingHairColour = true;
        }
        if (button == this.autoHairButton) {
            playerTexture = MannequinTextureHelper.getMannequinTexture(this.tileEntity);
            this.hairColour = this.autoColourHair(playerTexture.getResourceLocation());
            ((GuiMannequin)this.parent).tabOffset.sendData();
        }
    }

    private BufferedImage getBufferedImage(ResourceLocation rl) {
        BufferedImage buff = SkinHelper.getBufferedImageSkin(rl);
        if (buff == null) {
            buff = SkinHelper.getBufferedImageSkin(AbstractClientPlayer.field_110314_b);
        }
        return buff;
    }

    private int autoColourHair(ResourceLocation rl) {
        BufferedImage playerSkin = this.getBufferedImage(rl);
        int r = 0;
        int g = 0;
        int b = 0;
        for (int ix = 0; ix < 2; ++ix) {
            for (int iy = 0; iy < 1; ++iy) {
                Color c = new Color(playerSkin.getRGB(ix + 11, iy + 3));
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
            }
        }
        return new Color(r /= 2, g /= 2, b /= 2).getRGB();
    }

    private int autoColourSkin(ResourceLocation rl) {
        BufferedImage playerSkin = this.getBufferedImage(rl);
        int r = 0;
        int g = 0;
        int b = 0;
        for (int ix = 0; ix < 2; ++ix) {
            for (int iy = 0; iy < 1; ++iy) {
                Color c = new Color(playerSkin.getRGB(ix + 11, iy + 13));
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
            }
        }
        return new Color(r /= 2, g /= 2, b /= 2).getRGB();
    }
}

