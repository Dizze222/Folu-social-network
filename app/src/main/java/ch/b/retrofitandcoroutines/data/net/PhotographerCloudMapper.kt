package ch.b.retrofitandcoroutines.data.net

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.PhotographerParameters

interface PhotographerCloudMapper : Abstract.Mapper {
    fun map(id: Int,author: String,URL: String) : PhotographerParameters

class Base : PhotographerCloudMapper {
    override fun map(id: Int, author: String,URL: String) : PhotographerParameters{
      return  PhotographerParameters(id, author,URL)
    }
}
}