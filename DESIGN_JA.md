# Easy Prompt 設計書

## 概要
Easy Prompt は、生成AI向けのプロンプトを段階的に作成する Android アプリです。Jetpack Compose を使って画面を構築しており、ユーザーは各ステップで設定を入力していくと最終的に YAML 形式のプロンプトが生成されます。

## アーキテクチャ
- **言語・フレームワーク**: Kotlin、Jetpack Compose、Material3、Navigation Compose
- **モジュール構成**: 単一モジュール (app)
- **主要コンポーネント**:
  - `MainActivity`: テーマを適用し `EasyPromptApp` を表示
  - `EasyPromptApp`: `NavController` を用いた画面遷移と Scaffold 構成
  - `PromptViewModel`: `PromptUiState` を管理し、各画面からの入力を保持
  - `NavGraph`: `HomeScreen` と `PromptScreen` をルートとして構成

## 画面遷移
1. **HomeScreen**
   - プロンプトの種類 (人物中心 / 文字中心 / 背景のみ) を選択
   - 既存設定のリセット機能
2. **PromptWizard (PromptScreen)**
   - 選択したタイプに応じ以下のステップで入力を行う
   - `Canvas` → `Camera` → `Theme` → `Person`/`Title`/`Body` → `Lighting` → `Review`
3. **ReviewSec**
   - 入力内容から `PromptViewModel` が生成した YAML を表示・編集
   - コピー操作でクリップボードへ転送

## 状態管理
`PromptUiState` に各ステップの入力値が保持されます。`PromptViewModel` は `MutableStateFlow` で状態を公開し、更新関数 `updateUiState` を通じて画面から状態を変更します。

```kotlin
class PromptViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PromptUiState())
    val uiState: StateFlow<PromptUiState> = _uiState.asStateFlow()
    fun updateUiState(update: PromptUiState.() -> PromptUiState) {
        _uiState.update { it.update() }
    }
}
```

入力内容は `buildYaml()` で YAML 文字列として組み立てられ、`ReviewSec` で表示・編集可能です。【F:app/src/main/java/com/rururi/easyprompt/ui/screen/prompt/PromptViewModel.kt†L84-L161】

## UI要素
- `SetRadio`, `SetRadioText`, `SetTextField`, `SetColor` など、共通部品で入力を受け付けます。
- `PromptTopBar` と `PromptBottomBar` で画面上部・下部を共通化し、ステップ遷移や進捗表示を行います。【F:app/src/main/java/com/rururi/easyprompt/ui/screen/prompt/PromptBottomBar.kt†L1-L106】

## 出力形式
最終ステップの `Review` では YAML が生成されます。生成処理は `PromptViewModel.buildYaml()` に実装されています。空欄は無視され、入力された項目のみが `prompt:` 以下に階層化されて出力されます。

## 今後の拡張案
- プリセット機能や保存機能の追加
- 入力補完やバリデーションの強化
- 共有機能・生成AIとの連携

