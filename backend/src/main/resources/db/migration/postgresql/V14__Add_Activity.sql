create table activity (
  id                bigserial primary key,
  facility_id       bigint references facility (id),
  type              text,
  initiator_user_id bigint references enforcement_subject (id),
  payload           text
);