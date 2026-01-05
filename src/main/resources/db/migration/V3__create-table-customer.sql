CREATE TABLE t_customer (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255),
    account_id UUID,
    CONSTRAINT fk_customer_account FOREIGN KEY (account_id) REFERENCES t_account(id) ON DELETE CASCADE
);