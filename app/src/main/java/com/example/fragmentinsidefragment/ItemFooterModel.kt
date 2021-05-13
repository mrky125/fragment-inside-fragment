package com.example.fragmentinsidefragment

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.databinding.BR

@EpoxyModelClass(layout = R.layout.item_footer)
abstract class ItemFooterModel : DataBindingEpoxyModel() {

    @EpoxyAttribute var footer: String? = null

    override fun getDefaultLayout(): Int {
        return R.layout.item_footer
    }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.setVariable(BR.footerText, footer)
    }
}