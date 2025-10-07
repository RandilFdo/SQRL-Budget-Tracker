package com.ivy.transaction

import org.junit.Test
import org.junit.Assert.*
import java.time.LocalDate

class SpeechParserTest {

    @Test
    fun `parse complete transaction with amount, date, and category`() {
        val speechText = "I spent 25 dollars on lunch today at the restaurant"
        val result = SpeechParser.parseSpeechInput(speechText)
        
        assertEquals(25.0, result.amount)
        assertEquals(LocalDate.now(), result.date)
        assertEquals("Food", result.category)
        assertEquals("at the restaurant", result.description)
    }

    @Test
    fun `parse income transaction with salary`() {
        val speechText = "Received 2000 salary payment yesterday"
        val result = SpeechParser.parseSpeechInput(speechText)
        
        assertEquals(2000.0, result.amount)
        assertEquals(LocalDate.now().minusDays(1), result.date)
        assertEquals("Salary", result.category)
        assertEquals("payment", result.description)
    }

    @Test
    fun `parse transaction with only amount and category`() {
        val speechText = "500 for groceries"
        val result = SpeechParser.parseSpeechInput(speechText)
        
        assertEquals(500.0, result.amount)
        assertNull(result.date)
        assertEquals("Food", result.category)
        assertNull(result.description)
    }

    @Test
    fun `parse transaction with currency format`() {
        val speechText = "15.50 euros for coffee"
        val result = SpeechParser.parseSpeechInput(speechText)
        
        assertEquals(15.50, result.amount)
        assertNull(result.date)
        assertEquals("Food", result.category)
        assertNull(result.description)
    }

    @Test
    fun `parse transaction with absolute date`() {
        val speechText = "100 for transport on 25/12/2023"
        val result = SpeechParser.parseSpeechInput(speechText)
        
        assertEquals(100.0, result.amount)
        assertEquals(LocalDate.of(2023, 12, 25), result.date)
        assertEquals("Transport", result.category)
        assertNull(result.description)
    }

    @Test
    fun `parse transaction with weekday`() {
        val speechText = "50 for shopping on Monday"
        val result = SpeechParser.parseSpeechInput(speechText)
        
        assertEquals(50.0, result.amount)
        // Date will be the next Monday from today
        assertNotNull(result.date)
        assertEquals("Shopping", result.category)
        assertNull(result.description)
    }

    @Test
    fun `parse transaction with no recognizable data`() {
        val speechText = "some random text without numbers or categories"
        val result = SpeechParser.parseSpeechInput(speechText)
        
        assertNull(result.amount)
        assertNull(result.date)
        assertNull(result.category)
        assertEquals("some random text without numbers or categories", result.description)
    }

    @Test
    fun `parse transaction with multiple amounts takes first one`() {
        val speechText = "I paid 100 but got 50 back for transport"
        val result = SpeechParser.parseSpeechInput(speechText)
        
        assertEquals(100.0, result.amount)
        assertEquals("Transport", result.category)
        assertTrue(result.description?.contains("50") == true)
    }

    @Test
    fun `parse transaction with health category`() {
        val speechText = "200 for doctor visit today"
        val result = SpeechParser.parseSpeechInput(speechText)
        
        assertEquals(200.0, result.amount)
        assertEquals(LocalDate.now(), result.date)
        assertEquals("Health", result.category)
        assertEquals("visit", result.description)
    }

    @Test
    fun `parse transaction with entertainment category`() {
        val speechText = "15 for movie tickets tomorrow"
        val result = SpeechParser.parseSpeechInput(speechText)
        
        assertEquals(15.0, result.amount)
        assertEquals(LocalDate.now().plusDays(1), result.date)
        assertEquals("Entertainment", result.category)
        assertEquals("tickets", result.description)
    }
}
