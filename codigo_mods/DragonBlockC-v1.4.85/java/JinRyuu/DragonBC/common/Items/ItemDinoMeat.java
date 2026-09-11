/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemDinoMeat
extends ItemFood {
    private float l = 1.0f;

    public ItemDinoMeat(int par2, float f, float l) {
        super(par2, f, true);
        this.func_77625_d(16);
        this.func_77637_a(mod_DragonBC.DragonBlockC);
        this.func_77848_i();
        this.l = l;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        String[] names = new String[]{"item.ItemDinoMeat", "item.ItemDinoMeatCooked", "item.ItemDinoMeatBig", "item.ItemDinoMeatCookedBig"};
        int[] values1 = new int[]{10, 20, 15, 30};
        int[] values2 = new int[]{500, 1000, 750, 1500};
        for (int i = 0; i < names.length; ++i) {
            if (!stack.func_77973_b().func_77658_a().equals(names[i])) continue;
            String text = StatCollector.func_74838_a((String)"dbc.ItemDinoMeat.line1");
            if (text.contains("%1$s")) {
                text = JRMCoreH.trl("dbc.ItemDinoMeat.line1", new Object[]{JRMCoreH.format_lz2(values2[i])});
            } else if (i > 0) {
                text = text.replace("10", "" + values1[i]).replace("500", "" + values2[i]);
            }
            list.add(text);
            return;
        }
    }

    public String getTextureFile() {
        return "jinryuudragonbc:";
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("jinryuudragonbc:" + this.func_77658_a());
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        super.func_77654_b(par1ItemStack, par2World, par3EntityPlayer);
        par3EntityPlayer.field_71071_by.func_70441_a(new ItemStack(Items.field_151103_aS));
        return par1ItemStack;
    }

    protected void func_77849_c(ItemStack par1ItemStack, World par2World, EntityPlayer player) {
        if (!par2World.field_72995_K) {
            int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(player);
            byte pwr = JRMCoreH.getByte(player, "jrmcPwrtyp");
            byte rce = JRMCoreH.getByte(player, "jrmcRace");
            byte cls = JRMCoreH.getByte(player, "jrmcClass");
            int maxBody = JRMCoreH.stat((Entity)player, 2, pwr, 2, PlyrAttrbts[2], rce, cls, 0.0f);
            int curBody = JRMCoreH.getInt(player, "jrmcBdy");
            int maxEnergy = JRMCoreH.stat((Entity)player, 5, pwr, 5, PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs(player, (int)pwr));
            int curEnergy = JRMCoreH.getInt(player, "jrmcEnrgy");
            int maxStam = JRMCoreH.stat((Entity)player, 2, pwr, 3, PlyrAttrbts[2], rce, cls, 0.0f);
            int curStam = JRMCoreH.getInt(player, "jrmcStamina");
            int body = (int)((float)curBody + ((float)maxBody * 0.3f > 500.0f ? 500.0f : (float)maxBody * 0.3f) * this.l);
            JRMCoreH.setInt(body > maxBody ? maxBody : body, player, "jrmcBdy");
            int en = (int)((float)curEnergy + ((float)maxEnergy * 0.2f > 500.0f ? 500.0f : (float)maxEnergy * 0.2f) * this.l);
            JRMCoreH.setInt(en > maxEnergy ? maxEnergy : en, player, "jrmcEnrgy");
            int st = (int)((float)curStam + ((float)maxStam * 0.2f > 500.0f ? 500.0f : (float)maxStam * 0.2f) * this.l);
            JRMCoreH.setInt(st > maxStam ? maxStam : st, player, "jrmcStamina");
        }
    }
}

