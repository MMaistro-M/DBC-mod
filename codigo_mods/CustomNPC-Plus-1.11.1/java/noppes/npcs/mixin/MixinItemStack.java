/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.mixin;

import java.util.List;
import kamkeel.npcs.util.AttributeItemUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.global.GuiNpcManageAuction;
import noppes.npcs.client.gui.player.GuiAuctionInterface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ItemStack.class})
public abstract class MixinItemStack {
    @Shadow
    public NBTTagCompound field_77990_d;

    @Shadow
    public abstract boolean func_77942_o();

    @Shadow
    public abstract NBTTagCompound func_77978_p();

    @Inject(method={"getTooltip"}, at={@At(value="TAIL")}, cancellable=true)
    public void getAttributeTooltip(EntityPlayer player, boolean advanced, CallbackInfoReturnable<List<String>> cir) {
        boolean override = false;
        if (Minecraft.func_71410_x().field_71462_r instanceof GuiAuctionInterface || Minecraft.func_71410_x().field_71462_r instanceof GuiNpcManageAuction) {
            override = true;
        }
        List<String> tooltip = cir.getReturnValue();
        if (this.func_77942_o() && this.func_77978_p().func_74764_b("RPGCore")) {
            cir.setReturnValue(AttributeItemUtil.getToolTip(tooltip, this.func_77978_p(), override));
        }
    }
}

