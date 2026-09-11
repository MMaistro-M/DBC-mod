/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.common.ForgeHooks
 */
package noppes.npcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeHooks;
import noppes.npcs.NBTTags;
import noppes.npcs.entity.EntityNPCInterface;

public class DataInventory
implements IInventory {
    public static Random random = new Random();
    public HashMap<Integer, ItemStack> items = new HashMap();
    public HashMap<Integer, Double> dropchance = new HashMap();
    public HashMap<Integer, ItemStack> weapons = new HashMap();
    public HashMap<Integer, ItemStack> prevWeapons = new HashMap();
    public HashMap<Integer, ItemStack> armor = new HashMap();
    public int minExp = 0;
    public int maxExp = 0;
    public int lootMode = 0;
    private EntityNPCInterface npc;

    public DataInventory(EntityNPCInterface npc) {
        this.npc = npc;
    }

    public NBTTagCompound writeEntityToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("MinExp", this.minExp);
        nbttagcompound.func_74768_a("MaxExp", this.maxExp);
        nbttagcompound.func_74782_a("NpcInv", (NBTBase)NBTTags.nbtItemStackList(this.items));
        nbttagcompound.func_74782_a("Armor", (NBTBase)NBTTags.nbtItemStackList(this.getArmor()));
        nbttagcompound.func_74782_a("Weapons", (NBTBase)NBTTags.nbtItemStackList(this.getWeapons()));
        nbttagcompound.func_74782_a("DoubleDropChance", (NBTBase)NBTTags.nbtIntegerDoubleMap(this.dropchance));
        nbttagcompound.func_74768_a("LootMode", this.lootMode);
        return nbttagcompound;
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        this.minExp = nbttagcompound.func_74762_e("MinExp");
        this.maxExp = nbttagcompound.func_74762_e("MaxExp");
        this.items = NBTTags.getItemStackList(nbttagcompound.func_150295_c("NpcInv", 10));
        this.setArmor(NBTTags.getItemStackList(nbttagcompound.func_150295_c("Armor", 10)));
        this.setWeapons(NBTTags.getItemStackList(nbttagcompound.func_150295_c("Weapons", 10)));
        if (!nbttagcompound.func_74764_b("DoubleDropChance")) {
            this.dropchance.clear();
            HashMap<Integer, Integer> oldDropChance = NBTTags.getIntegerIntegerMap(nbttagcompound.func_150295_c("DropChance", 10));
            for (int i = 0; i < oldDropChance.entrySet().size(); ++i) {
                this.dropchance.put(i, (double)oldDropChance.get(i));
            }
        } else {
            this.dropchance = NBTTags.getIntegerDoubleMap(nbttagcompound.func_150295_c("DoubleDropChance", 10));
        }
        this.lootMode = nbttagcompound.func_74762_e("LootMode");
    }

    public HashMap<Integer, ItemStack> getWeapons() {
        return this.weapons;
    }

    public void setWeapons(HashMap<Integer, ItemStack> list) {
        this.weapons = list;
    }

    public HashMap<Integer, ItemStack> getArmor() {
        return this.armor;
    }

    public void setArmor(HashMap<Integer, ItemStack> list) {
        this.armor = list;
    }

    public ItemStack getWeapon() {
        return this.weapons.get(0);
    }

    public void setWeapon(ItemStack item) {
        this.weapons.put(0, item);
    }

    public ItemStack getProjectile() {
        return this.weapons.get(1);
    }

    public void setProjectile(ItemStack item) {
        this.weapons.put(1, item);
    }

    public ItemStack getOffHand() {
        return this.weapons.get(2);
    }

    public void setOffHand(ItemStack item) {
        this.weapons.put(2, item);
    }

    public ArrayList<ItemStack> getDroppedItems(DamageSource damagesource) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        ArrayList<EntityItem> list = new ArrayList<EntityItem>();
        for (int i : this.items.keySet()) {
            EntityItem e;
            ItemStack item = this.items.get(i);
            if (item == null) continue;
            double dchance = 100.0;
            double chance = random.nextDouble() * 100.0;
            if (this.dropchance.containsKey(i)) {
                dchance = this.dropchance.get(i);
            }
            if (!(chance < dchance) || (e = this.getEntityItem(item.func_77946_l())) == null) continue;
            list.add(e);
        }
        for (EntityItem e : list) {
            drops.add(e.func_92059_d());
        }
        int enchant = 0;
        if (damagesource.func_76346_g() instanceof EntityPlayer) {
            enchant = EnchantmentHelper.func_77519_f((EntityLivingBase)((EntityLivingBase)damagesource.func_76346_g()));
        }
        if (!ForgeHooks.onLivingDrops((EntityLivingBase)this.npc, (DamageSource)damagesource, list, (int)enchant, (boolean)true, (int)0)) {
            return drops;
        }
        return new ArrayList<ItemStack>();
    }

    public int getDroppedXp() {
        int droppedXp = this.minExp;
        if (this.maxExp - this.minExp > 0) {
            droppedXp += this.npc.field_70170_p.field_73012_v.nextInt(this.maxExp - this.minExp);
        }
        return droppedXp;
    }

    public void dropXp(Entity entity, int droppedXp) {
        while (droppedXp > 0) {
            int var2 = EntityXPOrb.func_70527_a((int)droppedXp);
            droppedXp -= var2;
            if (this.lootMode == 1 && entity instanceof EntityPlayer) {
                this.npc.field_70170_p.func_72838_d((Entity)new EntityXPOrb(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, var2));
                continue;
            }
            this.npc.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.npc.field_70170_p, this.npc.field_70165_t, this.npc.field_70163_u, this.npc.field_70161_v, var2));
        }
    }

    public void dropItems(Entity entity, ArrayList<ItemStack> itemList) {
        ArrayList<EntityItem> list = new ArrayList<EntityItem>();
        for (ItemStack itemStack : itemList) {
            EntityItem e;
            if (itemStack == null || (e = this.getEntityItem(itemStack.func_77946_l())) == null) continue;
            list.add(e);
        }
        for (EntityItem entityItem : list) {
            if (this.lootMode == 1 && entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entity;
                entityItem.field_145804_b = 2;
                this.npc.field_70170_p.func_72838_d((Entity)entityItem);
                ItemStack stack = entityItem.func_92059_d();
                int i = stack.field_77994_a;
                if (!player.field_71071_by.func_70441_a(stack)) continue;
                this.npc.field_70170_p.func_72956_a((Entity)entityItem, "random.pop", 0.2f, ((this.npc.func_70681_au().nextFloat() - this.npc.func_70681_au().nextFloat()) * 0.7f + 1.0f) * 2.0f);
                player.func_71001_a((Entity)entityItem, i);
                if (stack.field_77994_a > 0) continue;
                entityItem.func_70106_y();
                continue;
            }
            this.npc.field_70170_p.func_72838_d((Entity)entityItem);
        }
    }

    public EntityItem getEntityItem(ItemStack itemstack) {
        if (itemstack == null) {
            return null;
        }
        EntityItem entityitem = new EntityItem(this.npc.field_70170_p, this.npc.field_70165_t, this.npc.field_70163_u - (double)0.3f + (double)this.npc.func_70047_e(), this.npc.field_70161_v, itemstack);
        entityitem.field_145804_b = 40;
        float f2 = this.npc.func_70681_au().nextFloat() * 0.5f;
        float f4 = this.npc.func_70681_au().nextFloat() * 3.141593f * 2.0f;
        entityitem.field_70159_w = -MathHelper.func_76126_a((float)f4) * f2;
        entityitem.field_70179_y = MathHelper.func_76134_b((float)f4) * f2;
        entityitem.field_70181_x = 0.2f;
        return entityitem;
    }

    public ItemStack armorItemInSlot(int i) {
        return this.getArmor().get(i);
    }

    public int func_70302_i_() {
        return 15;
    }

    public ItemStack func_70301_a(int i) {
        if (i < 4) {
            return this.armorItemInSlot(i);
        }
        if (i < 7) {
            return this.getWeapons().get(i - 4);
        }
        return this.items.get(i - 7);
    }

    public ItemStack func_70298_a(int par1, int par2) {
        HashMap<Integer, ItemStack> var3;
        int i = 0;
        if (par1 >= 7) {
            var3 = this.items;
            par1 -= 7;
        } else if (par1 >= 4) {
            var3 = this.getWeapons();
            par1 -= 4;
            i = 1;
        } else {
            var3 = this.getArmor();
            i = 2;
        }
        ItemStack var4 = null;
        if (var3.get(par1) != null) {
            if (var3.get((Object)Integer.valueOf((int)par1)).field_77994_a <= par2) {
                var4 = var3.get(par1);
                var3.put(par1, null);
            } else {
                var4 = var3.get(par1).func_77979_a(par2);
                if (var3.get((Object)Integer.valueOf((int)par1)).field_77994_a == 0) {
                    var3.put(par1, null);
                }
            }
        }
        if (i == 1) {
            this.setWeapons(var3);
        }
        if (i == 2) {
            this.setArmor(var3);
        }
        return var4;
    }

    public ItemStack func_70304_b(int par1) {
        HashMap<Integer, ItemStack> var2;
        int i = 0;
        if (par1 >= 7) {
            var2 = this.items;
            par1 -= 7;
        } else if (par1 >= 4) {
            var2 = this.getWeapons();
            par1 -= 4;
            i = 1;
        } else {
            var2 = this.getArmor();
            i = 2;
        }
        if (var2.get(par1) != null) {
            ItemStack var3 = var2.get(par1);
            var2.put(par1, null);
            if (i == 1) {
                this.setWeapons(var2);
            }
            if (i == 2) {
                this.setArmor(var2);
            }
            return var3;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        HashMap<Integer, ItemStack> var3;
        int i = 0;
        if (par1 >= 7) {
            var3 = this.items;
            par1 -= 7;
        } else if (par1 >= 4) {
            var3 = this.getWeapons();
            par1 -= 4;
            i = 1;
        } else {
            var3 = this.getArmor();
            i = 2;
        }
        var3.put(par1, par2ItemStack);
        if (i == 1) {
            this.setWeapons(var3);
        }
        if (i == 2) {
            this.setArmor(var3);
        }
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer var1) {
        return true;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public String func_145825_b() {
        return "NPC Inventory";
    }

    public boolean func_145818_k_() {
        return true;
    }

    public void func_70296_d() {
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }
}

