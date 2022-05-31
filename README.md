# Dcard-interview

此專案主要是For Dcard的小作業，實作Github Search Repositories，下面是Demo畫面👇。

| Loading  | Result |
| ------------- | ------------- |
|<img src= "https://user-images.githubusercontent.com/24693300/171027104-22be08de-ba1e-4da1-ba6e-fb3502fa4b66.png" width="360" height="720" /> | <img src= "https://user-images.githubusercontent.com/24693300/171027471-d4f5abf1-e639-4881-9d16-8af040770090.png" width="360" height="720" />  |

| API Error | No Internet |
| ------------- | ------------- |
| <img src= "https://user-images.githubusercontent.com/24693300/171027764-a2eb623d-8738-410d-b18e-659943ee7051.png" width="360" height="720" /> | <img src= "https://user-images.githubusercontent.com/24693300/171028860-e2b7a5de-3d59-4d24-9fed-207c54c64af9.png" width="360" height="720" />  |

| Search enter space |
| ------------- |
| <img src= "https://user-images.githubusercontent.com/24693300/171076492-2a6c0567-7852-4c15-92b8-b54a668608de.png" width="360" height="720" /> | 

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

- Minimum SDK level 23
- 100% Kotlin
- 使用Koin進行injection.
- Jetpack
  - Lifecycle
  - ViewModel
  - DataBinding
- 使用Coroutines處理搜尋框
- 使用Airbnb Lottie處理Loading動畫
- 使用Gson解析Json
