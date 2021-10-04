package ch.b.retrofitandcoroutines.ui.main.adapter

import ch.b.retrofitandcoroutines.data.model.UserDTO

interface AdapterOnclick {
    fun onClick(item: UserDTO)
}