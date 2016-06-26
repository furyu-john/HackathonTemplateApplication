# HackathonTemplateApplication
ハッカソンでAndroidアプリを作るときに使うテンプレートアプリケーション。

## 目的
ハッカソン参加者がこのリポジトリをクローン（またはフォーク）して、すぐに開発を開始できるようになること。

### ハッカソン参加者の想定
 - Android Studio 2.0 を自分のノートPCにインストールしている。
 - Hello World アプリを実機 or エミュレータで起動できている。
 - プログラミングの経験あり。Android開発の知識は問わない。
 - Javaを利用したことがあると望ましい。

## 目標
### コピペ用テンプレート
以下のテンプレートが同プロジェクト内のコピペですぐに利用できること。
- レイアウト関係
 - [x] LinearLayout
 - [x] TextView、Button、ImageView
 - [x] CheckBox、Switch、ProgressBar
 - [x] ListView、GridViewおよび、カスタムAdapter
- ネットワーク通信
 - [ ] 外部で公開されているAPIの呼び出し
 - [ ] 画像や動画のダウンロード
 - [x] FireBaseを利用したリアルタイム通信
 - [x] google Map API呼び出し 
 - [x] push通知（Firebaseを使ったもの）
- 端末の機能
 - [x] カメラから画像取得
 - [x] GPS
 - [x] 加速度センサー・地磁気センサーで端末向き取得
 - [ ] Bluetooth
 - [ ] 音声再生・録音

### プロジェクト構成
- [ ] 簡単なパッケージ構成
- [x] タイトル画面のテンプレート

## 設計指針
ハッカソンで作成するアプリの特徴は、以下である。
- 非常に短い期間（1〜2日。長くても1週間）で開発する
- プレゼン時のデモ(1〜5分間程度)に耐えれば良い

この特徴およびハッカソン参加者の想定を考慮し、以下の開発指針を採用する。
- Fragmentは使わない
- MVVM、MVPなどは考えない
- Dagger2などのDIは行わない
- 複数端末の対応はしない。デモ用の端末（おそらくxxhdpiかxhdpi）に最適化する
- 自動回転には対応しない。縦向きか横向きに固定する。

ただし、アプリのコンセプトによっては自動回転対応などはありえる。

## 設定ファイルについて
### google mapのキー
```
src
 └ debug
   └ res
     └ values
   　   └ google_maps_api.xml
```
として中身を
```
<resources>
    <string name="google_maps_key" translatable="false" templateMergeStrategy="preserve">
    ほげほげ
    </string>
</resources>
```
とする。（直接AndroidManifestに書いても良い）

### firebaseに使われるgoogle-services.json
app直下においてください。
設定しかしていないので、バックグラウンドにアプリがあるときにしかプッシュが来ない模様
http://qiita.com/SnowMonkey/items/7f08eb0275f2420c29eb

