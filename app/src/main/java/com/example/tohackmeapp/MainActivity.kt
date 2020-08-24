package com.example.tohackmeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tohackmeapp.FormatConvertion.toObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var fAuth : FirebaseAuth? = null
    var fStore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()
        val userId = fAuth!!.currentUser!!.uid

        val documentReference : DocumentReference = fStore!!.collection("users").document(userId)

        btnAddTask.setOnClickListener() {
            val intent = Intent(this, TodoForm::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun onLogout(view: View) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}