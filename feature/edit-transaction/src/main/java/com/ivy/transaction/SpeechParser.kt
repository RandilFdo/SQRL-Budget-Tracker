package com.ivy.transaction

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.max

data class ParsedTransactionData(
    val amount: Double,
    val description: String,
    val date: LocalDateTime,
    val category: String? = null,
    val account: String? = null,
    val transactionType: VoiceTransactionType? = null
)

enum class VoiceTransactionType {
    INCOME, EXPENSE, TRANSFER
}

object SpeechParser {
    
    // This will be set by the EditTransactionViewModel to provide access to user categories
    private var availableCategories: List<com.ivy.data.model.Category> = emptyList()
    
    /**
     * Sets the available categories for matching against user-created categories.
     */
    fun setAvailableCategories(categories: List<com.ivy.data.model.Category>) {
        availableCategories = categories
    }
    
    // Comprehensive category keyword mappings
    private val expenseCategories = mapOf(
        "Food & Drinks" to listOf(
            "food", "drink", "drinks", "eat", "eating", "meal", "meals", "lunch", "dinner", "breakfast",
            "restaurant", "cafe", "coffee", "tea", "beer", "wine", "alcohol", "bar", "pub", "fast food",
            "pizza", "burger", "sandwich", "snack", "snacks", "grocery", "groceries", "supermarket",
            "market", "store", "shopping", "buy", "bought", "purchase", "purchased", "order", "ordered",
            "delivery", "takeout", "take away", "cooking", "ingredients", "recipe", "kitchen", "dining"
        ),
        "Bills & Fees" to listOf(
            "bill", "bills", "fee", "fees", "payment", "pay", "paid", "rent", "rental", "mortgage",
            "electricity", "electric", "power", "gas", "water", "internet", "wifi", "phone", "mobile",
            "cable", "tv", "television", "subscription", "subscriptions", "netflix", "spotify", "youtube",
            "premium", "membership", "insurance", "tax", "taxes", "government", "official", "license",
            "permit", "fine", "penalty", "late fee", "overdue", "utilities", "service", "services"
        ),
        "Transport" to listOf(
            "transport", "transportation", "travel", "trip", "journey", "bus", "train", "metro", "subway",
            "taxi", "uber", "lyft", "ride", "car", "gas", "petrol", "fuel", "parking", "toll", "tolls",
            "highway", "road", "flight", "plane", "airplane", "airport", "ticket", "tickets", "fare",
            "commute", "commuting", "drive", "driving", "vehicle", "maintenance", "repair", "repairs",
            "oil change", "tire", "tires", "registration", "dmv", "license plate", "insurance"
        ),
        "Groceries" to listOf(
            "grocery", "groceries", "supermarket", "market", "store", "food", "vegetables", "fruits",
            "meat", "chicken", "beef", "pork", "fish", "seafood", "dairy", "milk", "cheese", "eggs",
            "bread", "cereal", "pasta", "rice", "cooking", "ingredients", "spices", "herbs", "organic",
            "fresh", "produce", "frozen", "canned", "beverages", "juice", "soda", "water", "household",
            "cleaning", "detergent", "soap", "shampoo", "toilet paper", "paper towels"
        ),
        "Entertainment" to listOf(
            "entertainment", "fun", "movie", "movies", "cinema", "theater", "theatre", "show", "shows",
            "concert", "concerts", "music", "game", "games", "gaming", "video game", "playstation",
            "xbox", "nintendo", "steam", "app", "apps", "software", "book", "books", "magazine",
            "newspaper", "streaming", "netflix", "hulu", "disney", "amazon prime", "youtube", "twitch",
            "party", "parties", "celebration", "birthday", "anniversary", "event", "events", "festival",
            "amusement", "park", "zoo", "museum", "gallery", "art", "hobby", "hobbies", "leisure"
        ),
        "Shopping" to listOf(
            "shopping", "shop", "buy", "bought", "purchase", "purchased", "store", "mall", "online",
            "amazon", "ebay", "etsy", "clothes", "clothing", "shirt", "pants", "dress", "shoes",
            "accessories", "jewelry", "watch", "bag", "purse", "wallet", "electronics", "phone",
            "laptop", "computer", "tablet", "headphones", "speaker", "camera", "gadget", "gadgets",
            "home", "furniture", "decor", "decoration", "appliance", "appliances", "kitchen", "bedroom",
            "living room", "bathroom", "garden", "outdoor", "tools", "equipment", "supplies"
        ),
        "Gifts" to listOf(
            "gift", "gifts", "present", "presents", "donation", "donations", "charity", "charitable",
            "birthday", "anniversary", "wedding", "graduation", "holiday", "christmas", "valentine",
            "mother's day", "father's day", "thank you", "appreciation", "congratulations", "celebration",
            "party", "surprise", "special", "occasion", "milestone", "achievement", "reward", "prize"
        ),
        "Health" to listOf(
            "health", "medical", "doctor", "dentist", "hospital", "clinic", "pharmacy", "medicine",
            "medication", "prescription", "drugs", "vitamins", "supplements", "therapy", "treatment",
            "surgery", "operation", "checkup", "examination", "test", "tests", "lab", "laboratory",
            "x-ray", "scan", "scanning", "fitness", "gym", "workout", "exercise", "yoga", "pilates",
            "massage", "spa", "wellness", "mental health", "counseling", "therapy", "psychologist"
        ),
        "Investments" to listOf(
            "investment", "investments", "invest", "investing", "stock", "stocks", "shares", "equity",
            "bond", "bonds", "mutual fund", "etf", "crypto", "cryptocurrency", "bitcoin", "ethereum",
            "trading", "trader", "portfolio", "retirement", "401k", "ira", "pension", "savings",
            "deposit", "deposits", "cd", "certificate", "treasury", "dividend", "dividends", "profit",
            "capital gains", "brokerage", "robinhood", "fidelity", "vanguard", "schwab"
        ),
        "Loans" to listOf(
            "loan", "loans", "borrow", "borrowed", "lend", "lent", "debt", "credit", "credit card",
            "mortgage", "car loan", "student loan", "personal loan", "payday loan", "interest",
            "principal", "payment", "installment", "monthly payment", "due", "overdue", "late",
            "collection", "debt collector", "bankruptcy", "refinance", "consolidation", "balance",
            "minimum payment", "credit score", "credit report", "fico", "apr", "annual percentage"
        ),
        "Car" to listOf(
            "car", "vehicle", "auto", "automobile", "truck", "suv", "motorcycle", "bike", "bicycle",
            "gas", "petrol", "fuel", "oil", "maintenance", "repair", "repairs", "service", "services",
            "tire", "tires", "brake", "brakes", "engine", "transmission", "battery", "alternator",
            "insurance", "registration", "dmv", "license plate", "inspection", "emissions", "parking",
            "toll", "tolls", "highway", "road", "drive", "driving", "commute", "commuting"
        ),
        "Work" to listOf(
            "work", "job", "employment", "salary", "wage", "wages", "paycheck", "pay", "income",
            "earnings", "bonus", "commission", "overtime", "freelance", "contract", "consulting",
            "business", "office", "meeting", "conference", "travel", "expense", "expenses", "reimbursement",
            "client", "customers", "project", "deadline", "presentation", "training", "education",
            "certification", "license", "professional", "career", "promotion", "raise", "benefits"
        ),
        "Restaurant" to listOf(
            "restaurant", "dining", "dine", "eat", "eating", "meal", "meals", "lunch", "dinner",
            "breakfast", "brunch", "cafe", "coffee", "tea", "bar", "pub", "grill", "steakhouse",
            "italian", "chinese", "japanese", "mexican", "indian", "thai", "french", "mediterranean",
            "seafood", "pizza", "burger", "sandwich", "salad", "soup", "appetizer", "dessert",
            "wine", "beer", "cocktail", "drink", "drinks", "tip", "tips", "service", "waiter",
            "waitress", "chef", "kitchen", "menu", "reservation", "takeout", "delivery"
        ),
        "Family" to listOf(
            "family", "children", "kids", "child", "baby", "toddler", "teenager", "teen", "son",
            "daughter", "parent", "parents", "mother", "father", "mom", "dad", "grandmother",
            "grandfather", "grandma", "grandpa", "sister", "brother", "sibling", "siblings",
            "cousin", "uncle", "aunt", "nephew", "niece", "relative", "relatives", "household",
            "home", "house", "apartment", "rent", "mortgage", "utilities", "maintenance", "repairs"
        ),
        "Social Life" to listOf(
            "social", "friends", "friend", "buddy", "pal", "colleague", "colleagues", "coworker",
            "coworkers", "party", "parties", "gathering", "meetup", "date", "dating", "relationship",
            "wedding", "birthday", "anniversary", "celebration", "event", "events", "concert",
            "show", "movie", "dinner", "lunch", "coffee", "drinks", "bar", "club", "dancing",
            "karaoke", "game night", "board games", "video games", "sports", "team", "league"
        ),
        "Order Food" to listOf(
            "order", "ordered", "ordering", "delivery", "takeout", "take away", "food delivery",
            "uber eats", "doordash", "grubhub", "postmates", "caviar", "seamless", "foodpanda",
            "just eat", "deliveroo", "pizza", "burger", "sandwich", "chinese", "indian", "thai",
            "mexican", "italian", "japanese", "sushi", "fast food", "restaurant", "meal", "meals",
            "lunch", "dinner", "breakfast", "snack", "snacks", "appetizer", "dessert", "drink",
            "drinks", "beverage", "beverages", "tip", "tips", "service fee", "delivery fee"
        ),
        "Travel" to listOf(
            "travel", "trip", "vacation", "holiday", "journey", "adventure", "flight", "plane",
            "airplane", "airport", "hotel", "motel", "hostel", "airbnb", "booking", "reservation",
            "ticket", "tickets", "passport", "visa", "luggage", "baggage", "suitcase", "backpack",
            "tour", "tours", "sightseeing", "attraction", "attractions", "museum", "gallery",
            "beach", "mountain", "city", "country", "international", "domestic", "cruise", "train",
            "bus", "car rental", "taxi", "uber", "lyft", "transportation", "commute"
        ),
        "Fitness" to listOf(
            "fitness", "gym", "workout", "exercise", "training", "sport", "sports", "running",
            "jogging", "walking", "cycling", "biking", "swimming", "yoga", "pilates", "crossfit",
            "weightlifting", "bodybuilding", "cardio", "strength", "endurance", "flexibility",
            "personal trainer", "coach", "coaching", "class", "classes", "membership", "subscription",
            "equipment", "gear", "clothes", "shoes", "nutrition", "supplements", "protein", "vitamins"
        ),
        "Self-development" to listOf(
            "self development", "self-development", "personal development", "learning", "education",
            "course", "courses", "class", "classes", "training", "workshop", "seminar", "conference",
            "book", "books", "ebook", "audiobook", "podcast", "podcasts", "video", "videos",
            "tutorial", "tutorials", "online", "offline", "certification", "certificate", "diploma",
            "degree", "masterclass", "coaching", "mentoring", "skill", "skills", "hobby", "hobbies",
            "language", "languages", "programming", "coding", "design", "art", "music", "writing"
        ),
        "Clothes" to listOf(
            "clothes", "clothing", "fashion", "outfit", "outfits", "shirt", "shirts", "pants",
            "jeans", "dress", "dresses", "skirt", "skirts", "shorts", "jacket", "jackets",
            "coat", "coats", "sweater", "sweaters", "hoodie", "hoodies", "t-shirt", "tshirt",
            "blouse", "blouses", "suit", "suits", "tie", "ties", "shoes", "boots", "sneakers",
            "sandals", "heels", "flats", "socks", "underwear", "lingerie", "accessories", "jewelry",
            "watch", "watches", "bag", "bags", "purse", "purses", "wallet", "wallets", "belt", "belts"
        ),
        "Beauty" to listOf(
            "beauty", "cosmetics", "makeup", "make-up", "skincare", "skin care", "hair", "haircut",
            "hair color", "dye", "dying", "styling", "perm", "straightening", "curling", "blow dry",
            "manicure", "pedicure", "nail", "nails", "polish", "spa", "massage", "facial", "treatment",
            "treatments", "salon", "barber", "stylist", "shampoo", "conditioner", "soap", "lotion",
            "cream", "serum", "moisturizer", "sunscreen", "perfume", "cologne", "fragrance", "deodorant"
        ),
        "Education" to listOf(
            "education", "school", "university", "college", "academy", "institute", "tuition", "fees",
            "books", "textbooks", "supplies", "materials", "equipment", "laptop", "computer", "tablet",
            "software", "course", "courses", "class", "classes", "lesson", "lessons", "tutoring",
            "tutor", "teacher", "professor", "instructor", "training", "workshop", "seminar", "conference",
            "certification", "certificate", "diploma", "degree", "master's", "phd", "research", "thesis"
        ),
        "Pet" to listOf(
            "pet", "pets", "dog", "dogs", "cat", "cats", "bird", "birds", "fish", "hamster", "rabbit",
            "veterinary", "vet", "animal", "animals", "food", "treats", "toys", "collar", "leash",
            "cage", "crate", "bed", "bedding", "litter", "grooming", "bath", "shampoo", "medicine",
            "vaccination", "vaccinations", "checkup", "surgery", "operation", "insurance", "training",
            "walking", "boarding", "daycare", "kennel", "adoption", "rescue", "shelter"
        ),
        "Sports" to listOf(
            "sports", "sport", "athletic", "athletics", "team", "teams", "league", "leagues", "game",
            "games", "match", "matches", "tournament", "tournaments", "competition", "competitions",
            "football", "soccer", "basketball", "baseball", "tennis", "golf", "hockey", "volleyball",
            "swimming", "running", "cycling", "biking", "skiing", "snowboarding", "surfing", "climbing",
            "boxing", "martial arts", "karate", "judo", "taekwondo", "equipment", "gear", "uniform",
            "jersey", "shoes", "cleats", "helmet", "gloves", "ball", "racket", "club", "stick"
        )
    )
    
