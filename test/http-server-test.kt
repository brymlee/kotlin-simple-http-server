package brymlee

import org.junit.Test
import org.junit.Assert.*
import java.net.URL
import java.io.InputStream

fun httpGetAsString(path:String) : String{
	val url = URL(path)
	val connection = url.openConnection()
	val inputStream = connection.getInputStream()
	val bytes = ByteArray(inputStream.available())
	inputStream.read(bytes)
	inputStream.close()
	return String(bytes)
}

class Tests{

	@Test
	fun helloEqualsHello(){
		assertEquals("hello", "hello")
	}

	@Test
	fun noErrorWhenDoingHttpCallToGoogleHost(){
		val string = httpGetAsString("http://www.google.com")
		assertNotNull(string)
	}

	@Test
	fun startHttpServer_successfullyGetHelloWorld(){
		main(arrayOf(""))
		val response = httpGetAsString("http://localhost:8080")
		assertTrue(response.contains("Hello World!"))
		httpGetAsString("http://localhost:8080/shutdown")
	}
}

