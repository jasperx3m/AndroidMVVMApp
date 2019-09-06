package com.example.mvvmapplication.data.network

import com.example.mvvmapplication.ui.util.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {
    suspend fun<T: Any> apiRequest(call: suspend() -> Response<T>) : T {
        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                    /*val message= JSONObject(it).getString("message") // messaage from api*/

                } catch (e: JSONException) { }
                message.append("\n") //append -> add to string

            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}