create table executant (
  id          bigserial primary key           not null,
  user_id     bigint references app_user (id) not null,
  name        text,
  surname     text,
  middlename  text,
  phone       text,
  facility_id bigint references facility (id) not null,
  role        int
);