/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import kamkeel.npcs.network.packets.request.script.RecipeScriptPacket;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.SingleScriptHandler;
import noppes.npcs.janino.EventJaninoScript;

public class RecipeScript
extends SingleScriptHandler
implements IScriptHandlerPacket {
    private int recipeId = -1;
    private boolean anvil = false;

    public RecipeScript() {
    }

    public RecipeScript(int recipeId, boolean anvil) {
        this.recipeId = recipeId;
        this.anvil = anvil;
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.RECIPE;
    }

    @Override
    public IScriptUnit createJaninoScriptUnit() {
        return new EventJaninoScript(ScriptContext.RECIPE);
    }

    @Override
    public String noticeString() {
        return "RecipeScript";
    }

    @Override
    public void requestData() {
        if (this.recipeId >= 0) {
            RecipeScriptPacket.Get(this.anvil, this.recipeId);
        }
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        if (this.recipeId >= 0) {
            RecipeScriptPacket.Save(this.anvil, this.recipeId, index, totalCount, nbt);
        }
    }

    public static enum ScriptType {
        PRE("pre"),
        POST("post");

        public final String function;

        private ScriptType(String functionName) {
            this.function = functionName;
        }
    }
}

