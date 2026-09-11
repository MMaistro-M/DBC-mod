/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.entity.IPixelmon;
import noppes.npcs.compat.PixelmonHelper;
import noppes.npcs.scripted.entity.ScriptAnimal;
import noppes.npcs.util.ValueUtil;

public class ScriptPixelmon<T extends EntityTameable>
extends ScriptAnimal<T>
implements IPixelmon {
    private T pixelmon;
    private NBTTagCompound compound = null;

    public ScriptPixelmon(T pixelmon) {
        super(pixelmon);
        this.pixelmon = pixelmon;
        this.compound = new NBTTagCompound();
        pixelmon.func_70014_b(this.compound);
    }

    public ScriptPixelmon(T pixelmon, NBTTagCompound compound) {
        this(pixelmon);
        this.compound = compound;
    }

    @Override
    public boolean getIsShiny() {
        return this.compound.func_74767_n("IsShiny");
    }

    @Override
    public void setIsShiny(boolean bo) {
        this.compound.func_74757_a("IsShiny", bo);
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public int getLevel() {
        return this.compound.func_74762_e("Level");
    }

    @Override
    public void setLevel(int level) {
        this.compound.func_74768_a("Level", level);
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public int getIV(int type) {
        if (type == 0) {
            return this.compound.func_74762_e("IVHP");
        }
        if (type == 1) {
            return this.compound.func_74762_e("IVAttack");
        }
        if (type == 2) {
            return this.compound.func_74762_e("IVDefence");
        }
        if (type == 3) {
            return this.compound.func_74762_e("IVSpAtt");
        }
        if (type == 4) {
            return this.compound.func_74762_e("IVSpDef");
        }
        if (type == 5) {
            return this.compound.func_74762_e("IVSpeed");
        }
        return -1;
    }

    @Override
    public void setIV(int type, int value) {
        if (type == 0) {
            this.compound.func_74768_a("IVHP", value);
        } else if (type == 1) {
            this.compound.func_74768_a("IVAttack", value);
        } else if (type == 2) {
            this.compound.func_74768_a("IVDefence", value);
        } else if (type == 3) {
            this.compound.func_74768_a("IVSpAtt", value);
        } else if (type == 4) {
            this.compound.func_74768_a("IVSpDef", value);
        } else if (type == 5) {
            this.compound.func_74768_a("IVSpeed", value);
        }
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public int getEV(int type) {
        if (type == 0) {
            return this.compound.func_74762_e("EVHP");
        }
        if (type == 1) {
            return this.compound.func_74762_e("EVAttack");
        }
        if (type == 2) {
            return this.compound.func_74762_e("EVDefence");
        }
        if (type == 3) {
            return this.compound.func_74762_e("EVSpecialAttack");
        }
        if (type == 4) {
            return this.compound.func_74762_e("EVSpecialDefence");
        }
        if (type == 5) {
            return this.compound.func_74762_e("EVSpeed");
        }
        return -1;
    }

    @Override
    public void setEV(int type, int value) {
        if (type == 0) {
            this.compound.func_74768_a("EVHP", value);
        } else if (type == 1) {
            this.compound.func_74768_a("EVAttack", value);
        } else if (type == 2) {
            this.compound.func_74768_a("EVDefence", value);
        } else if (type == 3) {
            this.compound.func_74768_a("EVSpecialAttack", value);
        } else if (type == 4) {
            this.compound.func_74768_a("EVSpecialDefence", value);
        } else if (type == 5) {
            this.compound.func_74768_a("EVSpeed", value);
        }
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public int getStat(int type) {
        if (type == 0) {
            return this.compound.func_74762_e("StatsHP");
        }
        if (type == 1) {
            return this.compound.func_74762_e("StatsAttack");
        }
        if (type == 2) {
            return this.compound.func_74762_e("StatsDefence");
        }
        if (type == 3) {
            return this.compound.func_74762_e("StatsSpecialAttack");
        }
        if (type == 4) {
            return this.compound.func_74762_e("StatsSpecialDefence");
        }
        if (type == 5) {
            return this.compound.func_74762_e("StatsSpeed");
        }
        return -1;
    }

    @Override
    public void setStat(int type, int value) {
        if (type == 0) {
            this.compound.func_74768_a("StatsHP", value);
        } else if (type == 1) {
            this.compound.func_74768_a("StatsAttack", value);
        } else if (type == 2) {
            this.compound.func_74768_a("StatsDefence", value);
        } else if (type == 3) {
            this.compound.func_74768_a("StatsSpecialAttack", value);
        } else if (type == 4) {
            this.compound.func_74768_a("StatsSpecialDefence", value);
        } else if (type == 5) {
            this.compound.func_74768_a("StatsSpeed", value);
        }
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public int getSize() {
        return this.compound.func_74765_d("Growth");
    }

    @Override
    public void setSize(int type) {
        this.compound.func_74777_a("Growth", (short)type);
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public int getHapiness() {
        return this.compound.func_74762_e("Friendship");
    }

    @Override
    public void setHapiness(int value) {
        value = ValueUtil.clamp(value, 0, 255);
        this.compound.func_74768_a("Friendship", value);
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public int getNature() {
        return this.compound.func_74765_d("Nature");
    }

    @Override
    public void setNature(int type) {
        this.compound.func_74777_a("Nature", (short)type);
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public int getPokeball() {
        if (this.compound.func_74764_b("CaughtBall")) {
            return -1;
        }
        return this.compound.func_74762_e("CaughtBall");
    }

    @Override
    public void setPokeball(int type) {
        this.compound.func_74768_a("CaughtBall", type);
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public String getNickname() {
        return this.compound.func_74779_i("Nickname");
    }

    @Override
    public boolean hasNickname() {
        return !this.getNickname().isEmpty();
    }

    @Override
    public void setNickname(String name) {
        this.compound.func_74778_a("Nickname", name);
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public String getMove(int slot) {
        if (!this.compound.func_74764_b("PixelmonMoveID" + slot)) {
            return null;
        }
        return PixelmonHelper.getAttackName(this.compound.func_74762_e("PixelmonMoveID" + slot));
    }

    @Override
    public void setMove(int slot, String move) {
        slot = ValueUtil.clamp(slot, 0, 3);
        int id = PixelmonHelper.getAttackID(move);
        this.compound.func_82580_o("PixelmonMovePP" + slot);
        this.compound.func_82580_o("PixelmonMovePPBase" + slot);
        if (id < 0) {
            this.compound.func_82580_o("PixelmonMoveID" + slot);
        } else {
            this.compound.func_74768_a("PixelmonMoveID" + slot, id);
        }
        int size = 0;
        for (int i = 0; i < 4; ++i) {
            if (!this.compound.func_74764_b("PixelmonMoveID" + i)) continue;
            ++size;
        }
        this.compound.func_74768_a("PixelmonNumberMoves", size);
        this.pixelmon.func_70037_a(this.compound);
    }

    @Override
    public int getType() {
        return 8;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 8 || super.typeOf(type);
    }
}

