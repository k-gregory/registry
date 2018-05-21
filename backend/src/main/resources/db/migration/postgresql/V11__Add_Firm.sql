create table firm (
  id                  bigserial primary key not null,
  user_id             bigint references app_user (id) not null,
  name                text,
  edrpou_code         text not null unique,
  phone               text,
  email               text,
  bank_account_number text not null,
  bank_mfo            text
);