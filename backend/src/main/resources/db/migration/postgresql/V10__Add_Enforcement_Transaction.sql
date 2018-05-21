create table enforcement_transaction (
  id             bigserial primary key not null,
  enforcement_id bigint                not null references enforcement (id),
  description    text,
  document_id    bigint                not null references document (id),
  money          bigint
);