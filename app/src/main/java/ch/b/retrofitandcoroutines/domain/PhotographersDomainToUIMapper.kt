package ch.b.retrofitandcoroutines.domain

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.PhotographerListUI

interface PhotographersDomainToUIMapper : Abstract.Mapper{
    fun map(photographers: List<PhotographerDomain>) : PhotographerListUI

    fun map(errorType: ErrorType) : PhotographerListUI

    fun map() : PhotographerListUI
}