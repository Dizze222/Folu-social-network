package ch.b.retrofitandcoroutines.domain.all_posts


import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerListUI
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI

abstract class PhotographerListDomainToUIMapper :
    Abstract.Mapper.DomainToUi<List<PhotographerDomain>,PhotographerListUI>
