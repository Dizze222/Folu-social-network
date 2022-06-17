package ch.b.retrofitandcoroutines.data.core

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException

class ExceptionAuthMapperTest {

    @Test
    fun test_null_pointer_exception(){
        val mapper = ExceptionAuthMapper.Test()
        val actual = mapper.map(NullPointerException())
        val expected = ExceptionAuthMapper.Test().map(NullPointerException())
        assertEquals(expected,actual)
    }

    @Test
    fun test_unknown_host_exception(){
        val mapper = ExceptionAuthMapper.Test()
        val actual = mapper.map(UnknownHostException())
        val expected = ExceptionAuthMapper.Test().map(UnknownHostException())
        assertEquals(expected,actual)
    }

    @Test
    fun test_connect_exception(){
        val mapper = ExceptionAuthMapper.Test()
        val actual = mapper.map(ConnectException())
        val expected = ExceptionAuthMapper.Test().map(ConnectException())
        assertEquals(expected,actual)
    }

    @Test
    fun some_exception(){
        val mapper = ExceptionAuthMapper.Test()
        val actual = mapper.map(Exception())
        val expected = ExceptionAuthMapper.Test().map(Exception())
        assertEquals(expected,actual)
    }
}