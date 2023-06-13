package id.ac.unpas.tubesppmkelompok6kelasd.networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.tubesppmkelompok6kelasd.model.Dosen
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DosenApi {
    @GET("api/dosen")
    suspend fun all(): ApiResponse<DosenGetResponse>

    @GET("api/dosen/{id}")
    suspend fun find(@Path("id") id: String): ApiResponse<DosenSingleGetResponse>

    @POST("api/dosen")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Dosen): ApiResponse<DosenSingleGetResponse>

    @PUT("api/dosen/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") pathId: String,
                       @Body item: Dosen): ApiResponse<DosenSingleGetResponse>

    @DELETE("api/dosen/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<DosenSingleGetResponse>
}