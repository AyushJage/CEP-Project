-- ===================================================================
-- MySQL 8 Schema for Rainwater Harvesting & Water Conservation Monitor
-- Database: rwh_monitor
-- ===================================================================

CREATE DATABASE IF NOT EXISTS rwh_monitor DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE rwh_monitor;

-- 1. Users
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    language VARCHAR(10) NOT NULL DEFAULT 'mr',
    village VARCHAR(255),
    taluka VARCHAR(255),
    district VARCHAR(255),
    created_at DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. Farmer Profile
CREATE TABLE IF NOT EXISTS farmer_profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    land_area_sqm DOUBLE,
    irrigation_source VARCHAR(100),
    rwh_structure VARCHAR(100),
    main_crop VARCHAR(100),
    soil_type VARCHAR(100),
    assigned_field_worker_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (assigned_field_worker_id) REFERENCES users(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. Land Data (Plots)
CREATE TABLE IF NOT EXISTS land_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT NOT NULL,
    plot_name VARCHAR(255) NOT NULL,
    area_sqm DOUBLE NOT NULL,
    surface_type VARCHAR(100) NOT NULL,
    village VARCHAR(255),
    taluka VARCHAR(255),
    district VARCHAR(255),
    notes TEXT,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. Rainfall Record
CREATE TABLE IF NOT EXISTS rainfall_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT NOT NULL,
    record_date DATE NOT NULL,
    rainfall_mm DOUBLE NOT NULL,
    district VARCHAR(255),
    taluka VARCHAR(255),
    note TEXT,
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_record_date (record_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. Harvest Calculation
CREATE TABLE IF NOT EXISTS harvest_calculation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT NOT NULL,
    catchment_area_sqm DOUBLE NOT NULL,
    rainfall_mm DOUBLE NOT NULL,
    runoff_coefficient DOUBLE NOT NULL,
    surface_type VARCHAR(100) NOT NULL,
    harvested_litres DOUBLE NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6. Tank
CREATE TABLE IF NOT EXISTS tank (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(100) NOT NULL,
    capacity_litres DOUBLE NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 7. Tank Transaction
CREATE TABLE IF NOT EXISTS tank_transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tank_id BIGINT NOT NULL,
    type VARCHAR(10) NOT NULL,
    purpose VARCHAR(100) NOT NULL,
    litres DOUBLE NOT NULL,
    txn_date DATE NOT NULL,
    note TEXT,
    FOREIGN KEY (tank_id) REFERENCES tank(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 8. Fertility Report
CREATE TABLE IF NOT EXISTS fertility_report (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT NOT NULL,
    ph DOUBLE,
    nitrogen DOUBLE,
    phosphorus DOUBLE,
    potassium DOUBLE,
    organic_carbon DOUBLE,
    ec DOUBLE,
    score DOUBLE NOT NULL,
    band VARCHAR(20) NOT NULL,
    tips_json TEXT,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 9. Recommendation
CREATE TABLE IF NOT EXISTS recommendation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT NOT NULL,
    structure_type VARCHAR(255) NOT NULL,
    suggested_capacity_litres DOUBLE,
    reason_summary TEXT,
    tips_json TEXT,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 10. Alert
CREATE TABLE IF NOT EXISTS alert (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT NOT NULL,
    type VARCHAR(50) NOT NULL,
    message_key VARCHAR(255) NOT NULL,
    message_params VARCHAR(255),
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 11. Maintenance Task
CREATE TABLE IF NOT EXISTS maintenance_task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT NOT NULL,
    tank_id BIGINT,
    title_key VARCHAR(255) NOT NULL,
    due_date DATE NOT NULL,
    status VARCHAR(15) NOT NULL DEFAULT 'PENDING',
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (tank_id) REFERENCES tank(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 12. Learning Guide
CREATE TABLE IF NOT EXISTS learning_guide (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title_en VARCHAR(255) NOT NULL,
    title_mr VARCHAR(255) NOT NULL,
    body_en LONGTEXT NOT NULL,
    body_mr LONGTEXT NOT NULL,
    category VARCHAR(50) NOT NULL,
    target_situation VARCHAR(255),
    image_url VARCHAR(255),
    created_at DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 13. Crop Profile (Maharashtra Reference)
CREATE TABLE IF NOT EXISTS crop_profile (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    crop_name_en VARCHAR(100) NOT NULL,
    crop_name_mr VARCHAR(100) NOT NULL,
    season VARCHAR(20) NOT NULL,
    water_need VARCHAR(15) NOT NULL,
    approx_water_mm DOUBLE,
    suited_regions VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 14. District Region Map
CREATE TABLE IF NOT EXISTS district_region_map (
    district VARCHAR(100) PRIMARY KEY,
    region VARCHAR(100) NOT NULL,
    rainfall_subdivision VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 15. District Rainfall Normal
CREATE TABLE IF NOT EXISTS district_rainfall_normal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    district VARCHAR(100) NOT NULL UNIQUE,
    jan DOUBLE, feb DOUBLE, mar DOUBLE, apr DOUBLE, may DOUBLE, jun DOUBLE,
    jul DOUBLE, aug DOUBLE, sep DOUBLE, oct DOUBLE, nov DOUBLE, `dec` DOUBLE,
    annual_mm DOUBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 16. Historical Rainfall (Subdivision Series)
CREATE TABLE IF NOT EXISTS historical_rainfall (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subdivision VARCHAR(100) NOT NULL,
    rec_year INT NOT NULL,
    jan DOUBLE, feb DOUBLE, mar DOUBLE, apr DOUBLE, may DOUBLE, jun DOUBLE,
    jul DOUBLE, aug DOUBLE, sep DOUBLE, oct DOUBLE, nov DOUBLE, `dec` DOUBLE,
    annual_mm DOUBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 17. Groundwater Reading (India WRIS)
CREATE TABLE IF NOT EXISTS groundwater_reading (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    state VARCHAR(100) NOT NULL,
    district VARCHAR(100) NOT NULL,
    well_id VARCHAR(100),
    latitude DOUBLE,
    longitude DOUBLE,
    well_depth_m DOUBLE,
    aquifer_type VARCHAR(100),
    rainfall_mm DOUBLE,
    groundwater_level_m DOUBLE NOT NULL,
    reading_date DATE,
    INDEX idx_gw_district (district),
    INDEX idx_gw_date (reading_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 18. Daily Weather
CREATE TABLE IF NOT EXISTS daily_weather (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    city VARCHAR(100) NOT NULL,
    weather_date DATE NOT NULL,
    prcp_mm DOUBLE,
    tmin_c DOUBLE,
    tmax_c DOUBLE,
    INDEX idx_weather_city_date (city, weather_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 19. Water Quality Test
CREATE TABLE IF NOT EXISTS water_quality_test (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT NOT NULL,
    tank_id BIGINT,
    test_date DATE NOT NULL,
    ph DOUBLE,
    tds_or_conductivity DOUBLE,
    turbidity DOUBLE,
    nitrate DOUBLE,
    coliform_present BOOLEAN,
    verdict_drinking VARCHAR(20) NOT NULL,
    verdict_irrigation VARCHAR(20) NOT NULL,
    advice_json TEXT,
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (tank_id) REFERENCES tank(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 20. Tank Simulation
CREATE TABLE IF NOT EXISTS tank_simulation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT NOT NULL,
    city VARCHAR(100) NOT NULL,
    sim_year INT NOT NULL,
    catchment_area_sqm DOUBLE NOT NULL,
    runoff_coeff DOUBLE NOT NULL,
    capacity_litres DOUBLE NOT NULL,
    daily_demand_litres DOUBLE NOT NULL,
    summary_json LONGTEXT,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 21. Partner Organization
CREATE TABLE IF NOT EXISTS partner_organization (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    focus_en TEXT NOT NULL,
    focus_mr TEXT NOT NULL,
    website_url VARCHAR(255) NOT NULL,
    reported_note TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 22. Research Evidence
CREATE TABLE IF NOT EXISTS research_evidence (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    author_year VARCHAR(100) NOT NULL,
    region VARCHAR(100) NOT NULL,
    finding_en TEXT NOT NULL,
    finding_mr TEXT NOT NULL,
    category VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 23. AI Prediction History
CREATE TABLE IF NOT EXISTS ai_prediction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farmer_id BIGINT,
    type VARCHAR(50) NOT NULL,
    input_json TEXT,
    output_json TEXT,
    confidence DOUBLE,
    model_version VARCHAR(50),
    created_at DATETIME NOT NULL,
    FOREIGN KEY (farmer_id) REFERENCES users(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
