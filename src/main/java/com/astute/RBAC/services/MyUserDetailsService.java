//package com.astute.RBAC.services;
//
//import com.astute.RBAC.entities.Privilege;
//import com.astute.RBAC.entities.Role;
//import com.astute.RBAC.entities.User;
//import com.astute.RBAC.entities.repo.RoleRepo;
//import com.astute.RBAC.entities.repo.UserRepo;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//@Service("userDetailsService")
//@Transactional
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepo userRepository;
//
////    @Autowired
////    private IUserService service;
//
//    @Autowired
//    private MessageSource messages;
//
//    @Autowired
//    private RoleRepo roleRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email)
//      throws UsernameNotFoundException {
//
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            return new org.springframework.security.core.userdetails.User(
//              " ", " ", true, true, true, true,
//              getAuthorities(Arrays.asList(
//                roleRepository.findByName("ROLE_USER"))));
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//          user.getEmail(), user.getPassword(), user.isVerified(), true, true,
//          true, getAuthorities(user.getRoles()));
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(
//      Collection<Role> roles) {
//
//        return getGrantedAuthorities(getPrivileges(roles));
//    }
//
//    private List<String> getPrivileges(Collection<Role> roles) {
//
//        List<String> privileges = new ArrayList<>();
//        List<Privilege> collection = new ArrayList<>();
//        for (Role role : roles) {
//            privileges.add(role.getName());
//            collection.addAll(role.getPrivileges());
//        }
//        for (Privilege item : collection) {
//            privileges.add(item.getName());
//        }
//        return privileges;
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String privilege : privileges) {
//            authorities.add(new SimpleGrantedAuthority(privilege));
//        }
//        return authorities;
//    }
//}