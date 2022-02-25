
CREATE TABLE users
(
    idusers  INTEGER PRIMARY KEY,
    login     VARCHAR NOT NULL,
    password  BIGINT  NOT NULL,
    CONSTRAINT user_phone UNIQUE (login)
 );

CREATE TABLE user_notes
(
    user_id INTEGER NOT NULL,
    user_notes    VARCHAR,

    FOREIGN KEY (user_id) REFERENCES users (idusers) ON DELETE CASCADE
);