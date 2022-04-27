DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authorities;

create table users
(
  username   VARCHAR(50)  not null primary key,
  first_name VARCHAR(50),
  last_name  VARCHAR(50),
  password   VARCHAR(200) not null,
  email      VARCHAR(100) not null,
  enabled    boolean      not null
);

create table authorities
(
  username  VARCHAR(50) not null,
  authority VARCHAR(50) not null,
  constraint fk_authorities_users foreign key (username) references users (username)
);

create unique index ix_auth_username on authorities (username, authority);

INSERT into users(username,password,email,enabled) values ('user','{noop}userPass','user@gmail.com',true);
INSERT into users(username,password,email,enabled) values ('admin','{noop}adminPass','admin@gmail.com',true);

INSERT into authorities values ('user','USER');
INSERT into authorities values ('admin','ADMIN');