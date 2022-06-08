package ch.b.retrofitandcoroutines.core


fun List<Abstract.Object<Unit, BaseSingleRegistrationStringMapper.SingleStringMapper>>.logTo(): ArrayList<String> {
    val array = ArrayList<String>()
    this.map {
        it.map(object : BaseSingleRegistrationStringMapper.SingleStringMapper {
            override fun map(
                accessToken: String,
                refreshToken: String,
                successRegister: Boolean
            ) {
                array.add(accessToken)
                array.add(refreshToken)
            }

            override fun map(message: String) {
                array.add(message)
            }

        })
    }
    return array

}

fun List<Abstract.Object<Unit, BasePhotographerStringMapper.SingleStringMapper>>.getPosts(): ArrayList<String> {
    val array = ArrayList<String>()
    this.map {
        it.map(object : BasePhotographerStringMapper.SingleStringMapper {
            override fun map(
                id: Int,
                author: String,
                URL: String,
                like: Long,
                theme: String,
                comments: List<String>,
                authorOfComments: List<String>
            ) {
                array.add(id.toString())
                array.add(author)
                array.add(URL)
                array.add(like.toString())
                array.add(theme)
                array.add(comments.toString())
                array.add(authorOfComments.toString())
            }

            override fun map(message: String) {

            }

        })
    }
    return array
}


fun List<String>.convertToArrayList(): ArrayList<String> {
    val someList = arrayListOf<String>()
    for (i in this) {
        someList.add(i)
    }
    return someList
}


