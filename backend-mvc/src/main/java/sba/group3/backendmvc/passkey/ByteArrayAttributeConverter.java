package sba.group3.backendmvc.passkey;

import com.yubico.webauthn.data.ByteArray;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Converter(autoApply = true)
public class ByteArrayAttributeConverter implements AttributeConverter<ByteArray, String> {
    @Override
    public String convertToDatabaseColumn(ByteArray attribute) {
        return attribute == null ? null : attribute.getBase64Url();
    }

    @Override
    public ByteArray convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new ByteArray(Base64.getDecoder().decode(dbData));
    }
}
