CREATE TABLE public.TEAM
(
    pk_id INT PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);

CREATE TABLE public.TEAM_MATCH
(
    pk_id INT PRIMARY KEY NOT NULL,
    fk_team_a_id VARCHAR NOT NULL,
    fk_team_b_id VARCHAR NOT NULL,
    fk_match_date DATE NOT NULL,
    fk_match_time TIME NOT NULL
);

CREATE TABLE public.MATCH
(
    pk_date DATE PRIMARY KEY NOT NULL,
    pk_time TIME PRIMARY KEY NOT NULL,
    stadium VARCHAR NOT NULL
);

CREATE TABLE public.STADIUM
(
    pk_id INT PRIMARY KEY NOT NULL,
    location VARCHAR NOT NULL,
    total_capacity VARCHAR NOT NULL
);

CREATE TABLE public.WITH_TRACK
(
    fk_stadium_id INT NOT NULL,
    number_tracks INT
);

CREATE TABLE public.WITHOUT_TRACK
(
    fk_stadium_id INT NOT NULL,
);
