# Java SQLite Banking System

> A lightweight, console-based banking application built in Java. Features object-oriented design and persistent data storage using SQLite and JDBC, with built-in SQL injection protection.

## 🎥 Demo
*(Note: I will be adding a quick video demonstration of the application running here soon!)*

---

## ⚙️ Features
* **Persistent Data Storage:** Uses an SQLite database to ensure account balances and data survive application restarts.
* **Automated Setup:** Automatically verifies and generates the required database tables on the first run—no manual SQL setup required.
* **Secure Database Queries:** Utilizes `PreparedStatement` to safely inject user input and protect against SQL injection.
* **Input Validation & Error Handling:** Implements `try-catch` blocks to prevent crashes from invalid user inputs (e.g., typing letters instead of numbers) and business logic to prevent negative deposits or overdrafts.
* **Algorithmic Efficiency:** Leverages SQL `WHERE` clauses (which utilize B-Trees under the hood) to fetch specific user data in $O(\log n)$ time rather than loading and searching the entire database in Java.

## 🛠️ Tech Stack
* **Language:** Java
* **Database:** SQLite
* **API:** JDBC (Java Database Connectivity)

---

## 🚀 How to Run Locally

### Prerequisites
* Java JDK installed on your machine.
* The `sqlite-jdbc` `.jar` file (already included in the `/lib` folder of this repository).

### Installation & Execution
1. Clone the repository:
```bash
   git clone [https://github.com/your-username/Java-SQLite-Banking-System.git](https://github.com/your-username/Java-SQLite-Banking-System.git)

```

2. Navigate to the project directory:
```bash
cd Java-SQLite-Banking-System

```


3. Compile and run the application (ensure you include the JDBC driver in your classpath):
```bash
# Example command depending on your IDE/terminal setup
java -cp ".;lib/sqlite-jdbc-3.53.2.0.jar" BankingSystem.src.Main

```



---

## 🧠 What I Learned

Building this project was a major step up from standard terminal scripts. It taught me how to bridge the gap between application logic and external data storage. I learned how to manage database connections properly using `try-with-resources`, how to write safe SQL queries, and why offloading search operations to the database is fundamentally better than executing linear searches in application memory.

## 👨‍💻 Author

**Chyrus Miguel Macalla**

GitHub: [@Migelitz](https://github.com/Migelitz)
