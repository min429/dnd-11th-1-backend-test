package com.dnd.accompany.domain.example.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dnd.accompany.domain.example.api.dto.CreateExampleRequest;
import com.dnd.accompany.domain.example.api.dto.CreateExampleResponse;
import com.dnd.accompany.domain.example.entity.Example;
import com.dnd.accompany.domain.example.entity.Type;
import com.dnd.accompany.domain.example.infrastructure.ExampleRepository;

@ExtendWith(MockitoExtension.class)
class ExampleServiceTest {

	@InjectMocks
	private ExampleService exampleService;

	@Mock
	private ExampleRepository exampleRepository;

	/**
	 * @Nested로 브랜치 커버리지를 높입니다.
	 */
	@Nested
	@DisplayName("생성 요청 시")
	class ExampleTest {
		private CreateExampleRequest request;

		@BeforeEach
		void setup() {
			Type type = Type.A;
			String description = "example";
			request = new CreateExampleRequest(description, type);
		}

		@Test
		@DisplayName("Example이 정상 생성된다.")
		void success() {
			//given
			Example example = Example.builder()
				.description(request.description())
				.type(request.type())
				.build();

			given(exampleRepository.save(any(Example.class))).willReturn(example);

			//when
			CreateExampleResponse response = exampleService.create(request);

			//then
			Assertions.assertEquals(request.type(), response.type());
			verify(exampleRepository, times(1)).save(any(Example.class));
		}
	}
}
