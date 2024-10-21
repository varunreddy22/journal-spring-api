package com.varun.myJournal.service;

import com.varun.myJournal.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("ravi").password("ravi").build()),
                Arguments.of(User.builder().userName("suraj").password("suraj").build())
                );
    }
}
