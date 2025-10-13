CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    amount NUMERIC(19,2) NOT NULL,
    card_holder_name VARCHAR(100) NOT NULL,
    card_number VARCHAR(19) NOT NULL,
    expiration VARCHAR(7),
    security_code VARCHAR(3) NOT NULL,
    status VARCHAR(50) NOT NULL,
    payment_method_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL
);