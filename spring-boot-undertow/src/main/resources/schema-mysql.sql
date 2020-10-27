create schema if not exists samuro;

create table if not exists samuro.table_name
(
    column_1 int auto_increment
        primary key,
    column_2 int null,
    column_3 int null,
    constraint table_name_column_2_uindex
        unique (column_2)
);


