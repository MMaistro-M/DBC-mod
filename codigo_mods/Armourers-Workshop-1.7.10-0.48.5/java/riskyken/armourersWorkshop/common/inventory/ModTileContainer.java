/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 */
package riskyken.armourersWorkshop.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import riskyken.armourersWorkshop.common.inventory.ModContainer;

public class ModTileContainer<TILETYPE extends TileEntity>
extends ModContainer {
    protected final TILETYPE tileEntity;

    public ModTileContainer(EntityPlayer player, TILETYPE tileEntity) {
        super(player);
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean func_75145_c(EntityPlayer playerIn) {
        return !playerIn.field_70128_L & playerIn.func_70092_e((double)((TileEntity)this.tileEntity).field_145851_c, (double)((TileEntity)this.tileEntity).field_145848_d, (double)((TileEntity)this.tileEntity).field_145849_e) <= 64.0;
    }

    public TILETYPE getTileEntity() {
        return this.tileEntity;
    }
}

