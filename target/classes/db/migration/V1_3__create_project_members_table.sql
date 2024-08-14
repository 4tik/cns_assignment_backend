drop table if exists project_member;
create table project_member(
    id  serial  primary key not null,
    project_id  bigint,
    user_id     bigint,
    created_on  timestamp,
    created_by  bigint not null
);