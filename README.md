# 🍽 FoodOrderSystem 

## 📜 Description
FoodOrderSystem is a modern, *Java-powered* restaurant management system designed to streamline the food ordering process. Whether you're managing a bustling restaurant or just want an efficient way to handle orders, this application has you covered! Built with *Spring Boot* and backed by *PostgreSQL, it ensures smooth operations with robust security and scalability. Plus, with **Docker support*, deployment is a breeze! 🚀

## 🎯 Features
✅ *Restaurant Management*: Manage restaurant details, food categories, menus, and user roles.  
✅ *Menu Management*: Add, update, and remove menu items with ease.  
✅ *Seamless Order Processing*: Customers can browse the menu, place orders, and track their status.  
✅ *User Authentication & Role Management: Secure authentication using **Spring Security*.  
✅ *Database Integration: All data is safely stored in **PostgreSQL*.  
✅ *Effortless Deployment: Fully containerized with **Docker & Docker Compose*.  
✅ *Swagger API Documentation*: Easily test and explore the available endpoints.  

## 🛠 Technologies Used    
- *Java 17* 🌱
- *Spring Boot* (Spring Web, Spring Data JPA, Spring Security) 🌐
- *PostgreSQL* 🗄
- *Lombok* 🔧
- *Docker & Docker Compose* 🐳
- *Gradle* ⚡

## 🚀 Installation & Setup

### Prerequisites
Before you start, make sure you have:
✔ *Java 17+* installed  
✔ *Gradle* installed  
✔ *Docker & Docker Compose* installed  
✔ *PostgreSQL* (if running locally)  

### 🔹 Clone the Repository
bash
git clone https://github.com/akhmedzade2202/FoodOrderSystem.git
cd FoodOrderSystem



### 🏃 Running the Application
#### 1️⃣ Run Locally using Gradle
bash
./gradlew bootRun


#### 2️⃣ Run with Docker Compose (Recommended)
bash
docker-compose up -d


## 🐳 Docker Support
This project is **fully containerized** for easy deployment! 

### 🔹 Build and Run with Docker
bash
docker build -t food-order-system .
docker run -p 8080:8080 food-order-system


### 🔹 Running with Docker Compose
```bash
docker-compose up -d

## 🤝 Contributing
We welcome contributions! To contribute:
1️⃣ *Fork* the repository  
2️⃣ *Create* a new branch (feature/your-feature)  
3️⃣ *Commit* your changes  
4️⃣ *Push* to your branch and *create a Pull Request*  

🌟 *Enjoy coding and happy ordering!* 🎉
