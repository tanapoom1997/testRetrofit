package terk.borntodev.testjson3

import retrofit2.http.GET
import terk.borntodev.testjson3.model.Posts

interface JsonHolderAPI {

    @GET("posts")
    fun getPost(): retrofit2.Call<List<Posts>>
}