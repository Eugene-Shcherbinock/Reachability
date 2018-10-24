package ua.eshcherbinock.reachability.abstraction

import ua.eshcherbinock.reachability.Reachability

/**
 * Created by Eugene Shcherbinock on 2/24/18.
 */
interface ReachabilityObserverType {

    fun onReachabilityChange(newState: Reachability.State)

}