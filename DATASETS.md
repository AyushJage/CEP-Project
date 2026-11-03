# Kaggle & Open Government Datasets Documentation
## Rainwater Harvesting & Water Conservation Monitor
**Department of Information Technology, Don Bosco Institute of Technology (DBIT)**

This document details all 8 datasets bundled and supported by the application, their origins, column structures, and how to update them with larger datasets from Kaggle or India WRIS.

---

### Summary of Datasets

| # | Dataset Name | Kaggle / Source Reference | Bundled Filename | Destination Directory |
|---|---|---|---|---|
| 1 | **Rainfall in India (1901–2015)** | `rajanand/rainfall-in-india` | `rainfall in india 1901-2015.csv` | `src/main/resources/data/` |
| 2 | **District-wise Rainfall Normals** | `ashishjstar/rainfall-in-india-1901-2015` | `district wise rainfall normal.csv` | `src/main/resources/data/` |
| 3 | **Weather Data Indian Cities (1990–2022)** | `vanvalkenberg/historicalweatherdataforindiancities` | `Mumbai_1990_2022_Santacruz.csv` | `src/main/resources/data/` |
| 4 | **Indian Water Quality Data** | `rishabchitloor/indian-water-quality-data-2021-2023` | `Indian_water_data.csv` | `src/main/resources/data/` |
| 5 | **Crop Recommendation Dataset** | `atharvaingle/crop-recommendation-dataset` | `Crop_recommendation.csv` | `src/main/resources/data/` |
| 6 | **Soil Fertility Classification** | `rahuljaiswalonkaggle/soil-fertility-dataset` | `soil_fertility.csv` | `src/main/resources/data/` |
| 7 | **Groundwater Feasibility & Structure** | `kartiksaini18/groundwater-feasibilty-and-structure-dataset` | `feasibility_structures.csv` | `src/main/resources/data/` |
| 8 | **India Groundwater Climate Time-Series** | India WRIS / `kushvinthmadhavan/india-groundwater-climate-time-series-19942025` | `groundwater_maharashtra.csv` | `src/main/resources/data/` |

---

### Detailed Dataset Specifications

#### 1. Rainfall in India (1901–2015)
- **Source:** India Meteorological Department (IMD) / Kaggle
- **Subdivisions used:** `KONKAN & GOA`, `MADHYA MAHARASHTRA`, `MARATHWADA`, `VIDARBHA`
- **Key Columns:** `SUBDIVISION`, `YEAR`, `JAN`, `FEB`, `MAR`, `APR`, `MAY`, `JUN`, `JUL`, `AUG`, `SEP`, `OCT`, `NOV`, `DEC`, `ANNUAL`
- **Application Feature:** Historical rainfall trend chart, subdivision seasonal forecasting, harvest potential fallback.

#### 2. District-wise Rainfall Normals
- **Source:** IMD 30-year climate normals (1951–2000)
- **Key Columns:** `STATE_UT_NAME`, `DISTRICT`, `JAN`, `FEB`, `MAR`, `APR`, `MAY`, `JUN`, `JUL`, `AUG`, `SEP`, `OCT`, `NOV`, `DEC`, `ANNUAL`
- **Application Feature:** Auto-fills 30-year monthly and annual rainfall whenever a Maharashtra farmer selects their district (Section 5.5 #2).

#### 3. Weather Data Indian Cities (Daily Precipitation)
- **Source:** NOAA / Kaggle historical city weather observations
- **Key Columns:** `time`, `tavg`, `tmin`, `tmax`, `prcp`
- **Application Feature:** 365-day tank water balance simulation (`simulation.html`). Simulates daily rainfall inflow, household/irrigation outflow, overflow spills, and dry shortage days.

#### 4. Indian Water Quality Data
- **Source:** Central Pollution Control Board (CPCB) / Kaggle
- **Key Columns:** `Monitoring Location`, `Type Water Body`, `pH - Min`, `pH - Max`, `Conductivity - Min/Max`, `BOD`, `NitrateN`, `Fecal Coliform`, `Total Coliform`
- **Application Feature:** Reference comparison for water quality tracker (`water-quality.html`). Validates farmer tests against BIS IS 10500 drinking and irrigation thresholds.

#### 5. Crop Recommendation Dataset
- **Source:** Kaggle agricultural ML benchmark
- **Key Columns:** `N`, `P`, `K`, `temperature`, `humidity`, `ph`, `rainfall`, `label`
- **Application Feature:** Weka Random Forest crop classifier prioritized for Maharashtra agro-regions (Soybean, Cotton, Jowar, Bajra, Tur, Gram, Sugarcane, Wheat).

#### 6. Soil Fertility Dataset
- **Source:** Kaggle soil testing dataset
- **Key Columns:** `N`, `P`, `K`, `pH`, `EC`, `OC`, `S`, `Zn`, `Fe`, `Cu`, `Mn`, `B`, `Output`
- **Application Feature:** Side-by-side rule-based and ML fertility band classification (`fertility.html`).

#### 7. Rainwater Harvesting Feasibility Dataset
- **Source:** Central Ground Water Board (CGWB) & empirical studies
- **Key Columns:** `district`, `soil_type`, `gw_depth_m`, `slope`, `rainfall_mm`, `catchment_sqm`, `purpose`, `structure_type`
- **Application Feature:** RWH structure recommender (Farm pond, Recharge shaft, Contour trench, Jalkund, Rooftop tank).

#### 8. India Groundwater Time-Series (Maharashtra Filtered)
- **Source:** India WRIS (Water Resources Information System)
- **Key Columns:** `state`, `district`, `well_id`, `latitude`, `longitude`, `well_depth_m`, `aquifer_type`, `rainfall_mm`, `groundwater_level_m`, `reading_date`
- **Application Feature:** Interactive Leaflet.js well monitoring map pins, water table depth trends, and aquifer classifications.

---

### How to Replace with Full-Scale Kaggle CSVs

1. Download the full CSV from Kaggle.
2. Ensure the filename matches or update the filename mapping in `DataLoader.java`.
3. Place the file inside `src/main/resources/data/`.
4. Run `mvn clean spring-boot:run`. The idempotent loaders in `DataLoader.java` will automatically detect new or updated records on startup.
