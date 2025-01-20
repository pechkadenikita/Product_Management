# 🛒 Product & Category Management System

Welcome to the **Product & Category Management System** built using **Spring Boot**, **JPA**, and **Hibernate**! 🎉 This system allows seamless management of categories and products with CRUD operations and pagination.

---

## 🚀 Features
1. **Category CRUD Operations** 📂  
   - **GET** all categories (with pagination).  
   - **POST** a new category.  
   - **GET** a category by ID.  
   - **PUT** to update a category by ID.  
   - **DELETE** a category by ID.  

2. **Product CRUD Operations** 📦  
   - **GET** all products (with pagination).  
   - **POST** a new product.  
   - **GET** a product by ID.  
   - **PUT** to update a product by ID.  
   - **DELETE** a product by ID.  

3. **Relationships** 🔗  
   - One-to-Many relationship between categories and products.  
   - Fetch product details along with its associated category.

4. **Server-Side Pagination** 🔄  
   - Efficient handling of large datasets.

---

## 🛠️ Tech Stack
- **Framework**: Spring Boot 🌟  
- **Database**: Relational Database (MySQL/PostgreSQL) 👔️  
- **ORM**: Hibernate 🛠️  
- **Testing**: Postman for API Testing ✅  
- **Annotations**: Fully annotation-based configuration (No XML) 📜

---

## 🌐 API Endpoints

### 1⃣ Category APIs 📂
- **GET** All Categories:  
  `GET /api/categories?page=3`  
- **POST** Create New Category:  
  `POST /api/categories`  
- **GET** Category by ID:  
  `GET /api/categories/{id}`  
- **PUT** Update Category by ID:  
  `PUT /api/categories/{id}`  
- **DELETE** Category by ID:  
  `DELETE /api/categories/{id}`  

### 2⃣ Product APIs 📦
- **GET** All Products:  
  `GET /api/products?page=2`  
- **POST** Create New Product:  
  `POST /api/products`  
- **GET** Product by ID:  
  `GET /api/products/{id}`  
- **PUT** Update Product by ID:  
  `PUT /api/products/{id}`  
- **DELETE** Product by ID:  
  `DELETE /api/products/{id}`  

---

## ⚙️ Setup Instructions

### Prerequisites
- Java 17+ ☕  
- Maven 3.6+ 📦  
- MySQL or PostgreSQL installed 💥  

---

## 🧪 Testing
- **Postman**: Use the provided API endpoints for CRUD operations.  
- **Sample Data**: Populate your database with test data for better understanding.  

---

## 🗰 Project Structure
- **Entities**:  
  - `Category` 📂  
  - `Product` 💳  
- **Controllers**:  
  - `CategoryController` 🚦  
  - `ProductController` 🚦  
- **Service Layer**:  
  - `CategoryServiceImpl` 🔧  
  - `ProductServiceImpl` 🔧  


