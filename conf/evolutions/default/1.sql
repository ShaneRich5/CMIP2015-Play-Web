# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        bigint not null,
  street                    varchar(255),
  town                      varchar(255),
  parish                    varchar(255),
  landmark                  varchar(255),
  user_id                   bigint,
  constraint pk_address primary key (id))
;

create table appliance (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_appliance primary key (id))
;

create table appliance_user (
  id                        bigint not null,
  user_id                   bigint,
  appliance_id              bigint,
  type_id                   bigint,
  constraint pk_appliance_user primary key (id))
;

create table type (
  id                        bigint not null,
  name                      varchar(255),
  watts                     double,
  appliance_id              bigint,
  constraint pk_type primary key (id))
;

create table users (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  number                    varchar(255),
  email                     varchar(255),
  active                    boolean,
  constraint pk_users primary key (id))
;

create sequence address_seq;

create sequence appliance_seq;

create sequence appliance_user_seq;

create sequence type_seq;

create sequence users_seq;

alter table address add constraint fk_address_user_1 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_address_user_1 on address (user_id);
alter table appliance_user add constraint fk_appliance_user_user_2 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_appliance_user_user_2 on appliance_user (user_id);
alter table appliance_user add constraint fk_appliance_user_appliance_3 foreign key (appliance_id) references appliance (id) on delete restrict on update restrict;
create index ix_appliance_user_appliance_3 on appliance_user (appliance_id);
alter table appliance_user add constraint fk_appliance_user_type_4 foreign key (type_id) references type (id) on delete restrict on update restrict;
create index ix_appliance_user_type_4 on appliance_user (type_id);
alter table type add constraint fk_type_appliance_5 foreign key (appliance_id) references appliance (id) on delete restrict on update restrict;
create index ix_type_appliance_5 on type (appliance_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists address;

drop table if exists appliance;

drop table if exists appliance_user;

drop table if exists type;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists address_seq;

drop sequence if exists appliance_seq;

drop sequence if exists appliance_user_seq;

drop sequence if exists type_seq;

drop sequence if exists users_seq;

