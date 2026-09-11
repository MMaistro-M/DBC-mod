/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StringUtils
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.skin.data;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StringUtils;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.utils.ModLogger;

public class SkinDye
implements ISkinDye {
    public static final int MAX_SKIN_DYES = 8;
    private static final String TAG_SKIN_DYE = "dyeData";
    private static final String TAG_DYE = "dye";
    private static final String TAG_NAME = "name";
    private static final String TAG_RED = "r";
    private static final String TAG_GREEN = "g";
    private static final String TAG_BLUE = "b";
    private static final String TAG_TYPE = "t";
    private byte[][] dyes = new byte[8][4];
    private boolean[] hasDye = new boolean[8];
    private String[] names = new String[8];

    public SkinDye() {
    }

    public SkinDye(ISkinDye skinDye) {
        this();
        for (int i = 0; i < 8; ++i) {
            if (!skinDye.haveDyeInSlot(i)) continue;
            this.addDye(i, skinDye.getDyeColour(i));
        }
    }

    @Override
    public byte[] getDyeColour(int index) {
        return this.dyes[index];
    }

    @Override
    public String getDyeName(int index) {
        return this.names[index];
    }

    @Override
    public boolean haveDyeInSlot(int index) {
        return this.hasDye[index];
    }

    @Override
    public boolean hasName(int index) {
        return !StringUtils.func_151246_b((String)this.names[index]);
    }

    @Override
    public void addDye(byte[] rgbt, String name) {
        if (rgbt.length != 4) {
            ModLogger.log(Level.WARN, "Something tried to set an invalid dye colour.");
            Thread.dumpStack();
            return;
        }
        for (int i = 0; i < this.hasDye.length; ++i) {
            if (this.hasDye[i]) continue;
            this.dyes[i] = rgbt;
            this.hasDye[i] = true;
            this.names[i] = name;
            break;
        }
    }

    @Override
    public void addDye(byte[] rgbt) {
        this.addDye(rgbt, null);
    }

    @Override
    public void addDye(int index, byte[] rgbt, String name) {
        if (rgbt.length != 4) {
            ModLogger.log(Level.WARN, "Something tried to set an invalid dye colour.");
            Thread.dumpStack();
            return;
        }
        this.dyes[index] = rgbt;
        this.hasDye[index] = true;
        this.names[index] = name;
    }

    @Override
    public void addDye(int index, byte[] rgbt) {
        this.addDye(index, rgbt, null);
    }

    @Override
    public void removeDye(int index) {
        this.dyes[index] = new byte[]{0, 0, 0, -1};
        this.hasDye[index] = false;
        this.names[index] = null;
    }

    @Override
    public int getNumberOfDyes() {
        int count = 0;
        for (int i = 0; i < 8; ++i) {
            if (!this.hasDye[i]) continue;
            ++count;
        }
        return count;
    }

    @Override
    public void writeToBuf(ByteBuf buf) {
        for (int i = 0; i < 8; ++i) {
            buf.writeBoolean(this.hasDye[i]);
            if (!this.hasDye[i]) continue;
            buf.writeBytes(this.dyes[i]);
            if (!StringUtils.func_151246_b((String)this.names[i])) {
                buf.writeBoolean(true);
                ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.names[i]);
                continue;
            }
            buf.writeBoolean(false);
        }
    }

    @Override
    public void readFromBuf(ByteBuf buf) {
        for (int i = 0; i < 8; ++i) {
            this.hasDye[i] = buf.readBoolean();
            if (!this.hasDye[i]) continue;
            buf.readBytes(this.dyes[i]);
            if (!buf.readBoolean()) continue;
            this.names[i] = ByteBufUtils.readUTF8String((ByteBuf)buf);
        }
    }

    public void writeToCompound(NBTTagCompound compound) {
        NBTTagCompound dyeCompound = new NBTTagCompound();
        for (int i = 0; i < 8; ++i) {
            if (!this.hasDye[i]) continue;
            dyeCompound.func_74774_a(TAG_DYE + i + TAG_RED, this.dyes[i][0]);
            dyeCompound.func_74774_a(TAG_DYE + i + TAG_GREEN, this.dyes[i][1]);
            dyeCompound.func_74774_a(TAG_DYE + i + TAG_BLUE, this.dyes[i][2]);
            dyeCompound.func_74774_a(TAG_DYE + i + TAG_TYPE, this.dyes[i][3]);
            if (StringUtils.func_151246_b((String)this.names[i])) continue;
            dyeCompound.func_74778_a(TAG_NAME + i, this.names[i]);
        }
        compound.func_74782_a(TAG_SKIN_DYE, (NBTBase)dyeCompound);
    }

    public void readFromCompound(NBTTagCompound compound) {
        NBTTagCompound dyeCompound = compound.func_74775_l(TAG_SKIN_DYE);
        for (int i = 0; i < 8; ++i) {
            if (dyeCompound.func_150297_b(TAG_DYE + i, 7)) {
                this.dyes[i] = dyeCompound.func_74770_j(TAG_DYE + i);
                if (this.dyes[i].length == 4) {
                    this.hasDye[i] = true;
                } else {
                    this.dyes[i] = new byte[]{0, 0, 0, 0};
                }
                if (dyeCompound.func_150297_b(TAG_NAME + i, 8)) {
                    this.names[i] = dyeCompound.func_74779_i(TAG_NAME + i);
                }
            }
            if (!dyeCompound.func_150297_b(TAG_DYE + i + TAG_RED, 1) || !dyeCompound.func_150297_b(TAG_DYE + i + TAG_GREEN, 1) || !dyeCompound.func_150297_b(TAG_DYE + i + TAG_BLUE, 1) || !dyeCompound.func_150297_b(TAG_DYE + i + TAG_TYPE, 1)) continue;
            this.dyes[i] = new byte[]{0, 0, 0, 0};
            this.hasDye[i] = true;
            this.dyes[i][0] = dyeCompound.func_74771_c(TAG_DYE + i + TAG_RED);
            this.dyes[i][1] = dyeCompound.func_74771_c(TAG_DYE + i + TAG_GREEN);
            this.dyes[i][2] = dyeCompound.func_74771_c(TAG_DYE + i + TAG_BLUE);
            this.dyes[i][3] = dyeCompound.func_74771_c(TAG_DYE + i + TAG_TYPE);
            if (!dyeCompound.func_150297_b(TAG_NAME + i, 8)) continue;
            this.names[i] = dyeCompound.func_74779_i(TAG_NAME + i);
        }
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + Arrays.deepHashCode((Object[])this.dyes);
        result = 31 * result + Arrays.hashCode(this.hasDye);
        result = 31 * result + Arrays.hashCode(this.names);
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
        SkinDye other = (SkinDye)obj;
        if (!Arrays.deepEquals((Object[])this.dyes, (Object[])other.dyes)) {
            return false;
        }
        if (!Arrays.equals(this.hasDye, other.hasDye)) {
            return false;
        }
        return Arrays.equals(this.names, other.names);
    }

    public String toString() {
        return "SkinDye [dyes=" + Arrays.toString((Object[])this.dyes) + ", hasDye=" + Arrays.toString(this.hasDye) + ", names=" + Arrays.toString(this.names) + "]";
    }
}

