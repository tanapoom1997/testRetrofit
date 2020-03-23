package terk.borntodev.testjson3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import terk.borntodev.testjson3.model.Posts
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        val jsonHolderAPI = retrofit.create(JsonHolderAPI::class.java)
        val myCall:Call<List<Posts>> = jsonHolderAPI.getPost()

        myCall.enqueue(object :Callback<List<Posts>> {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Log.d("ERROR: ",t.message.toString())
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                val Post :List<Posts> = response.body()!!

                val stringBuilder = StringBuilder()

                for (post in Post){
                    stringBuilder.append(post.userId)
                    stringBuilder.append("\n")
                    stringBuilder.append(post.id)
                    stringBuilder.append("\n")
                    stringBuilder.append(post.title)
                    stringBuilder.append("\n")
                    stringBuilder.append(post.body)
                    stringBuilder.append("\n")
                }
                txtPost.text = stringBuilder.toString()
                Log.d("Value : ",stringBuilder.toString())
            }

        })

    }
}
