package ch.b.retrofitandcoroutines.ui.main.adapter

import ch.b.retrofitandcoroutines.data.model.UserDTO


interface AdapterOnClick {
    fun onClick(item: UserDTO)


}