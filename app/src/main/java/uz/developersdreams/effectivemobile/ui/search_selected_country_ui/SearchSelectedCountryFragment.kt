package uz.developersdreams.effectivemobile.ui.search_selected_country_ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.developersdreams.effectivemobile.R
import uz.developersdreams.effectivemobile.adapter.search_selected_ui.TicketsOffersRvAdapter
import uz.developersdreams.effectivemobile.databinding.FragmentSearchSelectedCountryBinding
import uz.developersdreams.effectivemobile.feature.extensions.formatDate
import uz.developersdreams.effectivemobile.feature.extensions.navigate
import uz.developersdreams.effectivemobile.feature.extensions.navigateUp
import uz.developersdreams.effectivemobile.feature.extensions.showHideView
import uz.developersdreams.effectivemobile.feature.util.Constants
import uz.developersdreams.effectivemobile.feature.util.getDate
import uz.developersdreams.effectivemobile.feature.util.setViewGone
import java.util.Calendar

@AndroidEntryPoint
class SearchSelectedCountryFragment : Fragment(R.layout.fragment_search_selected_country) {
    private val binding by viewBinding<FragmentSearchSelectedCountryBinding>()
    private val viewModel by viewModels<SearchSelectedViewModel>()
    private val ticketsOffersRvAdapter by lazy { TicketsOffersRvAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializing()
        action()
        observeState()
    }

    private fun initializing() {
        binding.apply {
            tvDepartureDate.text = getDate()
            rvDirectFlights.adapter = ticketsOffersRvAdapter
            with(viewModel.uiState.value) {
                this.tvBackText?.let {
                    tvBack.text = it
                    setViewGone(imgChipsAdd)
                }
                this.tvDepartureText?.let {
                    tvDepartureDate.text = it
                }
            }
        }
    }

    private fun action() {
        binding.apply {
            btnBackContainer.setOnClickListener { navigateUp() }

            tvFrom.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    showHideChangeBtn()
                    viewModel.onEvent(SearchSelectedEvent.FromText(p0.toString()))
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

            tvTo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    btnToClearText.showHideView(!p0.isNullOrBlank())
                    showHideChangeBtn()
                    viewModel.onEvent(SearchSelectedEvent.ToText(p0.toString()))
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

            btnChange.setOnClickListener {
                val tempText = tvFrom.text
                tvFrom.text = tvTo.text
                tvTo.text = tempText
            }

            btnToClearText.setOnClickListener {
                tvTo.setText("")
            }

            tvBackContainer.setOnClickListener {
                showDatePicker {
                    tvBack.text = it
                    setViewGone(imgChipsAdd)
                    viewModel.onEvent(SearchSelectedEvent.TvBackText(it))
                }
            }

            tvDepartureDateContainer.setOnClickListener {
                showDatePicker {
                    tvDepartureDate.text = it
                    viewModel.onEvent(SearchSelectedEvent.TvDepartureText(it))
                }
            }

            btnSeeAllTickets.setOnClickListener {
                val bundle = bundleOf(
                    Constants.PLACES_INFO to (
                        "${tvFrom.text}-${tvTo.text}"
                    ),
                    Constants.TICKET_INFO to (
                        "${tvDepartureDate.text}, 1 пассажир"
                    )
                )
                navigate(R.id.action_searchSelectedCountryFragment_to_showAllTicketsFragment, bundle)
            }
        }
    }

    private fun showHideChangeBtn() {
        with(binding) {
            btnChange.showHideView(!tvFrom.text.isNullOrBlank() && !tvTo.text.isNullOrBlank())
        }
    }

    private fun showDatePicker(date: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(requireContext(), { _, year: Int, month: Int, day: Int ->
            val selected = Calendar.getInstance()
            selected.set(year, month, day)
            val formatted = selected.formatDate()
            date(formatted)
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun observeState() = viewLifecycleOwner.lifecycleScope.launch {
        viewModel.uiState.collectLatest { state ->
            state.userTextFrom?.let {
                binding.tvFrom.setText(it.text)
            }
            state.userTextTo?.let {
                binding.tvTo.setText(it.text)
            }

            ticketsOffersRvAdapter.differ.submitList(state.ticketOffers.take(3))
        }
    }
}