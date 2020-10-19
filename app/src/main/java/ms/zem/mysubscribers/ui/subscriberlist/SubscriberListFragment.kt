package ms.zem.mysubscribers.ui.subscriberlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.subscriber_list_fragment.*
import ms.zem.mysubscribers.R
import ms.zem.mysubscribers.data.db.entity.SubscriberEntity

class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {

    private lateinit var viewModel: SubscriberListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subscriberListAdapter = SubscriberListAdapter(
            listOf(
                SubscriberEntity(1, "Mauro Zem", "mauro@zem.ms"),
                SubscriberEntity(2, "Marluci Zem", "marluci@zem.ms"),
                SubscriberEntity(3, "Mauricio Zem", "mauricio@zem.ms"),
                SubscriberEntity(4, "Mariane Zem", "mariane@zem.ms")
            )
        )

        rv_subscribers.run {
            setHasFixedSize(true)
            adapter = subscriberListAdapter
        }

    }

}