package com.example.todo_app.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_app.Adapter.myAdapter
import com.example.todo_app.DataClass.TodoData
import com.example.todo_app.R
import com.example.todo_app.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
     private lateinit var adapter:myAdapter
    private val List = mutableListOf<TodoData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home, container, false)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)











        adapter= myAdapter(List)
        binding.Rcv.adapter=adapter
        binding.Rcv.layoutManager=LinearLayoutManager(requireContext())

        // return view
// Retrieve data from Firebase Realtime Database
        val databaseReference = FirebaseDatabase.getInstance().reference.child("userimage")// rootname realtimeDB
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                List.clear()
                for (dataSnapshot in snapshot.children) {
                    val imageUrl = dataSnapshot.child("image").getValue(String::class.java)// childrootname realtimeDB

                    if (imageUrl != null) {
                        List.add(TodoData(imageUrl))

                       // Toast.makeText(requireContext(), "good successfully get Data in DB", Toast.LENGTH_SHORT).show()
                    }

                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "no get img dataBase", Toast.LENGTH_SHORT).show()
            }
        })




        return binding.root




    }




    }





