# MASTER PROMPT — Rainwater Harvesting & Water Conservation Monitor (Full-Stack Website)

Copy everything below this line into your AI coding tool (Claude Code, Cursor, Copilot, etc.). For best results, tell it to build **one phase at a time** (see Section 12).

---

## 1. ROLE & GOAL

You are a senior full-stack engineer. Build a complete, runnable, well-structured full-stack web application called **"Rainwater Harvesting & Water Conservation Monitor"** for a college community project (Don Bosco Institute of Technology, Dept. of IT). Write real, working code for every file — no placeholders, no "TODO", no pseudo-code. After each phase, tell me exactly how to run and test it.

## 2. PROJECT CONTEXT (why this exists)

- **Users:** Small and marginal farmers in Maharashtra, many with low literacy and low bandwidth, often on basic smartphones.
- **Problem:** Unpredictable rainfall and water scarcity. Our own farmer survey found 67% of respondents have no rainwater-harvesting structure. Top barriers: climate uncertainty, lack of technical knowledge, high implementation cost, and poor maintenance. Most farmers plan water use manually; there is no simple digital tool.
- **Solution:** A farmer-first, equipment-free web platform to record rainfall and land data, estimate harvestable water, track tank storage and usage, assess land fertility, and get simple, practical conservation recommendations.
- **SDG alignment:** SDG 6 (Clean Water & Sanitation), SDG 13 (Climate Action), SDG 15 (Life on Land).
- **Geographic focus (important):** The product is built **for Indian farmers, specifically Maharashtra**. All defaults, dropdowns, examples, crops, seasons, units, currency (₹), rainfall patterns and recommendations must be Maharashtra-relevant:
  - Maharashtra's 36 districts, grouped by agro-regions: Konkan, Western Maharashtra (Pune, Satara, Sangli, Kolhapur, Solapur), North Maharashtra/Khandesh (Nashik, Dhule, Jalgaon, Nandurbar, Ahmednagar), Marathwada (Chhatrapati Sambhajinagar, Jalna, Beed, Latur, Dharashiv, Nanded, Parbhani, Hingoli), and Vidarbha (Nagpur, Amravati, Akola, Yavatmal, Wardha, Chandrapur, Gadchiroli, Bhandara, Gondia, Washim, Buldhana).
  - Seasons: **Kharif** (Jun–Oct, monsoon), **Rabi** (Nov–Mar), **Zaid/Summer** (Apr–May). Rainfall is dominated by the southwest monsoon (Jun–Sep); Marathwada and parts of Vidarbha/Madhya Maharashtra are drought-prone, Konkan is high-rainfall.
  - Typical crops dropdown: soybean, cotton, sugarcane, jowar, bajra, tur (pigeon pea), moong, udid, wheat, gram (harvara), onion, grapes, pomegranate, banana, orange, turmeric, rice, groundnut, maize, vegetables.
  - Land units: **guntha, acre, hectare** (1 acre = 40 guntha = 4046.86 m²; 1 hectare = 10,000 m²). Local terms (e.g., "shet-tale" for farm pond, "vihir" for open well, "bore-well") shown in Marathi labels.
  - Show an informational "Government schemes" card (e.g., Magel Tyala Shettale farm-pond scheme, Jalyukt Shivar, PM Krishi Sinchayee Yojana) as plain educational links/text with a "verify current eligibility with your local Krushi office" note — do not invent eligibility rules or amounts.
- **Community & evidence context:** Maharashtra's drought-prone, rain-fed regions (Vidarbha, Marathwada, parts of Madhya Maharashtra) have a strong tradition of community-led water conservation. The site must acknowledge **Paani Foundation** (Satyamev Jayate Water Cup: village-level watershed work — contour trenches, earthen dams, soak pits, recharge structures, nala bunds, and organised *shramdaan* before the monsoon) and **NAAM Foundation** (restoration of existing water bodies: desilting tanks and nalas, rejuvenating small rivers) as real-world examples that this tool complements — see Section 5.5 #6. Evidence on the site must come only from the research findings listed in Section 5.5 #7; do not invent statistics.
- **Design principle:** Every feature must map back to a survey-identified barrier (knowledge gap → recommendations and learning module; high cost → cost-savings calculator; maintenance → reminders and checklist; climate uncertainty → rainfall history and harvest planning).

## 3. TECH STACK (strict — do not substitute)

| Layer | Technology |
|---|---|
| Frontend | Plain **HTML5, CSS3, vanilla JavaScript (ES6+)**. No React/Vue/Angular/jQuery/Bootstrap. Charts via **Chart.js** (CDN). |
| Backend | **Java 17+, Spring Boot 3.x**, Spring Web (REST API), Spring Data JPA (Hibernate), Spring Security, Bean Validation |
| Build tool | **Maven** |
| Database | **MySQL 8** (schema `rwh_monitor`). Use `spring.jpa.hibernate.ddl-auto=update` for development |
| Auth | Spring Security with BCrypt password hashing and **JWT** (stateless), role-based access |
| Communication | Frontend calls the REST API with `fetch()` using JSON. Backend serves the static frontend from `src/main/resources/static` so the whole app runs on one port (8080) |
| Exports | OpenCSV (CSV) and OpenPDF or iText (PDF) |
| Utilities | Lombok, ModelMapper or MapStruct (optional), OpenCSV for dataset loading |
| AI / ML | **Weka (Java library)** running inside Spring Boot — Random Forest / Linear Regression models trained from the Kaggle CSVs. Everything stays in Java; no separate Python service. |
| Maps | **Leaflet.js** (CDN) with OpenStreetMap tiles for groundwater/well map pins (vanilla JS usage only) |

