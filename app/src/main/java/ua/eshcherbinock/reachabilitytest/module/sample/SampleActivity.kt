package ua.eshcherbinock.reachabilitytest.module.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import ua.eshcherbinock.reachabilitytest.R
import ua.eshcherbinock.reachabilitytest.module.base.BaseActivity

class SampleActivity : BaseActivity<SampleModuleContracts.SampleViewType, SampleModuleContracts.SamplePresenterType>(),
        SampleModuleContracts.SampleViewType {

    /**
     * Properties
     */

    override var mPresenter: SampleModuleContracts.SamplePresenterType = SamplePresenter()

    override var mLayoutId: Int = R.layout.activity_sample

    private lateinit var mTextViewResult: TextView
    private lateinit var mReachabilityIndicator: Snackbar

    /**
     * Activity lifecycle
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.fetchData()
    }

    /**
     * SampleViewType implementation
     */

    override fun showLoadedData(result: String) {
        mTextViewResult.visibility = View.VISIBLE
        mTextViewResult.text = result
    }

    override fun setLoadingIndicatorState(show: Boolean) {
        findViewById<ProgressBar>(R.id.progress_bar).visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun setReachabilityIndicatorState(show: Boolean) {
        with (mReachabilityIndicator) {
            if (show)
                show()
            else
                dismiss()
        }
    }

    /**
     * BaseActivity
     */

    override fun updateDependencies() {
        super.updateDependencies()

        mTextViewResult = findViewById(R.id.text_view_result)
        mReachabilityIndicator = Snackbar.make(mTextViewResult, "No Internet connection", Snackbar.LENGTH_INDEFINITE)
    }

}
