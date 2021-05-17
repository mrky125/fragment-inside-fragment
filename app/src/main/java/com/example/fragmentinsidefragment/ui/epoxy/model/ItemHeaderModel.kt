package com.example.fragmentinsidefragment.ui.epoxy.model

import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.databinding.BR
import com.example.fragmentinsidefragment.R
import com.example.fragmentinsidefragment.viewmodel.EpoxyViewModel

@EpoxyModelClass(layout = R.layout.item_header)
abstract class ItemHeaderModel: DataBindingEpoxyModel() {

    @EpoxyAttribute var viewModel: EpoxyViewModel? = null
    @EpoxyAttribute var lifecycleOwner: LifecycleOwner? = null
    @EpoxyAttribute var item: LiveData<String>? = null
    @EpoxyAttribute var descriptionText: String? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_header
    }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        Log.d("item", "binding variables")
        binding?.apply {
            this.lifecycleOwner = lifecycleOwner
            setVariable(BR.viewModel, viewModel)
            setVariable(BR.descriptionText, item?.value)
        }
        lifecycleOwner?.also {
            item?.observe(it, { newStr ->
                binding?.setVariable(BR.descriptionText, newStr)
            })
        }
    }

    override fun bind(holder: DataBindingHolder) {
        super.bind(holder)
        Log.d("item", "bind, holder: $holder")
    }
}