package com.revature.popquiz.view.screens

import com.revature.popquiz.checkMatch
import com.revature.popquiz.checkParams
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class RegistrationKtTest {


    @Test
    fun `Checks if checkParams returns true if everything is filled`() {
        val result = checkParams("Test","Test","Test","Test")
        assertTrue(result)
    }
    @Test
    fun `Checks if checkParams returns false if nothing is filled`() {
        val result = checkParams("","","","")
        assertFalse(result)
    }
    @Test
    fun `Checks if checkParams returns false if one is not filled`() {
        val result = checkParams("Test","Test","","Test")
        assertFalse(result)
    }
    @Test
    fun `Checks if checkMatch returns false if there is no match`()
    {
        val result = checkMatch("Test","TestFail")
        assertFalse(result)
    }
    @Test
    fun `Checks if checkMatch returns true if there is a match`()
    {
        val result = checkMatch("Test","Test")
        assertTrue(result)
    }

}