package JinRyuu.JRMCore.items;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJFC;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ItemBarberSnC extends Item {
   public static Entity barberTarget;

   public ItemBarberSnC(int par2, float par3, boolean par4) {
      this.func_77625_d(1);
      this.func_77656_e(10);
      this.func_77637_a(mod_JRMCore.JRMCore);
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
      par3List.add(JRMCoreH.trl("jrmc", "BarberSnC.line1"));
   }

   public String getTextureFile() {
      return JRMCoreH.tjjrmc + ":";
   }

   public void func_94581_a(IIconRegister iconRegister) {
      this.field_77791_bV = iconRegister.func_94245_a(JRMCoreH.tjjrmc + ":" + this.func_77658_a().substring(5));
   }

   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if (!par3EntityPlayer.field_71075_bZ.field_75098_d && par3EntityPlayer.field_71071_by.func_146028_b(this)) {
      }

      if (par2World.field_72995_K) {
         barberTarget = null;
         par3EntityPlayer.openGui(
            mod_JRMCore.instance,
            8,
            par3EntityPlayer.field_70170_p,
            (int)par3EntityPlayer.field_70165_t,
            (int)par3EntityPlayer.field_70163_u,
            (int)par3EntityPlayer.field_70161_v
         );
      }

      if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
         par1ItemStack.func_77972_a(1, par3EntityPlayer);
      }

      return par1ItemStack;
   }

   public boolean func_111207_a(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
      if (entity.field_70170_p.field_72995_K) {
         if (JRMCoreH.JFC() && JRMCoreHJFC.isChildNPC(entity)) {
            itemstack.func_77972_a(1, entity);
            barberTarget = entity;
            player.openGui(mod_JRMCore.instance, 8, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
         }

         return true;
      } else {
         if (!(entity instanceof IShearable)) {
            return false;
         }

         IShearable target = (IShearable)entity;
         if (target.isShearable(itemstack, entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v)) {
            ArrayList<ItemStack> drops = target.onSheared(
               itemstack,
               entity.field_70170_p,
               (int)entity.field_70165_t,
               (int)entity.field_70163_u,
               (int)entity.field_70161_v,
               EnchantmentHelper.func_77506_a(Enchantment.field_77346_s.field_77352_x, itemstack)
            );
            Random rand = new Random();

            for (ItemStack stack : drops) {
               EntityItem ent = entity.func_70099_a(stack, 1.0F);
               ent.field_70181_x = ent.field_70181_x + rand.nextFloat() * 0.05F;
               ent.field_70159_w = ent.field_70159_w + (rand.nextFloat() - rand.nextFloat()) * 0.1F;
               ent.field_70179_y = ent.field_70179_y + (rand.nextFloat() - rand.nextFloat()) * 0.1F;
            }

            itemstack.func_77972_a(1, entity);
         }

         return true;
      }
   }

   private boolean getChild() {
      return false;
   }
}
