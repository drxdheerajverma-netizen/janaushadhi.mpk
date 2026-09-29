package com.example.data

import com.example.data.portfolio.AntibioticsPortfolio
import com.example.data.portfolio.CardioPortfolio
import com.example.data.portfolio.DiabetesPortfolio
import com.example.data.portfolio.PainGastroRespiratoryPortfolio
import com.example.data.portfolio.VitaminsSurgicalCarePortfolio
import com.example.model.FaqItem
import com.example.model.MainCategoryFilter
import com.example.model.Medicine
import com.example.model.ProductCategory

object MedicineRepository {

    val storeDetails = StoreInfo(
        centerCode = "PMBJK22063",
        storeNameHindi = "प्रधानमंत्री भारतीय जनऔषधि केंद्र",
        storeNameEnglish = "Pradhan Mantri Bhartiya Janaushadhi Kendra",
        proprietorHindi = "प्रो. धीरज वर्मा",
        proprietorEnglish = "Prop. Dheeraj Verma (D.Pharm)",
        phone = "+919305072480",
        displayPhone = "+91 93050 72480",
        whatsappNumber = "+919305072480",
        email = "drxdheerajverma@gmail.com",
        addressHindi = "मोहम्मदपुर खाला, तहसील फ़तेहपुर, जनपद बाराबंकी, उत्तर प्रदेश - 225303",
        addressEnglish = "Mohammad Pur Khala, Tehsil Fatehpur, Barabanki, Uttar Pradesh - 225303",
        landmarkHindi = "निकट मनोज ज्वैलर्स (Near Manoj Jewellers)",
        landmarkEnglish = "Near Manoj Jewellers",
        latitude = 27.2038,
        longitude = 81.2185,
        weekdayTimingsHindi = "सोमवार - शनिवार: सुबह 10:00 AM से रात्रि 8:00 PM तक",
        sundayTimingsHindi = "रविवार: सुबह 10:00 AM से रात्रि 8:00 PM तक",
        timingsEnglish = "Daily: 10:00 AM - 8:00 PM"
    )

    // Complete PMBJP Product Portfolio list directly from official government catalog
    val medicines: List<Medicine> = DiabetesPortfolio.list +
            CardioPortfolio.list +
            AntibioticsPortfolio.list +
            PainGastroRespiratoryPortfolio.list +
            VitaminsSurgicalCarePortfolio.list

