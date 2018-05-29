ALTER TABLE public.enforcement
  ADD sender_id bigint NULL;
ALTER TABLE public.enforcement
  ADD CONSTRAINT enforcement_subject_id_fk_s
FOREIGN KEY (sender_id) REFERENCES subject (id);
ALTER TABLE public.enforcement
  ADD receiver_id bigint NULL;
ALTER TABLE public.enforcement
  ADD CONSTRAINT enforcement_subject_id_fk_r
FOREIGN KEY (receiver_id) REFERENCES subject (id);
ALTER TABLE public.enforcement
  DROP sender;
ALTER TABLE public.enforcement
  DROP receiver;