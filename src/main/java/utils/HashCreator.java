package utils;

import utils.api.IHashCreator;

public class HashCreator implements IHashCreator {

    public String createHash(String passwordWithSalt) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(passwordWithSalt);
    }
}
