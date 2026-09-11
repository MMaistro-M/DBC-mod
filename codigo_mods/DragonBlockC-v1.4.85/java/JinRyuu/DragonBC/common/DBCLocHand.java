/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.LanguageRegistry
 */
package JinRyuu.DragonBC.common;

import JinRyuu.DragonBC.common.DBCLoc;
import JinRyuu.DragonBC.common.DBCLocHlp;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class DBCLocHand {
    public static void LangLod() {
        for (String LocFile : DBCLoc.localFiles) {
            String File2 = LocFile.replace("es_AR", "es_ES").replace("es_MX", "es_ES").replace("pt_BR", "pt_PT");
            LanguageRegistry.instance().loadLocalization(File2, DBCLocHlp.getLocFrmName(LocFile), DBCLocHlp.isXMLLangFil(File2));
        }
    }
}

