package com.dbit.rwh.service;

import com.dbit.rwh.entity.HarvestCalculation;
import com.dbit.rwh.entity.RainfallRecord;
import com.dbit.rwh.entity.Tank;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.HarvestCalculationRepository;
import com.dbit.rwh.repository.RainfallRecordRepository;
import com.dbit.rwh.repository.TankRepository;
import com.dbit.rwh.repository.TankTransactionRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
public class ReportService {

    private final RainfallRecordRepository rainfallRepo;
    private final HarvestCalculationRepository harvestRepo;
    private final TankRepository tankRepo;
    private final TankTransactionRepository txnRepo;

    public Map<String, Object> getYearlyReport(User farmer, int year) {
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);

        List<RainfallRecord> rains = rainfallRepo.findAllByFarmerAndRecordDateBetweenOrderByRecordDateAsc(farmer, start, end);
        double totalRainMm = rains.stream().mapToDouble(RainfallRecord::getRainfallMm).sum();

        List<HarvestCalculation> harvests = harvestRepo.findAllByFarmerOrderByCreatedAtDesc(farmer);
        double totalHarvestLitres = harvests.stream().mapToDouble(HarvestCalculation::getHarvestedLitres).sum();

        List<Tank> tanks = tankRepo.findAllByFarmer(farmer);
        double totalCapacity = tanks.stream().mapToDouble(Tank::getCapacityLitres).sum();

        double totalInflow = 0.0;
        double totalOutflow = 0.0;
        for (Tank t : tanks) {
            totalInflow += txnRepo.getTotalInflowByTankId(t.getId());
            totalOutflow += txnRepo.getTotalOutflowByTankId(t.getId());
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("year", year);
        result.put("farmerName", farmer.getName());
        result.put("phone", farmer.getPhone());
        result.put("district", farmer.getDistrict());
        result.put("taluka", farmer.getTaluka());
        result.put("village", farmer.getVillage());
        result.put("totalRainfallMm", Math.round(totalRainMm * 10.0) / 10.0);
        result.put("totalHarvestLitres", Math.round(totalHarvestLitres * 10.0) / 10.0);
        result.put("totalStorageCapacityLitres", totalCapacity);
        result.put("totalWaterUsedLitres", Math.round(totalOutflow * 10.0) / 10.0);
        result.put("netRemainingLitres", Math.max(0.0, totalInflow - totalOutflow));
        return result;
    }

    public byte[] exportCsv(User farmer, int year) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(baos, StandardCharsets.UTF_8))) {
            writer.writeNext(new String[]{"Rainwater Harvesting & Water Conservation Monitor - Annual Report"});
            writer.writeNext(new String[]{"Farmer Name", farmer.getName(), "Phone", farmer.getPhone()});
            writer.writeNext(new String[]{"Location", farmer.getVillage() + ", " + farmer.getTaluka() + ", " + farmer.getDistrict()});
            writer.writeNext(new String[]{"Report Year", String.valueOf(year)});
            writer.writeNext(new String[]{""});

            writer.writeNext(new String[]{"Date", "Rainfall (mm)", "Note"});
            LocalDate start = LocalDate.of(year, 1, 1);
            LocalDate end = LocalDate.of(year, 12, 31);
            List<RainfallRecord> rains = rainfallRepo.findAllByFarmerAndRecordDateBetweenOrderByRecordDateAsc(farmer, start, end);
            for (RainfallRecord r : rains) {
                writer.writeNext(new String[]{
                        r.getRecordDate().toString(),
                        String.valueOf(r.getRainfallMm()),
                        r.getNote() != null ? r.getNote() : ""
                });
            }
        }
        return baos.toByteArray();
    }

    public byte[] exportPdf(User farmer, int year) throws Exception {
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();

        // Header Title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, new Color(11, 110, 153));
        Paragraph title = new Paragraph("Rainwater Harvesting & Water Conservation Monitor", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Font subFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 11, Color.DARK_GRAY);
        Paragraph sub = new Paragraph("Don Bosco Institute of Technology - College Community Project (Maharashtra)", subFont);
        sub.setAlignment(Element.ALIGN_CENTER);
        sub.setSpacingAfter(15);
        document.add(sub);

        // Farmer Info Table
        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidthPercentage(100);
        infoTable.setSpacingAfter(15);

        addCell(infoTable, "Farmer Name:", true);
        addCell(infoTable, farmer.getName(), false);
        addCell(infoTable, "Contact Phone:", true);
        addCell(infoTable, farmer.getPhone(), false);
        addCell(infoTable, "Village / Taluka:", true);
        addCell(infoTable, farmer.getVillage() + ", " + farmer.getTaluka(), false);
        addCell(infoTable, "District:", true);
        addCell(infoTable, farmer.getDistrict(), false);
        addCell(infoTable, "Report Year:", true);
        addCell(infoTable, String.valueOf(year), false);
        document.add(infoTable);

        // Summary Card
        Map<String, Object> summary = getYearlyReport(farmer, year);
        Paragraph summaryHeading = new Paragraph("Annual Water Conservation Summary", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
        summaryHeading.setSpacingAfter(10);
        document.add(summaryHeading);

        PdfPTable sumTable = new PdfPTable(2);
        sumTable.setWidthPercentage(100);
        sumTable.setSpacingAfter(15);
        addCell(sumTable, "Total Recorded Rainfall (mm):", true);
        addCell(sumTable, summary.get("totalRainfallMm") + " mm", false);
        addCell(sumTable, "Total Estimated Harvest (Litres):", true);
        addCell(sumTable, summary.get("totalHarvestLitres") + " L", false);
        addCell(sumTable, "Total Storage Capacity:", true);
        addCell(sumTable, summary.get("totalStorageCapacityLitres") + " L", false);
        addCell(sumTable, "Total Water Utilized:", true);
        addCell(sumTable, summary.get("totalWaterUsedLitres") + " L", false);
        addCell(sumTable, "Net Stored Reserve:", true);
        addCell(sumTable, summary.get("netRemainingLitres") + " L", false);
        document.add(sumTable);

        document.close();
        return baos.toByteArray();
    }

    private void addCell(PdfPTable table, String text, boolean isHeader) {
        PdfPCell cell = new PdfPCell(new Phrase(text, isHeader ? FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10) : FontFactory.getFont(FontFactory.HELVETICA, 10)));
        cell.setPadding(6);
        if (isHeader) {
            cell.setBackgroundColor(new Color(240, 247, 250));
        }
        table.addCell(cell);
    }

    public ReportService(RainfallRecordRepository rainfallRepo, HarvestCalculationRepository harvestRepo, TankRepository tankRepo, TankTransactionRepository txnRepo) {
        this.rainfallRepo = rainfallRepo;
        this.harvestRepo = harvestRepo;
        this.tankRepo = tankRepo;
        this.txnRepo = txnRepo;
    }

}
