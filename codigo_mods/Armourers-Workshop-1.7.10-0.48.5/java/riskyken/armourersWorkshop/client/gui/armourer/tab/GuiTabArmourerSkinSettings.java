/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiSlider
 *  cpw.mods.fml.client.config.GuiSlider$ISlider
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 */
package riskyken.armourersWorkshop.client.gui.armourer.tab;

import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.armourer.GuiArmourer;
import riskyken.armourersWorkshop.client.gui.controls.GuiCheckBox;
import riskyken.armourersWorkshop.client.gui.controls.GuiCustomSlider;
import riskyken.armourersWorkshop.client.gui.controls.GuiDropDownList;
import riskyken.armourersWorkshop.client.gui.controls.GuiInventorySize;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiSetArmourerSkinProps;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.skin.type.wings.SkinWings;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

@SideOnly(value=Side.CLIENT)
public class GuiTabArmourerSkinSettings
extends GuiTabPanel
implements GuiSlider.ISlider,
GuiDropDownList.IDropDownListCallback {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LibGuiResources.ARMOURER);
    private static final String DEGREE = "\u00b0";
    private final TileEntityArmourer tileEntity;
    private static final int SYNC_ID_BLOCK = 15;
    private static final int SYNC_ID_MODEL = 16;
    private static final int SYNC_ID_WINGS = 17;
    private GuiCheckBox checkBlockGlowing;
    private GuiCheckBox checkBlockLadder;
    private GuiCheckBox checkBlockNoCollision;
    private GuiCheckBox checkBlockSeat;
    private GuiCheckBox checkBlockMultiblock;
    private GuiCheckBox checkBlockBed;
    private GuiCheckBox checkBlockInventory;
    private GuiInventorySize inventorySize;
    private GuiCheckBox checkBlockEnderInventory;
    private GuiCustomSlider sliderWingIdleSpeed;
    private GuiCustomSlider sliderWingFlyingSpeed;
    private GuiCustomSlider sliderWingMinAngle;
    private GuiCustomSlider sliderWingMaxAngle;
    private GuiCheckBox checkModelOverrideHead;
    private GuiCheckBox checkModelOverrideChest;
    private GuiCheckBox checkModelOverrideArmLeft;
    private GuiCheckBox checkModelOverrideArmRight;
    private GuiCheckBox checkModelOverrideLegLeft;
    private GuiCheckBox checkModelOverrideLegRight;
    private GuiCheckBox checkHideOverlayHead;
    private GuiCheckBox checkHideOverlayChest;
    private GuiCheckBox checkHideOverlayArmLeft;
    private GuiCheckBox checkHideOverlayArmRight;
    private GuiCheckBox checkHideOverlayLegLeft;
    private GuiCheckBox checkHideOverlayLegRight;
    private GuiCheckBox checkLimitLimbMovement;
    private GuiDropDownList dropDownMovementType;
    private boolean resetting;

    public GuiTabArmourerSkinSettings(int tabId, GuiScreen parent) {
        super(tabId, parent, false);
        this.tileEntity = ((GuiArmourer)parent).tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        String guiName = this.tileEntity.func_145825_b();
        this.buttonList.clear();
        SkinProperties skinProps = this.tileEntity.getSkinProps();
        this.checkBlockGlowing = new GuiCheckBox(15, 10, 20, GuiHelper.getLocalizedControlName(guiName, "glowing"), SkinProperties.PROP_BLOCK_GLOWING.getValue(skinProps));
        this.checkBlockLadder = new GuiCheckBox(15, 10, 35, GuiHelper.getLocalizedControlName(guiName, "ladder"), SkinProperties.PROP_BLOCK_LADDER.getValue(skinProps));
        this.checkBlockNoCollision = new GuiCheckBox(15, 10, 50, GuiHelper.getLocalizedControlName(guiName, "noCollision"), SkinProperties.PROP_BLOCK_NO_COLLISION.getValue(skinProps));
        this.checkBlockSeat = new GuiCheckBox(15, 10, 65, GuiHelper.getLocalizedControlName(guiName, "seat"), SkinProperties.PROP_BLOCK_SEAT.getValue(skinProps));
        this.checkBlockMultiblock = new GuiCheckBox(15, 10, 80, GuiHelper.getLocalizedControlName(guiName, "multiblock"), SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(skinProps));
        this.checkBlockBed = new GuiCheckBox(15, 22, 95, GuiHelper.getLocalizedControlName(guiName, "bed"), SkinProperties.PROP_BLOCK_BED.getValue(skinProps));
        this.checkBlockEnderInventory = new GuiCheckBox(15, 10, 110, GuiHelper.getLocalizedControlName(guiName, "enderInventory"), SkinProperties.PROP_BLOCK_ENDER_INVENTORY.getValue(skinProps));
        this.checkBlockInventory = new GuiCheckBox(15, 10, 125, GuiHelper.getLocalizedControlName(guiName, "inventory"), SkinProperties.PROP_BLOCK_INVENTORY.getValue(skinProps));
        if (!this.checkBlockMultiblock.isChecked()) {
            this.checkBlockBed.field_146124_l = false;
            this.checkBlockBed.setIsChecked(false);
        } else {
            this.checkBlockBed.field_146124_l = true;
        }
        this.checkBlockBed.field_146124_l = false;
        this.checkBlockEnderInventory.field_146124_l = !this.checkBlockInventory.isChecked();
        boolean bl = this.checkBlockInventory.field_146124_l = !this.checkBlockEnderInventory.isChecked();
        if (this.checkBlockInventory.isChecked()) {
            this.checkBlockEnderInventory.setIsChecked(false);
        }
        if (this.checkBlockEnderInventory.isChecked()) {
            this.checkBlockInventory.setIsChecked(false);
        }
        this.inventorySize = new GuiInventorySize(15, 10, 158, 9, 6);
        this.inventorySize.setSrc(TEXTURE, 176, 0);
        this.inventorySize.setSelection(SkinProperties.PROP_BLOCK_INVENTORY_WIDTH.getValue(skinProps), SkinProperties.PROP_BLOCK_INVENTORY_HEIGHT.getValue(skinProps));
        this.sliderWingIdleSpeed = new GuiCustomSlider(17, 10, 45, 154, 10, "", "ms", 200.0, 10000.0, SkinProperties.PROP_WINGS_IDLE_SPEED.getValue(skinProps), false, true, this);
        this.sliderWingFlyingSpeed = new GuiCustomSlider(17, 10, 65, 154, 10, "", "ms", 200.0, 10000.0, SkinProperties.PROP_WINGS_FLYING_SPEED.getValue(skinProps), false, true, this);
        this.sliderWingMinAngle = new GuiCustomSlider(17, 10, 85, 154, 10, "", DEGREE, -180.0, 180.0, SkinProperties.PROP_WINGS_MIN_ANGLE.getValue(skinProps), false, true, this);
        this.sliderWingMaxAngle = new GuiCustomSlider(17, 10, 105, 154, 10, "", DEGREE, -180.0, 180.0, SkinProperties.PROP_WINGS_MAX_ANGLE.getValue(skinProps), false, true, this);
        this.sliderWingIdleSpeed.setFineTuneButtons(true);
        this.sliderWingFlyingSpeed.setFineTuneButtons(true);
        this.sliderWingMinAngle.setFineTuneButtons(true);
        this.sliderWingMaxAngle.setFineTuneButtons(true);
        this.checkModelOverrideHead = new GuiCheckBox(16, 10, 20, GuiHelper.getLocalizedControlName(guiName, "modelOverrideHead"), SkinProperties.PROP_MODEL_OVERRIDE_HEAD.getValue(skinProps));
        this.checkModelOverrideChest = new GuiCheckBox(16, 10, 20, GuiHelper.getLocalizedControlName(guiName, "modelOverrideChest"), SkinProperties.PROP_MODEL_OVERRIDE_CHEST.getValue(skinProps));
        this.checkModelOverrideArmLeft = new GuiCheckBox(16, 10, 35, GuiHelper.getLocalizedControlName(guiName, "modelOverrideArmLeft"), SkinProperties.PROP_MODEL_OVERRIDE_ARM_LEFT.getValue(skinProps));
        this.checkModelOverrideArmRight = new GuiCheckBox(16, 10, 50, GuiHelper.getLocalizedControlName(guiName, "modelOverrideArmRight"), SkinProperties.PROP_MODEL_OVERRIDE_ARM_RIGHT.getValue(skinProps));
        this.checkModelOverrideLegLeft = new GuiCheckBox(16, 10, 20, GuiHelper.getLocalizedControlName(guiName, "modelOverrideLegLeft"), SkinProperties.PROP_MODEL_OVERRIDE_LEG_LEFT.getValue(skinProps));
        this.checkModelOverrideLegRight = new GuiCheckBox(16, 10, 35, GuiHelper.getLocalizedControlName(guiName, "modelOverrideLegRight"), SkinProperties.PROP_MODEL_OVERRIDE_LEG_RIGHT.getValue(skinProps));
        this.checkHideOverlayHead = new GuiCheckBox(16, 10, 35, GuiHelper.getLocalizedControlName(guiName, "hideOverlayHead"), SkinProperties.PROP_MODEL_HIDE_OVERLAY_HEAD.getValue(skinProps));
        this.checkHideOverlayChest = new GuiCheckBox(16, 10, 65, GuiHelper.getLocalizedControlName(guiName, "hideOverlayChest"), SkinProperties.PROP_MODEL_HIDE_OVERLAY_CHEST.getValue(skinProps));
        this.checkHideOverlayArmLeft = new GuiCheckBox(16, 10, 80, GuiHelper.getLocalizedControlName(guiName, "hideOverlayArmLeft"), SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_LEFT.getValue(skinProps));
        this.checkHideOverlayArmRight = new GuiCheckBox(16, 10, 95, GuiHelper.getLocalizedControlName(guiName, "hideOverlayArmRight"), SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_RIGHT.getValue(skinProps));
        this.checkHideOverlayLegLeft = new GuiCheckBox(16, 10, 50, GuiHelper.getLocalizedControlName(guiName, "hideOverlayLegLeft"), SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_LEFT.getValue(skinProps));
        this.checkHideOverlayLegRight = new GuiCheckBox(16, 10, 65, GuiHelper.getLocalizedControlName(guiName, "hideOverlayLegRight"), SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_RIGHT.getValue(skinProps));
        this.checkLimitLimbMovement = new GuiCheckBox(16, 10, 80, GuiHelper.getLocalizedControlName(guiName, "limitLimbs"), SkinProperties.PROP_MODEL_LEGS_LIMIT_LIMBS.getValue(skinProps));
        SkinWings.MovementType skinMovmentType = SkinWings.MovementType.valueOf(SkinProperties.PROP_WINGS_MOVMENT_TYPE.getValue(skinProps));
        this.dropDownMovementType = new GuiDropDownList(17, 10, 125, 50, "", this);
        for (int i = 0; i < SkinWings.MovementType.values().length; ++i) {
            SkinWings.MovementType movementType = SkinWings.MovementType.values()[i];
            String unlocalizedName = "movmentType." + "armourersWorkshop".toLowerCase() + ":" + movementType.name().toLowerCase();
            String localizedName = I18n.func_135052_a((String)unlocalizedName, (Object[])new Object[0]);
            this.dropDownMovementType.addListItem(localizedName, movementType.name(), true);
            if (movementType != skinMovmentType) continue;
            this.dropDownMovementType.setListSelectedIndex(i);
        }
        this.buttonList.add(this.checkBlockGlowing);
        this.buttonList.add(this.checkBlockLadder);
        this.buttonList.add(this.checkBlockNoCollision);
        this.buttonList.add(this.checkBlockSeat);
        this.buttonList.add(this.checkBlockMultiblock);
        this.buttonList.add(this.checkBlockBed);
        this.buttonList.add(this.checkBlockInventory);
        this.buttonList.add(this.checkBlockEnderInventory);
        this.buttonList.add(this.inventorySize);
        this.buttonList.add(this.sliderWingIdleSpeed);
        this.buttonList.add(this.sliderWingFlyingSpeed);
        this.buttonList.add(this.sliderWingMinAngle);
        this.buttonList.add(this.sliderWingMaxAngle);
        this.buttonList.add(this.checkModelOverrideHead);
        this.buttonList.add(this.checkModelOverrideChest);
        this.buttonList.add(this.checkModelOverrideArmLeft);
        this.buttonList.add(this.checkModelOverrideArmRight);
        this.buttonList.add(this.checkModelOverrideLegLeft);
        this.buttonList.add(this.checkModelOverrideLegRight);
        this.buttonList.add(this.checkHideOverlayHead);
        this.buttonList.add(this.checkHideOverlayChest);
        this.buttonList.add(this.checkHideOverlayArmLeft);
        this.buttonList.add(this.checkHideOverlayArmRight);
        this.buttonList.add(this.checkHideOverlayLegLeft);
        this.buttonList.add(this.checkHideOverlayLegRight);
        this.buttonList.add(this.checkLimitLimbMovement);
        this.buttonList.add(this.dropDownMovementType);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        SkinProperties skinProps = this.tileEntity.getSkinProps();
        if (!this.checkBlockMultiblock.isChecked()) {
            this.checkBlockBed.field_146124_l = false;
            this.checkBlockBed.setIsChecked(false);
        } else {
            this.checkBlockBed.field_146124_l = true;
        }
        this.checkBlockEnderInventory.field_146124_l = !this.checkBlockInventory.isChecked();
        boolean bl = this.checkBlockInventory.field_146124_l = !this.checkBlockEnderInventory.isChecked();
        if (this.checkBlockInventory.isChecked()) {
            this.checkBlockEnderInventory.setIsChecked(false);
        }
        if (this.checkBlockEnderInventory.isChecked()) {
            this.checkBlockInventory.setIsChecked(false);
        }
        this.checkBlockBed.field_146124_l = false;
        if (button.field_146127_k == 15) {
            SkinProperties.PROP_BLOCK_GLOWING.setValue(skinProps, this.checkBlockGlowing.isChecked());
            SkinProperties.PROP_BLOCK_LADDER.setValue(skinProps, this.checkBlockLadder.isChecked());
            SkinProperties.PROP_BLOCK_NO_COLLISION.setValue(skinProps, this.checkBlockNoCollision.isChecked());
            SkinProperties.PROP_BLOCK_SEAT.setValue(skinProps, this.checkBlockSeat.isChecked());
            SkinProperties.PROP_BLOCK_MULTIBLOCK.setValue(skinProps, this.checkBlockMultiblock.isChecked());
            SkinProperties.PROP_BLOCK_BED.setValue(skinProps, this.checkBlockBed.isChecked());
            SkinProperties.PROP_BLOCK_INVENTORY.setValue(skinProps, this.checkBlockInventory.isChecked());
            SkinProperties.PROP_BLOCK_ENDER_INVENTORY.setValue(skinProps, this.checkBlockEnderInventory.isChecked());
            SkinProperties.PROP_BLOCK_INVENTORY_WIDTH.setValue(skinProps, this.inventorySize.getSelectionWidth());
            SkinProperties.PROP_BLOCK_INVENTORY_HEIGHT.setValue(skinProps, this.inventorySize.getSelectionHeight());
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiSetArmourerSkinProps(skinProps));
        }
        if (button.field_146127_k == 16) {
            SkinProperties.PROP_MODEL_OVERRIDE_HEAD.setValue(skinProps, this.checkModelOverrideHead.isChecked());
            SkinProperties.PROP_MODEL_OVERRIDE_CHEST.setValue(skinProps, this.checkModelOverrideChest.isChecked());
            SkinProperties.PROP_MODEL_OVERRIDE_ARM_LEFT.setValue(skinProps, this.checkModelOverrideArmLeft.isChecked());
            SkinProperties.PROP_MODEL_OVERRIDE_ARM_RIGHT.setValue(skinProps, this.checkModelOverrideArmRight.isChecked());
            SkinProperties.PROP_MODEL_OVERRIDE_LEG_LEFT.setValue(skinProps, this.checkModelOverrideLegLeft.isChecked());
            SkinProperties.PROP_MODEL_OVERRIDE_LEG_RIGHT.setValue(skinProps, this.checkModelOverrideLegRight.isChecked());
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_HEAD.setValue(skinProps, this.checkHideOverlayHead.isChecked());
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_CHEST.setValue(skinProps, this.checkHideOverlayChest.isChecked());
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_LEFT.setValue(skinProps, this.checkHideOverlayArmLeft.isChecked());
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_RIGHT.setValue(skinProps, this.checkHideOverlayArmRight.isChecked());
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_LEFT.setValue(skinProps, this.checkHideOverlayLegLeft.isChecked());
            SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_RIGHT.setValue(skinProps, this.checkHideOverlayLegRight.isChecked());
            SkinProperties.PROP_MODEL_LEGS_LIMIT_LIMBS.setValue(skinProps, this.checkLimitLimbMovement.isChecked());
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiSetArmourerSkinProps(skinProps));
        }
        this.inventorySize.field_146125_m = this.checkBlockInventory.isChecked();
    }

    public void resetValues(SkinProperties skinProperties) {
        this.resetting = true;
        this.checkBlockGlowing.setIsChecked(SkinProperties.PROP_BLOCK_GLOWING.getValue(skinProperties));
        this.checkBlockLadder.setIsChecked(SkinProperties.PROP_BLOCK_LADDER.getValue(skinProperties));
        this.checkBlockNoCollision.setIsChecked(SkinProperties.PROP_BLOCK_NO_COLLISION.getValue(skinProperties));
        this.checkBlockSeat.setIsChecked(SkinProperties.PROP_BLOCK_SEAT.getValue(skinProperties));
        this.checkBlockMultiblock.setIsChecked(SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(skinProperties));
        this.checkBlockBed.setIsChecked(SkinProperties.PROP_BLOCK_BED.getValue(skinProperties));
        this.checkBlockInventory.setIsChecked(SkinProperties.PROP_BLOCK_INVENTORY.getValue(skinProperties));
        this.checkBlockEnderInventory.setIsChecked(SkinProperties.PROP_BLOCK_ENDER_INVENTORY.getValue(skinProperties));
        this.inventorySize.setSelection(SkinProperties.PROP_BLOCK_INVENTORY_WIDTH.getValue(skinProperties), SkinProperties.PROP_BLOCK_INVENTORY_HEIGHT.getValue(skinProperties));
        this.inventorySize.field_146125_m = this.checkBlockInventory.isChecked();
        this.sliderWingMinAngle.setValue(SkinProperties.PROP_WINGS_MIN_ANGLE.getValue(skinProperties));
        this.sliderWingMinAngle.updateSlider();
        this.sliderWingMaxAngle.setValue(SkinProperties.PROP_WINGS_MAX_ANGLE.getValue(skinProperties));
        this.sliderWingMaxAngle.updateSlider();
        this.sliderWingIdleSpeed.setValue(SkinProperties.PROP_WINGS_IDLE_SPEED.getValue(skinProperties));
        this.sliderWingIdleSpeed.updateSlider();
        this.sliderWingFlyingSpeed.setValue(SkinProperties.PROP_WINGS_FLYING_SPEED.getValue(skinProperties));
        this.sliderWingFlyingSpeed.updateSlider();
        this.checkModelOverrideHead.setIsChecked(SkinProperties.PROP_MODEL_OVERRIDE_HEAD.getValue(skinProperties));
        this.checkModelOverrideChest.setIsChecked(SkinProperties.PROP_MODEL_OVERRIDE_CHEST.getValue(skinProperties));
        this.checkModelOverrideArmLeft.setIsChecked(SkinProperties.PROP_MODEL_OVERRIDE_ARM_LEFT.getValue(skinProperties));
        this.checkModelOverrideArmRight.setIsChecked(SkinProperties.PROP_MODEL_OVERRIDE_ARM_RIGHT.getValue(skinProperties));
        this.checkModelOverrideLegLeft.setIsChecked(SkinProperties.PROP_MODEL_OVERRIDE_LEG_LEFT.getValue(skinProperties));
        this.checkModelOverrideLegRight.setIsChecked(SkinProperties.PROP_MODEL_OVERRIDE_LEG_RIGHT.getValue(skinProperties));
        this.checkHideOverlayHead.setIsChecked(SkinProperties.PROP_MODEL_HIDE_OVERLAY_HEAD.getValue(skinProperties));
        this.checkHideOverlayChest.setIsChecked(SkinProperties.PROP_MODEL_HIDE_OVERLAY_CHEST.getValue(skinProperties));
        this.checkHideOverlayArmLeft.setIsChecked(SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_LEFT.getValue(skinProperties));
        this.checkHideOverlayArmRight.setIsChecked(SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_RIGHT.getValue(skinProperties));
        this.checkHideOverlayLegLeft.setIsChecked(SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_LEFT.getValue(skinProperties));
        this.checkHideOverlayLegRight.setIsChecked(SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_RIGHT.getValue(skinProperties));
        this.checkLimitLimbMovement.setIsChecked(SkinProperties.PROP_MODEL_LEGS_LIMIT_LIMBS.getValue(skinProperties));
        SkinWings.MovementType skinMovmentType = SkinWings.MovementType.valueOf(SkinProperties.PROP_WINGS_MOVMENT_TYPE.getValue(skinProperties));
        for (int i = 0; i < SkinWings.MovementType.values().length; ++i) {
            SkinWings.MovementType movementType = SkinWings.MovementType.values()[i];
            if (movementType != skinMovmentType) continue;
            this.dropDownMovementType.setListSelectedIndex(i);
        }
        this.resetting = false;
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
        this.func_73729_b(this.x, this.y, 0, 0, this.width, this.height);
        this.func_73729_b(this.x + 7, this.y + 141, 7, 3, 162, 76);
        this.checkBlockGlowing.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinBlock;
        this.checkBlockLadder.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinBlock;
        this.checkBlockNoCollision.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinBlock;
        this.checkBlockSeat.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinBlock;
        this.checkBlockMultiblock.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinBlock;
        this.checkBlockBed.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinBlock;
        this.checkBlockInventory.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinBlock;
        this.checkBlockEnderInventory.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinBlock;
        this.inventorySize.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinBlock & this.checkBlockInventory.isChecked();
        this.sliderWingIdleSpeed.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinWings;
        this.sliderWingFlyingSpeed.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinWings;
        this.sliderWingMinAngle.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinWings;
        this.sliderWingMaxAngle.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinWings;
        this.dropDownMovementType.field_146125_m = this.tileEntity.getSkinType() == SkinTypeRegistry.skinWings;
        this.checkModelOverrideHead.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_OVERRIDE_HEAD);
        this.checkModelOverrideChest.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_OVERRIDE_CHEST);
        this.checkModelOverrideArmLeft.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_OVERRIDE_ARM_LEFT);
        this.checkModelOverrideArmRight.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_OVERRIDE_ARM_RIGHT);
        this.checkModelOverrideLegLeft.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_OVERRIDE_LEG_LEFT);
        this.checkModelOverrideLegRight.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_OVERRIDE_LEG_RIGHT);
        this.checkHideOverlayHead.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_HIDE_OVERLAY_HEAD);
        this.checkHideOverlayChest.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_HIDE_OVERLAY_CHEST);
        this.checkHideOverlayArmLeft.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_LEFT);
        this.checkHideOverlayArmRight.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_RIGHT);
        this.checkHideOverlayLegLeft.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_LEFT);
        this.checkHideOverlayLegRight.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_RIGHT);
        this.checkLimitLimbMovement.field_146125_m = this.tileEntity.getSkinType().getProperties().contains(SkinProperties.PROP_MODEL_LEGS_LIMIT_LIMBS);
    }

    @Override
    public void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
        if (this.tileEntity.getSkinType() == SkinTypeRegistry.skinWings) {
            String idleSpeedLabel = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.idleSpeed");
            String flyingSpeedLabel = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.flyingSpeed");
            String minAngleLabel = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.minAngle");
            String maxAngleLabel = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.maxAngle");
            this.fontRenderer.func_78276_b(idleSpeedLabel, 10, 36, 0x404040);
            this.fontRenderer.func_78276_b(flyingSpeedLabel, 10, 56, 0x404040);
            this.fontRenderer.func_78276_b(minAngleLabel, 10, 76, 0x404040);
            this.fontRenderer.func_78276_b(maxAngleLabel, 10, 96, 0x404040);
        }
        if (this.tileEntity.getSkinType() == SkinTypeRegistry.skinBlock & this.checkBlockInventory.isChecked()) {
            String labelInventorySize = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.inventorySize");
            String labelInventorySlots = "inventory." + "armourersWorkshop".toLowerCase() + ":" + this.tileEntity.func_145825_b() + ".label.inventorySlots";
            labelInventorySlots = StatCollector.func_74837_a((String)labelInventorySlots, (Object[])new Object[]{this.inventorySize.getSelectionWidth() * this.inventorySize.getSelectionHeight(), this.inventorySize.getSelectionWidth(), this.inventorySize.getSelectionHeight()});
            this.fontRenderer.func_78276_b(labelInventorySize, 10, 140, 0x404040);
            this.fontRenderer.func_78276_b(labelInventorySlots, 10, 150, 0x404040);
        }
    }

    public void onChangeSliderValue(GuiSlider slider) {
        if (!this.resetting) {
            SkinProperties skinProps = this.tileEntity.getSkinProps();
            if (!this.sliderWingIdleSpeed.dragging & !this.sliderWingFlyingSpeed.dragging & !this.sliderWingMinAngle.dragging & !this.sliderWingMaxAngle.dragging) {
                SkinProperties.PROP_WINGS_IDLE_SPEED.setValue(skinProps, Double.valueOf(Math.round(this.sliderWingIdleSpeed.getValue())));
                SkinProperties.PROP_WINGS_FLYING_SPEED.setValue(skinProps, Double.valueOf(Math.round(this.sliderWingFlyingSpeed.getValue())));
                SkinProperties.PROP_WINGS_MIN_ANGLE.setValue(skinProps, Double.valueOf(Math.round(this.sliderWingMinAngle.getValue())));
                SkinProperties.PROP_WINGS_MAX_ANGLE.setValue(skinProps, Double.valueOf(Math.round(this.sliderWingMaxAngle.getValue())));
            }
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiSetArmourerSkinProps(skinProps));
        }
    }

    @Override
    public void onDropDownListChanged(GuiDropDownList dropDownList) {
        SkinProperties skinProps = this.tileEntity.getSkinProps();
        SkinProperties.PROP_WINGS_MOVMENT_TYPE.setValue(skinProps, dropDownList.getListSelectedItem().tag);
        PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiSetArmourerSkinProps(skinProps));
    }
}

