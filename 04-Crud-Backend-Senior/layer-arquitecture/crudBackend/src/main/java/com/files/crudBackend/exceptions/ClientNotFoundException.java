package com.files.crudBackend.exceptions;

public class ClientNotFoundException extends ClientGlobalException {

    public ClientNotFoundException() {
        super("User Already exists!");
    }
}
