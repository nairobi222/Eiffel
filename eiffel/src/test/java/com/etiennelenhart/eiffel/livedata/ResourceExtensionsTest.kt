package com.etiennelenhart.eiffel.livedata

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ResourceExtensionsTest {

    @Test
    fun `GIVEN Resource Success with 'value' WHEN 'onSuccess' called THEN 'block' is invoked`() {
        val resource = Resource.Success("block")

        var actual = ""
        resource.onSuccess { actual = it }

        assertEquals("block", actual)
    }

    @Test
    fun `GIVEN Resource Pending with 'value' WHEN 'onSuccess' called THEN 'block' is not invoked`() {
        val resource = Resource.Pending("block")

        var actual = ""
        resource.onSuccess { actual = it }

        assertNotEquals("block", actual)
    }

    @Test
    fun `GIVEN Resource Failure WHEN 'onSuccess' called THEN 'block' is not invoked`() {
        val resource = Resource.Failure("block", 0)

        var actual = ""
        resource.onSuccess { actual = it }

        assertNotEquals("block", actual)
    }

    @Test
    fun `GIVEN Resource Pending with 'value' WHEN 'onPending' called THEN 'block' is invoked`() {
        val resource = Resource.Pending("block")

        var actual = ""
        resource.onPending { actual = it }

        assertEquals("block", actual)
    }

    @Test
    fun `GIVEN Resource Success with 'value' WHEN 'onPending' called THEN 'block' is not invoked`() {
        val resource = Resource.Success("block")

        var actual = ""
        resource.onPending { actual = it }

        assertNotEquals("block", actual)
    }

    @Test
    fun `GIVEN Resource Failure WHEN 'onPending' called THEN 'block' is not invoked`() {
        val resource = Resource.Failure("block", 0)

        var actual = ""
        resource.onPending { actual = it }

        assertNotEquals("block", actual)
    }

    @Test
    fun `GIVEN Resource Failure WHEN 'onFailure' called THEN 'block' is invoked`() {
        val resource = Resource.Failure("block", 0)

        var actual = ""
        resource.onFailure { value, _ -> actual = value }

        assertEquals("block", actual)
    }

    @Test
    fun `GIVEN Resource Success with 'value' WHEN 'onFailure' called THEN 'block' is not invoked`() {
        val resource = Resource.Success("block")

        var actual = ""
        resource.onFailure { value, _ -> actual = value }

        assertNotEquals("block", actual)
    }

    @Test
    fun `GIVEN Resource Pending with 'value' WHEN 'onFailure' called THEN 'block' is not invoked`() {
        val resource = Resource.Pending("block")

        var actual = ""
        resource.onFailure { value, _ -> actual = value }

        assertNotEquals("block", actual)
    }
}
