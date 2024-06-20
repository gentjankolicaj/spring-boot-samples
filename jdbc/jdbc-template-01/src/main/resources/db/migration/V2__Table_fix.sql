
--Drop wrongly create tables
DROP TABLE author;
DROP TABLE author_movies;

--Create new tables
create TABLE actor (
id BIGSERIAL PRIMARY KEY,
name TEXT NOT NULL,
surname TEXT NOT NULL,
birthday DATE NOT NULL,
birthplace TEXT NOT NULL
);

create TABLE actor_movies (
 id BIGSERIAL PRIMARY KEY,
 create_date DATE NOT NULL
);

--Drops existing column primary key and creates a new
ALTER TABLE movie drop COLUMN id;
ALTER TABLE movie ADD COLUMN id BIGSERIAL PRIMARY KEY;