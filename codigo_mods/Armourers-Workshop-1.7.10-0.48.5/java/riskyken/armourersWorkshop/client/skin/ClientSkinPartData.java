/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package riskyken.armourersWorkshop.client.skin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.client.model.SkinModel;
import riskyken.armourersWorkshop.client.model.bake.ColouredFace;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.common.skin.data.SkinDye;

@SideOnly(value=Side.CLIENT)
public class ClientSkinPartData {
    public static final SkinDye blankDye = new SkinDye();
    public ArrayList<ColouredFace>[] vertexLists;
    public HashMap<ModelKey, SkinModel> dyeModels;
    public int[] totalCubesInPart;
    private int[] averageR = new int[10];
    private int[] averageG = new int[10];
    private int[] averageB = new int[10];

    public ClientSkinPartData() {
        this.dyeModels = new HashMap();
    }

    public SkinModel getModelForDye(SkinPartRenderData renderData) {
        ModelKey modelKey = new ModelKey(renderData.getSkinDye(), renderData.getExtraColours());
        SkinModel skinModel = this.dyeModels.get(modelKey);
        if (skinModel == null) {
            skinModel = new SkinModel(this.vertexLists);
            this.dyeModels.put(modelKey, skinModel);
        }
        return skinModel;
    }

    public void cleanUpDisplayLists() {
        Set<ModelKey> keys = this.dyeModels.keySet();
        for (ModelKey modelKey : this.dyeModels.keySet()) {
            this.dyeModels.get(modelKey).cleanUpDisplayLists();
        }
    }

    public int getModelCount() {
        return this.dyeModels.size();
    }

    public void setVertexLists(ArrayList<ColouredFace>[] vertexLists) {
        this.vertexLists = vertexLists;
    }

    public void setAverageDyeValues(int[] r, int[] g, int[] b) {
        this.averageR = r;
        this.averageG = g;
        this.averageB = b;
    }

    public int[] getAverageDyeColour(int dyeNumber) {
        return new int[]{this.averageR[dyeNumber], this.averageG[dyeNumber], this.averageB[dyeNumber]};
    }

    private class ModelKey {
        private ISkinDye skinDye;
        byte[] extraColours;

        public ModelKey(ISkinDye skinDye, byte[] extraColours) {
            this.skinDye = skinDye == null ? blankDye : skinDye;
            this.extraColours = extraColours;
        }

        public int hashCode() {
            int prime = 31;
            int result = 1;
            result = 31 * result + Arrays.hashCode(this.extraColours);
            result = 31 * result + (this.skinDye == null ? 0 : this.skinDye.hashCode());
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            ModelKey other = (ModelKey)obj;
            if (!Arrays.equals(this.extraColours, other.extraColours)) {
                return false;
            }
            return !(this.skinDye == null ? other.skinDye != null : !this.skinDye.equals(other.skinDye));
        }
    }
}