    // Income category keywords
    private val incomeCategories = mapOf(
        "Work" to listOf(
            "salary", "wage", "wages", "paycheck", "pay", "income", "earnings", "bonus", "commission",
            "overtime", "freelance", "contract", "consulting", "business", "profit", "revenue", "sales",
            "tips", "gratuity", "stipend", "allowance", "pension", "retirement", "benefits", "reimbursement",
            "refund", "rebate", "cashback", "dividend", "dividends", "interest", "investment", "returns"
        ),
        "Investments" to listOf(
            "investment", "investments", "invest", "investing", "stock", "stocks", "shares", "equity",
            "bond", "bonds", "mutual fund", "etf", "crypto", "cryptocurrency", "bitcoin", "ethereum",
            "trading", "trader", "portfolio", "retirement", "401k", "ira", "pension", "savings",
            "deposit", "deposits", "cd", "certificate", "treasury", "dividend", "dividends", "profit",
            "capital gains", "brokerage", "robinhood", "fidelity", "vanguard", "schwab", "returns"
        ),
        "Gifts" to listOf(
            "gift", "gifts", "present", "presents", "donation", "donations", "charity", "charitable",
            "birthday", "anniversary", "wedding", "graduation", "holiday", "christmas", "valentine",
            "mother's day", "father's day", "thank you", "appreciation", "congratulations", "celebration",
            "party", "surprise", "special", "occasion", "milestone", "achievement", "reward", "prize",
            "inheritance", "bequest", "legacy", "windfall", "lottery", "winning", "jackpot"
        ),
        "Family" to listOf(
            "family", "children", "kids", "child", "baby", "toddler", "teenager", "teen", "son",
            "daughter", "parent", "parents", "mother", "father", "mom", "dad", "grandmother",
            "grandfather", "grandma", "grandpa", "sister", "brother", "sibling", "siblings",
            "cousin", "uncle", "aunt", "nephew", "niece", "relative", "relatives", "household",
            "home", "house", "apartment", "rent", "rental", "property", "real estate", "lease"
        ),
        "Other" to listOf(
            "other", "miscellaneous", "misc", "extra", "additional", "side", "part-time", "temporary",
            "seasonal", "casual", "odd job", "gig", "gigs", "task", "tasks", "project", "projects",
            "service", "services", "help", "assistance", "support", "favor", "favors", "exchange",
            "barter", "trade", "swap", "sell", "selling", "sale", "sales", "auction", "garage sale"
        )
    )
    
