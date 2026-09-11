/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.tileentity.TileEntity
 */
package riskyken.armourersWorkshop.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import riskyken.armourersWorkshop.common.blocks.BlockArmourer;
import riskyken.armourersWorkshop.common.blocks.BlockBoundingBox;
import riskyken.armourersWorkshop.common.blocks.BlockColourMixer;
import riskyken.armourersWorkshop.common.blocks.BlockColourable;
import riskyken.armourersWorkshop.common.blocks.BlockColourableGlass;
import riskyken.armourersWorkshop.common.blocks.BlockDoll;
import riskyken.armourersWorkshop.common.blocks.BlockDyeTable;
import riskyken.armourersWorkshop.common.blocks.BlockGlobalSkinLibrary;
import riskyken.armourersWorkshop.common.blocks.BlockHologramProjector;
import riskyken.armourersWorkshop.common.blocks.BlockMannequin;
import riskyken.armourersWorkshop.common.blocks.BlockOutfitMaker;
import riskyken.armourersWorkshop.common.blocks.BlockSkinLibrary;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnable;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnableChild;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnableChildGlowing;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnableGlowing;
import riskyken.armourersWorkshop.common.blocks.BlockSkinningTable;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityBoundingBox;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourMixer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourable;
import riskyken.armourersWorkshop.common.tileentities.TileEntityDyeTable;
import riskyken.armourersWorkshop.common.tileentities.TileEntityGlobalSkinLibrary;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityOutfitMaker;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinLibrary;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnableChild;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinningTable;

public class ModBlocks {
    public static Block armourerBrain;
    public static Block armourLibrary;
    public static Block globalSkinLibrary;
    public static Block boundingBox;
    public static Block colourable;
    public static Block colourableGlowing;
    public static Block colourableGlass;
    public static Block colourableGlassGlowing;
    public static Block colourMixer;
    public static Block mannequin;
    public static Block doll;
    public static Block skinningTable;
    public static Block skinnable;
    public static Block skinnableGlowing;
    public static Block skinnableChild;
    public static Block skinnableChildGlowing;
    public static Block dyeTable;
    public static Block hologramProjector;
    public static Block outfitMaker;

    public ModBlocks() {
        armourerBrain = new BlockArmourer();
        armourLibrary = new BlockSkinLibrary();
        globalSkinLibrary = new BlockGlobalSkinLibrary();
        boundingBox = new BlockBoundingBox();
        colourable = new BlockColourable("colourable", false);
        colourableGlowing = new BlockColourable("colourableGlowing", true);
        colourableGlass = new BlockColourableGlass("colourableGlass", false);
        colourableGlassGlowing = new BlockColourableGlass("colourableGlassGlowing", true);
        colourMixer = new BlockColourMixer();
        mannequin = new BlockMannequin();
        doll = new BlockDoll();
        skinningTable = new BlockSkinningTable();
        skinnable = new BlockSkinnable();
        skinnableGlowing = new BlockSkinnableGlowing();
        skinnableChild = new BlockSkinnableChild();
        skinnableChildGlowing = new BlockSkinnableChildGlowing();
        dyeTable = new BlockDyeTable();
        hologramProjector = new BlockHologramProjector();
        outfitMaker = new BlockOutfitMaker();
    }

    public void registerTileEntities() {
        this.registerTileEntity(TileEntityArmourer.class, "armourerBrain");
        this.registerTileEntity(TileEntitySkinLibrary.class, "armourLibrary");
        this.registerTileEntity(TileEntityGlobalSkinLibrary.class, "globalSkinLibrary");
        this.registerTileEntity(TileEntityColourable.class, "colourable");
        this.registerTileEntity(TileEntityColourMixer.class, "colourMixer");
        this.registerTileEntity(TileEntityBoundingBox.class, "awBoundingBox6");
        this.registerTileEntity(TileEntityMannequin.class, "mannequin");
        this.registerTileEntity(TileEntitySkinningTable.class, "skinningTable");
        this.registerTileEntity(TileEntitySkinnable.class, "skinnable");
        this.registerTileEntity(TileEntityDyeTable.class, "dyeTable");
        this.registerTileEntity(TileEntitySkinnableChild.class, "skinnableChild");
        this.registerTileEntity(TileEntityHologramProjector.class, "hologramProjector");
        this.registerTileEntity(TileEntityOutfitMaker.class, "outfit_maker");
    }

    private void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
        GameRegistry.registerTileEntity(tileEntityClass, (String)("te." + id));
    }
}

