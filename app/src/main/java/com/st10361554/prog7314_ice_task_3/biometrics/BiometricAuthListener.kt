package com.st10361554.prog7314_ice_task_3.biometrics

interface BiometricAuthListener
{
    fun onBiometricAuthenticateError(error: Int,errMsg: String)
    fun onBiometricAuthenticateSuccess(result: androidx.biometric.BiometricPrompt.AuthenticationResult)
}