package com.example.tohackmeapp

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.tohackmeapp.FormatConvertion.toObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    var fAuth: FirebaseAuth? = null
    var fStore: FirebaseFirestore? = null
    var documentReference: DocumentReference? = null

    val databaseReference = FirebaseDatabase.getInstance().reference.child("monsters")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

        val userId = fAuth!!.currentUser!!.uid
        documentReference = fStore!!.collection("users").document(userId)




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
        documentReference!!.addSnapshotListener(this) { snapshot, e ->
            if (e != null) {
//                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val user = snapshot.data!!.toObject<User>()
                hpScore.text = user.hp.toString()
                expScore.text = user.ep.toString()
                userName.text = user.name.toString()

                if (user.level < 4) {
                    databaseReference.child(user.monster_id.toString()).child(user.level.toString()).addListenerForSingleValueEvent(object: ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            val link = snapshot.value
                            Picasso.get().load(link.toString()).into(imageView)
                        }

                    })
                }
                else {
                    val userStrongField = getUserStrongField(user)
                    databaseReference.child(user.monster_id.toString()).child(user.level.toString() + "_$userStrongField").addListenerForSingleValueEvent(object: ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            val link = snapshot.value
                            Picasso.get().load(link.toString()).into(imageView)
                        }

                    })
                }

            }
        }

    }
    fun getUserStrongField (user: User): String {
        val map = mapOf(
            "physical" to user.physical,
            "intelligence" to user.intelligence,
            "lifestyle" to user.lifestyle
        )
        val max = map.maxBy { it.value }
        return max!!.key
    }

    fun onLogout(view: View) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

}