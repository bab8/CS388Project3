package com.example.flixster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixster.OnListFragmentInteractionListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class FlixsterFragment : Fragment(), OnListFragmentInteractionListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_flixster_items_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)
        return view
    }


    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()


        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY

        client[
            "https://api.themoviedb.org/3/movie/top_rated?&api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
            params,
            object : JsonHttpResponseHandler()
            {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {
                    progressBar.hide()

                    val resultsJSON : JSONArray = json.jsonObject.get("results") as JSONArray
                    val gson = Gson()
                    val arrayMovieType = object: TypeToken<List<Flixster>>() {}.type
                    val models : List<Flixster> = gson.fromJson(resultsJSON.toString(), arrayMovieType)
                    recyclerView.adapter = FlixsterRecyclerViewAdapter(models, this@FlixsterFragment)

                    Log.d("FlixsterFragment", "response successful")
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    progressBar.hide()

                    t?.message?.let {
                        Log.e("FlixsterFragment", errorResponse)
                    }
                }
            }]


    }


    override fun onItemClick(item: Flixster) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("title", item.title)
        intent.putExtra("img", item.movieImageUrl)
        intent.putExtra("desc", item.description)
        intent.putExtra("votes", item.votes)
        intent.putExtra("rating", item.rating)
        intent.putExtra("release", item.release)
        context?.startActivity(intent)
    }

}