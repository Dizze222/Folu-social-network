package ch.b.retrofitandcoroutines

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentScreen(private val fragment: Fragment) : SupportAppScreen() {

    override fun getFragment(): Fragment {
        return fragment
    }

    override fun getScreenKey(): String {
        return fragment.javaClass.canonicalName ?: "Unknown fragment name"
    }
}