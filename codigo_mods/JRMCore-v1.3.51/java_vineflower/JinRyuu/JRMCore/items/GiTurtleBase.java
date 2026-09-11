package JinRyuu.JRMCore.items;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJBRA;
import JinRyuu.JRMCore.JRMCoreHJFC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class GiTurtleBase extends ItemArmor {
   public String modid;
   public String na;
   public ArmorMaterial rl;
   @SideOnly(Side.CLIENT)
   public IIcon field_94604_cx1;
   private IIcon overlayIcon;
   private IIcon emptySlotIcon;
   private static final String[] CLOTH_OVERLAY_NAMES = new String[]{
      "leather_helmet_overlay", "leather_chestplate_overlay", "leather_leggings_overlay", "leather_boots_overlay"
   };

   public GiTurtleBase(ArmorMaterial par2ArmorMaterial, int par3, int par4, String armornamePrefix) {
      super(par2ArmorMaterial, par3, par4);
      this.rl = par2ArmorMaterial;
      this.func_77656_e(par2ArmorMaterial.func_78046_a(par4));
      this.field_77777_bU = 1;
      this.na = armornamePrefix;
      this.modid = JRMCoreH.tjjrmc;
   }

   @SideOnly(Side.CLIENT)
   public ModelBiped giMdl(int slt, EntityLivingBase e) {
      switch (slt) {
         case 0:
            return this.wear(e) ? JRMCoreHJBRA.GiTurtleMdl1 : JRMCoreClient.armor1;
         case 1:
            return this.wear(e) ? JRMCoreHJBRA.GiTurtleMdl1 : JRMCoreClient.armor1;
         case 2:
         default:
            return this.wear(e) ? JRMCoreHJBRA.GiTurtleMdl2 : JRMCoreClient.armor2;
         case 3:
            return this.wear(e) ? JRMCoreHJBRA.GiTurtleMdl1 : JRMCoreClient.armor1;
      }
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

   @SideOnly(Side.CLIENT)
   public void func_94581_a(IIconRegister par1IconRegister) {
      this.field_77791_bV = par1IconRegister.func_94245_a(
         this.modid + ":" + this.func_77658_a().replaceAll("Scoutera", "Scouter").replaceAll("Scouterb", "Scouter")
      );
      this.field_94604_cx1 = par1IconRegister.func_94245_a(field_94603_a[this.field_77881_a]);
      if (this.rl == ArmorMaterial.CLOTH) {
         this.overlayIcon = par1IconRegister.func_94245_a(CLOTH_OVERLAY_NAMES[this.field_77881_a]);
      }

      this.emptySlotIcon = par1IconRegister.func_94245_a(field_94603_a[this.field_77881_a]);
   }

   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
      String r = "";
      String s = "";
      String j = "";
      if (stack.func_77952_i() > stack.func_77958_k() / 2) {
         s = "_dam";
      }

      j = "jbra";
      if (!stack.toString().contains("leg") && !stack.toString().contains("Leg")) {
         r = this.modid + ":armor/" + this.na + "_1" + j + s + ".png";
      } else {
         r = this.modid + ":armor/" + this.na + "_2" + j + s + ".png";
      }

      return new ResourceLocation(this.modid, r) != null ? r : r.replaceAll(s, "");
   }
}
