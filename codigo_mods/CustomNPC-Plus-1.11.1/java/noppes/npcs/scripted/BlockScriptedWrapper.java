/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTBase$NBTPrimitive
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.World
 */
package noppes.npcs.scripted;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.ITileEntity;
import noppes.npcs.api.ITimers;
import noppes.npcs.api.block.IBlockScripted;
import noppes.npcs.api.block.ITextPlane;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.blocks.tiles.TileScripted;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.ScriptBlock;

public class BlockScriptedWrapper
extends ScriptBlock
implements IBlockScripted {
    private TileScripted tile;

    public BlockScriptedWrapper(World world, Block block, int x, int y, int z) {
        super(world, block, new BlockPos(x, y, z));
        this.tile = (TileScripted)((ScriptBlock)this).tile.getMCTileEntity();
    }

    @Override
    public void setModel(IItemStack item) {
        if (item == null) {
            this.tile.setItemModel(null, null);
        } else {
            this.tile.setItemModel(item.getMCItemStack(), Block.func_149634_a((Item)item.getMCItemStack().func_77973_b()));
        }
    }

    @Override
    public void setModel(String name) {
        if (name == null) {
            this.tile.setItemModel(null, null);
        } else {
            Block block = Block.func_149684_b((String)name);
            if (block != null) {
                this.tile.setItemModel(new ItemStack(Item.func_150898_a((Block)block)), block);
            } else if (Item.field_150901_e.func_148741_d(name)) {
                this.tile.setItemModel(new ItemStack((Item)Item.field_150901_e.func_82594_a(name)), null);
            } else {
                this.tile.setItemModel(null, null);
            }
        }
    }

    @Override
    public IItemStack getModel() {
        return NpcAPI.Instance().getIItemStack(this.tile.itemModel);
    }

    @Override
    public void setRedstonePower(int strength) {
        this.tile.setRedstonePower(strength);
    }

    @Override
    public int getRedstonePower() {
        return this.tile.powering;
    }

    @Override
    public void setIsLadder(boolean bo) {
        this.tile.isLadder = bo;
        this.tile.needsClientUpdate = true;
    }

    @Override
    public boolean getIsLadder() {
        return this.tile.isLadder;
    }

    @Override
    public void setIsPassible(boolean bo) {
        this.setIsPassable(bo);
    }

    @Override
    public boolean getIsPassible() {
        return this.getIsPassable();
    }

    @Override
    public void setIsPassable(boolean bo) {
        this.tile.isPassible = bo;
        this.tile.needsClientUpdate = true;
    }

    @Override
    public boolean getIsPassable() {
        if (this.tile == null) {
            return false;
        }
        return this.tile.isPassible;
    }

    @Override
    public void setLight(int value) {
        this.tile.setLightValue(value);
    }

    @Override
    public int getLight() {
        if (this.tile == null) {
            return 0;
        }
        return this.tile.lightValue;
    }

    @Override
    public void setScale(float x, float y, float z) {
        this.tile.setScale(x, y, z);
    }

    @Override
    public float getScaleX() {
        return this.tile.scaleX;
    }

    @Override
    public float getScaleY() {
        return this.tile.scaleY;
    }

    @Override
    public float getScaleZ() {
        return this.tile.scaleZ;
    }

    @Override
    public void setRotation(int x, int y, int z) {
        this.tile.setRotation(x % 360, y % 360, z % 360);
    }

    @Override
    public int getRotationX() {
        return this.tile.rotationX;
    }

    @Override
    public int getRotationY() {
        return this.tile.rotationY;
    }

    @Override
    public int getRotationZ() {
        return this.tile.rotationZ;
    }

    @Override
    public float getHardness() {
        return this.tile.blockHardness;
    }

    @Override
    public void setHardness(float hardness) {
        this.tile.blockHardness = hardness;
    }

    @Override
    public float getResistance() {
        return this.tile.blockResistance;
    }

    @Override
    public void setResistance(float resistance) {
        this.tile.blockResistance = resistance;
    }

    @Override
    public void executeCommand(String command) {
        if (!MinecraftServer.func_71276_C().func_82356_Z()) {
            throw new CustomNPCsException("Command blocks need to be enabled to executeCommands", new Object[0]);
        }
        NoppesUtilServer.runCommand((World)this.world.getMCWorld(), "ScriptedBlock", command);
    }

    @Override
    public ITextPlane getTextPlane() {
        return this.tile.text1;
    }

    @Override
    public ITextPlane getTextPlane2() {
        return this.tile.text2;
    }

    @Override
    public ITextPlane getTextPlane3() {
        return this.tile.text3;
    }

    @Override
    public ITextPlane getTextPlane4() {
        return this.tile.text4;
    }

    @Override
    public ITextPlane getTextPlane5() {
        return this.tile.text5;
    }

    @Override
    public ITextPlane getTextPlane6() {
        return this.tile.text6;
    }

    @Override
    public ITimers getTimers() {
        return this.tile.timers;
    }

    @Override
    public void setTileEntity(ITileEntity tile) {
        this.tile = (TileScripted)((Object)tile);
        super.setTileEntity(tile);
    }

    private NBTTagCompound getNBT() {
        if (this.tile == null) {
            return null;
        }
        NBTTagCompound compound = this.tile.getTileData().func_74775_l("CustomNPCsData");
        if (compound.func_82582_d() && !this.tile.getTileData().func_74764_b("CustomNPCsData")) {
            this.tile.getTileData().func_74782_a("CustomNPCsData", (NBTBase)compound);
        }
        return compound;
    }

    @Override
    public void setStoredData(String key, Object value) {
        NBTTagCompound compound = this.getNBT();
        if (compound == null) {
            return;
        }
        if (value instanceof Number) {
            compound.func_74780_a(key, ((Number)value).doubleValue());
        } else if (value instanceof String) {
            compound.func_74778_a(key, (String)value);
        }
    }

    @Override
    public Object getStoredData(String key) {
        NBTTagCompound compound = this.getNBT();
        if (compound == null) {
            return null;
        }
        if (!compound.func_74764_b(key)) {
            return null;
        }
        NBTBase base = compound.func_74781_a(key);
        if (base instanceof NBTBase.NBTPrimitive) {
            return ((NBTBase.NBTPrimitive)base).func_150286_g();
        }
        return ((NBTTagString)base).func_150285_a_();
    }

    @Override
    public void removeStoredData(String key) {
        NBTTagCompound compound = this.getNBT();
        if (compound == null) {
            return;
        }
        compound.func_82580_o(key);
    }

    @Override
    public boolean hasStoredData(String key) {
        NBTTagCompound compound = this.getNBT();
        if (compound == null) {
            return false;
        }
        return compound.func_74764_b(key);
    }

    @Override
    public void clearStoredData() {
        if (this.tile == null) {
            return;
        }
        this.tile.getTileData().func_74782_a("CustomNPCsData", (NBTBase)new NBTTagCompound());
    }

    @Override
    public String[] getStoredDataKeys() {
        NBTTagCompound compound = this.getNBT();
        if (compound == null) {
            return new String[0];
        }
        return compound.func_150296_c().toArray(new String[0]);
    }

    @Override
    public void removeTempData(String key) {
        if (this.tile == null) {
            return;
        }
        this.tile.tempData.remove(key);
    }

    @Override
    public void setTempData(String key, Object value) {
        if (this.tile == null) {
            return;
        }
        this.tile.tempData.put(key, value);
    }

    @Override
    public boolean hasTempData(String key) {
        if (this.tile == null) {
            return false;
        }
        return this.tile.tempData.containsKey(key);
    }

    @Override
    public Object getTempData(String key) {
        if (this.tile == null) {
            return null;
        }
        return this.tile.tempData.get(key);
    }

    @Override
    public void clearTempData() {
        if (this.tile == null) {
            return;
        }
        this.tile.tempData.clear();
    }

    @Override
    public String[] getTempDataKeys() {
        return this.tile.tempData.keySet().toArray(new String[this.tile.tempData.size()]);
    }
}

