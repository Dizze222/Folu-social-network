package ch.b.retrofitandcoroutines.data.all_posts.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.data.all_posts.PhotographerData
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerListDomain
abstract class PhotographerListDataToDomainMapper : Abstract.Mapper.DataToDomain<List<PhotographerData>,PhotographerListDomain>