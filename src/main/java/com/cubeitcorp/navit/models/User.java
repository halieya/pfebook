package com.cubeitcorp.navit.models;






import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    
    private String id;

    private String fullname;
    
    private String username;

  
    private String email;
    
    @Indexed(unique = true , direction = IndexDirection.DESCENDING)
    private String filter;

  
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    
}
