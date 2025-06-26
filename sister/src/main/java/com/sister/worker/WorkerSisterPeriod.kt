package com.sister.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

/**
 * Date：2025/6/26
 * Describe:
 */
class WorkerSisterPeriod(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return Result.success()
    }
}