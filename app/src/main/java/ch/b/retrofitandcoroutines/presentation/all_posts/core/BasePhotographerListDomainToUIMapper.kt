package ch.b.retrofitandcoroutines.presentation.all_posts.core

import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomain
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomainToUIMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomainToUIMapper
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerListUI
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI

class BasePhotographerListDomainToUIMapper(
    private val photographerMapper: PhotographerDomainToUIMapper<PhotographerUI>
) : PhotographerListDomainToUIMapper() {
    override fun map(data: List<PhotographerDomain>): PhotographerListUI =
        PhotographerListUI.Success(data, photographerMapper)


    override fun map(error: String): PhotographerListUI =
        PhotographerListUI.Fail(error)

    override fun map(): PhotographerListUI = PhotographerListUI.EmptyData

}