    val faqs: List<FaqItem> = listOf(
        FaqItem(
            id = "faq_01",
            questionHindi = "प्रधानमंत्री जनऔषधि केंद्र की दवाइयाँ इतनी सस्ती क्यों हैं?",
            questionEnglish = "Why are Jan Aushadhi medicines significantly cheaper than market brands?",
            answerHindi = "जनऔषधि केंद्र भारत सरकार के रसायन एवं उर्वरक मंत्रालय (फार्मास्यूटिकल्स विभाग) की पहल है। यहाँ दवाइयाँ बिना किसी बड़े विज्ञापन, ब्रांडिंग या बिचौलियों के खर्च के सीधे थोक में बनकर न्यूनतम सरकारी लागत पर उपलब्ध कराई जाती हैं, जिससे 50% से 90% तक की भारी बचत होती है।",
            answerEnglish = "PMBJP medicines are procured in bulk by the Government without heavy marketing, brand endorsements, or middlemen margins, passing on 50% to 90% direct savings to patients."
        ),
        FaqItem(
            id = "faq_02",
            questionHindi = "क्या सस्ती होने से इन दवाइयों की गुणवत्ता में कोई कमी होती है?",
            questionEnglish = "Does lower price mean compromised quality or lower efficacy?",
            answerHindi = "बिल्कुल नहीं! जनऔषधि की सभी दवाइयाँ विश्व स्वास्थ्य संगठन (WHO-GMP) प्रमाणित अत्याधुनिक विनिर्माण संयंत्रों में बनती हैं। बाजार में लाने से पहले प्रत्येक बैच की NABL मान्यता प्राप्त प्रयोगशालाओं में कठोर रासायनिक व चिकित्सीय जांच होती है। इनकी गुणवत्ता ब्रांडेड दवाओं के 100% समतुल्य होती है।",
            answerEnglish = "Absolutely not. All medicines are manufactured in WHO-GMP certified facilities and tested batch-by-batch in NABL-accredited laboratories. They are 100% bioequivalent to top brand counterparts."
        ),
        FaqItem(
            id = "faq_03",
            questionHindi = "क्या मैं अपनी ब्रांडेड दवा के स्थान पर जनऔषधि की दवा ले सकता हूँ?",
            questionEnglish = "Can I substitute my branded prescription with Jan Aushadhi medicine?",
            answerHindi = "हाँ! आपकी ब्रांडेड दवा के रैपर पर जो 'Generic / Chemical Salt Name' (जैसे Telmisartan, Metformin, Pantoprazole आदि) लिखा होता है, जनऔषधि में भी वही समान साल्ट और शक्ति उपलब्ध होती है। आप हमारे फार्मासिस्ट से पर्चा दिखाकर सही विकल्प ले सकते हैं।",
            answerEnglish = "Yes! Generic medicines contain the exact same active therapeutic ingredient (API) and strength. Show your prescription to our pharmacist (D.Pharm) to get the exact generic equivalent."
        ),
        FaqItem(
            id = "faq_04",
            questionHindi = "जनऔषधि 'सुविधा' सैनिटरी नैपकिन की क्या विशेषता व कीमत है?",
            questionEnglish = "What are the features and pricing of Jan Aushadhi Suvidha Napkins?",
            answerHindi = "जनऔषधि 'सुविधा' सैनिटरी नैपकिन मात्र ₹1 प्रति पैड (4 पैड का पैकेट ₹4 में) उपलब्ध है। यह 100% ऑक्सो-बायोडिग्रेडेबल (पर्यावरण हितैषी) है और इसमें बेहतर सोखने की क्षमता के साथ विंग्स दिए गए हैं।",
            answerEnglish = "PMBJP Suvidha sanitary pads cost only ₹1 per pad (pack of 4 for ₹4). They are 100% oxo-biodegradable and provide premium hygiene and comfort."
        ),
        FaqItem(
            id = "faq_05",
            questionHindi = "दवा लेने के लिए क्या डॉक्टर का पर्चा आवश्यक है?",
            questionEnglish = "Is a doctor's prescription required to purchase medicines?",
            answerHindi = "हाँ, सभी शेड्यूल-H व प्रिस्क्रिप्शन (Rx) वाली जीवनरक्षक दवाइयों (जैसे बीपी, शुगर, थायराइड, एंटीबायोटिक्स) के लिए योग्य पंजीकृत चिकित्सक (RMP) का पर्चा अनिवार्य है। सामान्य ओवर-द-काउंटर (OTC) उत्पाद जैसे सुविधा पैड, विटामिन्स, ग्लूकोमीटर स्ट्रिप्स आदि बिना पर्चे के भी लिए जा सकते हैं।",
            answerEnglish = "Yes, valid prescriptions from a Registered Medical Practitioner (RMP) are required for all Rx/Schedule-H medicines (BP, Diabetes, Thyroid, etc.). OTC items like Suvidha napkins, basic vitamins, and diagnostics do not require a prescription."
        ),
        FaqItem(
            id = "faq_06",
            questionHindi = "क्या मैं व्हाट्सएप (WhatsApp) पर पर्चा भेजकर दवा पता कर सकता हूँ?",
            questionEnglish = "Can I inquire or send my prescription via WhatsApp?",
            answerHindi = "हाँ, आप हमारे व्हाट्सएप नंबर 9305072480 पर अपने डॉक्टर के पर्चे की फोटो भेजकर दवाओं की उपलब्धता और कुल बचत जान सकते हैं। केंद्र पर आकर पर्चा दिखाकर दवा प्राप्त करें।",
            answerEnglish = "Yes! You can share your doctor's prescription via WhatsApp at +91 93050 72480 to check availability and calculate your savings."
        ),
        FaqItem(
            id = "faq_07",
            questionHindi = "दुकान का सटीक पता व खुलने का समय क्या है?",
            questionEnglish = "What is the exact store location and operational timings?",
            answerHindi = "हमारा केंद्र: मोहम्मदपुर खाला, तहसील फ़तेहपुर, जनपद बाराबंकी, उत्तर प्रदेश - 225303 (निकट मनोज ज्वैलर्स) पर स्थित है। केंद्र कोड PMBJK22063 है। खुलने का समय प्रतिदिन सुबह 10:00 AM से रात्रि 8:00 PM तक है।",
            answerEnglish = "Our center is located at Mohammad Pur Khala, Tehsil Fatehpur, Barabanki, UP - 225303, Near Manoj Jewellers (Center Code: PMBJK22063). Open Daily: 10:00 AM to 8:00 PM."
        )
    )

    fun searchMedicines(
        query: String,
        category: ProductCategory = ProductCategory.ALL,
        mainFilter: MainCategoryFilter = MainCategoryFilter.ALL
    ): List<Medicine> {
        val trimmed = query.trim().lowercase()
        return medicines.filter { med ->
            val matchesMain = mainFilter.matches(med.category)
            val matchesCategory = (category == ProductCategory.ALL || med.category == category)
            val matchesQuery = if (trimmed.isEmpty()) {
                true
            } else {
                med.drugCode.equals(trimmed, ignoreCase = true) ||
                med.drugCode.contains(trimmed) ||
                med.hindiName.lowercase().contains(trimmed) ||
                med.englishName.lowercase().contains(trimmed) ||
                med.genericSalt.lowercase().contains(trimmed) ||
                med.descriptionHindi.lowercase().contains(trimmed)
            }
            matchesMain && matchesCategory && matchesQuery
        }
    }
}

data class StoreInfo(
    val centerCode: String,
    val storeNameHindi: String,
    val storeNameEnglish: String,
    val proprietorHindi: String,
    val proprietorEnglish: String,
    val phone: String,
    val displayPhone: String,
    val whatsappNumber: String,
    val email: String,
    val addressHindi: String,
    val addressEnglish: String,
    val landmarkHindi: String,
    val landmarkEnglish: String,
    val latitude: Double,
    val longitude: Double,
    val weekdayTimingsHindi: String,
    val sundayTimingsHindi: String,
    val timingsEnglish: String
)