    // Transaction type indicators
    private val incomeIndicators = listOf(
        "earned", "received", "got", "gained", "made", "won", "inherited", "gifted", "donated",
        "salary", "wage", "bonus", "commission", "profit", "dividend", "interest", "refund",
        "reimbursement", "cashback", "rebate", "stipend", "allowance", "pension", "benefits",
        "income", "earnings", "revenue", "sales", "tips", "gratuity", "investment", "returns"
    )
    
    private val expenseIndicators = listOf(
        "spent", "paid", "bought", "purchased", "cost", "costs", "fee", "fees", "bill", "bills",
        "expense", "expenses", "charge", "charges", "debit", "withdrawal", "payment", "payments",
        "rent", "mortgage", "insurance", "tax", "taxes", "fine", "penalty", "late fee", "overdue",
        "subscription", "membership", "maintenance", "repair", "repairs", "service", "services",
        "shopping", "buying", "purchasing", "spending", "outgoing", "debit", "withdrawal"
    )
    
    private val transferIndicators = listOf(
        "transfer", "transferred", "moved", "sent", "sent to", "from", "to", "between", "account",
        "accounts", "savings", "checking", "credit", "debit", "cash", "bank", "wallet", "balance"
    )
    
    fun parseSpeechToTransaction(speechText: String): ParsedTransactionData? {
        return try {
            val normalizedText = speechText.lowercase().trim()
            val words = normalizedText.split("\\s+".toRegex())
            
            // Extract amount
            val amount = extractAmount(normalizedText, words)
            if (amount == null) return null
            
            // Determine transaction type
            val transactionType = determineTransactionType(normalizedText, words)
            
            // Extract category
            val category = extractCategory(normalizedText, words, transactionType)
            
            // Extract description
            val description = extractDescription(normalizedText, words, amount, category)
            
            // Extract date
            val date = extractDate(normalizedText, words)
            
            // Extract account
            val account = extractAccount(normalizedText, words)
            
            ParsedTransactionData(
                amount = amount,
                description = description,
                date = date,
                category = category,
                account = account,
                transactionType = transactionType
            )
        } catch (e: Exception) {
            null
        }
    }
    
