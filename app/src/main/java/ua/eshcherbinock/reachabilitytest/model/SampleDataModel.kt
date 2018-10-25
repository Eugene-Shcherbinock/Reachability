package ua.eshcherbinock.reachabilitytest.model

import android.os.Handler
import android.os.Looper
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

interface SampleDataModelType {

    fun fetchFakeData(completion: (String) -> Unit)

}

class SampleDataModel: SampleDataModelType {

    override fun fetchFakeData(completion: (String) -> Unit) {
        thread {
            TimeUnit.SECONDS.sleep(3)
            Handler(Looper.getMainLooper()).post {
                completion("Data fetched successful!")
            }
        }
    }

}
