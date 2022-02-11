package ch.b.retrofitandcoroutines.presentation

import ch.b.retrofitandcoroutines.domain.PhotographerDomainToUIMapper

class BasePhotographerDomainToUIMapper : PhotographerDomainToUIMapper{
    override fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String
    ): PhotographerUI = PhotographerUI.Base(id, author, URL, like, theme)
}