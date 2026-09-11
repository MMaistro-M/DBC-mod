/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.client.config.GuiSlider
 *  cpw.mods.fml.client.config.GuiSlider$ISlider
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.mannequin;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiCustomSlider;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.common.data.BipedRotations;
import riskyken.armourersWorkshop.common.data.Rectangle_I_2D;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiBipedRotations;

@SideOnly(value=Side.CLIENT)
public class GuiMannequinTabRotations
extends GuiTabPanel
implements GuiSlider.ISlider {
    private static final int ROT_MAN_TEX_WIDTH = 206;
    private static final int ROT_MAN_TEX_HEIGHT = 62;
    private static final int ROT_MAN_TEX_U = 0;
    private static final int ROT_MAN_TEX_V = 138;
    private final String inventoryName;
    private boolean guiLoaded = false;
    private GuiButtonExt resetRotsButton;
    private GuiButtonExt randomRotsButton;
    private BipedRotations bipedRotations;
    private BipedRotations lastBipedRotations;
    private GuiCustomSlider bipedRotXslider;
    private GuiCustomSlider bipedRotYslider;
    private GuiCustomSlider bipedRotZslider;
    private int activeBipedPart = 0;
    private Rectangle_I_2D[] bipedParts = new Rectangle_I_2D[6];

    public GuiMannequinTabRotations(int tabId, GuiScreen parent, String inventoryName, BipedRotations rots) {
        super(tabId, parent, true);
        this.inventoryName = inventoryName;
        this.updateRotationData(rots);
    }

    public void updateRotationData(BipedRotations rots) {
        this.bipedRotations = new BipedRotations();
        this.lastBipedRotations = new BipedRotations();
        NBTTagCompound compound = new NBTTagCompound();
        rots.saveNBTData(compound);
        this.bipedRotations.loadNBTData(compound);
        this.lastBipedRotations.loadNBTData(compound);
    }

    public BipedRotations getBipedRotations() {
        return this.bipedRotations;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        this.guiLoaded = false;
        int recX = (int)((float)width / 2.0f - 103.0f);
        this.bipedParts[0] = new Rectangle_I_2D(recX + 183, 18, 8, 8);
        this.bipedParts[1] = new Rectangle_I_2D(recX + 183, 27, 8, 12);
        this.bipedParts[2] = new Rectangle_I_2D(recX + 178, 27, 4, 12);
        this.bipedParts[3] = new Rectangle_I_2D(recX + 192, 27, 4, 12);
        this.bipedParts[4] = new Rectangle_I_2D(recX + 182, 40, 4, 12);
        this.bipedParts[5] = new Rectangle_I_2D(recX + 188, 40, 4, 12);
        this.resetRotsButton = new GuiButtonExt(0, width / 2 + 15, 25, 50, 14, GuiHelper.getLocalizedControlName(this.inventoryName, "reset"));
        this.randomRotsButton = new GuiButtonExt(0, width / 2 + 15, 40, 50, 14, GuiHelper.getLocalizedControlName(this.inventoryName, "random"));
        this.bipedRotXslider = new GuiCustomSlider(0, (int)((float)width / 2.0f - 103.0f) + 10, 25, 100, 10, "X: ", "", -180.0, 180.0, 0.0, true, true, this);
        this.bipedRotYslider = new GuiCustomSlider(0, (int)((float)width / 2.0f - 103.0f) + 10, 35, 100, 10, "Y: ", "", -180.0, 180.0, 0.0, true, true, this);
        this.bipedRotZslider = new GuiCustomSlider(0, (int)((float)width / 2.0f - 103.0f) + 10, 45, 100, 10, "Z: ", "", -180.0, 180.0, 0.0, true, true, this);
        if (this.bipedRotations != null) {
            this.setSliderValue(this.bipedRotXslider, Math.toDegrees(-this.bipedRotations.head.rotationX));
            this.setSliderValue(this.bipedRotYslider, Math.toDegrees(-this.bipedRotations.head.rotationY));
            this.setSliderValue(this.bipedRotZslider, Math.toDegrees(-this.bipedRotations.head.rotationZ));
        }
        this.buttonList.add(this.resetRotsButton);
        this.buttonList.add(this.randomRotsButton);
        this.buttonList.add(this.bipedRotXslider);
        this.buttonList.add(this.bipedRotYslider);
        this.buttonList.add(this.bipedRotZslider);
        this.bipedPartChange(0);
        this.guiLoaded = true;
    }

    private void setSliderValue(GuiCustomSlider slider, double value) {
        slider.setValue(value);
        slider.precision = 2;
        slider.updateSlider();
    }

    private void bipedPartChange(int partIndex) {
        this.activeBipedPart = partIndex;
        BipedRotations.BipedPart part = this.bipedRotations.getPartForIndex(this.activeBipedPart);
        this.guiLoaded = false;
        this.bipedRotXslider.setValue(Math.toDegrees(-part.rotationX));
        this.bipedRotYslider.setValue(Math.toDegrees(-part.rotationY));
        this.bipedRotZslider.setValue(Math.toDegrees(-part.rotationZ));
        this.bipedRotXslider.updateSlider();
        this.bipedRotYslider.updateSlider();
        this.bipedRotZslider.updateSlider();
        this.guiLoaded = true;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        for (int i = 0; i < this.bipedParts.length; ++i) {
            if (!this.bipedParts[i].isInside(mouseX, mouseY)) continue;
            this.bipedPartChange(i);
            break;
        }
        super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.resetRotsButton) {
            this.guiLoaded = false;
            this.bipedRotations.resetRotations();
            this.bipedPartChange(this.activeBipedPart);
            this.guiLoaded = true;
            this.checkAndSendRotationValues();
        }
        if (button == this.randomRotsButton) {
            this.guiLoaded = false;
            Random rnd = new Random();
            for (int i = 0; i < this.bipedParts.length; ++i) {
                BipedRotations.BipedPart part = this.bipedRotations.getPartForIndex(i);
                if (part == this.bipedRotations.chest) continue;
                part.rotationX = (float)Math.toRadians(rnd.nextFloat() * 180.0f - 90.0f);
                part.rotationY = (float)Math.toRadians(rnd.nextFloat() * 180.0f - 90.0f);
                part.rotationZ = (float)Math.toRadians(rnd.nextFloat() * 180.0f - 90.0f);
                this.bipedPartChange(this.activeBipedPart);
            }
            this.guiLoaded = true;
            this.checkAndSendRotationValues();
        }
    }

    public void checkAndSendRotationValues() {
        BipedRotations.BipedPart activePart = this.bipedRotations.getPartForIndex(this.activeBipedPart);
        activePart.setRotationsDegrees((float)(-this.bipedRotXslider.getValue()), (float)(-this.bipedRotYslider.getValue()), (float)(-this.bipedRotZslider.getValue()));
        if (!this.bipedRotations.equals(this.lastBipedRotations)) {
            NBTTagCompound compound = new NBTTagCompound();
            this.bipedRotations.saveNBTData(compound);
            this.lastBipedRotations.loadNBTData(compound);
            MessageClientGuiBipedRotations message = new MessageClientGuiBipedRotations(this.bipedRotations);
            PacketHandler.networkWrapper.sendToServer((IMessage)message);
        }
    }

    public void onChangeSliderValue(GuiSlider slider) {
        if (!this.guiLoaded) {
            return;
        }
        this.checkAndSendRotationValues();
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        int center = (int)((float)this.width / 2.0f);
        this.func_73729_b(center - 103, 0, 0, 138, 206, 62);
        for (int i = 0; i < this.bipedParts.length; ++i) {
            int colour = -855638272;
            if (this.bipedParts[i].isInside(mouseX, mouseY)) {
                colour = -855638017;
            }
            if (i == this.activeBipedPart) {
                colour = -872349952;
            }
            GuiMannequinTabRotations.func_73734_a((int)this.bipedParts[i].x, (int)this.bipedParts[i].y, (int)(this.bipedParts[i].x + this.bipedParts[i].width), (int)(this.bipedParts[i].y + this.bipedParts[i].height), (int)colour);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    public void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
    }
}

