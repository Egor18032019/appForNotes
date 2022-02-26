DROP table users CASCADE;
DROP table user_notes ;


CREATE SEQUENCE global_seq START WITH 10;
CREATE TABLE users
(
    idusers  INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    login    VARCHAR NOT NULL,
    password VARCHAR  NOT NULL,
    CONSTRAINT user_email_login UNIQUE (login)
);

CREATE TABLE user_notes
(
    user_id     INTEGER NOT NULL,
    user_notes  VARCHAR,
    timestamptz timestamp without time zone,
    FOREIGN KEY (user_id) REFERENCES users (idusers) ON DELETE CASCADE
);