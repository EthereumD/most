# most
This project is based on bitcoinj as the basis for wallet security enhancements and optimization.	

Bitcoin systems can be broadly divided into wallet, block chain, transaction, and Bitcoin network. 	
The wallet file stores the private key for the Bitcoin address. Strictly speaking, Bitcoin assets are not stored in Wallet Files. The Bitcoin asset is stored in the Block Chain. So when we want to check their own balance. You do not need to load your own Bitcoin private key. You only need to search for a transaction related to your own Bitcoin Address in the Block Chain. Then sum up all your transaction records to get the balance of your "Bitcoin" address.

Although Bitcoin assets are stored in the block chain. But the storage of Bitcoin wallet is very important. Because of the need for Bitcoin transactions, need to use the bitcoin private key. Bitcoin private keys can authorize all asset transfers. 

android-app-bitcoin-wallet-master：為以bitcoinj 實現的比特幣手機錢包
BitNFC-master：比特幣簡單的NFC應用實現
bitcoin-master：比特幣官方主要的核心錢包