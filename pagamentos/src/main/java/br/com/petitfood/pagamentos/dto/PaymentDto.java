package br.com.petitfood.pagamentos.dto;

import br.com.petitfood.pagamentos.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDto {
    private Long id;
    private BigDecimal amount;
    private String cardHolderName;
    private String cardNumber;
    private String expiration;
    private String securityCode;
    private Status status;
    private Long paymentMethodId;
    private Long orderId;
}
