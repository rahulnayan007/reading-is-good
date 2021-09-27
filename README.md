# reading-is-good
centralized warehouse for ReadingIsGood

API Details:-

1. Authentication API:-
POST http://localhost:8080/authenticate
Request:-
{
    "username": "user2021",
    "password": "password"
}
Response:-
{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMjAyMSIsImV4cCI6MTYzMjczMjc0OCwiaWF0IjoxNjMyNzI1NTQ4fQ.D_rM_PJNs8A70Cl0-GO_eFn5LdqbsXUeiqSg_zf-lOQ"
}

Note:- Authorization header in all API calls with value received in jwt field of Authentication API response. 

2. Add Customer API:-
POST http://localhost:8080/api/customer/
Request:-
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

Response:-
{
    "data": {
        "id": "61516acf81a83b451e7a6b62",
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

3. Add Customers API:-
POST http://localhost:8080/api/customer/list
Request:-
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

Response:-
{
    "data": [
        {
            "id": "61516ccb81a83b451e7a6b63",
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
            "id": "61516ccb81a83b451e7a6b64",
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

4. Add Book API:-
POST http://localhost:8080/api/book/
Request:-
{
    "isbn": "9781786330895",
    "title": "Ikigai - The Japanese Secret To A Long And Happy Life",
    "edition": "1",
    "authors": ["Hector Garcia","Francesc Miralles","Heather Cleary"],
    "publication": "Penguin Books",
    "price": "250",
    "count": 2
}

Response:-
{
    "data": {
        "id": "61516ce981a83b451e7a6b65",
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
        "count": 2
    },
    "status": {
        "code": "1",
        "message": "success"
    }
}

5. Add Books API:-
POST http://localhost:8080/api/book/list/
Request:-
{
    "data": [
        {
            "isbn": "9780670025299",
            "title": "How to Create a Mind: The Secret of Human Thought Revealed",
            "edition": "2",
            "authors": ["Ray Kurzweil"],
            "publication": "Viking Penguin",
            "price": "2099",
            "count": 10
        },
        {
            "isbn": "0446677450",
            "title": "Rich Dad Poor Dad",
            "edition": "5",
            "authors": ["Robert T. Kiyosaki", "Sharon Lechter"],
            "publication": "Warner Books",
            "price": "399",
            "count": 21
        }
    ]
}

Response:-
{
    "data": [
        {
            "id": "61516d4381a83b451e7a6b66",
            "isbn": "9780670025299",
            "title": "How to Create a Mind: The Secret of Human Thought Revealed",
            "edition": "2",
            "authors": [
                "Ray Kurzweil"
            ],
            "publication": "Viking Penguin",
            "price": 2099,
            "count": 10
        },
        {
            "id": "61516d4381a83b451e7a6b67",
            "isbn": "0446677450",
            "title": "Rich Dad Poor Dad",
            "edition": "5",
            "authors": [
                "Robert T. Kiyosaki",
                "Sharon Lechter"
            ],
            "publication": "Warner Books",
            "price": 399,
            "count": 21
        }
    ],
    "status": {
        "code": "1",
        "message": "success"
    }
}

6. Update Book API:-
PUT http://localhost:8080/api/book
Request:-
{
    "id": "61515c73529e6c699ea7ef3b",
    "isbn": "9781786330895",
    "title": "Ikigai - The Japanese Secret To A Long And Happy Life",
    "edition": "7",
    "authors": ["Hector Garcia","Francesc Miralles"],
    "publication": "Penguin Books",
    "price": "261"
}

Response:-
{
    "data": {
        "id": "61515c73529e6c699ea7ef3b",
        "isbn": "9781786330100",
        "title": "Ikigai - The Japanese Secret To A Long And Happy Life",
        "edition": "8",
        "authors": [
            "Hector Garcia"
        ],
        "publication": "Newyork Books",
        "price": 657,
        "count": 2
    },
    "status": {
        "code": "1",
        "message": "success"
    }
}

7. Add Order API:-
POST http://localhost:8080/api/order/
Request:-
{
    "orderId": "8768TY",
    "customerId": "12345",
    "bookIsbnList": ["0446677450"],
    "orderDate": "22/03/20",
    "status": "created",
    "totalItems": "2",
    "total": "399"
}

Response:-
TBD

8. Get Order By OrderId API:-
GET http://localhost:8080/api/order/8768TY
Response:-
{
    "data": {
        "id": "6150959e998f72145712b6b9",
        "orderId": "8768TY",
        "customerId": "12345",
        "bookIsbnList": [
            "0446677450"
        ],
        "orderDate": "22/03/20",
        "status": "created",
        "totalItems": 2,
        "total": 399
    },
    "status": {
        "code": "1",
        "message": "success"
    }
}

9. Get Orders Within Date Range API:-
GET http://localhost:8080/api/order/startDate/01-05-19/endDate/19-09-21
Response:-
{
    "data": [
        {
            "id": "6150959e998f72145712b6b9",
            "orderId": "8768TY",
            "customerId": "12345",
            "bookIsbnList": [
                "0446677450"
            ],
            "orderDate": "22/03/20",
            "status": "created",
            "totalItems": 2,
            "total": 399
        }
    ],
    "status": {
        "code": "1",
        "message": "success"
    }
}

10. Get Orders by CustomerID API:-
GET http://localhost:8080/api/order/customer/12345
Response:-
{
    "data": [
        {
            "id": "6150959e998f72145712b6b9",
            "orderId": "8768TY",
            "customerId": "12345",
            "bookIsbnList": [
                "0446677450"
            ],
            "orderDate": "22/03/20",
            "status": "created",
            "totalItems": 2,
            "total": 399
        }
    ],
    "status": {
        "code": "1",
        "message": "success"
    }
}

11. Get Month wise Statistics by CustomerID API:-
GET http://localhost:8080/api/statistics/monthly/customer/12345/year/2020
Response:-
{
    "data": {
        "report": {
            "Mar": {
                "month": "Mar",
                "totalOrderCount": 1,
                "totalBookCount": 2,
                "totalPurchasedAmount": 399
            }
        }
    },
    "status": {
        "code": "1",
        "message": "success"
    }
}

12. Swagger API Docs:-
GET http://localhost:8080/api-docs

13. Swagger UI:-
GET http://localhost:8080/swagger-ui/index.html

All business APIs are designed to contain data in response along with satus which tells if the request processing was successful or a failure. 
SpringBoot, Spring security, MongoDB, swagger, Junit and Jocaco have been used to develop this Application.
