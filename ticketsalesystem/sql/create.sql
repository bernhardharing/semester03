CREATE TABLE public.TEAM
(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE public.TEAM_MATCH
(
    id SERIAL PRIMARY KEY,
    fk_team_a_id INT NOT NULL,
    fk_team_b_id INT NOT NULL,
    fk_match_ID INT
);

CREATE SEQUENCE public.Match_Sequence START WITH 1001 INCREMENT BY 1;

CREATE TABLE public.MATCH
(
    id INTEGER NOT NULL DEFAULT nextval('public.Match_Sequence') PRIMARY KEY,
    date DATE,
    time TIME,
    stadium VARCHAR
);

CREATE TABLE public.STADIUM
(
    id SERIAL PRIMARY KEY,
    location VARCHAR NOT NULL,
    total_capacity INT NOT NULL
);

CREATE SEQUENCE public.Customer_Sequence START WITH 1001 INCREMENT BY 1;
CREATE TABLE public.CUSTOMER
(
    CUSTOMER_NUMBER INTEGER NOT NULL DEFAULT nextval('public.Customer_Sequence') PRIMARY KEY ,
    NAME VARCHAR,
    ADDRESS VARCHAR
);


CREATE SEQUENCE public.Ticket_Sequence START WITH 1001 INCREMENT BY 1;
CREATE TABLE public.TICKET
(
    TICKET_NUMBER INTEGER NOT NULL DEFAULT nextval('public.Ticket_Sequence') PRIMARY KEY ,
    DATE DATE,
    FK_CUSTOMER_NUMBER INT,
    FK_CONTINGENT_ID INT,
    FK_PLACE_ID INT,
    FK_MATCH_ID INT
);

CREATE TABLE public.CATEGORY
(
      DESCRIPTION VARCHAR PRIMARY KEY,
      PRICE INT
);

CREATE SEQUENCE public.Contingent_Sequence START WITH 1001 INCREMENT BY 1;

CREATE TABLE public.CONTINGENT
(
  id INTEGER NOT NULL DEFAULT nextval('public.Contingent_Sequence') PRIMARY KEY,
      QUANTITY INT,
      FK_MATCH_ID INT
);

CREATE SEQUENCE public.Place_Sequence START WITH 1001 INCREMENT BY 1;

CREATE TABLE public.PLACE
(
    id INTEGER NOT NULL DEFAULT nextval('public.Place_Sequence') PRIMARY KEY,
    FK_CATEGORY_DESCRIPTION VARCHAR,
    FK_STADION_ID INT
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
  FOREIGN KEY (fk_match_id) REFERENCES MATCH(id);

-- Place
ALTER TABLE public.PLACE
  ADD CONSTRAINT place_category_description
  FOREIGN KEY (FK_CATEGORY_DESCRIPTION) REFERENCES CATEGORY(description);
ALTER TABLE public.PLACE
  ADD CONSTRAINT place_stadion_id
  FOREIGN KEY (fk_stadion_id) REFERENCES stadium(id);

-- Team-Match
ALTER TABLE public.TEAM_MATCH
  ADD CONSTRAINT team_match_team_a_id
  FOREIGN KEY (fk_team_a_id) REFERENCES TEAM(id);
ALTER TABLE public.TEAM_MATCH
  ADD CONSTRAINT team_match_team_b_id
  FOREIGN KEY (fk_team_b_id) REFERENCES TEAM(id);
ALTER TABLE public.TEAM_MATCH
  ADD CONSTRAINT team_match_match_date_time
  FOREIGN KEY (fk_match_id) REFERENCES MATCH(id);

-- Ticket
ALTER TABLE public.TICKET
  ADD CONSTRAINT ticket_place_number_row_section
  FOREIGN KEY (FK_PLACE_id) REFERENCES place(id);
ALTER TABLE public.TICKET
  ADD CONSTRAINT ticket_customer_number
  FOREIGN KEY (FK_CUSTOMER_NUMBER) REFERENCES CUSTOMER(customer_number);
ALTER TABLE public.TICKET
  ADD CONSTRAINT ticket_contingent_id
  FOREIGN KEY (FK_CONTINGENT_ID) REFERENCES CONTINGENT(id);