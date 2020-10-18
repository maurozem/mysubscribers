package ms.zem.mysubscribers.ui.subscriber

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.subscriber_fragment.*
import ms.zem.mysubscribers.R
import ms.zem.mysubscribers.data.db.AppDatabase
import ms.zem.mysubscribers.data.db.dao.SubscriberDAO
import ms.zem.mysubscribers.extension.hideKeyboard
import ms.zem.mysubscribers.repository.DatabaseDataSource
import ms.zem.mysubscribers.repository.SubscriberRepository

class SubscriberFragment : Fragment(R.layout.subscriber_fragment) {

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    AppDatabase.getInstance(requireContext()).subscriberDAO
                val repository: SubscriberRepository = DatabaseDataSource(subscriberDAO)
                return SubscriberViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setListners()
    }

    private fun setListners() {
        btn_add.setOnClickListener {
            val name = tiedt_name.text.toString()
            val email = tiedt_email.text.toString()

            viewModel.addSubscriber(name, email)
        }
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner){ subscribeState ->
            when (subscribeState) {
                is SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner){ stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity){
            parentActivity.hideKeyboard()
        }
    }

    private fun clearFields() {
        tiedt_name.text?.clear()
        tiedt_email.text?.clear()
    }


}