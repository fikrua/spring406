package springboot_401.demospringboot_401;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private  User user;
    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities){
        super(user.getUsername(),user.getPassword(),authorities);
        this.user=user;
    }
    public CustomUserDetails(User user,String password,Collection<? extends GrantedAuthority> authorities){

        super(user.getUsername(),user.getPassword(),authorities);
        this.user=user;
    }
    public CustomUserDetails (User user, boolean enabled,
                              boolean accountNonExpired,
                              boolean credentialNonExpired,
                              boolean accountNonLocked,
                              Collection<? extends GrantedAuthority> authorities){
        super(user.getUsername(),user.getPassword(),enabled,accountNonExpired,credentialNonExpired,accountNonLocked,authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
