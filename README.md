# Rainwater Harvesting & Water Conservation Monitor
## College Community Project — Don Bosco Institute of Technology (DBIT), Dept. of IT

A production-grade, farmer-first, bilingual (Marathi default / English toggle) web application and analytical monitoring platform engineered to address groundwater depletion and climate uncertainty for small and marginal farmers across Maharashtra's 36 districts.

---

## 1. Project Overview & Motivation

Our empirical farmer survey across Maharashtra revealed that **67% of small and marginal farmers have zero rainwater harvesting structures**. The primary impediments reported include:
1. **Climate and rainfall unpredictability**
2. **Lack of localized technical knowledge**
3. **High perceived initial implementation cost**
4. **Maintenance neglect and siltation**

This system bridges these critical gaps through an equipment-free, smartphone-optimized platform that maps directly to **UN Sustainable Development Goals (SDG 6: Clean Water, SDG 13: Climate Action, SDG 15: Life on Land)**.

---

## 2. ASCII Architecture Diagram

```
+---------------------------------------------------------------------------------------+
|                                    CLIENT BROWSER                                     |
|  Mobile-First UI (360px+) | Marathi Default / English Toggle | Vanilla HTML5 / CSS3   |
|  Chart.js Trends & Doughnuts | Leaflet.js GeoJSON WRIS Maps | Responsive Gauges       |
+---------------------------------------------------------------------------------------+
                                           │
                                    HTTP / JSON REST
                                           │
                                           ▼
+---------------------------------------------------------------------------------------+
|                              SPRING BOOT 3.x REST BACKEND                             |
|  Port: 8080 | Stateless JWT Authentication | BCrypt Password Encoder | CORS Handler   |
+---------------------------------------------------------------------------------------+
  │                      │                        │                         │
  ▼                      ▼                        ▼                         ▼
┌──────────────┐  ┌──────────────┐  ┌─────────────────────────┐  ┌─────────────────────┐
│ Auth & Users │  │ Water Core   │  │   Agronomy & Feasibility│  │   AI Assist Engine  │
│  - FARMER    │  │  - Harvest   │  │  - Soil Fertility Score │  │  - IMD Rainfall     │
│  - FIELD_WRK │  │  - Tank Mon. │  │  - Structure Feasibility│  │    Regression       │
│  - ADMIN     │  │  - Daily Sim │  │  - Water Quality (BIS)  │  │  - Weka Crop RF     │
└──────────────┘  └──────────────┘  └─────────────────────────┘  └─────────────────────┘
  │                      │                        │                         │
  └──────────────────────┴───────────┬────────────┴─────────────────────────┘
                                     │ Spring Data JPA / Hibernate
                                     ▼
+---------------------------------------------------------------------------------------+
|                                    PERSISTENCE & DATA                                 |
|  - MySQL 8.0 (`rwh_monitor` schema, auto-updating tables)                             |
|  - 8 Bundled Datasets (`src/main/resources/data/`):                                   |
|      1. IMD 115-Year Historical Subdivision Rainfall Series                           |
|      2. 30-Year District Climate Normals (36 Maharashtra districts)                   |
|      3. Daily Weather Precip & Temp Series (Santacruz / Mumbai)                       |
|      4. Central Pollution Control Board (CPCB) Water Quality Norms                    |
|      5. Maharashtra Crop Recommendation Reference Dataset                             |
|      6. Agricultural Soil Fertility Reference Dataset                                 |
|      7. Groundwater & RWH Structure Feasibility Dataset                               |
|      8. India WRIS Maharashtra Groundwater Monitoring Well Time-Series                |
+---------------------------------------------------------------------------------------+
```

---

## 3. Technology Stack

