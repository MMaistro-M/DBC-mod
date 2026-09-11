/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api;

import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEnergyBeam;
import noppes.npcs.api.entity.IEnergyDisc;
import noppes.npcs.api.entity.IEnergyExplosion;
import noppes.npcs.api.entity.IEnergyLaser;
import noppes.npcs.api.entity.IEnergyOrb;
import noppes.npcs.api.entity.IEnergyPanel;
import noppes.npcs.api.entity.IEnergySweeper;
import noppes.npcs.api.entity.IEnergyZone;
import noppes.npcs.api.entity.IEntity;

public interface IEnergyHandler {
    public IEnergyOrb createOrb(IWorld var1, IEntity var2, double var3, double var5, double var7, float var9);

    public IEnergyBeam createBeam(IWorld var1, IEntity var2, double var3, double var5, double var7, float var9, float var10);

    public IEnergyDisc createDisc(IWorld var1, IEntity var2, double var3, double var5, double var7, float var9, float var10);

    public IEnergyLaser createLaser(IWorld var1, IEntity var2, double var3, double var5, double var7, float var9);

    public IEnergyZone createHazard(IWorld var1, IEntity var2, double var3, double var5, double var7);

    public IEnergyZone createTrap(IWorld var1, IEntity var2, double var3, double var5, double var7);

    public IEnergySweeper createSweeper(IWorld var1, IEntity var2, double var3, double var5, double var7);

    public IEnergyPanel createPanel(IWorld var1, IEntity var2, double var3, double var5, double var7);

    public IEnergyExplosion createExplosion(IWorld var1, IEntity var2, double var3, double var5, double var7, float var9);
}

