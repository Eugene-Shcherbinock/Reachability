package ua.eshcherbinock.reachabilitytest.module.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity <in V: BaseContracts.BaseViewType, P: BaseContracts.BasePresenterType<V>>:
        AppCompatActivity(), BaseContracts.BaseViewType {

    /**
     * Properties
     */

    protected abstract var mPresenter: P
    protected abstract var mLayoutId: Int

    /**
     * Activity lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayoutId)

        updateDependencies()

        mPresenter.onCreate(this as V, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }

    /**
     * BaseViewType implementation
     */

    override fun getContext(): Context = this

    /**
     * Protected
     */

    protected open fun updateDependencies() {}

}