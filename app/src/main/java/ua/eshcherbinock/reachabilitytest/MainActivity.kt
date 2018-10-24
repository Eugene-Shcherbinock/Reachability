package ua.eshcherbinock.reachabilitytest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ua.eshcherbinock.reachability.Reachability
import ua.eshcherbinock.reachability.abstraction.ReachabilityNotifierType
import ua.eshcherbinock.reachability.abstraction.ReachabilityObserverType

class MainActivity : AppCompatActivity() {

    private lateinit var mReachability: ReachabilityNotifierType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mReachability = Reachability.getInstance(this)
        mReachability.addListener(object : ReachabilityObserverType {

            override fun onReachabilityChange(newState: Reachability.State) {
                Toast.makeText(this@MainActivity, "State: $newState", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onStart() {
        super.onStart()
        mReachability.startNotifier()
    }

    override fun onStop() {
        super.onStop()
        mReachability.stopNotifier()
    }

}
