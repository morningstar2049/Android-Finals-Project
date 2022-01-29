package com.example.androidfinalsproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var gunName: EditText
    private lateinit var gunDescription: EditText
    private lateinit var imageUrl : EditText
    private lateinit var addButton : Button
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("Userinfo")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view : View = inflater.inflate(R.layout.fragment_first, container, false)

        gunName = view.findViewById(R.id.editTextTextPersonName3)
        gunDescription = view.findViewById(R.id.editTextTextPersonName2)
        imageUrl = view.findViewById(R.id.editTextTextPersonName)
        addButton = view.findViewById(R.id.addButton)



        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RecyclerViewGunAdapter(getGunData())

        return view


    }

    private fun getGunData() : List<Gun>{
        val gunList = ArrayList<Gun>()
        val title = gunName.text.toString()
        val description = gunDescription.text.toString()
        val url = imageUrl.text.toString()
        val gunInfo = Gun(1,url, title, description)






        addButton.setOnClickListener {
            db.child(auth.currentUser?.uid!!).setValue(gunInfo)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
                    }
                }
        }



        gunList.add(
            Gun(
                1,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Glock_17-removebg-preview.png/300px-Glock_17-removebg-preview.png",
                "Glock-18",
                "Designed By Gaston Glock"

            )

        )
        gunList.add(
            Gun(
                2,
                "data:image/jpeg;base64,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",
                "M4A1",
                "Fully automatic variant of M4"

            )

        )

        gunList.add(
            Gun(
                3,
                "https://aoav.org.uk/wp-content/uploads/2016/03/ak47.jpg",
                "AK-47",
                "Officially known as Avtomat Kalashnikov"
            )
        )
        gunList.add(
            Gun(
                4,
                "https://www.artilharia6.com/2863/magnum-research-desert-eagle-sts-6-mb.jpg",
                "Desert Eagle",
                "Otherwise known as Hand-cannon"
            )
        )
        gunList.add(
            Gun(
                5,
                "https://large.shootingsportsmedia.com/103274.jpg",
                "Colt Python",
                "Intended for premium market segment"
            )
        )
        gunList.add(
            Gun(
                6,
                "https://static.turbosquid.com/Preview/2015/03/29__10_37_24/000.pnge41c2121-801e-4cf1-9c65-994a383ab97aLarge.jpg",
                "UZI",
                "Designed by Major Uziel Gal"
            )
        )
        gunList.add(
            Gun(
                7,
                "https://www.asmc.com/media/image/ff/37/e6/51104-0.jpg",
                "Makarov",
                "Soviet Unions standard side arm in 1951"
            )
        )




        return gunList
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}