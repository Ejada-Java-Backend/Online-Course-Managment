# Online Course Management

A Spring Boot-based backend system for managing online courses in an educational environment. This project provides RESTful APIs for managing students, courses, enrollments, reviews, grades, and authentication, supporting robust administration and reporting features.

## Features

- **User Authentication**: Secure login using JWT tokens.
- **Course Management**: Create, update, delete, and fetch details for courses.
- **Student Management**: Register, update, and remove students from the system.
- **Enrollment Management**: Enroll students in courses, manage enrollment status, and retrieve enrollment statistics.
- **Review System**: Let students review and rate courses.
- **Grade Tracking**: Store and analyze student grades for courses.
- **Email Notifications**: Send notifications to users via email.
- **Internationalization**: Support for multiple languages (e.g., English, Arabic).
- **Role-based Authorization**: Differentiate access levels for admin and regular users.
- **Comprehensive API**: Endpoints for filtering, searching, and aggregating data (e.g., by student, course, term).

## Technology Stack

- **Language**: Java
- **Framework**: Spring Boot
- **Security**: Spring Security, JWT
- **Data**: JPA/Hibernate, MySQL (or any relational database)
- **Testing**: JUnit
- **Email**: Spring Mail
- **Others**: Lombok, REST API

## API Endpoints

- `/api/auth`: User authentication (login).
- `/api/courses`: Manage courses (CRUD operations, filtering).
- `/api/students`: Manage students (CRUD operations).
- `/api/enrollments`: Manage enrollments, get statistics by student/course/term.
- `/api/reviews`: Manage course reviews.
- `/api/email`: Send email notifications.

## Getting Started

1. **Clone the repository**  
   ```sh
   git clone https://github.com/Ejada-Java-Backend/Online-Course-Managment.git
   ```

2. **Configure the database**  
   Update your `application.properties` with your database credentials.

3. **Run the application**  
   ```sh
   ./mvnw spring-boot:run
   ```

4. **API Usage**  
   Access endpoints via REST clients (e.g., Postman).

## Internationalization

The project supports multiple languages for messages and responses. Update your requests with the `lang` parameter to switch languages.

## License

This project is private and for educational or organizational use within Ejada-Java-Backend.

---

**For questions or contributions, please contact the repository owner or maintainer.**
