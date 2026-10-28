package com.example.taskanalizator.domain.converter;

import com.example.taskanalizator.domain.enums.TicketStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static java.util.Objects.isNull;

@Converter(autoApply = true)
public class TicketStatusConverter implements AttributeConverter<TicketStatus, String> {
    @Override
    public String convertToDatabaseColumn(TicketStatus attribute) {
        return isNull(attribute) ? null : attribute.name();
    }

    @Override
    public TicketStatus convertToEntityAttribute(String dbData) {
        return isNull(dbData) ? null : TicketStatus.fromString(dbData);
    }
}
