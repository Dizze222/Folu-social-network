package ch.b.retrofitandcoroutines.authorization

import ch.b.retrofitandcoroutines.authorization.data.net.authorization.AuthenticationCloudDataSource
import ch.b.retrofitandcoroutines.utils.core_network.ExceptionAuthMapper
import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationCloud
import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationData
import ch.b.retrofitandcoroutines.utils.core_network.authorization.AuthorizationListData
import ch.b.retrofitandcoroutines.utils.core_network.authorization.BaseRepositoryAuth
import ch.b.retrofitandcoroutines.utils.core_network.authorization.cache.TokenToSharedPreferences
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.AuthorizationListCloudMapper
import ch.b.retrofitandcoroutines.utils.core_network.authorization.mappers.ToAuthorizationMapper
import ch.b.retrofitandcoroutines.registration.data.net.RegistrationCloudDataSource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AuthorizationRepositoryTest {
    private val unknownHostException = Exception()
    private val testAuthCloudDataSource = TestAuthCloudDataSource(true)
    private val testRegistrationCloudDataSource = TestRegistrationDataSource(true)
    private val testCloudMapper = AuthorizationListCloudMapper.Base(ToAuthorizationMapper())
    private val repository = BaseRepositoryAuth(
        testCloudMapper,
        ExceptionAuthMapper.Test(),
        ExceptionAuthMapper.Test(),
        TokenToSharedPreferences.Test(),
        testRegistrationCloudDataSource,
        testAuthCloudDataSource
    )

    @Test
    fun authentication_success_test() = runBlocking {
        val actual = repository.auth(1, "Ivan", "Ivanov", "pas", "auth")
        val expected = AuthorizationListData.Success(
            listOf(
                AuthorizationData.Base(
                    "accessToken",
                    "refreshToken",
                    true
                )
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun registration_success_test() = runBlocking {
        val actual = repository.auth(1, "Ivan", "Ivanov", "pas", "register")
        val expected = AuthorizationListData.Success(
            listOf(
                AuthorizationData.Base(
                    "accessToken",
                    "refreshToken",
                    true
                )
            )
        )
        assertEquals(expected, actual)
    }


    private inner class TestAuthCloudDataSource(private val success: Boolean) :
        AuthenticationCloudDataSource {
        override suspend fun authentication(
            phoneNumber: Long,
            password: String
        ): List<AuthorizationCloud.Base> {
            if (success) {
                return listOf(
                    AuthorizationCloud.Base("accessToken", "refreshToken", true)
                )
            } else {
                throw unknownHostException
            }
        }
    }

    private inner class TestRegistrationDataSource(private val success: Boolean) :
        RegistrationCloudDataSource {
        override suspend fun register(
            phoneNumber: Long,
            name: String,
            secondName: String,
            password: String
        ): List<AuthorizationCloud.Base> = if (success) {
            listOf(AuthorizationCloud.Base("accessToken", "refreshToken", true))
        } else {
            listOf(AuthorizationCloud.Base("null", "null", false))
        }
    }
}