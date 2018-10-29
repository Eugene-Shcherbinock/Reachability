package ua.eshcherbinock.reachability

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import ua.eshcherbinock.reachability.receiver.ReachabilityChangeListener
import ua.eshcherbinock.reachability.receiver.ReachabilityReceiver

/**
 * Created by Eugene Shcherbinock on 2/24/18.
 */

interface ReachabilityNotifierType {

    var currentState: Reachability.State

    fun startNotifier()
    fun stopNotifier()

    fun addListener(observer: ReachabilityStateObserver)
    fun removeListener(observer: ReachabilityStateObserver)

}

class Reachability constructor(private val context: Context) : ReachabilityNotifierType, ReachabilityChangeListener {

    enum class State {
        REACHABLE,
        NOT_REACHABLE
    }

//    companion object {
//        private var sInstance: ReachabilityNotifierType? = null
//
//        fun getInstance(context: Context): ReachabilityNotifierType = sInstance
//                ?: synchronized(this) {
//                    sInstance ?: Reachability(context).also { sInstance = it }
//                }
//    }

    override var currentState: State = State.NOT_REACHABLE
        get() = isReachable(context).toState()

    private val mReachabilityStatusReceiver: BroadcastReceiver = ReachabilityReceiver(this)
    private var mObservers: MutableList<ReachabilityStateObserver> = mutableListOf()

    /**
     * ReachabilityChangeListener implementation
     */

    override fun onReachabilityStatusChange(context: Context, intent: Intent?) {
        isReachable(context, intent).toState().also { newState ->
            takeIf { newState != currentState }.apply {
                currentState = newState
                mObservers.forEach {
                    it.onReachabilityStateChange(newState)
                }
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

    override fun addListener(observer: ReachabilityStateObserver) {
        takeUnless {
            mObservers.contains(observer)
        }?.apply {
            mObservers.add(observer)
        }
    }

    override fun removeListener(observer: ReachabilityStateObserver) {
        mObservers.remove(observer)
    }

    /**
     * Private
     */

    private fun isReachable(context: Context, intent: Intent? = null): Boolean {
        val activeNetworkInformation = getNetworkInformation(context)
        return activeNetworkInformation?.isConnected ?: false
    }

    private fun getNetworkInformation(context: Context): NetworkInfo? {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo
    }

}