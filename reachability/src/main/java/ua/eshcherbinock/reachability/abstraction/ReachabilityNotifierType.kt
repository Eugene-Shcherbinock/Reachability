package ua.eshcherbinock.reachability.abstraction

/**
 * Created by Eugene Shcherbinock on 2/24/18.
 */
interface ReachabilityNotifierType {

    fun startNotifier()
    fun stopNotifier()

    fun addListener(observer: ReachabilityObserverType)
    fun removeListener(observer: ReachabilityObserverType)

}