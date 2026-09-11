/*
 * Decompiled with CFR 0.152.
 */
package somehussar.janino;

import io.github.somehussar.janinoloader.api.delegates.LoadClassCondition;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AdvancedClassFilter
implements LoadClassCondition {
    private final Set<String> allowedClasses = new HashSet<String>();
    private final Set<Pattern> allowedWildCards = new HashSet<Pattern>();
    private final Set<String> bannedClasses = new HashSet<String>();
    private final Set<Pattern> bannedWildCards = new HashSet<Pattern>();

    public AdvancedClassFilter() {
        this.addClasses("java.io.Serializable", "java.util.Iterator");
        this.addRegexes("java\\.lang\\..*");
    }

    public AdvancedClassFilter addRegexes(String ... classRegexes) {
        this.allowedWildCards.addAll(Arrays.stream(classRegexes).map(Pattern::compile).collect(Collectors.toList()));
        return this;
    }

    public AdvancedClassFilter banRegexes(String ... classRegexes) {
        this.bannedWildCards.addAll(Arrays.stream(classRegexes).map(Pattern::compile).collect(Collectors.toList()));
        return this;
    }

    public AdvancedClassFilter addClasses(String ... classPath) {
        this.allowedClasses.addAll(Arrays.asList(classPath));
        return this;
    }

    public AdvancedClassFilter banClasses(String ... classPath) {
        this.bannedClasses.addAll(Arrays.asList(classPath));
        return this;
    }

    @Override
    public boolean isValid(String name) {
        if (this.bannedClasses.contains(name)) {
            return false;
        }
        if (this.bannedWildCards.stream().anyMatch(pattern -> pattern.matcher(name).matches())) {
            return false;
        }
        if (name.startsWith("java.lang")) {
            return true;
        }
        if (this.allowedClasses.contains(name)) {
            return true;
        }
        return this.allowedWildCards.stream().anyMatch(pattern -> pattern.matcher(name).matches());
    }
}

