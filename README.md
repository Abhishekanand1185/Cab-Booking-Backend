# 🚗 QuickCab - Ride Hailing Platform (Spring Boot)

## 🔗 Overview

*QuickCab* is a ride-hailing application built using *Spring Boot*, designed to replicate the core functionalities of platforms like Uber. It enables users to book rides, drivers to accept them, and supports multiple payment strategies, dynamic fare calculation, and email-based authentication.

The project is designed with modular service layers, strategy patterns, and JPA-based persistence, showcasing best practices for scalable backend architecture.

---

## 📂 Project Structure


com.codingshuttle.project.uber.uberApp
│
├── controllers/              # Handles REST API endpoints
├── dto/                      # Data Transfer Objects for requests/responses
├── entities/                 # JPA Entities for persistence
├── repositories/             # Interfaces for database operations
├── services/                 # Business logic interfaces
│   ├── impl/                 # Concrete implementations of services
├── strategies/               # Strategy Pattern implementations
│   ├── impl/                 # Concrete strategy classes
├── utils/                    # Utility classes (e.g., GeometryUtil)
└── UberAppApplication.java   # Main Spring Boot application runner


---

## 🔒 Key Features

### 1. *User and Driver Authentication*

* Separate login for users and service providers.
* Email verification and password reset using *Gmail SMTP*.
* Secure authentication using *JWT tokens*.

### 2. *Ride Management*

* Users can request rides.
* Drivers can accept or reject ride requests.
* Each ride request has a status lifecycle (e.g., REQUESTED → ACCEPTED → COMPLETED).

### 3. *Driver Matching Strategy*

Implements the *Strategy Design Pattern* to dynamically choose how drivers are matched:

* *Nearest Driver Strategy* – Finds the geographically closest driver.
* *Highest Rated Driver Strategy* – Prioritizes top-rated drivers for high-rated customers.

### 4. *Ride Fare Calculation Strategy*

* Uses the *Strategy Pattern* for fare calculation.
* Supports surge pricing between *6 PM to 9 PM*.
* Default fare multiplier: 10.

### 5. *Payment Strategy*

Implements flexible payment options:

* *CashPaymentStrategy* – Direct payment from user to driver.
* *WalletPaymentStrategy* – Deducts from user's in-app wallet and credits driver's wallet.
* Platform commission set at *30% (0.3)*.

### 6. *Wallet System*

* Each user and driver has a wallet entity.
* Wallet transactions are recorded via WalletTransactionService.
* Repository-backed persistence for full transaction traceability.

### 7. *Database Integration (PostgreSQL)*

* Fully integrated with PostgreSQL using *Spring Data JPA*.
* Schema auto-managed with spring.jpa.hibernate.ddl-auto=update.
* Geographic data support via org.locationtech.jts for spatial operations.

### 8. *Email Integration (Gmail SMTP)*

* Sends verification emails and password resets via Gmail.
* SMTP configuration included in application.properties.

---

## 🔧 Technical Stack

| Component                   | Technology                |
| --------------------------- | ------------------------- |
| *Backend Framework*       | Spring Boot 3.x           |
| *ORM*                     | Hibernate / JPA           |
| *Database*                | PostgreSQL                |
| *Authentication*          | Spring Security + JWT     |
| *Email*                   | Gmail SMTP                |
| *Mapping*                 | ModelMapper               |
| *Geospatial Calculations* | JTS (Java Topology Suite) |

---

## 🔊 Core Packages Explained

### *1. Services Layer*

* RideRequestService → Manage ride requests.
* WalletTransactionService → Manage wallet transactions.
* Implementations follow a clean separation of business logic.

### *2. Strategies Layer*

Implements *Strategy Design Pattern*:

* DriverMatchingStrategy → Defines how drivers are selected.
* PaymentStrategy → Defines how payment is processed.
* RideFareCalculationStrategy → Defines how fares are calculated.

Strategy Managers dynamically pick the correct strategy at runtime:

java
public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
    if(riderRating >= 4.8) return highestRatedDriverStrategy;
    else return nearestDriverStrategy;
}


### *3. Utilities*

GeometryUtil handles geographic coordinate creation using *JTS GeometryFactory*:

java
public static Point createPoint(PointDto pointDto) {
    GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
    Coordinate coordinate = new Coordinate(pointDto.getCoordinates()[0], pointDto.getCoordinates()[1]);
    return geometryFactory.createPoint(coordinate);
}


---

## 📄 Configuration Files

### *application.properties* (Local Environment Example)

properties
spring.application.name=uberApp
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.mail.username=
spring.mail.password=
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
jwt.secretKey=paiosdfqg43j51b4hjbhcafifa87dsf6hk32jhkrj9a7dfhkjhalkasdf97823


---

## 🚀 Application Flow

1. *User registers and logs in* (email verification via SMTP).
2. *User requests a ride* → stored as RideRequest.
3. *System selects strategy*:

   * If rider rating > 4.8 → use highest-rated driver.
   * Else → nearest driver.
4. *Fare calculated* via time-based strategy (surge or normal).
5. *Driver accepts ride* → ride status updated.
6. *Payment processed* → through selected PaymentStrategy.
7. *Wallets updated* → transactions logged via WalletTransactionService.

---

## 🛠 Installation and Setup

### *1. Clone Repository*

bash
git clone https://github.com/yourusername/uberApp.git
cd uberApp


### *2. Configure Database*

Edit application.properties to match your local PostgreSQL credentials.

### *3. Build and Run Application*

bash
mvn clean install
mvn spring-boot:run


### *4. Access Application*

* API runs on: http://localhost:8080
* Database: PostgreSQL

---

## 🕹 API Endpoints (Sample)

| Method | Endpoint               | Description              |
| ------ | ---------------------- | ------------------------ |
| POST   | /api/auth/register   | Register new user/driver |
| POST   | /api/auth/login      | Authenticate and get JWT |
| POST   | /api/rides/request   | Request a ride           |
| GET    | /api/rides/{id}      | Get ride details         |
| POST   | /api/payment/process | Process ride payment     |

---

## 💡 Design Patterns Used

* *Strategy Pattern* → Payment, Driver Matching, Fare Calculation.
* *Dependency Injection* → Spring-managed services and repositories.
* *Layered Architecture* → Separation of controllers, services, repositories.
* *DTO Pattern* → Encapsulation of transfer data between layers.
* *Exception Handling* → Custom exceptions for clarity (e.g., ResourceNotFoundException).

---

## 📊 Future Enhancements

* Add real-time ride tracking with WebSockets.
* Integrate Google Maps API for real routing.
* Introduce ride cancellation and refund system.
* Add driver incentives and surge zone visualization.

---

## 👤 Author

*Abhishek Anand*
Fourth year Computer Science and Engineering student passionate about software development and scalable backend systems.

---

## 🌐 References

* Spring Boot Documentation: [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
* Hibernate ORM: [https://hibernate.org/](https://hibernate.org/)
* PostgreSQL: [https://www.postgresql.org/](https://www.postgresql.org/)
* JTS Topology Suite: [https://locationtech.github.io/jts/](https://locationtech.github.io/jts/)
