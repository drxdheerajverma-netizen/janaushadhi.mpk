package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import java.net.URLEncoder

object IntentHelper {

    fun openDialer(context: Context, phoneNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${phoneNumber.replace(" ", "")}")
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "कॉल करने में असमर्थ: $phoneNumber", Toast.LENGTH_SHORT).show()
        }
    }

    fun openWhatsApp(context: Context, phoneNumber: String, message: String) {
        val cleanNumber = phoneNumber.replace("+", "").replace(" ", "").replace("-", "")
        try {
            val encodedMsg = URLEncoder.encode(message, "UTF-8")
            val uri = Uri.parse("https://api.whatsapp.com/send?phone=$cleanNumber&text=$encodedMsg")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "WhatsApp खोलने में असमर्थ", Toast.LENGTH_SHORT).show()
        }
    }

    fun openGoogleMaps(context: Context, latitude: Double, longitude: Double, label: String) {
        try {
            // Use standard geo uri and fallback to browser/maps web
            val encodedLabel = Uri.encode(label)
            val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($encodedLabel)")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            } else {
                // Fallback to web browser Google Maps
                val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$latitude,$longitude")
                val webIntent = Intent(Intent.ACTION_VIEW, webUri)
                context.startActivity(webIntent)
            }
        } catch (e: Exception) {
            // Final fallback
            try {
                val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=Mohammad+Pur+Khala+Barabanki+Near+Manoj+Jewellers")
                context.startActivity(Intent(Intent.ACTION_VIEW, webUri))
            } catch (err: Exception) {
                Toast.makeText(context, "मानचित्र खोलने में असमर्थ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openEmail(context: Context, emailAddress: String, subject: String, body: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$emailAddress")
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "ईमेल ऐप उपलब्ध नहीं है", Toast.LENGTH_SHORT).show()
        }
    }
}
