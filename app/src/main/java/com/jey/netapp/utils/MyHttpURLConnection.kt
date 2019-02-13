package com.jey.netapp.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object MyHttpURLConnection {

    fun startHttpRequest(urlString: String): String {

        val stringBuilder = StringBuilder()

        try {

            // 1. Declare a URL Connection
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            // 2. Open InputStream to connection
            conn.connect()
            val `in` = conn.inputStream
            // 3. Download and decode the string response using builder
            val reader = BufferedReader(InputStreamReader(`in`))
            var line: String
            while (reader.readLine() != null) {
                line = reader.readLine()
                stringBuilder.append(line)
            }

        } catch (exception: MalformedURLException) {

        } catch (exception: IOException) {

        } catch (e: Exception) {

        }

        return stringBuilder.toString()
    }

}
