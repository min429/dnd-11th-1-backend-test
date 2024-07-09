package com.dnd.accompany.domain.example.api.dto;

import com.dnd.accompany.domain.example.entity.Type;

import jakarta.validation.constraints.NotNull;

public record CreateExampleRequest(
	@NotNull
	String description,
	Type type
) {
}
