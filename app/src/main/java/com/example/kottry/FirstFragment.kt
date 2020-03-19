package com.example.kottry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirstFragment : Fragment() {

    lateinit var fragAdapter: FragAdapter
    lateinit var list: RecyclerView
    private var items = ArrayList<PojoModel>()
    private var apiInterface: ApiInterface? = null
    private val s: String = "First Fragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list = view.findViewById(R.id.fragment_recycler)
        list.layoutManager = LinearLayoutManager(context)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface = retrofit.create(ApiInterface::class.java)

        apiInterface!!.getData().enqueue(object : Callback<ArrayList<PojoModel>> {
            override fun onResponse(
                call: Call<ArrayList<PojoModel>>,
                response: Response<ArrayList<PojoModel>>
            ) {
                items = response.body()!!
                fragAdapter = FragAdapter(items)
                list.adapter = fragAdapter
                fragAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<PojoModel>>, t: Throwable) {
                Log.i(s, t.message!!)
            }

        })

    }

    class FragAdapter(private val list: List<PojoModel>) :
        RecyclerView.Adapter<FragAdapter.FragViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return FragViewHolder(inflater, parent)
        }

        override fun getItemCount(): Int = list.size


        override fun onBindViewHolder(holder: FragViewHolder, position: Int) {
            val PojoModel: PojoModel = list[position]
            holder.bind(PojoModel)
        }

        class FragViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.frag_view_holder, parent, false)) {

            private var txtName: TextView = itemView.findViewById(R.id.txt_name)
            private var txtDesc: TextView = itemView.findViewById(R.id.txt_desc)

            fun bind(PojoModel: PojoModel) {
                txtName.text = PojoModel.title
                txtDesc.text = PojoModel.completed.toString()
            }
        }

    }
}





