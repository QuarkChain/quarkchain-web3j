# QuarkChain Web3j
 
The Java JSON-RPC client for QuarkChain.

## Getting Started

Add the following dependency to your pom.xml:

```xml
<dependency>
	<groupId>org.quarkchain</groupId>
	<artifactId>web3j</artifactId>
	<version>0.0.1</version>
	<scope>system</scope>
	<systemPath>${project.basedir}/lib/quarkchain-web3j.jar</systemPath>
</dependency>
```

Start up a QuarkChain cluster such as [GoQuarkChain](https://github.com/QuarkChain/goquarkchain#development-setup). 
Or connect to devnet with url: http://jrpc.devnet.quarkchain.io:38391.

To send asynchronous requests:

```java
Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:38391/
NetworkInfo network = web3.networkInfo().sendAsync().get();
Info networkInfo = network.getResult();
```

To send synchronous requests:

```java
Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:38391/
NetworkInfo network = web3.networkInfo().send();
Info networkInfo = network.getResult();

```
See [here](/src/sample/sample/Sample.java) for more examples.

## APIs

See [JSON-RPC documentation](https://developers.quarkchain.io/#json-rpc) for API references.


 
 