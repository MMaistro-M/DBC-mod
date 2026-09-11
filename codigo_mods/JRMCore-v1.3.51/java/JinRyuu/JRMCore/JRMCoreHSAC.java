package JinRyuu.JRMCore;

import com.jin_ryuu.SwordArtC.Main;
import com.jin_ryuu.SwordArtC.items.ModItems;
import java.util.Random;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class JRMCoreHSAC {
   public static ArmorMaterial GI = EnumHelper.addArmorMaterial("GI", 15, new int[]{3, 5, 5, 5}, 20);
   public static ArmorMaterial GI2 = EnumHelper.addArmorMaterial("GI", 25, new int[]{3, 5, 5, 5}, 20);
   public static ToolMaterial SWORDSHORT = EnumHelper.addToolMaterial("SWORDSHORT", 1, 600, 6.0F, 1.0F, 20);
   public static ToolMaterial SWORDLONG = EnumHelper.addToolMaterial("SWORDLONG", 1, 750, 6.0F, 2.0F, 20);
   public static ToolMaterial CURVED = EnumHelper.addToolMaterial("CURVED", 1, 700, 6.0F, 1.0F, 20);
   public static ToolMaterial DAGGER = EnumHelper.addToolMaterial("DAGGER", 1, 500, 6.0F, 1.0F, 20);
   public static ToolMaterial RAPIER = EnumHelper.addToolMaterial("RAPIER", 1, 850, 6.0F, 1.0F, 20);
   public static ToolMaterial AXE = EnumHelper.addToolMaterial("AXE", 1, 500, 6.0F, 1.0F, 20);
   public static ToolMaterial SPEAR = EnumHelper.addToolMaterial("SPEAR", 1, 800, 6.0F, 2.0F, 20);
   public static ToolMaterial BATTLEAXE = EnumHelper.addToolMaterial("BATTLEAXE", 1, 950, 6.0F, 3.0F, 20);
   public static ToolMaterial WARHAMMER = EnumHelper.addToolMaterial("WARHAMMER", 1, 850, 6.0F, 3.0F, 20);
   public static ToolMaterial SWORD2 = EnumHelper.addToolMaterial("SWORD2", 1, 900, 6.0F, 2.0F, 20);
   public static ToolMaterial KATANA = EnumHelper.addToolMaterial("KATANA", 1, 1000, 6.0F, 2.0F, 20);
   public static final int ah_sword = 10;
   public static final int ah_spear = 11;
   public static final String[] bonusesCanBe = new String[]{"atr", "skill"};
   public static final int atr_STR = 0;
   public static final int atr_AGI = 1;
   public static final int atr_Skill = 2;
   public static final String lvlItem = "lvlItem";
   public static final String lvlUpgrade = "lvlUpgrade";
   public static final String attackMin = "attackMin";
   public static final String attackMax = "attackMax";
   public static final String requires = "requires";

   public static int ah(Item i, int d) {
      if (i instanceof ItemSword) {
         if (((ItemSword)i).func_150932_j().equals(SWORDSHORT.name())) {
            return 10;
         } else {
            return ((ItemSword)i).func_150932_j().equals(SPEAR.name()) ? 11 : d;
         }
      } else {
         return d;
      }
   }

   public static void openGui(int id, EntityPlayer pl) {
      pl.openGui(Main.instance, id, pl.field_70170_p, (int)pl.field_70165_t, (int)pl.field_70163_u, (int)pl.field_70161_v);
   }

   public static void initGui() {
   }

   public static void actionPerformed(GuiButton button) {
   }

   public static void drawScreen(int x, int y, float f) {
   }

   public static void initSkls() {
   }

   public static int SAO_() {
      return 0;
   }

   public static int SAO_getDropLevelBasedOnCoord(Entity e) {
      double x = e.field_70165_t;
      double z = e.field_70161_v;
      return (int)MathHelper.func_76133_a(x * x + z * z);
   }

   public static int SAO_getLvlBasedOnDrop(Entity e) {
      int i = 1 + SAO_getDropLevelBasedOnCoord(e) / 200;
      return i > 300 ? 300 : i;
   }

   public static String[] SAO_getRandomBonus(int requ) {
      String[] s = new String[]{"0", "0", ""};
      int ri = 1 + new Random().nextInt(3);
      ri = ri > 2 ? 2 : ri;
      int rmv = new Random().nextInt(requ * 2);

      for (int i = 0; i < ri; i++) {
         int grmv = new Random().nextInt(rmv + 1);
         rmv -= grmv;
         s[i] = "" + grmv;
      }

      return s;
   }

   public static void addSAOWeaponStats(ItemStack p_92115_1_, Object... args) {
      NBTTagList nbttaglist = SAO_WeaponStatsNBTTag(p_92115_1_);
      boolean flag = true;

      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
         NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
         if (nbttagcompound.func_74779_i("category").length() > 3) {
            return;
         }
      }

      if (flag) {
         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
         nbttagcompound1.func_74778_a("name", "" + args[0]);
         nbttagcompound1.func_74778_a("category", "" + weaponCategory(p_92115_1_.func_77977_a()));
         nbttagcompound1.func_74768_a("lvlItem", Integer.parseInt("" + args[1]));
         nbttagcompound1.func_74768_a("lvlUpgrade", Integer.parseInt("" + args[2]));
         nbttagcompound1.func_74768_a("attackMin", Integer.parseInt("" + args[3]));
         nbttagcompound1.func_74768_a("attackMax", Integer.parseInt("" + args[4]));
         nbttagcompound1.func_74768_a("requires", Integer.parseInt("" + args[5]));
         nbttagcompound1.func_74778_a("bonuses", "" + args[6]);
         nbttaglist.func_74742_a(nbttagcompound1);
      }

      if (!p_92115_1_.func_77942_o()) {
         p_92115_1_.func_77982_d(new NBTTagCompound());
      }

      p_92115_1_.func_77978_p().func_74782_a("SAOWeaponStats", nbttaglist);
   }

   public static NBTTagList SAO_WeaponStatsNBTTag(ItemStack p_92110_1_) {
      return p_92110_1_.field_77990_d != null && p_92110_1_.field_77990_d.func_74764_b("SAOWeaponStats")
         ? (NBTTagList)p_92110_1_.field_77990_d.func_74781_a("SAOWeaponStats")
         : new NBTTagList();
   }

   public static void onItemTooltipEvent(ItemTooltipEvent event) {
      if (event.itemStack.func_77973_b() instanceof ItemSword) {
         NBTTagList nbttaglist = SAO_WeaponStatsNBTTag(event.itemStack);
         if (nbttaglist != null) {
            for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
               String name = nbttaglist.func_150305_b(i).func_74779_i("name");
               String category = nbttaglist.func_150305_b(i).func_74779_i("category");
               int lvlItem = nbttaglist.func_150305_b(i).func_74762_e("lvlItem");
               int lvlUpgrade = nbttaglist.func_150305_b(i).func_74762_e("lvlUpgrade");
               int attackMin = nbttaglist.func_150305_b(i).func_74762_e("attackMin");
               int attackMax = nbttaglist.func_150305_b(i).func_74762_e("attackMax");
               int requires = nbttaglist.func_150305_b(i).func_74762_e("requires");
               String[] bonuses = nbttaglist.func_150305_b(i).func_74779_i("bonuses").split(";");
               name = "";
               event.toolTip.add(name + (lvlUpgrade > 0 ? " +" + lvlUpgrade : ""));
               event.toolTip.add(weaponCatName(category));
               event.toolTip.add("Attack: " + attackMin + "-" + attackMax);
               event.toolTip.add("Type: " + weaponTypes(category));
               event.toolTip.add("Durability: " + (event.itemStack.func_77958_k() - event.itemStack.func_77952_i()));
               event.toolTip.add("Requires: " + requires);
               String[] bonusNams = new String[]{"Strength", "Agility"};

               for (int j = 0; j < bonusNams.length; j++) {
                  if (Integer.parseInt(bonuses[j]) > 0) {
                     event.toolTip.add(bonusNams[j] + ": " + bonuses[j]);
                  }
               }
            }
         }
      }
   }

   public static float getDamage(int dmg, int str, int dex) {
      return JRMCoreH.round(dmg + str + 0.3F * dex, 0, 0);
   }

   public static int getWeaponDamage(ItemStack p_77624_1_, int STR) {
      if (p_77624_1_ != null) {
         NBTTagList nbttaglist = SAO_WeaponStatsNBTTag(p_77624_1_);
         if (nbttaglist != null) {
            int i = 0;
            if (i < nbttaglist.func_74745_c()) {
               int max = nbttaglist.func_150305_b(i).func_74762_e("attackMax");
               int min = nbttaglist.func_150305_b(i).func_74762_e("attackMin");
               int requ = nbttaglist.func_150305_b(i).func_74762_e("requires");
               boolean b = requ <= STR;
               Random r = new Random();
               int dmg = min + (max - min <= 1 ? 1 : r.nextInt(max - min));
               return b ? dmg : 1;
            }
         }
      }

      return 1;
   }

   public static int getWeaponBonus(ItemStack p_77624_1_, int bonus) {
      if (p_77624_1_ != null) {
         NBTTagList nbttaglist = SAO_WeaponStatsNBTTag(p_77624_1_);
         if (nbttaglist != null) {
            int i = 0;
            if (i < nbttaglist.func_74745_c()) {
               String[] bonuses = nbttaglist.func_150305_b(i).func_74779_i("bonuses").split(";");
               return Integer.parseInt(bonuses[bonus]);
            }
         }
      }

      return 0;
   }

   public static int getWeaponStatName(ItemStack p_77624_1_, String statname) {
      if (p_77624_1_ != null) {
         NBTTagList nbttaglist = SAO_WeaponStatsNBTTag(p_77624_1_);
         if (nbttaglist != null) {
            int i = 0;
            if (i < nbttaglist.func_74745_c()) {
               return nbttaglist.func_150305_b(i).func_74762_e(statname);
            }
         }
      }

      return 0;
   }

   public static int getWeaponEquip(ItemStack p_77624_1_) {
      if (p_77624_1_ != null) {
         NBTTagList nbttaglist = SAO_WeaponStatsNBTTag(p_77624_1_);
         if (nbttaglist != null) {
            int i = 0;
            if (i < nbttaglist.func_74745_c()) {
               int attackMax = nbttaglist.func_150305_b(i).func_74762_e("attackMax");
               return getWeaponEquip(p_77624_1_, attackMax);
            }
         }
      }

      return 0;
   }

   public static String weaponTypes(String category) {
      String cat = JRMCoreH.SAO_SkillMap_WeaponTypes[0];

      for (int i = 0; i < JRMCoreH.SAO_SkillMap_Weapons.length; i++) {
         if (JRMCoreH.SAO_SkillMap_Weapons[i].equalsIgnoreCase(category)) {
            cat = JRMCoreH.SAO_SkillMap_WeaponTypes[i];
         }
      }

      return cat;
   }

   public static String weaponCategory(String unLocName) {
      String cat = JRMCoreH.SAO_SkillMap_Weapons[0];

      for (int i = 0; i < JRMCoreH.SAO_Weapon_Cat_Sword.length; i++) {
         if (unLocName.contains(JRMCoreH.SAO_Weapon_Cat_Sword[i])) {
            return JRMCoreH.SAO_SkillMap_Weapons[0];
         }
      }

      for (int i = 0; i < JRMCoreH.SAO_Weapon_Cat_Curved.length; i++) {
         if (unLocName.contains(JRMCoreH.SAO_Weapon_Cat_Curved[i])) {
            return JRMCoreH.SAO_SkillMap_Weapons[1];
         }
      }

      for (int i = 0; i < JRMCoreH.SAO_Weapon_Cat_Dagger.length; i++) {
         if (unLocName.contains(JRMCoreH.SAO_Weapon_Cat_Dagger[i])) {
            return JRMCoreH.SAO_SkillMap_Weapons[2];
         }
      }

      for (int i = 0; i < JRMCoreH.SAO_Weapon_Cat_Rapier.length; i++) {
         if (unLocName.contains(JRMCoreH.SAO_Weapon_Cat_Rapier[i])) {
            return JRMCoreH.SAO_SkillMap_Weapons[3];
         }
      }

      for (int i = 0; i < JRMCoreH.SAO_Weapon_Cat_Axe.length; i++) {
         if (unLocName.contains(JRMCoreH.SAO_Weapon_Cat_Axe[i])) {
            return JRMCoreH.SAO_SkillMap_Weapons[4];
         }
      }

      for (int i = 0; i < JRMCoreH.SAO_Weapon_Cat_Spear.length; i++) {
         if (unLocName.contains(JRMCoreH.SAO_Weapon_Cat_Spear[i])) {
            return JRMCoreH.SAO_SkillMap_Weapons[7];
         }
      }

      for (int i = 0; i < JRMCoreH.SAO_Weapon_Cat_BattleAxe.length; i++) {
         if (unLocName.contains(JRMCoreH.SAO_Weapon_Cat_BattleAxe[i])) {
            return JRMCoreH.SAO_SkillMap_Weapons[8];
         }
      }

      for (int i = 0; i < JRMCoreH.SAO_Weapon_Cat_WarHammer.length; i++) {
         if (unLocName.contains(JRMCoreH.SAO_Weapon_Cat_WarHammer[i])) {
            return JRMCoreH.SAO_SkillMap_Weapons[9];
         }
      }

      for (int i = 0; i < JRMCoreH.SAO_Weapon_Cat_Sword2.length; i++) {
         if (unLocName.contains(JRMCoreH.SAO_Weapon_Cat_Sword2[i])) {
            return JRMCoreH.SAO_SkillMap_Weapons[6];
         }
      }

      for (int i = 0; i < JRMCoreH.SAO_Weapon_Cat_Katana.length; i++) {
         if (unLocName.contains(JRMCoreH.SAO_Weapon_Cat_Katana[i])) {
            return JRMCoreH.SAO_SkillMap_Weapons[10];
         }
      }

      return cat;
   }

   public static String weaponCatName(String category) {
      return JRMCoreH.trl("saoc", "category." + category);
   }

   public static int getWeaponEquip(ItemStack is, int atkmax) {
      return (is.func_77958_k() + atkmax) / 100;
   }

   public String getWeaponName(String name) {
      return JRMCoreH.trl("saoc", "weapon." + name);
   }

   public static void onEntityDrop(LivingDropsEvent event) {
      if (event.source.func_76355_l().equals("player") && event.entity instanceof EntityMob) {
         int rand = (int)(Math.random() * 100.0);
         if (rand < 95) {
            int r = (int)(Math.random() * 100.0);
            Item wep = ModItems.ItemsOW[new Random().nextInt(ModItems.ItemsOW.length)];
            ItemStack stack = new ItemStack(wep, 1, 1);
            if (stack != null) {
               Item item = stack.func_77973_b();
               if (item != null && item instanceof ItemSword) {
                  String category = weaponCategory(((ItemSword)wep).func_150932_j());
                  int lvlItem = SAO_getLvlBasedOnDrop(event.entityLiving);
                  int attackMin = 1 + new Random().nextInt((int)(lvlItem * 8.0F));
                  int attackMax = 3 + new Random().nextInt((int)(lvlItem * 10.0F));
                  if (attackMin > attackMax) {
                     attackMax = attackMin;
                     attackMin /= 2;
                  }

                  int requires = lvlItem;
                  String[] b = SAO_getRandomBonus(requires);
                  String bonuses = "";

                  for (int i = 0; i < b.length; i++) {
                     bonuses = bonuses + b[i] + ";";
                  }

                  addSAOWeaponStats(stack, "RandomName", "" + lvlItem, "0", "" + attackMin, "" + attackMax, "" + requires, bonuses);
                  event.drops
                     .add(new EntityItem(event.entity.field_70170_p, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, stack));
               }
            }
         }
      }
   }

   public static void onItemEvent(ItemEvent event) {
      Entity p = event.entity;
      if (p != null) {
         ItemStack stack = event.entityItem.func_92059_d();
         if (stack != null) {
            Item item = stack.func_77973_b();
            if (item != null && item instanceof ItemSword) {
               String category = weaponCategory(stack.func_77977_a());
               int lvlItem = SAO_getLvlBasedOnDrop(p);
               int attackMin = 1 + new Random().nextInt((int)(lvlItem * 8.0F));
               int attackMax = 3 + new Random().nextInt((int)(lvlItem * 10.0F));
               if (attackMin > attackMax) {
                  attackMax = attackMin;
                  attackMin /= 2;
               }

               int requires = lvlItem;
               String[] b = SAO_getRandomBonus(requires);
               String bonuses = "";

               for (int i = 0; i < b.length; i++) {
                  bonuses = bonuses + b[i] + ";";
               }

               addSAOWeaponStats(stack, "RandomName", "" + lvlItem, "0", "" + attackMin, "" + attackMax, "" + requires, bonuses);
            }
         }
      }
   }

   public static class skill {
   }
}
