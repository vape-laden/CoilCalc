package de.vapeladen.coilrechner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.vapeladen.coilrechner.ui.calculator.CalculatorScreen
import de.vapeladen.coilrechner.ui.info.DeveloperInfoScreen
import de.vapeladen.coilrechner.ui.knowledge.KnowledgeScreen
import de.vapeladen.coilrechner.ui.tools.BatteryLifeScreen
import de.vapeladen.coilrechner.ui.tools.ConvertersScreen
import de.vapeladen.coilrechner.ui.theme.VapeCoilRechnerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VapeCoilRechnerTheme {
                CoilRechnerApp()
            }
        }
    }
}

sealed class Screen(val route: String, val titleRes: Int, val icon: ImageVector) {
    object Calculator : Screen("calculator", R.string.tab_calculator, Icons.Default.Calculate)
    object Knowledge : Screen("knowledge", R.string.tab_knowledge, Icons.Default.MenuBook)
    object Info : Screen("info", R.string.tab_info, Icons.Default.Info)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoilRechnerApp() {
    val navController = rememberNavController()
    val screens = listOf(Screen.Calculator, Screen.Knowledge, Screen.Info)
    var showMenu by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, "Menu", tint = Color.White)
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.menu_battery_life)) },
                            onClick = {
                                navController.navigate("battery_life")
                                showMenu = false
                            },
                            leadingIcon = { Icon(Icons.Default.BatteryFull, null) }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.menu_converters)) },
                            onClick = {
                                navController.navigate("converters")
                                showMenu = false
                            },
                            leadingIcon = { Icon(Icons.Default.SwapHoriz, null) }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6639B7),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF6639B7)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = stringResource(screen.titleRes)) },
                        label = { Text(stringResource(screen.titleRes)) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedIconColor = Color.White.copy(alpha = 0.6f),
                            unselectedTextColor = Color.White.copy(alpha = 0.6f),
                            indicatorColor = Color(0xFF9C27B0)
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Calculator.route,
        modifier = modifier
    ) {
        composable(Screen.Calculator.route) {
            CalculatorScreen(
                onNavigateToDeveloperInfo = {
                    navController.navigate(Screen.Info.route)
                }
            )
        }
        
        composable(Screen.Knowledge.route) {
            KnowledgeScreen()
        }
        
        composable(Screen.Info.route) {
            DeveloperInfoScreen(
                onNavigateBack = {
                    navController.navigate(Screen.Calculator.route) {
                        popUpTo(Screen.Calculator.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable("battery_life") {
            BatteryLifeScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable("converters") {
            ConvertersScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}