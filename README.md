
# SpringWebflux-Bank Management System
Spring webflux security application with JWT and R2DBC Mysql

 
## To run the project
spring.r2dbc.url=r2dbc:pool:mysql://localhost:3306/bankingapp

spring.r2dbc.username=root

spring.r2dbc.password=root

Change these credentials according to your local computer

 
# Postman Api
## Register new User

POST request

controller - http://localhost:8080/register

router - http://localhost:8080/fn/register

{

    "name": "atik",
	"username": "atikshaikh",
	"password": "pass",
	"address": "wakadi",
	"state": "maharashtra",
	"country": "India",
	"emailAddress": "shaikhatikrajjak@gmail.com",
	"pan": "2164315454321654",
	"contactNumber": "9420626475",
	"dob": "03/06/1998",
	"accountType": "Savings",
    "roles": "ROLE_USER"
}

response: 
{

    "id": 2,
    "username": "atikshaikh",
    "password": "pass",
    "name": "atik",
    "roles": "ROLE_USER",
    "emailAddress": "shaikhatikrajjak@gmail.com",
    "contactNumber": "9420626475",
    "dob": "03/06/1998",
    "accountType": "Savings",
    "address": "wakadi",
    "state": "maharashtra",
    "country": "India",
    "enabled": false,
    "accountNonLocked": false,
    "credentialsNonExpired": false,
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "accountNonExpired": false,
    "pan": "2164315454321654"
}


## Login user

POST request

controller - http://localhost:8080/login

router - http://localhost:8080/fn/login
 

{

    "username": "atikshaikh",
    "password": "pass" 
}

reponse: 
{

    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhdGlrc2hhaWtoIiwiZXhwIjoxNjMwMDgwMDk4LCJpYXQiOjE2MzAwNjIwOTh9.kykPWvVGVSqh2HcGBaH8eJA7SThlEVSxd15IVQy6O-ByrSjcqQXVHzJcgPYJuEP4FVn1GGz3wXKSQlXz7QZNTg"
}

## Update Account

PUT request

controller - http://localhost:8080/update-account/{id}

router - http://localhost:8083/fn/update-account/{id}

authentication header - bearer jwttoken
 

{

    "name": "atik rajjak shaikh",
	"username": "atikshaikh",
	"password": "pass",
	"address": "wakadi",
	"state": "maharashtra",
	"country": "India",
	"emailAddress": "shaikhatikrajjak@gmail.com",
	"pan": "2164315454321654",
	"contactNumber": "9420626475",
	"dob": "03/06/1998",
	"accountType": "Savings",
    "roles": "ROLE_USER"
}

response: 
{

    "statusCode": 200,
    "status": "OK",
    "id": 1,
    "message": "Account Updated!"
}

## Apply Loan

POST request

controller - http://localhost:8080/apply-loan/{userId}

router - http://localhost:8082/fn/apply-loan/{userId}

authentication header - bearer jwttoken

{

    "loanType": "Home Loan",
    "loanAmount": 12000,
    "date": "21/08/2021",
    "rateOfInterest": 7,
    "durationOfLoan": 2
}

response: 
{

    "statusCode": 201,
    "status": "CREATED",
    "id": 1,
    "message": "Loan Applied"
}
