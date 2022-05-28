# Dcard-interview

此專案主要是For Dcard的小作業，實作Github Search Repositories。

## Feature
- [x] 程式碼使用 Kotlin 撰寫。
- [x] 文字輸入匡。
- [x] 偵測到輸入文字改變時呼叫 GitHub API 查詢 GitHub repositories，並顯示出搜尋結果。
- 包含以下 UI 狀態：
    - [x] 空狀態：輸入框為空時，提示使用者輸入內容。
    - [x] 載入狀態：呼叫 API 等待回應時。
    - [x] 錯誤狀態：API 回應錯誤時。
- [x] 使用 MDC-Android 套件，UI 符合 Material Design Guidelines。
- [x] 使用 Kotlin Coroutines。
- [ ] 撰寫測試。

## Tech stack & Open-source libraries

- Minimum SDK level 21
- Kotlin based , Coroutines & Flow for asynchronous.
- Koin for dependency injection.
- Jetpack
  - Lifecycle
  - ViewModel
  - DataBinding
