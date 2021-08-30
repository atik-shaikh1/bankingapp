drop table if exists user;
CREATE TABLE IF NOT EXISTS user
(
    id int auto_increment primary key,
	name varchar(50),
	username varchar(50) unique,
	password varchar(50),
	address varchar(50),
	state varchar(50),
	country varchar(50),
	email_address varchar(50),
	pan varchar(50),
	contact_number varchar(50),
	dob varchar(50),
	account_type varchar(50),
    roles VARCHAR(50)
);

INSERT INTO user (name, username, password, address, state, country, email_address, pan, contact_number, dob, account_type, roles)
VALUES (
	'atik', 
	'atik shaikh',
	'pass', 
	'wakadi', 
	'maharashtra',
	'India',
	'shaikh@gmail.com',
	'454851215181',
	'9420626475',
	'03/06/1998',
	'Savings',
	'ROLE_USER'
);