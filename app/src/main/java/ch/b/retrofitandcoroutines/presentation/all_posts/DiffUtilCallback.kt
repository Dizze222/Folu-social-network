package ch.b.retrofitandcoroutines.presentation.all_posts

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(
    private val oldList: List<PhotographerUI>,
    private val newList: List<PhotographerUI>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].sameContent(newList[newItemPosition])

}