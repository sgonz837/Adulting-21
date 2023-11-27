package com.adulting21.adulting21

//
//class login : AppCompatActivity() {
//    private lateinit var bottomNavigationView: BottomNavigationView
//
//    private lateinit var firebaseAuth: FirebaseAuth
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.login_page)
//
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        val loginbtn = findViewById<Button>(R.id.login_btn)
//        //if login button is clicked, then execute tasks
//        loginbtn.setOnClickListener {
//            login_page(it)
//
//            //code here if login button is clicked
//
//            //val intent = Intent(this, Navigation::class.java)
//            //startActivity(intent)
//        }
//    }
//
//    fun login_page(view: View) {
//        Log.d("TAG", "Succesful")
//        val email = findViewById<EditText>(R.id.email).text.toString()
//        val password = findViewById<EditText>(R.id.password).text.toString()
//
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Email and password are required.", Toast.LENGTH_SHORT).show()
//            return
//        }
//        // Sign in with the provided email and password
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Login successful, navigate to the main activity
//                    val intent = Intent(this, HomeFragment::class.java)
//                    startActivity(intent)
//                    finish()
//                } else {
//                    // Login failed, display an error message
//                    Toast.makeText(this, "Login failed: ${task.exception?.localizedMessage}", Toast.LENGTH_LONG).show()
//                }
////            }
//}
//}
//



//        override fun onBackPressed() {
//            super.onBackPressed()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
