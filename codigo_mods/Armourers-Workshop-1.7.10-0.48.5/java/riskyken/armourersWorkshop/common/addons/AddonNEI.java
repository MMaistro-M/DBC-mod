/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.addons;

import java.lang.reflect.Method;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.addons.ModAddon;

public class AddonNEI
extends ModAddon {
    public AddonNEI() {
        super("NotEnoughItems", "NotEnoughItems");
    }

    public boolean isVisible() {
        if (this.isModLoaded()) {
            if (!this.isEnabled()) {
                return false;
            }
            try {
                Class<?> c = Class.forName("codechicken.nei.NEIClientConfig");
                Object object = c.getMethod("isHidden", new Class[0]).invoke(null, new Object[0]);
                if (object != null && object instanceof Boolean) {
                    return (Boolean)object == false;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean isEnabled() {
        try {
            Class<?> c = Class.forName("codechicken.nei.NEIClientConfig");
            Object object = c.getMethod("isEnabled", new Class[0]).invoke(null, new Object[0]);
            if (object != null && object instanceof Boolean) {
                return (Boolean)object;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void hideItem(ItemStack stack) {
        if (this.isModLoaded()) {
            try {
                Class<?> ccApi = Class.forName("codechicken.nei.api.API");
                Method ccHideStack = ccApi.getMethod("hideItem", ItemStack.class);
                ccHideStack.invoke(null, stack);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

