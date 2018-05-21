create table enforcement_subject (
  id             bigserial primary key not null,
  type           int,
  firm_id        bigint references firm (id),
  individual_id  varchar(10) references individual (uid),
  enforcement_id bigint references enforcement (id)
);