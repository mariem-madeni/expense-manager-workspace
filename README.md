# 💼 Finance SaaS CRM

> A production-ready multi-tenant Finance CRM built with Spring Boot, Angular, PostgreSQL, and Docker.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat&logo=springboot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-17+-DD0031?style=flat&logo=angular&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?style=flat&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=flat&logo=docker&logoColor=white)
![CI/CD](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-2088FF?style=flat&logo=githubactions&logoColor=white)

---

## 📌 Overview

Finance SaaS CRM is a transformation of a basic expense manager into a **full multi-tenant SaaS platform**. Each company (tenant) has its own isolated data — users, expenses, revenues, clients, and invoices — all served by a single backend.

---

## 🏗️ Architecture

```
                        ┌─────────────────────────┐
                        │     Nginx  (port 80)     │
                        │  Reverse Proxy / Router  │
                        └────────┬────────┬────────┘
                                 │        │
                    /api/*       │        │    /
              ┌──────────────────┘        └──────────────────┐
              ▼                                              ▼
   ┌─────────────────────┐                     ┌─────────────────────┐
   │   Spring Boot API   │                     │   Angular Frontend  │
   │     (port 8080)     │                     │   (static files)    │
   └──────────┬──────────┘                     └─────────────────────┘
              │
              ▼
   ┌─────────────────────┐
   │   PostgreSQL 15     │
   │     (port 5432)     │
   └─────────────────────┘
```

> 4 Docker containers orchestrated with Docker Compose behind a single Nginx reverse proxy.

---

## ✨ Features

### 🏢 Multi-Tenant SaaS
- Every resource is scoped to a **Company** (tenant)
- One backend serves all companies with full data isolation
- Subscription tiers: `FREE` / `STARTER` / `PRO`

### 🔐 Security
- JWT authentication with BCrypt password hashing
- Role-based access control (3 roles)
- Protected REST endpoints per role

### 👥 Role System
| Role | Description |
|------|-------------|
| `SUPER_ADMIN` | Platform owner — full access |
| `COMPANY_ADMIN` | Manages their company and users |
| `COMPANY_USER` | Regular employee |

### 📊 Modules
- **Expenses** — track and categorize company spending
- **Revenue** — log income per user/category
- **Clients** — CRM client management
- **Invoices** — linked to clients, with status tracking (`PENDING` / `PAID` / `CANCELLED`)
- **Categories** — classify expenses and revenues
- **Users** — scoped to company with role management

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Spring Boot (Java 21), Spring Security, JWT |
| Frontend | Angular 17+, Standalone Components |
| Database | PostgreSQL 15 (Hibernate auto DDL) |
| Reverse Proxy | Nginx |
| Containerization | Docker + Docker Compose |
| CI/CD | GitHub Actions |

---

## 🚀 Getting Started

### Prerequisites
- Docker & Docker Compose installed
- Git

### Clone & Run

```bash
git clone https://github.com/your-username/finance-saas-crm.git
cd finance-saas-crm
docker-compose up --build
```


### Environment Variables (GitHub Secrets)

| Secret | Description |
|--------|-------------|
| `JWT_SECRET` | Secret key for JWT signing |
| `DB_PASSWORD` | PostgreSQL password |
| `DB_USERNAME` | PostgreSQL username |

---

## 🗄️ Database Schema

```
company ──< users ──< depense
        │          └─< revenu
        ├──< client ──< invoice
        └──< category
```

| Table | Key Fields |
|-------|-----------|
| `company` | id, name, email, subscription, isActive |
| `users` | id, firstname, lastname, email, company_id, role_id |
| `role` | id, rolename |
| `depense` | id, titre, montant, category_id, user_id, company_id |
| `revenu` | id, titre, montant, category_id, user_id, company_id |
| `client` | id, name, email, phone, company_id |
| `invoice` | id, amount, status, client_id, company_id |
| `category` | id, descategory |
| `image` | id, imagedata (bytea), user_id |

---

## 📁 Project Structure

```
finance-saas-crm/
│
├── backend/                  # Spring Boot API
│   ├── src/main/java/
│   │   ├── config/           # Security, JWT config
│   │   ├── controllers/      # REST endpoints
│   │   ├── models/           # JPA entities
│   │   ├── repositories/     # Spring Data JPA
│   │   └── services/         # Business logic
│   └── Dockerfile
│
├── frontend/                 # Angular 17+ app
│   ├── src/app/
│   │   ├── components/       # UI components
│   │   ├── guards/           # Auth guards
│   │   ├── interceptors/     # JWT interceptor
│   │   └── services/         # API services
│   └── Dockerfile
│
├── nginx/
│   └── nginx.conf            # Reverse proxy config
│
├── docker-compose.yml
└── .github/
    └── workflows/
        └── deploy.yml        # CI/CD pipeline
```

---

## ⚙️ CI/CD Pipeline

On every push to `main`:

```
Push to main
    │
    ▼
GitHub Actions
    ├── Build Spring Boot JAR
    ├── Build Angular (dist/expense-manager/browser)
    ├── Build Docker images
    └── Deploy containers
```

> **Important fix** — ensure your frontend Dockerfile uses the correct dist path:
> ```dockerfile
> # ✅ Correct
> COPY --from=build /app/dist/expense-manager/browser /usr/share/nginx/html
> ```

---

## 🗺️ Roadmap

- [x] Multi-tenant architecture
- [x] JWT security + role system
- [x] Docker Compose (4 containers)
- [x] GitHub Actions CI/CD
- [x] Company, Client, Invoice entities
- [ ] Angular frontend (login, dashboard, CRUD pages)
- [ ] KPI dashboard with charts
- [ ] Company settings & team invite flow
- [ ] Subscription management
- [ ] Marketing landing page

---

## 🎨 Design System

| Token | Value |
|-------|-------|
| Primary | `#4F46E5` |
| Secondary | `#06B6D4` |
| Neutral | `#F8FAFC` |

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
