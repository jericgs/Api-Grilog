CREATE TABLE occurrence (
    id SERIAL NOT NULL,
    delivery_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    registration_date TIMESTAMP NOT NULL,

    PRIMARY KEY(id)
);

ALTER TABLE occurrence ADD CONSTRAINT fk_occurrence_delivery
FOREIGN KEY (delivery_id) REFERENCES delivery (id);