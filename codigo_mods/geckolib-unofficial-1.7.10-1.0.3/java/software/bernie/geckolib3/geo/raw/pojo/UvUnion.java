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
import software.bernie.geckolib3.geo.raw.pojo.UvFaces;

@JsonDeserialize(using=Deserializer.class)
@JsonSerialize(using=Serializer.class)
public class UvUnion {
    public double[] boxUVCoords;
    public UvFaces faceUV;
    public boolean isBoxUV;

    static class Serializer
    extends JsonSerializer<UvUnion> {
        Serializer() {
        }

        @Override
        public void serialize(UvUnion obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (obj.boxUVCoords != null) {
                jsonGenerator.writeObject(obj.boxUVCoords);
                return;
            }
            if (obj.faceUV != null) {
                jsonGenerator.writeObject(obj.faceUV);
                return;
            }
            jsonGenerator.writeNull();
        }
    }

    static class Deserializer
    extends JsonDeserializer<UvUnion> {
        Deserializer() {
        }

        @Override
        public UvUnion deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            UvUnion value = new UvUnion();
            switch (jsonParser.currentToken()) {
                case VALUE_NULL: {
                    break;
                }
                case START_ARRAY: {
                    value.boxUVCoords = jsonParser.readValueAs(double[].class);
                    value.isBoxUV = true;
                    break;
                }
                case START_OBJECT: {
                    value.faceUV = jsonParser.readValueAs(UvFaces.class);
                    value.isBoxUV = false;
                    break;
                }
                default: {
                    throw new IOException("Cannot deserialize UvUnion");
                }
            }
            return value;
        }
    }
}

