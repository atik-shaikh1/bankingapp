DROP TABLE IF EXISTS loan;
CREATE TABLE IF NOT EXISTS loan (

	id int auto_increment primary key,
	loan_type varchar(50),
	loan_amount decimal,
	date varchar(50),
	rate_of_interest decimal,
	duration_of_loan decimal,
	user_id int

);
