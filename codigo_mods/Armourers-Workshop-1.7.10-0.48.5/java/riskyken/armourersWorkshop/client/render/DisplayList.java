/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GLAllocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render;

import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.renderer.GLAllocation;
import org.lwjgl.opengl.GL11;

public class DisplayList {
    private static AtomicInteger LIST_COUNT = new AtomicInteger(0);
    private int list;
    private boolean compiled = false;

    public static int getListCount() {
        return LIST_COUNT.get();
    }

    public void begin() {
        if (this.compiled) {
            this.cleanup();
        }
        this.list = GLAllocation.func_74526_a((int)1);
        LIST_COUNT.incrementAndGet();
        GL11.glNewList((int)this.list, (int)4864);
    }

    public boolean isCompiled() {
        return this.compiled;
    }

    public void end() {
        GL11.glEndList();
        this.compiled = true;
    }

    public void render() {
        if (this.compiled) {
            GL11.glCallList((int)this.list);
        }
    }

    public void cleanup() {
        GLAllocation.func_74523_b((int)this.list);
        LIST_COUNT.decrementAndGet();
        this.compiled = false;
    }

    protected void finalize() throws Throwable {
        if (this.compiled) {
            this.cleanup();
        }
        super.finalize();
    }
}

