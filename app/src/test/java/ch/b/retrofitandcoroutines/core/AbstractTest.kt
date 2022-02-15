package ch.b.retrofitandcoroutines.core

import junit.framework.Assert.assertTrue
import org.junit.Test
import java.io.IOException
import java.lang.Exception

class AbstractTest {
    /*
    @Test
    fun test_success(){
        val dataObject = TestDataObject.Success("a","b")
        val domainObject = dataObject.map(DataMapper.Base())
        assertTrue(domainObject is DomainObject.Success)
    }

    @Test
    fun test_fail(){
        val dataObject = TestDataObject.Fail(IOException())
        val domainObject = dataObject.map(DataMapper.Base())
        assertTrue(domainObject is DomainObject.Fail)

    }

    sealed class TestDataObject : Abstract.Object<DomainObject,DataMapper>(){
        class Success(private val textOne: String,private val textTwo: String) : TestDataObject(){
            override fun map(mapper: DataMapper): DomainObject = mapper.map(textOne,textTwo)
        }
        class Fail(private val exception: Exception) : TestDataObject(){
            override fun map(mapper: DataMapper): DomainObject = mapper.map(exception)
        }
    }
    sealed class DomainObject : Abstract.Object<UIObject,DomainToUIMapper>(){
        class Success(private val textOne: String,private val textTwo: String) : DomainObject(){
            override fun map(mapper: DomainToUIMapper): UIObject = mapper.map(textOne,textTwo)
        }
        class Fail(private val exception: Exception) : DomainObject(){
            override fun map(mapper: DomainToUIMapper): UIObject = mapper.map(exception)
        }
    }

    sealed class UIObject

    interface DomainToUIMapper : Abstract.Mapper{
        fun map(textOne: String,textTwo: String) : UIObject
        fun map(e:Exception) : UIObject
    }

    interface DataMapper : Abstract.Mapper{
        fun map(textOne: String,textTwo: String) : DomainObject
        fun map(e: Exception) : DomainObject

        class Base : DataMapper{
            override fun map(textOne: String, textTwo: String): DomainObject = DomainObject.Success(textOne,textTwo)
            override fun map(e: Exception): DomainObject = DomainObject.Fail(e)
        }
    }

     */
}