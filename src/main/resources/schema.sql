DROP TABLE IF EXISTS Person;

CREATE TABLE Person(
   id identity not null primary key,
   first_name varchar(100) not null,
   last_name varchar(100) not null,
   age int check(age > 0 and age < 100)

)