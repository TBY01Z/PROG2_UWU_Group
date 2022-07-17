package com.prog2.uwugroup;

/**
 * Diese Klasse zeigt eine IP und wird zur realiesierung einer Ip genutzt.
 */
public class IP {
    private final int firstOctet;
    private final int secondOctet;
    private final int thirdOctet;
    private final int fourthOctet;
    private final int port;

    /**
     * @param firstOctet Erstes Ocktet der IP Adresse
     * @param secondOctet Zweites Ocktet der IP Adresse
     * @param thirdOctet Drittes Ocktet der IP Adresse
     * @param fourthOctet Viertes Ocktet der IP Adresse
     * @param port Der port der Angesteuert werden soll
     */
    public IP(int firstOctet, int secondOctet, int thirdOctet, int fourthOctet, int port) {
        this.firstOctet = firstOctet;
        this.secondOctet = secondOctet;
        this.thirdOctet = thirdOctet;
        this.fourthOctet = fourthOctet;
        this.port = port;
    }

    /**
     * @return Gibt das Erste Ocktet als int zurueck.
     */
    public int firstOctet() {
        return firstOctet;
    }

    /**
     * @return Gibt das Zweite Ocktet als int zurueck.
     */
    public int secondOctet() {
        return secondOctet;
    }

    /**
     * @return Gibt das Dritte Ocktet als int zurueck.
     */
    public int thirdOctet() {
        return thirdOctet;
    }

    /**
     * @return Gibt das Vierte Ocktet als int zurueck.
     */
    public int fourthOctet() {
        return fourthOctet;
    }

    /**
     * @return Gibt den Port als int zurueck.
     */
    public int port() {
        return port;
    }
}
