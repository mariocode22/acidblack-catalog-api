package com.acidblack.catalogo_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions.BigDecimalRepresentation;

@Configuration
public class MongoConfig {

    // Por defecto BigDecimal se guarda como String; tus documentos usan NumberDecimal (Decimal128).
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return MongoCustomConversions.create(
                config -> config.bigDecimal(BigDecimalRepresentation.DECIMAL128));
    }
}