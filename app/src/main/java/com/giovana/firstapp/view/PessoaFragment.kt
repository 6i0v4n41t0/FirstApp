package com.giovana.firstapp.view

import android.app.AlertDialog
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

        //Carrega a pessoa caso tenha selecionado
        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        binding.btnEnviar.setOnClickListener {
                val nome = binding.edtNome.editableText.toString()
                val anoNascimento = binding.edtIdade.editableText.toString()
                var faixa = ""
                var sexo = ""


            if (nome !="" && anoNascimento != "" &&
                binding.rbFeminino.isChecked  || binding.rbMaculino.isChecked){

                if(binding.rbFeminino.isChecked){
                    sexo = "Feminino"
                }
                else{
                    sexo = "Masculino"
                }

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

                viewModel.pessoa.value?.let {
                    pessoa.id = it.id
                    viewModel.update(pessoa)

                } ?:run {
                    viewModel.insert(pessoa)
                }


                binding.edtNome.editableText.clear()
                binding.edtIdade.editableText.clear()

                findNavController().navigateUp()
            } else{
                Toast.makeText(requireContext(), "Digite os dados", Toast.LENGTH_LONG).show()
            }

        }

        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de pessoa")
                .setMessage("Você realmente deseja excluir?")
                .setPositiveButton("Sim"){ _,_ ->
                    viewModel.delete(viewModel.pessoa.value?.id ?:0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("Não"){ _,_ -> }
                .show()
        }

        viewModel.pessoa.observe(viewLifecycleOwner){pessoa ->
            binding.edtNome.setText(pessoa.nome)
            binding.edtIdade.setText((LocalDateTime.now().year - pessoa.idade).toString())

            if (pessoa.sexo == "Feminino"){
                binding.rbFeminino.isChecked = true
            }else{
                binding.rbMaculino.isChecked = true
            }

            binding.btnDeletar.visibility = View.VISIBLE
        }
    }

}