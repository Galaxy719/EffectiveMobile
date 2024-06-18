package uz.developersdreams.effectivemobile.ui.search_ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.developersdreams.effectivemobile.R
import uz.developersdreams.effectivemobile.adapter.search_ui.RecommendedRvAdapter
import uz.developersdreams.effectivemobile.adapter.search_ui.SearchItemAdapter
import uz.developersdreams.effectivemobile.databinding.FragmentSearchBinding
import uz.developersdreams.effectivemobile.feature.domain.model.recommended_place.RecommendedPlace
import uz.developersdreams.effectivemobile.feature.domain.model.search_item.SearchItem
import uz.developersdreams.effectivemobile.feature.extensions.navigate
import uz.developersdreams.effectivemobile.feature.extensions.showHideView

@AndroidEntryPoint
class SearchFragment : BottomSheetDialogFragment(R.layout.fragment_search) {
    private val binding by viewBinding<FragmentSearchBinding>()
    private val viewModel by viewModels<SearchViewModel>()
    private val searchItemAdapter by lazy { SearchItemAdapter() }
    private val recommendedPlaceAdapter by lazy { RecommendedRvAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializing()
        action()
        setUserText()
        tempItemList()
    }

    private fun initializing() {
        binding.apply {
            rvButtons.adapter = searchItemAdapter
            rvRecommendedPlace.adapter = recommendedPlaceAdapter
        }
    }

    private fun action() {
        binding.apply {
            tvFrom.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    btnFromClearText.showHideView(!p0.isNullOrBlank())
                    viewModel.onEvent(SearchEvent.FromText(p0.toString()))
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

            tvTo.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    btnToClearText.showHideView(!p0.isNullOrBlank())
                    viewModel.onEvent(SearchEvent.ToText(p0.toString()))
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

            btnFromClearText.setOnClickListener {
                tvFrom.setText("")
            }

            btnToClearText.setOnClickListener {
                tvTo.setText("")
            }

            searchItemAdapter.setOnItemClickListener(object : SearchItemAdapter.OnClickListener{
                override fun onItemClicked(searchItem: SearchItem) {
                    navigate(R.id.action_searchFragment_to_searchSelectedCountryFragment)
                }
            })

            recommendedPlaceAdapter.setOnItemClickListener(object : RecommendedRvAdapter.OnClickListener{
                override fun onItemClicked(item: RecommendedPlace) {
                    tvTo.setText(item.name)
                    viewModel.onEvent(SearchEvent.ToText(item.name))
                    navigate(R.id.action_searchFragment_to_searchSelectedCountryFragment)
                }
            })
        }
    }

    private fun setUserText() = lifecycleScope.launch {
        viewModel.uiState.collectLatest { state ->
            state.userTextFrom?.let {
                binding.tvFrom.setText(it.text)
            }
            state.userTextTo?.let {
                binding.tvTo.setText(it.text)
            }
        }
    }

    private fun tempItemList() = lifecycleScope.launch {
        val item = listOf(
            SearchItem(R.drawable.ic_complex_route, getString(R.string.complexRoute)),
            SearchItem(R.drawable.ic_anywhere, getString(R.string.anywhere)),
            SearchItem(R.drawable.ic_weekend, getString(R.string.weekend)),
            SearchItem(R.drawable.ic_hot_tickets, getString(R.string.hotTickets)),
        )
        val list = listOf(
            RecommendedPlace(R.drawable.img_istanbul, "Стамбул", "Популярное направление"),
            RecommendedPlace(R.drawable.img_sochi, "Сочи", "Популярное направление"),
            RecommendedPlace(R.drawable.img_phuket, "Пхукет", "Популярное направление"),
        )

        searchItemAdapter.differ.submitList(item)
        recommendedPlaceAdapter.differ.submitList(list)
    }
}