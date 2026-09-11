/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.apache.logging.log4j.Logger
 */
package invtweaks;

import invtweaks.InvTweaks;
import invtweaks.InvTweaksConfig;
import invtweaks.InvTweaksConfigManager;
import invtweaks.InvTweaksContainerSectionManager;
import invtweaks.InvTweaksGuiIconButton;
import invtweaks.InvTweaksGuiSettings;
import invtweaks.InvTweaksObfuscation;
import invtweaks.api.container.ContainerSection;
import java.util.concurrent.TimeoutException;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Logger;

public class InvTweaksGuiSettingsButton
extends InvTweaksGuiIconButton {
    private static final Logger log = InvTweaks.log;

    public InvTweaksGuiSettingsButton(InvTweaksConfigManager cfgManager, int id, int x, int y, int w, int h, String displayString, String tooltip, boolean useCustomTexture) {
        super(cfgManager, id, x, y, w, h, displayString, tooltip, useCustomTexture);
    }

    @Override
    public void func_146112_a(Minecraft minecraft, int i, int j) {
        super.func_146112_a(minecraft, i, j);
        InvTweaksObfuscation obf = new InvTweaksObfuscation(minecraft);
        this.func_73732_a(obf.getFontRenderer(), this.field_146126_j, this.field_146128_h + 5, this.field_146129_i - 1, this.getTextColor(i, j));
    }

    public boolean func_146116_c(Minecraft minecraft, int i, int j) {
        InvTweaksObfuscation obf = new InvTweaksObfuscation(minecraft);
        InvTweaksConfig config = this.cfgManager.getConfig();
        if (super.func_146116_c(minecraft, i, j)) {
            block6: {
                try {
                    InvTweaksContainerSectionManager containerMgr = new InvTweaksContainerSectionManager(minecraft, ContainerSection.INVENTORY);
                    if (obf.getHeldStack() == null) break block6;
                    try {
                        for (int k = containerMgr.getSize() - 1; k >= 0; --k) {
                            if (containerMgr.getItemStack(k) != null) continue;
                            containerMgr.leftClick(k);
                            break;
                        }
                    }
                    catch (TimeoutException e) {
                        InvTweaks.logInGameErrorStatic("invtweaks.sort.releaseitem.error", e);
                    }
                }
                catch (Exception e) {
                    log.error("mousePressed", (Throwable)e);
                }
            }
            this.cfgManager.makeSureConfigurationIsLoaded();
            obf.displayGuiScreen(new InvTweaksGuiSettings(minecraft, obf.getCurrentScreen(), config));
            return true;
        }
        return false;
    }
}

