package org.quarkchain.web3j.protocol;

import java.math.BigInteger;

import org.quarkchain.web3j.protocol.core.DefaultBlockParameter;
import org.quarkchain.web3j.protocol.core.JsonRpcWeb3j;
import org.quarkchain.web3j.protocol.core.Request;
import org.quarkchain.web3j.protocol.core.request.EthFilter;
import org.quarkchain.web3j.protocol.core.request.TransactionReq;
import org.quarkchain.web3j.protocol.core.response.Call;
import org.quarkchain.web3j.protocol.core.response.EstimateGas;
import org.quarkchain.web3j.protocol.core.response.EthLog;
import org.quarkchain.web3j.protocol.core.response.EthTransaction;
import org.quarkchain.web3j.protocol.core.response.GasPrice;
import org.quarkchain.web3j.protocol.core.response.GetAccountData;
import org.quarkchain.web3j.protocol.core.response.GetBalances;
import org.quarkchain.web3j.protocol.core.response.GetMinorBlock;
import org.quarkchain.web3j.protocol.core.response.GetRootBlock;
import org.quarkchain.web3j.protocol.core.response.GetRootHashConfirmingMinorBlockById;
import org.quarkchain.web3j.protocol.core.response.GetTransactionConfirmedByNumberRootBlocks;
import org.quarkchain.web3j.protocol.core.response.GetTransactionCount;
import org.quarkchain.web3j.protocol.core.response.GetTransactionReceipt;
import org.quarkchain.web3j.protocol.core.response.NetworkInfo;
import org.quarkchain.web3j.protocol.core.response.SendTransaction;

/**
 * JSON-RPC Request object building factory.
 */
public interface Web3j {
	static Web3j build(Web3jService web3jService) {
		return new JsonRpcWeb3j(web3jService);
	}

	Request<?, NetworkInfo> networkInfo();

	Request<?, GetTransactionCount> getTransactionCount(String address);

	Request<?, GetBalances> getBalances(String address);

	Request<?, GetAccountData> getAccountData(String address, DefaultBlockParameter defaultBlockParameter,
			boolean includeOtherShards);

	Request<?, SendTransaction> sendTransaction(TransactionReq transaction);
	
	Request<?, SendTransaction> sendRawTransaction(String signedTransactionData);

	Request<?, Call> call(TransactionReq transaction, DefaultBlockParameter defaultBlockParameter);

	Request<?, EstimateGas> estimateGas(TransactionReq transaction);

	Request<?, GasPrice> gasPrice(String fullShardKey, String tokenID);

	Request<?, GetMinorBlock> getMinorBlockByHeight(BigInteger fullShardId, BigInteger blockNumber, boolean withTx);

	Request<?, GetMinorBlock> getMinorBlockById(String blockId, boolean withTx);

	Request<?, GetRootBlock> getRootBlockByHeight(BigInteger blockNumber);

	Request<?, GetRootBlock> getRootBlockById(String blockId);

	Request<?, EthTransaction> getTransactionById(String transactionHash);

	Request<?, GetTransactionReceipt> getTransactionReceipt(String transactionHash);

	Request<?, EthLog> getLogs(EthFilter ethFilter, String fullShardKey);

	//works when "ENABLE_TRANSACTION_HISTORY": true,
	Request<?, GetRootHashConfirmingMinorBlockById> getRootHashConfirmingMinorBlockById(String minorBlockId);

	Request<?, GetTransactionConfirmedByNumberRootBlocks> getTransactionConfirmedByNumberRootBlocks(
			String transactionId);

}
