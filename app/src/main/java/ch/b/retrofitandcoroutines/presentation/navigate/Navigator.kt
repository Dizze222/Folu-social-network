package ch.b.retrofitandcoroutines.presentation.navigate

import android.content.Context
import ch.b.retrofitandcoroutines.core.Read
import ch.b.retrofitandcoroutines.core.Save

interface Navigator : Save<Int>, Read<Int> {

    class Base(context: Context) : Navigator {

        private val sharedPreferences =
            context.getSharedPreferences(NAVIGATOR_FILE_NAME, Context.MODE_PRIVATE)

        override fun save(data: Int) {
            sharedPreferences.edit().putInt(CURRENT_SCREEN_KEY, data).apply()
        }

        override fun read(): Int {
            return sharedPreferences.getInt(CURRENT_SCREEN_KEY, 0)
        }

        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigation"
            const val CURRENT_SCREEN_KEY = "screenId"
        }
    }
}

class Screens {
    companion object{
        const val ALL_PHOTOGRAPHERS = 0
        const val CERTAIN_POST = 1
    }
}