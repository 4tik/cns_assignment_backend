drop table if exists projects;
create table projects(
    id  serial      primary key not null,
    name            varchar(250),
    introduce       varchar(500),
    status          int,
    start_date_time date null,
    end_date_time   date null,
    created_on      timestamp,
    created_by      bigint not null,
    updated_on      timestamp,
    updated_by      bigint null
);