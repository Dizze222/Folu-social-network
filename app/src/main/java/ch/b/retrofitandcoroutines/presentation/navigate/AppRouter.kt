package ch.b.retrofitandcoroutines.presentation.navigate

import ch.b.retrofitandcoroutines.presentation.container_screens.FragmentScreen
import ru.terrakok.cicerone.Router


class AppRouter : Router() {

    fun replaceTab(screen: FragmentScreen) {
        val command = ReplaceBottomTab(screen)
        executeCommands(command)
    }
}