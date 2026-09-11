package JinRyuu.JRMCore.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AIFollowOwner extends EntityAIBase {
   private JinRyuu.FamilyC.EntityNPC thePet;
   private EntityLivingBase mom;
   private EntityLivingBase dad;
   private EntityLivingBase theOwner;
   World theWorld;
   private double field_75336_f;
   private PathNavigate petPathfinder;
   private int field_75343_h;
   float maxDist;
   float minDist;
   private boolean field_75344_i;

   public AIFollowOwner(JinRyuu.FamilyC.EntityNPC par1EntityTameable, double par2, float par4, float par5) {
      this.thePet = par1EntityTameable;
      this.theWorld = par1EntityTameable.field_70170_p;
      this.field_75336_f = par2;
      this.petPathfinder = par1EntityTameable.func_70661_as();
      this.minDist = par4;
      this.maxDist = par5;
      this.func_75248_a(3);
   }

   public boolean func_75250_a() {
      EntityLivingBase entitylivingbase = this.thePet.field_70170_p.func_72924_a(this.thePet.getDad());
      EntityLivingBase mom = this.thePet.field_70170_p.func_72924_a(this.thePet.getMom());
      Entity flwtrgt = this.thePet.field_70170_p.func_73045_a(this.thePet.getFollowTarget());
      int flw = this.thePet.getFollow();
      if (flw == 2 && mom != null) {
         entitylivingbase = mom;
      }

      if (flw == 3 && flwtrgt != null && flwtrgt instanceof EntityPlayer) {
         entitylivingbase = (EntityLivingBase)flwtrgt;
      }

      if (entitylivingbase == null) {
         this.thePet.setFollow(0);
         return false;
      }

      if (this.thePet.stopMoving()) {
         return false;
      }

      if (this.thePet.func_70068_e(entitylivingbase) < this.minDist * this.minDist) {
         return false;
      }

      this.theOwner = entitylivingbase;
      return true;
   }

   public boolean func_75253_b() {
      return !this.petPathfinder.func_75500_f() && this.thePet.func_70068_e(this.theOwner) > this.maxDist * this.maxDist && !this.thePet.stopMoving();
   }

   public void func_75249_e() {
      this.field_75343_h = 0;
      this.field_75344_i = this.thePet.func_70661_as().func_75486_a();
      this.thePet.func_70661_as().func_75491_a(false);
   }

   public void func_75251_c() {
      this.theOwner = null;
      this.petPathfinder.func_75499_g();
      this.thePet.func_70661_as().func_75491_a(this.field_75344_i);
   }

   public void func_75246_d() {
      this.thePet.func_70671_ap().func_75651_a(this.theOwner, 10.0F, this.thePet.func_70646_bf());
      if (!this.thePet.stopMoving() && --this.field_75343_h <= 0) {
         this.field_75343_h = 10;
         if (!this.petPathfinder.func_75497_a(this.theOwner, this.field_75336_f)
            && !this.thePet.func_110167_bD()
            && this.thePet.func_70068_e(this.theOwner) >= 144.0) {
            int i = MathHelper.func_76128_c(this.theOwner.field_70165_t) - 2;
            int j = MathHelper.func_76128_c(this.theOwner.field_70161_v) - 2;
            int k = MathHelper.func_76128_c(this.theOwner.field_70121_D.field_72338_b);

            for (int l = 0; l <= 4; l++) {
               for (int i1 = 0; i1 <= 4; i1++) {
                  if ((l < 1 || i1 < 1 || l > 3 || i1 > 3)
                     && World.func_147466_a(this.theWorld, i + l, k - 1, j + i1)
                     && !this.theWorld.func_147445_c(i + l, k, j + i1, false)
                     && !this.theWorld.func_147445_c(i + l, k + 1, j + i1, false)) {
                     this.thePet.func_70012_b(i + l + 0.5F, k, j + i1 + 0.5F, this.thePet.field_70177_z, this.thePet.field_70125_A);
                     this.petPathfinder.func_75499_g();
                     return;
                  }
               }
            }
         }
      }
   }
}
