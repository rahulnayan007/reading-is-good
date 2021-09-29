## reading-is-good
	Documentation for centralized warehouse system for ReadingIsGood

## Maven command to compile and test
	mvn clean compile test install

## Maven command to run app from command prompt
	mvn spring-boot:run

## Code coverage report
	\target\site\jacoco\index.html

## Log File
	\log\app.log


# API Details:-

## All APIs are designed to adhere to a standard Request and Response format with a Status block. 
	This system is developed on Java 11 and uses frameworks like SpringBoot, Spring Security, Swagger, logback, Junit, Jacoco and docker.
	The system leverages MongoDB to store data.

## Note:-
## This system requires connection to MongoDB instance. Update below properties in application.properties file according to setup.
	spring.data.mongodb.database=book_store
	spring.data.mongodb.host=localhost
	spring.data.mongodb.port
	
## Call Authentication API and use jwt token received in response as "Authorization" header for subsequent calls, example:-
	Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMjAyMSIsImV4cCI6MTYzMjczMjc0OCwiaWF0IjoxNjMyNzI1NTQ4fQ.D_rM_PJNs8A70Cl0-GO_eFn5LdqbsXUeiqSg_zf-lOQ


## 1. Authentication API:-
POST http://localhost:8080/authenticate
## Request:-
	{
		"username": "user2021",
		"password": "password"
	}
## Response:-
	{
		"jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMjAyMSIsImV4cCI6MTYzMjczMjc0OCwiaWF0IjoxNjMyNzI1NTQ4fQ.D_rM_PJNs8A70Cl0-GO_eFn5LdqbsXUeiqSg_zf-lOQ"
	}


## 2. Add Customer API:-
POST http://localhost:8080/api/customer/
## Request:-
	{
		"customerId": "CUST123",
		"firstName": "Rahul",
		"lastName": "Nayan",
		"mobile": "8764563456",
		"email": "rn@hotmail.com",
		"addresses": [
			{
				"addressId": "695",
				"addressLine1": "11 Park Avenue",
				"addressLine2": "Pheonix Citadel",
				"zipcode": "560000",
				"city": "Begaluru",
				"state": "Karnataka",
				"type": "Home"
			},
			{
				"addressId": "696",
				"addressLine1": "A To Z Corp",
				"addressLine2": "It Park",
				"zipcode": "560011",
				"city": "Bengaluru",
				"state": "Karnataka",
				"type": "Office"
			}
		]
	}

## Response:-
	{
		"data": {
			"customerId": "CUST123",
			"firstName": "Rahul",
			"lastName": "Nayan",
			"mobile": "8764563456",
			"email": "rn@hotmail.com",
			"addresses": [
				{
					"addressId": "695",
					"addressLine1": "11 Park Avenue",
					"addressLine2": "Pheonix Citadel",
					"zipcode": "560000",
					"city": "Begaluru",
					"state": "Karnataka",
					"type": "Home"
				},
				{
					"addressId": "696",
					"addressLine1": "A To Z Corp",
					"addressLine2": "It Park",
					"zipcode": "560011",
					"city": "Bengaluru",
					"state": "Karnataka",
					"type": "Office"
				}
			]
		},
		"status": {
			"code": "1",
			"message": "success"
		}
	}


## 3. Add Customers API:-
POST http://localhost:8080/api/customer/list
## Request:-
	{
		"data": [
			{
				"customerId": "CUST1534",
				"firstName": "Avinash",
				"lastName": "Mahale",
				"mobile": "7656543544",
				"email": "avi@gmail.com",
				"addresses": [
					{
						"addressId": "697",
						"addressLine1": "1D Kalptaru",
						"addressLine2": "GB Road",
						"zipcode": "400001",
						"city": "Mumbai",
						"state": "Maharashtra",
						"type": "Home"
					}
				]
			},
			{
				"customerId": "CUST129",
				"firstName": "Sabir",
				"lastName": "Ali",
				"mobile": "7766567455",
				"email": "sabir@yahoo.com",
				"addresses": [
					{
						"addressId": "699",
						"addressLine1": "1A",
						"addressLine2": "Amazon",
						"zipcode": "100001",
						"city": "Old Delhi",
						"state": "Delhi",
						"type": "Office"
					},
					{
						"addressId": "700",
						"addressLine1": "H No. 40, Sabir",
						"addressLine2": "Gandhi Road",
						"zipcode": "110011",
						"city": "New Delhi",
						"state": "Delhi",
						"type": "Home"
					}
				]
			}
		]
	}

