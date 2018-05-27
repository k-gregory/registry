ALTER TABLE facility
    ADD COLUMN head_id BIGINT REFERENCES executant(id);