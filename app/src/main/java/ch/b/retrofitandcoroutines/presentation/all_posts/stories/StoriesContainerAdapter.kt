package ch.b.retrofitandcoroutines.presentation.all_posts.stories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.data.all_posts.net.Story
import kotlinx.android.synthetic.main.stories_container.view.*

open class StoriesContainerAdapter: RecyclerView.Adapter<StoriesContainerAdapter.StoriesContainerHolder>() {


    private var stories = emptyList<Story>()
    fun stories(list: List<Story>) {
        stories = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesContainerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stories_container, parent, false)
        return StoriesContainerHolder(view)
    }

    override fun getItemCount(): Int {
        return COUNT_OF_STORIES
    }

    override fun onBindViewHolder(holder: StoriesContainerHolder, position: Int) {
        holder.bind()
    }

    inner class StoriesContainerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recyclerViewStories = itemView.recyclerViewStories

       fun bind(){
            val storiesAdapter = StoriesAdapter()
            storiesAdapter.stories = stories
            recyclerViewStories.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerViewStories.setHasFixedSize(true)
            recyclerViewStories.adapter = storiesAdapter
        }
    }

    companion object{
        private const val COUNT_OF_STORIES = 1
    }
}