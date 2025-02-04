package com.example.dersretrofitson.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dersretrofitson.databinding.FragmentLoginBinding
import com.example.dersretrofitson.util.goneItem
import com.example.dersretrofitson.util.visibleItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    var job1: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        viewModel.getUser()

        binding.buttonLogin.setOnClickListener {
            login()
        }

        binding.buttonRegister.setOnClickListener {
            register()
        }

        GlobalScope.launch {
            Log.e("addımlar", "addım 1")
            delay(5000)
        }

        job1 = lifecycleScope.launch(Dispatchers.IO) {
            Log.e("addımlar", "addım 1")
            delay(5000)
        }

        job1?.cancel()

        lifecycleScope.launch {
            Log.e("addımlar", "addım 2")
            delay(3000)
        }

        lifecycleScope.launch {
            Log.e("addımlar", "addım 3")
        }
    }

    private fun register() {
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (password.length < 8) {
                Toast.makeText(context, "Şifre minumum 8 olmalıdır", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.registerFirebase(email, password)
            }

        } else {
            Toast.makeText(context, "Bu alanlar boş ola bilmez", Toast.LENGTH_SHORT).show()
        }
    }

    private fun login() {
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.loginFirebase(email, password)
        } else {
            Toast.makeText(context, "Bu alanlar boş ola bilmez", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeData() {
        viewModel.userLogin.observe(viewLifecycleOwner) { userLogin ->
            if (userLogin) {
                Toast.makeText(context, "Uğurlu giriş", Toast.LENGTH_SHORT).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            } else {
                Toast.makeText(context, "Xetalı giriş", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.userRegister.observe(viewLifecycleOwner) { userRegister ->
            if (userRegister) {
                Toast.makeText(context, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Kayıt Başarısız", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.progressBar2.visibleItem()
            } else {
                binding.progressBar2.goneItem()
            }
        }

        viewModel.isUserAuth.observe(viewLifecycleOwner) { isAuth ->
            if (isAuth) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        }
    }


}