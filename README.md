# Easy Prompt

Easy Prompt は、生成AIのためのプロンプトを段階的に作成する Android アプリです。Jetpack Compose を用いて UI を構築しており、入力した内容を YAML 形式で出力できます。

## 特長

- **ステップ形式のウィザード**: `Canvas` から始まり `Camera`、`Theme`、`Person`/`Title`/`Body`、`Lighting`、`Review` と進みます。
- **YAML 生成**: 入力した値だけを `prompt:` 以下に階層化して書き出します。
- **クリップボードへのコピー**: Review 画面で生成された YAML をワンタップでコピーできます。
- **状態管理**: `PromptViewModel` で `PromptUiState` を `StateFlow` として管理します。

## 主要コンポーネント

- `MainActivity` – `EasyPromptApp` を表示するアクティビティ。
- `EasyPromptApp` – `NavController` を使った画面遷移を提供します。
- `PromptViewModel` – ユーザー入力を保持し、YAML 文字列を組み立てます。
- `NavGraph` – `HomeScreen` と `PromptScreen` をルートにナビゲーションを設定します。

## ビルド方法

1. Android Studio でこのリポジトリを開きます。
2. `./gradlew assembleDebug` を実行して APK を生成します。
3. 生成された APK を端末にインストールしてください。

## 参考: 状態管理の実装例

```kotlin
class PromptViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PromptUiState())
    val uiState: StateFlow<PromptUiState> = _uiState.asStateFlow()
    fun updateUiState(update: PromptUiState.() -> PromptUiState) {
        _uiState.update { it.update() }
    }
}
```

生成処理は `buildYaml()` に実装されており、空欄を無視して結果をまとめます。

## 今後の改善案

- プリセットや保存機能の追加
- 入力補完やバリデーションの強化
- 共有機能や生成AIとの連携

