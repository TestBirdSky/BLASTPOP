package com.sister.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.sister.s.SisterWorker
import java.util.concurrent.TimeUnit

/**
 * Date：2025/6/26
 * Describe:
 */
class WorkerHelper {
    fun workStart(context: Context) {
        val workRequest = OneTimeWorkRequest.Builder(SisterWorker::class.java)
            .setInitialDelay(1, TimeUnit.MINUTES).build()
        val workManager = WorkManager.getInstance(context)
        workManager.enqueueUniqueWork("work_cloak_1980", ExistingWorkPolicy.KEEP, workRequest)
    }

    fun workPeriod(context: Context) {
        val workManager = WorkManager.getInstance(context)
        val work = PeriodicWorkRequest.Builder(WorkerSisterPeriod::class.java, 15, TimeUnit.MINUTES)
            .build()
        workManager.enqueueUniquePeriodicWork("blast_tips", ExistingPeriodicWorkPolicy.UPDATE, work)
    }

}