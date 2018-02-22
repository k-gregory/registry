create table comment (
  id      bigserial primary key  not null,
  message varchar(1000)          not null
);

create table app_user (
  id       bigserial    not null primary key,
  username varchar(50)  not null unique,
  password varchar(500) not null,
  enabled  boolean      not null
);

create table app_group (
  id   bigserial   not null primary key,
  name varchar(50) not null unique
);

create table authority (
  id   bigserial not null primary key,
  name varchar(50) unique
);

create table group_members (
  group_id bigint not null references app_group (id),
  user_id  bigint not null references app_user (id),
  unique (group_id, user_id)
);

create table user_authorities (
  user_id      bigint not null references app_user (id),
  authority_id bigint not null references authority (id),
  unique (user_id, authority_id)
);

create table group_authorities (
  group_id     bigint not null references app_group (id),
  authority_id bigint not null references authority (id),
  unique (group_id, authority_id)
);