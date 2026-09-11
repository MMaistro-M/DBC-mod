/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.scripted.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.IRecipeEvent;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.controllers.data.RecipeScript;
import noppes.npcs.scripted.event.player.PlayerEvent;

public class RecipeScriptEvent
extends PlayerEvent
implements IRecipeEvent {
    public final Object recipe;
    public final IItemStack[] items;
    public final boolean isAnvil;

    public RecipeScriptEvent(IPlayer player, Object recipe, boolean anvil, IItemStack[] items) {
        super(player);
        this.recipe = recipe;
        this.items = items;
        this.isAnvil = anvil;
    }

    @Override
    public Object getRecipe() {
        return this.recipe;
    }

    @Override
    public IItemStack[] getItems() {
        return this.items;
    }

    @Override
    public boolean isAnvil() {
        return this.isAnvil;
    }

    public static class Post
    extends RecipeScriptEvent
    implements IRecipeEvent.Post {
        private IItemStack result;

        public Post(IPlayer player, Object recipe, boolean isAnvil, IItemStack[] items, IItemStack result) {
            super(player, recipe, isAnvil, items);
            this.result = result;
        }

        @Override
        public IItemStack getCraft() {
            return this.result;
        }

        @Override
        public void setResult(IItemStack stack) {
            this.result = stack;
        }

        @Override
        public String getHookName() {
            return RecipeScript.ScriptType.POST.function;
        }
    }

    @Cancelable
    public static class Pre
    extends RecipeScriptEvent
    implements IRecipeEvent.Pre {
        private String message = "";
        private int xpCost = 0;
        private int materialUsage = 0;

        public Pre(IPlayer player, Object recipe, boolean isAnvil, IItemStack[] items) {
            super(player, recipe, isAnvil, items);
        }

        @Override
        public void setMessage(String message) {
            this.message = message == null ? "" : message;
        }

        @Override
        public String getMessage() {
            return this.message;
        }

        @Override
        public int getXpCost() {
            return this.xpCost;
        }

        @Override
        public void setXpCost(int xpCost) {
            if (this.isAnvil) {
                this.xpCost = xpCost;
            }
        }

        @Override
        public int getMaterialUsage() {
            return this.materialUsage;
        }

        @Override
        public void setMaterialUsage(int materialUsage) {
            if (this.isAnvil) {
                this.materialUsage = materialUsage;
            }
        }

        @Override
        public String getHookName() {
            return RecipeScript.ScriptType.PRE.function;
        }
    }
}

