# 老鼠的翻譯模組 / Mouse's Translation Mod

* **安裝教學影片 / Installation Tutorial (Mandarin):** [https://www.youtube.com/watch?v=Lk19SDxXrVc](https://www.youtube.com/watch?v=Lk19SDxXrVc)

## 安裝步驟一覽 / Installation Steps

1. **前往 Google Script** / Head to [Google Apps Script](https://script.google.com/home).
2. **點擊「新增專案」** / Click **"New Project"**.
3. **將以下程式碼複製貼上** / Paste the following code into the editor:

```js
var mock = {
  parameter:{
    q:'hello',
    source:'en',
    target:'fr'
  }
};

function doGet(e) {
  e = e || mock;

  var sourceText = '';
  if (e.parameter.q){
    sourceText = e.parameter.q;
  }

  var sourceLang = '';
  if (e.parameter.source){
    sourceLang = e.parameter.source;
  }

  var targetLang = 'en';
  if (e.parameter.target){
    targetLang = e.parameter.target;
  }

  var translatedText = LanguageApp.translate(sourceText, sourceLang, targetLang, {contentType: 'html'});

  return ContentService.createTextOutput(translatedText).setMimeType(ContentService.MimeType.TEXT);
}

```

4. **點擊「部署」→「新增部署作業」** / Click **"Deploy"** > **"New Deployment"**.
5. **選取類型為「網頁應用程式」** / Select **"Web app"** as the deployment type.
6. **將「誰可以存取」設定為「所有人」** / Set **"Who has access"** to **"Anyone"**.
7. **點擊「部署」後，將生成的網址貼到 `/mtr` 設定中的「Google Script 連結網址」中** / After deploying, copy the **Web App URL** and paste it into the **"Google Script URL"** field in the `/mtr` config screen.

## 功能 / Features

* **翻譯遊戲中的任何文字** / Translate any in-game text (Chat, GUI, Signs, etc.).
* **快捷鍵快速切換** / Key bindings to quickly toggle the translation function.
* **多國語言支持** / Supports a wide range of languages via Google Translate. (繁體中文、简体中文、日本語、English)
