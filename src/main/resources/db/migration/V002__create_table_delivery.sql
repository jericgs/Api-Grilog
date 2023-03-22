CREATE TABLE delivery (
    id SERIAL NOT NULL,
    client_id BIGINT NOT NULL,
    tax DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    order_date TIMESTAMP NOT NULL,
    completion_date TIMESTAMP,

    recipient_name VARCHAR(60) NOT NULL,
    recipient_street VARCHAR(255) NOT NULL,
    recipient_number VARCHAR(30) NOT NULL,
    recipient_complement VARCHAR(60) NOT NULL,
    recipient_neighborhood VARCHAR(30) NOT NULL,

    PRIMARY KEY(id)
);

ALTER TABLE delivery ADD CONSTRAINT fk_delivery_client
FOREIGN KEY (client_id) REFERENCES client (id);