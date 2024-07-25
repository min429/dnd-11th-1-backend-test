package com.dnd.accompany.domain.example.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.accompany.domain.example.api.dto.CreateExampleRequest;
import com.dnd.accompany.domain.example.api.dto.CreateExampleResponse;
import com.dnd.accompany.domain.example.service.ExampleService;
import com.dnd.accompany.global.common.exception.NotFoundException;
import com.dnd.accompany.global.common.response.ErrorCode;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class ExampleController {

	private final ExampleService exampleService;

	@Operation(summary = "정상 응답")
	@PostMapping
	public ResponseEntity<CreateExampleResponse> create(@RequestBody @Valid CreateExampleRequest request) {
		CreateExampleResponse response = exampleService.create(request);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "예외 응답")
	@GetMapping
	public void exception() {
		throw new NotFoundException(ErrorCode.BAD_REQUEST);
	}
}
