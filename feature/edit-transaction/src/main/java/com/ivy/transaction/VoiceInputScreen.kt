package com.ivy.transaction

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivy.base.model.TransactionType
import com.ivy.design.l0_system.UI
import com.ivy.design.l0_system.style
import com.ivy.navigation.EditTransactionScreen
import com.ivy.navigation.navigation

@Composable
fun VoiceInputScreen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val speechRecognizerHelper = remember { SpeechRecognizerHelper(context) }
    
    var isListening by remember { mutableStateOf(false) }
    var recognizedText by remember { mutableStateOf("") }
    var isProcessing by remember { mutableStateOf(false) }
    var navigationTarget by remember { mutableStateOf<EditTransactionScreen?>(null) }
    var restartKey by remember { mutableStateOf(0) }
    
    // Animation for pulsing circle
    val scale = remember { Animatable(1f) }
    
    LaunchedEffect(isListening) {
        if (isListening) {
            scale.animateTo(
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                )
            )
        } else {
            scale.stop()
            scale.snapTo(1f)
        }
    }
    
    // Start listening when screen appears or restart key changes
    LaunchedEffect(restartKey) {
        isListening = true
        recognizedText = ""
        isProcessing = false
        speechRecognizerHelper.startListening()
            .collect { result ->
                when (result) {
                    is SpeechRecognitionResult.Success -> {
                        recognizedText = result.text
                        isListening = false
                        isProcessing = true
                        
                        // Parse the speech and navigate to transaction screen
                        val parsedData = SpeechParser.parseSpeechToTransaction(result.text)
                        val transactionType = determineTransactionType(parsedData, result.text)
                        
                        navigationTarget = EditTransactionScreen(
                            initialTransactionId = null,
                            type = transactionType,
                            voiceAmount = parsedData?.amount,
                            voiceDescription = parsedData?.description,
                            voiceCategoryName = parsedData?.category
                        )
                    }
                    is SpeechRecognitionResult.Error -> {
                        isListening = false
                        recognizedText = "Error: ${result.message}"
                    }
                }
            }
    }
    
    // Handle navigation when target changes
    val nav = navigation()
    LaunchedEffect(navigationTarget) {
        navigationTarget?.let { target ->
            nav.navigateTo(target)
            navigationTarget = null
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UI.colors.pure),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp)
        ) {
            // Pulsing microphone circle
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .scale(scale.value)
                    .clip(CircleShape)
                    .background(
                        if (isListening) UI.colors.primary else UI.colors.medium
                    )
                    .clickable { 
                        if (!isListening && !isProcessing) {
                            restartKey++
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🎤",
                    fontSize = 80.sp
                )
            }
            
            Spacer(modifier = Modifier.size(32.dp))
            
            // Status text
            Text(
                text = when {
                    isProcessing -> "Processing..."
                    isListening -> "Listening..."
                    else -> "Tap to start"
                },
                style = UI.typo.b2.style(
                    color = UI.colors.pureInverse,
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.size(16.dp))
            
            // Recognized text
            if (recognizedText.isNotEmpty()) {
                Text(
                    text = recognizedText,
                    style = UI.typo.b1.style(
                        color = UI.colors.pureInverse,
                        fontWeight = FontWeight.Normal
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

private fun determineTransactionType(parsedData: ParsedTransactionData?, originalText: String): TransactionType {
    val text = originalText.lowercase()
    
    // Check for income keywords - comprehensive list
    val incomeKeywords = listOf(
        "earned", "earn", "received", "receive", "got", "get", "income", "salary", "wage", "wages",
        "payment", "payments", "refund", "refunds", "bonus", "bonuses", "freelance", "freelancing",
        "work", "working", "job", "jobs", "money", "cash", "dollars", "euros", "pounds", "profit",
        "profits", "revenue", "revenues", "commission", "commissions", "tip", "tips", "gift", "gifts",
        "allowance", "allowances", "dividend", "dividends", "interest", "rental", "rentals", "sale", "sales",
        "sold", "sell", "selling", "won", "win", "winning", "prize", "prizes", "award", "awards",
        "grant", "grants", "scholarship", "scholarships", "loan", "loans", "credit", "credits",
        "deposit", "deposits", "investment", "investments", "return", "returns", "yield", "yields",
        "made", "making", "collected", "collect", "gathered", "gather", "obtained", "obtain",
        "acquired", "acquire", "gained", "gain", "picked up", "picked", "took", "take", "taken",
        "brought", "bring", "brought in", "brought home", "came", "come", "arrived", "arrive",
        "delivered", "deliver", "handed", "hand", "handed over", "passed", "pass", "passed on",
        "transferred", "transfer", "moved", "move", "shifted", "shift", "sent", "send", "sent over",
        "paid", "pay", "paid out", "paid back", "reimbursed", "reimburse", "compensated", "compensate",
        "rewarded", "reward", "honored", "honor", "recognized", "recognize", "acknowledged", "acknowledge"
    )
    if (incomeKeywords.any { text.contains(it) }) {
        return TransactionType.INCOME
    }
    
    // Check for transfer keywords - comprehensive list
    val transferKeywords = listOf(
        "transfer", "transferred", "transfers", "moved", "move", "moving", "sent", "send", "sending",
        "to account", "from account", "between accounts", "account to account", "bank transfer",
        "wire transfer", "online transfer", "mobile transfer", "internal transfer", "external transfer",
        "shift", "shifting", "relocate", "relocating", "migrate", "migrating", "convert", "converting",
        "exchange", "exchanging", "swap", "swapping", "switch", "switching", "change", "changing"
    )
    if (transferKeywords.any { text.contains(it) }) {
        return TransactionType.TRANSFER
    }
    
    // Check for expense keywords - comprehensive list
    val expenseKeywords = listOf(
        "spent", "spend", "spending", "paid", "pay", "paying", "bought", "buy", "buying", "purchase",
        "purchased", "purchasing", "expense", "expenses", "cost", "costs", "costing", "charged",
        "charge", "charging", "bill", "bills", "billing", "fee", "fees", "fine", "fines", "penalty",
        "penalties", "tax", "taxes", "taxing", "rent", "renting", "lease", "leasing", "subscription",
        "subscriptions", "membership", "memberships", "donation", "donations", "charity", "charities",
        "tip", "tips", "gratuity", "gratuities", "service", "services", "maintenance", "repair",
        "repairs", "fix", "fixes", "fixing", "upgrade", "upgrades", "upgrading", "install", "installing",
        "delivery", "deliveries", "shipping", "postage", "fuel", "gas", "petrol", "diesel", "toll",
        "tolls", "parking", "ticket", "tickets", "fare", "fares", "admission", "entry", "entrance",
        "booking", "bookings", "reservation", "reservations", "order", "orders", "ordering"
    )
    if (expenseKeywords.any { text.contains(it) }) {
        return TransactionType.EXPENSE
    }
    
    // Default to expense if amount is negative or no clear indicators
    return if (parsedData?.amount != null && parsedData.amount < 0) {
        TransactionType.EXPENSE
    } else {
        TransactionType.EXPENSE // Default to expense
    }
}

@Composable
private fun Spacer(modifier: Modifier) {
    androidx.compose.foundation.layout.Spacer(modifier = modifier)
}


