-- create schema if not exists hole;

create table if not exists hole.example_table
(
    id int auto_increment
        primary key,
    column_1 varchar(20)   null,
    column_2 tinyint       null,
    column_3 int           null,
    column_4 varchar(200)  null
);

