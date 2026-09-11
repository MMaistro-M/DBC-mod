/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs;

import java.util.List;
import java.util.Set;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.ICompatibilty;
import noppes.npcs.NBTTags;
import noppes.npcs.controllers.data.Line;
import noppes.npcs.controllers.data.Lines;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.data.ModelRotate;

public class VersionCompatibility {
    public static int ModRev = 23;

    public static void CheckNpcCompatibility(EntityNPCInterface npc, NBTTagCompound compound) {
        NBTTagList list;
        if (npc.npcVersion == ModRev) {
            return;
        }
        if (npc.npcVersion < 23 && compound.func_74764_b("AimWhileShooting")) {
            boolean aimShot = compound.func_74767_n("AimWhileShooting");
            compound.func_74768_a("AimType", !aimShot ? 0 : 1);
        }
        if (npc.npcVersion < 22 && compound.func_74764_b("CanLeap")) {
            boolean canLeap = compound.func_74767_n("CanLeap");
            compound.func_74768_a("LeapType", !canLeap ? 0 : 1);
        }
        if (npc.npcVersion < 19 && compound.func_74764_b("CanDrown")) {
            compound.func_74768_a("DrowningType", compound.func_74767_n("CanDrown") ? 1 : 0);
            compound.func_82580_o("CanDrown");
        }
        if (npc.npcVersion < 18) {
            String cloakTexture = compound.func_74779_i("CloakTexture");
            cloakTexture = cloakTexture.replace("/cloak/Daybreak/", "/cloak/Guilds/Daybreak/");
            cloakTexture = cloakTexture.replace("/cloak/Created/", "/cloak/Extras/");
            cloakTexture = cloakTexture.replace("/cloak/Color Capes/", "/cloak/Color/");
            compound.func_74778_a("CloakTexture", cloakTexture);
        }
        if (npc.npcVersion < 17 && compound.func_74764_b("DialogDarkenScreen")) {
            compound.func_82580_o("DialogDarkenScreen");
        }
        if (npc.npcVersion < 12) {
            VersionCompatibility.CompatabilityFix(compound, npc.advanced.writeToNBT(new NBTTagCompound()));
            VersionCompatibility.CompatabilityFix(compound, npc.ais.writeToNBT(new NBTTagCompound()));
            VersionCompatibility.CompatabilityFix(compound, npc.stats.writeToNBT(new NBTTagCompound()));
            VersionCompatibility.CompatabilityFix(compound, npc.display.writeToNBT(new NBTTagCompound()));
            VersionCompatibility.CompatabilityFix(compound, npc.inventory.writeEntityToNBT(new NBTTagCompound()));
        }
        if (npc.npcVersion < 5) {
            String texture = compound.func_74779_i("Texture");
            texture = texture.replace("/mob/customnpcs/", "customnpcs:textures/entity/");
            texture = texture.replace("/mob/", "customnpcs:textures/entity/");
            compound.func_74778_a("Texture", texture);
        }
        if (npc.npcVersion < 6 && compound.func_74781_a("NpcInteractLines") instanceof NBTTagList) {
            List<String> interactLines = NBTTags.getStringList(compound.func_150295_c("NpcInteractLines", 10));
            Lines lines = new Lines();
            for (int i = 0; i < interactLines.size(); ++i) {
                Line line = new Line();
                line.text = (String)interactLines.toArray()[i];
                lines.lines.put(i, line);
            }
            compound.func_74782_a("NpcInteractLines", (NBTBase)lines.writeToNBT());
            List<String> worldLines = NBTTags.getStringList(compound.func_150295_c("NpcLines", 10));
            lines = new Lines();
            for (int i = 0; i < worldLines.size(); ++i) {
                Line line = new Line();
                line.text = (String)worldLines.toArray()[i];
                lines.lines.put(i, line);
            }
            compound.func_74782_a("NpcLines", (NBTBase)lines.writeToNBT());
            List<String> attackLines = NBTTags.getStringList(compound.func_150295_c("NpcAttackLines", 10));
            lines = new Lines();
            for (int i = 0; i < attackLines.size(); ++i) {
                Line line = new Line();
                line.text = (String)attackLines.toArray()[i];
                lines.lines.put(i, line);
            }
            compound.func_74782_a("NpcAttackLines", (NBTBase)lines.writeToNBT());
            List<String> killedLines = NBTTags.getStringList(compound.func_150295_c("NpcKilledLines", 10));
            lines = new Lines();
            for (int i = 0; i < killedLines.size(); ++i) {
                Line line = new Line();
                line.text = (String)killedLines.toArray()[i];
                lines.lines.put(i, line);
            }
            compound.func_74782_a("NpcKilledLines", (NBTBase)lines.writeToNBT());
        }
        if (npc.npcVersion == 12 && (list = compound.func_150295_c("StartPos", 3)).func_74745_c() == 3) {
            int z = ((NBTTagInt)list.func_74744_a(2)).func_150287_d();
            int y = ((NBTTagInt)list.func_74744_a(1)).func_150287_d();
            int x = ((NBTTagInt)list.func_74744_a(0)).func_150287_d();
            compound.func_74783_a("StartPosNew", new int[]{x, y, z});
        }
        if (npc.npcVersion == 13) {
            boolean bo = compound.func_74767_n("HealthRegen");
            compound.func_74768_a("HealthRegen", bo ? 1 : 0);
            NBTTagCompound comp = compound.func_74775_l("TransformStats");
            bo = comp.func_74767_n("HealthRegen");
            comp.func_74768_a("HealthRegen", bo ? 1 : 0);
            compound.func_74782_a("TransformStats", (NBTBase)comp);
        }
        npc.npcVersion = ModRev;
    }