## Response:-
	{
		"data": [
			{
				"customerId": "CUST1534",
				"firstName": "Avinash",
				"lastName": "Mahale",
				"mobile": "7656543544",
				"email": "avi@gmail.com",
				"addresses": [
					{
						"addressId": "697",
						"addressLine1": "1D Kalptaru",
						"addressLine2": "GB Road",
						"zipcode": "400001",
						"city": "Mumbai",
						"state": "Maharashtra",
						"type": "Home"
					}
				]
			},
			{
				"customerId": "CUST129",
				"firstName": "Sabir",
				"lastName": "Ali",
				"mobile": "7766567455",
				"email": "sabir@yahoo.com",
				"addresses": [
					{
						"addressId": "699",
						"addressLine1": "1A",
						"addressLine2": "Amazon",
						"zipcode": "100001",
						"city": "Old Delhi",
						"state": "Delhi",
						"type": "Office"
					},
					{
						"addressId": "700",
						"addressLine1": "H No. 40, Sabir",
						"addressLine2": "Gandhi Road",
						"zipcode": "110011",
						"city": "New Delhi",
						"state": "Delhi",
						"type": "Home"
					}
				]
			}
		],
		"status": {
			"code": "1",
			"message": "success"
		}
	}


## 4. Add Book API:-
POST http://localhost:8080/api/book/
## Request:-
	{
		"isbn": "9781786330895",
		"title": "Ikigai - The Japanese Secret To A Long And Happy Life",
		"edition": "1",
		"authors": ["Hector Garcia","Francesc Miralles","Heather Cleary"],
		"publication": "Penguin Books",
		"price": "250",
		"stock": 200
	}

## Response:-
	{
		"data": {
			"isbn": "9781786330895",
			"title": "Ikigai - The Japanese Secret To A Long And Happy Life",
			"edition": "1",
			"authors": [
				"Hector Garcia",
				"Francesc Miralles",
				"Heather Cleary"
			],
			"publication": "Penguin Books",
			"price": 250,
			"stock": 200
		},
		"status": {
			"code": "1",
			"message": "success"
		}
	}


## 5. Add Books API:-
POST http://localhost:8080/api/book/list/
## Request:-
	{
		"data": [
			{
				"isbn": "9780670025299",
				"title": "How to Create a Mind: The Secret of Human Thought Revealed",
				"edition": "2",
				"authors": ["Ray Kurzweil"],
				"publication": "Viking Penguin",
				"price": "2099",
				"stock": 10
			},
			{
				"isbn": "0446677450",
				"title": "Rich Dad Poor Dad",
				"edition": "5",
				"authors": ["Robert T. Kiyosaki", "Sharon Lechter"],
				"publication": "Warner Books",
				"price": "399",
				"stock": 21
			}
		]
	}

## Response:-
	{
		"data": [
			{
				"isbn": "9780670025299",
				"title": "How to Create a Mind: The Secret of Human Thought Revealed",
				"edition": "2",
				"authors": [
					"Ray Kurzweil"
				],
				"publication": "Viking Penguin",
				"price": 2099,
				"stock": 10
			},
			{
				"isbn": "0446677450",
				"title": "Rich Dad Poor Dad",
				"edition": "5",
				"authors": [
					"Robert T. Kiyosaki",
					"Sharon Lechter"
				],
				"publication": "Warner Books",
				"price": 399,
				"stock": 21
			}
		],
		"status": {
			"code": "1",
			"message": "success"
		}
	}


## 6. Update Book API:-
PATCH http://localhost:8080/api/book
## Request:-
	{
		"isbn": "9781786330895",
		"count": "261"
	}

