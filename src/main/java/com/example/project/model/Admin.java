package com.example.project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Admin extends User{



    public Admin() {
        super();
    }
    @Override
    public void resetPwd() {

    }




}
