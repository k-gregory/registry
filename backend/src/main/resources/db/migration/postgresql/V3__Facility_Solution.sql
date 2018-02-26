create table facility(
  id bigserial primary key not null,
  name text
);

create table solution(
  id bigserial primary key not null,
  sender text,
  receiver text,
  facility_id bigint references facility(id)
)