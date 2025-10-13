// java
package br.com.petitfood.pagamentos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    @Size(max = 100)
    private String cardHolderName;

    @NotBlank
    @Size(max = 19)
    private String cardNumber;

    @Size(max = 7)
    private String expiration;

    @NotBlank
    @Size(max = 3)
    private String securityCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long orderId;

    @NotNull
    private Long paymentMethodId;
}