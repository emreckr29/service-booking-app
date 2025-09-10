# Service Booking App

A full-stack web application designed to allow users to easily book appointments for various services and for businesses to manage these bookings.

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

---

### Table of Contents

- [About The Project](#about-the-project)
- [Tech Stack](#tech-stack)
- [Key Features](#key-features)
- [Screenshots](#screenshots)
- [Getting Started](#getting-started)
- [Contact](#contact)

---

## 📖 About The Project

This project is a comprehensive service reservation platform built with modern web technologies. The backend leverages the power of Spring Boot to provide a secure REST API, while the frontend is a dynamic and user-friendly interface created with Angular. The application is designed to meet the needs of both customers and service providers.

---

## 🛠️ Tech Stack

The core technologies and libraries used in the development of this project:

| Backend (`/service-booking-backend`) | Frontend (`/service-booking-frontend`) | Database |
| ------------------------------------ | -------------------------------------- | -------- |
| Java 17                              | Angular 17+                            | MySQL    |
| Spring Boot 3+                       | TypeScript                             |          |
| Spring Security (JWT Authentication) | SCSS                                   |          |
| Spring Data JPA / Hibernate          | NG-ZORRO (UI Library)                  |          |
| Maven                                | Angular Universal (SSR)                |          |
| Lombok                               | RxJS                                   |          |

---

## ✨ Key Features

- **User Management:** Secure Sign-up and Sign-in functionality.
- **JWT-Based Authorization:** Role-based (user/company) access control for API endpoints.
- **Service Management:** CRUD operations for businesses to add, update, and delete services.
- **Dynamic Booking:** Allows users to create appointments for available dates and times.
- **Dashboard:** Panels for both users and businesses to view and manage their appointments.
- **Responsive Design:** A fully responsive layout compatible with mobile and desktop devices.

---

## 🖼️ Screenshots

*Add a few screenshots of your application here. For example, the home page, login screen, booking calendar, etc.*

![Home Page Screenshot](https://via.placeholder.com/800x450.png?text=Home+Page+Screenshot)
_The Home Page_

![Booking Page Screenshot](https://via.placeholder.com/800x450.png?text=Booking+Page+Screenshot)
_The Booking Page_

---

## 🚀 Getting Started

Follow these instructions to set up and run the project on your local machine.

### Prerequisites

- Java 17 (or later)
- Apache Maven
- Node.js and npm
- MySQL (or another database configured in the project)

### 1. Clone the Repository

```bash
git clone [https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git](https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git)
cd YOUR_REPOSITORY
```

### 2. Run the Backend (`/service-booking-backend`)

You need to configure sensitive information such as the database connection and JWT secret.

1.  **Create Database:** Create a new database schema for the project in MySQL.
2.  **Environment Variables:** Set up the necessary environment variables for the backend project. For a list of required variables, please see the `service-booking-backend/src/main/resources/application.properties.example` file.
3.  **Run the Application:**
    ```bash
    # Navigate to the backend directory
    cd service-booking-backend

    # Compile and run the project using Maven
    mvn spring-boot:run
    ```
    The backend will start on `http://localhost:8080` by default.

### 3. Run the Frontend (`/service-booking-frontend`)

1.  **Install Dependencies:**
    ```bash
    # Open a new terminal and navigate to the frontend directory
    cd service-booking-frontend

    # Install the required node modules
    npm install
    ```
2.  **Start the Application:**
    ```bash
    # Start the development server
    ng serve
    ```
    The application will be available at `http://localhost:4200`.

---

## ✉️ Contact

Emre [YOUR_LASTNAME] - [@linkedin-username](https://www.linkedin.com/in/linkedin-username/) - your.email@example.com

Project Link: [https://github.com/YOUR_USERNAME/YOUR_REPOSITORY](https://github.com/YOUR_USERNAME/YOUR_REPOSITORY)