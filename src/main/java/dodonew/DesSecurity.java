package dodonew;

import android.util.Base64;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes.dex */
public class DesSecurity {
    Cipher deCipher;
    Cipher enCipher;

    public DesSecurity(String key, String iv) throws Exception {
        if (key == null) {
            throw new NullPointerException("Parameter is null!");
        }
        InitCipher(key.getBytes(), iv.getBytes());
    }

    private void InitCipher(byte[] secKey, byte[] secIv) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(secKey);
        DESKeySpec dsk = new DESKeySpec(md.digest());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dsk);
        IvParameterSpec iv = new IvParameterSpec(secIv);
        this.enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        this.deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        this.enCipher.init(1, key, iv);
        this.deCipher.init(2, key, iv);
    }

    public String encrypt64(byte[] data) throws Exception {
        return Base64.encodeToString(this.enCipher.doFinal(data), 0);
    }
}