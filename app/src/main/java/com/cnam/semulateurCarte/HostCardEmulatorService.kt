package com.cnam.semulateurCarte

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log

/**
 * Created by simad
 */
class HostCardEmulatorService: HostApduService() {

    companion object {
        val TAG = "Host Card Emulator"
        val CARD_NUMBER = "5591413456789123"
        val STATUS_FAILED = "0"
        val AID = "A0000002471001"
    }

    override fun onDeactivated(reason: Int) {
        Log.d(TAG, "Deactivated: " + reason)
    }

    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        if (commandApdu == null) {
            return Utils.hexStringToByteArray(STATUS_FAILED)
        }

        val hexCommandApdu = Utils.toHex(commandApdu)

        if (hexCommandApdu.substring(10, 24) == AID)  {
            return Utils.hexStringToByteArray(CARD_NUMBER)
        } else {
            return Utils.hexStringToByteArray(STATUS_FAILED)
        }
    }
}