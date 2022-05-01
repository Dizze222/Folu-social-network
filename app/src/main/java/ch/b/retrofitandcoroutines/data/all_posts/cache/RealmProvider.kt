package ch.b.retrofitandcoroutines.data.all_posts.cache

import io.realm.Realm

interface RealmProvider {
    fun provide() : Realm
    class Base : RealmProvider {
        override fun provide(): Realm = Realm.getDefaultInstance()
    }
}