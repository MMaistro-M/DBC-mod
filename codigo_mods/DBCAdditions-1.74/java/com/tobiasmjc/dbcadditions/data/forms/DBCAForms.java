/*
 * Decompiled with CFR 0.152.
 */
package com.tobiasmjc.dbcadditions.data.forms;

import com.tobiasmjc.dbcadditions.data.FormMasteryData;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.data.forms.display.CustomAura;
import com.tobiasmjc.dbcadditions.data.forms.display.HairType;
import com.tobiasmjc.dbcadditions.data.forms.display.Tattoo;
import java.util.LinkedHashSet;
import java.util.Set;

public class DBCAForms {
    public static Set<DBCAForm> FORMS = new LinkedHashSet<DBCAForm>();
    public static Set<DBCAForm> HOST_FORMS = new LinkedHashSet<DBCAForm>();
    public static DBCAForm Beast = new DBCAForm("beast", 4.5f, 15727842, 15488090, 15727842).setHairType(HairType.SS2).setColorCode('4').setCustomHair("747174186950000018191950500000712519675000006732197250009478474369212194784741692121948043416921219480434369212194784341692121947849396921219480413969212194764147692121948043456921210050505569212197186156692121971858606921219718565869212197785260692121971861606921219780616169212197805861692121978058586921219778585869212100505045692121977645526178009776475261780097745249617800977654476178009774455061780097744752617800977452476178009774544761781897724752617800977147526178009771524761780097725247617800005550506178000069455061780000695650617800007456506178009718414960561997183452586123971834506380199718325265711997184352586118971836526063189718344763801997183447638019971834525861189718345263801997183447638019971834476380199718345263801997183452638019971834476380199718344963801920").setKiColor(16724581);
    public static DBCAForm NamekianPotential = new DBCAForm("potential_unlocked", 3.0f, 15717216, -1, 15717216).setColorCode('e').setBodyColors(14671970, 15524763, 12854822, -1).setKiDrainPercentage(-0.5);
    public static DBCAForm Orange = new DBCAForm("orange", 4.0f, 16744999, 13636110, 16744999).setColorCode('6').setBodyColors(16744999, 15524763, 12854822, -1).setBulkMultiplier(1.12f).setSizeMultiplier(1.4f).addTattoo(Tattoo.NAMEKIAN_ORANGE);
    public static DBCAForm ArcosianBuffed = new DBCAForm("full_power", 1.5f, -1, -1, -1).setBulkMultiplier(1.16f).setSizeMultiplier(1.035f).setKiDrainPercentage(-0.5).setStateRender(4).setColorCode('8').setMasteryData(new FormMasteryData().setInstantTransformLevel(25));
    public static DBCAForm Black = new DBCAForm("black", 4.8f, 0x545454, 15488090, 0x545454).setColorCode('0').setBodyColors(0x545454, 0xC3C3C3, -1, 0x171717).setKiDrainPercentage(-1.25).setStateRender(6);
    public static DBCAForm SemiPerfect = new DBCAForm("semi_perfect", 1.8f, -1, 7507711, 15717216).setColorCode('6').setBulkMultiplier(1.07f).setSizeMultiplier(1.07f).setKiDrainPercentage(0.0).setKaiokenStackable(true).setHealthDrainKaio(1.2f).setMasteryData(new FormMasteryData().setInstantTransformLevel(0)).setStateRender(2);
    public static DBCAForm Perfect = new DBCAForm("perfect", 2.5f, -1, 16745631, 15717216).setColorCode('6').setBulkMultiplier(1.02f).setSizeMultiplier(1.02f).setKiDrainPercentage(0.0).setKaiokenStackable(true).setHealthDrainKaio(1.5).setMasteryData(new FormMasteryData().setInstantTransformLevel(0)).setStateRender(3);
    public static DBCAForm SuperPerfect = new DBCAForm("super_perfect", 3.0f, -1, 16745631, 15717216).setColorCode('6').setBulkMultiplier(1.02f).setSizeMultiplier(1.02f).setKiDrainPercentage(0.0).setKaiokenStackable(true).setHealthDrainKaio(2.0).setMasteryData(new FormMasteryData().setInstantTransformLevel(0)).setStateRender(3);
    public static DBCAForm PerfectGod = new DBCAForm("perfect_god", 3.5f, -1, 16718642, 16747301).setColorCode('c').setBulkMultiplier(1.03f).setSizeMultiplier(1.03f).setKiDrainPercentage(0.25).setMasteryData(new FormMasteryData().setInstantTransformLevel(0)).setStateRender(3);
    public static DBCAForm UncontrolledMax = new DBCAForm("uncontrolled_max", 4.2f, -1, -1, 16735051).setColorCode('c').setBulkMultiplier(1.02f).setSizeMultiplier(2.35f).setKiDrainPercentage(-0.85f).setStateRender(4);
    public static DBCAForm PerfectMax = new DBCAForm("perfect_max", 4.8f, -1, 16723456, 16735051).setColorCode('c').setBulkMultiplier(1.02f).setSizeMultiplier(1.02f).setMasteryData(new FormMasteryData().setInstantTransformLevel(50)).setKiDrainPercentage(-0.55f).setStateRender(5);

