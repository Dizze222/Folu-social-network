package ch.b.retrofitandcoroutines.domain.all_posts


import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerListUI
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI

interface PhotographerListDomainToUIMapper : Abstract.Mapper {
    fun map(photographers: List<PhotographerDomain>): PhotographerListUI

    fun map(errorType: ErrorType): PhotographerListUI

    //fun map(): PhotographerListUI


}