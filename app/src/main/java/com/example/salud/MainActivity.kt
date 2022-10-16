package com.example.salud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.salud.navigation.StepTrackerNavHost
import com.example.salud.navigation.SaludScreen
import com.example.salud.ui.theme.StepTrackerTheme
import com.example.salud.utils.BottomNavItem
import com.example.salud.utils.Constants.routeTitle
import com.example.salud.view.screen.OnboardingScreen
import com.example.salud.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<AuthViewModel>()
    private val auth = FirebaseAuth.getInstance()
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StepTrackerTheme {
                navHostController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (viewModel.isUserAuthenticated)
                        StepTrackerApp(navHostController)
                    else OnboardingScreen(navController = navHostController)
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepTrackerApp(navController: NavHostController) {
    val context = LocalContext.current

    var auth = Firebase.auth


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    routeTitle[currentRoute]?.let {
                        Text(
                            it,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.displayLarge,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { if (navController.previousBackStackEntry != null) navController.popBackStack() }) {
                        Icon(
                            imageVector = if (navController.previousBackStackEntry == null) Icons.Filled.Info else Icons.Filled.ArrowBack,
                            "Menu"
                        )
                    }
                },
                actions = {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(auth.currentUser?.photoUrl)
                            .crossfade(true)
                            .build(),
                        "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(shape = CircleShape)
                            .clip(shape = CircleShape)
                            .size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            )
        },
        bottomBar = {
            NavigationBar {

                listOf<BottomNavItem>(
                    BottomNavItem.Home,
                    BottomNavItem.LeaderBoard
                ).forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.screen_route,
                        onClick = {
                            navController.navigate(item.screen_route) {

                                navController.graph.startDestinationRoute?.let { screen_route ->
                                    popUpTo(screen_route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painterResource(id = item.icon), contentDescription = null
                            )
                        })
                }

            }
        }
    ) {
        StepTrackerNavHost(navController = navController, modifier = Modifier.padding(it))
    }

    val account = GoogleSignIn.getLastSignedInAccount(context)
    if (account != null) navController.navigate(SaludScreen.HOME_SCREEN.name)
}

