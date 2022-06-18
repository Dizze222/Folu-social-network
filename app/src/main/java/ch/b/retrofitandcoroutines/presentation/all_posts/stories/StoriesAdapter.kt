package ch.b.retrofitandcoroutines.presentation.all_posts.stories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.data.all_posts.net.Stories
import ch.b.retrofitandcoroutines.data.all_posts.net.Story
import ch.b.retrofitandcoroutines.databinding.MyStoryItemBinding
import ch.b.retrofitandcoroutines.databinding.StoryItemBinding
import ch.b.retrofitandcoroutines.presentation.core.ImageLoad

class StoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var stories = emptyList<Story>()
        set(value) {
            notifyDataSetChanged()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val defaultView = StoryItemBinding.inflate(layoutInflater, parent, false)
        when (viewType) {
            Stories.DEFAULT -> {
                return StoriesViewHolder.DefaultStoryHolder(defaultView)
            }
            Stories.MY_STORY -> {
                val view = MyStoryItemBinding.inflate(layoutInflater, parent, false)
                return StoriesViewHolder.MyStoryHolder(view)
            }
        }
        return StoriesViewHolder.DefaultStoryHolder(defaultView)
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun getItemViewType(position: Int): Int {
        return stories[position].storyType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            Stories.DEFAULT -> (holder as StoriesViewHolder.DefaultStoryHolder).bind(stories[position])
            Stories.MY_STORY -> (holder as StoriesViewHolder.MyStoryHolder).bind(stories[position])
        }
    }

    abstract class StoriesViewHolder(
        view: ViewBinding,
    ) : RecyclerView.ViewHolder(view.root) {
        open fun bind(story: Story) = Unit

        class DefaultStoryHolder(binding: StoryItemBinding) :
            StoriesViewHolder(binding) {
            private val textViewProfileName = binding.textViewProfileName
            private val imageViewProfile = binding.imageViewProfile

            override fun bind(story: Story) {
                val context = itemView.context
                if (story.hasUncheckedStory) {
                    imageViewProfile.borderWidth =
                        context.resources.getDimension(R.dimen.story_border_width).toInt()
                    imageViewProfile.borderColor =
                        context.resources.getColor(R.color.story_border_color)
                }
                textViewProfileName.text = story.profileName
                ImageLoad.Base(story.profileImageUrl!!).load(imageViewProfile)
            }
        }

        class MyStoryHolder(binding: MyStoryItemBinding) : StoriesViewHolder(binding) {
            private val textViewProfileName = binding.textViewProfileName
            private val imageViewProfile = binding.imageViewMyProfile
            private val imageViewAddStory = binding.imageViewAddStory

            override fun bind(story: Story) {
                val context = itemView.context
                if (story.hasUncheckedStory) {
                    imageViewProfile.borderWidth =
                        context.resources.getDimension(R.dimen.story_border_width).toInt()
                    imageViewProfile.borderColor =
                        context.resources.getColor(R.color.story_border_color)
                    imageViewAddStory.visibility = View.INVISIBLE
                }
                ImageLoad.Base(story.profileImageUrl!!).load(imageViewProfile)
                textViewProfileName.text = context.getString(R.string.your_story)
            }
        }
    }
}

