/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.deser.std;

import com.relocated.fasterxml.jackson.core.JsonParser;
import com.relocated.fasterxml.jackson.core.JsonToken;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.DeserializationFeature;
import com.relocated.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;

public class StackTraceElementDeserializer
extends StdScalarDeserializer<StackTraceElement> {
    private static final long serialVersionUID = 1L;

    public StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }

    @Override
    public StackTraceElement deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.START_OBJECT) {
            String className = "";
            String methodName = "";
            String fileName = "";
            String moduleName = null;
            String moduleVersion = null;
            String classLoaderName = null;
            int lineNumber = -1;
            while ((t = p.nextValue()) != JsonToken.END_OBJECT) {
                String propName = p.getCurrentName();
                if ("className".equals(propName)) {
                    className = p.getText();
                    continue;
                }
                if ("classLoaderName".equals(propName)) {
                    classLoaderName = p.getText();
                    continue;
                }
                if ("fileName".equals(propName)) {
                    fileName = p.getText();
                    continue;
                }
                if ("lineNumber".equals(propName)) {
                    if (t.isNumeric()) {
                        lineNumber = p.getIntValue();
                        continue;
                    }
                    lineNumber = this._parseIntPrimitive(p, ctxt);
                    continue;
                }
                if ("methodName".equals(propName)) {
                    methodName = p.getText();
                    continue;
                }
                if ("nativeMethod".equals(propName)) continue;
                if ("moduleName".equals(propName)) {
                    moduleName = p.getText();
                    continue;
                }
                if ("moduleVersion".equals(propName)) {
                    moduleVersion = p.getText();
                    continue;
                }
                this.handleUnknownProperty(p, ctxt, this._valueClass, propName);
            }
            return this.constructValue(ctxt, className, methodName, fileName, lineNumber, moduleName, moduleVersion, classLoaderName);
        }
        if (t == JsonToken.START_ARRAY && ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            p.nextToken();
            StackTraceElement value = this.deserialize(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                this.handleMissingEndArrayForSingle(p, ctxt);
            }
            return value;
        }
        return (StackTraceElement)ctxt.handleUnexpectedToken(this._valueClass, p);
    }

    @Deprecated
    protected StackTraceElement constructValue(DeserializationContext ctxt, String className, String methodName, String fileName, int lineNumber, String moduleName, String moduleVersion) {
        return this.constructValue(ctxt, className, methodName, fileName, lineNumber, moduleName, moduleVersion, null);
    }

    protected StackTraceElement constructValue(DeserializationContext ctxt, String className, String methodName, String fileName, int lineNumber, String moduleName, String moduleVersion, String classLoaderName) {
        return new StackTraceElement(className, methodName, fileName, lineNumber);
    }
}

