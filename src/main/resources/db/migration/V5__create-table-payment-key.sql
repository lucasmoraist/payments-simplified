CREATE TABLE t_payment_key (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    key_value VARCHAR(255) UNIQUE NOT NULL,
    customer_id UUID NOT NULL,
    CONSTRAINT fk_payment_key_customer FOREIGN KEY (customer_id) REFERENCES t_customer(id) ON DELETE CASCADE
);