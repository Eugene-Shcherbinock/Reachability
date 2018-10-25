package ua.eshcherbinock.reachabilitytest.module.base

import android.content.Context
import android.os.Bundle

object BaseContracts {

    interface BaseViewType {

        fun getContext(): Context

    }

    interface BasePresenterType <in V: BaseViewType> {

        fun onCreate(view: V, withSavedInstanceState: Bundle? = null)

        fun onStart()

        fun onStop()

        fun onDestroy()

    }

}