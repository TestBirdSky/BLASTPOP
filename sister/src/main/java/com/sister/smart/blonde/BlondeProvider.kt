package com.sister.smart.blonde

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 * Date：2025/4/22
 * Describe:
 */
class BlondeProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        runCatching {
            val c = Class.forName("com.sister.smart.blonde.AngelHelper")
            val s = c.getMethod("smart", String::class.java).invoke(null, uri.toString())
            if (s is Cursor) {
                return s
            }
        }
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}