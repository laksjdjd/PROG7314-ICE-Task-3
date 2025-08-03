package com.st10361554.prog7314_ice_task_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.exceptions.ClearCredentialException
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.st10361554.prog7314_ice_task_3.chatbot.ChatActivity
import com.st10361554.prog7314_ice_task_3.databinding.ActivityMainBinding
import com.st10361554.prog7314_ice_task_3.landing.SignUpActivity
import kotlinx.coroutines.launch

/**
 * MainActivity is the entry point of the application.
 * It displays the main screen and provides navigation to the chatbot interface.
 *
 * @property binding The view binding for the activity's layout.
 * @property btnOpenChat The button used to open the chat activity.
 */
class MainActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    // View components
    private lateinit var btnOpenChat: Button
    private lateinit var btnLogout: Button
    private lateinit var tvUsername: TextView

    // CredentialManager for clearing credential state on logout
    private lateinit var credentialManager: CredentialManager

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the data it most recently supplied in onSaveInstanceState.
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        // Enables edge-to-edge display for a more immersive experience
        enableEdgeToEdge()

        // Inflate the view binding for the main activity layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply system window insets to the root view so content doesn't overlap system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnOpenChat = binding.btnOpenChat
        btnLogout = binding.btnLogout
        tvUsername = binding.tvUsername

        // Initialize Firebase Auth instance
        auth = FirebaseAuth.getInstance()

        // Initialize CredentialManager instance
        credentialManager = CredentialManager.create(this)

        // Check the authentication state of the user
        checkAuthState()

        // Set up click listeners for UI components
        setUpOnClickListeners()
    }

    /**
     * Sets up click listeners for buttons and other interactive UI elements.
     * Specifically, this initializes the button that opens the chat activity and the logout button.
     */
    private fun setUpOnClickListeners()
    {
        // Set listener to open ChatActivity when the button is clicked
        btnOpenChat.setOnClickListener {
            // Create an intent to navigate to the chat activity
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        // Set listener to log out the user when the logout button is clicked
        btnLogout.setOnClickListener {
            // Sign out the user from Firebase and clear credential state
            signOut()

            // Redirect to SignUpActivity after logging out
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

            // Close MainActivity so user cannot return to it
            finish()
        }
    }

    /**
     * Checks the authentication state of the user.
     * If not authenticated, redirects to the SignUpActivity; otherwise, displays username.
     */
    private fun checkAuthState()
    {
        val currentUser = auth.currentUser

        // If the user is not authenticated, redirect to SignUpActivity
        if (currentUser == null)
        {
            btnLogout.isVisible = false // Hide logout button if not authenticated

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish() // Close MainActivity so user cannot return to it

        }
        else
        {
            btnLogout.isVisible = true // Show logout button if authenticated
            // User is authenticated, proceed with normal flow
            setUsername()
        }
    }

    /**
     * Sets the username TextView to display the user's display name if signed in,
     * or a default label if signed in anonymously.
     */
    private fun setUsername()
    {
        val user = auth.currentUser

        if (user != null)
        {
            if (user.isAnonymous)
            {
                // User is signed in anonymously; show a default username
                tvUsername.text = getString(R.string.default_username)
            }
            else
            {
                // User has a non-anonymous account; show their display name (or email if displayName is null)
                tvUsername.text = user.displayName ?: user.email ?: getString(R.string.default_username)
            }
        }
        else
        {
            // No user is signed in leave blank
            tvUsername.text = ""
        }
    }

    /**
     * Signs out the user from Firebase and clears the credential state using CredentialManager.
     * This ensures that credentials are also cleared from the device's credential providers.
     */
    private fun signOut()
    {
        // Firebase sign out
        auth.signOut()

        // Clear credential state from CredentialManager (Google, Passkeys, etc.)
        lifecycleScope.launch {
            try {
                val clearRequest = ClearCredentialStateRequest()
                credentialManager.clearCredentialState(clearRequest)
            } catch (e: ClearCredentialException) {
                Log.e("MainActivity", "Couldn't clear user credentials: ${e.localizedMessage}")
            }
        }
    }

}