## 4. USER ROLES

1. **FARMER** — registers, manages own profile, land, rainfall, tank, and reports.
2. **FIELD_WORKER** — "Assisted Entry" mode: enters data on behalf of a farmer (selects the farmer by phone or ID). Sees only the farmers assigned to them or those they registered.
3. **ADMIN** — aggregate dashboard across all farmers (village/taluka-wise), manages learning content, rainfall dataset, and user accounts.

## 5. FEATURES TO BUILD

### 5.1 Core (MVP)

1. **Registration & login** — name, phone number (unique, used as username), password, village, taluka, district (Maharashtra districts as a dropdown), preferred language. JWT issued on login.
2. **Farmer profile & land data** — land area (acre/hectare/guntha with unit converter, stored in m²), irrigation source (dropdown: Rain-fed / Borewell / Well / Canal / River / Other), existing RWH structure (dropdown: None / Farm pond / Storage tank / Check dam / Roof harvesting / Other), main crop (dropdown), soil type (dropdown). **Use dropdowns and icons, not free text.** Full CRUD.
3. **Rainfall & harvest calculator**
   - Inputs: catchment area (m²), rainfall (mm), surface type (dropdown → runoff coefficient).
   - Formula: `Harvestable Water (litres) = Catchment Area (m²) × Rainfall (mm) × Runoff Coefficient`  (1 mm of rain on 1 m² = 1 litre).
   - Runoff coefficients: Roof/concrete 0.85, Compacted soil 0.60, Farmland/cropped 0.40, Grassland 0.30, Forest/vegetated 0.20.
   - Show the result in litres, m³, and "number of 1000-L tanks filled". Allow "use historical average rainfall for my district/month" (from the Kaggle dataset) as a one-click input.
   - Save calculations as history.
4. **Rainfall records** — farmer logs daily/monthly rainfall (date, mm, optional note). Line/bar chart of the last 12 months compared with the district's historical average.
5. **Water storage (tank) tracker**
   - Create tanks/ponds (name, type, capacity in litres).
   - Log **inflow** (from harvesting/rain) and **outflow** (irrigation, drinking, livestock, other) transactions. Current level = sum(inflow) − sum(outflow), never below 0 or above capacity.
   - Visual **gauge** (CSS/SVG animated water level in % with colour: green >50%, amber 20–50%, red <20%).
   - Usage breakdown pie/doughnut chart.
6. **Land fertility report (rule-based)**
   - Inputs: pH, Nitrogen, Phosphorus, Potassium, Organic Carbon (%), EC, optional others (dropdown-assisted ranges "Low/Medium/High" for farmers who do not have a soil test).
   - Scoring: each parameter scored 0–100 against agronomic thresholds, weighted into an overall **Fertility Score (0–100)** with a band (Poor / Moderate / Good / Excellent). Example thresholds: pH ideal 6.5–7.5; N (kg/ha) low <280, medium 280–560, high >560; P low <11, medium 11–25, high >25; K low <110, medium 110–280, high >280; OC low <0.5, medium 0.5–0.75, high >0.75. Keep thresholds in a config class/table so they are easy to change.
   - Output: score, per-parameter status, and plain-language "how to improve" tips (e.g., compost, green manure, gypsum for high pH, lime for low pH).
   - Structure the scoring service behind an interface so an ML classifier (Weka or a Python service) can replace it later.
7. **Recommendation engine** — a rule-based service combining fertility score + irrigation source + land area + rainfall + existing structure to suggest:
   - Best structure type (farm pond, storage tank, roof harvesting, contour bunding, percolation pit) with a one-line reason,
   - Suggested capacity (litres),
   - 3–5 prioritised conservation tips (mulching, drip irrigation, crop-timing, etc.),
   - Each recommendation has a **low-cost manual** option first and an **optional mechanised** option second.
   Example rule: no structure + rain-fed + small land (< 2 acres) → low-cost farm pond/storage tank.

### 5.2 Second-tier features (build after the MVP works)

8. **Cost-savings calculator** — harvested water value vs. cost of purchased/pumped water (inputs: tanker price per 1000 L or diesel/electricity cost per hour of pumping; output: monthly/yearly savings and structure payback period).
9. **Alerts & maintenance reminders** — low tank level (<20%), heavy-rain notice (placeholder hook for a future weather API), periodic maintenance checklist (clean inlet/filter, check for cracks, desilt pond) with due dates and a "mark done" button. Show as a notification bell and an alerts page.
10. **Learning / training module** — short, illustrated guides (stored in DB, managed by admin), filterable by situation (e.g., "no structure + rain-fed + small land"). Include a "Recommended for you" row based on the farmer's profile. Seed it with at least 8 guides (farm pond basics, roof harvesting, filter design, tank maintenance, mulching, drip irrigation, soil testing, composting).
11. **Monthly/yearly reports** — harvest vs usage trends with Chart.js; **export to PDF and CSV**.
12. **SDG impact page** — static page mapping each feature to SDG 6, 13, 15 with a short explanation and the survey statistics.
13. **Admin dashboard** — total farmers, total harvested litres, adoption rate (% with a structure), taluka-wise and village-wise rainfall and adoption charts, fertility score distribution, filters by district/taluka/date range, and a table of farmers with search and pagination.

