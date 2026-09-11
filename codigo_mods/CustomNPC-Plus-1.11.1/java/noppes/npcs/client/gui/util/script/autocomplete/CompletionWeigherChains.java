/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete;

import java.util.Arrays;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.AlphabeticalWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.ContextFitWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.DeprecatedWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.ImportStatusWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.InheritanceDepthWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.MatchQualityWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.MemberKindWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.UsageFrequencyWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.WeigherChain;

public class CompletionWeigherChains {
    public static WeigherChain javaChain() {
        return new WeigherChain(Arrays.asList(new MatchQualityWeigher(), new MemberKindWeigher(), new ContextFitWeigher(), new ImportStatusWeigher(), new UsageFrequencyWeigher(), new DeprecatedWeigher(), new AlphabeticalWeigher()));
    }

    public static WeigherChain jsChain() {
        return new WeigherChain(Arrays.asList(new MatchQualityWeigher(), new MemberKindWeigher(), new ContextFitWeigher(), new ImportStatusWeigher(), new UsageFrequencyWeigher(), new InheritanceDepthWeigher(), new DeprecatedWeigher(), new AlphabeticalWeigher()));
    }
}

