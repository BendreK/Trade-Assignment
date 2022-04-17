package com.barclays.util;

import javax.persistence.AttributeConverter;

public class BooleanToYNStringConverter implements AttributeConverter<Character, Boolean> {
    @Override
    public Character convertToEntityAttribute(Boolean b) {
        if (b == null) {
            return null;
        }
        if (b.booleanValue()) {
            return 'Y';
        }
        return 'N';
    }

    @Override
    public Boolean convertToDatabaseColumn(Character s) {
        if (s == null) {
            return null;
        }

        if (s == 'Y') {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
