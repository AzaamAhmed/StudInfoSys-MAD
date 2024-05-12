package com.example.studentmanagmentsystem.validation

class FormData(
    private var name:String,
    private var studentID:String,
    private var phoneNum:String
) {
    fun validateStudentId():ValidationResult{
        return if(studentID.isEmpty()){
            ValidationResult.Empty("Student ID is empty")
        }else if(!studentID.startsWith("IT" )&& !studentID.startsWith("it") ){
            ValidationResult.Invalid("Should be starting with IT")
        }else if(studentID.length!=10){
            ValidationResult.Invalid("Student ID should have 10 characters")
        }else{
            ValidationResult.Valid
        }

    }

    fun validateName():ValidationResult{
        return if(name.isEmpty()){
            ValidationResult.Empty("Name is empty")
        }else if(!name.all { it.isLetter() || it == ' ' }){
            ValidationResult.Invalid("Name should only contain alphabets")
        }
        else{
            ValidationResult.Valid
        }
    }

    fun validatePhoneNumber():ValidationResult{
        return if(phoneNum.isEmpty()){
            ValidationResult.Empty("Number is empty")
        }else if(phoneNum.length!=10){
            ValidationResult.Invalid("Number should have 10 characters")
        }
        else{
            ValidationResult.Valid
        }
    }
}