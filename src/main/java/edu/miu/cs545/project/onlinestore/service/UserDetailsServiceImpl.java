package edu.miu.cs545.project.onlinestore.service;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.List;
import edu.miu.cs545.project.onlinestore.domain.*;
import edu.miu.cs545.project.onlinestore.dto.NewUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import edu.miu.cs545.project.onlinestore.repository.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User updateProfile(NewUserDTO updateUser){
        User user = userRepository.getUserByUsername(updateUser.getUsername());
        if (null!=user) {
            if(!updateUser.getPassword().isEmpty()){
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                final String encryptedPassword = bCryptPasswordEncoder.encode(updateUser.getPassword());
                user.setPassword(encryptedPassword);
            }
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setPhoneNumber(updateUser.getPhoneNumber());
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public String signUpUser(NewUserDTO newUser) {
        try {
            User us = userRepository.getUserByUsername(newUser.getUsername());
            if (null!= us) {
                return "User name already existing";
            }
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            final String encryptedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
            User user = new User();
            user.setUsername(newUser.getUsername());
            user.setPassword(encryptedPassword);
            user.setEmail(newUser.getUsername());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPhoneNumber(newUser.getPhoneNumber());
            List<Role> roles = roleRepository.findRolesByIdIn(newUser.getRoles().
                    stream().map(rol->rol.getId()).collect(Collectors.toList()));
            user.setRoles(new HashSet<>(roles));
            final User createdUser = userRepository.save(user);
            for(Role role:roles){
                switch ((int) role.getId()){
                    case 1:
                        Admin admin = new Admin();
                        user.setEnabled(true);
                        admin.setUser(user);
                        admin.setLevel("1");
                        adminRepository.save(admin);
                        break;
                    case 2:
                        Seller seller = new Seller();
                        seller.setApproved(false);
                        seller.setUser(user);
                        sellerRepository.save(seller);
                        break;
                    case 3:
                        Buyer buyer = new Buyer();
                        buyer.setAccumulatedPoints(0);
                        user.setEnabled(true);
                        buyer.setUser(user);
                        buyerRepository.save(buyer);
                        break;
                }
            }
            return "User Registered successfully.";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (null==user) {
            throw new UsernameNotFoundException("Could not find user with the given name");
        }
        return new UserDetailsImpl(user);
    }
}