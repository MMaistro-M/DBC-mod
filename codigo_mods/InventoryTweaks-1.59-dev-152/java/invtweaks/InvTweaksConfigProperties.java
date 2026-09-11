/*
 * Decompiled with CFR 0.152.
 */
package invtweaks;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class InvTweaksConfigProperties
extends Properties {
    private static final long serialVersionUID = 1L;
    private final List<String> keys = new LinkedList<String>();

    @Override
    public Enumeration<Object> keys() {
        return Collections.enumeration(new LinkedHashSet<String>(this.keys));
    }

    @Override
    public Object put(String key, Object value) {
        this.keys.add(key);
        return super.put(key, value);
    }

    public void sortKeys() {
        Collections.sort(this.keys);
    }
}

