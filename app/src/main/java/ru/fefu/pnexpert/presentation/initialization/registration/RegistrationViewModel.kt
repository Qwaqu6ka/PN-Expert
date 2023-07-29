package ru.fefu.pnexpert.presentation.initialization.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.fefu.pnexpert.presentation.initialization.registration.navigation.RegistrationNavigationRoute
import ru.fefu.pnexpert.presentation.initialization.registration.sing_up.SingUpFormEvent
import ru.fefu.pnexpert.utils.validation.models.SingUpFormState
import ru.fefu.pnexpert.utils.validation.singUpValidation.ValidatePassword
import ru.fefu.pnexpert.utils.validation.singUpValidation.ValidatePhoneNumber
import ru.fefu.pnexpert.utils.validation.singUpValidation.ValidateRepeatPassword
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validatePhoneNumber: ValidatePhoneNumber,
    private val validatePassword: ValidatePassword,
    private val validateRepeatPassword: ValidateRepeatPassword,
):ViewModel() {
    //registration pages variables
    private var _pagesNavController: NavController? = null
    val pagesNavController get() = _pagesNavController

    private var _currentRegistrationPage by mutableStateOf<RegistrationNavigationRoute>(
        RegistrationNavigationRoute.SingUpScreen
    )
    val currentRegistrationPage get() = _currentRegistrationPage


    //input data variables
    var inputDataState by mutableStateOf(SingUpFormState())
    var errorData by mutableStateOf("")

    //a thread for sending notifications to the UI thread
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    //init pages nav controller
    fun initPagesNavController(navController: NavController){
        _pagesNavController = navController
    }

    fun changeRegistrationPage(currentPage: RegistrationNavigationRoute){
        _currentRegistrationPage = currentPage
    }

    //listener ui input events
    fun inputDataEvent(event: SingUpFormEvent){
        when(event){
            is SingUpFormEvent.PhoneNumberChanged ->{
                inputDataState = inputDataState.copy(phoneNumber = event.phoneNumber)
            }
            is SingUpFormEvent.PasswordChanged->{
                inputDataState = inputDataState.copy(password = event.password)
            }
            is SingUpFormEvent.RepeatPasswordChanged->{
                inputDataState = inputDataState.copy(repeatPassword = event.repeatPassword)
            }
            is SingUpFormEvent.Submit ->{
                submitInputData()
            }
        }
    }

    private fun submitInputData() {
        val phoneNumberResult = validatePhoneNumber(inputDataState.phoneNumber)
        val passwordResult = validatePassword(inputDataState.password)
        val repeatPasswordResult = validateRepeatPassword(
            inputDataState.password,
            inputDataState.repeatPassword,
        )

        //find validate errors
        val hasError = listOf(
            phoneNumberResult,
            passwordResult,
            repeatPasswordResult
        ).any{ !it.success}

        if (hasError){
            inputDataState = inputDataState.copy(
                phoneNumberError = phoneNumberResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatPasswordError = repeatPasswordResult.errorMessage
            )
            return
        }

        inputDataState = inputDataState.copy(
            phoneNumberError = null,
            passwordError = null,
            repeatPasswordError = null
        )

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }


    sealed class ValidationEvent{
        object Success:ValidationEvent()
        object Error:ValidationEvent()
    }
}