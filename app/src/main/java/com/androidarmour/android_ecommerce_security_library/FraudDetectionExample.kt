package com.androidarmour.android_ecommerce_security_library

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FraudDetectionExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fraud_detection_example)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

fun monitorTransaction(amount: Double, location: String) {
    val normalLimit = 1000.0
    if (amount > normalLimit || isLocationSuspicious(location)) {
        flagTransactionAsSuspicious()
    }
}

fun isLocationSuspicious(location: String): Boolean {
    // Implement logic to check if location deviates from user's usual locations
    return location != "User'sUsualLocation"
}

fun flagTransactionAsSuspicious() {
    // Notify backend or block the transaction
    println("Transaction flagged as suspicious!")
}

fun runFraudDetectionModel(transactionData: TransactionData): Boolean {
    val tfliteModel = loadTFLiteModel("fraud_detection_model.tflite")
    val prediction = tfliteModel.run(transactionData)
    return prediction.isFraud
}
