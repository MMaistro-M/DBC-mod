/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiEnchantment
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiContainerCreative
 *  net.minecraft.client.gui.inventory.GuiEditSign
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerPlayer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ResourceLocation
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.input.Mouse
 */
package invtweaks;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import invtweaks.InvTweaks;
import invtweaks.api.container.ContainerSection;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;

public class InvTweaksObfuscation {
    private static final Logger log = InvTweaks.log;
    public Minecraft mc;
    private static Map<String, Field> fieldsMap = new HashMap<String, Field>();

    public InvTweaksObfuscation(Minecraft mc) {
        this.mc = mc;
    }

    public void addChatMessage(String message) {
        if (this.mc.field_71456_v != null) {
            this.mc.field_71456_v.func_146158_b().func_146227_a((IChatComponent)new ChatComponentText(message));
        }
    }

    public static String getNamespacedID(String id) {
        if (id == null) {
            return null;
        }
        if (id.indexOf(58) == -1) {
            return "minecraft:" + id;
        }
        return id;
    }

    public EntityPlayer getThePlayer() {
        return this.mc.field_71439_g;
    }

    public PlayerControllerMP getPlayerController() {
        return this.mc.field_71442_b;
    }

    public GuiScreen getCurrentScreen() {
        return this.mc.field_71462_r;
    }

    public FontRenderer getFontRenderer() {
        return this.mc.field_71466_p;
    }

    public void displayGuiScreen(GuiScreen parentScreen) {
        this.mc.func_147108_a(parentScreen);
    }

    public static int getDisplayWidth() {
        return FMLClientHandler.instance().getClient().field_71443_c;
    }

    public static int getDisplayHeight() {
        return FMLClientHandler.instance().getClient().field_71440_d;
    }

    public GameSettings getGameSettings() {
        return this.mc.field_71474_y;
    }

    public int getKeyBindingForwardKeyCode() {
        return this.getGameSettings().field_74351_w.field_74512_d;
    }

    public int getKeyBindingBackKeyCode() {
        return this.getGameSettings().field_74368_y.field_74512_d;
    }

    public InventoryPlayer getInventoryPlayer() {
        return this.getThePlayer().field_71071_by;
    }

    public ItemStack getCurrentEquippedItem() {
        return this.getThePlayer().func_71045_bC();
    }

    public ContainerPlayer getPlayerContainer() {
        return (ContainerPlayer)this.getThePlayer().field_71069_bz;
    }

    public ItemStack[] getMainInventory() {
        return this.getInventoryPlayer().field_70462_a;
    }

    public void setMainInventory(ItemStack[] value) {
        this.getInventoryPlayer().field_70462_a = value;
    }

    public void setHasInventoryChanged(boolean value) {
        this.getInventoryPlayer().field_70459_e = value;
    }

    public void setHeldStack(ItemStack stack) {
        this.getInventoryPlayer().func_70437_b(stack);
    }

    public boolean hasInventoryChanged() {
        return this.getInventoryPlayer().field_70459_e;
    }

    public ItemStack getHeldStack() {
        return this.getInventoryPlayer().func_70445_o();
    }

    public ItemStack getFocusedStack() {
        return this.getInventoryPlayer().func_70448_g();
    }

    public int getFocusedSlot() {
        return this.getInventoryPlayer().field_70461_c;
    }

    public static boolean areItemStacksEqual(ItemStack itemStack1, ItemStack itemStack2) {
        return itemStack1.func_77969_a(itemStack2) && itemStack1.field_77994_a == itemStack2.field_77994_a;
    }

    public boolean areSameItemType(ItemStack itemStack1, ItemStack itemStack2) {
        return itemStack1.func_77969_a(itemStack2) || itemStack1.func_77984_f() && itemStack1.func_77973_b() == itemStack2.func_77973_b();
    }

    public boolean areItemsStackable(ItemStack itemStack1, ItemStack itemStack2) {
        return itemStack1 != null && itemStack2 != null && itemStack1.func_77969_a(itemStack2) && itemStack1.func_77985_e() && (!itemStack1.func_77981_g() || itemStack1.func_77960_j() == itemStack2.func_77960_j()) && ItemStack.func_77970_a((ItemStack)itemStack1, (ItemStack)itemStack2);
    }

