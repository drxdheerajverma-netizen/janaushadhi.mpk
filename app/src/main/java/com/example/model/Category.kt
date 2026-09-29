package com.example.model

enum class MainCategoryFilter(
    val id: String,
    val titleHindi: String,
    val titleEnglish: String,
    val iconName: String
) {
    ALL(
        id = "all",
        titleHindi = "सभी उत्पाद",
        titleEnglish = "All Items",
        iconName = "Medication"
    ),
    GENERIC(
        id = "generic",
        titleHindi = "जेनेरिक दवाइयाँ",
        titleEnglish = "Generic Medicines",
        iconName = "LocalPharmacy"
    ),
    SURGICAL(
        id = "surgical",
        titleHindi = "सर्जिकल व उपकरण",
        titleEnglish = "Surgical & Devices",
        iconName = "MedicalServices"
    ),
    SUPPLEMENTS(
        id = "supplements",
        titleHindi = "सप्लीमेंट्स व पोषण",
        titleEnglish = "Supplements",
        iconName = "Spa"
    ),
    WOMEN_CHILD(
        id = "women_child",
        titleHindi = "महिला व शिशु",
        titleEnglish = "Women & Child",
        iconName = "Female"
    );

    fun matches(category: ProductCategory): Boolean {
        return when (this) {
            ALL -> true
            GENERIC -> category in listOf(
                ProductCategory.DIABETES,
                ProductCategory.BP_HEART,
                ProductCategory.ANTIBIOTICS,
                ProductCategory.GASTRO_ACIDITY,
                ProductCategory.RESPIRATORY,
                ProductCategory.THYROID,
                ProductCategory.SKIN_DERMA,
                ProductCategory.GENERAL_MEDS
            )
            SURGICAL -> category == ProductCategory.SURGICAL_DEVICES
            SUPPLEMENTS -> category == ProductCategory.VITAMINS
            WOMEN_CHILD -> category in listOf(
                ProductCategory.WOMEN_CARE,
                ProductCategory.CHILD_CARE,
                ProductCategory.SANITARY_NAPKINS
            )
        }
    }
}

enum class ProductCategory(
    val id: String,
    val titleHindi: String,
    val titleEnglish: String,
    val iconName: String,
    val descriptionHindi: String
) {
    ALL(
        id = "all",
        titleHindi = "सभी उत्पाद",
        titleEnglish = "All Products",
        iconName = "Medication",
        descriptionHindi = "केंद्र पर उपलब्ध समस्त जेनेरिक दवाइयाँ व सर्जिकल उत्पाद"
    ),
    DIABETES(
        id = "diabetes",
        titleHindi = "डायबिटीज (शुगर)",
        titleEnglish = "Diabetes Care",
        iconName = "Bloodtype",
        descriptionHindi = "मेटफॉर्मिन, ग्लिमेपिराइड, वोग्लीबोस, डापाग्लिफ्लोज़िन व इंसुलिन"
    ),
    BP_HEART(
        id = "bp_heart",
        titleHindi = "बीपी एवं हृदय रोग",
        titleEnglish = "BP & Heart Care",
        iconName = "Favorite",
        descriptionHindi = "टेल्मीसार्टन, एम्लोडिपिन, एटोरवास्टेटिन, रोसुवास्टेटिन व हृदय दवाइयाँ"
    ),
    ANTIBIOTICS(
        id = "antibiotics",
        titleHindi = "एंटीबायोटिक्स",
        titleEnglish = "Antibiotics",
        iconName = "Biotech",
        descriptionHindi = "अमोक्सीसिलिन + क्लेव, सेफिक्सिम, एज़िथ्रोमाइसिन, सिप्रोफ्लोक्सासिन"
    ),
    GASTRO_ACIDITY(
        id = "gastro_acidity",
        titleHindi = "गैस व पाचन",
        titleEnglish = "Gastro & Digestion",
        iconName = "LocalPharmacy",
        descriptionHindi = "पैंटोप्रैजोल, रेबेप्रैजोल, ओमेप्रैजोल, सुक्रालफेट व एन्जाइम सिरप"
    ),
    RESPIRATORY(
        id = "respiratory",
        titleHindi = "खाँसी व श्वास",
        titleEnglish = "Respiratory & Allergy",
        iconName = "Air",
        descriptionHindi = "मोंटेलुकास्ट + लेवोसेटिरिज़िन, साल्बुटामॉल, बुडेसोनाइड, कफ सिरप"
    ),
    THYROID(
        id = "thyroid",
        titleHindi = "थायराइड",
        titleEnglish = "Thyroid Care",
        iconName = "HealthAndSafety",
        descriptionHindi = "थायरोक्सिन सोडियम 12.5mcg से 150mcg तक पूर्ण रेंज"
    ),
    VITAMINS(
        id = "vitamins",
        titleHindi = "विटामिन व सप्लीमेंट्स",
        titleEnglish = "Vitamins & Supplements",
        iconName = "Spa",
        descriptionHindi = "विटामिन D3 60K, B-कॉम्प्लेक्स, कैल्शियम, B12 व प्रोटीन पाउडर"
    ),
    SKIN_DERMA(
        id = "skin_derma",
        titleHindi = "त्वचा व मलहम",
        titleEnglish = "Skin & Topicals",
        iconName = "Healing",
        descriptionHindi = "क्लोट्रिमाजोल, कीटोकोनाजोल, म्यूसिरोसिन, सिल्वर सल्फाडायजीन"
    ),
    WOMEN_CARE(
        id = "women_care",
        titleHindi = "महिला स्वास्थ्य",
        titleEnglish = "Women's Health",
        iconName = "Female",
        descriptionHindi = "फॉलिक एसिड, आयरन टॉनिक, प्रेगनेंसी टेस्ट किट व इंटिमेट वॉश"
    ),
    CHILD_CARE(
        id = "child_care",
        titleHindi = "शिशु एवं बाल स्वास्थ्य",
        titleEnglish = "Children's Health",
        iconName = "ChildCare",
        descriptionHindi = "पैरासिटामॉल सिरप, जिंक, ORS घोल, ग्राइप वाटर व बेबी ड्रॉप्स"
    ),
    SANITARY_NAPKINS(
        id = "sanitary_napkins",
        titleHindi = "सुविधा सैनिटरी पैड",
        titleEnglish = "Suvidha Napkins",
        iconName = "CleanHands",
        descriptionHindi = "मात्र ₹1 प्रति पैड! बायो-डिग्रेडेबल जनऔषधि 'सुविधा' पैड व डायपर"
    ),
    SURGICAL_DEVICES(
        id = "surgical_devices",
        titleHindi = "सर्जिकल व उपकरण",
        titleEnglish = "Surgical & Devices",
        iconName = "MedicalServices",
        descriptionHindi = "डिजिटल बीपी मशीन, ग्लूकोमीटर, पल्स ऑक्सीमीटर, पट्टियां व कॉटन"
    ),
    GENERAL_MEDS(
        id = "general_meds",
        titleHindi = "दर्द निवारक व सामान्य",
        titleEnglish = "Pain & General",
        iconName = "Healing",
        descriptionHindi = "पैरासिटामॉल 650, एसिक्लोफेनाक, डिक्लोफेनाक जेल व स्प्रे"
    )
}
