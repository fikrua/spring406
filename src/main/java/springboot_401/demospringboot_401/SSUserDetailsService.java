package springboot_401.demospringboot_401;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service

public class SSUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userRepository.findByUsername(username);
            if(user==null){
                System.out.println("user not found with the provided username"+user.toString());
                return null;
            }
            System.out.println("user from username"+user.toString());
            return new CustomUserDetails(user, getAuthorities(user));

        }catch (Exception e){
            throw new UsernameNotFoundException("user name not found");
        }

    }
    private Set<GrantedAuthority> getAuthorities(User appUser){
        Set<GrantedAuthority> authorities =new HashSet<>();
        for(Role role: appUser.getRoles()){
            GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        System.out.println("user authority are "+authorities.toString());
        return authorities;
    }
}