### 5.3 AI Assist (powered by Kaggle datasets)

Add an **"AI Assist"** module, honestly scoped as *"AI-assisted prediction"* (classical ML trained on public Kaggle data — **not** a custom deep-learning model). Every AI output must show a plain-language explanation, a confidence indicator, and a short disclaimer ("Advisory only — confirm with your local Krushi Sahayak / agriculture office").

**Datasets (download from Kaggle; the app must also work with the small bundled samples):**
1. **Rainfall in India** (`rajanand/rainfall-in-india`) — subdivision-wise monthly/annual rainfall 1901–2017. Use rows for Maharashtra subdivisions: *Konkan & Goa, Madhya Maharashtra, Marathwada, Vidarbha*.
2. **Soil Fertility Dataset** (`rahuljaiswalonkaggle/soil-fertility-dataset`) — pH, N, P, K and other soil indicators with fertility labels.
3. **Crop Recommendation Dataset** (`atharvaingle/crop-recommendation-dataset`) — N, P, K, temperature, humidity, pH, rainfall → suggested crop. Filter/prioritise crops grown in Maharashtra when presenting results.
4. **Rainwater Harvesting Feasibility Dataset** (`kartiksaini18/groundwater-feasibilty-and-structure-dataset`, < 5 MB) — climatic, soil type, groundwater depth and structural feasibility parameters. Trains the **structure-type recommender** (recharge pit / trench / shaft / tank sizing) — see 5.5 #1.
5. **Rainfall in India 1901–2015, district-wise** (`ashishjstar/rainfall-in-india-1901-2015`, or `rajanand/rainfall-in-india`) — use the file `district wise rainfall normal.csv` (monthly, seasonal and annual normals in mm per district) for the **district auto-fill**, alongside the subdivision-wise series used for forecasting. Filter to Maharashtra districts.
6. **India Groundwater Climate Time-Series 1994–2025** (`kushvinthmadhavan/india-groundwater-climate-time-series-19942025`, ~15–30 MB; India WRIS data: state/district, lat/long, well depth, aquifer type, rainfall, groundwater level). **Filter to State == Maharashtra before seeding** so the DB holds only a few thousand rows. Powers the groundwater dashboard and map pins — see 5.5 #3.
7. **Weather Data Indian Cities 1990–2022** (`vanvalkenberg/historicalweatherdataforindiancities`, ~10 MB; daily `prcp` in mm and min/max temperature per city, including Mumbai and other hubs). Powers the **daily tank simulation** — see 5.5 #4. Seed only Maharashtra cities that exist in the file (e.g., Mumbai, Pune, Nagpur, Nashik, Aurangabad/Chhatrapati Sambhajinagar if present) and tell me which were found.
8. **Indian Water Quality Data 2021–2023** (`rishabchitloor/indian-water-quality-data-2021-2023`, < 5 MB; pH, dissolved oxygen, conductivity, BOD, nitrate, coliform). Used for the **water-quality tracker** — see 5.5 #5.

**Important — dataset handling:** I could not verify these exact Kaggle slugs, file names or column names. Before writing any loader, **ask me to paste the first 5 rows and header of each CSV (or inspect them if available)**, then write the loaders to match the real columns. Make every loader tolerant (trim headers, case-insensitive column lookup, skip null rows) and keep the bundled sample CSVs so the app still runs without the downloads.

**AI features to build:**
1. **Rainfall forecast (seasonal estimate)** — For the farmer's agro-region (mapped from their district) and a chosen month/season, predict expected rainfall (mm) using Weka regression (Linear Regression or Random Forest) trained on the historical monthly series, with features such as subdivision, month, and recent-years average. Show: predicted mm, a ±range based on historical standard deviation, a "below / near / above normal" label versus the 30-year mean, and a bar/line chart of history vs. prediction. Feed this prediction into the harvest calculator as a "use AI-predicted rainfall" button to estimate **expected harvestable water** for the coming season.
2. **Fertility classification** — Train a Weka Random Forest (or J48) on the Soil Fertility dataset to classify the farmer's soil as Low / Medium / High fertility. Show it side by side with the rule-based score (5.1 #6); display both and note when they disagree. Report model accuracy on a held-out test split in the admin/"About the AI" page.
3. **Crop suggestion** — Train a Random Forest on the Crop Recommendation dataset. Inputs: N, P, K, pH, expected rainfall (mm), average temperature and humidity (taken from simple Maharashtra region/season defaults the farmer can override). Output the **top 3 crops** with probabilities, then apply a Maharashtra filter/boost so locally suited crops (e.g., soybean, cotton, tur, jowar in Kharif; gram, wheat, jowar in Rabi) rank above unsuitable ones. Show a water-need note ("high / medium / low water crop") so suggestions respect the farmer's expected harvested water.
4. **Water plan generator** — Combine (a) predicted seasonal rainfall, (b) expected harvest volume, (c) tank capacity, and (d) suggested crop's water need into a simple month-by-month **water budget table + chart** (expected inflow vs. planned usage) with a plain-language verdict: "Water likely sufficient / tight in Feb–May / deficit — consider drip irrigation or a short-duration crop". This is the "water planning" objective of the project.
5. **"Ask AI Assist" explainer** — Each AI result has a "Why this result?" panel listing the top input factors (use Random Forest feature importances) in simple Marathi/English sentences.

