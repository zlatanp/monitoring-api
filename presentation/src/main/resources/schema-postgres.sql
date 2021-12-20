DROP TABLE IF EXISTS monitoring;
CREATE TABLE monitoring
(
    id          UUID PRIMARY KEY,
    temperature DOUBLE PRECISION NOT NULL,
    date VARCHAR(255)
);

INSERT INTO monitoring(id, temperature, date)
VALUES ('cbddb968-3bf3-11ec-a19a-5221958ff6ed', 17, 'Wed Sep 08 17:51:10 CEST 2021');
INSERT INTO monitoring(id, temperature, date)
VALUES ('e917b631-3af0-11ec-b630-d654e28ef683', 18.6, 'Wed Sep 08 17:52:10 CEST 2021');
INSERT INTO monitoring(id, temperature, date)
VALUES ('130e251b-3bc6-11ec-9270-420089b60334', 30.1, 'Wed Sep 08 17:53:10 CEST 2021');
INSERT INTO monitoring(id, temperature, date)
VALUES ('09f9eb0e-408e-11ec-8bc1-7aa8ce01b8ca', 17.8, 'Wed Sep 08 17:54:10 CEST 2021');
INSERT INTO monitoring(id, temperature, date)
VALUES ('7469b5bd-4087-11ec-be0b-1a0fe12c1d64', 21.2, 'Wed Sep 08 17:55:10 CEST 2021');
INSERT INTO monitoring(id, temperature, date)
VALUES ('fcf0419d-40a8-11ec-a815-42606761c0bc', 21.3, 'Wed Sep 08 17:56:10 CEST 2021');
