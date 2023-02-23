package com.kei.dakomapp.ui.auth

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kei.dakomapp.R
import com.kei.dakomapp.databinding.ActivityRegisterBinding
import com.kei.dakomapp.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId : String = ""
    private  lateinit var  registerBinding: ActivityRegisterBinding

    companion object {
        fun getLaunchService (from: Context) = Intent(from, RegisterActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        registerBinding.btnRegister.setOnClickListener(this)
    }
    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btnRegister -> registerUser()
        }
    }

    private fun registerUser() {
        val name : String = registerBinding.etNameRegister.text.toString()
        val email : String = registerBinding.etEmailRegister.text.toString()
        val password : String = registerBinding.etPasswordRegister.text.toString()
        val confirmPassword : String = registerBinding.etConfirmPassRegister.text.toString()

        if (name == ""){
            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show()
        }else if (email == ""){
            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show()
        }else if (password == ""){
            Toast.makeText(this, getString(R.string.txt_error), Toast.LENGTH_SHORT).show()
        }else if ((confirmPassword == "").equals(password)){
            Toast.makeText(this, getString(R.string.txt_pass_mismatch), Toast.LENGTH_SHORT).show()
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->
                if(it.isSuccessful){
                    firebaseUserId = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child(getString(R.string.txt_users)).child(firebaseUserId)
                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["name"] = name
                    userHashMap["email"] = email

                    refUsers.updateChildren(userHashMap).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                            val intent = Intent(MainActivity.getLaunchService(this))
                            startActivity(intent)
                        }
                    }
                }else{
                    val progress = ProgressDialog(this, R.style.Theme_AppCompat_Light_Dialog)
                    progress.hide()
                    Toast.makeText(this, getString(R.string.txt_error) + it.exception!!
                        .message.toString(), Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}