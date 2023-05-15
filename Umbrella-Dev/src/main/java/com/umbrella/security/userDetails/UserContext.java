package com.umbrella.security.userDetails;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

public class UserContext implements UserDetails, OAuth2User {

    @Getter
    private String password;

    @Getter
    private final Long id;

    @Getter
    private final String username;

    @Getter
    private final String nickName;

    @Getter
    private final Set<GrantedAuthority> authorities;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    @Getter
    private Map<String, Object> attributes;

    private static final String NAME_ATTRIBUTE = "name";
    private static final String ERROR_MESSAGE = "로그인에 실패하였습니다. 이메일 주소 혹은 비밀번호를 다시 확인해주세요.";

    public UserContext(String username, String password, Long id, String nickName, Set<GrantedAuthority> authorities,
                       boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.nickName = nickName;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public UserContext(String username, String password, Long id, String nickName, Set<GrantedAuthority> authorities) {
        this(username, password, id, nickName, authorities, true, true, true, true);
    }

    public UserContext(String username, String password, Long id, String nickName,
                       Set<GrantedAuthority> authorities, Map<String, Object> attributes,
                       boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.nickName = nickName;
        this.authorities = authorities;
        this.attributes = attributes;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public UserContext(String username, String password, Long id, String nickName, Set<GrantedAuthority> authorities, Map<String, Object> attributes) {
        this(username, password, id, nickName, authorities, attributes, true, true, true, true);
    }

    @Override
    public String getName() {
        return String.valueOf(attributes.get(NAME_ATTRIBUTE));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, ERROR_MESSAGE);

        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());
        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, ERROR_MESSAGE);
            sortedAuthorities.add(grantedAuthority);
        }
        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            }
            if (g1.getAuthority() == null) {
                return 1;
            }
            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }
}