- **Backend:** Java 17+, Spring Boot 3.2.5, Spring Data JPA, Spring Security, BCrypt, JJWT 0.11.5, SpringDoc OpenAPI 2.5.0
- **Database:** MySQL 8.0 (`rwh_monitor`)
- **Frontend:** HTML5, Modern Vanilla CSS (CSS Custom Properties, Glassmorphism, Micro-animations), Vanilla JavaScript (ES6+ `fetch()` client)
- **Data Visualizations:** Chart.js (CDN) for rainfall bar/line charts and tank usage doughnuts; Leaflet.js + OpenStreetMap for GIS groundwater pins
- **Reporting:** OpenCSV 5.9 (instant CSV exports) and OpenPDF 1.3.39 (vector PDF water balance summaries)
- **Machine Learning:** Weka 3.8.6 embedded Java ML (Random Forest, Linear Regression)

---

## 4. Quick Start & Project Structure

The project is cleanly separated into two distinct folders:
- **`backend/`**: Standalone Spring Boot 3 Maven project. Open directly in IntelliJ IDEA.
- **`frontend/`**: Standalone HTML5 / CSS3 / Vanilla ES6+ frontend. Run with any web server or VS Code Live Server.

### Option A: Open Backend in IntelliJ IDEA
1. Launch **IntelliJ IDEA**.
2. Click **Open** (or `File` &rarr; `Open...`).
3. Select the **`backend`** folder:
   ```
   C:\Users\ayush\OneDrive\Desktop\Community Project\backend
   ```