    public static void CheckModelCompatibility(EntityNPCInterface npc, NBTTagCompound compound) {
        if (npc.npcVersion == ModRev) {
            return;
        }
        NBTTagCompound modelData = compound.func_74775_l("NpcModelData");
        if (npc.npcVersion < 20 && compound.func_74764_b("NpcJob") && compound.func_74762_e("NpcJob") == 9) {
            compound.func_74768_a("NpcJob", 0);
            boolean moving = compound.func_74767_n("PuppetMoving");
            boolean attacking = compound.func_74767_n("PuppetAttacking");
            boolean standing = compound.func_74767_n("PuppetStanding");
            NBTTagCompound head = compound.func_74775_l("PuppetHead");
            NBTTagCompound body = compound.func_74775_l("PuppetBody");
            NBTTagCompound larm = compound.func_74775_l("PuppetLArm");
            NBTTagCompound rarm = compound.func_74775_l("PuppetRArm");
            NBTTagCompound lleg = compound.func_74775_l("PuppetLLeg");
            NBTTagCompound rleg = compound.func_74775_l("PuppetRLeg");
            modelData.func_74757_a("EnableRotation", true);
            ModelRotate newRotation = new ModelRotate();
            newRotation.whileMoving = moving;
            newRotation.whileAttacking = attacking;
            newRotation.whileStanding = standing;
            NBTTagCompound rotation = newRotation.writeToNBT();
            rotation.func_74782_a("PuppetHead", (NBTBase)head);
            rotation.func_74782_a("PuppetBody", (NBTBase)body);
            rotation.func_74782_a("PuppetLArm", (NBTBase)larm);
            rotation.func_74782_a("PuppetRArm", (NBTBase)rarm);
            rotation.func_74782_a("PuppetLLeg", (NBTBase)lleg);
            rotation.func_74782_a("PuppetRLeg", (NBTBase)rleg);
            modelData.func_74782_a("ModelRotation", (NBTBase)rotation);
            compound.func_82580_o("PuppetMoving");
            compound.func_82580_o("PuppetAttacking");
            compound.func_82580_o("PuppetStanding");
            compound.func_82580_o("PuppetHead");
            compound.func_82580_o("PuppetBody");
            compound.func_82580_o("PuppetLArm");
            compound.func_82580_o("PuppetRArm");
            compound.func_82580_o("PuppetLLeg");
            compound.func_82580_o("PuppetRLeg");
        }
        if (npc.npcVersion < 19) {
            if (modelData.func_74764_b("LegParts")) {
                NBTTagCompound legParts = modelData.func_74775_l("LegParts");
                if (legParts.func_74764_b("Texture")) {
                    legParts.func_74778_a("Texture", legParts.func_74779_i("Texture").replace("moreplayermodels:textures", "customnpcs:textures/parts"));
                }
                modelData.func_74782_a("LegParts", (NBTBase)legParts);
            }
            if (modelData.func_74764_b("Parts")) {
                NBTTagList list = modelData.func_150295_c("Parts", 10);
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    NBTTagCompound item = list.func_150305_b(i);
                    if (item.func_74764_b("Texture")) {
                        item.func_74778_a("Texture", item.func_74779_i("Texture").replace("moreplayermodels:textures", "customnpcs:textures/parts"));
                    }
                    list.func_150304_a(i, (NBTBase)item);
                }
                modelData.func_74782_a("Parts", (NBTBase)list);
            }
        }
        compound.func_74782_a("NpcModelData", (NBTBase)modelData);
    }

    public static void CheckAvailabilityCompatibility(ICompatibilty compatibilty, NBTTagCompound compound) {
        if (compatibilty.getVersion() == ModRev) {
            return;
        }
        VersionCompatibility.CompatabilityFix(compound, compatibilty.writeToNBT(new NBTTagCompound()));
        compatibilty.setVersion(ModRev);
    }

    private static void CompatabilityFix(NBTTagCompound compound, NBTTagCompound check) {
        Set tags = check.func_150296_c();
        for (String name : tags) {
            NBTBase nbt = check.func_74781_a(name);
            if (!compound.func_74764_b(name)) {
                compound.func_74782_a(name, nbt);
                continue;
            }
            if (!(nbt instanceof NBTTagCompound) || !(compound.func_74781_a(name) instanceof NBTTagCompound)) continue;
            VersionCompatibility.CompatabilityFix(compound.func_74775_l(name), (NBTTagCompound)nbt);
        }
    }
}

