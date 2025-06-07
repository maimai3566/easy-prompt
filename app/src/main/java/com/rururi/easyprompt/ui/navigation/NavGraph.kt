package com.rururi.easyprompt.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rururi.easyprompt.ui.screen.HomeScreen
import com.rururi.easyprompt.ui.screen.PresetScreen
import com.rururi.easyprompt.ui.screen.prompt.PromptScreen
import com.rururi.easyprompt.ui.screen.prompt.PromptType
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import androidx.compose.runtime.getValue

@Composable
fun NavGraph(
    promptViewModel: PromptViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ){
        composable(route = Screen.Home.route){
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