/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.EntityLivingBase
 */
package kamkeel.npcs.addon.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.client.gui.model.GuiCreationScreen;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.entity.EntityNPCInterface;

public class GeckoAddonClient {
    public static GeckoAddonClient Instance;
    public boolean supportEnabled = true;

    public GeckoAddonClient() {
        Instance = this;
    }

    public void showGeckoButtons(GuiCreationScreen creationScreen, EntityLivingBase entity) {
    }

    public void geckoGuiCreationScreenActionPerformed(GuiCreationScreen creationScreen, GuiNpcButton btn) {
    }

    public void geckoNpcDisplayInitGui(GuiNPCInterface2 guiNPCInterface) {
    }

    public void geckoNpcDisplayActionPerformed(GuiNPCInterface2 guiNPCInterface, GuiNpcButton btn) {
    }

    public boolean isGeckoModel(ModelBase mainModel) {
        return false;
    }

    public void geckoRenderModel(ModelMPM mainModel, EntityNPCInterface npc, float rotationYaw, float renderPartialTicks) {
    }
}

