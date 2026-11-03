package com.dbit.rwh.config;

import com.dbit.rwh.entity.*;
import com.dbit.rwh.repository.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataLoader.class);

    private final UserRepository userRepo;
    private final FarmerProfileRepository profileRepo;
    private final TankRepository tankRepo;
    private final TankTransactionRepository txnRepo;
    private final MaintenanceTaskRepository maintenanceRepo;
    private final LearningGuideRepository learningRepo;
    private final CropProfileRepository cropRepo;
    private final DistrictRegionMapRepository regionMapRepo;
    private final DistrictRainfallNormalRepository normalRepo;
    private final HistoricalRainfallRepository historicalRainRepo;
    private final DailyWeatherRepository weatherRepo;
    private final PartnerOrganizationRepository partnerRepo;
    private final ResearchEvidenceRepository evidenceRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        seedUsersAndTanks();
        seedDistrictRegionMap();
        seedCropProfiles();
        seedPartnerOrganizations();
        seedResearchEvidence();
        seedLearningGuides();
        loadDistrictRainfallNormals();
        loadHistoricalRainfall();
        loadDailyCityWeather();
    }

    private void seedUsersAndTanks() {
        if (userRepo.count() > 0) return;

        log.info("🌱 Seeding default users, tanks, and profiles...");

        User admin = User.builder()
                .name("DBIT Admin")
                .phone("9999999999")
                .passwordHash(passwordEncoder.encode("admin123"))
                .role(Role.ADMIN)
                .village("Kurla")
                .taluka("Kurla")
                .district("Mumbai Suburban")
                .language("en")
                .build();
        userRepo.save(admin);

        User worker = User.builder()
                .name("Suresh Patil (Krushi Sahayak)")
                .phone("8888888888")
                .passwordHash(passwordEncoder.encode("field123"))
                .role(Role.FIELD_WORKER)
                .village("Baramati")
                .taluka("Baramati")
                .district("Pune")
                .language("mr")
                .build();
        userRepo.save(worker);

        // 5 Sample farmers across different Maharashtra agro-regions
        Object[][] farmerData = {
            {"Dnyaneshwar Shinde", "7777777701", "Shirur", "Shirur", "Pune", 8093.72, "Rain-fed", "Farm pond", "soybean", "Black (Regur)"},
            {"Ananda Gaikwad", "7777777702", "Ramtek", "Ramtek", "Nagpur", 12140.58, "Borewell", "Storage tank", "cotton", "Alluvial"},
            {"Balasaheb Jadhav", "7777777703", "Ausa", "Ausa", "Latur", 6070.29, "Rain-fed", "None", "tur", "Laterite"},
            {"Sunita Deshmukh", "7777777704", "Shirol", "Shirol", "Kolhapur", 4046.86, "Well", "Farm pond", "sugarcane", "Black (Regur)"},
            {"Vilasrao Pawar", "7777777705", "Ambad", "Ambad", "Jalna", 10117.15, "Rain-fed", "None", "jowar", "Loamy"}
        };

        for (Object[] d : farmerData) {
            User f = User.builder()
                    .name((String) d[0])
                    .phone((String) d[1])
                    .passwordHash(passwordEncoder.encode("farmer123"))
                    .role(Role.FARMER)
                    .village((String) d[2])
                    .taluka((String) d[3])
                    .district((String) d[4])
                    .language("mr")
                    .build();
            userRepo.save(f);

            FarmerProfile fp = FarmerProfile.builder()
                    .user(f)
                    .landAreaSqm((Double) d[5])
                    .irrigationSource((String) d[6])
                    .rwhStructure((String) d[7])
                    .mainCrop((String) d[8])
                    .soilType((String) d[9])
                    .assignedFieldWorker(worker)
                    .build();
            profileRepo.save(fp);

            // Seed sample tank for farmers with structure
            if (!d[7].equals("None")) {
                Tank tank = Tank.builder()
                        .farmer(f)
                        .name("Main Farm Pond")
                        .type((String) d[7])
                        .capacityLitres(50000.0)
                        .build();
                tankRepo.save(tank);

                TankTransaction in1 = TankTransaction.builder()
                        .tank(tank)
                        .type(TankTransaction.TxnType.INFLOW)
                        .purpose("Rain harvest")
                        .litres(35000.0)
                        .txnDate(LocalDate.now().minusDays(15))
                        .note("Monsoon collection")
                        .build();
                txnRepo.save(in1);

                TankTransaction out1 = TankTransaction.builder()
                        .tank(tank)
                        .type(TankTransaction.TxnType.OUTFLOW)
                        .purpose("Irrigation")
                        .litres(8000.0)
                        .txnDate(LocalDate.now().minusDays(3))
                        .note("Kharif crop watering")
                        .build();
                txnRepo.save(out1);

                MaintenanceTask task = MaintenanceTask.builder()
                        .farmer(f)
                        .tank(tank)
                        .titleKey("Pre-monsoon inlet silt cleaning")
                        .dueDate(LocalDate.now().plusDays(20))
                        .status(MaintenanceTask.Status.PENDING)
                        .build();
                maintenanceRepo.save(task);
            }
        }
    }

    private void seedDistrictRegionMap() {
        if (regionMapRepo.count() > 0) return;

        log.info("🗺️ Seeding Maharashtra 36 districts to agro-region map...");

        // Konkan
        String[][] konkan = {{"Mumbai City", "Konkan", "KONKAN & GOA"}, {"Mumbai Suburban", "Konkan", "KONKAN & GOA"},
                {"Thane", "Konkan", "KONKAN & GOA"}, {"Palghar", "Konkan", "KONKAN & GOA"},
                {"Raigad", "Konkan", "KONKAN & GOA"}, {"Ratnagiri", "Konkan", "KONKAN & GOA"}, {"Sindhudurg", "Konkan", "KONKAN & GOA"}};

        // Western Maharashtra
        String[][] western = {{"Pune", "Western Maharashtra", "MADHYA MAHARASHTRA"}, {"Satara", "Western Maharashtra", "MADHYA MAHARASHTRA"},
                {"Sangli", "Western Maharashtra", "MADHYA MAHARASHTRA"}, {"Kolhapur", "Western Maharashtra", "MADHYA MAHARASHTRA"},
                {"Solapur", "Western Maharashtra", "MADHYA MAHARASHTRA"}};

        // North Maharashtra / Khandesh
        String[][] khandesh = {{"Nashik", "Khandesh / North Maharashtra", "MADHYA MAHARASHTRA"}, {"Dhule", "Khandesh / North Maharashtra", "MADHYA MAHARASHTRA"},
                {"Jalgaon", "Khandesh / North Maharashtra", "MADHYA MAHARASHTRA"}, {"Nandurbar", "Khandesh / North Maharashtra", "MADHYA MAHARASHTRA"},
                {"Ahmednagar", "Khandesh / North Maharashtra", "MADHYA MAHARASHTRA"}, {"Ahilyanagar", "Khandesh / North Maharashtra", "MADHYA MAHARASHTRA"}};

        // Marathwada
        String[][] marathwada = {{"Chhatrapati Sambhajinagar", "Marathwada", "MATATHWADA"}, {"Aurangabad", "Marathwada", "MATATHWADA"},
                {"Jalna", "Marathwada", "MATATHWADA"}, {"Beed", "Marathwada", "MATATHWADA"}, {"Latur", "Marathwada", "MATATHWADA"},
                {"Dharashiv", "Marathwada", "MATATHWADA"}, {"Osmanabad", "Marathwada", "MATATHWADA"}, {"Nanded", "Marathwada", "MATATHWADA"},
                {"Parbhani", "Marathwada", "MATATHWADA"}, {"Hingoli", "Marathwada", "MATATHWADA"}};

        // Vidarbha
        String[][] vidarbha = {{"Nagpur", "Vidarbha", "VIDARBHA"}, {"Amravati", "Vidarbha", "VIDARBHA"},
                {"Akola", "Vidarbha", "VIDARBHA"}, {"Yavatmal", "Vidarbha", "VIDARBHA"}, {"Wardha", "Vidarbha", "VIDARBHA"},
                {"Chandrapur", "Vidarbha", "VIDARBHA"}, {"Gadchiroli", "Vidarbha", "VIDARBHA"}, {"Bhandara", "Vidarbha", "VIDARBHA"},
                {"Gondia", "Vidarbha", "VIDARBHA"}, {"Washim", "Vidarbha", "VIDARBHA"}, {"Buldhana", "Vidarbha", "VIDARBHA"}};

        List<DistrictRegionMap> list = new ArrayList<>();
        for (String[][] group : List.of(konkan, western, khandesh, marathwada, vidarbha)) {
            for (String[] row : group) {
                list.add(new DistrictRegionMap(row[0], row[1], row[2]));
            }
        }
        regionMapRepo.saveAll(list);
    }

    private void seedCropProfiles() {
        if (cropRepo.count() > 0) return;

        log.info("🌾 Seeding Maharashtra crop water requirement references...");

        List<CropProfile> crops = List.of(
            CropProfile.builder().cropNameEn("Soybean").cropNameMr("सोयाबीन").season("KHARIF").waterNeed("MEDIUM").approxWaterMm(450.0).suitedRegions("Western Maharashtra, Marathwada, Vidarbha").build(),
            CropProfile.builder().cropNameEn("Cotton").cropNameMr("कापूस").season("KHARIF").waterNeed("MEDIUM").approxWaterMm(600.0).suitedRegions("Vidarbha, Marathwada, Khandesh").build(),
            CropProfile.builder().cropNameEn("Tur (Pigeon Pea)").cropNameMr("तूर").season("KHARIF").waterNeed("LOW").approxWaterMm(350.0).suitedRegions("Marathwada, Vidarbha").build(),
            CropProfile.builder().cropNameEn("Jowar (Sorghum)").cropNameMr("ज्वारी").season("KHARIF").waterNeed("LOW").approxWaterMm(300.0).suitedRegions("Western Maharashtra, Marathwada").build(),
            CropProfile.builder().cropNameEn("Wheat").cropNameMr("गहू").season("RABI").waterNeed("MEDIUM").approxWaterMm(400.0).suitedRegions("Khandesh, Western Maharashtra, Vidarbha").build(),
            CropProfile.builder().cropNameEn("Gram (Harbara)").cropNameMr("हरभरा").season("RABI").waterNeed("LOW").approxWaterMm(250.0).suitedRegions("Marathwada, Western Maharashtra").build(),
            CropProfile.builder().cropNameEn("Sugarcane").cropNameMr("ऊस").season("KHARIF").waterNeed("HIGH").approxWaterMm(1800.0).suitedRegions("Western Maharashtra (Kolhapur, Sangli, Pune)").build(),
            CropProfile.builder().cropNameEn("Pomegranate").cropNameMr("डाळिंब").season("ZAID").waterNeed("LOW").approxWaterMm(500.0).suitedRegions("Solapur, Sangli, Ahmednagar").build(),
            CropProfile.builder().cropNameEn("Onion").cropNameMr("कांदा").season("RABI").waterNeed("MEDIUM").approxWaterMm(350.0).suitedRegions("Nashik, Pune, Ahmednagar").build(),
            CropProfile.builder().cropNameEn("Rice").cropNameMr("भात / धान").season("KHARIF").waterNeed("HIGH").approxWaterMm(1200.0).suitedRegions("Konkan, Bhandara, Gondia").build()
        );
        cropRepo.saveAll(crops);
    }

    private void seedPartnerOrganizations() {
        if (partnerRepo.count() > 0) return;

        log.info("🤝 Seeding partner organizations (Paani Foundation, NAAM Foundation)...");

        partnerRepo.save(PartnerOrganization.builder()
                .name("Paani Foundation (पाणी फाउंडेशन)")
                .focusEn("Community-led watershed development and Satyamev Jayate Water Cup. Villagers plan and build contour trenches, earthen dams, soak pits, and nala bunds through shramdaan prior to the monsoon.")
                .focusMr("लोकसहभागातून जलसंधारण चळवळ आणि सत्यमेव जयते वॉटर कप स्पर्धा. श्रमदानातून सलग समपातळी चर (CCT), माती नाला बांध आणि पुनर्भरण खड्डे निर्मिती.")
                .websiteUrl("https://www.paanifoundation.in")
                .reportedNote("Organisation-reported work across 4,000+ drought-prone Maharashtra villages.")
                .build());

        partnerRepo.save(PartnerOrganization.builder()
                .name("NAAM Foundation (नाम फाउंडेशन)")
                .focusEn("Restoration of existing water bodies through scientific desilting of farm ponds, percolation tanks, and river rejuvenation in Marathwada and Vidarbha.")
                .focusMr("विदर्भ व मराठवाड्यातील शेततळी, पाझर तलाव आणि नाल्यांचे खोलीकरण व गाळ उपसा मोहीम.")
                .websiteUrl("https://naamfoundation.org")
                .reportedNote("Organisation-reported desilting of over 1,200 km of streams and village ponds.")
                .build());
    }

    private void seedResearchEvidence() {
        if (evidenceRepo.count() > 0) return;

        log.info("📚 Seeding scientific research evidence for RWH...");

        evidenceRepo.saveAll(List.of(
            ResearchEvidence.builder().authorYear("Pawar et al. (2014)").region("Renavi village, Sangli")
                    .findingEn("Rooftop rainwater harvesting of ~20 lakh litres met domestic demand of 1,300 residents for 78 days. A 5,000-L tank cost approximately ₹11,700.")
                    .findingMr("सांगली येथील रेणावी गावात छतावरील पावसाच्या पाण्याने १,३०० लोकांची ७८ दिवसांची घरगुती पाण्याची गरज भागवली.")
                    .category("ROOFTOP").build(),
            ResearchEvidence.builder().authorYear("Karkar & Bhalerao (2017)").region("Western Ghats")
                    .findingEn("Jalkund storage structures (6,000–30,000 L) saw storage cost plummet from ₹0.14 to ₹0.046 per litre by Year 3.")
                    .findingMr("पश्चिम घाटातील जलकुंडाचा पाणी साठवण खर्च ३ वर्षांत प्रति लिटर १४ पैशांवरून ४.६ पैशांवर आला.")
                    .category("FARM_POND").build(),
            ResearchEvidence.builder().authorYear("Patode et al. (2021)").region("Barshitakli, Akola (Vidarbha)")
                    .findingEn("Continuous check structures along drainage lines sustained higher post-monsoon groundwater tables and irrigated kharif and rabi crops.")
                    .findingMr("अकोला येथे ओढ्यावरील सलग बांधांमुळे भूजल पातळीत लक्षणीय वाढ होऊन रब्बी पिकांना पाणी उपलब्ध झाले.")
                    .category("CHECK_DAM").build(),
            ResearchEvidence.builder().authorYear("Garg (2022)").region("Semi-Arid Watersheds Meta-analysis")
                    .findingEn("Meta-analysis of 25 semi-arid watersheds: groundwater table rose 2.6–6.9 m, crop intensification up 40–100%, and farmer net income improved 50–200%.")
                    .findingMr("२५ पाणलोट क्षेत्रांच्या अभ्यासात भूजल पातळी २.६ ते ६.९ मीटर वाढली व शेतकरी उत्पन्न ५०-२००% वाढले.")
                    .category("MACRO").build(),
            ResearchEvidence.builder().authorYear("Todmal (2019)").region("Maharashtra Semi-Arid Belt")
                    .findingEn("More than 50% of runoff from intense rainfall events drains away unharvested in September–October, representing massive untapped conservation potential.")
                    .findingMr("सप्टेंबर-ऑक्टोबरमधील परतीच्या पावसाचे ५०% पेक्षा जास्त पाणी वाहून जाते, जे साठवण्यासाठी मोठी संधी आहे.")
                    .category("MACRO").build()
        ));
    }

    private void seedLearningGuides() {
        if (learningRepo.count() > 0) return;

        log.info("📖 Seeding bilingual farmer learning guides...");

        learningRepo.saveAll(List.of(
            LearningGuide.builder()
                    .titleEn("Farm Pond (Shet-tale) Construction & Lining")
                    .titleMr("शेततळे निर्मिती आणि अस्तरीकरण पद्धत")
                    .bodyEn("A farm pond of 30m x 30m x 3m can store up to 27 lakh litres of rainwater. Ensure inlet silt trap is constructed and line with 500-micron ISI plastic to prevent seepage in loamy or porous soils.")
                    .bodyMr("३० x ३० x ३ मीटर आकाराचे शेततळे सुमारे २७ लाख लिटर पाणी साठवू शकते. गाळ आत येऊ नये म्हणून इनलेट चेंबर बनवावे आणि गळती रोखण्यासाठी ५०० मायक्रॉनचे प्लास्टिक अस्तर वापरावे.")
                    .category("STRUCTURE")
                    .targetSituation("rain-fed + land > 2 acres")
                    .imageUrl("/assets/farm_pond.svg")
                    .build(),
            LearningGuide.builder()
                    .titleEn("Rooftop Harvesting & First-Flush Separator")
                    .titleMr("छतावरील पावसाचे पाणी साठवण व फर्स्ट-फ्लश फिल्टर")
                    .bodyEn("The first 15-20 minutes of monsoon rain wash rooftop dust and debris. A simple T-junction valve allows dirty initial water to drain away before directing pristine rainwater into your storage tank.")
                    .bodyMr("पावसाच्या पहिल्या १५-२० मिनिटांत छतावरील धूळ वाहून जाते. फर्स्ट-फ्लश पाईपद्वारे सुरुवातीचे गढूळ पाणी सोडून द्यावे आणि त्यानंतरचे स्वच्छ पाणी टाकीत साठवावे.")
                    .category("STRUCTURE")
                    .targetSituation("rooftop + drinking water priority")
                    .imageUrl("/assets/rooftop.svg")
                    .build(),
            LearningGuide.builder()
                    .titleEn("Organic Mulching for Moisture Conservation")
                    .titleMr("ओलावा टिकवण्यासाठी सेंद्रिय आच्छादन (Mulching)")
                    .bodyEn("Spreading a 5-10 cm layer of soybean residue, sugarcane trash, or dry grass cuts surface evaporation by 40%, suppresses weeds, and enriches soil organic carbon as it decomposes.")
                    .bodyMr("सोयाबीनचा भुसा किंवा उसाचे पाचट ५-१० सेंमी जाडीत पिकांच्या ओळीत पसरल्याने बाष्पीभवन ४०% कमी होते आणि तण नियंत्रणात राहते.")
                    .category("SOIL")
                    .targetSituation("drought-prone + low organic carbon")
                    .imageUrl("/assets/mulching.svg")
                    .build(),
            LearningGuide.builder()
                    .titleEn("Pre-Monsoon Tank Desilting & Maintenance")
                    .titleMr("पावसाळ्यापूर्वी शेततळ्यातील गाळ उपसा आणि देखभाल")
                    .bodyEn("Silt accumulation reduces pond capacity by 10-15% annually. Remove accumulated silt in May before pre-monsoon showers and spread it across fields as fertile topsoil.")
                    .bodyMr("मे महिन्यात पावसाळ्यापूर्वी शेततळ्यातील गाळ काढून शेतात टाकावा. यामुळे शेताची सुपीकता वाढते आणि तळ्याची पाणी साठवण क्षमता पूर्ववत होते.")
                    .category("MAINTENANCE")
                    .targetSituation("existing structure")
                    .imageUrl("/assets/desilting.svg")
                    .build()
        ));
    }

    private void loadDistrictRainfallNormals() {
        if (normalRepo.count() > 0) return;

        log.info("📊 Ingesting Maharashtra district-wise 30-year rainfall normals from CSV...");
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(
                new ClassPathResource("data/district wise rainfall normal.csv").getInputStream(), StandardCharsets.UTF_8)).build()) {

            String[] header = reader.readNext();
            String[] row;
            List<DistrictRainfallNormal> normals = new ArrayList<>();

            while ((row = reader.readNext()) != null) {
                if (row.length < 15) continue;
                String state = row[0].trim();
                if ("MAHARASHTRA".equalsIgnoreCase(state)) {
                    String district = row[1].trim();
                    DistrictRainfallNormal n = DistrictRainfallNormal.builder()
                            .district(district)
                            .jan(parseDouble(row[2]))
                            .feb(parseDouble(row[3]))
                            .mar(parseDouble(row[4]))
                            .apr(parseDouble(row[5]))
                            .may(parseDouble(row[6]))
                            .jun(parseDouble(row[7]))
                            .jul(parseDouble(row[8]))
                            .aug(parseDouble(row[9]))
                            .sep(parseDouble(row[10]))
                            .oct(parseDouble(row[11]))
                            .nov(parseDouble(row[12]))
                            .dec(parseDouble(row[13]))
                            .annualMm(parseDouble(row[14]))
                            .build();
                    normals.add(n);
                }
            }
            normalRepo.saveAll(normals);
            log.info("✅ Ingested {} Maharashtra district rainfall normals.", normals.size());
        } catch (Exception e) {
            log.warn("⚠️ Could not load district normals CSV: {}", e.getMessage());
        }
    }

    private void loadHistoricalRainfall() {
        if (historicalRainRepo.count() > 0) return;

        log.info("🌧️ Ingesting multi-decade subdivision rainfall series from CSV...");
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(
                new ClassPathResource("data/rainfall in india 1901-2015.csv").getInputStream(), StandardCharsets.UTF_8)).build()) {

            reader.readNext(); // skip header
            String[] row;
            List<HistoricalRainfall> records = new ArrayList<>();

            Set<String> targetSubdivisions = Set.of("KONKAN & GOA", "MADHYA MAHARASHTRA", "MATATHWADA", "MARATHWADA", "VIDARBHA");

            while ((row = reader.readNext()) != null) {
                if (row.length < 15) continue;
                String sub = row[0].trim().toUpperCase();
                if (targetSubdivisions.contains(sub)) {
                    records.add(HistoricalRainfall.builder()
                            .subdivision(sub)
                            .year(Integer.parseInt(row[1].trim()))
                            .jan(parseDouble(row[2]))
                            .feb(parseDouble(row[3]))
                            .mar(parseDouble(row[4]))
                            .apr(parseDouble(row[5]))
                            .may(parseDouble(row[6]))
                            .jun(parseDouble(row[7]))
                            .jul(parseDouble(row[8]))
                            .aug(parseDouble(row[9]))
                            .sep(parseDouble(row[10]))
                            .oct(parseDouble(row[11]))
                            .nov(parseDouble(row[12]))
                            .dec(parseDouble(row[13]))
                            .annualMm(parseDouble(row[14]))
                            .build());
                }
            }
            historicalRainRepo.saveAll(records);
            log.info("✅ Ingested {} historical subdivision rainfall records for Maharashtra.", records.size());
        } catch (Exception e) {
            log.warn("⚠️ Could not load historical rainfall CSV: {}", e.getMessage());
        }
    }

    private void loadDailyCityWeather() {
        if (weatherRepo.count() > 0) return;

        log.info("☀️ Ingesting daily city weather observations for tank simulation...");
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(
                new ClassPathResource("data/Mumbai_1990_2022_Santacruz.csv").getInputStream(), StandardCharsets.UTF_8)).build()) {

            reader.readNext(); // skip header
            String[] row;
            List<DailyWeather> list = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            int count = 0;
            while ((row = reader.readNext()) != null && count < 2500) { // load ~7 years sample
                if (row.length < 5) continue;
                try {
                    LocalDate date = LocalDate.parse(row[0].trim(), formatter);
                    Double tmin = parseDouble(row[2]);
                    Double tmax = parseDouble(row[3]);
                    Double prcp = parseDouble(row[4]);

                    list.add(DailyWeather.builder()
                            .city("Mumbai")
                            .weatherDate(date)
                            .tminC(tmin)
                            .tmaxC(tmax)
                            .prcpMm(prcp)
                            .build());
                    count++;
                } catch (Exception ignored) {}
            }
            weatherRepo.saveAll(list);
            log.info("✅ Ingested {} daily weather points for Mumbai simulation.", list.size());
        } catch (Exception e) {
            log.warn("⚠️ Could not load city weather CSV: {}", e.getMessage());
        }
    }

    private Double parseDouble(String str) {
        if (str == null || str.trim().isEmpty() || "NA".equalsIgnoreCase(str.trim())) return 0.0;
        try {
            return Double.parseDouble(str.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public DataLoader(UserRepository userRepo, FarmerProfileRepository profileRepo, TankRepository tankRepo, TankTransactionRepository txnRepo, MaintenanceTaskRepository maintenanceRepo, LearningGuideRepository learningRepo, CropProfileRepository cropRepo, DistrictRegionMapRepository regionMapRepo, DistrictRainfallNormalRepository normalRepo, HistoricalRainfallRepository historicalRainRepo, DailyWeatherRepository weatherRepo, PartnerOrganizationRepository partnerRepo, ResearchEvidenceRepository evidenceRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
        this.tankRepo = tankRepo;
        this.txnRepo = txnRepo;
        this.maintenanceRepo = maintenanceRepo;
        this.learningRepo = learningRepo;
        this.cropRepo = cropRepo;
        this.regionMapRepo = regionMapRepo;
        this.normalRepo = normalRepo;
        this.historicalRainRepo = historicalRainRepo;
        this.weatherRepo = weatherRepo;
        this.partnerRepo = partnerRepo;
        this.evidenceRepo = evidenceRepo;
        this.passwordEncoder = passwordEncoder;
    }

}
