package ua.eshcherbinock.reachability.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

/**
 * Created by Eugene Shcherbinock on 2/24/18.
 */

internal class ReachabilityReceiver constructor(private val listener: ReachabilityChangeListener) : BroadcastReceiver() {

    companion object {
        private const val REACHABILITY_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"

        val REACHABILITY_CHANGED: IntentFilter = IntentFilter(REACHABILITY_CHANGE_ACTION)
    }

    /**
     * BroadcastReceiver implementation
     */

    override fun onReceive(context: Context, intent: Intent) {
        listener.onReachabilityStatusChange(context, intent)
    }

}