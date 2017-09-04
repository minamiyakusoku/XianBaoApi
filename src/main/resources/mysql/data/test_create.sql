SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS af_details;
DROP TABLE IF EXISTS af_info;




/* Create Tables */

CREATE TABLE af_details
(
	id bigint NOT NULL,
	name varchar(32) NOT NULL,
	idcard varchar(50) NOT NULL,
	deposit_date varchar(50) NOT NULL,
	deposit_unit varchar(50) NOT NULL,
	deposit_base decimal(10,2) NOT NULL,
	unit_deposit decimal(10,2) NOT NULL,
	person_deposit decimal(10,2) NOT NULL,
	total decimal(10,2) NOT NULL,
	is_booked varchar(4) NOT NULL,
	PRIMARY KEY (id)
);


CREATE TABLE af_info
(
	id bigint NOT NULL,
	name varchar(50) NOT NULL,
	idcard varchar(100) NOT NULL,
	accu_found_no varchar(20) NOT NULL,
	deposit_unit varchar(200) NOT NULL,
	last_deposit_date varchar(20) NOT NULL,
	unit_deposit decimal(10,2) NOT NULL,
	person_deposit decimal(10,2),
	total decimal(10,2) NOT NULL,
	deposit_status varchar(8) NOT NULL,
	PRIMARY KEY (id)
);



