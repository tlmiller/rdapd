package net.apnic.whowas.entity.config;

import java.util.stream.Stream;

import net.apnic.whowas.history.ObjectClass;
import net.apnic.whowas.rdap.Entity;
import net.apnic.whowas.search.RegexSearchIndex;
import net.apnic.whowas.search.WildCardSearchIndex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityConfiguration
{
    @Bean
    public WildCardSearchIndex entityHandleWildCardSearchIndex()
    {
        return new WildCardSearchIndex(ObjectClass.ENTITY, "handle",
            (rev, objectKey) -> Stream.of(objectKey.getObjectName()));
    }

    @Bean
    public RegexSearchIndex entityHandleRegexSearchIndex()
    {
        return new RegexSearchIndex(ObjectClass.ENTITY, "handle",
            (rev, objectKey) -> Stream.of(objectKey.getObjectName()));
    }

    @Bean
    public WildCardSearchIndex entityFNWildCardSearchIndex()
    {
        return new WildCardSearchIndex(ObjectClass.ENTITY, "fn",
            (rev, objectKey) ->
            {
                return ((Entity)rev.getContents())
                    .getVCard()
                    .findVCardAttribute("fn")
                    .map(vcard ->
                    {
                        return vcard.getValue().toString();
                    });
            });
    }

    @Bean
    public RegexSearchIndex entityFNRegexSearchIndex()
    {
        return new RegexSearchIndex(ObjectClass.ENTITY, "fn",
            (rev, objectKey) ->
            {
                return ((Entity)rev.getContents())
                    .getVCard()
                    .findVCardAttribute("fn")
                    .map(vcard ->
                    {
                        return vcard.getValue().toString();
                    });
            });
    }
}