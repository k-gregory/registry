ALTER TABLE public.enforcement
  ADD name text NULL;
ALTER TABLE public.enforcement
  ADD category_id bigint NULL;
ALTER TABLE public.enforcement
  ADD resolution_text text NULL;
ALTER TABLE public.enforcement
  ADD deadline timestamp NULL;
ALTER TABLE public.enforcement
  ADD resolved_at timestamp NULL;
ALTER TABLE public.enforcement
  ADD executant_id bigint NULL;
ALTER TABLE public.enforcement
  ADD CONSTRAINT enforcement_executant_id_fk
FOREIGN KEY (executant_id) REFERENCES executant (id);
ALTER TABLE public.enforcement
  ADD execute_document_id bigint NULL;
ALTER TABLE public.enforcement
  ADD CONSTRAINT enforcement_document_id_fk
FOREIGN KEY (execute_document_id) REFERENCES document (id);
ALTER TABLE public.enforcement
  ADD registrator_id bigint NULL;
ALTER TABLE public.enforcement
  ADD CONSTRAINT enforcement_facility_id_fk
FOREIGN KEY (registrator_id) REFERENCES facility (id);
ALTER TABLE public.enforcement
  RENAME COLUMN started_at TO created_at;
ALTER TABLE public.enforcement
  DROP sender;
ALTER TABLE public.enforcement
  DROP receiver;
ALTER TABLE public.enforcement
  DROP facility_id;