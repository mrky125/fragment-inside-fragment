package com.example.fragmentinsidefragment.ui.epoxy.model

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.databinding.BR
import com.example.fragmentinsidefragment.R
import com.example.fragmentinsidefragment.viewmodel.EpoxyMultiPagingViewModel

@EpoxyModelClass(layout = R.layout.item_error)
abstract class ItemErrorModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    var viewModel: EpoxyMultiPagingViewModel? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.setVariable(BR.viewModel, viewModel)
    }
}