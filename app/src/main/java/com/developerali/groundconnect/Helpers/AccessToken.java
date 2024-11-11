package com.developerali.groundconnect.Helpers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AccessToken {
    private static final String firebaseMessagingScope = "https://www.googleapis.com/auth/firebase.messaging";

    public void getAccessToken(AccessTokenCallback callback) {
        new GetTokenTask(callback).execute();
    }

    private static class GetTokenTask extends AsyncTask<Void, Void, String> {
        private final AccessTokenCallback callback;

        GetTokenTask(AccessTokenCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String jsonString = "{\n" +
                        "  \"type\": \"service_account\",\n" +
                        "  \"project_id\": \"groundconnect-9bace\",\n" +
                        "  \"private_key_id\": \"1a7bd652cc7b06b3da5d3a0034413ee41fe10068\",\n" +
                        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDyw2Z1XJQenUVY\\nV6B9gsPmD57R6VCjJkjcrKT4scgdHbX2wmwtXpSwdzSSZ9ScDG90Ti8elwD6auCM\\nQx7wVtwW/+fNv749wKeqQ8SPjXuy1KBsb3U4XYdXjBNSwal8rwDVYUD3e3KPUsCM\\nf4CI3/5vDdNI1/cH/WSokCNwmRjRvo0UBSZ/4BlFPwvvBJ4HX28r4LCSYvBD5kyN\\nFw0SqpsPL0NiFM5EXVv6v1Jr8jOeOYieiO0c0DGYYkkIEWepEh8H/ZczKz9840db\\nOC8e5V9GgBvMrqaVQufQaAemLDlWWYeQROOfkbVB7WIp7FGPfvTDgsj5/IQiYk/k\\nLqpTdX6nAgMBAAECggEAEDDuHXe+PUh5/olhn91f2XFDQ3bPNQJxq54sJpYc5aw8\\nnaeb9W6wdhCNRhJ8wKenq1cnl1+05f7I2TIE1zzD0xjgSf2234GA7T811ek2zdeW\\nl4TbiZXbWAB0rXlTJ4i4goLJBXiLTHnVc8lxNi/SVW/TnrEgvbytwPxUEMd1O0/b\\n74peYu7SedOisrshLYPC27K118txzfZSYysjNtDar2kEBVNPFlUq5wm+QBYio2KC\\nxGTeX7ot22x4/PchA2HUSUGaMHAsoejoeq62ReartogK+MZHzsQFMI6qzPa9ybol\\nvUPTXi7WzIVt+T3AsB0U/esEJlKjr3XR2KKOCaIVUQKBgQD/St353AQiCJMiygjV\\n8VVKkzHSxLV/VppOVoMCiT5s+m+d0NVoHuekcQM1juxEECistXxNsOrBLWrCT8Q3\\nlZLEWlMTfBJXK62quPpvIh95TmDBp4wcjIgSnuYJ6kB7D+x2CGP7m8nkY/NshI9A\\nPXxDHpnJV1g7i0DPpNsgndipCQKBgQDzb6S/grKXysAk+rUndJJZkssBgA7r5UQB\\n387/HmpGx/vUSNan0mVN3p7P201YmF109CPURfhwf+o1DPvWnPclFXRuKO6PDg8c\\nKO+fgUG7ut/zx3hdlBKg6sQm3supobyxg5HreEKEPUC9NLHvxNEjrP32Pp7HF05n\\nkE1ZUkZGLwKBgQCJGy896oC5unsy+uhl5jeVVpoyhPyox90VZ3by3LyPEKzzR93l\\nDnv/n8ZGdFzFbRw7LTj2DKb3Wqn0c5yA8a6iD5SAaWj8MfQSNUgieWQH5SUnvBEO\\noBbQUVBgUNOShAnNllIEJjX2mAt643J/4MWk0oFrVaKIUBBdtD8PNFbQ6QKBgQCi\\niN850tzFRmUGC7JaNpXkYoNSbBsKW5gNnE4LvlX9DldQxJU1ld93n3TvmR/KUGjN\\nxpgJifI3wuAwLYwUHddRlkKXrpKhRyTFi3t6RwYJfts6W+s8p7nMYRM4VLcsekWP\\nTCZoTnIy8ua/rWhEi2eL51vlRqgii6gYs5tBURKElwKBgQD2Dl2k1cPRa17QuX/U\\nnWoDS9tRrsOdWVL0dYd5sYUFRbnu0Ub6W9PTSayLAjynmylzT1fdjInVEgCnfrtT\\nWrekcB1lz8sEXdxAMKUXe+h5U5vJFtZlJ2rySm/npOn36CCMO2SWkN4ZHSNFBauY\\nCrlZ/u9PRi7Y3dCfUpk5+CYNhQ==\\n-----END PRIVATE KEY-----\\n\",\n" +
                        "  \"client_email\": \"firebase-adminsdk-fhxno@groundconnect-9bace.iam.gserviceaccount.com\",\n" +
                        "  \"client_id\": \"106346972553162645252\",\n" +
                        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-fhxno%40groundconnect-9bace.iam.gserviceaccount.com\",\n" +
                        "  \"universe_domain\": \"googleapis.com\"\n" +
                        "}";
                InputStream stream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
                GoogleCredentials googleCredentials = GoogleCredentials.fromStream(stream)
                        .createScoped(firebaseMessagingScope);
                googleCredentials.refresh();
                return googleCredentials.getAccessToken().getTokenValue();
            } catch (Exception e) {
                Log.e("AccessToken", "getAccessToken: " + e.getLocalizedMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String token) {
            if (callback != null) {
                callback.onTokenReceived(token);
            }
        }
    }

    public interface AccessTokenCallback {
        void onTokenReceived(String token);
    }
}