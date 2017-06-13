# spring-autowireproblem-workaround

This is a workaround for Spring Framework [SPR-13522](https://jira.spring.io/browse/SPR-13522) issue.

Issue summary: @Autowire for configurable components fails in tests with lambdas when using load-time weaving.
