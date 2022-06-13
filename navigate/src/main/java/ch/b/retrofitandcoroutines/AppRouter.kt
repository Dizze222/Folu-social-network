package ch.b.retrofitandcoroutines


import ru.terrakok.cicerone.Router


class AppRouter : Router() {

    fun replaceTab(screen: FragmentScreen) {
        val command = ReplaceBottomTab(screen)
        executeCommands(command)
    }
}