package ms.zem.mysubscribers.ui.subscriberlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.subscriber_list_fragment.*
import ms.zem.mysubscribers.R
import ms.zem.mysubscribers.data.db.AppDatabase
import ms.zem.mysubscribers.data.db.dao.SubscriberDAO
import ms.zem.mysubscribers.data.db.entity.SubscriberEntity
import ms.zem.mysubscribers.repository.DatabaseDataSource
import ms.zem.mysubscribers.repository.SubscriberRepository
import ms.zem.mysubscribers.ui.subscriber.SubscriberViewModel

class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    AppDatabase.getInstance(requireContext()).subscriberDAO
                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelEvents()
    }

    private fun observeViewModelEvents() {
        viewModel.allSubscriberEvent.observe(viewLifecycleOwner){ subscribers ->

            val subscriberListAdapter = SubscriberListAdapter(subscribers)

            rv_subscribers.run {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }
        }
    }

}