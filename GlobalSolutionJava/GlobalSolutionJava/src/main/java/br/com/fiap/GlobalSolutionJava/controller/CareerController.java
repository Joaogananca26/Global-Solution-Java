package br.com.fiap.GlobalSolutionJava.controller;

import br.com.fiap.GlobalSolutionJava.dto.request.CareerRequest;
import br.com.fiap.GlobalSolutionJava.dto.response.CareerResponse;
import br.com.fiap.GlobalSolutionJava.service.CareerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@AllArgsConstructor
@RestController
public class CareerController {

    private final CareerService careerService;

    @PostMapping("/career")
    public ResponseEntity<CareerResponse> generateCareer(
            @RequestBody @Valid CareerRequest request
    ) {
        if (request.answers() == null || request.answers().size() < 10) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "É necessário responder às 10 perguntas."
            );
        }

        CareerResponse response = careerService.generateCareer(request);
        return ResponseEntity.ok(response);
    }
}