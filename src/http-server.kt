package brymlee

import java.io.IOException
import java.io.OutputStream
import java.net.InetSocketAddress
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer


fun main(arguments:Array<String>){
	val server = com.sun.net.httpserver.HttpServer.create(InetSocketAddress(8080), 0)	
	server.setExecutor(null)
	server.start()
	server.createContext("/shutdown", { httpExchange:HttpExchange -> 
		val response = "Shutting Down"
		httpExchange.sendResponseHeaders(200, response.length.toLong())
		val outputStream = httpExchange.getResponseBody()
		outputStream.write(response.toByteArray())
		outputStream.close()
		server.stop(0)
	})

	server.createContext("/", { httpExchange:HttpExchange -> 
		val response = "Hello World!"
		httpExchange.sendResponseHeaders(200, response.length.toLong())
		val outputStream = httpExchange.getResponseBody()
		outputStream.write(response.toByteArray())
		outputStream.close()
	})
}
