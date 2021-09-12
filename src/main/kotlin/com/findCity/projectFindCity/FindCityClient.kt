package com.findCity.projectFindCity

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@Service
class FindCityClient {
    fun findNoOfCityFromApi(): FindCityDataClass{
        try {
            val mapper = ObjectMapper().registerModule(KotlinModule())
            val url =
                URL("https://samples.openweathermap.org/data/2.5/box/city?bbox=50,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22")
            var readLine: String? = null
            val conection: HttpURLConnection = url.openConnection() as HttpURLConnection
            conection.setRequestMethod("GET")
            val responseCode: Int = conection.getResponseCode()
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val `input` = BufferedReader(
                    InputStreamReader(conection.getInputStream())
                )
                val response = StringBuffer()
                while (`input`.readLine().also { readLine = it } != null) {
                    response.append(readLine)
                }
                `input`.close()
                val findcity: FindCityDataClass = mapper.readValue(response.toString(), FindCityDataClass::class.java)
                return findcity
            } else {
                return FindCityDataClass(cod = "400", message = "API is down, Please try after sometime!!")
            }
        } catch (e: Exception){
            return FindCityDataClass(cod = "400", message = "API is down, Please try after sometime!!")
        }
    }
}