    public static void loadForms() {
        DBCAForms.registerForm(SemiPerfect.setNextForm(Perfect.getName()));
        DBCAForms.registerForm(Perfect.setNextForm(SuperPerfect.getName()));
        DBCAForms.registerForm(SuperPerfect);
        DBCAForms.registerForm(PerfectGod);
        DBCAForms.registerForm(UncontrolledMax.setNextForm(PerfectMax.getName()));
        DBCAForms.registerForm(PerfectMax);
        DBCAForms.registerForm(Beast);
        DBCAForms.registerForm(NamekianPotential.setNextForm(Orange.getName()));
        DBCAForms.registerForm(Orange);
        DBCAForms.registerForm(ArcosianBuffed);
        DBCAForms.registerForm(Black);
    }

    public static void loadAuras() {
        Beast.addAura(new CustomAura().setSpd(45).setAlp(0.2f).setColL1(0xFFE0FF).setColL2(16724581).setColL3(16724581).setTexL3("auragb").setLightning(1.0f, 0.05f, 0.2f, 0.6f).setSound("jinryuudragonbc:DBC5.aura_ui"));
        NamekianPotential.addAura(new CustomAura().setSpd(30).setAlp(0.2f).setTexL1("aurak").setColL1(16176461));
        Orange.addAura(new CustomAura().setSpd(20).setState(10.0f).setAlp(0.17f).setColL1(16766061).setColL2(16761659).setTexL1("aurai").setTexL2("auragb").setSound("jinryuudragonbc:1610.aurab"));
        Black.addAura(new CustomAura().setSpd(45).setAlp(0.2f).setTexL3("aurau").setColL2(0x545454).setColL3(0x545454));
        CustomAura perfectAura = new CustomAura().setColL1(16176461).setSpd(15);
        SemiPerfect.addAura(perfectAura);
        Perfect.addAura(perfectAura);
        SuperPerfect.addAura(new CustomAura().setColL1(16176461).setSpd(15).setLightning(true));
        PerfectGod.addAura(new CustomAura().setAlp(0.2f).setTexL1("aurai").setTexL2("aurai2").setColL1(15488090).setColL2(16747301).setBol6(0).setSound("jinryuudragonbc:1610.aurag"));
        UncontrolledMax.addAura(new CustomAura().setAlp(0.2f).setTexL1("aurai2").setTexL3("aurag").setColL1(16725238).setColL2(16725238).setState(22.0f));
        PerfectMax.addAura(new CustomAura().setSpd(30).setAlp(0.2f).setBol6(6).setTexL1("aurag").setTexL3("auragb").setColL1(12464847).setColL2(12464847).setSound("jinryuudragonbc:DBC5.aura_destroyer"));
    }

    public static void registerForm(DBCAForm form) {
        HOST_FORMS.add(form);
    }

    public static DBCAForm getForm(String name) {
        DBCAForm form = null;
        for (DBCAForm f : FORMS) {
            if (!f.getName().equals(name)) continue;
            form = f;
        }
        return form;
    }

    public static DBCAForm getForm(int id) {
        DBCAForm form = null;
        for (DBCAForm f : FORMS) {
            if (f.getID() != id) continue;
            form = f;
        }
        return form;
    }

    public static DBCAForm getDBCAFormByItem(int itemID) {
        if (itemID == FormItemsDBA.Beast.getID()) {
            return Beast;
        }
        if (itemID == FormItemsDBA.Orange.getID()) {
            return Orange;
        }
        if (itemID == FormItemsDBA.NamekianPotential.getID()) {
            return NamekianPotential;
        }
        if (itemID == FormItemsDBA.Black.getID()) {
            return Black;
        }
        if (itemID == FormItemsDBA.ArcosianFullPower.getID()) {
            return ArcosianBuffed;
        }
        if (itemID == FormItemsDBA.Perfect.getID()) {
            return Perfect;
        }
        if (itemID == FormItemsDBA.GodPerfect.getID()) {
            return PerfectGod;
        }
        if (itemID == FormItemsDBA.SemiPerfect.getID()) {
            return SemiPerfect;
        }
        if (itemID == FormItemsDBA.UncontrolledMax.getID()) {
            return UncontrolledMax;
        }
        if (itemID == FormItemsDBA.PerfectMax.getID()) {
            return PerfectMax;
        }
        return null;
    }
}

