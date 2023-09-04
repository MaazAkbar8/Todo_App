package com.example.todo_app




import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todo_app.databinding.ActivityMainBinding
import com.example.todo_app.screen.AddFragment
import com.example.todo_app.screen.HomeFragment

class MainActivity : AppCompatActivity() {
 //private lateinit var btnhom:TextView
 //   private lateinit var btnadd:TextView


  //  private lateinit var plus:TextView
   lateinit var binding: ActivityMainBinding
   // two variable
    //private val homeF=HomeFragment()
   // private val AddF=AddFragment()


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //*********************************************************






        // new workes
        val homeFragment = HomeFragment()
        val addFragment = AddFragment()
        curentfragment(homeFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miHome-> curentfragment(homeFragment)
                R.id.miadd->curentfragment(addFragment)
            }
            true
        }




    }
    private fun curentfragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfrgment,fragment)
            commit()
        }

    }



        //intiakization
        /*  btnadd = findViewById(R.id.btnadd)
        btnhom=findViewById(R.id.btnhome)


       // plus=findViewById(R.id.tvplus)
       //************************************************************

        //*********************************************************
        //if clicked btnhome to dispaly on fragment homefragment
       btnhom.setOnClickListener{
            showaction(homeF)


            Toast.makeText(this, "yes available", Toast.LENGTH_SHORT).show()
        }
        //*************************************************************
        // if clicked btnadd to disply on fragment addfragment
        btnadd.setOnClickListener {
             showaction(AddF)

            Toast.makeText(this, "yes available", Toast.LENGTH_SHORT).show()
        }
        //**************************************************************


    }







    //    this is  a fuction which help replace or convert one fragment to another fragment using  (backstack)
    private fun showaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
        .replace(R.id.Rlfragemt, fragment)

         .commit()
    }




    }*/







       */

       */
       */
       */
    }




