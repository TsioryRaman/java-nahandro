ALTER TABLE address
    ADD COLUMN user_id BIGINT NOT NULL;

ALTER TABLE address
    ADD CONSTRAINT fk_addresses_users
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE;