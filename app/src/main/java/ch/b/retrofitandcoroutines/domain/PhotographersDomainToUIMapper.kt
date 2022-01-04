package ch.b.retrofitandcoroutines.domain

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.presentation.PhotographersUI

interface PhotographersDomainToUIMapper : Abstract.Mapper{
    fun map(photographers: List<PhotographerParameters>) : PhotographersUI

    fun map(errorType: ErrorType) : PhotographersUI

}