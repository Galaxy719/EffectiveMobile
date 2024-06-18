package uz.developersdreams.effectivemobile.ui.show_all_tickets_ui

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.developersdreams.effectivemobile.R
import uz.developersdreams.effectivemobile.adapter.show_all_tickets_ui.ShowAllTicketRvAdapter
import uz.developersdreams.effectivemobile.databinding.FragmentShowAllTicketsBinding
import uz.developersdreams.effectivemobile.feature.extensions.navigateUp
import uz.developersdreams.effectivemobile.feature.util.Constants

@AndroidEntryPoint
class ShowAllTicketsFragment : Fragment(R.layout.fragment_show_all_tickets) {
    private val binding by viewBinding<FragmentShowAllTicketsBinding>()
    private val viewModel by viewModels<ShowAllTicketsViewModel>()
    private val ticketsAdapter by lazy { ShowAllTicketRvAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializing()
        action()
        observeState()
    }

    private fun initializing() {
        binding.apply {
            tvShowPlaces.text = arguments?.getString(Constants.PLACES_INFO)
            tvShowInfo.text = arguments?.getString(Constants.TICKET_INFO)
            rvShowTicket.adapter = ticketsAdapter
        }
    }

    private fun action() {
        binding.btnBackContainer.setOnClickListener {
            navigateUp()
        }
    }

    private fun observeState() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.uiState.collectLatest { state ->
            ticketsAdapter.differ.submitList(state.tickets)
        }
    }
}