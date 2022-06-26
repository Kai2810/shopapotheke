package com.shopapotheke.validators;

import com.shopapotheke.dto.Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;


@Component
@Slf4j
public class Validator {
    private static ZoneId zone = ZoneId.of("Europe/Paris");

    public static boolean isRepositoryCreatedAfterDate(Repository repo, LocalDate dateOnwards) {
        return !LocalDate.ofInstant(repo.getCreated_at(), zone).isAfter(dateOnwards);
    }
}
