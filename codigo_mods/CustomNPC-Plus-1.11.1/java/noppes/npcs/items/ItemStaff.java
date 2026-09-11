/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import java.awt.Color;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyAnchorData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyCombatData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyHomingData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLifespanData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLightningData;
import kamkeel.npcs.controllers.data.ability.enums.AnchorPoint;
import kamkeel.npcs.entity.EntityAbilityOrb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.constants.EnumNpcToolMaterial;
import noppes.npcs.enchants.EnchantInterface;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.items.ItemNpcInterface;
import noppes.npcs.util.IProjectileCallback;
import org.lwjgl.opengl.GL11;

public class ItemStaff
extends ItemNpcInterface
implements IProjectileCallback {
    private EnumNpcToolMaterial material;
    protected OrbColor color;

    public ItemStaff(int par1, EnumNpcToolMaterial material) {
        super(par1);
        this.material = material;
        this.color = OrbColor.get(material.ordinal());
        this.func_77637_a(CustomItems.tabWeapon);
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)1.0f, (float)1.14f, (float)1.0f);
        GL11.glTranslatef((float)0.14f, (float)-0.3f, (float)0.08f);
    }

    public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int par4) {
        if (world.field_72995_K) {
            return;
        }
        if (stack.field_77990_d == null) {
            return;
        }
        Entity entity = ((WorldServer)world).func_73045_a(stack.field_77990_d.func_74762_e("MagicProjectile"));
        if (!(entity instanceof EntityAbilityOrb)) {
            return;
        }
        EntityAbilityOrb orb = (EntityAbilityOrb)entity;
        if (orb.getChargeProgress() < 1.0f) {
            orb.func_70106_y();
            return;
        }
        orb.startMoving(null);
        world.func_72956_a((Entity)player, "customnpcs:magic.shot", 1.0f, 1.0f);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        int tick = this.func_77626_a(stack) - count;
        int chargeTime = 20 + this.material.getHarvestLevel() * 8;
        if (player.field_70170_p.field_72995_K) {
            EntityAbilityOrb orb;
            Entity existing;
            this.spawnParticle(stack, player);
            if (tick > chargeTime && stack.field_77990_d != null && (existing = player.field_70170_p.func_73045_a(stack.field_77990_d.func_74762_e("MagicProjectile"))) instanceof EntityAbilityOrb && (orb = (EntityAbilityOrb)existing).isCharging()) {
                orb.resetChargeTick();
            }
            return;
        }
        if (tick > chargeTime && stack.field_77990_d != null) {
            EntityAbilityOrb orb;
            Entity existing = ((WorldServer)player.field_70170_p).func_73045_a(stack.field_77990_d.func_74762_e("MagicProjectile"));
            if (existing instanceof EntityAbilityOrb && (orb = (EntityAbilityOrb)existing).isCharging()) {
                orb.resetChargeTick();
            }
            return;
        }
        if (tick == chargeTime) {
            if (!player.field_71075_bZ.field_75098_d && !this.hasInfinite(stack)) {
                if (!this.hasItem(player, CustomItems.mana)) {
                    return;
                }
                this.consumeItem(player, CustomItems.mana);
            }
            player.field_70170_p.func_72956_a((Entity)player, "customnpcs:magic.charge", 1.0f, 1.0f);
            if (stack.field_77990_d == null) {
                stack.field_77990_d = new NBTTagCompound();
            }
            int damage = 6 + this.material.getDamageVsEntity() + player.field_70170_p.field_73012_v.nextInt(4);
            damage = (int)((float)damage + (float)(damage * EnchantInterface.getLevel(EnchantInterface.Damage, stack)) * 0.5f);
            EnergyCombatData combat = new EnergyCombatData();
            combat.damage = damage;
            combat.explosive = true;
            EnergyDisplayData colorData = new EnergyDisplayData(this.getOrbColor(stack, false), this.getOrbColor(stack, true));
            EnergyLightningData lightning = new EnergyLightningData();
            EnergyLifespanData lifespan = new EnergyLifespanData(100.0f, 72000);
            EnergyHomingData homing = new EnergyHomingData();
            homing.speed = 0.5f;
            homing.homingStrength = 0.35f;
            homing.homingRange = 20.0f;
            EntityAbilityOrb orb = new EntityAbilityOrb(player.field_70170_p, (EntityLivingBase)player, null, player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v, 1.0f, colorData, combat, homing, lightning, lifespan);
            orb.setupCharging(new EnergyAnchorData(AnchorPoint.EYE, 0.0f, 0.0f, 1.0f), chargeTime);
            player.field_70170_p.func_72838_d((Entity)orb);
            stack.field_77990_d.func_74768_a("MagicProjectile", orb.func_145782_y());
        }
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int getOrbColor(ItemStack stack, boolean outer) {
        if (this.color == OrbColor.GENERIC) {
            float[] color = EntitySheep.field_70898_d[stack.func_77960_j()];
            return new Color(color[0], color[1], color[2]).getRGB();
        }
        return outer ? this.color.outer : this.color.inner;
    }

    public ItemStack getProjectile(ItemStack stack) {
        if (stack.func_77973_b() == CustomItems.staffWood) {
            return new ItemStack(CustomItems.spellNature);
        }
        if (stack.func_77973_b() == CustomItems.staffStone || stack.func_77973_b() == CustomItems.staffDemonic) {
            return new ItemStack(CustomItems.spellDark);
        }
        if (stack.func_77973_b() == CustomItems.staffIron || stack.func_77973_b() == CustomItems.staffMithril) {
            return new ItemStack(CustomItems.spellHoly);
        }
        if (stack.func_77973_b() == CustomItems.staffBronze) {
            return new ItemStack(CustomItems.spellLightning);
        }
        if (stack.func_77973_b() == CustomItems.staffGold) {
            return new ItemStack(CustomItems.spellFire);
        }
        if (stack.func_77973_b() == CustomItems.staffDiamond || stack.func_77973_b() == CustomItems.staffFrost) {
            return new ItemStack(CustomItems.spellIce);
        }
        if (stack.func_77973_b() == CustomItems.staffEmerald) {
            return new ItemStack(CustomItems.spellArcane);
        }
        return new ItemStack(CustomItems.orb, 1, stack.func_77960_j());
    }

    public void spawnParticle(ItemStack stack, EntityPlayer player) {
        if (stack.func_77973_b() == CustomItems.staffWood) {
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 5, 2);
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 12, 2);
        } else if (stack.func_77973_b() == CustomItems.staffStone || stack.func_77973_b() == CustomItems.staffDemonic) {
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 5649239, 2);
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 4400964, 2);
        } else if (stack.func_77973_b() == CustomItems.staffBronze) {
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 8648694, 2);
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 6091007, 2);
        } else if (stack.func_77973_b() == CustomItems.staffIron || stack.func_77973_b() == CustomItems.staffMithril) {
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 0xFCFFC9, 2);
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 15728535, 2);
        } else if (stack.func_77973_b() == CustomItems.staffGold) {
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 1, 2);
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 14, 2);
        } else if (stack.func_77973_b() == CustomItems.staffDiamond || stack.func_77973_b() == CustomItems.staffFrost) {
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 9756653, 2);
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 4503295, 2);
        } else if (stack.func_77973_b() == CustomItems.staffEmerald) {
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 16761831, 2);
            CustomNpcs.proxy.spawnParticle((EntityLivingBase)player, "Spell", 16487167, 2);
        }
    }

    @Override
    public int func_77619_b() {
        return this.material.getEnchantability();
    }

    public boolean func_77616_k(ItemStack par1ItemStack) {
        return true;
    }

    @Override
    public boolean onImpact(EntityProjectile entityProjectile, EntityLivingBase entity, ItemStack itemstack) {
        int poison;
        int confusion = EnchantInterface.getLevel(EnchantInterface.Confusion, itemstack);
        if (confusion > 0 && entity.func_70681_au().nextInt(4) > confusion) {
            entity.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 100));
        }
        if ((poison = EnchantInterface.getLevel(EnchantInterface.Poison, itemstack)) > 0 && entity.func_70681_au().nextInt(4) > poison) {
            entity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100));
        }
        return false;
    }

    public boolean hasInfinite(ItemStack stack) {
        return EnchantInterface.getLevel(EnchantInterface.Infinite, stack) > 0;
    }

    public static enum OrbColor {
        WOOD(8177688, 6572594),
        STONE(5451099, 0x515151),
        BRONZE(8648694, 6091007),
        IRON(16251589, 15399572),
        DIA(4503295, 9756653),
        GOLD(11731230, 13925426),
        EMERALD(14511607, 13573626),
        DEMONIC(12063818, 4335171),
        FROST(0xFFFFFF, 11324414),
        MITHRIL(16251589, 0xFFFFFF),
        GENERIC(0xFFFFFF, 0x88FFFF);

        public final int inner;
        public final int outer;

        private OrbColor(int inner, int outer) {
            this.inner = inner;
            this.outer = outer;
        }

        public static OrbColor get(int id) {
            if (id >= OrbColor.values().length || id < 0) {
                return GENERIC;
            }
            return OrbColor.values()[id];
        }
    }
}

