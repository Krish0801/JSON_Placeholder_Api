package com.example.jsonplaceholderapi.ui.registerPage

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jsonplaceholderapi.R
import com.example.jsonplaceholderapi.navigation.NavigationItem
import com.example.jsonplaceholderapi.ui.LoginActivity
import com.example.jsonplaceholderapi.ui.MainActivity
import com.example.jsonplaceholderapi.ui.loginPage.email
import com.example.jsonplaceholderapi.ui.loginPage.password
import kotlinx.coroutines.launch

var email = mutableStateOf("")
var password = mutableStateOf("")
@Composable
fun RegisterPage(viewModel: RegisterViewModel = hiltViewModel(),navController: NavHostController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.registerState.collectAsState(initial = null)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent,
            )
    ) {


        Box(
            modifier = Modifier
                .align(Alignment.Center),
        ) {

            Image(
                painter = painterResource(id = R.drawable.user_reg),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth(),

                )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //.........................Spacer
                Spacer(modifier = Modifier.height(30.dp))

                //.........................Text: title
                androidx.compose.material3.Text(
                    text = "Register An Account",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 130.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                )

                Spacer(modifier = Modifier.padding(8.dp))
                RegisterEmail()

                Spacer(modifier = Modifier.padding(3.dp))
                RegisterPassword()


                val gradientColor = listOf(Color(0xFF484BF1), Color(0xFF673AB7))
                val cornerRadius = 16.dp


                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.registerUser(email.value, password.value)
                        }
                    },
                    shape = RoundedCornerShape(cornerRadius), // 16dp is the corner radius
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)
                        .background(
                            Brush.linearGradient(
                                gradientColor,
                                start = Offset.Zero,
                                end = Offset.Infinite
                            )
                        )
                ) {
                    Text(text = "Register", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))

                if (state.value?.isLoading == true) {
                    CircularProgressIndicator()
                }

                Spacer(modifier = Modifier.padding(10.dp))

                signInButton()
//                androidx.compose.material3.TextButton(onClick = {navController.navigate(NavigationItem.LoginPage.route)}) {
//                    androidx.compose.material3.Text(
//                        text = "Sign In",
//                        letterSpacing = 1.sp,
//                        style = MaterialTheme.typography.labelLarge
//                    )
//                }

                Spacer(modifier = Modifier.padding(20.dp))

            }


        }

    }

    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                context.startActivity(Intent(context,MainActivity::class.java))
                (context as LoginActivity).finish()
                Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
            }
        }
    }

    LaunchedEffect(key1 = state.value?.isError) {
        scope.launch {
            if (state.value?.isError?.isNotEmpty() == true) {
                val error = state.value?.isError
                Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
            }
        }
    }

}


//email id
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterEmail() {
    val keyboardController = LocalSoftwareKeyboardController.current
    //var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = email.value,
        onValueChange = { email.value = it },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                "Enter Email Address",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
            )
        },
//        placeholder = { Text(text = "Email Address") },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}

//password
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterPassword() {
    val keyboardController = LocalSoftwareKeyboardController.current
    //var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    OutlinedTextField(
        value = password.value,
        onValueChange = { password.value = it },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                "Enter Password",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary
        ),
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                // Please provide localized description for accessibility services
                val description = if (passwordHidden) "Show password" else "Hide password"
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        },
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )
    )
}

@Composable
fun signInButton() {
    val context = LocalContext.current
    val headerColor= Color(0xFF4453DD)
    Text(
        text = "Already have an account? SignIn",
        color = headerColor,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(18.dp)
            .clickable {
                val intent = Intent(context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                (context as Activity).finish()
            }
            .fillMaxWidth()
    )
}


