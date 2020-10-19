package ms.zem.mysubscribers.ui.subscriberlist

import androidx.lifecycle.ViewModel
import ms.zem.mysubscribers.repository.SubscriberRepository

class SubscriberListViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    val allSubscriberEvent = repository.getAllSubscribers()

}