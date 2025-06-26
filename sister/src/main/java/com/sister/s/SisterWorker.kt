package com.sister.s

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sister.smart.blonde.AngelHelper
import com.sister.smart.blonde.Headband
import com.sister.worker.WorkerHelper
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * Date：2025/4/25
 * Describe:
 */
class SisterWorker(val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        delay(2900)
        WorkerHelper().workStart(appContext)
        return Result.success()
    }
}