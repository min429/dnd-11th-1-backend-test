package com.dnd.accompany.domain.example.api.dto;

import com.dnd.accompany.domain.example.entity.Type;

public record CreateExampleResponse(
	Long id,
	Type type
) {
}
