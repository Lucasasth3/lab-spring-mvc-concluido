package network.webtech.labmvc.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "tbl_user")
public class User {

    @Id
    private String id;

    @Field("username")
    private String username;

    @Field("password")
    private String password;

}
