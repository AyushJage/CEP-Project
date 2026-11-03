package com.dbit.rwh.service;

import com.dbit.rwh.dto.CostCalculateRequest;
import com.dbit.rwh.dto.CostCalculateResponse;
import org.springframework.stereotype.Service;

@Service
public class CostService {

    public CostCalculateResponse calculateSavings(CostCalculateRequest req) {
        double tankerSavings = (req.getAnnualHarvestedLitres() / 1000.0) * req.getTankerPricePer1000L();

        double pumpingSavings = 0.0;
        if (req.getElectricityOrDieselPerHour() != null && req.getPumpingHoursSavedPerYear() != null) {
            pumpingSavings = req.getElectricityOrDieselPerHour() * req.getPumpingHoursSavedPerYear();
        }

        double totalAnnualSavings = tankerSavings + pumpingSavings;
        double monthlySavings = totalAnnualSavings / 12.0;

        double paybackPeriodYears = (totalAnnualSavings > 0)
                ? (req.getStructureCost() / totalAnnualSavings)
                : 99.0;
        paybackPeriodYears = Math.round(paybackPeriodYears * 10.0) / 10.0;

        String verdictEn;
        String verdictMr;
        if (paybackPeriodYears <= 1.5) {
            verdictEn = "Exceptional ROI! Structure pays for itself in " + paybackPeriodYears + " years.";
            verdictMr = "अतिउत्कृष्ट परतावा! " + paybackPeriodYears + " वर्षांत रचनेचा सर्व खर्च वसूल होईल.";
        } else if (paybackPeriodYears <= 3.5) {
            verdictEn = "Good economic investment. Payback in " + paybackPeriodYears + " years, consistent with Western Ghats research.";
            verdictMr = "उत्तम आर्थिक गुंतवणूक. " + paybackPeriodYears + " वर्षांत खर्च वसूल होईल.";
        } else {
            verdictEn = "Long-term drought security. While financial payback is " + paybackPeriodYears + " years, it safeguards crops against dry spells.";
            verdictMr = "दीर्घकालीन दुष्काळ सुरक्षा. " + paybackPeriodYears + " वर्षांत परतावा मिळून पिकांना कोरड्या दुष्काळात जीवनदान मिळेल.";
        }

        return CostCalculateResponse.builder()
                .tankerSavingsYearly(Math.round(tankerSavings * 100.0) / 100.0)
                .pumpingSavingsYearly(Math.round(pumpingSavings * 100.0) / 100.0)
                .totalSavingsYearly(Math.round(totalAnnualSavings * 100.0) / 100.0)
                .monthlySavings(Math.round(monthlySavings * 100.0) / 100.0)
                .paybackPeriodYears(paybackPeriodYears)
                .paybackVerdict(verdictEn)
                .paybackVerdictEn(verdictEn)
                .paybackVerdictMr(verdictMr)
                .build();
    }
}
