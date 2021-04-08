create table rule (
    id serial primary key,
    name text
);

create table type (
    id serial primary key,
    name text
);

create table accident (
    id serial primary key,
    name text,
    text text,
    address text,
    type_id int references type(id)
);

create table accident_rule (
    id serial primary key ,
    accident_id int references accident(id),
    rule_id int references rule(id)
);
