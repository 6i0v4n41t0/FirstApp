package com.giovana.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.giovana.firstapp.R
import com.giovana.firstapp.databinding.FragmentAllPessoasBinding
import com.giovana.firstapp.databinding.FragmentPessoaDetailBinding
import com.giovana.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaDetailFragment : Fragment() {
//Chamar a viewmodel para pegar os dados
    private val viewModel: PessoaViewModel by viewModels ()

    //Criar o binding para pegar os elementos da tela

    private var _binding: FragmentPessoaDetailBinding? = null
    private val binding: FragmentPessoaDetailBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Configurar o binding
        _binding = FragmentPessoaDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    //Chamar a funcao onViewCreated onde vamos implementar o codigo
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Pegar o id da pessoa que foi enviado pela AllPessoasFragment
        //Setar a pessoa para ser carregada na tela
        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        //Carregar as informacoes da pessoa selecionada
        viewModel.pessoa.observe(viewLifecycleOwner) { pessoa ->
            binding.tvNome.text = "Nome: " + pessoa.nome
            binding.tvAno.text = "Ano de nascimento: " + (LocalDateTime.now().year - pessoa.idade).toString()
            binding.tvIdade.text = "Idade: " + pessoa.idade.toString()
            binding.tvFaixa.text = "Faixa Etária: " + pessoa.faixa

            if (pessoa.sexo == "Feminino") {
                binding.ivSexoF.visibility = View.VISIBLE
                binding.ivSexoM.visibility = View.GONE
            } else {
                binding.ivSexoM.visibility = View.VISIBLE
                binding.ivSexoF.visibility = View.GONE
            }
        }
    }
}