CREATE TABLE employee
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE one_on_one
(
    id            BIGSERIAL PRIMARY KEY,
    title         VARCHAR(255)                    NOT NULL,
    participant_1 BIGINT REFERENCES employee (id) NOT NULL,
    participant_2 BIGINT REFERENCES employee (id) NOT NULL,
    planned_date  TIMESTAMP WITH TIME ZONE        NOT NULL,
    description   TEXT                            NULL,
    location      VARCHAR(255)                    NOT NULL
);

INSERT INTO employee (name)
VALUES ('Attila'),
       ('Balazs'),
       ('Cecilia'),
       ('Dia'),
       ('Emese'),
       ('Ferenc');

INSERT INTO one_on_one (title, participant_1, participant_2, planned_date, description, location)
VALUES ('Attila:Ceclilia', 1, 3, now(), 'regular one on one', 'zoom');
