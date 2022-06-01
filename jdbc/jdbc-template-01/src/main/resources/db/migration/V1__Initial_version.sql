create TABLE movie (
    id BIGINT PRIMARY KEY,
    name TEXT NOT NULL,
    release_date DATE NOT NULL,
    unique (name)
);

create TABLE author (
id BIGINT PRIMARY KEY,
name TEXT NOT NULL,
surname TEXT NOT NULL,
birthday DATE NOT NULL,
birthplace TEXT NOT NULL
);

create TABLE author_movies (
 id BIGINT PRIMARY KEY,
 create_date DATE NOT NULL
);