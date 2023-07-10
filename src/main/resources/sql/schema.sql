CREATE SCHEMA `cinema_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;


CREATE TABLE cinema(
                       id INTEGER PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(50) NOT NULL
);


CREATE TABLE film(
                     film_id INTEGER PRIMARY KEY AUTO_INCREMENT,
                     title VARCHAR(50) NOT NULL,
                     cinema_id INTEGER NOT NULL,
                     CONSTRAINT FOREIGN KEY (cinema_id) REFERENCES cinema(id)
);


CREATE TABLE actor(
                      id INTEGER PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(60) NOT NULL
);


CREATE TABLE film_actor(
                           film_id INTEGER,
                           actor_id INTEGER,
                           CONSTRAINT PRIMARY KEY (film_id, actor_id),
                           CONSTRAINT FOREIGN KEY (film_id) REFERENCES film(film_id) ON DELETE CASCADE,
                           CONSTRAINT FOREIGN KEY (actor_id) REFERENCES actor(id) ON DELETE CASCADE
);


