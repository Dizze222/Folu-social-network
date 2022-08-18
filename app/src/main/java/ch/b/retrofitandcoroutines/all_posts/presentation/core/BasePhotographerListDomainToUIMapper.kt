package ch.b.retrofitandcoroutines.all_posts.presentation.core

import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerDomain
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographerListUI
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographerUI

class BasePhotographerListDomainToUIMapper(
    private val photographerMapper: PhotographerDomainToUIMapper<PhotographerUI>
) : PhotographerListDomainToUIMapper() {
    override fun map(data: List<PhotographerDomain>): PhotographerListUI =
        PhotographerListUI.Success(data, photographerMapper)


    override fun map(error: String): PhotographerListUI =
        PhotographerListUI.Fail(error)

    override fun map(): PhotographerListUI = PhotographerListUI.EmptyData

}