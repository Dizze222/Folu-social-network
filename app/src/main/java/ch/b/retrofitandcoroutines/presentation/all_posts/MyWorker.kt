package ch.b.retrofitandcoroutines.presentation.all_posts

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        try {
            Log.i("Work","work start")
            Thread.sleep(3000)
            var a = 0
            for (i in 0..100){
                Log.i("Work","doWorking ")
                a += i
            }
            Log.i("Work","doWork $a")

        } catch (ex: Exception) {
            return Result.failure()
        }
        return Result.success()

    }

    override fun onStopped() {
        super.onStopped()

        Log.i("Work","onStopped")
    }
}