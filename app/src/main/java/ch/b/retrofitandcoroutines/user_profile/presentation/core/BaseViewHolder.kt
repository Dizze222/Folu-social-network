package ch.b.retrofitandcoroutines.user_profile.presentation.core

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<M, WB : ViewBinding> constructor(viewBinding: WB) :
    RecyclerView.ViewHolder(viewBinding.root) {

    private var item: M? = null

    fun doBindings(data: M?) {
        this.item = data
    }

    abstract fun bind()

    fun getRowItem(): M? {
        return item
    }
}