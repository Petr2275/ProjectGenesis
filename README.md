## 🧬 Databáze

Soubor [`database/genesis-schema.sql`](./database/genesis-schema.sql) obsahuje SQL příkazy pro vytvoření tabulek.

📬 Postman kolekce k testování API najdeš ve složce [postman/](./postman)

🛠 REST API
Dostupné na: http://localhost:8080/api/v1/users

🔄 Přehled endpointů
Metoda	URL	Popis
POST	/api/v1/users	Vytvoření uživatele
GET	/api/v1/users	Získání všech uživatelů
GET	/api/v1/users/{id}	Získání jednoho uživatele
PUT	/api/v1/users	Úprava uživatele
DELETE	/api/v1/users/{id}	Smazání uživatele

📂 Export
SQL dump je uložen v:
/database/backup.sql

Export proveden pomocí:
mariadb-dump -u root -p -P 3306 todos > backup.sql

♻️ Obnovení databáze
mysql -u root -p -P 3306 < backup.sql

✨ Autor
Petr2275 – GitHub: github.com/Petr2275

# 🧬 Project Genesis

RESTová aplikace pro správu uživatelů pomocí Spring Boot + MariaDB.

## 🚀 Spuštění aplikace

### 1. Požadavky

- Java 17+
- Maven
- MariaDB (port 3306, databáze `todos`)

### 2. Lokální spuštění

```bash
./mvnw spring-boot:run

