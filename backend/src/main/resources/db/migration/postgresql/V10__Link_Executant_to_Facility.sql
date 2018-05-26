ALTER TABLE executant
    ADD COLUMN facility_id BIGINT REFERENCES facility(id) NOT NULL DEFAULT 1;

ALTER TABLE executant
    ALTER COLUMN facility_id DROP DEFAULT;