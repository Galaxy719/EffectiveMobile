package uz.developersdreams.effectivemobile.ui.air_tickets_ui

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
import uz.developersdreams.effectivemobile.adapter.air_tickets_ui.OffersRvAdapter
import uz.developersdreams.effectivemobile.databinding.FragmentAirTicketsBinding
import uz.developersdreams.effectivemobile.feature.extensions.navigate


@AndroidEntryPoint
class AirTicketsFragment : Fragment(R.layout.fragment_air_tickets) {
    private val binding by viewBinding<FragmentAirTicketsBinding>()
    private val viewModel by viewModels<AirTicketsViewModel>()
    private val offersRvAdapter by lazy { OffersRvAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializing()
        action()
        observeUiState()
    }

    private fun initializing() {
        binding.apply {
            rvOffers.adapter = offersRvAdapter
        }
    }

    private fun action() {
        binding.apply {
            tvFrom.setOnClickListener {
                navigate(R.id.action_airTicketsFragment_to_searchFragment)
            }
            tvTo.setOnClickListener {
                navigate(R.id.action_airTicketsFragment_to_searchFragment)
            }
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collectLatest { state ->
                offersRvAdapter.differ.submitList(state.offers)
                binding.apply {
                    tvFrom.text = state.fromText
                    tvTo.text = state.toText
                }
            }
        }
    }
}