package com.giovana.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.giovana.firstapp.databinding.FragmentCalculoBinding
import com.giovana.firstapp.service.model.Pessoa
import com.giovana.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime


class PessoaFragment : Fragment() {

    private val viewModel: PessoaViewModel by viewModels ()


    private var _binding: FragmentCalculoBinding? = null
    private val binding: FragmentCalculoBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentCalculoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEnviar.setOnClickListener {
                val nome = binding.edtNome.editableText.toString()
                val anoNascimento = binding.edtIdade.editableText.toString()

            if (nome !="" && anoNascimento != ""){
                binding.tvNome.text = "Nome: " + nome

                val anoAtual = LocalDateTime.now().year
                var idade = anoAtual - anoNascimento.toInt()

                binding.tvIdade.text = "Idade: ${idade}"

                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade
                )

                viewModel.insert(pessoa)

                binding.edtNome.editableText.clear()
                binding.edtIdade.editableText.clear()
            } else{
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }

        }
    }

}