package ua.eshcherbinock.reachability

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import ua.eshcherbinock.reachability.abstraction.ReachabilityNotifierType
import ua.eshcherbinock.reachability.abstraction.ReachabilityObserverType
import ua.eshcherbinock.reachability.receiver.ReachabilityChangeListener
import ua.eshcherbinock.reachability.receiver.ReachabilityReceiver

/**
 * Created by Eugene Shcherbinock on 2/24/18.
 */

class Reachability private constructor(private val context: Context) : ReachabilityNotifierType, ReachabilityChangeListener {

    enum class State {
        REACHABLE,
        NOT_REACHABLE
    }

    companion object {
        private var sInstance: Reachability? = null

        fun getInstance(context: Context): ReachabilityNotifierType = sInstance
                ?: synchronized(this) {
                    sInstance ?: Reachability(context).also { sInstance = it }
                }
    }

    /**
     * Properties
     */

    private val mReachabilityStatusReceiver: BroadcastReceiver = ReachabilityReceiver(this)
    private var mObservers: MutableList<ReachabilityObserverType> = mutableListOf()

    /**
     * ReachabilityChangeListener implementation
     */

    override fun onReachabilityStatusChange(context: Context, intent: Intent) {
        when (isReachable(context, intent)) {
            true -> State.REACHABLE
            else -> State.NOT_REACHABLE
        }.also { newState ->
            mObservers.forEach {
                it.onReachabilityChange(newState)
            }
        }
    }

    /**
     * ReachabilityNotifierType implementation
     */

    override fun startNotifier() {
        context.registerReceiver(mReachabilityStatusReceiver, ReachabilityReceiver.REACHABILITY_CHANGED)
    }

    override fun stopNotifier() {
        context.unregisterReceiver(mReachabilityStatusReceiver)
    }

    override fun addListener(observer: ReachabilityObserverType) {
        mObservers.add(observer)
    }

    override fun removeListener(observer: ReachabilityObserverType) {
        mObservers.remove(observer)
    }

    /**
     * Private
     */

    private fun isReachable(context: Context, intent: Intent): Boolean {
        val activeNetworkInformation = getNetworkInformation(context)
        return activeNetworkInformation?.isConnected ?: false
    }

    private fun getNetworkInformation(context: Context): NetworkInfo? {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo
    }

}