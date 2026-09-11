/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ChatAllowedCharacters
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.SaveBookPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiBook
extends GuiScreen {
    private static final Logger logger = LogManager.getLogger();
    private static final ResourceLocation bookGuiTextures = new ResourceLocation("textures/gui/book.png");
    private final EntityPlayer editingPlayer;
    private final ItemStack bookObj;
    private final boolean bookIsUnsigned;
    private boolean field_146481_r;
    private boolean field_146480_s;
    private int updateCount;
    private int bookImageWidth = 192;
    private int bookImageHeight = 192;
    private int bookTotalPages = 1;
    private int currPage;
    private NBTTagList bookPages;
    private String bookTitle = "";
    private NextPageButton buttonNextPage;
    private NextPageButton buttonPreviousPage;
    private GuiButton buttonDone;
    private GuiButton buttonSign;
    private GuiButton buttonFinalize;
    private GuiButton buttonCancel;
    private int x;
    private int y;
    private int z;

    public GuiBook(EntityPlayer par1EntityPlayer, ItemStack item, int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.editingPlayer = par1EntityPlayer;
        this.bookObj = item;
        boolean bl = this.bookIsUnsigned = item.func_77973_b() == Items.field_151099_bA;
        if (item.func_77942_o()) {
            NBTTagCompound nbttagcompound = item.func_77978_p();
            this.bookPages = nbttagcompound.func_150295_c("pages", 8);
            if (this.bookPages != null) {
                this.bookPages = (NBTTagList)this.bookPages.func_74737_b();
                this.bookTotalPages = this.bookPages.func_74745_c();
                if (this.bookTotalPages < 1) {
                    this.bookTotalPages = 1;
                }
            }
        }
        if (this.bookPages == null && this.bookIsUnsigned) {
            this.bookPages = new NBTTagList();
            this.bookPages.func_74742_a((NBTBase)new NBTTagString(""));
            this.bookTotalPages = 1;
        }
    }

    public void func_73876_c() {
        super.func_73876_c();
        ++this.updateCount;
    }

    public boolean func_73868_f() {
        return false;
    }

    public void func_73866_w_() {
        this.field_146292_n.clear();
        Keyboard.enableRepeatEvents((boolean)true);
        if (this.bookIsUnsigned) {
            this.buttonSign = new GuiButton(3, this.field_146294_l / 2 - 100, 4 + this.bookImageHeight, 98, 20, I18n.func_135052_a((String)"book.signButton", (Object[])new Object[0]));
            this.field_146292_n.add(this.buttonSign);
            this.buttonDone = new GuiButton(0, this.field_146294_l / 2 + 2, 4 + this.bookImageHeight, 98, 20, I18n.func_135052_a((String)"gui.done", (Object[])new Object[0]));
            this.field_146292_n.add(this.buttonDone);
            this.buttonFinalize = new GuiButton(5, this.field_146294_l / 2 - 100, 4 + this.bookImageHeight, 98, 20, I18n.func_135052_a((String)"book.finalizeButton", (Object[])new Object[0]));
            this.field_146292_n.add(this.buttonFinalize);
            this.buttonCancel = new GuiButton(4, this.field_146294_l / 2 + 2, 4 + this.bookImageHeight, 98, 20, I18n.func_135052_a((String)"gui.cancel", (Object[])new Object[0]));
            this.field_146292_n.add(this.buttonCancel);
        } else {
            this.buttonDone = new GuiButton(0, this.field_146294_l / 2 - 100, 4 + this.bookImageHeight, 200, 20, I18n.func_135052_a((String)"gui.done", (Object[])new Object[0]));
            this.field_146292_n.add(this.buttonDone);
        }
        int i = (this.field_146294_l - this.bookImageWidth) / 2;
        int b0 = 2;
        this.buttonNextPage = new NextPageButton(1, i + 120, b0 + 154, true);
        this.field_146292_n.add(this.buttonNextPage);
        this.buttonPreviousPage = new NextPageButton(2, i + 38, b0 + 154, false);
        this.field_146292_n.add(this.buttonPreviousPage);
        this.updateButtons();
    }

    public void func_146281_b() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    private void updateButtons() {
        this.buttonNextPage.field_146125_m = !this.field_146480_s && (this.currPage < this.bookTotalPages - 1 || this.bookIsUnsigned);
        this.buttonPreviousPage.field_146125_m = !this.field_146480_s && this.currPage > 0;
        boolean bl = this.buttonDone.field_146125_m = !this.bookIsUnsigned || !this.field_146480_s;
        if (this.bookIsUnsigned) {
            this.buttonSign.field_146125_m = !this.field_146480_s;
            this.buttonCancel.field_146125_m = this.field_146480_s;
            this.buttonFinalize.field_146125_m = this.field_146480_s;
            this.buttonFinalize.field_146124_l = this.bookTitle.trim().length() > 0;
        }
    }

    private void sendBookToServer(boolean sign) {
        if (this.bookIsUnsigned && this.field_146481_r && this.bookPages != null) {
            String s;
            while (this.bookPages.func_74745_c() > 1 && (s = this.bookPages.func_150307_f(this.bookPages.func_74745_c() - 1)).length() == 0) {
                this.bookPages.func_74744_a(this.bookPages.func_74745_c() - 1);
            }
            if (this.bookObj.func_77942_o()) {
                NBTTagCompound nbttagcompound = this.bookObj.func_77978_p();
                nbttagcompound.func_74782_a("pages", (NBTBase)this.bookPages);
            } else {
                this.bookObj.func_77983_a("pages", (NBTBase)this.bookPages);
            }
            if (sign) {
                this.bookObj.func_77983_a("author", (NBTBase)new NBTTagString(this.editingPlayer.func_70005_c_()));
                this.bookObj.func_77983_a("title", (NBTBase)new NBTTagString(this.bookTitle.trim()));
                this.bookObj.func_150996_a(Items.field_151164_bB);
            }
            PacketClient.sendClient(new SaveBookPacket(this.x, this.y, this.z, sign, this.bookObj.func_77955_b(new NBTTagCompound())));
        }
    }

    protected void func_146284_a(GuiButton p_146284_1_) {
        if (p_146284_1_.field_146124_l) {
            if (p_146284_1_.field_146127_k == 0) {
                this.field_146297_k.func_147108_a((GuiScreen)null);
                this.sendBookToServer(false);
            } else if (p_146284_1_.field_146127_k == 3 && this.bookIsUnsigned) {
                this.field_146480_s = true;
            } else if (p_146284_1_.field_146127_k == 1) {
                if (this.currPage < this.bookTotalPages - 1) {
                    ++this.currPage;
                } else if (this.bookIsUnsigned) {
                    this.addNewPage();
                    if (this.currPage < this.bookTotalPages - 1) {
                        ++this.currPage;
                    }
                }
            } else if (p_146284_1_.field_146127_k == 2) {
                if (this.currPage > 0) {
                    --this.currPage;
                }
            } else if (p_146284_1_.field_146127_k == 5 && this.field_146480_s) {
                this.sendBookToServer(true);
                this.field_146297_k.func_147108_a((GuiScreen)null);
            } else if (p_146284_1_.field_146127_k == 4 && this.field_146480_s) {
                this.field_146480_s = false;
            }
            this.updateButtons();
        }
    }

    private void addNewPage() {
        if (this.bookPages != null && this.bookPages.func_74745_c() < 50) {
            this.bookPages.func_74742_a((NBTBase)new NBTTagString(""));
            ++this.bookTotalPages;
            this.field_146481_r = true;
        }
    }

    protected void func_73869_a(char par1, int par2) {
        super.func_73869_a(par1, par2);
        if (this.bookIsUnsigned) {
            if (this.field_146480_s) {
                this.func_146460_c(par1, par2);
            } else {
                this.keyTypedInBook(par1, par2);
            }
        }
    }

    private void keyTypedInBook(char p_146463_1_, int p_146463_2_) {
        switch (p_146463_1_) {
            case '\u0016': {
                this.func_146459_b(GuiScreen.func_146277_j());
                return;
            }
        }
        switch (p_146463_2_) {
            case 14: {
                String s = this.func_146456_p();
                if (s.length() > 0) {
                    this.func_146457_a(s.substring(0, s.length() - 1));
                }
                return;
            }
            case 28: 
            case 156: {
                this.func_146459_b("\n");
                return;
            }
        }
        if (ChatAllowedCharacters.func_71566_a((char)p_146463_1_)) {
            this.func_146459_b(Character.toString(p_146463_1_));
        }
    }

    private void func_146460_c(char p_146460_1_, int p_146460_2_) {
        switch (p_146460_2_) {
            case 14: {
                if (!this.bookTitle.isEmpty()) {
                    this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 1);
                    this.updateButtons();
                }
                return;
            }
            case 28: 
            case 156: {
                if (!this.bookTitle.isEmpty()) {
                    this.sendBookToServer(true);
                    this.field_146297_k.func_147108_a((GuiScreen)null);
                }
                return;
            }
        }
        if (this.bookTitle.length() < 16 && ChatAllowedCharacters.func_71566_a((char)p_146460_1_)) {
            this.bookTitle = this.bookTitle + Character.toString(p_146460_1_);
            this.updateButtons();
            this.field_146481_r = true;
        }
    }

    private String func_146456_p() {
        return this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.func_74745_c() ? this.bookPages.func_150307_f(this.currPage) : "";
    }

    private void func_146457_a(String p_146457_1_) {
        if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.func_74745_c()) {
            this.bookPages.func_150304_a(this.currPage, (NBTBase)new NBTTagString(p_146457_1_));
            this.field_146481_r = true;
        }
    }

    private void func_146459_b(String p_146459_1_) {
        String s1 = this.func_146456_p();
        String s2 = s1 + p_146459_1_;
        int i = this.field_146289_q.func_78267_b(s2 + "" + EnumChatFormatting.BLACK + "_", 118);
        if (i <= 118 && s2.length() < 256) {
            this.func_146457_a(s2);
        }
    }

    public void func_73863_a(int par1, int par2, float par3) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(bookGuiTextures);
        int k = (this.field_146294_l - this.bookImageWidth) / 2;
        int b0 = 2;
        this.func_73729_b(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
        if (this.field_146480_s) {
            String s = this.bookTitle;
            if (this.bookIsUnsigned) {
                s = this.updateCount / 6 % 2 == 0 ? s + "" + EnumChatFormatting.BLACK + "_" : s + "" + EnumChatFormatting.GRAY + "_";
            }
            String s1 = I18n.func_135052_a((String)"book.editTitle", (Object[])new Object[0]);
            int l = this.field_146289_q.func_78256_a(s1);
            this.field_146289_q.func_78276_b(s1, k + 36 + (116 - l) / 2, b0 + 16 + 16, 0);
            int i1 = this.field_146289_q.func_78256_a(s);
            this.field_146289_q.func_78276_b(s, k + 36 + (116 - i1) / 2, b0 + 48, 0);
            String s2 = I18n.func_135052_a((String)"book.byAuthor", (Object[])new Object[]{this.editingPlayer.func_70005_c_()});
            int j1 = this.field_146289_q.func_78256_a(s2);
            this.field_146289_q.func_78276_b(EnumChatFormatting.DARK_GRAY + s2, k + 36 + (116 - j1) / 2, b0 + 48 + 10, 0);
            String s3 = I18n.func_135052_a((String)"book.finalizeWarning", (Object[])new Object[0]);
            this.field_146289_q.func_78279_b(s3, k + 36, b0 + 80, 116, 0);
        } else {
            String s = I18n.func_135052_a((String)"book.pageIndicator", (Object[])new Object[]{this.currPage + 1, this.bookTotalPages});
            String s1 = "";
            if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.func_74745_c()) {
                s1 = this.bookPages.func_150307_f(this.currPage);
            }
            if (this.bookIsUnsigned) {
                s1 = this.field_146289_q.func_78260_a() ? s1 + "_" : (this.updateCount / 6 % 2 == 0 ? s1 + "" + EnumChatFormatting.BLACK + "_" : s1 + "" + EnumChatFormatting.GRAY + "_");
            }
            int l = this.field_146289_q.func_78256_a(s);
            this.field_146289_q.func_78276_b(s, k - l + this.bookImageWidth - 44, b0 + 16, 0);
            this.field_146289_q.func_78279_b(s1, k + 36, b0 + 16 + 16, 116, 0);
        }
        super.func_73863_a(par1, par2, par3);
    }

    @SideOnly(value=Side.CLIENT)
    static class NextPageButton
    extends GuiButton {
        private final boolean field_146151_o;

        public NextPageButton(int par1, int par2, int par3, boolean par4) {
            super(par1, par2, par3, 23, 13, "");
            this.field_146151_o = par4;
        }

        public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
            if (this.field_146125_m) {
                boolean flag = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                p_146112_1_.func_110434_K().func_110577_a(bookGuiTextures);
                int k = 0;
                int l = 192;
                if (flag) {
                    k += 23;
                }
                if (!this.field_146151_o) {
                    l += 13;
                }
                this.func_73729_b(this.field_146128_h, this.field_146129_i, k, l, 23, 13);
            }
        }
    }
}

