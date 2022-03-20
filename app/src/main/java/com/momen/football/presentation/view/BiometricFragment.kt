package com.momen.football.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.momen.football.R
import com.momen.football.databinding.FragmentBiometricBinding
import com.momen.football.util.AuthenticationError
import kotlinx.android.synthetic.main.fragment_biometric.*

class BiometricFragment : Fragment() {

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var biometricManager: BiometricManager
    private lateinit var binding: FragmentBiometricBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_biometric, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupBiometricAuthentication()
        checkBiometricFeatureState()
        if (isBiometricFeatureAvailable()) {
            biometricPrompt.authenticate(buildBiometricPrompt())
        }
    }
    private fun setupBiometricAuthentication() {
        biometricManager = BiometricManager.from(requireActivity())
        val executor = ContextCompat.getMainExecutor(requireActivity())
        biometricPrompt = BiometricPrompt(this, executor, biometricCallback)
    }

    private fun checkBiometricFeatureState() {
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> setErrorNotice("Sorry. It seems your device has no biometric hardware")
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> setErrorNotice("Biometric features are currently unavailable.")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> setErrorNotice("You have not registered any biometric credentials")
            BiometricManager.BIOMETRIC_SUCCESS -> {}
        }
    }

    private fun buildBiometricPrompt(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder()
            .setTitle("Verify your identity")
            .setDescription("Confirm your identity so we can verify it's you")
            .setNegativeButtonText("Cancel")
            .setConfirmationRequired(false) //Allows user to authenticate without performing an action, such as pressing a button, after their biometric credential is accepted.
            .build()
    }

    private fun isBiometricFeatureAvailable(): Boolean {
        return biometricManager.canAuthenticate(BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
    }

    private val biometricCallback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            findNavController().navigate(R.id.action_biometricFragment_to_competitionListFragment)
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)

            if (errorCode != AuthenticationError.AUTHENTICATION_DIALOG_DISMISSED.errorCode && errorCode != AuthenticationError.CANCELLED.errorCode) {
                setErrorNotice(errString.toString())
            }
        }
    }

    private fun setErrorNotice(errorMessage: String) {
        tvErrorNotice.text = errorMessage
    }
}