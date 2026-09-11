/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.geckolib3.geo.raw.pojo;

import com.relocated.fasterxml.jackson.core.JsonGenerator;
import com.relocated.fasterxml.jackson.core.JsonParser;
import com.relocated.fasterxml.jackson.core.JsonProcessingException;
import com.relocated.fasterxml.jackson.databind.DeserializationContext;
import com.relocated.fasterxml.jackson.databind.JsonDeserializer;
import com.relocated.fasterxml.jackson.databind.JsonSerializer;
import com.relocated.fasterxml.jackson.databind.SerializerProvider;
import com.relocated.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.relocated.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import software.bernie.geckolib3.geo.raw.pojo.PolysEnum;

@JsonDeserialize(using=Deserializer.class)
@JsonSerialize(using=Serializer.class)
public class PolysUnion {
    public double[][][] doubleArrayArrayArrayValue;
    public PolysEnum enumValue;

    static class Serializer
    extends JsonSerializer<PolysUnion> {
        Serializer() {
        }

        @Override
        public void serialize(PolysUnion obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (obj.doubleArrayArrayArrayValue != null) {
                jsonGenerator.writeObject(obj.doubleArrayArrayArrayValue);
                return;
            }
            if (obj.enumValue != null) {
                jsonGenerator.writeObject((Object)obj.enumValue);
                return;
            }
            throw new IOException("PolysUnion must not be null");
        }
    }

    static class Deserializer
    extends JsonDeserializer<PolysUnion> {
        Deserializer() {
        }

        @Override
        public PolysUnion deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            PolysUnion value = new PolysUnion();
            switch (jsonParser.currentToken()) {
                case VALUE_STRING: {
                    String string = jsonParser.readValueAs(String.class);
                    try {
                        value.enumValue = PolysEnum.forValue(string);
                    }
                    catch (Exception exception) {}
                    break;
                }
                case START_ARRAY: {
                    value.doubleArrayArrayArrayValue = jsonParser.readValueAs(double[][][].class);
                    break;
                }
                default: {
                    throw new IOException("Cannot deserialize PolysUnion");
                }
            }
            return value;
        }
    }
}

