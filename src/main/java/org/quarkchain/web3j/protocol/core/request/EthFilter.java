package org.quarkchain.web3j.protocol.core.request;

import java.util.Arrays;
import java.util.List;

import org.quarkchain.web3j.protocol.core.DefaultBlockParameter;

public class EthFilter extends Filter<EthFilter> {
	private DefaultBlockParameter fromBlock; // optional, params - defaults to latest for both
	private DefaultBlockParameter toBlock;
	private List<String> address; // specification implies this can be single address as string or list

	public EthFilter() {
		super();
	}

	public EthFilter(DefaultBlockParameter fromBlock, DefaultBlockParameter toBlock, List<String> address) {
		super();
		this.fromBlock = fromBlock;
		this.toBlock = toBlock;
		this.address = address;
	}

	public EthFilter(DefaultBlockParameter fromBlock, DefaultBlockParameter toBlock, String address) {
		this(fromBlock, toBlock, Arrays.asList(address));
	}

	public DefaultBlockParameter getFromBlock() {
		return fromBlock;
	}

	public DefaultBlockParameter getToBlock() {
		return toBlock;
	}

	public List<String> getAddress() {
		return address;
	}

	@Override
	Filter getThis() {
		return this;
	}
}
