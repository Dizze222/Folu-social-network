package ch.b.retrofitandcoroutines.presentation.navigate

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ch.b.retrofitandcoroutines.presentation.containers.*
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import java.util.*


class AppNavigator(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    @IdRes containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    constructor(activity: FragmentActivity, @IdRes containerId: Int) : this(
        activity,
        activity.supportFragmentManager,
        containerId
    )

    private val containers = LinkedList<BaseFragmentContainer>()

    fun initContainers() {
        val fm = fragmentManager ?: return
        val allPostTabContainer = fm.findFragmentByTag(AllPostTabContainer.TAG) as? AllPostTabContainer
            ?: AllPostTabContainer().newInstance()
        fm.beginTransaction()
            .replace(containerId, allPostTabContainer, AllPostTabContainer.TAG)
            .detach(allPostTabContainer)
            .commitNow()

        val likedTabContainer = fm.findFragmentByTag(LikedTabContainer.TAG) as? LikedTabContainer
            ?: LikedTabContainer().newInstance()
        fm.beginTransaction()
            .replace(containerId, likedTabContainer, LikedTabContainer.TAG)
            .detach(likedTabContainer)
            .commitNow()

        val registrationContainer = fm.findFragmentByTag(RegistrationContainer.TAG) as? RegistrationContainer
            ?: RegistrationContainer().newInstance()
        fm.beginTransaction()
            .replace(containerId,registrationContainer, RegistrationContainer.TAG)
            .detach(registrationContainer)
            .commitNow()

        val splashContainer = fm.findFragmentByTag(SplashContainer.TAG) as? SplashContainer
            ?: SplashContainer().newInstance()
        fm.beginTransaction()
            .replace(containerId,splashContainer, SplashContainer.TAG)
            .detach(splashContainer)
            .commitNow()


        val authorizationContainer = fm.findFragmentByTag(AuthorizationContainer.TAG) as? AuthorizationContainer
            ?: AuthorizationContainer().newInstance()
        fm.beginTransaction()
            .replace(containerId,authorizationContainer,AuthorizationContainer.TAG)
            .detach(authorizationContainer)
            .commitNow()

        containers.add(allPostTabContainer)
        containers.add(likedTabContainer)
        containers.add(registrationContainer)
        containers.add(splashContainer)
        containers.add(authorizationContainer)
    }

    override fun applyCommand(command: Command) {
        if (command is ReplaceBottomTab) {
            val transaction = fragmentManager?.beginTransaction() ?: return
            var wasContainerAttached = false
            containers.forEach { container ->
                if (container.getContainerName() == command.screen.screenKey) {
                    transaction.attach(container)
                    wasContainerAttached = true
                } else {
                    transaction.detach(container)
                }
            }
            if (!wasContainerAttached) {
                throw RuntimeException("Container = ${command.screen.screenKey} not found!")
            }
            transaction.commitNow()
        } else {
            super.applyCommand(command)
        }
    }

}