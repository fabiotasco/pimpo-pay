
package pay.pimpo.authentication.configurations;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import pay.pimpo.authentication.entities.User;

/**
 * Customiza as informações de usuário contidas no token JWT.
 *
 * @author fabio.tasco
 */
class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

	@Override
	public OAuth2AccessToken enhance(final OAuth2AccessToken accessToken, final OAuth2Authentication authentication) {
		final User user = (User) authentication.getPrincipal();

		final Map<String, Object> info = new LinkedHashMap<>(accessToken.getAdditionalInformation());

		info.put("documentType", user.getDocumentType());

		final DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);

		return super.enhance(customAccessToken, authentication);
	}

}
