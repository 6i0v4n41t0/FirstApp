package com.giovana.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.giovana.firstapp.databinding.FragmentPessoaBinding
import com.giovana.firstapp.service.model.Pessoa
import com.giovana.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime


class PessoaFragment : Fragment() {

    private val viewModel: PessoaViewModel by viewModels ()


    private var _binding: FragmentPessoaBinding? = null
    private val binding: FragmentPessoaBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentPessoaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnEnviar.setOnClickListener {
                val nome = binding.edtNome.editableText.toString()
                val anoNascimento = binding.edtIdade.editableText.toString()
                val sexo = binding.edtSexo.editableText.toString()
                var faixa = ""


            if (nome !="" && anoNascimento != ""){
                val anoAtual = LocalDateTime.now().year
                var idade = anoAtual - anoNascimento.toInt()


                if(idade<=12){
                    faixa = "Infantil"
                }
                else if(idade<18){
                    faixa = "Adolescente"
                }
                else if(idade<=64){
                    faixa = "Adulto"
                }
                else{
                    faixa = "Idoso"
                }


                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade,
                    sexo = sexo,
                    faixa = faixa
                )

                viewModel.insert(pessoa)

                binding.edtNome.editableText.clear()
                binding.edtIdade.editableText.clear()

                findNavController().navigateUp()
            } else{
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }

        }
    }

}