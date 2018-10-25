package ua.eshcherbinock.reachabilitytest.model

interface FakeDataModelType {

    fun fetchFakeData(completion: (String) -> Unit)

}

class FakeDataModel: FakeDataModelType {

    override fun fetchFakeData(completion: (String) -> Unit) {
        
    }

}
