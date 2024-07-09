package com.dnd.accompany.domain.example.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.accompany.domain.example.api.dto.CreateExampleRequest;
import com.dnd.accompany.domain.example.api.dto.CreateExampleResponse;
import com.dnd.accompany.domain.example.service.ExampleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class ExampleController {

	private final ExampleService exampleService;

	@PostMapping
	public ResponseEntity<Long> create(@RequestBody @Valid CreateExampleRequest request) {
		CreateExampleResponse response = exampleService.create(request);

		return ResponseEntity.ok(response.id());
	}
}
