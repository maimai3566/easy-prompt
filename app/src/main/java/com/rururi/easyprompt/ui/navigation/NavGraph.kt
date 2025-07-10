package com.rururi.easyprompt.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rururi.easyprompt.data.UserPreferencesRepository
import com.rururi.easyprompt.ui.screen.HomeScreen
import com.rururi.easyprompt.ui.screen.HomeViewModel
import com.rururi.easyprompt.ui.screen.prompt.PromptScreen
import com.rururi.easyprompt.ui.screen.prompt.PromptType
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    userPreferencesRepository: UserPreferencesRepository,
    modifier: Modifier = Modifier
) {
    val promptViewModel: PromptViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ){
        composable(route = Screen.Home.route){
            val homeViewModel: HomeViewModel = hiltViewModel()
            val isFirstLaunch by homeViewModel.isFirstLaunch.collectAsState()

            HomeScreen(
                onNavigateToPerson = {  //人物中心の処理
                    navController.navigate("prompt/${PromptType.PERSON}")
                    promptViewModel.updateUiState {copy(promptType = PromptType.PERSON)}
                },
                onNavigateToText = {    //文字中心の処理
                    navController.navigate("prompt/${PromptType.TEXT}")
                    promptViewModel.updateUiState {copy(promptType = PromptType.TEXT)}
                },
                onNavigateToBackground = {  //背景のみの処理
                    navController.navigate("prompt/${PromptType.BACKGROUND}")
                    promptViewModel.updateUiState {copy(promptType = PromptType.BACKGROUND)}
                },
                onResetAll = { //フルリセット処理
                    promptViewModel.resetAll()
                },
                isFirstLaunch = isFirstLaunch,
                onDismiss = { homeViewModel.onTutorialDismissed() },
                onShowTutorialForDebug = { homeViewModel.showTutorialForDebug() }
            )
        }
        composable( //押したボタンによって動的に画面遷移
            route = "prompt/{type}",    //ルートにプレースホルダーあり：prompt/PERSONのように画面の引数を渡す
            arguments = listOf(navArgument("type") { type = NavType.StringType })   //ルートにプレースホルダーがあることを宣言。{}は"type"がString型と指定
        ){ backStackEntry ->
            val typeArg = backStackEntry.arguments?.getString("type") ?: "PERSON"   //選択されているプロンプトのタイプ　デフォルトはPERSON
            val promptType = PromptType.valueOf(typeArg)    //取得した文字列をPromptTypeに変換
            val uiState by promptViewModel.uiState.collectAsState()
            PromptScreen(
                navController = navController,
                viewModel = promptViewModel,
                promptType = promptType,
                uiState = uiState,
            )
        }
    }
}