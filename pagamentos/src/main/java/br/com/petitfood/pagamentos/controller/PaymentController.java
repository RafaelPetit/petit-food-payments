package br.com.petitfood.pagamentos.controller;

import br.com.petitfood.pagamentos.dto.PaymentDto;
import br.com.petitfood.pagamentos.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/Payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping
    public Page<PaymentDto> listar(@PageableDefault(size = 10) Pageable pagination) {
        return service.getAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getById(@PathVariable @NotNull Long id) {
        PaymentDto dto = service.getById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PaymentDto> cadastrar(@RequestBody @Valid PaymentDto dto, UriComponentsBuilder uriBuilder) {
        PaymentDto createdDto = service.createPayment(dto);
        URI uri = uriBuilder.path("/Payments/{id}").buildAndExpand(createdDto.getId()).toUri();

        return ResponseEntity.created(uri).body(createdDto);
    }

    @PutMapping
    public ResponseEntity<PaymentDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDto dto) {
        PaymentDto updatedDto = service.updatePayment(id, dto);

        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(@PathVariable @NotNull Long id) {
        service.deletePayment(id);

        return ResponseEntity.noContent().build();
    }
}
