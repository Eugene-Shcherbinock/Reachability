package ua.eshcherbinock.reachability.receiver

import android.content.Context
import android.content.Intent

/**
 * Created by Eugene Shcherbinock on 2/24/18.
 */

interface ReachabilityChangeListener {

    fun onReachabilityStatusChange(context: Context, intent: Intent)

}