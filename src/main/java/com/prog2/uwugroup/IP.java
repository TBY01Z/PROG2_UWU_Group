package com.prog2.uwugroup;

public class IP {
    private int firstOctet;
    private int secondOctet;
    private int thirdOctet;
    private int fourthOctet;
    private int port;

    public IP(int firstOctet, int secondOctet, int thirdOctet, int fourthOctet, int port) {
        this.firstOctet = firstOctet;
        this.secondOctet = secondOctet;
        this.thirdOctet = thirdOctet;
        this.fourthOctet = fourthOctet;
        this.port = port;
    }

    public int firstOctet() {
        return firstOctet;
    }

    public IP setFirstOctet(int firstOctet) {
        this.firstOctet = firstOctet;
        return this;
    }

    public int secondOctet() {
        return secondOctet;
    }

    public IP setSecondOctet(int secondOctet) {
        this.secondOctet = secondOctet;
        return this;
    }

    public int thirdOctet() {
        return thirdOctet;
    }

    public IP setThirdOctet(int thirdOctet) {
        this.thirdOctet = thirdOctet;
        return this;
    }

    public int fourthOctet() {
        return fourthOctet;
    }

    public IP setFourthOctet(int fourthOctet) {
        this.fourthOctet = fourthOctet;
        return this;
    }

    public int port() {
        return port;
    }

    public IP setPort(int port) {
        this.port = port;
        return this;
    }
}