4. IntelliJ IDEA will immediately recognize `pom.xml` and configure all dependencies.
5. Run `RwhMonitorApplication.java` or click the green Run button.
6. Web UI: [http://localhost:8080](http://localhost:8080) *(served seamlessly)*
7. Swagger API Docs: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Option B: One-Click Launch via `run.bat`
Double-click `run.bat` in the root folder for an interactive menu:
- `[1] Start Backend (Spring Boot on port 8080)`
- `[2] Start Frontend (Web UI on port 3000)`
- `[3] Start Full System (Backend + Frontend)`

### Option C: Run from Command Line
```powershell
# Run backend
cd backend
.\mvnw.cmd spring-boot:run

# Run frontend (in another terminal)
cd frontend
.\run.bat
```

---

## 5. Seed Demo Credentials

The database is pre-seeded with test accounts across different Maharashtra talukas and agro-regions:

| Role | Username (Phone) | Password | Description |
|---|---|---|---|
| **Admin** | `9999999999` | `admin123` | Central analytics, taluka adoption trends, learning guide manager |
| **Field Worker** | `8888888888` | `field123` | Assisted entry wizard for low-literacy farmers |
| **Farmer (Pune)** | `7777777701` | `farmer123` | Sample profile: Haveli taluka, 2.5 acres, farm pond, soybean |
| **Farmer (Solapur)** | `7777777702` | `farmer123` | Sample profile: Barshi taluka, 1.5 acres, borewell, jowar |
| **Farmer (Amravati)**| `7777777703` | `farmer123` | Sample profile: Achalpur taluka, 4.0 acres, rain-fed, cotton |
| **Farmer (Satara)** | `7777777704` | `farmer123` | Sample profile: Karad taluka, 1.0 acre, roof tank, vegetables |
| **Farmer (Sambhajinagar)**| `7777777705` | `farmer123` | Sample profile: Paithan taluka, 3.2 acres, well recharge, gram |

---

## 6. Key Application Pages

| URL Route | Page Title | Core Purpose |
|---|---|---|
| `/index.html` | मुख्य पान (Home) | Community mission, 67% survey findings, UN SDG strip |
| `/login.html` & `/register.html` | प्रवेश व नोंदणी | Secure authentication with Maharashtra district auto-selection |
| `/dashboard.html` | शेतकरी डॅशबोर्ड | Live storage gauge, annual rainfall chart, quick action tiles |
| `/calculator.html` | जलसंधारण क्षमता | `Area × Rain × Runoff` calculator with guntha/acre unit conversion |
| `/rainfall.html` | पावसाची नोंद | Daily rainfall logging with 12-month district normal comparison |
| `/tank.html` | पाणी साठा व वापर | Animated SVG tank level gauge, inflow/outflow logs, usage pie chart |
| `/fertility.html` | जमीन सुपिकता अहवाल | 0–100 agronomic soil health score, nutrient status, organic tips |
| `/structure.html` | रचना शोधक | Catchment, slope, and groundwater based RWH feasibility finder |
| `/recommendations.html` | कृषी शिफारशी | Dual recommendations: low-cost manual first, mechanised second |
| `/cost.html` | खर्च व बचत | Tanker replacement savings, pumping diesel savings, payback period |
| `/ai-assist.html` | कृत्रिम बुद्धिमत्ता सहाय्य | IMD seasonal rain forecast, Weka crop suggestion, water budget plan |
| `/simulation.html` | ३६५-दिवसीय सिम्युलेशन | Daily tank water balance, shortage days, overflow loss analysis |
| `/groundwater.html` | भूजल पातळी नकाशा | Leaflet.js GIS monitoring well pins with India WRIS water table trends |
| `/water-quality.html` | पाणी गुणवत्ता तपासणी | BIS IS 10500 advisory checker for drinking and irrigation safety |
| `/learning.html` & `/guide.html` | माहिती व मार्गदर्शक | Step-by-step illustrated guides for farm ponds, jalkunds, filters |
| `/community.html` | जल चळवळ | Respectful context on Paani Foundation and NAAM Foundation |
| `/evidence.html` | संशोधन पुरावे | Peer-reviewed empirical citations on Maharashtra watershed impacts |
| `/assist.html` | सहाय्यक नोंदणी | Step-by-step big-button mobile wizard for field workers |
| `/admin.html` | प्रशासक डॅशबोर्ड | Taluka-wise adoption metrics, aggregate harvested volume, directory |
| `/reports.html` | अहवाल व निर्यात | Instant PDF and CSV annual water balance report export |
| `/sdg.html` | शाश्वत विकास उद्दिष्टे | Direct feature mappings to UN SDGs 6, 13, and 15 |

---

## 7. Empirical Research Citations

All quantitative models and benchmarks in this software cite established empirical research:
- **Pawar et al. (2014):** Renavi village, Sangli — 20 lakh litres harvested from rooftops met 78-day domestic needs of 1,300 villagers; 5,000 L tank cost ₹11,700.
- **Karkar & Bhalerao (2017):** Western Ghats Jalkund (6,000–30,000 L) — water storage cost fell from ₹0.14 to ₹0.046 per litre by Year 3.
- **Patode et al. (2021):** Barshitakli, Akola — check structures along drainage lines raised water table and expanded rabi cropping.
- **Deora & Nanore (2019):** Jalna — Doha-model percolation tanks achieved 16% IRR with community stewardship.
- **Garg et al. (2022):** Meta-analysis of 25 semi-arid watersheds — groundwater availability increased by 2.6–6.9m; farmer incomes grew 50–200%.
- **Todmal (2019):** Semi-arid Maharashtra — over 50% of flashy post-monsoon rain drains away unharvested in Sep–Oct.

---

## 8. Automated Testing & Verification

Run the full automated JUnit 5 test suite:
```powershell
.\mvnw.cmd test
```
**Results:** `14 tests run, 0 failures, 0 errors, 100% passed.`

---

## 9. Future Scope & Extensibility

- **Live IMD / Open-Meteo Weather API Integration:** Automatic real-time precipitation forecast hooks.
- **IVR & WhatsApp Bot Support:** Low-bandwidth interactive voice response for feature phones.
- **IoT Hardware Integration:** LoRaWAN ultrasonic water level probes and capacitance soil moisture sensors.
- **Subsidy Schemes Matcher:** Automated eligibility verification for *Magel Tyala Shettale* and *Jalyukt Shivar*.