    private fun extractAmount(text: String, words: List<String>): Double? {
        // Try to find currency amounts with various patterns
        val amountPatterns = listOf(
            Regex("\\$\\s*(\\d+(?:\\.\\d{2})?)"), // $50, $50.00
            Regex("(\\d+(?:\\.\\d{2})?)\\s*dollars?"), // 50 dollars, 50.00 dollar
            Regex("(\\d+(?:\\.\\d{2})?)\\s*euros?"), // 50 euros, 50.00 euro
            Regex("(\\d+(?:\\.\\d{2})?)\\s*pounds?"), // 50 pounds, 50.00 pound
            Regex("(\\d+(?:\\.\\d{2})?)\\s*\\€"), // 50€, 50.00€
            Regex("(\\d+(?:\\.\\d{2})?)\\s*£"), // 50£, 50.00£
            Regex("(\\d+(?:\\.\\d{2})?)"), // Just numbers
        )
        
        for (pattern in amountPatterns) {
            val match = pattern.find(text)
            if (match != null) {
                val amountStr = match.groupValues[1]
                try {
                    val amount = amountStr.toDouble()
                    if (amount > 0) return amount
                } catch (e: NumberFormatException) {
                    continue
                }
            }
        }
        
        // Fallback: look for numbers in words
        for (word in words) {
            try {
                val amount = word.toDouble()
                if (amount > 0) return amount
            } catch (e: NumberFormatException) {
                continue
            }
        }
        
        return null
    }
    
