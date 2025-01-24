package org.quarkchain.web3j.crypto;

import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.quarkchain.web3j.protocol.core.request.EvmTransaction;
import org.quarkchain.web3j.protocol.core.request.TxData;
import org.quarkchain.web3j.rlp.RlpString;
import org.quarkchain.web3j.rlp.RlpType;
import org.quarkchain.web3j.utils.Numeric;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionEncoderTest {

    @Test
    public void testAsRlpValues() {
        String toAddress = "0x00aab646f0aad5afac5ed6724434c4439904829400000000";

        TxData txData = new TxData(
                BigInteger.ONE,  
                BigInteger.valueOf(10_000_000_000L), 
                BigInteger.valueOf(21000), 
                toAddress, // to
                BigInteger.valueOf(1_000_000_000L),  
                "0x", 
                "1",  
                "0x9148d72cc71f3d8a8ae93a1e28f22449bd9fc70f00000000",  
                "0x00aab646f0aad5afac5ed6724434c4439904829400000000", 
                "0x", 
                "0x", 
                BigInteger.valueOf(27), 
                new BigInteger("48eafed96f1f9dc716b377260070eb188f12a3a899b69772972c9f7094d453ef", 16), 
                new BigInteger("5c534aa35c576ede5d914e381cf4e5ef8f8f19ebdacdf165aa7b6420ac735bde", 16) 
        );

        txData.setVersion(BigInteger.ZERO);

        EvmTransaction evmTransaction = new EvmTransaction();
        evmTransaction.setData(txData);

        List<RlpType> result = TransactionEncoder.asRlpValuesPub(evmTransaction);
        assertEquals(15, result.size(), "RLP size is 15");
        assertEquals(RlpString.create(BigInteger.ONE), result.get(0),
                "Expected RlpType does not match the actual result.");
        assertEquals(RlpString.create(BigInteger.valueOf(10_000_000_000L)), result.get(1),
                "Expected gasPrice as BigInteger");
        assertEquals(RlpString.create(BigInteger.valueOf(21000)), result.get(2), "Expected gas as BigInteger");

        // to
        RlpString rlpString = (RlpString) result.get(3);
        String actualHex = Numeric.toHexString(rlpString.getBytes());
        System.out.println("Actual content (HEX) = " + actualHex);
        assertEquals(toAddress, actualHex, "To address mismatch");
    }
}