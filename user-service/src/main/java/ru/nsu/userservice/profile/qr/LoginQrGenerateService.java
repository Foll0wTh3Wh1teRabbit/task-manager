package ru.nsu.userservice.profile.qr;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface LoginQrGenerateService {

    byte[] generateQrCode(String principal) throws WriterException, IOException;

}
