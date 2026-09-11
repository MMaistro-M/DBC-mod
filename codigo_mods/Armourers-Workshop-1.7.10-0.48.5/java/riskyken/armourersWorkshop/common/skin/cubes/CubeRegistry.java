/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package riskyken.armourersWorkshop.common.skin.cubes;

import java.util.ArrayList;
import net.minecraft.block.Block;
import riskyken.armourersWorkshop.common.skin.cubes.Cube;
import riskyken.armourersWorkshop.common.skin.cubes.CubeGlass;
import riskyken.armourersWorkshop.common.skin.cubes.CubeGlassGlowing;
import riskyken.armourersWorkshop.common.skin.cubes.CubeGlowing;
import riskyken.armourersWorkshop.common.skin.cubes.ICube;
import riskyken.armourersWorkshop.utils.ModLogger;

public final class CubeRegistry {
    public static CubeRegistry INSTANCE;
    private ArrayList<ICube> cubeList = new ArrayList();

    public static void init() {
        INSTANCE = new CubeRegistry();
        INSTANCE.registerCubes();
    }

    public ICube getCubeFormId(byte id) {
        if (id >= 0 && id < this.cubeList.size()) {
            return this.cubeList.get(id);
        }
        return null;
    }

    public byte getTotalCubes() {
        return (byte)this.cubeList.size();
    }

    private void registerCube(ICube cube) {
        this.cubeList.add(cube);
        ModLogger.log("Registering equipment cube - id:" + cube.getId() + " name:" + cube.getClass().getSimpleName());
    }

    public void registerCubes() {
        this.registerCube(new Cube());
        this.registerCube(new CubeGlowing());
        this.registerCube(new CubeGlass());
        this.registerCube(new CubeGlassGlowing());
    }

    public boolean isBuildingBlock(Block block) {
        for (int i = 0; i < this.cubeList.size(); ++i) {
            if (this.cubeList.get(i).getMinecraftBlock() != block) continue;
            return true;
        }
        return false;
    }

    public ICube getCubeFromBlock(Block block) {
        for (int i = 0; i < this.cubeList.size(); ++i) {
            if (this.cubeList.get(i).getMinecraftBlock() != block) continue;
            return this.cubeList.get(i);
        }
        return null;
    }
}

