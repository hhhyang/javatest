package com.javatest.antlr;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TopoElementQueryListener extends ElementQueryBaseListener {

    private StringBuilder sb = new StringBuilder();

    public String getResult() {
        return sb.toString();
    }

    @Override
    public void enterValueListQuery(ElementQueryParser.ValueListQueryContext ctx) {

        String id = "'" + ctx.ID().getText() + "'";
        String valueList = ctx.VALUE_LIST().getText();
        sb.append("has(" + id + ", within(" + valueList + "))");
    }

    @Override
    public void enterAndQuery(ElementQueryParser.AndQueryContext ctx) {
        log.error("enterAndQuery");
        sb.append("and(");
    }

    @Override
    public void exitAndQuery(ElementQueryParser.AndQueryContext ctx) {
        sb.append(")");
    }

    @Override
    public void enterQueryAnd(ElementQueryParser.QueryAndContext ctx) {
        sb.append(", ");
    }

    @Override
    public void enterValueQuery(ElementQueryParser.ValueQueryContext ctx) {
        String id = "'" + ctx.ID().getText() + "'";
        String value = ctx.VALUE().getText();
        sb.append("has(" + id + ", " + value + ")");
    }

    @Override
    public void enterNotQuery(ElementQueryParser.NotQueryContext ctx) {
        sb.append("not(");
    }

    @Override
    public void exitNotQuery(ElementQueryParser.NotQueryContext ctx) {
        sb.append(")");
    }

    @Override
    public void enterPredicateQuery(ElementQueryParser.PredicateQueryContext ctx) {
        String id = "'" + ctx.ID().getText() + "'";
        String predicate = ctx.predicate().getText();
        sb.append("has(" + id + ", " + predicate + ")");
    }

    @Override
    public void enterOrQuery(ElementQueryParser.OrQueryContext ctx) {
        sb.append("or(");
    }

    @Override
    public void exitOrQuery(ElementQueryParser.OrQueryContext ctx) {
        sb.append(")");
    }

    @Override
    public void enterQueryOr(ElementQueryParser.QueryOrContext ctx) {
        sb.append(", ");
    }
}
