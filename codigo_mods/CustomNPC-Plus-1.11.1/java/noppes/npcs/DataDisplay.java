/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.LoaderState
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.StringUtils
 */
package noppes.npcs;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StringUtils;
import noppes.npcs.CustomNpcs;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.HitboxData;
import noppes.npcs.controllers.data.SkinOverlay;
import noppes.npcs.controllers.data.TintData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.data.DataSkinOverlays;
import noppes.npcs.util.ValueUtil;

public class DataDisplay {
    public EntityNPCInterface npc;
    public String name;
    public String title = "";
    private int markovGeneratorId = 8;
    private int markovGender = 0;
    public byte skinType = 0;
    public String url = "";
    public GameProfile playerProfile;
    public String texture = "customnpcs:textures/entity/humanmale/Steve.png";
    public String cloakTexture = "";
    public DataSkinOverlays skinOverlayData;
    public long overlayRenderTicks = 0L;
    public AnimationData animationData;
    public HitboxData hitboxData;
    public TintData tintData;
    public String glowTexture = "";
    public int visible = 0;
    public int modelSize = 5;
    public int showName = 0;
    public int modelType = 0;
    public boolean disableLivingAnimation = false;
    public byte showBossBar = 0;
    public ArrayList<UUID> invisibleToList = new ArrayList();
    @SideOnly(value=Side.CLIENT)
    public boolean isInvisibleToMe;
    @SideOnly(value=Side.CLIENT)
    public HashSet<Integer> tempInvisIds;

    public DataDisplay(EntityNPCInterface npc) {
        this.npc = npc;
        this.markovGeneratorId = new Random().nextInt(CustomNpcs.MARKOV_GENERATOR.length - 1);
        this.skinOverlayData = new DataSkinOverlays((Object)npc);
        this.name = this.getRandomName();
        this.animationData = new AnimationData(this);
        this.hitboxData = new HitboxData();
        this.tintData = new TintData();
    }

    public String getRandomName() {
        return CustomNpcs.MARKOV_GENERATOR[this.markovGeneratorId].fetch(this.markovGender);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74778_a("Name", this.name);
        nbttagcompound.func_74768_a("MarkovGeneratorId", this.markovGeneratorId);
        nbttagcompound.func_74768_a("MarkovGender", this.markovGender);
        nbttagcompound.func_74778_a("Title", this.title);
        nbttagcompound.func_74778_a("SkinUrl", this.url);
        nbttagcompound.func_74778_a("Texture", this.texture);
        nbttagcompound.func_74778_a("CloakTexture", this.cloakTexture);
        nbttagcompound.func_74774_a("UsingSkinUrl", this.skinType);
        nbttagcompound.func_74778_a("GlowTexture", this.glowTexture);
        nbttagcompound = this.skinOverlayData.writeToNBT(nbttagcompound);
        nbttagcompound = this.animationData.writeToNBT(nbttagcompound);
        nbttagcompound = this.hitboxData.writeToNBT(nbttagcompound);
        nbttagcompound = this.tintData.writeToNBT(nbttagcompound);
        if (this.playerProfile != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            NBTUtil.func_152460_a((NBTTagCompound)nbttagcompound1, (GameProfile)this.playerProfile);
            nbttagcompound.func_74782_a("SkinUsername", (NBTBase)nbttagcompound1);
        }
        nbttagcompound.func_74768_a("Size", this.modelSize);
        nbttagcompound.func_74768_a("modelType", this.modelType);
        nbttagcompound.func_74768_a("ShowName", this.showName);
        nbttagcompound.func_74768_a("NpcVisible", this.visible);
        nbttagcompound.func_74757_a("NoLivingAnimation", this.disableLivingAnimation);
        nbttagcompound.func_74774_a("BossBar", this.showBossBar);
        boolean saveNonPersistendIDs = Loader.instance().isInState(LoaderState.SERVER_STARTED);
        ArrayList<Integer> nonPersistedIDs = new ArrayList<Integer>();
        NBTTagList list = new NBTTagList();
        for (UUID uuid : this.invisibleToList) {
            EntityPlayer p;
            if (saveNonPersistendIDs && (p = NoppesUtilServer.getPlayer(uuid)) != null) {
                nonPersistedIDs.add(p.func_145782_y());
            }
            list.func_74742_a((NBTBase)new NBTTagString(uuid.toString()));
        }
        if (!nonPersistedIDs.isEmpty()) {
            int[] tempIDArr = nonPersistedIDs.stream().filter(Objects::nonNull).mapToInt(i -> i).toArray();
            nbttagcompound.func_74783_a("InvisibleToNonPersistentID", tempIDArr);
        }
        nbttagcompound.func_74782_a("InvisibleToList", (NBTBase)list);
        return nbttagcompound;
    }

