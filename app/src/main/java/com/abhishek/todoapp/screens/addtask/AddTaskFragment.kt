package com.abhishek.todoapp.screens.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.abhishek.todoapp.R
import com.abhishek.todoapp.databinding.FragmentAddTaskBinding
import com.abhishek.todoapp.model.Task
import com.androidpoet.metaphor.metaphorMaterialContainerTransformViewIntoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val viewModel: AddTaskViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        metaphorMaterialContainerTransformViewIntoFragment(
            requireActivity().findViewById(R.id.fab),
            binding.root
        )

        binding.closeIcon.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.save.setOnClickListener {
            val task = Task()
            if (binding.title.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please Enter Title", Toast.LENGTH_SHORT).show()
            } else {
                task.title = binding.title.text.toString()
                if (binding.body.text.isNullOrEmpty()) {
                    Toast.makeText(context, "Please Enter Content", Toast.LENGTH_SHORT).show()
                } else {
                    task.content = binding.body.text.toString()
                    viewModel.addTask(task)
                    findNavController().popBackStack()
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
