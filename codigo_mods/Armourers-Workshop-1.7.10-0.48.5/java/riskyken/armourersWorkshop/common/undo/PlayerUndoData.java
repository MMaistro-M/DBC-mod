/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.undo;

import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.undo.UndoData;
import riskyken.armourersWorkshop.common.undo.UndoManager;
import riskyken.armourersWorkshop.utils.ModLogger;

public class PlayerUndoData {
    private ArrayList<UndoData> undos;
    private ArrayList<Integer> markers;
    private EntityPlayer player;
    private boolean isPainting;
    private int markerCount = 0;

    public PlayerUndoData(EntityPlayer player) {
        this.player = player;
        this.undos = new ArrayList();
        this.markers = new ArrayList();
    }

    public void begin() {
        if (this.isPainting) {
            ModLogger.log(Level.ERROR, "Last tool undo did not end correctly.");
        }
        this.isPainting = true;
        this.markerCount = 0;
    }

    public void end() {
        if (!this.isPainting) {
            ModLogger.log(Level.ERROR, "Tool ended painting with out marking the start.");
            StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
            for (int i = 1; i < stElements.length; ++i) {
                ModLogger.log(Level.ERROR, stElements[i]);
            }
        }
        this.isPainting = false;
        if (this.markerCount != 0) {
            this.markers.add(this.markerCount);
        }
    }

    public void addUndoData(UndoData undoData) {
        if (!this.isPainting) {
            ModLogger.log(Level.ERROR, "Tool painting with out marking the start.");
        }
        ++this.markerCount;
        this.undos.add(undoData);
        if (this.markers.size() > UndoManager.maxUndos) {
            if (this.markers.size() < 1) {
                int undoMarker = this.markers.get(0);
                this.markers.remove(0);
                for (int i = 0; i < undoMarker; ++i) {
                    this.undos.remove(i);
                }
            } else {
                ModLogger.log(Level.ERROR, "No undo markers. Something is wrong!");
            }
        }
    }

    public void playerPressedUndo(World world) {
        if (this.markers.size() < 1) {
            ModLogger.log(Level.ERROR, "No undo markers. Something is wrong!");
            this.undoLast(world);
        } else {
            int undoMarker = this.markers.get(this.markers.size() - 1);
            for (int i = 0; i < undoMarker; ++i) {
                this.undoLast(world);
            }
            this.markers.remove(this.markers.size() - 1);
        }
    }

    private void undoLast(World world) {
        if (this.undos.size() < 1) {
            return;
        }
        UndoData undoData = this.undos.get(this.undos.size() - 1);
        if (world.field_73011_w.field_76574_g != undoData.dimensionId) {
            return;
        }
        Block block = world.func_147439_a(undoData.blockX, undoData.blockY, undoData.blockZ);
        if (block instanceof IPantableBlock) {
            Color c = new Color(undoData.rgb[0] & 0xFF, undoData.rgb[1] & 0xFF, undoData.rgb[2] & 0xFF);
            int rgb = c.getRGB();
            IPantableBlock worldColourable = (IPantableBlock)block;
            worldColourable.setColour((IBlockAccess)world, undoData.blockX, undoData.blockY, undoData.blockZ, rgb, undoData.side);
            worldColourable.setPaintType((IBlockAccess)world, undoData.blockX, undoData.blockY, undoData.blockZ, PaintType.getPaintTypeFormSKey(undoData.paintType), undoData.side);
        }
        this.undos.remove(this.undos.size() - 1);
    }

    public int getAvalableUndos() {
        return this.undos.size();
    }
}

