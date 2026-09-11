/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.util.JsonException
 */
package software.bernie.geckolib3.file;

import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.util.JsonException;
import org.apache.commons.lang3.StringUtils;

public class GeckoJsonException
extends IOException {
    private final List<Entry> entries = Lists.newArrayList();
    private final String string;

    public GeckoJsonException(String p_i45279_1_) {
        this.entries.add(new Entry(null));
        this.string = p_i45279_1_;
    }

    public GeckoJsonException(String p_i45280_1_, Throwable p_i45280_2_) {
        super(p_i45280_2_);
        this.entries.add(new Entry(null));
        this.string = p_i45280_1_;
    }

    public void func_151380_a(String p_151380_1_) {
        this.entries.get(0).func_151373_a(p_151380_1_);
    }

    public void func_151381_b(String p_151381_1_) {
        this.entries.get((int)0).field_151376_a = p_151381_1_;
        this.entries.add(0, new Entry(null));
    }

    @Override
    public String getMessage() {
        return "Invalid " + this.entries.get(this.entries.size() - 1).toString() + ": " + this.string;
    }

    public static JsonException func_151379_a(Exception p_151379_0_) {
        if (p_151379_0_ instanceof JsonException) {
            return (JsonException)p_151379_0_;
        }
        String s = p_151379_0_.getMessage();
        if (p_151379_0_ instanceof FileNotFoundException) {
            s = "File not found";
        }
        return new JsonException(s, (Throwable)p_151379_0_);
    }

    public static class Entry {
        public String field_151376_a = null;
        public final List field_151375_b = Lists.newArrayList();

        private Entry() {
        }

        public void func_151373_a(String p_151373_1_) {
            this.field_151375_b.add(0, p_151373_1_);
        }

        public String func_151372_b() {
            return StringUtils.join(this.field_151375_b, "->");
        }

        public String toString() {
            return this.field_151376_a != null ? (!this.field_151375_b.isEmpty() ? this.field_151376_a + " " + this.func_151372_b() : this.field_151376_a) : (!this.field_151375_b.isEmpty() ? "(Unknown file) " + this.func_151372_b() : "(Unknown file)");
        }

        Entry(Object p_i45278_1_) {
            this();
        }
    }
}

