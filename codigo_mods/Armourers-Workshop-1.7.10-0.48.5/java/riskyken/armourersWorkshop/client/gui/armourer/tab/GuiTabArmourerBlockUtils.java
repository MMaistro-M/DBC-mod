/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 */
package riskyken.armourersWorkshop.client.gui.armourer.tab;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.gui.AbstractGuiDialog;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.armourer.GuiArmourer;
import riskyken.armourersWorkshop.client.gui.armourer.dialog.GuiDialogClear;
import riskyken.armourersWorkshop.client.gui.armourer.dialog.GuiDialogCopy;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiArmourerBlockUtil;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

@SideOnly(value=Side.CLIENT)
public class GuiTabArmourerBlockUtils
extends GuiTabPanel
implements AbstractGuiDialog.IDialogCallback {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LibGuiResources.ARMOURER);
    private final TileEntityArmourer tileEntity;
    private GuiButtonExt buttonClear;
    private GuiButtonExt buttonCopy;

    public GuiTabArmourerBlockUtils(int tabId, GuiScreen parent) {
        super(tabId, parent, false);
        this.tileEntity = ((GuiArmourer)parent).tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        String guiName = this.tileEntity.func_145825_b();
        this.buttonClear = new GuiButtonExt(10, 10, 20, 70, 16, GuiHelper.getLocalizedControlName(guiName, "clear"));
        this.buttonCopy = new GuiButtonExt(11, 10, 40, 70, 16, GuiHelper.getLocalizedControlName(guiName, "copy"));
        this.buttonList.add(this.buttonClear);
        this.buttonList.add(this.buttonCopy);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        String guiName = this.tileEntity.func_145825_b();
        if (button == this.buttonClear) {
            ((GuiArmourer)this.parent).openDialog(new GuiDialogClear(this.parent, guiName + ".dialog.clear", (AbstractGuiDialog.IDialogCallback)this.parent, 190, 140, this.tileEntity.getSkinType(), this.tileEntity.getSkinProps()));
        }
        if (button == this.buttonCopy) {
            ((GuiArmourer)this.parent).openDialog(new GuiDialogCopy(this.parent, guiName + ".dialog.copy", (AbstractGuiDialog.IDialogCallback)this.parent, 190, 140, this.tileEntity.getSkinType(), this.tileEntity.getSkinProps()));
        }
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
        this.func_73729_b(this.x, this.y, 0, 0, this.width, this.height);
        this.func_73729_b(this.x + 7, this.y + 141, 7, 3, 162, 76);
    }

    @Override
    public void dialogResult(AbstractGuiDialog dialog, AbstractGuiDialog.DialogResult result) {
        if (result == AbstractGuiDialog.DialogResult.OK & dialog != null) {
            String tag;
            if (dialog instanceof GuiDialogClear && !StringUtils.func_151246_b((String)(tag = ((GuiDialogClear)dialog).getClearTag()))) {
                ISkinPartType partType = SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName(tag);
                boolean clearBlocks = ((GuiDialogClear)dialog).isClearBlocks();
                boolean clearPaint = ((GuiDialogClear)dialog).isClearPaint();
                boolean clearMarkers = ((GuiDialogClear)dialog).isClearMarkers();
                MessageClientGuiArmourerBlockUtil message = new MessageClientGuiArmourerBlockUtil("clear", partType, null, clearBlocks, clearPaint, clearMarkers);
                PacketHandler.networkWrapper.sendToServer((IMessage)message);
            }
            if (dialog instanceof GuiDialogCopy) {
                ISkinPartType srcPart = ((GuiDialogCopy)dialog).getSrcPart();
                ISkinPartType desPart = ((GuiDialogCopy)dialog).getDesPart();
                boolean mirror = ((GuiDialogCopy)dialog).isMirror();
                MessageClientGuiArmourerBlockUtil message = new MessageClientGuiArmourerBlockUtil("copy", srcPart, desPart, mirror, false, false);
                PacketHandler.networkWrapper.sendToServer((IMessage)message);
            }
        }
    }
}

