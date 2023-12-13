Mastek assignment for online bookstore using Java 11+, Spring Boot, and PostgreSQL.

Please refer following details to set up project and api endpoints usasge - 

** book-store project is build with java 11 and used IDE is spring-tool-suite : 
Port : 8080
spring.application.name=book-store
server.servlet.context-path=/book-store

** Postgresql db set up 
1. Added dependency for postgresql in pom.xml-
<dependency>
<groupId>org.postgresql</groupId>
<artifactId>postgresql</artifactId>
<scope>runtime</scope>
</dependency>

2. Downloaded pgadmin 4 desktop for postgresql desktop
3. created database with name "test"
4. Set port (5432) and datasource url as default
5. Added following configration in application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/test
spring.datasource.username=postgres
spring.datasource.password=********
spring.jpa.properties.hibernate.dialect: org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.show_sql: true
spring.jpa.properties.hibernate.hbm2ddl.auto: update


** Api document -
1. POST add-new-book
url -http://localhost:8080/book-store/add-new-book
request -
{
    "id":1,
    "title":"Harry Potter",
    "author":"J K Rolling",
    "isbn":"1234567899876",
    "publishedDate":"10/05/1997",
    "genre":"friction"
}
response -
{
    "STATUS": "200",
    "MESSAGE": "Book added successfully",
    "DATA": {
        "id": 1,
        "title": "Harry Potter",
        "author": "J K Rolling",
        "publishedDate": "1997-05-09T18:30:00.000+00:00",
        "genre": "friction",
        "isbn": "1234567899876"
    }
}

2. GET - get-book-by-id
url - http://localhost:8080/book-store/get-book/1
response - {
    "STATUS": "200",
    "MESSAGE": "Book fetched Successfully",
    "DATA": {
        "id": 1,
        "title": "Harry Potter",
        "author": "J K Rolling",
        "publishedDate": "1997-05-09T18:30:00.000+00:00",
        "genre": "friction",
        "isbn": "1234567899876"
    }
}

3. GET - get list of books
url - http://localhost:8080/book-store/get-book-list
response - {
    "STATUS": "200",
    "MESSAGE": "All books fetched Successfully",
    "DATA": [
        {
            "id": 1,
            "title": "Harry Potter",
            "author": "J K Rolling",
            "publishedDate": "1997-05-09T18:30:00.000+00:00",
            "genre": "friction",
            "isbn": "1234567899876"
        }
    ]
}

4. PUT - Update book
url - http://localhost:8080/book-store/update-book/1
request - {
    "title": "Harry Potter Part 1",
    "author": "J M Rolling"
}
response -  {
    "STATUS": "200",
    "MESSAGE": "Book updated successfully for id:1"
}
Note : you can add one or multiple params to update the book record

5. DELETE - Delete book
url -http://localhost:8080/book-store/delete-book/1
response - {
    "STATUS": "200",
    "MESSAGE": "Book deleted successfully for id:1"
}
