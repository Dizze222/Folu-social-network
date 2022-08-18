package ch.b.retrofitandcoroutines.all_posts.data.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.data.PhotographerData
import ch.b.retrofitandcoroutines.all_posts.domain.PhotographerListDomain
abstract class PhotographerListDataToDomainMapper : Abstract.Mapper.DataToDomain<List<PhotographerData>, PhotographerListDomain>