    private fun determineTransactionType(text: String, words: List<String>): VoiceTransactionType {
        val textLower = text.lowercase()
        
        // Check for transfer indicators first
        val transferScore = transferIndicators.count { textLower.contains(it) }
        if (transferScore > 0) return VoiceTransactionType.TRANSFER
        
        // Check for income indicators
        val incomeScore = incomeIndicators.count { textLower.contains(it) }
        
        // Check for expense indicators
        val expenseScore = expenseIndicators.count { textLower.contains(it) }
        
        // Additional context clues
        val incomeContextScore = when {
            textLower.contains("from") && (textLower.contains("work") || textLower.contains("job")) -> 2
            textLower.contains("received") || textLower.contains("got") || textLower.contains("earned") -> 2
            textLower.contains("salary") || textLower.contains("wage") || textLower.contains("bonus") -> 3
            textLower.contains("dividend") || textLower.contains("interest") || textLower.contains("investment") -> 2
            else -> 0
        }
        
        val expenseContextScore = when {
            textLower.contains("for") && (textLower.contains("food") || textLower.contains("rent") || textLower.contains("bill")) -> 2
            textLower.contains("spent") || textLower.contains("paid") || textLower.contains("bought") -> 2
            textLower.contains("subscription") || textLower.contains("fee") || textLower.contains("bill") -> 3
            else -> 0
        }
        
        val totalIncomeScore = incomeScore + incomeContextScore
        val totalExpenseScore = expenseScore + expenseContextScore
        
        return when {
            totalIncomeScore > totalExpenseScore -> VoiceTransactionType.INCOME
            totalExpenseScore > totalIncomeScore -> VoiceTransactionType.EXPENSE
            else -> VoiceTransactionType.EXPENSE // Default to expense if unclear
        }
    }
    
