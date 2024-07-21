# Compari

## Project Description

"Compari" is a website that allows users to compare prices of various products available in multiple stores across Tunisia. The main features of the site include:
- Simplified product search
- Price comparison between different sellers
- Display of product details and customer reviews
- User registration and account management

## Technologies Used

- **Back-end**: Spring Boot
- **Front-end**: Angular
- **Database**: PostgreSQL

## Installation

Follow these steps to set up the project locally:

### Back-end (Spring Boot)

1. Clone the repository and navigate to the back-end directory:
   ```bash
   git clone https://github.com/yourusername/compari-backend.git
   cd compari-backend
   ```
2. Configure the PostgreSQL database settings in src/main/resources/application.properties:
   ```bash
   spring.datasource.url=jdbc:postgresql://localhost:5432/compari
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   ```
3.Run the application:
   ```bash
./mvnw spring-boot:run
```

### Front-end (Angular)
