package com.javacode.testtask.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum OperationType {
    DEPOSIT,
    WITHDRAW;
}
