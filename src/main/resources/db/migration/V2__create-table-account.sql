CREATE TABLE t_account (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    agency VARCHAR(255),
    account_number VARCHAR(255),
    account_type VARCHAR(255),
    balance DECIMAL(19, 2)
);