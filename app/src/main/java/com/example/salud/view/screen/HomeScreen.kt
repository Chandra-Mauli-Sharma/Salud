package com.example.salud.view.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.salud.R
import com.example.salud.data.HomeScreenCardList
import com.example.salud.utils.BottomNavItem
import com.example.salud.view.utils.HomeScreenCard
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.model.KalendarType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Home_Screen(navController: NavController,modifier: Modifier) {
    var visible by remember { mutableStateOf(false) }
//    CustomNavigationDrawer(drawerState = drawerState, scope = scope, navController)
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        AnimatedVisibility(
            visible,
            // Sets the initial height of the content to 20, revealing only the top of the content at
            // the beginning of the expanding animation.
            enter = expandVertically(expandFrom = Alignment.Top) { 20 },
            // Shrinks the content to half of its full height via an animation.
            exit = shrinkVertically(animationSpec = tween()) { fullHeight ->
                fullHeight / 2
            },
        ) {
            Kalendar(kalendarType = KalendarType.Oceanic)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.ArrowBack, "Back")
            Text(
                "Today",
                fontSize = 30.sp,
                modifier = Modifier.clickable { visible = !visible })
            Icon(Icons.Filled.ArrowForward, "Forward")
        }

        LazyVerticalGrid(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.Center,
            columns = GridCells.Fixed(2)
        ) {
            items(items = HomeScreenCardList().list) {
                HomeScreenCard(
                    cardColor = it.cardColor,
                    cardText = it.cardText,
                    cardIcon = painterResource(id = it.cardIcon),
                    navController = navController,
                    route = it.route
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavigationDrawer(
    drawerState: DrawerState,
    scope: CoroutineScope,
    navController: NavController
) {

    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
    var selectedItem by remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.wrapContentSize(), drawerShape = RoundedCornerShape(20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.shinchan),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(20.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = item == selectedItem,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {

        }
    )
}
