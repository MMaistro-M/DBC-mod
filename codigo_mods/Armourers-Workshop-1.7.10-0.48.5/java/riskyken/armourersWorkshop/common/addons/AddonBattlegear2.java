/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.common.addons;

import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.common.addons.ModAddon;
import riskyken.armourersWorkshop.utils.EventState;

public class AddonBattlegear2
extends ModAddon {
    public AddonBattlegear2() {
        super("battlegear2", "Battlegear 2");
    }

    @Override
    public void preInit() {
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "waraxe.wood");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "waraxe.stone");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "waraxe.iron");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "waraxe.diamond");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "waraxe.gold");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "mace.wood");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "mace.stone");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "mace.iron");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "mace.diamond");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "mace.gold");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "spear.wood");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "spear.stone");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "spear.iron");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "spear.diamond");
        this.addItemOverride(ModAddon.ItemOverrideType.SWORD, "spear.gold");
    }

    @Override
    public void onWeaponRender(IItemRenderer.ItemRenderType type, EventState state) {
        if (AddonBattlegear2.isBattlegearRender()) {
            if (state == EventState.PRE) {
                GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
            }
            if (state == EventState.POST) {
                GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
            }
        }
    }

    public static boolean isBattlegearRender() {
        String bgRenderHelper = "mods.battlegear2.client.utils.BattlegearRenderHelper";
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        Object callerClassName = null;
        for (int i = 1; i < stElements.length; ++i) {
            StackTraceElement ste = stElements[i];
            if (!ste.getClassName().equals(bgRenderHelper)) continue;
            return true;
        }
        return false;
    }
}

