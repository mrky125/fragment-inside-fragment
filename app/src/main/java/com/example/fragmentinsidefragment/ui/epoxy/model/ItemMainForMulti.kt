package com.example.fragmentinsidefragment.ui.epoxy.model

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.databinding.BR
import com.example.fragmentinsidefragment.R
import com.example.fragmentinsidefragment.model.MultiPaging
import com.example.fragmentinsidefragment.viewmodel.EpoxyMultiPagingViewModel

@EpoxyModelClass(layout = R.layout.item_main_for_multi)
abstract class ItemMainForMulti : DataBindingEpoxyModel() {

    @EpoxyAttribute
    var viewModel: EpoxyMultiPagingViewModel? = null

    @EpoxyAttribute
    var lifecycleOwner: LifecycleOwner? = null

    @EpoxyAttribute
    var itemMain: MultiPaging.MainItem? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.also {
            it.setVariable(BR.viewModel, viewModel)
            it.setVariable(BR.item, itemMain)
            it.lifecycleOwner = lifecycleOwner
        }
    }
}