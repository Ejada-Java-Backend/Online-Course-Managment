# Online Course Management System

A comprehensive **Spring Boot-based RESTful API** for managing online courses, student enrollments, grades, and administrative functions. This enterprise-grade application features robust security with JWT authentication, role-based access control, and statistical analysis capabilities.

## 🚀 Key Features

### Core Functionality
- **User Management**: Complete CRUD operations for students, instructors, and administrators
- **Course Management**: Course creation, categorization, and enrollment tracking
- **Grade Management**: Grade assignment, statistical analysis (mean, median), and GPA calculations
- **Authentication & Authorization**: JWT-based security with role-based access control (@PreAuthorize)

### Advanced Features
- **Statistical Analytics**: Top student rankings, course performance metrics, enrollment statistics
- **Security**: BCrypt password encryption, JWT token management, role-based endpoints
- **Data Validation**: Input validation with custom exception handling
- **Email Integration**: Mail service configuration for notifications
- **Internationalization**: Support for multiple languages (English, Arabic)

## 🏗️ Architecture & Technologies

### Backend Stack
- **Framework**: Spring Boot 3.5.4
- **Security**: Spring Security with JWT authentication
- **Database**: MySQL 8.0 with JPA/Hibernate
- **Build Tool**: Maven
- **Java Version**: JDK 24
- **Additional**: Lombok for boilerplate reduction, validation annotations

### Key Dependencies
- Spring Boot Starter (Web, Security, Data JPA, Validation, Mail)
- MySQL Connector
- JWT (JJWT implementation)
- Lombok
- Spring Boot DevTools

## 🐳 Docker Deployment

This application is **fully containerized** using Docker and Docker Compose for easy deployment and scalability.

### Prerequisites
- Docker
- Docker Compose

### Quick Start with Docker

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Ejada-Java-Backend/Online-Course-Managment.git
   cd Online-Course-Managment
   ```

2. **Start the application with Docker Compose**:
   ```bash
   docker-compose up -d
   ```

3. **Access the application**:
   - API Base URL: `http://localhost:8080`
   - Database: MySQL on `localhost:3306`

### Docker Services

#### Application Container
- **Base Image**: OpenJDK 24 JDK Slim
- **Port**: 8080
- **Build**: Multi-stage build with Maven wrapper
- **Environment**: Configurable via environment variables

#### Database Container
- **Image**: MySQL 8.0
- **Port**: 3306
- **Data Persistence**: Named volume for data persistence
- **Health Checks**: Built-in health monitoring

### Environment Variables

The application supports environment-based configuration for containerized deployments:

```bash
# Database Configuration
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/backend
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your_password

# JWT Configuration
JWT_SECRET=your_jwt_secret_key
JWT_EXPIRATION_MS=86400000
```

## 🛠️ Development Setup

### Local Development (Non-Docker)

1. **Prerequisites**:
   - JDK 24+
   - Maven 3.6+
   - MySQL 8.0+

2. **Database Setup**:
   ```sql
   CREATE DATABASE backend;
   ```

3. **Configuration**:
   Update `src/main/resources/application.properties` with your database credentials.

4. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

### Building from Source

```bash
# Build JAR file
./mvnw clean package

# Run JAR file
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

## 📚 API Endpoints

### Authentication
- `POST /api/auth/login` - User authentication
- `POST /api/auth/register` - User registration

### Course Management
- `GET /api/courses` - List all courses
- `GET /api/courses/{id}` - Get course by ID
- `POST /api/courses` - Create new course (Admin only)
- `DELETE /api/courses/{id}` - Delete course (Admin only)

### Grade Management
- `GET /api/grades/course/{courseId}` - Get grades by course (Admin only)
- `GET /api/grades/student/{studentId}` - Get grades by student (Admin only)
- `GET /api/grades/stats/{courseId}` - Get course statistics (Admin only)

### User Management
- `GET /api/users` - List all users (Admin only)
- `GET /api/users/{id}` - Get user by ID (Admin only)
- `DELETE /api/users/{id}` - Delete user (Admin only)

## 🔒 Security Features

- **JWT Authentication**: Stateless authentication with configurable expiration
- **Role-Based Access Control**: Admin, Instructor, and Student roles
- **Password Encryption**: BCrypt hashing for secure password storage
- **Method-Level Security**: @PreAuthorize annotations for fine-grained control
- **CORS Configuration**: Configurable cross-origin resource sharing

## 📊 Statistical Features

- **Course Statistics**: Mean, median, and student count calculations
- **Top Student Rankings**: Configurable top-N student queries per course
- **GPA Calculations**: Year-based GPA computation for students
- **Enrollment Analytics**: Minimum enrollment filtering and course performance metrics

## 🧪 Testing

```bash
# Run all tests
./mvnw test

# Run with coverage
./mvnw test jacoco:report
```

## 🚀 Production Deployment

### Docker Production Setup

1. **Create production docker-compose.yml**:
   ```yaml
   version: '3.8'
   services:
     app:
       image: your-registry/course-management:latest
       environment:
         SPRING_PROFILES_ACTIVE: production
         # Add production environment variables
   ```

2. **Deploy**:
   ```bash
   docker-compose -f docker-compose.prod.yml up -d
   ```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is part of the Ejada Java Backend training program.

## 🏆 Project Highlights

This project demonstrates:
- **Enterprise Java Development** with Spring Boot ecosystem
- **Microservices-ready Architecture** with containerization
- **Security Best Practices** with JWT and role-based access
- **Database Design** with JPA/Hibernate relationships
- **DevOps Integration** with Docker containerization
- **RESTful API Design** following industry standards
- **Statistical Computing** for educational analytics