package com.lucasmoraist.payments_simplified.application.mapper;

import com.lucasmoraist.payments_simplified.domain.model.PaymentKey;
import com.lucasmoraist.payments_simplified.infrastructure.api.web.request.PaymentKeyRequest;
import com.lucasmoraist.payments_simplified.infrastructure.database.sql.entity.PaymentKeyEntity;

public final class PaymentKeyMapper {

    private PaymentKeyMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PaymentKey toDomain(PaymentKeyEntity paymentKeyEntity) {
        return new PaymentKey(
                paymentKeyEntity.getId(),
                paymentKeyEntity.getKeyValue(),
                null
        );
    }

    public static PaymentKey toDto(PaymentKeyRequest paymentKeyRequest) {
        return new PaymentKey(
                null,
                paymentKeyRequest.keyValue(),
                null
        );
    }

    public static PaymentKeyEntity toEntity(PaymentKey paymentKey) {
        return new PaymentKeyEntity(
                null,
                paymentKey.keyValue(),
                null
        );
    }

}
