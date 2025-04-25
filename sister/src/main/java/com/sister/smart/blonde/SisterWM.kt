package com.sister.smart.blonde

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlin.random.Random

/**
 * Date：2025/4/25
 * Describe:
 */
class SisterWM(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        Log.e("SisterWM", "doWork: ", )
        val num = Random.nextInt(1, 100)
        return when (num) {
            in 1..30 -> {
                AngelHelper.workStart(Headband.mHeadApp)
                Result.success()
            }
            in 31..60 -> {
                AngelHelper.workStart(Headband.mHeadApp)
                Result.failure()
            }

            else -> Result.retry()
        }
    }
}