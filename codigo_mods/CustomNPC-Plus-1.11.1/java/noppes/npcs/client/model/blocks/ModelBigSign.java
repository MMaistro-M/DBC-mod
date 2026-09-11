/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package noppes.npcs.client.model.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBigSign
extends ModelBase {
    public ModelRenderer signBoard = new ModelRenderer((ModelBase)this, 0, 0);

    public ModelBigSign() {
        this.signBoard.func_78790_a(-8.0f, -8.0f, -1.0f, 16, 16, 2, 0.0f);
    }

    public void renderSign() {
        this.signBoard.func_78785_a(0.0625f);
    }
}

