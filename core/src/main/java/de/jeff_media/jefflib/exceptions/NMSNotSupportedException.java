package de.jeff_media.jefflib.exceptions;

import de.jeff_media.jefflib.JeffLib;

public class NMSNotSupportedException extends RuntimeException {

    public static void check() throws NMSNotSupportedException {
        if(JeffLib.getPlugin() == null) {
            throw new JeffLibNotInitializedException();
        }
        if(JeffLib.getNMSHandler() == null) {
            throw new NMSNotSupportedException();
        }
    }

    public NMSNotSupportedException() {
        super("Could not find an NMS handler for the current Minecraft version.");
    }

}
