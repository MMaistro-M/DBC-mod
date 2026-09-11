/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package riskyken.armourersWorkshop.client.model.armourer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(value=Side.CLIENT)
public class ModelChest
extends ModelBase {
    public static final ModelChest MODEL = new ModelChest();
    private ModelRenderer main = new ModelRenderer((ModelBase)this, 16, 16);
    private ModelRenderer leftArm;
    private ModelRenderer rightArm;

    public ModelChest() {
        this.main.func_78789_a(-4.0f, -12.0f, -2.0f, 8, 12, 4);
        this.main.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftArm.field_78809_i = true;
        this.leftArm.func_78789_a(-1.0f, -12.0f, -2.0f, 4, 12, 4);
        this.leftArm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.rightArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightArm.func_78789_a(-3.0f, -12.0f, -2.0f, 4, 12, 4);
        this.rightArm.func_78793_a(0.0f, 0.0f, 0.0f);
    }

    public void renderChest(float scale) {
        this.main.func_78785_a(scale);
    }

    public void renderLeftArm(float scale) {
        this.leftArm.func_78785_a(scale);
    }

    public void renderRightArm(float scale) {
        this.rightArm.func_78785_a(scale);
    }
}

