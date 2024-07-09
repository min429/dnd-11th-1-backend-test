package com.dnd.accompany.domain.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnd.accompany.domain.example.api.dto.CreateExampleRequest;
import com.dnd.accompany.domain.example.api.dto.CreateExampleResponse;
import com.dnd.accompany.domain.example.entity.Example;
import com.dnd.accompany.domain.example.infrastructure.ExampleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExampleService {

	private final ExampleRepository exampleRepository;

	@Transactional
	public CreateExampleResponse create(CreateExampleRequest request) {
		Example example = exampleRepository.save(
			Example.builder()
				.description(request.description())
				.type(request.type())
				.build()
		);

		return new CreateExampleResponse(
			example.getId(),
			example.getType()
		);
	}
}
