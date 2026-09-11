package JinRyuu.JRMCore.items;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreH2;
import JinRyuu.JRMCore.JRMCoreHJBRA;
import JinRyuu.JRMCore.JRMCoreHJFC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class ItemVanity extends Item {
   private int defcol = JRMCoreH2.cols[15];
   private String Display = "Color1";
   public String armorNamePrefix;
   public String modid;
   public String na;
   public ArmorMaterial rl;
   public int id = -1;
   public final int armorType;

   public ItemVanity(int defcol, int armorType, String armornamePrefix) {
      this.defcol = defcol;
      this.armorType = armorType;
      this.na = armornamePrefix;
      this.func_77656_e(320);
      this.func_77625_d(1);
      this.id = -1;
   }

   public ItemVanity(int defcol, int armorType, String armornamePrefix, int id) {
      this.defcol = defcol;
      this.armorType = armorType;
      this.na = armornamePrefix;
      this.func_77656_e(320);
      this.func_77625_d(1);
      this.id = id;
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
      par3List.add(JRMCoreH.trl("jrmc", "Vanity"));
      par3List.add(JRMCoreH.trl("jrmc", "descvanityColorable1"));
      par3List.add(JRMCoreH.trl("jrmc", "descvanityColorable2"));
      if (this.hasColor(par1ItemStack)) {
         par3List.add(JRMCoreH.trl("jrmc", "BodysuitColor") + ": " + JRMCoreH.trl("jrmc", this.getColorReadable(par1ItemStack)));
      }
   }

   @SideOnly(Side.CLIENT)
   public ModelBiped giMdl(int slt, EntityLivingBase e) {
      if (this.wear(e)) {
         boolean has = false;

         for (int i = 0; i < ItemsJRMC.ItemsVanityNum.length; i++) {
            if (ItemsJRMC.ItemVanity3[i] > -1 && this.id == ItemsJRMC.ItemVanity3[i]) {
               has = true;
               return JRMCoreHJBRA.JRMC_GiTurtleMdl2[this.id];
            }
         }

         if (!has) {
            if (slt != 5) {
               if (this.func_77658_a().contains("Head")) {
                  return JRMCoreHJBRA.GiTurtleMdl2;
               }

               return JRMCoreHJBRA.GiTurtleMdl3;
            }

            return JRMCoreHJBRA.GiTurtleMdl2;
         }
      }

      return null;
   }

   public boolean wear(EntityLivingBase e) {
      return JRMCoreH.JBRA() && (e instanceof EntityPlayer || JRMCoreH.JFC() && JRMCoreHJFC.isChildNPC(e));
   }

   @SideOnly(Side.CLIENT)
   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int par3) {
      ModelBiped modelbiped = this.giMdl(par3, entityLiving);
      if (this.wear(entityLiving)) {
         modelbiped = JRMCoreHJBRA.showModel(modelbiped, entityLiving, itemStack, par3);
      } else {
         modelbiped.field_78116_c.field_78806_j = par3 == 0;
         modelbiped.field_78114_d.field_78806_j = false;
         modelbiped.field_78115_e.field_78806_j = par3 == 1 || par3 == 2;
         modelbiped.field_78112_f.field_78806_j = par3 == 1;
         modelbiped.field_78113_g.field_78806_j = par3 == 1;
         modelbiped.field_78123_h.field_78806_j = par3 == 2 || par3 == 3;
         modelbiped.field_78124_i.field_78806_j = par3 == 2 || par3 == 3;
         if (entityLiving instanceof EntityMob) {
            modelbiped.field_78112_f.field_78806_j = false;
            modelbiped.field_78113_g.field_78806_j = false;
         }

         ItemStack var11 = entityLiving.func_70694_bm();
         modelbiped.field_78120_m = var11 != null ? 1 : 0;
         if (var11 != null && entityLiving instanceof EntityPlayer && ((EntityPlayer)entityLiving).func_71052_bv() > 0) {
            EnumAction var12 = var11.func_77975_n();
            if (var12 == EnumAction.block) {
               modelbiped.field_78120_m = 3;
            } else if (var12 == EnumAction.bow) {
               modelbiped.field_78118_o = true;
            }
         }

         modelbiped.field_78117_n = entityLiving.func_70093_af();
         modelbiped.field_78093_q = entityLiving.func_70115_ae();
         modelbiped.field_78091_s = entityLiving.func_70631_g_();
      }

      return modelbiped;
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
      return this.modid + ":";
   }

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IIconRegister iconRegister) {
      this.field_77791_bV = iconRegister.func_94245_a(this.modid + ":" + this.func_77658_a().replaceAll("item.", ""));
   }

   @SideOnly(Side.CLIENT)
   public int func_82790_a(ItemStack item, int var) {
      return this.getColor(item);
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

   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
      String r = "";
      String s = "";
      String j = "";
      if (stack.func_77952_i() > stack.func_77958_k() / 2) {
         s = "_dam";
      }

      j = "jbra";
      if (stack.toString().contains("leg") || stack.toString().contains("Leg")) {
         r = this.modid + ":armor/" + this.na + "_2" + j + s + ".png";
      } else if (stack.toString().contains("boot") || stack.toString().contains("Boot")) {
         r = this.modid + ":armor/" + this.na + "_3" + j + s + ".png";
      } else if (!stack.toString().contains("head") && !stack.toString().contains("Head")) {
         r = this.modid + ":armor/" + this.na + "_1" + j + s + ".png";
      } else {
         r = this.modid + ":armor/" + this.na + "_4" + j + s + ".png";
      }

      return new ResourceLocation(this.modid, r) != null ? r : r.replaceAll(s, "");
   }
}
