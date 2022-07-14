package ch.b.retrofitandcoroutines.presentation.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import ch.b.retrofitandcoroutines.presentation.core.ui.MainActivity

class BroadcastReceiver: BroadcastReceiver()  {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("REC",intent!!.getIntExtra(MainActivity().PARAM_TASK,0).toString())
    }
}