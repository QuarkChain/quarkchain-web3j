pragma solidity ^0.4.24;

contract Token {

	string public  name = "TokenABC";
	string public constant symbol = "ABC";
	uint8 public constant decimals = 18;
	event Transfer(address indexed from, address indexed to, uint256 tokens);
	mapping(address => uint256) balances;
	address owner;

	constructor(uint256 total) public {
		balances[msg.sender] = total;
		owner = msg.sender;
	}

	function balanceOf(address tokenOwner) public view returns(uint256) {
		return balances[tokenOwner];
	}

    function setName(string newName) public {
        require(msg.sender == owner, "only owner can change name");
        name = newName;
    }
    
    function getName() view public returns (string) {
        return name;
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
