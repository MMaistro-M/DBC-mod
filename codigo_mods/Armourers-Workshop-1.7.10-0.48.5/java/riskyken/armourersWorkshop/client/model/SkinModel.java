/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.MathHelper
 */
package riskyken.armourersWorkshop.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.util.MathHelper;
import riskyken.armourersWorkshop.client.model.bake.ColouredFace;
import riskyken.armourersWorkshop.client.render.DisplayList;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;

@SideOnly(value=Side.CLIENT)
public class SkinModel {
    public DisplayList[] displayList;
    public boolean[] haveList;
    public long loadedTime;

    public SkinModel(ArrayList<ColouredFace>[] vertexLists) {
        this.displayList = new DisplayList[vertexLists.length];
        this.haveList = new boolean[vertexLists.length];
        for (int i = 0; i < this.displayList.length; ++i) {
            if (vertexLists[i].size() > 0) {
                this.displayList[i] = new DisplayList();
                this.haveList[i] = true;
                continue;
            }
            this.haveList[i] = false;
        }
    }

    public void setLoaded() {
        this.loadedTime = System.currentTimeMillis();
    }

    public int getLoadingLod() {
        long time = System.currentTimeMillis();
        if (time < this.loadedTime + 500L) {
            long timePassed = time - this.loadedTime;
            return MathHelper.func_76125_a((int)(ConfigHandlerClient.maxLodLevels + 1 - ((int)((float)timePassed / 125.0f) + 1)), (int)0, (int)(ConfigHandlerClient.maxLodLevels + 1));
        }
        return 0;
    }

    public void cleanUpDisplayLists() {
        for (int i = 0; i < this.displayList.length; ++i) {
            if (!this.haveList[i]) continue;
            this.displayList[i].cleanup();
        }
    }
}

