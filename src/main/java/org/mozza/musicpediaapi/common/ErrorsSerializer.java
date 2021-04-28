package org.mozza.musicpediaapi.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent
// ResponseEntity의 body에 Errors 객체를 바로 담을 수 없기 때문에 serializer 설정을 해줘야 한다.
public class ErrorsSerializer extends JsonSerializer<Errors> {
    @Override
    public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("errors");
        gen.writeStartArray();
        // errors.reject() -> GlobalError, errors.rejectValue() -> FieldError
        errors.getFieldErrors().stream().forEach(e -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("field",e.getField());
                gen.writeStringField("objectName",e.getObjectName());
                gen.writeStringField("code",e.getCode());
                gen.writeStringField("defaultMessage",e.getDefaultMessage());
                Object rejectedValue = e.getRejectedValue();
                if (rejectedValue != null) {
                    gen.writeStringField("rejectedValue", rejectedValue.toString());
                }
                gen.writeEndObject();;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        errors.getGlobalErrors().forEach(e -> {
            try {
                gen.writeStartObject();
                gen.writeStringField("objectName",e.getObjectName());
                gen.writeStringField("code",e.getCode());
                gen.writeStringField("defaultMessage",e.getDefaultMessage());
                gen.writeEndObject();;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