**AI engineering rules:**
- Put all ML code in a `com.dbit.rwh.ai` package behind interfaces (`RainfallForecaster`, `FertilityClassifier`, `CropRecommender`) so models can be swapped later (e.g., a Python/scikit-learn microservice).
- Train models **at first startup** (or via an admin-only `POST /api/admin/ai/retrain`) from the loaded DB tables/CSVs, serialize them to `models/*.model` with Weka's `SerializationHelper`, and load them on later startups. Do not retrain on every request.
- Log and expose evaluation metrics (RMSE/MAE for regression; accuracy and confusion matrix for classifiers) at `GET /api/ai/model-info`.
- Handle missing/invalid inputs gracefully; if a model is unavailable, fall back to the rule-based logic and tell the user.
- Never describe the output as guaranteed; wording must say "estimated" / "suggested".
- Store each AI result in `ai_prediction` (see schema) so the farmer can revisit it and admins can see usage.
- Keep the UI simple: one **AI Assist** page with 4 big cards (Rainfall Forecast, Soil Check, Crop Suggestion, Water Plan), each a short dropdown-based form → result card with chart. Fully bilingual (Marathi/English).

### 5.4 Accessibility (required, not optional)

- **Marathi / English toggle** on every page, persisted in `localStorage`. Implement with JSON language files (`/i18n/en.json`, `/i18n/mr.json`) and `data-i18n` attributes. Default language: **Marathi**. Provide real Marathi translations for every label, button, and message.
- **Assisted-entry mode** — a simplified step-by-step wizard (one question per screen, big buttons, icons) for field workers entering data with a farmer.
- **Mobile-first, lightweight** — works well on a 360 px-wide phone, under ~300 KB per page excluding Chart.js, no heavy frameworks.
- Large tap targets (min 48 px), high contrast, icon + text labels, simple language, number inputs with unit hints, and inline validation messages in the selected language.
- Semantic HTML, labels for inputs, keyboard navigable, `aria-live` for alerts.

### 5.5 Data-driven modules, community context & evidence

