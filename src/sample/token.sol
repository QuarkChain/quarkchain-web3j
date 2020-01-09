pragma solidity ^0.4.24;

contract Token {

	string public constant name = "TokenABC";
	string public constant symbol = "ABC";
	uint8 public constant decimals = 18;
	event Transfer(address indexed from, address indexed to, uint256 tokens);
	mapping(address => uint256) balances;

	constructor(uint256 total) public {
		balances[msg.sender] = total;
	}

	function balanceOf(address tokenOwner) public view returns(uint256) {
		return balances[tokenOwner];
	}

	function transfer(address receiver, uint256 amount) public returns(bool) {
		require(amount <= balances[msg.sender]);
		balances[msg.sender] = balances[msg.sender] - amount;
		assert(balances[receiver] + amount > balances[receiver]);
		balances[receiver] = balances[receiver] + amount;
		emit Transfer(msg.sender, receiver, amount);
		return true;
	}
}
