package com.ianarbuckle.core.utils

import android.util.Patterns

/**
 * Created by Ian Arbuckle on 2019-09-01.
 *
 */
object FormFieldValidator {

    fun validEmailAddress(email: String): Result<String> {
        return if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Result.success(email)
        } else
            Result.failure(IllegalArgumentException("Invalid email address"))
    }

    fun notBlank(value: String): Result<String> {
        return if (value.isNotEmpty()) {
            Result.success(value)
        } else
            Result.failure(IllegalArgumentException("Value cannot be blank"))
    }
}