1. **Structure feasibility engine (upgrades the recommendation engine)** — A form (district, soil type, groundwater depth in metres, annual rainfall, catchment area, land slope: flat/gentle/steep, purpose: irrigation/drinking/recharge) → recommended **recharge structure type** (recharge pit, trench, shaft, farm pond, storage tank, check dam/CNB, percolation tank, contour trench, soak pit) plus a **suggested tank/pit capacity**. Train a Weka classifier (Random Forest/J48) on the Feasibility dataset, behind a `StructureRecommender` interface, with the rule-based service from 5.1 #7 as fallback and for explanations. Show the top 2 structures with a "why" panel, an approximate cost range (editable config, labelled approximate), and the low-cost-manual option first.
2. **District auto-fill** — When a farmer picks a Maharashtra district, auto-fill average monthly/seasonal/annual rainfall from the district normals and use it to estimate rooftop/farm collection potential in the calculator (same formula as 5.1 #3). The farmer can override.
3. **Groundwater & conservation dashboard** — Using the Maharashtra-filtered India WRIS data: district/taluka filter, groundwater level trend line (1994–2025), rainfall vs. groundwater level chart, aquifer-type breakdown, and a **Leaflet map** of monitoring wells with colour-coded pins (shallow / moderate / deep water level). Label the data source and period on the page. Add a "Your district" summary card on the farmer dashboard.
4. **Daily tank simulation** — Pick a city/district, catchment area, runoff coefficient, tank capacity, daily water demand (L/day, dropdown presets for household/livestock/irrigation) and a year (from the daily weather data). Simulate day by day: `inflow = area(m²) × prcp(mm) × C`, `level = min(capacity, level + inflow − demand)`, record overflow (wasted litres) and shortage days. Output: line chart of tank level over the year, monsoon vs. dry-season comparison, total harvested, total overflow, number of dry days, and a plain-language verdict plus "a tank of X litres would cut shortage days to Y" using a quick sweep of 3–5 capacities. Implement in a `TankSimulationService` with unit tests.
5. **Water-quality tracker** — Farmers/field workers log a quick test (pH, TDS or conductivity, turbidity, nitrate, coliform present yes/no — all optional, dropdown "unknown" allowed). The service gives a **Safe / Caution / Not safe** verdict for **(a) drinking/domestic** and **(b) irrigation**, with per-parameter status and plain-language advice (first-flush diverter, sand/charcoal filter, boiling/chlorination, do not drink untreated). Keep thresholds in an editable config using the Indian drinking-water standard (BIS IS 10500) and common irrigation guidelines; label them "approximate — verify against current BIS/CPCB standards". Use the water-quality dataset to show how the farmer's reading compares with typical Indian surface/ground-water values. Never present a verdict as a lab certificate.
6. **Community & partners page ("Jal Chalval")** — A respectful info page covering:
   - **Paani Foundation / Satyamev Jayate Water Cup:** community-led watershed development — villagers plan and build contour trenches, earthen dams, soak pits, recharge works and nala bunds, with *shramdaan* before the monsoon; research supports that participation is a key predictor of irrigation impact in Maharashtra watershed programmes. Link the structure types to the recommendation engine and learning guides.
   - **NAAM Foundation:** restoring existing water bodies — desilting tanks and nalas and rejuvenating small rivers — which research supports as a strategy when guided by scientific assessment and community involvement (desilting without hydrological assessment can be poorly targeted).
   - **Accuracy rule:** research supports the *strategies*; organisation-specific facts (founding details, project names, scale/figures) must be shown only as **"organisation-reported"** with a link to the organisation's own website, or omitted. **Do not invent or guess any numbers, dates, village names or beneficiaries.** Do not imply endorsement or partnership by these organisations. Add a "How this tool helps a village group" box (shared dashboard, planning structures, tracking storage).
7. **Research evidence page** — A short "What research says" page built *only* from these findings (cite author + year on each card, with an "approximate; see original paper" note):
   - Pawar et al. (2014), Renavi village, Sangli: ~20 lakh litres harvestable from rooftops met domestic demand of ~1,300 people for ~78 days; a 5,000-litre tank cost about ₹11,700.
   - Karkar & Bhalerao (2017), Western Ghats Jalkund (6,000–30,000 L): storage cost fell from ₹0.14 to ₹0.046 per litre by year 3.
   - Patode et al. (2021), Barshitakli, Akola (Vidarbha): check structures along drainage lines were followed by higher groundwater levels and use of stored water in kharif and rabi.
   - Deora & Nanore (2019), Jalna: Doha-model percolation tanks — IRR about 16%, but benefits limited to fields near streams and no community institution managing them.
   - Kumar (2016): farm ponds in five rain-fed states (incl. Akola, Maharashtra) raised farm productivity by 12–72%.
   - Garg (2022): meta-analysis of 25 semi-arid watersheds — groundwater availability up 2.6–6.9 m, crop intensification 40–100%, farmer incomes 50–200%, soil loss down ~70%.
   - Todmal (2019): in Maharashtra's semi-arid region, more than half the water from flashy rainfall events drains away, mostly in September–October, i.e., untapped harvesting potential.
   - Verma (2021): India's traditional structures (talab, johad, khadin, baoli…) have declined; IoT-based smart RWH is still nascent; only ~10–20% of rainwater is harvested.
   Use these to justify features (e.g., Jalkund cost card in the cost calculator, Sangli example in the calculator help text) and as references in the README. **Sensitivity note:** research also links drought and water stress to agrarian distress; keep that out of the farmer-facing UI (no suicide statistics, no alarming language). It may be mentioned in the README/report problem statement only, respectfully.
8. **Learning guides to seed (extends 5.1 #10):** farm pond (*shet-tale*), Jalkund, check structure/CNB, Doha-model percolation tank, contour trench, soak pit, nala bunding, rooftop harvesting with first-flush filter, desilting basics, shramdaan/community planning, plus the earlier topics. Marathi + English text for each.

## 6. DATABASE DESIGN (MySQL)

Create JPA entities with proper relationships, constraints, and indexes. Suggested tables:

- `users` (id, name, phone UNIQUE, password_hash, role, language, village, taluka, district, created_at)
- `farmer_profile` (id, user_id FK, land_area_sqm, irrigation_source, rwh_structure, main_crop, soil_type, assigned_field_worker_id FK nullable)
- `land_data` (id, farmer_id FK, plot_name, area_sqm, surface_type, notes)
- `rainfall_record` (id, farmer_id FK, record_date, rainfall_mm, note)
- `harvest_calculation` (id, farmer_id FK, catchment_area_sqm, rainfall_mm, runoff_coefficient, surface_type, harvested_litres, created_at)
- `tank` (id, farmer_id FK, name, type, capacity_litres, created_at)
- `tank_transaction` (id, tank_id FK, type [INFLOW/OUTFLOW], purpose, litres, txn_date, note)
- `fertility_report` (id, farmer_id FK, ph, nitrogen, phosphorus, potassium, organic_carbon, ec, score, band, tips_json, created_at)
- `recommendation` (id, farmer_id FK, structure_type, suggested_capacity_litres, tips_json, created_at)
- `alert` (id, farmer_id FK, type, message_key, message_params, is_read, created_at)
- `maintenance_task` (id, farmer_id FK, tank_id FK nullable, title_key, due_date, status)
- `learning_guide` (id, title_en, title_mr, body_en, body_mr, category, target_situation, image_url, created_at)
- `historical_rainfall` (id, subdivision/district, year, month, rainfall_mm) — loaded from the Kaggle "Rainfall in India" dataset
- `soil_reference` (id, parameters from Kaggle "Soil Fertility" dataset) — used to calibrate/validate thresholds and to train the fertility classifier
- `crop_reference` (id, N, P, K, temperature, humidity, ph, rainfall, label) — Kaggle "Crop Recommendation" dataset, used to train the crop recommender
- `crop_profile` (id, crop_name_en, crop_name_mr, season [KHARIF/RABI/ZAID], water_need [LOW/MEDIUM/HIGH], approx_water_mm, suited_regions) — hand-seeded Maharashtra crop reference used for filtering and water planning
- `region_climate` (id, region [Konkan/Western/Khandesh/Marathwada/Vidarbha], month, avg_temp_c, avg_humidity_pct) — seeded Maharashtra defaults for crop-model inputs
- `district_region_map` (district, region, rainfall_subdivision) — maps each Maharashtra district to its agro-region and the Kaggle rainfall subdivision
- `ai_prediction` (id, farmer_id FK, type [RAINFALL/FERTILITY/CROP/WATER_PLAN/STRUCTURE], input_json, output_json, confidence, model_version, created_at)
- `feasibility_reference` (id, columns mirroring the feasibility dataset: climate, soil_type, groundwater_depth_m, feasibility labels, structure_type) — trains the structure recommender
- `district_rainfall_normal` (id, district, jan…dec, seasonal fields, annual_mm) — Maharashtra rows of the district-wise normals
- `groundwater_reading` (id, state, district, well_id/name, latitude, longitude, well_depth_m, aquifer_type, rainfall_mm, groundwater_level_m, reading_date) — Maharashtra-only subset
- `daily_weather` (id, city, weather_date, prcp_mm, tmin_c, tmax_c) with an index on (city, weather_date)
- `water_quality_reference` (id, location/water_body, year, ph, dissolved_oxygen, conductivity, bod, nitrate, coliform)
- `water_quality_test` (id, farmer_id FK, tank_id FK nullable, test_date, ph, tds_or_conductivity, turbidity, nitrate, coliform_present, verdict_drinking, verdict_irrigation, advice_json)
- `tank_simulation` (id, farmer_id FK, city, year, catchment_area_sqm, runoff_coeff, capacity_litres, daily_demand_litres, summary_json, created_at)
- `partner_organization` (id, name, focus_en, focus_mr, website_url, reported_note) — only Paani Foundation and NAAM Foundation seeded; no invented figures
- `research_evidence` (id, author_year, region, finding_en, finding_mr, category)

Provide the full SQL script `schema.sql` **and** the JPA entities. Provide a `data.sql`/DataLoader seed with: 1 admin, 1 field worker, 5 sample farmers across different talukas, sample rainfall, tanks, transactions, and learning guides.

## 7. DATASET LOADING

- Add a `DataLoader` (`CommandLineRunner`) that, on first startup, loads `Rainfall_in_India.csv` (subdivision-wise monthly rainfall 1901–2017) and the soil fertility CSV from `src/main/resources/data/` into `historical_rainfall` / `soil_reference` using OpenCSV, only if the tables are empty.
- Include small **sample CSVs** with the correct column structure (Maharashtra subdivisions: Konkan & Goa, Madhya Maharashtra, Marathwada, Vidarbha) so the app works even before I drop in the real Kaggle files. Document exactly where to place the real files.
- Expose `GET /api/rainfall/historical?district=&month=` returning average monthly rainfall.
- Also load the **Crop Recommendation** CSV (`Crop_recommendation.csv`) into `crop_reference`, and seed `district_region_map`, `crop_profile` and `region_climate` with Maharashtra-specific data written by you. Where you are not certain of a number (e.g., crop water need in mm), use clearly labelled approximate values in a config/SQL file that is easy to edit and note "approximate — verify with agriculture department data" in the README.
- Handle Kaggle CSV quirks: the Rainfall in India file uses subdivision names and month columns (JAN…DEC, ANNUAL) with some null values; skip/impute nulls and trim whitespace.
- Add loaders for the Section 5.3 datasets #4–#8: feasibility → `feasibility_reference`; district normals → `district_rainfall_normal` (Maharashtra only); groundwater time-series → `groundwater_reading` (**filter State == Maharashtra at load time**, batch-insert in chunks of ~1000); daily city weather → `daily_weather` (only Maharashtra cities; ignore other states); water quality → `water_quality_reference`. Make each loader idempotent (skip if the table has rows), log row counts, and keep a `DATASETS.md` in the repo listing each dataset, its Kaggle link, the expected file name, and where to place it.

## 8. REST API (design it cleanly)

Use versionless `/api/...` routes, DTOs (never expose entities directly), a global `@RestControllerAdvice` exception handler returning a consistent JSON error `{timestamp, status, error, message, path}`, validation annotations, and pagination for list endpoints. Document with **springdoc-openapi (Swagger UI)** at `/swagger-ui.html`.

- **Auth:** `POST /api/auth/register`, `POST /api/auth/login`, `GET /api/auth/me`
- **Profile/Land:** `GET/PUT /api/farmers/me`, `GET/POST/PUT/DELETE /api/land`
- **Rainfall:** `GET/POST/PUT/DELETE /api/rainfall`, `GET /api/rainfall/historical`, `GET /api/rainfall/summary?year=`
- **Calculator:** `POST /api/harvest/calculate`, `GET /api/harvest/history`
- **Tanks:** `GET/POST/PUT/DELETE /api/tanks`, `POST /api/tanks/{id}/transactions`, `GET /api/tanks/{id}/transactions`, `GET /api/tanks/{id}/status`
- **Fertility:** `POST /api/fertility/assess`, `GET /api/fertility/history`
- **Recommendations:** `POST /api/recommendations/generate`, `GET /api/recommendations/latest`
- **Cost savings:** `POST /api/cost/calculate`
- **Alerts & maintenance:** `GET /api/alerts`, `PATCH /api/alerts/{id}/read`, `GET/POST /api/maintenance`, `PATCH /api/maintenance/{id}/done`
- **Learning:** `GET /api/learning?category=&situation=`, `GET /api/learning/{id}`, `GET /api/learning/recommended`; admin: `POST/PUT/DELETE /api/admin/learning`
- **Reports:** `GET /api/reports/monthly?year=&month=`, `GET /api/reports/yearly?year=`, `GET /api/reports/export/pdf`, `GET /api/reports/export/csv`
- **Field worker (assisted entry):** `GET /api/assist/farmers?phone=`, `POST /api/assist/farmers` (register on behalf), and the same data-entry endpoints accepting `farmerId` when the caller is FIELD_WORKER/ADMIN
- **Admin:** `GET /api/admin/dashboard/summary`, `GET /api/admin/dashboard/taluka-trends`, `GET /api/admin/farmers?search=&page=&size=`, `GET /api/admin/adoption`
- **AI Assist:** `POST /api/ai/rainfall-forecast`, `POST /api/ai/fertility`, `POST /api/ai/crop-suggestion`, `POST /api/ai/water-plan`, `GET /api/ai/history`, `GET /api/ai/model-info`; admin: `POST /api/admin/ai/retrain`
- **Data modules:** `POST /api/structure/recommend`, `GET /api/rainfall/district-normal?district=`, `POST /api/rainfall/collection-potential`, `GET /api/groundwater/summary?district=`, `GET /api/groundwater/trend?district=&from=&to=`, `GET /api/groundwater/wells?district=` (GeoJSON-style for the map), `POST /api/simulation/tank`, `GET /api/simulation/history`, `GET /api/simulation/cities`, `POST /api/water-quality/test`, `GET /api/water-quality/history`, `GET /api/water-quality/reference`
- **Content:** `GET /api/partners`, `GET /api/evidence?category=`
- **Static info:** `GET /api/meta/options` (returns all dropdown values: districts, crops, soil types, structures, surface types)

Security: CORS configured, JWT filter, method-level `@PreAuthorize` by role, farmers may only access their own data (enforce on the server, not just the UI).

## 9. BACKEND STRUCTURE

```
rwh-monitor/
├── pom.xml
├── src/main/java/com/dbit/rwh/
│   ├── RwhMonitorApplication.java
│   ├── config/        (SecurityConfig, CorsConfig, OpenApiConfig, DataLoader)
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── repository/
│   ├── service/       (HarvestService, FertilityService [interface + RuleBased impl],
│   │                   RecommendationService, TankService, AlertService, ReportService …)
│   ├── ai/            (RainfallForecaster, FertilityClassifier, CropRecommender interfaces,
│   │                   Weka implementations, ModelTrainer, ModelStore, WaterPlanService)
│   ├── security/      (JwtUtil, JwtAuthFilter, UserDetailsServiceImpl)
│   ├── exception/
│   └── util/
├── src/main/resources/
│   ├── application.properties
│   ├── schema.sql, data.sql
│   ├── data/          (sample CSVs)
│   └── static/        (frontend — see below)
└── src/test/java/…    (unit tests for HarvestService, FertilityService, TankService, RecommendationService)
```

`application.properties`: MySQL URL `jdbc:mysql://localhost:3306/rwh_monitor?createDatabaseIfNotExist=true`, username/password as externalised properties with sensible defaults (`root` / `root`), JWT secret and expiry, server port 8080, Swagger enabled.

## 10. FRONTEND STRUCTURE (vanilla HTML/CSS/JS)

```
static/
├── index.html            (landing: hero, how it works, SDG strip, login/register CTA)
├── login.html, register.html
├── dashboard.html        (farmer home: gauge, alerts, quick actions, rainfall chart)
├── calculator.html       (harvest calculator)
├── rainfall.html
├── tank.html             (tank tracker + gauge + transactions)
├── fertility.html
├── recommendations.html
├── cost.html
├── ai-assist.html        (AI Assist: rainfall forecast, soil check, crop suggestion, water plan)
├── structure.html        (structure feasibility finder)
├── groundwater.html      (groundwater dashboard + Leaflet map)
├── simulation.html       (daily tank simulation)
├── water-quality.html    (water-quality tracker)
├── community.html        (Paani Foundation / NAAM Foundation context)
├── evidence.html         (what research says)
├── learning.html, guide.html
├── reports.html
├── alerts.html
├── sdg.html
├── assist.html           (field-worker wizard)
├── admin.html            (admin dashboard)
├── css/  (variables.css, base.css, components.css, pages.css)
├── js/   (api.js [fetch wrapper with JWT + error handling], auth.js, i18n.js,
│          charts.js, gauge.js, utils.js, + one JS file per page)
├── i18n/ (en.json, mr.json)
└── assets/ (icons as inline SVG, images)
```

Frontend rules:
- A shared header/nav/footer injected by JS so pages stay consistent; role-aware navigation; route guards redirecting unauthenticated users to login.
- A single `api.js` wrapper: attaches the JWT, handles 401 (auto-logout), shows friendly translated error toasts, and shows loading states.
- Client-side validation mirrors server validation.
- Skeleton/loading states, empty states with helpful text, and success/error toasts.
- Works offline-tolerant for the language files (cache them).
- No inline secrets; no external dependencies except Chart.js and (optionally) Google Fonts with Noto Sans / Noto Sans Devanagari for Marathi.

## 11. UI / VISUAL DESIGN

- Theme: water + earth. Primary deep blue/teal (#0B6E99 / #0E9AA7), accent leaf-green (#4CAF50), warm sand background (#FAF7F0), amber and red for warnings. Define all colours as CSS variables; support dark mode via `prefers-color-scheme`.
- Friendly, uncluttered cards, rounded corners, soft shadows, big numeric "stat tiles" with icons, animated water-level gauge (CSS/SVG wave), subtle rain-drop hero animation on the landing page (respect `prefers-reduced-motion`).
- Typography: Noto Sans + Noto Sans Devanagari, base size 16–18 px, generous spacing.
- Bottom tab bar on mobile for farmers (Home, Calculator, Tank, Learn, More); top nav on desktop.
- Landing page must tell the story: problem (with survey stats: 67% have no structure), solution, features, SDG alignment, and CTA.

## 12. BUILD ORDER (do one phase per response; stop and wait for me to say "next")

- **Phase 1 — Foundation:** Maven project, `pom.xml`, MySQL config, all entities/repositories, `schema.sql`, seed data, security + JWT, auth endpoints, global exception handler, Swagger. Frontend: shared CSS, `api.js`, `i18n.js` + both language files, landing, login, register.
- **Phase 2 — Farmer profile & data entry:** profile, land CRUD, options endpoint, rainfall records + charts, dashboard shell, language toggle on all pages.
- **Phase 3 — Core value:** harvest calculator + history, fertility report, historical rainfall loader and comparison.
- **Phase 4 — Tank & recommendations:** tank tracker with gauge and transactions, recommendation engine, alerts.
- **Phase 5 — Admin & assisted entry:** admin dashboard with charts and filters, field-worker wizard, role guards.
- **Phase 6 — AI Assist:** load the Kaggle datasets, Weka model training + persistence, rainfall forecast, fertility classifier, crop suggestion, water-plan generator, "Why this result?" explanations, `model-info` metrics, AI Assist page.
- **Phase 7 — Data modules (Section 5.5):** structure feasibility engine, district auto-fill, groundwater dashboard + Leaflet map, daily tank simulation, water-quality tracker, Community & partners page, research evidence page. Ask me for CSV headers before writing loaders.
- **Phase 8 — Second-tier:** cost calculator, maintenance reminders, learning module with seeded guides, reports with PDF/CSV export, SDG page, government-schemes info card.
- **Phase 9 — Polish & QA:** accessibility audit, mobile polish, unit tests, README with setup steps, sample credentials, API list, screenshots checklist, and a short demo script.

## 13. QUALITY REQUIREMENTS

- Code must compile and run with `mvn spring-boot:run` after creating an empty MySQL instance. Give me the exact commands for Windows.
- Clean layering (Controller → Service → Repository), constructor injection, DTO mapping, meaningful names, short comments only where logic is non-obvious.
- All business formulas live in service classes with unit tests (e.g., 100 m² × 50 mm × 0.85 = 4250 L).
- Validate everything on the server (area > 0, rainfall 0–2000 mm, pH 0–14, tank level between 0 and capacity, etc.).
- Never trust the client for user ID — derive it from the JWT.
- Use transactions for tank operations; prevent negative stock.
- Seed accounts to document in the README: `admin / admin123`, `fieldworker / field123`, `farmer1 / farmer123` (phone-style usernames are fine).
- Provide a final README with: project overview, architecture diagram (ASCII), setup steps, dataset placement, API summary, user flows, known limitations, and future scope.

## 14. FUTURE SCOPE (do NOT build; just mention in README and add clean extension points)

IVR/WhatsApp bot for feature-phone farmers, live weather API (e.g., IMD/Open-Meteo) for real forecasts and heavy-rain alerts, IoT sensors (ultrasonic tank level, soil moisture), a Python/scikit-learn microservice or deeper models behind the existing AI interfaces, district-level models trained on larger crop-yield datasets, subsidy-scheme matching, and a village/taluka cooperative dashboard.

## 15. USER FLOW (for reference when wiring pages)

Start → Login/Register → role check →
**Farmer:** Dashboard → (Profile/Land → Rainfall log → Harvest calculator → Tank tracker → Fertility report → Recommendations → Learning → Reports) → End
**Field worker:** Find/Register farmer → Assisted-entry wizard → Save → End
**Admin:** Dashboard → filter by taluka/village → view trends → manage learning content/users → End

Reminder: every phase must respect the **Maharashtra/Indian-farmer focus** (Section 2) and keep the UI Marathi-first. Phase numbers in Section 12 supersede any earlier numbering.

Now begin with **Phase 1**. Output the complete file tree first, then every file's full code, then the exact steps to run and verify Phase 1.
