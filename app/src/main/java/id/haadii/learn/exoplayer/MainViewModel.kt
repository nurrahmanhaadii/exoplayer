package id.haadii.learn.exoplayer

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.haadii.learn.exoplayer.data.ApiMain
import id.haadii.learn.exoplayer.data.model.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var dataFilm = MutableLiveData<Film>()

//    init {
//        getFilm()
//    }

    fun getFilm() {
        Log.e("getfilm", "true")
        ApiMain().api().video(BuildConfig.API_KEY, "animation").enqueue(object : Callback<Film> {
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.isSuccessful) {
                    dataFilm.value = response.body()
                    Log.e("response", "${response.body()}")
                } else {
                    Log.e("response", "unsuccess")
                }
            }

            override fun onFailure(call: Call<Film>, t: Throwable) {
                Log.e("error", "$t")
            }
        })
    }

    fun setFilm() : MutableLiveData<Film> {
        return dataFilm
    }
}