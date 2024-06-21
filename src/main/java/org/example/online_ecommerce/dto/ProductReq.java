package org.example.online_ecommerce.dto;

import java.util.UUID;

public record ProductReq(
        String name,
        Integer price,
        UUID categoryId,
        UUID attachmentId
) {
}
