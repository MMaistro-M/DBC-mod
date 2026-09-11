/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.util.ResourceLocation
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;
import riskyken.armourersWorkshop.common.lib.LibSounds;
import riskyken.armourersWorkshop.utils.UtilColour;

@SideOnly(value=Side.CLIENT)
public class GuiBookTextButton
extends GuiButtonExt {
    public GuiBookTextButton(int id, int xPos, int yPos, int width, String displayString) {
        super(id, xPos, yPos, width, 8, displayString);
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (this.field_146125_m) {
            this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int hoverState = this.func_146114_a(this.field_146123_n);
            int color = UtilColour.getMinecraftColor(8, UtilColour.ColourFamily.MINECRAFT);
            if (hoverState == 2) {
                color = UtilColour.getMinecraftColor(7, UtilColour.ColourFamily.MINECRAFT);
            }
            String buttonText = this.field_146126_j;
            int strWidth = mc.field_71466_p.func_78256_a(buttonText);
            int ellipsisWidth = mc.field_71466_p.func_78256_a("...");
            mc.field_71466_p.func_78276_b(buttonText, this.field_146128_h, this.field_146129_i, color);
        }
    }

    public void func_146113_a(SoundHandler soundHandler) {
        soundHandler.func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation(LibSounds.PAGE_TURN), (float)1.0f));
    }
}

