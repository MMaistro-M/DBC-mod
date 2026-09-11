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
public class ModelLegs
extends ModelBase {
    public static final ModelLegs MODEL = new ModelLegs();
    private ModelRenderer legLeft = new ModelRenderer((ModelBase)this, 0, 16);
    private ModelRenderer legRight;

    public ModelLegs() {
        this.legLeft.field_78809_i = true;
        this.legLeft.func_78789_a(-2.0f, -12.0f, -2.0f, 4, 12, 4);
        this.legLeft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.legRight = new ModelRenderer((ModelBase)this, 0, 16);
        this.legRight.func_78789_a(-2.0f, -12.0f, -2.0f, 4, 12, 4);
        this.legRight.func_78793_a(0.0f, 0.0f, 0.0f);
    }

    public void renderLeftLeft(float scale) {
        this.legLeft.func_78785_a(scale);
    }

    public void renderRightLeg(float scale) {
        this.legRight.func_78785_a(scale);
    }
}

