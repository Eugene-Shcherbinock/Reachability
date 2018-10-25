package ua.eshcherbinock.reachability

/**
 * Created by Eugene Shcherbinock on 2/24/18.
 */
interface ReachabilityStateObserver {

    fun onReachabilityStateChange(newState: Reachability.State)

}