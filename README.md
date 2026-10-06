# SmartStock

A simple stock management system for Lanka Retail Distributors, built as a university group assignment (SE2030). It helps keep track of products, suppliers, purchases and stock levels in one place.

[Features](#features) · [Tech Stack](#tech-stack) · [Design Patterns](#design-patterns) · [Run the Project](#run-the-project)

---

## Features

- **Products**: add, edit, delete and search products with price, quantity, category and supplier.
- **Categories**: group products, and view the products in each category.
- **Suppliers**: manage supplier details (phone and email are validated), and view the products of each supplier.
- **Purchases**: record purchases with a discount and a status (Pending, Received, Cancelled).
- **Stock**: record stock in and stock out movements. Product quantity updates automatically.
- **Dashboard**: shows totals (including feedback) and low stock alerts.
- **Feedback**: record customer comments with a 1 to 5 rating, edit, delete and search them.
- **Users and login**: simple login to access the system.
- **Validation and search**: required fields, no future dates, and a search box on every list page.

## Tech Stack

| Part | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.1 (MVC and Data JPA) |
| Templates | Thymeleaf, HTML and CSS |
| Database | MySQL 8.4 (Docker) |
| Build tool | Maven |

## Design Patterns

All patterns are in the `com.se2030.smartstock.patterns` package.

### Strategy (`patterns/strategy`)
Each discount type is its own class (`NoDiscount`, `BulkDiscount`, `FlatDiscount`) behind the `DiscountStrategy` interface. When a purchase is created, the chosen discount is picked by `DiscountStrategies` and applied to the purchase total in `Purchase.getTotalAmount()`.

### Factory Method (`patterns/factory`)
`StockFactory` creates stock records. `StockInFactory` and `StockOutFactory` set the right type and date. When a purchase is marked **Received**, `PurchaseService` uses the factory to create a stock in record automatically.

### Observer (`patterns/observer`)
`StockSubject` notifies every `StockObserver` when a product's stock changes. This happens in `StockService` after a new stock movement.
- `LowStockObserver` tracks products with 10 or fewer items, shown on the dashboard.
- `StockLogObserver` prints each change to the console.

## Run the Project

1. Start the database:
   ```
   docker compose up -d
   ```
2. Start the app:
   ```
   ./mvnw spring-boot:run
   ```
3. Open http://localhost:8080 and log in with `admin` / `admin123`.
