package com.example.fragmentinsidefragment.ui.epoxy.model

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.databinding.BR
import com.example.fragmentinsidefragment.R

@EpoxyModelClass(layout = R.layout.item_header)
abstract class ItemHeaderModel: DataBindingEpoxyModel() {

    @EpoxyAttribute var lifecycleOwner: LifecycleOwner? = null
    @EpoxyAttribute var item: LiveData<String>? = null
    @EpoxyAttribute var descriptionText: String? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_header
    }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.lifecycleOwner = lifecycleOwner
        lifecycleOwner?.also {
            item?.observe(it, { newStr ->
                binding?.setVariable(BR.descriptionText, newStr)
            })
        }
    }
}