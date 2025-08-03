package com.st10361554.prog7314_ice_task_3.landing

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.st10361554.prog7314_ice_task_3.MainActivity
import com.st10361554.prog7314_ice_task_3.R
import com.st10361554.prog7314_ice_task_3.biometrics.BiometricAuthListener
import com.st10361554.prog7314_ice_task_3.biometrics.BiometricUtils
import com.st10361554.prog7314_ice_task_3.databinding.ActivitySignUpBinding
import kotlinx.coroutines.launch

/**
 * Activity for user sign-up, including Google Sign-In integration using Credential Manager and Firebase Authentication.
 * Also supports biometric authentication for anonymous sign-up.
 */
class SignUpActivity : AppCompatActivity(), BiometricAuthListener {

    // View binding for accessing XML layout views
    private lateinit var binding: ActivitySignUpBinding

    // Firebase Authentication instance for managing user sign-in
    private lateinit var auth: FirebaseAuth

    /**
     * Called when the activity is starting.
     * Sets up view binding, window insets, Firebase Auth, and click listeners.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using view binding and set it as the content view
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle window insets to support edge-to-edge UI
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize FirebaseAuth instance for authenticating users
        auth = FirebaseAuth.getInstance()

        // Set up click listeners for UI elements
        setUpOnClickListeners()
    }

    /**
     * Sets up all click listeners for interactive UI elements.
     * Handles Google Sign-Up and biometric sign-up button clicks.
     */
    private fun setUpOnClickListeners() {
        // Google Sign-Up button click listener to initiate Google Sign-In flow
        binding.btnGoogleSignUp.setOnClickListener {

            // Build GoogleIdOption for Google Sign-In, using your web client ID and allowing all device accounts
            val googleIdOption = GetGoogleIdOption.Builder()
                .setServerClientId(getString(R.string.default_web_client_id)) // Web client ID from Google Cloud Console
                .setFilterByAuthorizedAccounts(false) // Allow all Google accounts on the device
                .build()

            // Create a credential request with the GoogleIdOption
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            // Launch the sign-in process using CredentialManager in a coroutine
            lifecycleScope.launch {
                try
                {
                    // Create the CredentialManager instance
                    val credentialManager = CredentialManager.create(this@SignUpActivity)

                    // Suspend function to get credential response from Google Sign-In
                    val response = credentialManager.getCredential(
                        context = this@SignUpActivity,
                        request = request
                    )

                    // Extract the credential from the response and handle Google sign-in result
                    val credential = response.credential

                    handleSignIn(credential)
                }
                catch (e: Exception)
                {
                    // Log error and show user-friendly message
                    Log.w("SignUpActivity", "Google sign-in failed", e)

                    val message = if (e is androidx.credentials.exceptions.NoCredentialException)
                    {
                        // More helpful error if no Google account was found
                        "No matching Google account was found. Please add an account to your device or try again."
                    }
                    else
                    {
                        "Google sign-in failed: ${e.localizedMessage}"
                    }

                    Toast.makeText(this@SignUpActivity, message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Biometric Sign-Up button click listener
        binding.btnBiometricSignUp.setOnClickListener {

            Log.d("SignUpActivity", "Biometric button clicked.")

            // Check if biometric authentication is available and ready on the device
            if (BiometricUtils.isBiometricReady(this))
            {
                // Show biometric prompt to user, pass this activity as callback listener
                BiometricUtils.showBiometricPrompt(
                    activity = this,
                    listener = this,
                    cryptoObject = null,
                )
            }
            else
            {
                // Show message if biometrics are not available
                Toast.makeText(this, "Biometric feature not available", Toast.LENGTH_SHORT).show()

                // Redirect user back to this screen (or handle as needed)
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    /**
     * Handles the credential returned by CredentialManager after user sign-in.
     * Verifies if the credential is a Google ID token and proceeds to Firebase authentication.
     *
     * @param credential The credential returned from Google Sign-In
     */
    private fun handleSignIn(credential: Credential)
    {
        // Check if the credential is a Google ID token
        if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL)
        {
            // Extract the Google ID token from the credential
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            // Use the ID token to sign in to Firebase
            firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
        }
        else
        {
            // Log warning and notify user if credential type is unexpected
            Log.w("SignUpActivity", "Credential is not of type Google ID!")

            Toast.makeText(
                this,
                "Credential is not of type Google ID!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Uses the Google ID token to authenticate the user with Firebase.
     * On success, redirects the user to the main activity.
     *
     * @param idToken The ID token obtained from Google Sign-In
     */
    private fun firebaseAuthWithGoogle(idToken: String)
    {
        // Create a Firebase credential using the Google ID token
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        // Attempt to sign in with Firebase using the Google credential
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful)
                {
                    // Sign-in succeeded; notify user and redirect to main activity
                    Log.d("SignUpActivity", "signInWithCredential:success")

                    Toast.makeText(this, "Authentication Successful.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    // Sign-in failed; log error and notify user
                    Log.w("SignUpActivity", "signInWithCredential:failure", task.exception)

                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    /**
     * BiometricAuthListener implementation:
     * Called when biometric authentication fails or is cancelled.
     * Handles specific error codes and redirects the user as needed.
     *
     * @param error The error code from BiometricPrompt
     * @param errMsg The error message from BiometricPrompt
     */
    override fun onBiometricAuthenticateError(error: Int, errMsg: String)
    {
        when (error)
        {
            BiometricPrompt.ERROR_USER_CANCELED -> {
                // User cancelled biometric authentication, notify and redirect to sign-up
                Toast.makeText(this, "Biometric authentication cancelled", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, SignUpActivity::class.java))
                finish()
            }
            BiometricPrompt.ERROR_NEGATIVE_BUTTON -> {
                // User clicked negative button, notify and redirect to sign-up
                Toast.makeText(this, "Biometric authentication cancelled", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, SignUpActivity::class.java))
                finish()
            }
        }
    }

    /**
     * BiometricAuthListener implementation:
     * Called when biometric authentication is successful.
     * Proceeds to anonymous sign-up via Firebase.
     *
     * @param result The result from BiometricPrompt
     */
    override fun onBiometricAuthenticateSuccess(result: BiometricPrompt.AuthenticationResult)
    {
        signUpWithBiometrics()
    }

    /**
     * Signs up the user anonymously with Firebase after successful biometric authentication.
     * On success, redirects the user to the main activity.
     */
    private fun signUpWithBiometrics()
    {
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful)
                {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignUpActivity", "signInAnonymously:success")

                    Toast.makeText(this, "Authentication Successful.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    // If sign in fails, display a message to the user.
                    Log.w("SignUpActivity", "signInAnonymously:failure", task.exception)

                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}