CREATE SEQUENCE option_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE poll_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE vote_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE option
(
    option_id    bigint auto_increment,
    option_value VARCHAR(255),
    poll_id      BIGINT,
    CONSTRAINT pk_option PRIMARY KEY (option_id)
);

CREATE TABLE poll
(
    poll_id  BIGINT auto_increment,
    question VARCHAR(255),
    CONSTRAINT pk_poll PRIMARY KEY (poll_id)
);

CREATE TABLE users
(
    user_id    BIGINT auto_increment,
    username   VARCHAR(255),
    password   VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    admin      CHAR   NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

CREATE TABLE vote
(
    vote_id   BIGINT auto_increment,
    option_id BIGINT,
    CONSTRAINT pk_vote PRIMARY KEY (vote_id)
);

ALTER TABLE option
    ADD CONSTRAINT FK_OPTION_ON_POLL FOREIGN KEY (poll_id) REFERENCES poll (poll_id);

ALTER TABLE vote
    ADD CONSTRAINT FK_VOTE_ON_OPTION FOREIGN KEY (option_id) REFERENCES option (option_id);