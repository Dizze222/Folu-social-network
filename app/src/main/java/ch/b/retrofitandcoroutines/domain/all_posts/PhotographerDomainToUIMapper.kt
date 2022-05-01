package ch.b.retrofitandcoroutines.domain.all_posts

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI
import io.realm.RealmList

interface PhotographerDomainToUIMapper : Abstract.Mapper {
    fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: RealmList<String>,
        authorOfComments: RealmList<String>
    ): PhotographerUI
}