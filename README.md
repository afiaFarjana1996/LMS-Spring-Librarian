# LMS-Spring-Librarian
This project has the functionalities of a Librarian of LMS project.

# Technologies-Used
This is a Java8 project built in Spring-boot framework
embedded servlet-container- apache tomcat
build tool- maven
relational database-mysql
API testing tool-postman

# Package-Description
1.Entity package: This package has all the classes of plain old java objects. Each class has private fields and getter setter methods for setting value of the fields and getter method for getting that value.

2.Dao package: This package has all the classes of data access objects. Singleton instance of database connection has been made to avoid time-cost of openning database connection each time. All the data access methods are defined in these classes.

3.Service package: This package has all the classes for service layer classes where we define the business logic. Methods in these classes uses methods from dao classes.

4.Controller package: This package has all the controller classes. These classes contain all the definition for the api endpoints.
