package ua.eshcherbinock.reachabilitytest.module.sample

import ua.eshcherbinock.reachabilitytest.module.base.BaseContracts

object SampleModuleContracts {

    interface SampleViewType: BaseContracts.BaseViewType {

        fun setLoadingIndicatorState(show: Boolean)

        fun setReachabilityIndicatorState(show: Boolean)

        fun showLoadedData(result: String)

    }

    interface SamplePresenterType: BaseContracts.BasePresenterType <SampleViewType> {

        fun fetchData()

    }

}