    public void readToNBT(NBTTagCompound nbttagcompound) {
        this.setName(nbttagcompound.func_74779_i("Name"));
        this.setMarkovGeneratorId(nbttagcompound.func_74762_e("MarkovGeneratorId"));
        this.setMarkovGender(nbttagcompound.func_74762_e("MarkovGender"));
        this.title = nbttagcompound.func_74779_i("Title");
        String prevUrl = this.url;
        this.url = nbttagcompound.func_74779_i("SkinUrl");
        byte prevSkinType = this.skinType;
        this.skinType = nbttagcompound.func_74771_c("UsingSkinUrl");
        this.playerProfile = null;
        if (this.skinType == 1) {
            if (nbttagcompound.func_150297_b("SkinUsername", 10)) {
                this.playerProfile = NBTUtil.func_152459_a((NBTTagCompound)nbttagcompound.func_74775_l("SkinUsername"));
            } else if (nbttagcompound.func_150297_b("SkinUsername", 8) && !StringUtils.func_151246_b((String)nbttagcompound.func_74779_i("SkinUsername"))) {
                this.playerProfile = new GameProfile(null, nbttagcompound.func_74779_i("SkinUsername"));
            }
            this.loadProfile();
        }
        String prevTexture = this.texture;
        this.texture = nbttagcompound.func_74779_i("Texture");
        this.cloakTexture = nbttagcompound.func_74779_i("CloakTexture");
        this.glowTexture = nbttagcompound.func_74779_i("GlowTexture");
        if (!nbttagcompound.func_74764_b("SkinOverlayData") && !this.glowTexture.isEmpty()) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.func_74768_a("SkinOverlayID", 0);
            new SkinOverlay(this.glowTexture).writeToNBT(compound);
            if (!nbttagcompound.func_74764_b("SkinOverlayData")) {
                NBTTagList tagList = new NBTTagList();
                tagList.func_74742_a((NBTBase)compound);
                nbttagcompound.func_74782_a("SkinOverlayData", (NBTBase)tagList);
            } else if (!this.glowTexture.isEmpty()) {
                nbttagcompound.func_150295_c("SkinOverlayData", 10).func_74742_a((NBTBase)compound);
                this.glowTexture = "";
            }
        }
        this.skinOverlayData.readFromNBT(nbttagcompound);
        this.animationData.readFromNBT(nbttagcompound);
        this.hitboxData.readFromNBT(nbttagcompound);
        this.tintData.readFromNBT(nbttagcompound);
        this.modelSize = ValueUtil.clamp(nbttagcompound.func_74762_e("Size"), 1, Integer.MAX_VALUE);
        if (this.modelSize > ConfigMain.NpcSizeLimit) {
            this.modelSize = ConfigMain.NpcSizeLimit;
        }
        this.modelType = nbttagcompound.func_74762_e("modelType");
        this.showName = nbttagcompound.func_74762_e("ShowName");
        this.visible = nbttagcompound.func_74762_e("NpcVisible");
        this.disableLivingAnimation = nbttagcompound.func_74767_n("NoLivingAnimation");
        this.showBossBar = nbttagcompound.func_74771_c("BossBar");
        NBTTagList tagList = (NBTTagList)nbttagcompound.func_74781_a("InvisibleToList");
        if (tagList != null) {
            this.invisibleToList.clear();
            for (int i = 0; i < tagList.func_74745_c(); ++i) {
                String nbtTagString = tagList.func_150307_f(i);
                this.invisibleToList.add(UUID.fromString(nbtTagString));
            }
        } else {
            this.invisibleToList = new ArrayList();
        }
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            if (this.tempInvisIds == null) {
                this.tempInvisIds = new HashSet();
            } else {
                this.tempInvisIds.clear();
            }
            int[] tempEntityIDList = nbttagcompound.func_74759_k("InvisibleToNonPersistentID");
            int playerID = Minecraft.func_71410_x().field_71439_g.func_145782_y();
            for (int i : tempEntityIDList) {
                if (i == playerID) {
                    this.isInvisibleToMe = true;
                }
                this.tempInvisIds.add(i);
            }
        }
        if (prevSkinType != this.skinType || !this.texture.equals(prevTexture) || !this.url.equals(prevUrl)) {
            this.npc.textureLocation = null;
        }
        this.npc.updateHitbox();
    }

    public void loadProfile() {
        GameProfile gameprofile;
        if (!(this.playerProfile == null || StringUtils.func_151246_b((String)this.playerProfile.getName()) || MinecraftServer.func_71276_C() == null || this.playerProfile.isComplete() && this.playerProfile.getProperties().containsKey((Object)"textures") || (gameprofile = MinecraftServer.func_71276_C().func_152358_ax().func_152655_a(this.playerProfile.getName())) == null)) {
            Property property = (Property)Iterables.getFirst((Iterable)gameprofile.getProperties().get((Object)"textures"), null);
            if (property == null) {
                gameprofile = MinecraftServer.func_71276_C().func_147130_as().fillProfileProperties(gameprofile, false);
            }
            this.playerProfile = gameprofile;
        }
    }

    public boolean showName() {
        if (this.npc.isKilled()) {
            return false;
        }
        return this.showName == 0 || this.showName == 2 && this.npc.isAttacking();
    }

    public String getSkinTexture() {
        return this.texture;
    }

    public void setSkinTexture(String texture) {
        if (this.texture.equals(texture)) {
            return;
        }
        this.texture = texture;
        this.npc.textureLocation = null;
        this.skinType = 0;
        this.npc.updateClient = true;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (this.name.equals(name)) {
            return;
        }
        this.name = name;
        this.npc.updateClient = true;
    }

    public int getMarkovGender() {
        return this.markovGender;
    }

    public void setMarkovGender(int gender) {
        if (this.markovGender == gender) {
            return;
        }
        this.markovGender = ValueUtil.clamp(gender, 0, 2);
    }

    public int getMarkovGeneratorId() {
        return this.markovGeneratorId;
    }

    public void setMarkovGeneratorId(int id) {
        if (this.markovGeneratorId == id) {
            return;
        }
        this.markovGeneratorId = ValueUtil.clamp(id, 0, CustomNpcs.MARKOV_GENERATOR.length - 1);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean getTempScriptInvisible(int entityId) {
        return this.tempInvisIds.contains(entityId);
    }
}

