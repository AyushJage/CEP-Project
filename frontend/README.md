# JalSathi — Frontend

Mobile-first, bilingual (Marathi default / English toggle) web application for the Rainwater Harvesting & Water Conservation Monitor.

## Structure
```
frontend/
├── index.html              # Landing page
├── dashboard.html          # Farmer water & land dashboard
├── login.html              # User login
├── register.html           # Farmer registration
├── calculator.html         # Harvest calculator
├── tank.html               # Water storage tracker
├── fertility.html          # Soil fertility score assessment
├── structure.html          # RWH structure feasibility guide
├── groundwater.html        # WRIS groundwater monitoring map
├── rainfall.html           # Rainfall logger & historical comparison
├── simulation.html         # Daily water balance simulation
├── recommendations.html    # AI crop & conservation advisor
├── water-quality.html      # Water quality testing (BIS 10500)
├── cost.html               # Cost-benefit & government subsidy calculator
├── learning.html           # Audio/visual conservation guides
├── alerts.html             # Drought & rainfall alerts
├── reports.html            # Seasonal summary report (PDF/CSV)
├── sdg.html                # UN Sustainable Development Goals alignment
├── community.html          # Paani & NAAM Foundation watershed initiatives
├── evidence.html           # Empirical survey findings
├── admin.html              # Administrator overview
├── assist.html             # Field worker assisted entry
├── css/                    # Modular styling (variables, base, components)
├── js/                     # API fetch client (api.js), i18n, navbar
└── i18n/                   # Language dictionaries (mr.json, en.json)
```

## How to Run Frontend Standalone

### Option 1: Double-click `run.bat`
This starts a lightweight web server on `http://localhost:3000` and automatically launches your browser.

### Option 2: VS Code Live Server
1. Open this `frontend` folder in VS Code.
2. Right-click `index.html` and click **"Open with Live Server"**.

### Option 3: Node / NPX
```bash
npx serve -l 3000 .
```

### Option 4: Python
```bash
python -m http.server 3000
```

## Backend Connection
The frontend automatically connects to the Spring Boot REST API at `http://localhost:8080/api` (CORS enabled).
When served directly by Spring Boot (e.g., when running backend via IntelliJ IDEA), it seamlessly uses relative paths (`/api`).
