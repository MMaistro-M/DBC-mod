/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.controllers.data;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.handler.data.ILine;

public class Line
implements ILine {
    public String text = "";
    public String sound = "";
    public boolean hideText = false;

    public Line() {
    }

    public Line(String text) {
        this.text = text;
    }

    public ILine copy() {
        Line line = new Line(this.text);
        line.sound = this.sound;
        line.hideText = this.hideText;
        return line;
    }

    public ILine formatTarget(EntityLivingBase entity) {
        if (entity == null) {
            return this;
        }
        Line line = (Line)this.copy();
        line.text = entity instanceof EntityPlayer ? line.text.replace("@target", ((EntityPlayer)entity).getDisplayName()) : line.text.replace("@target", entity.func_70005_c_());
        return line;
    }

    @Override
    public ILine formatTarget(IEntityLivingBase entityLivingBase) {
        return this.formatTarget((EntityLivingBase)entityLivingBase.getMCEntity());
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getSound() {
        return this.sound;
    }

    @Override
    public void setSound(String sound) {
        this.sound = sound;
    }

    @Override
    public void hideText(boolean hide) {
        this.hideText = hide;
    }

    @Override
    public boolean hideText() {
        return this.hideText;
    }
}