    private fun extractCategory(text: String, words: List<String>, transactionType: VoiceTransactionType): String? {
        // First try to match against user-created categories using CategoryMatcher
        if (availableCategories.isNotEmpty()) {
            val matchedCategory = CategoryMatcher.findBestMatch(text, availableCategories, transactionType)
            if (matchedCategory != null) {
                return matchedCategory.name.value
            }
        }
        
        // Fallback to predefined category matching
        val textLower = text.lowercase()
        val categories = if (transactionType == VoiceTransactionType.INCOME) incomeCategories else expenseCategories
        
        var bestMatch: String? = null
        var bestScore = 0
        
        for ((categoryName, keywords) in categories) {
            var score = 0
            
            // Direct keyword matching
            for (keyword in keywords) {
                if (textLower.contains(keyword)) {
                    score += 1
                    
                    // Boost score for exact word matches
                    if (words.contains(keyword)) {
                        score += 2
                    }
                    
                    // Boost score for longer keywords (more specific)
                    if (keyword.length > 5) {
                        score += 1
                    }
                }
            }
            
            // Context-based scoring
            when (categoryName) {
                "Food & Drinks" -> {
                    if (textLower.contains("restaurant") || textLower.contains("cafe") || textLower.contains("bar")) score += 3
                    if (textLower.contains("lunch") || textLower.contains("dinner") || textLower.contains("breakfast")) score += 2
                }
                "Bills & Fees" -> {
                    if (textLower.contains("monthly") || textLower.contains("subscription")) score += 2
                    if (textLower.contains("electricity") || textLower.contains("water") || textLower.contains("gas")) score += 3
                }
                "Transport" -> {
                    if (textLower.contains("uber") || textLower.contains("taxi") || textLower.contains("bus")) score += 3
                    if (textLower.contains("gas") || textLower.contains("fuel") || textLower.contains("parking")) score += 2
                }
                "Shopping" -> {
                    if (textLower.contains("amazon") || textLower.contains("online") || textLower.contains("store")) score += 2
                }
                "Health" -> {
                    if (textLower.contains("doctor") || textLower.contains("hospital") || textLower.contains("pharmacy")) score += 3
                }
            }
            
            if (score > bestScore) {
                bestScore = score
                bestMatch = categoryName
            }
        }
        
        return if (bestScore > 0) bestMatch else null
    }
    
