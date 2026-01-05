package com.lucasmoraist.payments_simplified.application.mapper;

import com.lucasmoraist.payments_simplified.domain.enums.DocumentType;
import com.lucasmoraist.payments_simplified.domain.model.Document;
import com.lucasmoraist.payments_simplified.infrastructure.api.web.request.DocumentRequest;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.DocumentEntity;

public final class DocumentMapper {

    private DocumentMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Document toDomain(DocumentEntity documentEntity) {
        return new Document(
                documentEntity.getId(),
                documentEntity.getType(),
                documentEntity.getNumber(),
                null
        );
    }

    public static Document toDto(DocumentRequest documentRequest) {
        return new Document(
                null,
                DocumentType.fromValue(documentRequest.number()),
                documentRequest.number(),
                null
        );
    }

    public static DocumentEntity toEntity(Document document) {
        return new DocumentEntity(
                null,
                document.type(),
                document.number(),
                null
        );
    }

}
