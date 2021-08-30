CREATE TABLE if not exists user  (

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
