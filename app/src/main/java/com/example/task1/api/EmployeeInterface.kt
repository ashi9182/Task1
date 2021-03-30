package com.example.task1.api

import com.example.task1.models.Employee
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeInterface {
    @GET("{identify}")
    fun getEmployee(@Path("identify")id : Int) : Call<Employee>


    companion object{
        operator fun invoke() : EmployeeInterface{
            return Retrofit.Builder()
                .baseUrl("https://dummy.restapiexample.com/api/v1/employee/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EmployeeInterface::class.java)
        }

        const val carJsonString = "{\n" +
                "  \"cars\": [\n" +
                "    {\n" +
                "      \"modelName\": \"A-Class\",\n" +
                "      \"modelYear\": \"2020\",\n" +
                "      \"index\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"modelName\": \"C-Class\",\n" +
                "      \"modelYear\": \"2020\",\n" +
                "      \"index\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"modelName\": \"B-Class\",\n" +
                "      \"modelYear\": \"2020\",\n" +
                "      \"index\": 2\n" +
                "    },\n" +
                "    {\n" +
                "      \"modelName\": \"H-Class\",\n" +
                "      \"modelYear\": \"2020\",\n" +
                "      \"index\": 3\n" +
                "    },\n" +
                "    {\n" +
                "      \"modelName\": \"J-Class\",\n" +
                "      \"modelYear\": \"2020\",\n" +
                "      \"index\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"modelName\": \"G-Class\",\n" +
                "      \"modelYear\": \"2020\",\n" +
                "      \"index\": 5\n" +
                "    },\n" +
                "    {\n" +
                "      \"modelName\": \"I-Class\",\n" +
                "      \"modelYear\": \"2020\",\n" +
                "      \"index\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"modelName\": \"D-Class\",\n" +
                "      \"modelYear\": \"2020\",\n" +
                "      \"index\": 7\n" +
                "    },\n" +
                "    {\n" +
                "      \"modelName\": \"F-Class\",\n" +
                "      \"modelYear\": \"2020\",\n" +
                "      \"index\": 8\n" +
                "    },\n" +
                "    {\n" +
                "      \"modelName\": \"E-Class\",\n" +
                "      \"modelYear\": \"2020\",\n" +
                "      \"index\": 9\n" +
                "    }\n" +
                "  ]\n" +
                "}"
    }
}