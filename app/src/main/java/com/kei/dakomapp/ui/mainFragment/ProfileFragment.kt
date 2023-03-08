package com.kei.dakomapp.ui.mainFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.kei.dakomapp.R
import com.kei.dakomapp.databinding.FragmentProfileBinding
import com.kei.dakomapp.ui.auth.LandingScreen
import com.kei.dakomapp.ui.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), View.OnClickListener{

    var refUser: DatabaseReference? = null
    var firebaseUser : FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentProfileBinding.inflate(layoutInflater)

        profile(bind)
        return bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivBtnLogoutProfile.setOnClickListener(this)
    }



    private fun profile(binding: FragmentProfileBinding) {
        firebaseUser = FirebaseAuth.getInstance().currentUser
        refUser = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser!!.uid)
        refUser!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { _ ->
                    val name = snapshot.child("name").value.toString()
                    val email = snapshot.child("email").value.toString()
                    val profileImage = snapshot.child("profile_image").value.toString()

                    binding.tvUserNameProfile.text = name
                    binding.tvUserEmailProfile.text = email

                }
            }
        })
    }


    override fun onClick(p0: View) {
        when(p0.id){
            R.id.ivBtnLogoutProfile -> logout()
        }
    }

    private fun logout() {
        val intent = Intent (activity, LandingScreen::class.java)
        activity?.startActivity(intent)
        FirebaseAuth.getInstance().signOut()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}