/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer;

import net.minecraft.client.model.ModelBase;
import noppes.npcs.client.renderer.RenderNPCInterface;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class RenderNpcDragon
extends RenderNPCInterface {
    public RenderNpcDragon(ModelBase model, float f) {
        super(model, f);
    }

    @Override
    protected void renderPlayerScale(EntityNPCInterface npc, float f) {
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(0.120000005f * (float)npc.display.modelSize));
        super.renderPlayerScale(npc, f);
    }
}

