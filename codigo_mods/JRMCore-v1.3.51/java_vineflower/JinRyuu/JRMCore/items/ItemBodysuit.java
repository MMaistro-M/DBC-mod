package JinRyuu.JRMCore.items;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreH2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemBodysuit extends Item {
   private int defcol = JRMCoreH2.cols[15];
   private String Display = "Color1";

   public ItemBodysuit(int defcol) {
      this.defcol = defcol;
      this.func_77656_e(320);
      this.func_77625_d(1);
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
      if (this.hasColor(par1ItemStack)) {
         par3List.add(JRMCoreH.trl("jrmc", "BodysuitColor") + ": " + JRMCoreH.trl("jrmc", this.getColorReadable(par1ItemStack)));
      }
   }

   public String getColorReadable(ItemStack par1) {
      int i = this.getColor(par1);

      for (int j = 0; j < JRMCoreH2.cols.length; j++) {
         if (JRMCoreH2.cols[j] == i) {
            return JRMCoreH2.colNams[j];
         }
      }

      return JRMCoreH2.colNams[15];
   }

   public String getTextureFile() {
      return JRMCoreH.tjjrmc + ":";
   }

   public void func_94581_a(IIconRegister iconRegister) {
      this.field_77791_bV = iconRegister.func_94245_a(JRMCoreH.tjjrmc + ":" + this.func_77658_a().replaceAll("item.", ""));
   }

   public boolean hasColor(ItemStack p_82816_1_) {
      return !p_82816_1_.func_77942_o()
         ? false
         : (!p_82816_1_.func_77978_p().func_150297_b(this.Display, 10) ? false : p_82816_1_.func_77978_p().func_74775_l(this.Display).func_150297_b("color", 3));
   }

   public int getColor(ItemStack p_82814_1_) {
      NBTTagCompound nbttagcompound = p_82814_1_.func_77978_p();
      if (nbttagcompound == null) {
         return this.defcol;
      }

      NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l(this.Display);
      return nbttagcompound1 == null ? 10511680 : (nbttagcompound1.func_150297_b("color", 3) ? nbttagcompound1.func_74762_e("color") : this.defcol);
   }

   @SideOnly(Side.CLIENT)
   public int func_82790_a(ItemStack item, int var) {
      return this.getColor(item);
   }

   public void removeColor(ItemStack p_82815_1_) {
      NBTTagCompound nbttagcompound = p_82815_1_.func_77978_p();
      if (nbttagcompound != null) {
         NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l(this.Display);
         if (nbttagcompound1.func_74764_b("color")) {
            nbttagcompound1.func_82580_o("color");
         }
      }
   }

   public ItemStack setColor(ItemStack p_82813_1_, int p_82813_2_) {
      NBTTagCompound nbttagcompound = p_82813_1_.func_77978_p();
      if (nbttagcompound == null) {
         nbttagcompound = new NBTTagCompound();
         p_82813_1_.func_77982_d(nbttagcompound);
      }

      NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l(this.Display);
      if (!nbttagcompound.func_150297_b(this.Display, 10)) {
         nbttagcompound.func_74782_a(this.Display, nbttagcompound1);
      }

      nbttagcompound1.func_74768_a("color", p_82813_2_);
      p_82813_1_.func_77982_d(nbttagcompound);
      return p_82813_1_;
   }
}