    public static ItemStack getSlotStack(Container container, int i) {
        Slot slot = (Slot)container.field_75151_b.get(i);
        return slot == null ? null : slot.func_75211_c();
    }

    public static int getSlotNumber(Slot slot) {
        return slot.field_75222_d;
    }

    @SideOnly(value=Side.CLIENT)
    public static Slot getSlotAtMousePosition(GuiContainer guiContainer) {
        if (guiContainer != null) {
            Container container = guiContainer.field_147002_h;
            int x = InvTweaksObfuscation.getMouseX(guiContainer);
            int y = InvTweaksObfuscation.getMouseY(guiContainer);
            for (int k = 0; k < container.field_75151_b.size(); ++k) {
                Slot slot = (Slot)container.field_75151_b.get(k);
                if (!InvTweaksObfuscation.getIsMouseOverSlot(guiContainer, slot, x, y)) continue;
                return slot;
            }
            return null;
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public static boolean getIsMouseOverSlot(GuiContainer guiContainer, Slot slot) {
        return InvTweaksObfuscation.getIsMouseOverSlot(guiContainer, slot, InvTweaksObfuscation.getMouseX(guiContainer), InvTweaksObfuscation.getMouseY(guiContainer));
    }

    @SideOnly(value=Side.CLIENT)
    private static boolean getIsMouseOverSlot(GuiContainer guiContainer, Slot slot, int x, int y) {
        if (guiContainer != null) {
            return (x -= guiContainer.field_147003_i) >= slot.field_75223_e - 1 && x < slot.field_75223_e + 16 + 1 && (y -= guiContainer.field_147009_r) >= slot.field_75221_f - 1 && y < slot.field_75221_f + 16 + 1;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    private static int getMouseX(GuiContainer guiContainer) {
        return Mouse.getEventX() * guiContainer.field_146294_l / InvTweaksObfuscation.getDisplayWidth();
    }

    @SideOnly(value=Side.CLIENT)
    private static int getMouseY(GuiContainer guiContainer) {
        return guiContainer.field_146295_m - Mouse.getEventY() * guiContainer.field_146295_m / InvTweaksObfuscation.getDisplayHeight() - 1;
    }

    public static int getSpecialChestRowSize(Container container) {
        return 0;
    }

    public boolean hasTexture(ResourceLocation texture) {
        try {
            this.mc.func_110442_L().func_110536_a(texture);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getCurrentLanguage() {
        return Minecraft.func_71410_x().func_135016_M().func_135041_c().func_135034_a();
    }

    public static boolean isValidChest(Container container) {
        return false;
    }

    public static boolean isLargeChest(Container container) {
        return false;
    }

    public static boolean isValidInventory(Container container) {
        return false;
    }

    public static boolean showButtons(Container container) {
        return false;
    }

    public static Map<ContainerSection, List<Slot>> getContainerSlotMap(Container container) {
        return null;
    }

    public static boolean isGuiContainer(Object o) {
        return o != null && o instanceof GuiContainer;
    }

    public static boolean isGuiInventoryCreative(Object o) {
        return o != null && o.getClass().equals(GuiContainerCreative.class);
    }

    public static boolean isGuiEnchantmentTable(Object o) {
        return o != null && o.getClass().equals(GuiEnchantment.class);
    }

    public static boolean isGuiInventory(Object o) {
        return o != null && o.getClass().equals(GuiInventory.class);
    }

    public static boolean isGuiButton(Object o) {
        return o != null && o instanceof GuiButton;
    }

    public static boolean isGuiEditSign(Object o) {
        return o != null && o.getClass().equals(GuiEditSign.class);
    }

    public static boolean isItemArmor(Object o) {
        return o != null && o instanceof ItemArmor;
    }

    public static boolean isBasicSlot(Object o) {
        return o != null && o.getClass().equals(Slot.class);
    }
}

