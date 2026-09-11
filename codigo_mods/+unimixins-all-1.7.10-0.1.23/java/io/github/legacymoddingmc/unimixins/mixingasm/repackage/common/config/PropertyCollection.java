/*
 * Decompiled with CFR 0.152.
 */
package io.github.legacymoddingmc.unimixins.mixingasm.repackage.common.config;

import io.github.legacymoddingmc.unimixins.mixingasm.repackage.common.config.PropertyToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

class PropertyCollection {
    private Map<String, PropertyToken> properties = new TreeMap<String, PropertyToken>();

    PropertyCollection() {
    }

    public static PropertyCollection fromFile(File file) {
        PropertyCollection coll = new PropertyCollection();
        coll.readPropertyTokens(file);
        return coll;
    }

    private void readPropertyTokens(File file) {
        ArrayList<String> comment = new ArrayList<String>();
        String prop = "";
        ArrayList<String> keyOrder = new ArrayList<String>();
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file));){
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.isEmpty()) continue;
                    if ((line = line.trim()).startsWith("#")) {
                        comment.add(line.substring(1).trim());
                        continue;
                    }
                    prop = line;
                    PropertyToken token = new PropertyToken(comment, prop);
                    this.properties.put(token.getKey(), token);
                    keyOrder.add(token.getKey());
                    comment.clear();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(File file) {
        this.writePropertyTokens(file);
    }

    private void writePropertyTokens(File file) {
        boolean dirty = this.properties.values().stream().anyMatch(PropertyToken::isDirty);
        if (dirty) {
            file.getParentFile().mkdirs();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));){
                for (Map.Entry<String, PropertyToken> e : this.properties.entrySet()) {
                    PropertyToken token = e.getValue();
                    for (String commentLine : token.getComment()) {
                        bw.write("# " + commentLine + "\n");
                    }
                    bw.write(token.getKey() + "=" + token.getValue() + "\n\n");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public PropertyToken get(String key) {
        return this.properties.get(key);
    }

    public void put(String key, PropertyToken token) {
        PropertyToken old = this.properties.get(key);
        if (!token.equals(old)) {
            this.properties.put(key, token);
            token.setDirty();
        }
    }
}

