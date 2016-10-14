CREATE TABLE public.TEAM
(
    pk_id INT PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE public.TEAM_MATCH
(
    pk_id INT PRIMARY KEY,
    fk_team_a_id INT NOT NULL,
    fk_team_b_id INT NOT NULL,
    fk_match_date DATE NOT NULL,
    fk_match_time TIME NOT NULL
);

CREATE TABLE public.MATCH
(
    pk_date DATE,
    pk_time TIME,
    stadium VARCHAR NOT NULL,
    PRIMARY KEY(pk_date,pk_time)
);

CREATE TABLE public.STADIUM
(
    pk_id INT PRIMARY KEY NOT NULL,
    location VARCHAR NOT NULL,
    total_capacity VARCHAR NOT NULL
);

CREATE TABLE public.CUSTOMER
(
    CUSTOMER_NUMBER INT PRIMARY KEY,
    NAME VARCHAR,
    ADDRESS VARCHAR
);

CREATE TABLE public.TICKET
(
    TICKET_NUMBER INT PRIMARY KEY,
    DATE DATE,
    FK_CUSTOMER_NUMBER INT,
    FK_CONTINGENT_ID INT,
    FK_PLACE_NUMBER INT,
    FK_PLACE_ROW INT,
    FK_PLACE_SECTION INT
);

CREATE TABLE public.CATEGORY
(
      DESCRIPTION VARCHAR PRIMARY KEY,
      PRICE INT
);

CREATE TABLE public.CONTINGENT
(
      ID int PRIMARY KEY,
      QUANTITY INT,
      FK_MATCH_DATE DATE ,
      FK_MATCH_TIME TIME
);

CREATE TABLE public.PLACE
(
    PLACE_NUMBER INT,
    PLACE_ROW INT,
    PLACE_SECTION INT,
    FK_CATEGORY_DESCRIPTION VARCHAR,
    FK_STADION_ID INT,
    FK_TICKET_NUMBER INT,
    PRIMARY KEY (PLACE_NUMBER,PLACE_ROW,PLACE_SECTION)
);

CREATE TABLE public.WITH_TRACK
(
  number_tracks INT
) INHERITS (STADIUM);

CREATE TABLE public.WITHOUT_TRACK
()INHERITS (STADIUM);
-- Contingent
ALTER TABLE public.contingent
  ADD CONSTRAINT contingent_match_date_time
  FOREIGN KEY (fk_match_date,FK_MATCH_TIME) REFERENCES MATCH(pk_date, pk_time);

-- -Place
ALTER TABLE public.PLACE
  ADD CONSTRAINT place_category_description
  FOREIGN KEY (FK_CATEGORY_DESCRIPTION) REFERENCES CATEGORY(description);
ALTER TABLE public.PLACE
  ADD CONSTRAINT place_stadion_id
  FOREIGN KEY (fk_stadion_id) REFERENCES stadium(pk_id);
ALTER TABLE public.PLACE
  ADD CONSTRAINT place_ticket_number
  FOREIGN KEY (FK_TICKET_NUMBER) REFERENCES ticket(ticket_number);

-- Team-Match
ALTER TABLE public.TEAM_MATCH
  ADD CONSTRAINT team_match_team_a_id
  FOREIGN KEY (fk_team_a_id) REFERENCES TEAM(pk_id);
ALTER TABLE public.TEAM_MATCH
  ADD CONSTRAINT team_match_team_b_id
  FOREIGN KEY (fk_team_b_id) REFERENCES TEAM(pk_id);
ALTER TABLE public.TEAM_MATCH
  ADD CONSTRAINT team_match_match_date_time
  FOREIGN KEY (fk_match_date,fk_match_time) REFERENCES MATCH(pk_date, pk_time);

-- Ticket
ALTER TABLE public.TICKET
  ADD CONSTRAINT ticket_place_number_row_section
  FOREIGN KEY (FK_PLACE_ROW,FK_PLACE_NUMBER,FK_PLACE_SECTION) REFERENCES place(PLACE_ROW,PLACE_NUMBER,PLACE_SECTION);
ALTER TABLE public.TICKET
  ADD CONSTRAINT ticket_customer_number
  FOREIGN KEY (FK_CUSTOMER_NUMBER) REFERENCES CUSTOMER(customer_number);
ALTER TABLE public.TICKET
  ADD CONSTRAINT ticket_contingent_id
  FOREIGN KEY (FK_CONTINGENT_ID) REFERENCES CONTINGENT(id);