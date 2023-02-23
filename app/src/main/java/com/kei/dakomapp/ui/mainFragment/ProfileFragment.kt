package com.kei.dakomapp.ui.mainFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kei.dakomapp.R
import com.kei.dakomapp.ui.auth.LandingScreen
import com.kei.dakomapp.ui.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

//@AndroidEntryPoint
class ProfileFragment : Fragment(), View.OnClickListener{
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivBtnLogoutProfile.setOnClickListener(this)
        firebaseUser = FirebaseAuth.getInstance().currentUser
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