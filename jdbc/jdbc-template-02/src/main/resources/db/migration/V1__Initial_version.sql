--For postgresql tables --
create TABLE movie (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    release_date DATE,
    unique (name)
);

create TABLE author (
  id BIGSERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  surname TEXT NOT NULL,
  birthday DATE,
  birthplace TEXT NOT NULL
);

--For mysql tables --
create TABLE movie (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `release_date` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;

create TABLE author (
   `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `surname` VARCHAR(200) NOT NULL,
  `birthday` DATE NULL,
  `birthplace` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`)
  )
ENGINE = InnoDB;