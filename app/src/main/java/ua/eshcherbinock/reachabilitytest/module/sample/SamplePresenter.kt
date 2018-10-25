package ua.eshcherbinock.reachabilitytest.module.sample

import android.os.Bundle
import ua.eshcherbinock.reachability.Reachability
import ua.eshcherbinock.reachability.ReachabilityNotifierType
import ua.eshcherbinock.reachability.ReachabilityStateObserver
import ua.eshcherbinock.reachabilitytest.model.SampleDataModel
import ua.eshcherbinock.reachabilitytest.model.SampleDataModelType
import ua.eshcherbinock.reachabilitytest.module.base.BasePresenter

class SamplePresenter: BasePresenter<SampleModuleContracts.SampleViewType>(), SampleModuleContracts.SamplePresenterType, ReachabilityStateObserver {

    /**
     * Properties
     */

    private lateinit var mReachability: ReachabilityNotifierType

    private val mSampleModel: SampleDataModelType = SampleDataModel()

    /**
     * BasePresenter
     */

    override fun onCreate(view: SampleModuleContracts.SampleViewType, withSavedInstanceState: Bundle?) {
        super.onCreate(view, withSavedInstanceState)

        mReachability = Reachability.getInstance(view.getContext())
        mReachability.addListener(this)
    }

    override fun onStart() {
        super.onStart()
        mReachability.startNotifier()
    }

    override fun onStop() {
        super.onStop()
        mReachability.stopNotifier()
    }

    override fun onDestroy() {
        super.onDestroy()
        mReachability.removeListener(this)
    }

    /**
     * SamplePresenterType implementation
     */

    override fun fetchData() {
        if (mReachability.currentState == Reachability.State.NOT_REACHABLE) {
            return
        }
        fetch()
    }

    /**
     * ReachabilityStateObserver implementation
     */

    override fun onReachabilityStateChange(newState: Reachability.State) {
        mView?.setReachabilityIndicatorState(newState == Reachability.State.NOT_REACHABLE)
        if (newState == Reachability.State.REACHABLE) {
            fetch()
        }
    }

    /**
     * Private
     */

    private fun fetch() {
        mView?.setLoadingIndicatorState(true)

        mSampleModel.fetchFakeData { result: String ->
            mView?.let {
                it.setLoadingIndicatorState(false)
                it.showLoadedData(result)
            }
        }
    }

}