## Response:-
	{
		"data": {
			"isbn": "9780670025299",
			"title": "How to Create a Mind: The Secret of Human Thought Revealed",
			"edition": "2",
			"authors": [
				"Ray Kurzweil"
			],
			"publication": "Viking Penguin",
			"price": 2099,
			"stock": 200
		},
		"status": {
			"code": "1",
			"message": "success"
		}
	}


## Error Response:-
	{
		"data": null,
		"status": {
			"code": "3",
			"message": "Book not found."
		}
	}


## 7. Add Order API:-
POST http://localhost:8080/api/order/
## Request:-
	{
		"orderId": "8768101",
		"customerId": "CUST1534",
		"bookIsbnList": ["0446677450", "9781786330895", "9780670025299"],
		"orderDate": "10/05/21",
		"status": "created",
		"totalItems": "3",
		"total": "2340"
	}

## Response:-
	{
		"data": {
			"orderId": "8768101",
			"customerId": "CUST1534",
			"bookIsbnList": [
				"0446677450",
				"9781786330895",
				"9780670025299"
			],
			"orderDate": "10/05/21",
			"status": "created",
			"totalItems": 3,
			"total": 2340
		},
		"status": {
			"code": "1",
			"message": "success"
		}
	}


## 8. Get Order By OrderId API:-
GET http://localhost:8080/api/order/8768100
## Response:-
	{
		"data": {
			"orderId": "8768100",
			"customerId": "CUST123",
			"bookIsbnList": [
				"0446677450"
			],
			"orderDate": "22/03/20",
			"status": "created",
			"totalItems": 1,
			"total": 399
		},
		"status": {
			"code": "1",
			"message": "success"
		}
	}


## 9. Get Orders Within Date Range API:-
GET http://localhost:8080/api/order/startDate/01-05-19/endDate/19-09-21
## Response:-
	{
		"data": [
			{
				"orderId": "8768100",
				"customerId": "CUST123",
				"bookIsbnList": [
					"0446677450"
				],
				"orderDate": "22/03/20",
				"status": "created",
				"totalItems": 1,
				"total": 399
			},
			{
				"orderId": "8768101",
				"customerId": "CUST1534",
				"bookIsbnList": [
					"0446677450",
					"9781786330895",
					"9780670025299"
				],
				"orderDate": "10/05/21",
				"status": "created",
				"totalItems": 3,
				"total": 2340
			}
		],
		"status": {
			"code": "1",
			"message": "success"
		}
	}


## 10. Get Orders by CustomerID API:-
GET http://localhost:8080/api/order/customer/CUST1534
## Response:-
	{
		"data": [
			{
				"orderId": "8768101",
				"customerId": "CUST1534",
				"bookIsbnList": [
					"0446677450",
					"9781786330895",
					"9780670025299"
				],
				"orderDate": "10/05/21",
				"status": "created",
				"totalItems": 3,
				"total": 2340
			}
		],
		"status": {
			"code": "1",
			"message": "success"
		}
	}


## 11. Get Month wise Statistics by CustomerID API:-
http://localhost:8080/api/statistics/monthly/customer/CUST1534/year/2021
## Response:-
	{
		"data": {
			"report": {
				"May": {
					"month": "May",
					"totalOrderCount": 1,
					"totalBookCount": 3,
					"totalPurchasedAmount": 2340
				}
			}
		},
		"status": {
			"code": "1",
			"message": "success"
		}
	}


## 12. Swagger API Docs:-
GET http://localhost:8080/api-docs


## 13. Swagger UI:-
GET http://localhost:8080/swagger-ui/index.html


## Steps to use Docker for running mondogb and app seperately
	Run below commands at docker cli
	docker pull mongo:latest
	docker run -d -p 27017:27017 --name bookstoremongodb:1.0 mongo:latest

## Steps to login to mongo shell
	docker exce -it bookstoremongodb bash

## Use below commands to get mongo shell
	mongo

## Use mongo commands
	show dbs
	use book_store
	db.book.find().pretty()
	db.customer.find().pretty()
	db.order.find().pretty()

##Switch to directroy of Dockerfile and execute below commands
	docker build -t readingisgood:1.0 .
	docker run -p 8080:8080 --name readingisgood --link bookstoremongodb:mongo - d readingisgood:1.0

##Check app logs
	docker logs readingisgood


