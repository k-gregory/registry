BEGIN;

ALTER TABLE executant
    ADD COLUMN facility_id bigint references facility (id);

UPDATE executant SET facility_id=1 WHERE facility_id IS NULL;

ALTER TABLE executant ALTER COLUMN facility_id SET NOT NULL;
COMMIT;