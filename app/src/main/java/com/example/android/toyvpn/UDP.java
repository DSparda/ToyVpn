package com.example.android.toyvpn;

import java.nio.ByteBuffer;

public class UDP {
    private final Integer sourcePort;
    private final Integer destinationPort;
    private final Integer length;
    private final Integer checksum;

    private DNS dns;
    private final ByteBuffer packet;

    public UDP(ByteBuffer packet) {
        this.packet = packet;

        sourcePort = get16Bits();
        destinationPort = get16Bits();
        length = get16Bits();
        checksum = get16Bits();

        if (sourcePort.intValue() == 53 || destinationPort.intValue() == 53) {
            dns = new DNS(packet);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(sport: " + sourcePort.toString());
        sb.append(", dport: " + destinationPort.toString() + ")");

        return sb.toString();
    }

    private int get16Bits() {
        return packet.getShort() & 0xFFFF;
    }
}
