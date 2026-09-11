/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.StatCollector
 */
package riskyken.armourersWorkshop.client.guidebook;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.util.StatCollector;
import riskyken.armourersWorkshop.client.guidebook.BookChapterBase;
import riskyken.armourersWorkshop.client.guidebook.BookPage;
import riskyken.armourersWorkshop.client.guidebook.IBook;

@SideOnly(value=Side.CLIENT)
public class BookChapterCredits
extends BookChapterBase {
    public BookChapterCredits(IBook parentBook, String name) {
        super(parentBook, name);
    }

    @Override
    public void createPages() {
        this.addCategoryPage("programing", new String[]{"RiskyKen"});
        this.addCategoryPage("premade Skins", new String[]{"RiskyKen", "Choccie_Bunny", "VermillionX", "Dreamer", "Servantfly", "EXTZ", "Gray_Mooo", "Flummie2000"});
        this.addCategoryPage("textures", new String[]{"RiskyKen", "LordPhrozen", "TheEpicJames", "Thundercat_"});
        this.addCategoryPage("sound", new String[]{"RiskyKen", "Borro55"});
        this.addCategoryPage("localisations", new String[]{"Ethan (zh_CN)", "ISJump (ko_KR)", "VicNightfall (de_DE)", "Shtopm (ru_RU)", "EzerArch (pt_PT)", "EzerArch (pt_BR)", "Flummie2000 (de_DE)", "V972 (ru_RU)", "BredFace (pt_BR)", "Equine0x (fr_FR)", "_Hoppang_ (ko_KR)", "BlackGear27 (es_ES)"});
    }

    private void addCategoryPage(String pageName, String[] people) {
        Arrays.sort(people);
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(StatCollector.func_74838_a((String)(this.getUnlocalizedName() + "." + pageName)));
        lines.add("");
        for (int i = 0; i < people.length; ++i) {
            lines.add(people[i]);
        }
        this.addPage(new BookPage(this.parentBook, lines));
    }
}

