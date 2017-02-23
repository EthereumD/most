# most
This project is based on bitcoinj as the basis for wallet security enhancements and optimization.	

Bitcoin systems can be broadly divided into wallet, block chain, transaction, and Bitcoin network. 	
The wallet file stores the private key for the Bitcoin address. Strictly speaking, Bitcoin assets are not stored in Wallet Files. The Bitcoin asset is stored in the Block Chain. So when we want to check their own balance. You do not need to load your own Bitcoin private key. You only need to search for a transaction related to your own Bitcoin Address in the Block Chain. Then sum up all your transaction records to get the balance of your "Bitcoin" address.

Although Bitcoin assets are stored in the block chain. But the storage of Bitcoin wallet is very important. Because of the need for Bitcoin transactions, need to use the bitcoin private key. Bitcoin private keys can authorize all asset transfers. 

比特幣系統可以大致分為錢包，區塊鏈，交易和比特幣網絡。
錢包文件存儲比特幣地址的私鑰。 嚴格地說，比特幣資產不會存儲在電子錢包文件中。 比特幣資產存儲在塊鏈中。 所以當我們想檢查自己的平衡。 你不需要加載你自己的唯一的比特幣私鑰。 您只需要在塊鏈中搜索與您自己的比特幣地址相關的交易。 然後總計所有的交易記錄，以獲得您的“比特幣”地址的餘額。

比特幣私鑰可以授權所有資產轉移。 請使用bitcoin私鑰。 比特幣私鑰可以授權所有資產轉移。

此項目的內容包刮以下子項目:

 * __android-app-bitcoin-wallet-master__：
    為以bitcoinj 實現的比特幣手機錢包.
 * __BitNFC-master__：
    比特幣簡單的NFC應用實現
 * __bitcoin-master__：
    比特幣官方主要的核心錢包
 * __bitcoin-app-jdbc__：
    簡單java 和 SQL 連線
 * __bitcoinj-test__：
    bitcoinj library 測試
 * __crypto__：
    一些以java實現的加密算法
 * __BitBuy__：
    由jambocoder159製作