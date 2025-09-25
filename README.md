# Invoicely – Invoice Management Platform

## URL : https://quick-invoice-generator-seven.vercel.app/

## Overview
**Invoicely** is a full-stack invoice management platform built with Java, Spring Boot, MongoDB, React, and JavaScript. It allows users to create, download, and email invoices in under 10 seconds using pre-built templates, optimized for speed and usability. Authentication is handled securely with Clerk, and invoice files are stored in the cloud via Cloudinary.  

---

## Features
- **Fast Invoice Generation:** Create professional invoices using pre-built templates in under 10 seconds.  
- **PDF Rendering:** Generate downloadable and shareable PDF invoices.  
- **Cloud Storage Integration:** Store and manage invoices securely using Cloudinary.  
- **Authentication & Security:** Clerk handles user authentication, ensuring safe access to invoices.  
- **Email Integration:** Send invoices directly to clients via email from the platform.  
- **Production-Ready:** Optimized for performance, scalability, and reliability.

---

## Tech Stack
| Layer | Technology |
|-------|------------|
| Frontend | React, JavaScript |
| Backend | Java, Spring Boot |
| Database | MongoDB |
| Authentication | Clerk |
| File Storage | Cloudinary |

---

## Installation & Setup

1. **Clone the repository**
```bash
git clone https://github.com/Shrawan0701/QuickInvoiceGenerator.git
cd QuickInvoiceGenerator
```
2. **Backend Setup**
  ```bash
 cd invoice-backend
# Add your MongoDB URI and Clerk API keys in application.properties or .env
./mvnw spring-boot:run
```
## Usage

- **Sign up or log in with Clerk.**
- **Create a new invoice using pre-built templates.**
- **Download or email the invoice in PDF format.**
- **View and manage all invoices securely via the dashboard.**
