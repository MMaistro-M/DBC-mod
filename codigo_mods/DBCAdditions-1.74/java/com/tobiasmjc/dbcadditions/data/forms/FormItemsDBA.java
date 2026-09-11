/*
 * Decompiled with CFR 0.152.
 */
package com.tobiasmjc.dbcadditions.data.forms;

import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.FormItem;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import java.util.LinkedHashSet;
import java.util.Set;

public class FormItemsDBA {
    private static byte[] SaiyanAndHalfSaiyan = new byte[]{1, 2};
    private static byte[] SaiyanHalfSaiyanAndHuman = new byte[]{0, 1, 2};
    private static byte[] Human = new byte[]{0};
    private static byte[] Namekian = new byte[]{3};
    private static byte[] Arcosian = new byte[]{4};
    private static byte[] Majin = new byte[]{5};
    private static byte[] BioAndroid = new byte[]{(byte)(DBCARaces.BIO_ANDROID.ID + 10)};
    public static Set<FormItem> FormItems = new LinkedHashSet<FormItem>();
    public static Set<FormItem> FormItemsHost = new LinkedHashSet<FormItem>();
    public static FormItem SSG2 = new FormItem("jinryuujrmcore.SuperG2", SaiyanAndHalfSaiyan, 1);
    public static FormItem SS2 = new FormItem("jinryuujrmcore.Super2", SaiyanAndHalfSaiyan, 5);
    public static FormItem SS4 = new FormItem("jinryuujrmcore.Super4", SaiyanAndHalfSaiyan, 7).setTailRequired(true);
    public static FormItem SSGod = new FormItem("jinryuujrmcore.SuperG", SaiyanAndHalfSaiyan, 5, 0);
    public static FormItem SSBlue = new FormItem("jinryuujrmcore.SuperB", SaiyanAndHalfSaiyan, 5, 1);
    public static FormItem SSBlueEv = new FormItem("jinryuujrmcore.SuperBS", SaiyanAndHalfSaiyan, 5, 2);
    public static FormItem Buffed = new FormItem("jinryuujrmcore.HForm2", Human, 1);
    public static FormItem FullReleasedHuman = new FormItem("jinryuujrmcore.HForm1", Human, 2);
    public static FormItem GodHuman = new FormItem("jinryuujrmcore.HFormG", Human, 5, 0);
    public static FormItem Giant = new FormItem("jinryuujrmcore.NForm2", Namekian, 1);
    public static FormItem FullReleasedNamekian = new FormItem("jinryuujrmcore.NForm1", Namekian, 2);
    public static FormItem GodNamekian = new FormItem("jinryuujrmcore.NFormG", Namekian, 5, 0);
    public static FormItem SuperArcosian = new FormItem("jinryuujrmcore.Form5", Arcosian, 3);
    public static FormItem GodArcosian = new FormItem("jinryuujrmcore.FormG", Arcosian, 6, 0);
    public static FormItem Golden = new FormItem("jinryuujrmcore.Form6", Arcosian, 6);
    public static FormItem Evil = new FormItem("jinryuujrmcore.MForm1", Majin, 2);
    public static FormItem FullPower = new FormItem("jinryuujrmcore.MForm2", Majin, 3);
    public static FormItem Pure = new FormItem("jinryuujrmcore.MForm3", Majin, 5);
    public static FormItem GodMajin = new FormItem("jinryuujrmcore.MFormG", Majin, 5, 0);
    public static FormItem SemiPerfect = new FormItem("dbcadditions.semi_perfect", BioAndroid, 1).setCustomForm(DBCAForms.SemiPerfect.getID());
    public static FormItem Perfect = new FormItem("dbcadditions.perfect", BioAndroid, 2).setCustomForm(DBCAForms.Perfect.getID());
    public static FormItem SuperPerfect = new FormItem("dbcadditions.super_perfect", BioAndroid, 3).setCustomForm(DBCAForms.SuperPerfect.getID());
    public static FormItem GodPerfect = new FormItem("dbcadditions.perfect_god", BioAndroid, 3, 0).setCustomForm(DBCAForms.PerfectGod.getID());
    public static FormItem UncontrolledMax = new FormItem("dbcadditions.uncontrolled_max", BioAndroid, 4).setCustomForm(DBCAForms.UncontrolledMax.getID());
    public static FormItem PerfectMax = new FormItem("dbcadditions.perfect_max", BioAndroid, 5).setCustomForm(DBCAForms.PerfectMax.getID());
    public static FormItem Beast = new FormItem("dbcadditions.beast", SaiyanHalfSaiyanAndHuman, 4).addRequiredCustomSkill(DBCASkills.Beast, 1).setCustomForm(DBCAForms.Beast.getID());
    public static FormItem NamekianPotential = new FormItem("dbcadditions.potential_unlocked", Namekian, 3).setCustomForm(DBCAForms.NamekianPotential.getID()).addRequiredCustomSkill(DBCASkills.NamekianPotential, 1);
    public static FormItem Orange = new FormItem("dbcadditions.orange", Namekian, 3).setCustomForm(DBCAForms.Orange.getID()).addRequiredCustomSkill(DBCASkills.NamekianPotential, 2);
    public static FormItem ArcosianFullPower = new FormItem("dbcadditions.full_power", Arcosian, 1).setCustomForm(DBCAForms.ArcosianBuffed.getID());
    public static FormItem Black = new FormItem("dbcadditions.black", Arcosian, 6).setCustomForm(DBCAForms.Black.getID()).addRequiredCustomSkill(DBCASkills.ArcosianPotential, 1);

    public static void loadFormItems() {
        FormItemsHost.add(SSG2);
        FormItemsHost.add(SS2);
        FormItemsHost.add(SS4);
        FormItemsHost.add(SSGod);
        FormItemsHost.add(SSBlue);
        FormItemsHost.add(SSBlueEv);
        FormItemsHost.add(Buffed);
        FormItemsHost.add(FullReleasedHuman);
        FormItemsHost.add(GodHuman);
        FormItemsHost.add(Giant);
        FormItemsHost.add(FullReleasedNamekian);
        FormItemsHost.add(GodNamekian);
        FormItemsHost.add(NamekianPotential);
        FormItemsHost.add(Orange);
        FormItemsHost.add(ArcosianFullPower);
        FormItemsHost.add(SuperArcosian);
        FormItemsHost.add(GodArcosian);
        FormItemsHost.add(Golden);
        FormItemsHost.add(Black);
        FormItemsHost.add(SemiPerfect);
        FormItemsHost.add(Perfect);
        FormItemsHost.add(SuperPerfect);
        FormItemsHost.add(GodPerfect);
        FormItemsHost.add(UncontrolledMax);
        FormItemsHost.add(PerfectMax);
        FormItemsHost.add(Evil);
        FormItemsHost.add(FullPower);
        FormItemsHost.add(Pure);
        FormItemsHost.add(GodMajin);
        FormItemsHost.add(Beast);
    }

    public static FormItem getFormItemByForm(int formid) {
        for (FormItem item : FormItems) {
            if (!item.isCustomForm() || formid != item.getCustomForm().getID()) continue;
            return item;
        }
        return null;
    }

    public static FormItem getFormItem(int id) {
        for (FormItem item : FormItems) {
            if (id != item.getID()) continue;
            return item;
        }
        return null;
    }
}

