package ch.b.retrofitandcoroutines.presentation.navigate

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ch.b.retrofitandcoroutines.ReplaceBottomTab
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
        val fragmentManager = fragmentManager ?: return
        val allPostTabContainer = fragmentManager.findFragmentByTag(AllPostTabContainer.TAG) as? AllPostTabContainer
            ?: AllPostTabContainer.newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId, allPostTabContainer, AllPostTabContainer.TAG)
            .detach(allPostTabContainer)
            .commitNow()

        val likedTabContainer = fragmentManager.findFragmentByTag(LikedTabContainer.TAG) as? LikedTabContainer
            ?: LikedTabContainer.newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId, likedTabContainer, LikedTabContainer.TAG)
            .detach(likedTabContainer)
            .commitNow()

        val registrationContainer = fragmentManager.findFragmentByTag(RegistrationContainer.TAG) as? RegistrationContainer
            ?: RegistrationContainer.newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId,registrationContainer, RegistrationContainer.TAG)
            .detach(registrationContainer)
            .commitNow()

        val splashContainer = fragmentManager.findFragmentByTag(SplashContainer.TAG) as? SplashContainer
            ?: SplashContainer.newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId,splashContainer, SplashContainer.TAG)
            .detach(splashContainer)
            .commitNow()


        val authorizationContainer = fragmentManager.findFragmentByTag(AuthorizationContainer.TAG) as? AuthorizationContainer
            ?: AuthorizationContainer.newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId,authorizationContainer,AuthorizationContainer.TAG)
            .detach(authorizationContainer)
            .commitNow()


        val searchContainer = fragmentManager.findFragmentByTag(SearchTabContainer.TAG) as? SearchTabContainer
            ?: SearchTabContainer.newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId,searchContainer,SearchTabContainer.TAG)
            .detach(searchContainer)
            .commitNow()

        val userProfileContainer = fragmentManager.findFragmentByTag(UserProfileContainer.TAG) as? UserProfileContainer
            ?: UserProfileContainer.newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId,userProfileContainer,UserProfileContainer.TAG)
            .detach(userProfileContainer)
            .commitNow()

        val picContainer = fragmentManager.findFragmentByTag(PicContainer.TAG) as? PicContainer
            ?:PicContainer.newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId,picContainer,PicContainer.TAG)
            .detach(picContainer)
            .commitNow()

        val bottomContainer = fragmentManager.findFragmentByTag(BottomContainer.TAG) as? BottomContainer
            ?: BottomContainer.newInstance()
        fragmentManager.beginTransaction()
            .replace(containerId,bottomContainer,BottomContainer.TAG)
            .detach(bottomContainer)
            .commitNow()


        containers.add(allPostTabContainer)
        containers.add(likedTabContainer)
        containers.add(registrationContainer)
        containers.add(splashContainer)
        containers.add(authorizationContainer)
        containers.add(searchContainer)
        containers.add(userProfileContainer)
        containers.add(picContainer)
        containers.add(bottomContainer)
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