    private fun extractDescription(text: String, words: List<String>, amount: Double, category: String?): String {
        var description = text
        
        // Remove amount from description
        description = description.replace(Regex("\\$\\s*\\d+(?:\\.\\d{2})?"), "")
        description = description.replace(Regex("\\d+(?:\\.\\d{2})?\\s*(?:dollars?|euros?|pounds?)"), "")
        description = description.replace(Regex("\\d+(?:\\.\\d{2})?\\s*[\\$€£]"), "")
        description = description.replace(Regex("\\b\\d+(?:\\.\\d{2})?\\b"), "")
        
        // Remove common filler words
        val fillerWords = listOf("i", "spent", "paid", "bought", "purchased", "for", "on", "at", "the", "a", "an", "and", "or", "but", "in", "on", "at", "to", "for", "of", "with", "by")
        description = description.split("\\s+".toRegex())
            .filter { it.isNotBlank() && !fillerWords.contains(it.lowercase()) }
            .joinToString(" ")
        
        // If description is too short or empty, create a meaningful one
        if (description.isBlank() || description.length < 3) {
            description = when {
                category != null -> "$category transaction"
                else -> "Voice transaction"
            }
        }
        
        return description.trim().takeIf { it.isNotBlank() } ?: "Voice transaction"
    }
    
    private fun extractDate(text: String, words: List<String>): LocalDateTime {
        val textLower = text.lowercase()
        val now = LocalDateTime.now()
        
        return when {
            textLower.contains("today") -> now
            textLower.contains("yesterday") -> now.minusDays(1)
            textLower.contains("tomorrow") -> now.plusDays(1)
            textLower.contains("monday") -> now.with(java.time.DayOfWeek.MONDAY)
            textLower.contains("tuesday") -> now.with(java.time.DayOfWeek.TUESDAY)
            textLower.contains("wednesday") -> now.with(java.time.DayOfWeek.WEDNESDAY)
            textLower.contains("thursday") -> now.with(java.time.DayOfWeek.THURSDAY)
            textLower.contains("friday") -> now.with(java.time.DayOfWeek.FRIDAY)
            textLower.contains("saturday") -> now.with(java.time.DayOfWeek.SATURDAY)
            textLower.contains("sunday") -> now.with(java.time.DayOfWeek.SUNDAY)
            else -> now
        }
    }
    
    private fun extractAccount(text: String, words: List<String>): String? {
        val textLower = text.lowercase()
        
        val accountKeywords = listOf(
            "cash", "bank", "checking", "savings", "credit", "debit", "card", "wallet", "paypal",
            "venmo", "zelle", "apple pay", "google pay", "revolut", "chase", "bank of america",
            "wells fargo", "citi", "capital one", "discover", "amex", "american express"
        )
        
        for (keyword in accountKeywords) {
            if (textLower.contains(keyword)) {
                return keyword.replaceFirstChar { it.uppercase() }
            }
        }
        
        return null
    }
}