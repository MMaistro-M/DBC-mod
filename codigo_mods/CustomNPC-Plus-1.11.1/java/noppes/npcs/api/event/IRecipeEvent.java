/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.event.IPlayerEvent;
import noppes.npcs.api.item.IItemStack;

public interface IRecipeEvent
extends IPlayerEvent {
    public Object getRecipe();

    public IItemStack[] getItems();

    public boolean isAnvil();

    public static interface Post
    extends IRecipeEvent {
        public IItemStack getCraft();

        public void setResult(IItemStack var1);
    }

    @Cancelable
    public static interface Pre
    extends IRecipeEvent {
        public void setMessage(String var1);

        public String getMessage();

        public int getXpCost();

        public void setXpCost(int var1);

        public int getMaterialUsage();

        public void setMaterialUsage(int var1);
    }
}

