package com.albertjokelin.revise.ui.science

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertjokelin.revise.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_science.*
import okhttp3.*
import java.io.IOException



class ScienceFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView_main.layoutManager = LinearLayoutManager(activity)

        fetchJson()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_science, container, false)

    }

    fun fetchJson() {
        println("Attempting to fetch JSON")
        val url = "https://albert-jokelin.github.io/10th%20Grade/Science/Science.json"
        val request = Request.Builder().url(url).build()
        val client= OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to retrieve data")
            }

            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homefeed = gson.fromJson(body, HomeFeed::class.java)

                activity!!.runOnUiThread {
                    recyclerView_main.adapter = ScienceAdapter(homefeed)
                }

            }
        })

    }


}

class HomeFeed(val materials: List<Material>)

class Material(val name: String, val id: Int, val myLink: String)