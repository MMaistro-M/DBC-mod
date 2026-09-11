/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model.armourer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelHead
extends ModelBase {
    public static final ModelHead MODEL = new ModelHead();
    private ModelRenderer main = new ModelRenderer((ModelBase)this, 0, 0);
    private ModelRenderer overlay;

    public ModelHead() {
        this.main.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.main.func_78793_a(0.0f, 0.0f, 0.0f);
        this.overlay = new ModelRenderer((ModelBase)this, 32, 0);
        this.overlay.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.5f);
        this.overlay.func_78793_a(0.0f, 0.0f, 0.0f);
        this.overlay.func_78787_b(64, 32);
    }

    public void render(float scale, boolean showOverlay) {
        this.main.func_78785_a(scale);
        if (showOverlay) {
            GL11.glDisable((int)2884);
            this.overlay.func_78785_a(scale);
            GL11.glEnable((int)2884);
        }
    }
}

