package ms.zem.mysubscribers.ui.subscriber

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ms.zem.mysubscribers.R
import ms.zem.mysubscribers.repository.SubscriberRepository

class SubscriberViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    private val _subscriberStateEventData = MutableLiveData<SubscriberState>()
    val subscriberStateEventData: LiveData<SubscriberState>
        get() = _subscriberStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addSubscriber(name: String, email: String) = viewModelScope.launch {
        try {
            val id = repository.insertSubscriber(name, email)
            if ( id > 0 ){
                _subscriberStateEventData.value = SubscriberState.Inserted
                _messageEventData.value = R.string.subscriber_inserted_sucessfully
            } else {
                _messageEventData.value = R.string.subscriber_error_to_insert
            }
        } catch (ex: Exception){
            Log.e(TAG, ex.toString())
            _messageEventData.value = R.string.subscriber_error_to_insert
        }
    }

    sealed class SubscriberState{
        object Inserted : SubscriberState()
    }

    companion object{
        private val TAG = SubscriberViewModel::class.java.simpleName
    }
}