package ua.eshcherbinock.reachabilitytest.module.base

import android.os.Bundle

interface BasePresenterType <in V: BaseViewType> {

    fun onCreate(savedInstanceState: Bundle?)

    fun onStart()

    fun onStop()

    fun onDestroy()

}