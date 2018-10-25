package ua.eshcherbinock.reachabilitytest.module.base

import android.os.Bundle


open class BasePresenter <V: BaseContracts.BaseViewType>: BaseContracts.BasePresenterType<V> {

    /**
     * Properties
     */

    protected var mView: V? = null

    /**
     * BasePresenterType implementation
     */

    override fun onCreate(view: V, withSavedInstanceState: Bundle?) {
        mView = view
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
        mView = null
    }

}