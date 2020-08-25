package com.example.tohackmeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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





        btnAddTask.setOnClickListener() {
            val intent = Intent(this, TodoForm::class.java)
            startActivity(intent)
        }

        btnShowTask.setOnClickListener() {
            val intent = Intent(this, TodoList::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        val userId = fAuth!!.currentUser!!.uid
        val documentReference : DocumentReference = fStore!!.collection("users").document(userId)
        documentReference.addSnapshotListener(this) { snapshot, e ->
            if (e != null) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val user = snapshot.data!!.toObject<User>()
                hpScore.text = user.hp.toString()
                expScore.text = user.ep.toString()
                userName.text = user.name.toString()
            }
        }

    }

    fun onLogout(view: View) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}