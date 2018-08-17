package com.google.bitcoin.core;

import com.google.bitcoin.bouncycastle.util.encoders.Hex;
import com.google.bitcoin.core.*;
import com.google.bitcoin.store.MemoryBlockStore;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        NetworkParameters n = new NetworkParameters();
        Block genesisBlock = new Block(n);
        Transaction t = new Transaction(n);
        try {
            // A script containing the difficulty bits and the following message:
            //
            //   "The Times 03/Jan/2009 Chancellor on brink of second bailout for banks"
            byte[] bytes = Hex.decode
                    ("04ffff001d0104455468652054696d65732030332f4a616e2f32303039204368616e63656c6c6f72206f6e206272696e6b206f66207365636f6e64206261696c6f757420666f722062616e6b73");
            t.inputs.add(new TransactionInput(n, t, bytes));
            ByteArrayOutputStream scriptPubKeyBytes = new ByteArrayOutputStream();
            Script.writeBytes(scriptPubKeyBytes, Hex.decode
                    ("04678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5f"));
            scriptPubKeyBytes.write(Script.OP_CHECKSIG);
            t.outputs.add(new TransactionOutput(n, scriptPubKeyBytes.toByteArray()));
        } catch (Exception e) {
            // Cannot happen.
        }
        genesisBlock.addTransaction(t);
        System.out.println(genesisBlock.toString());
        /*BlockChain chain = new BlockChain(n, new Wallet(n), new MemoryBlockStore(n));
        try {
            chain.add(genesisBlock);
        } catch (VerificationException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }*/
        Block block1 = new Block(n);
        Wallet wallet1 = new Wallet(n);
        System.out.println("wallet 1:" + wallet1.toString());
        Wallet wallet2 = new Wallet(n);
        System.out.println("wallet 2:" + wallet2.toString());
        //block1.addTransaction(wallet1.createSend(wallet2.p));
    }
}
