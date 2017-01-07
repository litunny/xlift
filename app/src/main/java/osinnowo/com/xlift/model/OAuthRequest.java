package osinnowo.com.xlift.model;

/**
 * Created by upperlink on 07/01/2017.
 */

public class OAuthRequest {

        private String grant_type;
        private String scope;

        public String getGrantType() {
            return grant_type;
        }

        public void setGrantType(String grantType) {
            this.grant_type = grantType;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }
}
