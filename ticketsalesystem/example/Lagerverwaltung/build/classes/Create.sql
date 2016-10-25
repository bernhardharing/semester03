CREATE TABLE artikelstamm(artikelnummer VARCHAR(20) NOT NULL,
                          bezeichnung VARCHAR(20));
						  
ALTER TABLE artikelstamm ADD CONSTRAINT artikelstamm_artikelnummer_pk PRIMARY KEY(artikelnummer);

CREATE SEQUENCE bestand_bestandsid_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE bestand(bestandsid INT NOT NULL DEFAULT NEXTVAL('bestand_bestandsid_sequence'),
                     menge INT NOT NULL,
					 artikelnummer VARCHAR(20) NOT NULL);
						  
ALTER TABLE bestand ADD CONSTRAINT bestand_bestandsid_pk PRIMARY KEY(bestandsid);
ALTER TABLE bestand ADD CONSTRAINT bestand_artikelnummer_fk FOREIGN KEY(artikelnummer) REFERENCES artikelstamm(artikelnummer);

CREATE TABLE charge(charge VARCHAR(20) NOT NULL);

ALTER TABLE charge ADD CONSTRAINT charge_charge_pk PRIMARY KEY(charge);

CREATE TABLE artikelstamm_charge(artikelnummer VARCHAR(20) NOT NULL,
                                 charge VARCHAR(20) NOT NULL);
						  
ALTER TABLE artikelstamm_charge ADD CONSTRAINT artikelstamm_charge_artikelnummer_charge_pk PRIMARY KEY(artikelnummer, charge);
ALTER TABLE artikelstamm_charge ADD CONSTRAINT artikelstamm_charge_artikelnummer_fk FOREIGN KEY(artikelnummer) REFERENCES artikelstamm(artikelnummer);
ALTER TABLE artikelstamm_charge ADD CONSTRAINT artikelstamm_charge_charge_fk FOREIGN KEY(charge) REFERENCES charge(charge);

CREATE SEQUENCE kunde_kundennummer_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE kunde(kundennummer INT NOT NULL DEFAULT NEXTVAL('kunde_kundennummer_sequence'),
                   name VARCHAR(40) NOT NULL);
                   
ALTER TABLE kunde ADD CONSTRAINT kunde_kundennummer_pk PRIMARY KEY(kundennummer);

CREATE SEQUENCE bestellkopf_bestellnummer_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE bestellkopf(bestellnummer INT NOT NULL DEFAULT NEXTVAL('bestellkopf_bestellnummer_sequence'),
                         kundennummer INT NOT NULL);

ALTER TABLE bestellkopf ADD CONSTRAINT bestellkopf_bestellnummer_pk PRIMARY KEY(bestellnummer);
ALTER TABLE bestellkopf ADD CONSTRAINT bestellkopf_kundennummer_fk FOREIGN KEY(kundennummer) REFERENCES kunde(kundennummer);

CREATE TABLE bestellposition(bestellnummer INT NOT NULL,
							 positionsnummer INT NOT NULL,
                             artikelnummer VARCHAR(20) NOT NULL,
                             menge INT NOT NULL);
							 
ALTER TABLE bestellposition ADD CONSTRAINT bestellposition_bestellnummer_bestellposition_pk PRIMARY KEY(bestellnummer, positionsnummer);
ALTER TABLE bestellposition ADD CONSTRAINT bestellposition_artikelnummer_fk FOREIGN KEY(artikelnummer) REFERENCES artikelstamm(artikelnummer);
ALTER TABLE bestellposition ADD CONSTRAINT bestellposition_bestellnummer_fk FOREIGN KEY(bestellnummer) REFERENCES bestellkopf(bestellnummer);

CREATE SEQUENCE lieferschein_lieferscheinnummer_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE lieferschein(lieferscheinnummer INT NOT NULL DEFAULT NEXTVAL('lieferschein_lieferscheinnummer_sequence'),
                          bestellnummer INT NOT NULL);
						  
ALTER TABLE lieferschein ADD CONSTRAINT lieferschein_lieferscheinnummer_pk PRIMARY KEY(lieferscheinnummer);
ALTER TABLE lieferschein ADD CONSTRAINT lieferschein_bestellnummer_fk FOREIGN KEY(bestellnummer) REFERENCES bestellkopf(bestellnummer);


-- Narrow rights for ALL USERS (PUBLIC) ------------------------------
REVOKE CREATE /* SCHEMA*/ ON DATABASE postgres FROM PUBLIC;
REVOKE CREATE /* OBJECTS*/ ON SCHEMA public FROM PUBLIC;
-- Reader -----------------------------------------------------------
CREATE USER reader WITH PASSWORD 'reader';
CREATE ROLE READER_ROLE;
GRANT READER_ROLE TO reader;

-- DATA_ENTRY -------------------------------------------------------
CREATE USER readandwrite WITH PASSWORD 'readandwrite';
CREATE ROLE DATA_ENTRY_ROLE;
GRANT READER_ROLE TO DATA_ENTRY_ROLE;
GRANT DATA_ENTRY_ROLE TO readandwrite;
-- Admin -----------------------------------------------------------
CREATE USER admin WITH PASSWORD 'admin';
CREATE ROLE ADMIN_ROLE;
GRANT CREATE ON SCHEMA public TO ADMIN_ROLE; -- schema privilge
GRANT DATA_ENTRY_ROLE TO ADMIN_ROLE;
GRANT ADMIN_ROLE TO admin;
-- Object Privileges --------------------------------------------------
GRANT SELECT ON ALL TABLES IN SCHEMA public TO READER_ROLE;
GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO READER_ROLE;
GRANT INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO DATA_ENTRY_ROLE;
GRANT UPDATE ON ALL SEQUENCES IN SCHEMA public TO DATA_ENTRY_ROLE;
GRANT DELETE ON ALL TABLES IN SCHEMA public TO ADMIN_ROLE;