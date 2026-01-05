CREATE TABLE t_document (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type VARCHAR(50) NOT NULL,
    number VARCHAR(255) UNIQUE NOT NULL,
    customer_id UUID NOT NULL,
    CONSTRAINT fk_document_customer FOREIGN KEY (customer_id) REFERENCES t_customer(id) ON DELETE CASCADE
);