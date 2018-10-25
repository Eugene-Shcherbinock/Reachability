package ua.eshcherbinock.reachabilitytest.module.fake

import ua.eshcherbinock.reachabilitytest.module.base.BaseContracts

object FakeModuleContracts {

    interface FakeViewType: BaseContracts.BaseViewType {

        fun setReachabilityIndicatorState(show: Boolean)

        fun showLoadedData(result: String)

    }

    interface FakePresenterType: BaseContracts.BasePresenterType <FakeViewType> {

        fun fetchData()

    }

}