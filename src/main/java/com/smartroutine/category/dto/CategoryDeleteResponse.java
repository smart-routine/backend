package com.smartroutine.category.dto;

import java.util.UUID;

public record CategoryDeleteResponse(
    UUID id,
    UUID userId
) {

}
