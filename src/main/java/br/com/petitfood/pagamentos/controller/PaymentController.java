package br.com.petitfood.pagamentos.controller;

import br.com.petitfood.pagamentos.dto.PaymentDto;
import br.com.petitfood.pagamentos.service.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        PaymentDto payment = service.createPayment(dto);
        URI uri = uriBuilder.path("/Payments/{id}").buildAndExpand(payment.getId()).toUri();

        Message message = new Message(("Payment confirmed with id: " + payment.getId()).getBytes());
        rabbitTemplate.convertAndSend("payment.confirmed", payment);

        return ResponseEntity.created(uri).body(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDto dto) {
        PaymentDto updatedDto = service.updatePayment(id, dto);

        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable @NotNull Long id) {
        service.deletePayment(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/approve")
    @CircuitBreaker(name = "approvePayment", fallbackMethod = "approvePaymentFallback")
    public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
        service.approvePayment(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> approvePaymentFallback(Long id, Exception e) {
        service.updateStatusFallback(id);
        return ResponseEntity.status(503).build();
    }
}
