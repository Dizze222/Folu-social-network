package ch.b.retrofitandcoroutines.domain

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.PhotographerUI

interface PhotographerDomainToUIMapper : Abstract.Mapper {
    fun map(id: Int, author: String, URL: String, like: Long, theme: String) : PhotographerUI
}