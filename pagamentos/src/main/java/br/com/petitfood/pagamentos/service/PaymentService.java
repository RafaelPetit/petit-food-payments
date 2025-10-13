package br.com.petitfood.pagamentos.service;

import br.com.petitfood.pagamentos.dto.PaymentDto;
import br.com.petitfood.pagamentos.model.Payment;
import br.com.petitfood.pagamentos.model.Status;
import br.com.petitfood.pagamentos.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PaymentDto> getAll(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PaymentDto.class));
    }

    public PaymentDto getById(Long id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment não encontrado"));

        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto createPayment(PaymentDto dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setStatus(Status.CREATED);
        payment = repository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }

    public PaymentDto updatePayment(Long id, PaymentDto dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setId(id);
        payment = repository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}
