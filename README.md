![](https://img.shields.io/badge/Support-Stop-red.svg)      
<img src="https://raw.githubusercontent.com/PW-Chen/most/master/image/Bitcoin_Logo.png?token=AVzoQns6go-OTbnwDE95x-tGhanEvABAks5Y33SVwA%3D%3D" width="10%" height="10%">     

# 規劃
## 建立 ＳＱＬ
* 使用者 SQL user(name, passwd)
* 商家資訊 SQL store(store_number, store_name,store_address,store_btc_address)
* 產品資訊 SQL product(product_number, product_name, product_detail)
* 商家商品庫存 SQL store_product(store_number,product_number,product_quantity)

## 撰寫 android 手機 客戶端 與  商家端
### 建立 NFC 手機 （ 客戶端 ） 與 手機 （ 商家端 ）之間的 資料傳輸
### 建立 商家端 UI
* 撈取 使用者 SQL user 登入
* 撈取 商家資訊 SQL store 顯示
* 撈取 產品資訊 SQL product 和 商家商品庫存 SQL store_product 建立 詳細庫存清單
* 建立 UI 介面  接 上按鈕 編輯數量  生成ＮＦＣ資訊 包括 （比特幣地址、商品號、數量）

### 建立 客戶端 UI
* 建立 UI 介面  啟動 NFC     

# most
This project is based on bitcoinj as the basis for wallet security enhancements and optimization.

Bitcoin systems can be broadly divided into wallet, block chain, transaction, and Bitcoin network. 	
The wallet file stores the private key for the Bitcoin address. Strictly speaking, Bitcoin assets are not stored in Wallet Files. The Bitcoin asset is stored in the Block Chain. So when we want to check their own balance. You do not need to load your own Bitcoin private key. You only need to search for a transaction related to your own Bitcoin Address in the Block Chain. Then sum up all your transaction records to get the balance of your "Bitcoin" address.

Although Bitcoin assets are stored in the block chain. But the storage of Bitcoin wallet is very important. Because of the need for Bitcoin transactions, need to use the bitcoin private key. Bitcoin private keys can authorize all asset transfers.

本計畫是基於 bitcoinj library 作為錢包製作的基礎 再作進一步的安全性的優化。

比特幣整體架構大致可分為地址，區塊鏈，交易和比特幣網絡。
在bitcoin core 下 wallet.dat 存儲比特幣地址的私鑰。 嚴格地說，比特幣資產不會存儲在電子錢包文件中。 比特幣資產存儲在區塊鏈中。 所以當我們想檢查自己所擁有地址餘額。  您只需要在塊鏈中搜索與您自己的比特幣地址相關的交易。 然後總計所有的交易記錄，以獲得您的“比特幣”地址的餘額。

比特幣私鑰可以授權所有資產轉移。



### 本計畫的內容包刮以下子項目:

 * android-app-bitcoin-wallet-master ： 為以 bitcoinj 實現的比特幣手機錢包.

 * BitNFC-master：比特幣簡單的NFC應用實現

 * bitcoin-master：比特幣官方主要的核心錢包

 * bitcoin-app-jdbc：簡單 java 和 SQL 連線

 * bitcoin-py-sql：簡單 python 和 SQL 連線

 * bitcoinj-test：bitcoinj library 測試

 * crypto：一些以 java 實現的加密算法

 * BitBuy：由 jambocoder159 製作 android 端 APP

 * BPCS-GUI-main：為Blockchain-based Payment Collection Supervision system 主要程序
