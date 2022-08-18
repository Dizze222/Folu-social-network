package ch.b.retrofitandcoroutines.all_posts.domain


import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.presentation.PhotographerListUI

abstract class PhotographerListDomainToUIMapper :
    Abstract.Mapper.DomainToUi<List<PhotographerDomain>, PhotographerListUI>
