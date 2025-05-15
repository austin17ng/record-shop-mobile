package io.gw.recordshop

import java.text.NumberFormat
import java.util.Locale

object MoneyUtils {
    fun formatMoney(amount: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.US)
        return formatter.format(amount)
    }
}