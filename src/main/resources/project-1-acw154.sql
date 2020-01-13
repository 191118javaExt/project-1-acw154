--create schema project1;
--SET search_path TO project1, postgres;
DROP TABLE IF EXISTS ers_reimbursement;

CREATE TABLE IF NOT EXISTS ers_users (
	ers_users_id SERIAL PRIMARY KEY,
	ers_username VARCHAR(50) UNIQUE NOT NULL,
	ers_password VARCHAR(50) NOT NULL,
	user_first_name VARCHAR(100),
	user_last_name VARCHAR(100),
	user_email VARCHAR(150) UNIQUE,
	user_role_id INTEGER 
);

ALTER SEQUENCE ers_users_ers_users_id_seq RESTART WITH 1000 INCREMENT BY 2;

CREATE TABLE IF NOT EXISTS ers_reimbursement (
	reimb_id SERIAL PRIMARY KEY,
	reimb_amount NUMERIC(30, 2) NOT NULL,
	reimb_submitted TIMESTAMP,
	reimb_resolved TIMESTAMP,
	reimb_description VARCHAR(250),
	reimb_receipt BYTEA,
	reimb_author INTEGER NOT NULL,
	reimb_resolver INTEGER,
	reimb_status_id INTEGER NOT NULL,
	reimb_type_id INTEGER NOT NULL
);

ALTER SEQUENCE ers_reimbursement_reimb_id_seq RESTART WITH 100 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS ers_reimbursement_status (
	reimb_status_id INTEGER PRIMARY KEY,
	reimb_status VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS ers_reimbursement_type (
	reimb_type_id INTEGER PRIMARY KEY,
	reimb_type VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS ers_user_roles (
	ers_user_role_id INTEGER PRIMARY KEY,
	user_role VARCHAR(10)
);
ALTER TABLE ers_users ADD CONSTRAINT user_roles_FK
	FOREIGN KEY (user_role_id) REFERENCES ers_user_roles (ers_user_role_id);

ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_users_fk_auth
	FOREIGN KEY (reimb_author) REFERENCES ers_users (ers_users_id);

ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_users_fk_reslvr
	FOREIGN KEY (reimb_resolver) REFERENCES ers_users (ers_users_id);

ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_reimbursement_status_fk
	FOREIGN KEY (reimb_status_id) REFERENCES ers_reimbursement_status (reimb_status_id);

ALTER TABLE ers_reimbursement ADD CONSTRAINT ers_reimbursement_type_fk
	FOREIGN KEY (reimb_type_id) REFERENCES ers_reimbursement_type (reimb_type_id);

--ALTER TABLE ers_users ALTER COLUMN ers_password TYPE VARCHAR(64);
--ALTER TABLE ers_user_roles ALTER COLUMN user_role TYPE VARCHAR(20);
--Already Done
INSERT INTO ers_reimbursement_status VALUES (-1, 'DENIED');
INSERT INTO ers_reimbursement_status VALUES (0, 'PENDING');
INSERT INTO ers_reimbursement_status VALUES (1, 'APPROVED');
INSERT INTO ers_reimbursement_type VALUES (1, 'LODGING');
INSERT INTO ers_reimbursement_type VALUES (2, 'TRAVEL');
INSERT INTO ers_reimbursement_type VALUES (3, 'FOOD');
INSERT INTO ers_reimbursement_type VALUES (4, 'OTHER');
INSERT INTO ers_user_roles VALUES (1, 'EMPLOYEE');
INSERT INTO ers_user_roles VALUES (2, 'FINANCEMANAGER');

SELECT * FROM ers_reimbursement_status ers;
SELECT * FROM ers_reimbursement_type ert;
SELECT * FROM ers_user_roles eur;


CREATE OR REPLACE FUNCTION set_submitted_time_f() RETURNS TRIGGER 
AS 
$$
	BEGIN 
		NEW.reimb_submitted = now();
		return NEW;
	END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION set_resolved_time_f() RETURNS TRIGGER 
AS 
$$
	BEGIN 
		NEW.reimb_resolved = now();
		return NEW;
	END
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_submitted_time
before insert
ON ers_reimbursement
FOR EACH row
	EXECUTE PROCEDURE set_submitted_time_f();

CREATE TRIGGER set_resolved_time
BEFORE UPDATE
ON ers_reimbursement
FOR EACH row
	EXECUTE PROCEDURE set_resolved_time_f();
--
--
--DROP TRIGGER set_resolved_time on ers_reimbursement;

--UPDATE ers_reimbursement SET reimb_status_id = 0, reimb_resolver = null WHERE reimb_id= 1;

UPDATE ers_reimbursement SET reimb_status_id = 0 WHERE reimb_id = 9;
