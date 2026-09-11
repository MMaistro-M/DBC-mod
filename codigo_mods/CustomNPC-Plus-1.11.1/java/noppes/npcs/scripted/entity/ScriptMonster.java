/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.monster.EntityMob
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.monster.EntityMob;
import noppes.npcs.api.entity.IMonster;
import noppes.npcs.scripted.entity.ScriptLiving;

public class ScriptMonster<T extends EntityMob>
extends ScriptLiving<T>
implements IMonster {
    public ScriptMonster(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 3 ? true : super.typeOf(type);
    }
}

