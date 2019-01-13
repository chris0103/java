package com.akkademy;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

public class ParsingActor extends AbstractActor {

    @Override
    public PartialFunction<Object, BoxedUnit> receive() {
        return ReceiveBuilder.match(ParseHtmlArticle.class, msg -> {
            String body = ArticleExtractor.INSTANCE.getText(msg.htmlString);
            sender().tell(new ArticleBody(msg.uri, body), self());
        }).build();
    }
}
