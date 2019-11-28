package id.haadii.learn.exoplayer.data

import id.haadii.learn.exoplayer.data.model.Film
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("videos/")
    fun video(@Query("key") value: String,
              @Query("video_type") type: String) : Call<Film>
}