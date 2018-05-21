ALTER TABLE public.individual
  ADD inn int NULL;
ALTER TABLE public.individual
  ADD phone text NULL;
ALTER TABLE public.individual
  ADD email text NULL;
ALTER TABLE public.individual
  ADD bank_account_number text NULL;
ALTER TABLE public.individual
  ADD bank_mfo text NULL;
ALTER TABLE public.individual
  RENAME COLUMN firstname TO name;
ALTER TABLE public.individual
  RENAME COLUMN middlename TO surname;
ALTER TABLE public.individual
  RENAME COLUMN lastname TO middle_name;