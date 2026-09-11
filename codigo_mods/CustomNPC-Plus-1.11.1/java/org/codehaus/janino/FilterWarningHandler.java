/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.janino;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.compiler.util.StringPattern;
import org.codehaus.commons.nullanalysis.Nullable;

public class FilterWarningHandler
implements WarningHandler {
    private final StringPattern[] handlePatterns;
    private final WarningHandler delegate;

    public FilterWarningHandler(StringPattern[] handlePatterns, WarningHandler delegate) {
        this.handlePatterns = handlePatterns;
        this.delegate = delegate;
    }

    @Override
    public void handleWarning(@Nullable String handle, String message, @Nullable Location location) throws CompileException {
        if (handle == null || StringPattern.matches(this.handlePatterns, handle)) {
            this.delegate.handleWarning(handle, message, location);
        }
    }
}

