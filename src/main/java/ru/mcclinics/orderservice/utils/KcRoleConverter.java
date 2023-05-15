//package ru.mcclinics.orderservice.utils;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.core.GrantedAuthority;
//import java.util.Collection;
//import java.util.Map;
//
//public class KcRoleConverter implements Converter<OAuth2ResourceServerProperties.Jwt, Collection<GrantedAuthority>> {
//
//
//    @Override
//    public Collection<GrantedAuthority> convert(OAuth2ResourceServerProperties.Jwt jwt) {
//
//        Map<String, Object> realmAccess = (Map<String, Object>)jwt.get("realm_access");
//
//        return null;
//    }
//}
