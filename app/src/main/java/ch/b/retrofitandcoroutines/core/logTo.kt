package ch.b.retrofitandcoroutines.core

fun List<Abstract.Object<Unit, BaseSingleStringMapper.SingleStringMapper>>.logTo(): ArrayList<String> {
    val array = ArrayList<String>()
    this.map {
        it.map(object : BaseSingleStringMapper.SingleStringMapper {
            override fun map(
                accessToken: String,
                refreshToken: String,
                successRegister: Boolean
            ) {
                array.add(accessToken)
                array.add(refreshToken)
                array.add(successRegister.toString())
            }

            override fun map(message: String) {
                array.add(message)
            }

        })
    }
    return array
}