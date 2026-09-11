/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.node;

import com.relocated.fasterxml.jackson.core.JsonGenerator;
import com.relocated.fasterxml.jackson.core.JsonPointer;
import com.relocated.fasterxml.jackson.core.JsonToken;
import com.relocated.fasterxml.jackson.core.type.WritableTypeId;
import com.relocated.fasterxml.jackson.databind.JsonNode;
import com.relocated.fasterxml.jackson.databind.SerializerProvider;
import com.relocated.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.relocated.fasterxml.jackson.databind.node.BaseJsonNode;
import com.relocated.fasterxml.jackson.databind.node.MissingNode;
import com.relocated.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.List;

public abstract class ValueNode
extends BaseJsonNode {
    protected ValueNode() {
    }

    @Override
    protected JsonNode _at(JsonPointer ptr) {
        return MissingNode.getInstance();
    }

    @Override
    public <T extends JsonNode> T deepCopy() {
        return (T)this;
    }

    @Override
    public abstract JsonToken asToken();

    @Override
    public void serializeWithType(JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(this, this.asToken()));
        this.serialize(g, provider);
        typeSer.writeTypeSuffix(g, typeIdDef);
    }

    @Override
    public String toString() {
        return this.asText();
    }

    @Override
    public final JsonNode get(int index) {
        return null;
    }

    @Override
    public final JsonNode path(int index) {
        return MissingNode.getInstance();
    }

    @Override
    public final boolean has(int index) {
        return false;
    }

    @Override
    public final boolean hasNonNull(int index) {
        return false;
    }

    @Override
    public final JsonNode get(String fieldName) {
        return null;
    }

    @Override
    public final JsonNode path(String fieldName) {
        return MissingNode.getInstance();
    }

    @Override
    public final boolean has(String fieldName) {
        return false;
    }

    @Override
    public final boolean hasNonNull(String fieldName) {
        return false;
    }

    @Override
    public final JsonNode findValue(String fieldName) {
        return null;
    }

    @Override
    public final ObjectNode findParent(String fieldName) {
        return null;
    }

    @Override
    public final List<JsonNode> findValues(String fieldName, List<JsonNode> foundSoFar) {
        return foundSoFar;
    }

    @Override
    public final List<String> findValuesAsText(String fieldName, List<String> foundSoFar) {
        return foundSoFar;
    }

    @Override
    public final List<JsonNode> findParents(String fieldName, List<JsonNode> foundSoFar) {
        return foundSoFar;
    }
}

