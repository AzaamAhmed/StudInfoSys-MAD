package com.example.studentmanagmentsystem

import android.app.AlertDialog
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.studentmanagmentsystem.database.StudentDatabase
import com.example.studentmanagmentsystem.database.StudentRepository
import com.example.studentmanagmentsystem.database.student
import com.example.studentmanagmentsystem.validation.FormData
import com.example.studentmanagmentsystem.validation.ValidationResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var name:EditText
    private lateinit var id:EditText
    private lateinit var num:EditText
    private var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootview = inflater.inflate(R.layout.fragment_register, container, false)

        val repository = StudentRepository(StudentDatabase.getInstance(requireContext()))


        name = rootview.findViewById(R.id.edtName)
        id = rootview.findViewById(R.id.edtID)
        num = rootview.findViewById(R.id.edtNum)

        val btnReg: Button= rootview.findViewById(R.id.btnReg)

        btnReg.setOnClickListener{
            val data1 = name.text.toString()
            val data2 = id.text.toString()
            val data3 = num.text.toString()


            valid()
            if(count == 3) {
                CoroutineScope(Dispatchers.IO).launch {
                    repository.insert(student(data1, data2, data3))
                }
                Toast.makeText(requireContext(), "successfully added", Toast.LENGTH_LONG).show()
                val newFragment = ListFragment() // Replace 'YourNewFragment' with the actual name of your new fragment
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.fragmentContainer, newFragment) // Replace 'R.id.fragment_container' with the ID of your fragment container
                transaction?.addToBackStack(null) // Add the transaction to the back stack (optional)
                transaction?.commit()
                count = 0
            }else{
                count = 0
            }

        }

        return rootview
    }



    fun valid(){
        val myForm = FormData(
            name.text.toString(),
            id.text.toString(),
            num.text.toString()
        )

        val studentIdValidation =  myForm.validateStudentId()
        val nameValidation = myForm.validateName()
        val numValidation = myForm.validatePhoneNumber()

        when(studentIdValidation){
            is ValidationResult.Valid ->{
                count ++
            }
            is ValidationResult.Invalid ->{
                id.error = studentIdValidation.errorMessage
            }
            is ValidationResult.Empty ->{
                id.error = studentIdValidation.errorMessage
            }
        }

        when(nameValidation){
            is ValidationResult.Valid ->{
                count ++
            }
            is ValidationResult.Invalid ->{
                name.error = nameValidation.errorMessage
            }
            is ValidationResult.Empty ->{
                name.error = nameValidation.errorMessage
            }
        }

        when(numValidation){
            is ValidationResult.Valid ->{
                count ++
            }
            is ValidationResult.Invalid ->{
                num.error = numValidation.errorMessage
            }
            is ValidationResult.Empty ->{
                num.error = numValidation.errorMessage
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}